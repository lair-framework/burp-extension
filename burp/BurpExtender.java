/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lair.models.Constants;
import lair.models.Host;
import lair.models.HostRef;
import lair.models.PluginId;
import lair.models.Port;
import lair.models.Project;
import lair.models.Vulnerability;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;

/**
 *
 * @author dkottmann
 */
public class BurpExtender implements IBurpExtender, ITab, IContextMenuFactory, ActionListener {

    private IBurpExtenderCallbacks callbacks;
    private JPanel mainPanel = new JPanel();
    private JTextField pidText = new JTextField();
    private JTextField mongoText = new JTextField();
    private PrintWriter stdout = null;
    private PrintWriter stderr = null;
    private IScanIssue[] selectedIssues = null;
    private Pattern ipPattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        callbacks.setExtensionName("Lair");
        callbacks.registerContextMenuFactory(this);

        stdout = new PrintWriter(callbacks.getStdout());
        stderr = new PrintWriter(callbacks.getStderr());

        JLabel pidLabel = new JLabel();
        JLabel mongoLabel = new JLabel();

        String mongoUrl = System.getenv("MONGO_URL");
        if (mongoUrl == null || mongoUrl.equalsIgnoreCase("")) {
            mongoUrl = "";
        }
        this.mongoText.setText(mongoUrl);

        // TODO: Read MONGO_URL from env and assign to mongoText
        this.mainPanel.setLayout(new GridBagLayout());
        pidLabel.setText("Project ID");
        pidLabel.setOpaque(true);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 15, 0, 0);
        this.mainPanel.add(pidLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 22, 0, 478);
        this.mainPanel.add(this.pidText, gridBagConstraints);

        mongoLabel.setText("Mongo URL");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(24, 15, 0, 0);
        this.mainPanel.add(mongoLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 22, 509, 478);
        this.mainPanel.add(this.mongoText, gridBagConstraints);

        callbacks.customizeUiComponent(pidLabel);
        callbacks.customizeUiComponent(mongoLabel);
        callbacks.customizeUiComponent(this.pidText);
        callbacks.customizeUiComponent(this.mongoText);

        callbacks.addSuiteTab(this);
    }

    @Override
    public String getTabCaption() {
        return "Lair Configuration";
    }

    @Override
    public Component getUiComponent() {
        return this.mainPanel;
    }

    @Override
    public ArrayList<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        ArrayList<JMenuItem> menu = new ArrayList<JMenuItem>();
        byte ctx = invocation.getInvocationContext();
        // Only show context menu for scanner results...
        if (ctx == IContextMenuInvocation.CONTEXT_SCANNER_RESULTS) {
            this.selectedIssues = invocation.getSelectedIssues();
            JMenuItem item = new JMenuItem("Send to Lair", null);
            item.addActionListener(this);
            menu.add(item);
        }
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String mongoUrl = this.mongoText.getText();
        if (mongoUrl == null || mongoUrl.equalsIgnoreCase("")) {
            this.callbacks.issueAlert("Missing required configuration: MONGO_URL");
            return;
        }

        String pid = this.pidText.getText();
        if (pid == null || pid.equalsIgnoreCase("")) {
            this.callbacks.issueAlert("Missing required configuration: Project ID");
            return;
        }

        sendToLair(pid, mongoUrl);

    }

    private void sendToLair(String pid, String mongoUrl) {
        MongoClient m = null;
        try {
            MongoClientURI uri = new MongoClientURI(mongoUrl);
            m = connect(uri);
            DB db = m.getDB(uri.getDatabase());

            // Retrieve project, ensure it exists
            DBCollection projects = db.getCollection("projects");
            projects.setObjectClass(Project.class);
            BasicDBObject doc = new BasicDBObject("_id", pid);
            Project project = (Project) projects.findOne(doc);
            if (project == null) {
                throw new Exception("Project does not exist in Lair");
            }

            // Retrieve host, ensure it exists
            DBCollection hosts = db.getCollection("hosts");
            hosts.setObjectClass(Host.class);
            for (IScanIssue issue : this.selectedIssues) {
                String h = issue.getHttpService().getHost();
                InetAddress addr = InetAddress.getByName(issue.getHttpService().getHost());
                Matcher ipMatcher = this.ipPattern.matcher(h);
                boolean ipLookup = false;
                if (ipMatcher.matches()) {
                    // Scan result is by IP, do lookup by IP in Lair
                    doc = new BasicDBObject("project_id", pid).append("string_addr", h);
                    ipLookup = true;
                } else {
                    // Scan result is by FQDN, do lookup by hostname in Lair
                    doc = new BasicDBObject("project_id", pid).append("hostnames", h);
                }

                DBCursor cursor = hosts.find(doc);
                if (ipLookup && (cursor == null || cursor.count() != 1)) {
                    // Expect 1 and only 1 host result if lookup by IP
                    throw new Exception("Error retrieving host by IP. Does " + h + " exist in your Lair project?");
                }

                if (!ipLookup && (cursor == null || cursor.count() == 0)) {
                    // Expect 1 or more hosts if lookup by hostname
                    throw new Exception("Error retrieving host by name. Does " + h + " exist in your Lair project?");
                }

                // Retrieve port for each host, insert it if it doesn't exist
                DBCollection ports = db.getCollection("ports");
                ports.setObjectClass(Port.class);
                Port port = null;
                while (cursor.hasNext()) {
                    Host host = (Host) cursor.next();
                    int p = issue.getHttpService().getPort();
                    doc = new BasicDBObject("project_id", pid).append("host_id", host.getId()).append("port", p);
                    port = (Port) ports.findOne(doc);
                    if (port == null) {
                        // Port doesn't exist for host, add it
                        port = new Port();
                        port.setAlive(true);
                        port.setHostId(host.getId());
                        port.setId(new ObjectId().toString());
                        port.setPort(p);
                        port.setProjectId(pid);
                        ports.insert(port);
                        this.callbacks.issueAlert("Added new port for host " + host.getStringAddr());
                    }

                    // Retrieve vulnerability, adding it if it doesn't exist
                    DBCollection vulns = db.getCollection("vulnerabilities");
                    vulns.setObjectClass(Vulnerability.class);
                    Vulnerability vuln = null;
                    BasicDBObject pluginDoc = new BasicDBObject("tool", Constants.TOOL).append("id", String.valueOf(issue.getIssueType()));
                    doc = new BasicDBObject("project_id", pid).append("plugin_ids", pluginDoc);
                    vuln = (Vulnerability) vulns.findOne(doc);
                    if (vuln == null) {
                        // Vulnerability doesn't exist, add it
                        vuln = new Vulnerability();
                        vuln.setId(new ObjectId().toString());
                        vuln.setProjectId(pid);
                        vuln.setTitle(issue.getIssueName());
                        vuln.setDescription(issue.getIssueBackground() != null ? Jsoup.parse(issue.getIssueBackground()).text() : "");
                        vuln.setSolution(issue.getRemediationBackground() != null ? Jsoup.parse(issue.getRemediationBackground()).text() : "");
                        String sev = issue.getSeverity();
                        if (sev.equals("High")) {
                            vuln.setCvss(10.0);
                        } else if (sev.equals("Medium")) {
                            vuln.setCvss(5.0);
                        } else if (sev.equals("Low")) {
                            vuln.setCvss(3.0);
                        } else {
                            vuln.setCvss(0.0);
                        }
                        PluginId pluginId = new PluginId();
                        pluginId.setId(Integer.toString(issue.getIssueType()));
                        pluginId.setTool(Constants.TOOL);
                        ArrayList<PluginId> pluginIds = new ArrayList<PluginId>();
                        pluginIds.add(pluginId);
                        vuln.setPluginIds(pluginIds);
                        vuln.setIdentifiedBy(pluginIds);
                        HostRef hostRef = new HostRef();
                        hostRef.setPort(issue.getHttpService().getPort());
                        hostRef.setStringAddr(host.getStringAddr());
                        ArrayList<HostRef> hostList = new ArrayList<HostRef>();
                        hostList.add(hostRef);
                        vuln.setHosts(hostList);
                        vuln.setLastModifiedBy(Constants.TOOL);
                        String evidence = (issue.getIssueDetail() != null ? Jsoup.parse(issue.getIssueDetail()).text() : "None");
                        vuln.setEvidence("\n" + host.getStringAddr() + ":\n" + evidence);
                        vulns.insert(vuln);
                        this.callbacks.issueAlert("Added new vulnerability");
                    } else {
                        // Vuln exists in database, is host/port already tied to it?
                        boolean isPresent = false;
                        for (HostRef hostRef : vuln.getHosts()) {
                            if (hostRef.getStringAddr().equals(host.getStringAddr())
                                    && hostRef.getPort() == issue.getHttpService().getPort()) {
                                isPresent = true;
                            }
                        }

                        if (!isPresent) {
                            // Host/port isn't tied to vuln, add host and save
                            HostRef hostRef = new HostRef();
                            hostRef.setPort(issue.getHttpService().getPort());
                            hostRef.setStringAddr(host.getStringAddr());
                            ArrayList<HostRef> hostList = vuln.getHosts();
                            hostList.add(hostRef);
                            vuln.setHosts(hostList);
                            String evidence = (issue.getIssueDetail() != null ? Jsoup.parse(issue.getIssueDetail()).text() : "None");
                            vuln.setEvidence(vuln.getEvidence() + "\n\n" + host.getStringAddr() + ":\n" + evidence);
                            vulns.save(vuln);
                            this.callbacks.issueAlert("Added host to existing vulnerability");
                        } else {
                            // Doing nothing, host already exists for that vuln
                            this.callbacks.issueAlert("Doing nothing. Host/port already exists for that vulnerability");
                        }
                    }
                }
            }

        } catch (UnknownHostException e) {
            this.callbacks.issueAlert("Unable to connect to Mongo host URL/Port");
        } catch (Exception e) {
            this.callbacks.issueAlert(e.getMessage());
        } finally {
            if (m != null) {
                m.close();
            }
        }
    }

    private MongoClient connect(MongoClientURI mongoUrl) throws UnknownHostException {
        this.callbacks.issueAlert("Using truststore: " + System.getProperty("javax.net.ssl.trustStore"));
        return new MongoClient(mongoUrl);
    }

}

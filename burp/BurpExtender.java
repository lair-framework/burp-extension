/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burp;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import lair.models.Command;
import lair.models.Constants;
import lair.models.Host;
import lair.models.IdentifiedBy;
import lair.models.Issue;
import lair.models.IssueHost;
import lair.models.Note;
import lair.models.OS;
import lair.models.PluginId;
import lair.models.Project;
import lair.models.Service;
import lair.models.WebDirectory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

/**
 *
 * @author dkottmann
 */
public class BurpExtender implements IBurpExtender, ITab, IContextMenuFactory, ActionListener {

    private final String apiUrl = "/api/projects/";
    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private JPanel mainPanel = new JPanel();
    private JTextField pidText = new JTextField();
    private JTextField lairApiServerText = new JTextField();
    private PrintWriter stdout = null;
    private PrintWriter stderr = null;
    private IScanIssue[] selectedIssues = null;
    private Pattern ipPattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
    private Pattern apiPattern = Pattern.compile("(?<protocol>http|https)://(?<user>.*?):(?<password>.*)@(?<authority>.*$)");

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        callbacks.setExtensionName("Lair");
        callbacks.registerContextMenuFactory(this);

        helpers = callbacks.getHelpers();

        stdout = new PrintWriter(callbacks.getStdout());
        stderr = new PrintWriter(callbacks.getStderr());

        JLabel pidLabel = new JLabel();
        JLabel lairApiServerLabel = new JLabel();

        String lairApiServer = System.getenv("LAIR_API_SERVER");
        if (lairApiServer == null || lairApiServer.equalsIgnoreCase("")) {
            lairApiServer = "";
        }
        this.lairApiServerText.setText(lairApiServer);

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

        lairApiServerLabel.setText("Lair API Server");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(24, 15, 0, 0);
        this.mainPanel.add(lairApiServerLabel, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 22, 509, 478);
        this.mainPanel.add(this.lairApiServerText, gridBagConstraints);

        callbacks.customizeUiComponent(pidLabel);
        callbacks.customizeUiComponent(lairApiServerLabel);
        callbacks.customizeUiComponent(this.pidText);
        callbacks.customizeUiComponent(this.lairApiServerText);

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
        String lairApiServer = this.lairApiServerText.getText();
        if (lairApiServer == null || lairApiServer.equalsIgnoreCase("")) {
            this.callbacks.issueAlert("Missing required configuration: LAIR_API_SERVER");
            return;
        }

        String pid = this.pidText.getText();
        if (pid == null || pid.equalsIgnoreCase("")) {
            this.callbacks.issueAlert("Missing required configuration: Project ID");
            return;
        }

        try {
            sendToLair(pid, lairApiServer);
        } catch (IOException ex) {
            this.callbacks.issueAlert("Error closing HTTP connection");
            return;
        } catch (Exception ex) {
            this.callbacks.issueAlert(ex.getMessage());
            return;
        }
    }

    private void sendToLair(String pid, String lairApiServer) throws IOException, Exception {
        Matcher apiMatcher = this.apiPattern.matcher(lairApiServer);
        if (!apiMatcher.matches()) {
            throw new Exception("Error : Invalid format for LAIR_API_SERVER. Expecting 'http://user:password@host:port'.");
        }
        String username = apiMatcher.group("user");
        String password = apiMatcher.group("password");
        String protocol = apiMatcher.group("protocol");
        String authority = apiMatcher.group("authority");

        URL lairURL = new URL(protocol + "://" + authority);
        String lairHost = lairURL.getHost();
        int lairPort = lairURL.getPort();

        RequestConfig cfg = RequestConfig.custom().setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(50000)
                .build();
        HttpHost target = new HttpHost(lairHost, lairPort, protocol);
        CredentialsProvider creds = new BasicCredentialsProvider();
        creds.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(username, password)
        );

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(creds).build();
        try {

            AuthCache authCache = new BasicAuthCache();
            BasicScheme basicAuth = new BasicScheme();
            authCache.put(target, basicAuth);
            HttpClientContext localContext = HttpClientContext.create();
            localContext.setAuthCache(authCache);
            HttpPatch req = new HttpPatch(protocol + "://" + lairHost + ":" + lairPort + this.apiUrl + pid);

            req.setConfig(cfg);
            Gson gson = new Gson();

            Project project = new Project();
            project.setId(pid);
            Command cmd = new Command();
            cmd.setCommand("Manual issue import");
            cmd.setTool(Constants.TOOL);
            project.getCommands().add(cmd);
            project.setTool(Constants.TOOL);
            HashMap<String, Host> hostMap = new HashMap<String, Host>();
            HashMap<String, Issue> issueMap = new HashMap<String, Issue>();
            for (IScanIssue issue : this.selectedIssues) {
                String h = issue.getHttpService().getHost();
                Matcher ipMatcher = this.ipPattern.matcher(h);
                ArrayList<Host> hosts = new ArrayList<Host>();
                if (!ipMatcher.matches()) {

                    // This is an insert by hostname, we need IPs from lair so send request to get ips by hostname.
                    // It may be possible that multiple IPs are tied to the same host name.
                    String u = protocol + "://" + lairHost + ":" + lairPort + this.apiUrl + pid + "/hosts?hostname=" + h;
                    HttpGet hostReq = new HttpGet(u);
                    hostReq.setConfig(cfg);
                    hostReq.setHeader("Content-Type", "application/json");

                    ResponseHandler<String> resHandler = new ResponseHandler<String>() {
                        @Override
                        public String handleResponse(HttpResponse res) throws ClientProtocolException, IOException {
                            int status = res.getStatusLine().getStatusCode();
                            if (status >= 200 && status < 300) {
                                HttpEntity entity = res.getEntity();
                                return entity != null ? EntityUtils.toString(entity) : null;
                            } else {
                                throw new ClientProtocolException("Unexpected response: " + status);
                            }
                        }
                    };

                    String body = httpClient.execute(hostReq, resHandler, localContext);
                    JsonReader reader = new JsonReader(new StringReader(body));
                    try {
                        hosts = readHostsArray(reader);
                    } finally {
                        reader.close();
                    }

                } else {
                    // Got an IP address. Easy, create a new host or use existing
                    Host host;
                    if (hostMap.containsKey(h)) {
                        host = hostMap.get(h);
                    } else {
                        host = new Host();
                        host.setIpv4(h);
                        host.setProjectId(pid);
                        hostMap.put(h, host);
                        hosts.add(host);
                    }
                }

                // Loop through all affected hosts, adding issue details to each.
                for (Host host : hosts) {
                    Service service = new Service();
                    service.setProjectId(pid);
                    service.setPort(issue.getHttpService().getPort());
                    host.getServices().add(service);
                    for (IHttpRequestResponse reqRes : issue.getHttpMessages()) {
                        IRequestInfo reqInfo = this.helpers.analyzeRequest(reqRes);
                        IResponseInfo resInfo = this.helpers.analyzeResponse(reqRes.getResponse());

                        WebDirectory wd = new WebDirectory();
                        wd.setProjectId(pid);
                        wd.setPath(reqInfo.getUrl().getPath());
                        wd.setPort(service.getPort());
                        wd.setResponseCode(Short.toString(resInfo.getStatusCode()));
                        host.getWebDirectories().add(wd);
                    }

                    Issue vuln;
                    if (issueMap.containsKey(issue.getIssueName())) {
                        vuln = issueMap.get(issue.getIssueName());
                    } else {
                        vuln = new Issue();
                        vuln.setProjectId(pid);
                        vuln.setTitle(issue.getIssueName());
                        vuln.setDescription(issue.getRemediationBackground() != null ? Jsoup.parse(issue.getIssueBackground()).text() : "");
                        vuln.setSolution(issue.getRemediationBackground() != null ? Jsoup.parse(issue.getRemediationBackground()).text() : "");
                        String sev = issue.getSeverity();
                        switch (sev) {
                            case "High":
                                vuln.setCvss(10.0);
                                vuln.setRating(Constants.RATING_HIGH);
                                break;
                            case "Medium":
                                vuln.setCvss(5.0);
                                vuln.setRating(Constants.RATING_MEDIUM);
                                break;
                            case "Low":
                                vuln.setCvss(3.0);
                                vuln.setRating(Constants.RATING_LOW);
                                break;
                            default:
                                vuln.setCvss(0.0);
                                vuln.setRating(Constants.RATING_LOW);
                                break;
                        }
                        PluginId pluginId = new PluginId();
                        pluginId.setId(Integer.toString(issue.getIssueType()));
                        pluginId.setTool(Constants.TOOL);
                        vuln.getPluginIds().add(pluginId);

                        IdentifiedBy idBy = new IdentifiedBy();
                        idBy.setTool(Constants.TOOL);
                        vuln.getIdentifiedBy().add(idBy);

                        issueMap.put(vuln.getTitle(), vuln);
                    }

                    IssueHost issueRef = new IssueHost();
                    issueRef.setPort(issue.getHttpService().getPort());
                    issueRef.setIpv4(host.getIpv4());
                    issueRef.setProtocol(Constants.PROTOCOL_TCP);
                    vuln.getHosts().add(issueRef);

                    vuln.setLastModidifiedBy(Constants.TOOL);
                    String evidence = (issue.getIssueDetail() != null ? Jsoup.parse(issue.getIssueDetail()).text() : "None");
                    vuln.setEvidence(vuln.getEvidence() + "\n" + host.getIpv4() + ":\n" + evidence);

                    project.getHosts().add(host);
                }
            }

            for (Issue issue : issueMap.values()) {
                project.getIssues().add(issue);
            }

            String json = gson.toJson(project);

            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            req.setEntity(input);

            ResponseHandler<String> resHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse res) throws ClientProtocolException, IOException {
                    int status = res.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = res.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response: " + status);
                    }
                }
            };

            String body = httpClient.execute(req, resHandler, localContext);

        } catch (Exception e) {
            this.callbacks.issueAlert(e.getMessage());
        } finally {
            httpClient.close();
        }
    }

    // Parse JSon array of hosts
    private ArrayList<Host> readHostsArray(JsonReader reader) throws IOException {
        ArrayList<Host> hosts = new ArrayList<Host>();

        reader.beginArray();
        while (reader.hasNext()) {
            hosts.add(readHost(reader));
        }
        reader.endArray();
        return hosts;
    }

    // Parse a single host from JSon. Note we only extract a subset of fields
    // since the burp drone only deals with limited host data.
    private Host readHost(JsonReader reader) throws IOException {
        Host host = new Host();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("_id")) {
                host.setProjectId(reader.nextString());
            } else if (name.equals("longIpv4Addr")) {
                host.setLongIpv4Addr(reader.nextLong());
            } else if (name.equals("ipv4")) {
                host.setIpv4(reader.nextString());
            } else if (name.equals("mac")) {
                host.setMac(reader.nextString());
            } else if (name.equals("os")) {
                host.setOs(readOS(reader));
            } else if (name.equals("hostnames")) {
                host.setHostnames(readHostnamesArray(reader));
            } else if (name.equals("statusMessage")) {
                host.setStatusMessage(reader.nextString());
            } else if (name.equals("tags")) {
                host.setTags(readTagsArray(reader));
            } else if (name.equals("status")) {
                host.setStatus(reader.nextString());
            } else if (name.equals("lastModifiedBy")) {
                host.setLastModifiedBy(reader.nextString());
            } else if (name.equals("isFlagged")) {
                host.setIsFlagged(reader.nextBoolean());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return host;
    }

    // The following functions parse subcomponents of the json response.
    private OS readOS(JsonReader reader) throws IOException {
        OS os = new OS();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("tool")) {
                os.setTool(reader.nextString());
            } else if (name.equals("weight")) {
                os.setWeight(reader.nextInt());
            } else if (name.equals("fingerprint")) {
                os.setFingerprint(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return os;
    }

    private Note readNote(JsonReader reader) throws IOException {
        Note note = new Note();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("title")) {
                note.setTitle(reader.nextString());
            } else if (name.equals("content")) {
                note.setContent(reader.nextString());
            } else if (name.equals("lastModifiedBy")) {
                note.setLastModifiedBy(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return note;
    }

    private HashSet<String> readHostnamesArray(JsonReader reader) throws IOException {
        HashSet<String> hostnames = new HashSet<String>();
        reader.beginArray();
        while (reader.hasNext()) {
            hostnames.add(reader.nextString());
        }
        reader.endArray();
        return hostnames;
    }

    private ArrayList<Note> readNotesArray(JsonReader reader) throws IOException {
        ArrayList<Note> notes = new ArrayList<Note>();
        reader.beginArray();
        while (reader.hasNext()) {
            notes.add(readNote(reader));
        }
        reader.endArray();
        return notes;
    }

    private HashSet<String> readTagsArray(JsonReader reader) throws IOException {
        HashSet<String> tags = new HashSet<String>();
        reader.beginArray();
        while (reader.hasNext()) {
            tags.add(reader.nextString());
        }
        reader.endArray();
        return tags;
    }

}

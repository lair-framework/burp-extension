/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;

/**
 *
 * @author dkottmann
 */
public class Port extends BasicDBObject {

    public Port() {
        super();
        this.setPort(0);
        this.setProtocol(Constants.PROTOCOL_TCP);
        this.setService(Constants.SERVICE_UNKNOWN);
        this.setProduct(Constants.PRODUCT_UNKNOWN);
        this.setAlive(true);
        this.setStatus(Constants.STATUS_GREY);
        this.setCredentialList(new ArrayList<Credential>());
        this.setNotes(new ArrayList<Note>());
        this.setLastModifiedBy(Constants.TOOL);
        this.setFlag(false);
    }
    
    public Port(BasicDBObject base) {
        super(base);
    }
    
    public String getId() {
        return this.getString("_id");
    }
    
    public void setId(String id) {
        this.put("_id", id);
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return this.getString("project_id");
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.put("project_id", projectId);
    }

    /**
     * @return the hostId
     */
    public String getHostId() {
        return this.getString("host_id");
    }

    /**
     * @param hostId the hostId to set
     */
    public void setHostId(String hostId) {
        this.put("host_id", hostId);
    }

    /**
     * @return the port
     */
    public int getPort() {
        return this.getInt("port");
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.put("port", port);
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return this.getString("protocol");
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.put("protocol", protocol);
    }

    /**
     * @return the service
     */
    public String getService() {
        return this.getString("service");
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.put("service", service);
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return this.getString("product");
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.put("product", product);
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return this.getBoolean("alive");
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.put("alive", alive);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return this.getString("status");
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.put("status", status);
    }

    /**
     * @return the credentialList
     */
    public ArrayList<Credential> getCredentialList() {
        ArrayList<Credential> ret = new ArrayList<Credential>();
        for (BasicDBObject obj : (ArrayList<BasicDBObject>) this.get("credentials")) {
            ret.add(new Credential(obj));
        }
        return ret;
    }

    /**
     * @param credentialList the credentialList to set
     */
    public void setCredentialList(ArrayList<Credential> credentials) {
        this.put("credentials", credentials);
    }

    public ArrayList<Note> getNotes() {
        ArrayList<Note> ret = new ArrayList<Note>();
        for (BasicDBObject obj : (ArrayList<BasicDBObject>) this.get("notes")) {
            ret.add(new Note(obj));
        }
        return ret;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.put("notes", notes);
    }
        
    public String getLastModifiedBy() {
        return this.getString("last_modified_by");
    }
    
    public void setLastModifiedBy(String lastModifiedBy) {
        this.put("last_modified_by", lastModifiedBy);
    }
    
    public boolean isFlag() {
        return this.getBoolean("flag");
    }
    
    public void setFlag(boolean flag) {
        this.put("flag", flag);
    }
    
}

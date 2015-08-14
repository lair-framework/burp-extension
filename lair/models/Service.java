/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author dkottmann
 */
public class Service {

    @SerializedName("_id") private String id;
    private String projectId;
    private String hostId;
    private Integer port;
    private String protocol;
    private String service;
    private String product;
    private String status;
    private Boolean isFlagged;
    private String lastModifiedBy;
    private ArrayList<Note> notes;
    private ArrayList<File> files;
    
    public Service() {
        this.id = "";
        this.projectId = "";
        this.hostId = "";
        this.port = 0;
        this.protocol = Constants.PROTOCOL_TCP;
        this.service = Constants.SERVICE_UNKNOWN;
        this.product = Constants.PRODUCT_UNKNOWN;
        this.status = Constants.STATUS_GREY;
        this.isFlagged = false;
        this.lastModifiedBy = Constants.TOOL;
        this.notes = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the hostId
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * @param hostId the hostId to set
     */
    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the isFlagged
     */
    public Boolean getIsFlagged() {
        return isFlagged;
    }

    /**
     * @param isFlagged the isFlagged to set
     */
    public void setIsFlagged(Boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    /**
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return the notes
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    /**
     * @return the files
     */
    public ArrayList<File> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.port);
        hash = 53 * hash + Objects.hashCode(this.protocol);
        hash = 53 * hash + Objects.hashCode(this.service);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Service other = (Service) obj;
        if (!Objects.equals(this.port, other.port)) {
            return false;
        }
        if (!Objects.equals(this.protocol, other.protocol)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }
}

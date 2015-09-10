/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author dkottmann
 */
public class WebDirectory {
    @SerializedName("_id") private String id;
    private String projectId;
    private String hostId;
    private String path;
    private Integer port;
    private String responseCode;
    private String lastModifiedBy;
    private Boolean isFlagged;
    
    public WebDirectory() {
        this.id = "";
        this.projectId = "";
        this.hostId = "";
        this.path = "";
        this.port = 80;
        this.responseCode = "200";
        this.lastModifiedBy = Constants.TOOL;
        this.isFlagged = false;
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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
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
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author dkottmann
 */
public class Host {
    @SerializedName("_id") private String id;
    private String projectId;
    private Long longIpv4Addr;
    private String ipv4;
    private String mac;
    private HashSet<String> hostnames;
    private OS os;
    private ArrayList<Note> notes;
    private String statusMessage;
    private HashSet<String> tags;
    private String status;
    private String lastModifiedBy;
    private Boolean isFlagged;
    private ArrayList<File> files;
    private ArrayList<WebDirectory> webDirectories;
    private HashSet<Service> services;
    
    public Host() {
        this.id = "";
        this.projectId = "";
        this.longIpv4Addr = new Long(0);
        this.ipv4 = "";
        this.mac = "";
        this.hostnames = new HashSet<String>();
        this.os = new OS();
        this.notes = new ArrayList<Note>();
        this.statusMessage = "";
        this.tags = new HashSet<String>();
        this.status = Constants.STATUS_GREY;
        this.lastModifiedBy = Constants.TOOL;
        this.isFlagged = false;
        this.files = new ArrayList<File>();
        this.webDirectories = new ArrayList<WebDirectory>();
        this.services = new HashSet<Service>();  
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
     * @return the longIpv4Addr
     */
    public Long getLongIpv4Addr() {
        return longIpv4Addr;
    }

    /**
     * @param longIpv4Addr the longIpv4Addr to set
     */
    public void setLongIpv4Addr(Long longIpv4Addr) {
        this.longIpv4Addr = longIpv4Addr;
    }

    /**
     * @return the ipv4
     */
    public String getIpv4() {
        return ipv4;
    }

    /**
     * @param ipv4 the ipv4 to set
     */
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac the mac to set
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return the hostnames
     */
    public HashSet<String> getHostnames() {
        return hostnames;
    }

    /**
     * @param hostnames the hostnames to set
     */
    public void setHostnames(HashSet<String> hostnames) {
        this.hostnames = hostnames;
    }

    /**
     * @return the os
     */
    public OS getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(OS os) {
        this.os = os;
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
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the tags
     */
    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(HashSet<String> tags) {
        this.tags = tags;
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

    /**
     * @return the webDirectories
     */
    public ArrayList<WebDirectory> getWebDirectories() {
        return webDirectories;
    }

    /**
     * @param webDirectories the webDirectories to set
     */
    public void setWebDirectories(ArrayList<WebDirectory> webDirectories) {
        this.webDirectories = webDirectories;
    }

    /**
     * @return the services
     */
    public HashSet<Service> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(HashSet<Service> services) {
        this.services = services;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.ipv4);
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
        final Host other = (Host) obj;
        if (!Objects.equals(this.ipv4, other.ipv4)) {
            return false;
        }
        return true;
    }
}

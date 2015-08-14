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
public class Issue {
    @SerializedName("_id") private String id;
    private String projectId;
    private String title;
    private Double cvss;
    private String rating;
    private Boolean isConfirmed;
    private String description;
    private String evidence;
    private String solution;
    private HashSet<IssueHost> hosts;
    private HashSet<PluginId> pluginIds;
    private HashSet<String> cves;
    private HashSet<IssueReference> references;
    private HashSet<IdentifiedBy> identifiedBy;
    private Boolean isFlagged;
    private String status;
    private String lastModidifiedBy;
    private ArrayList<Note> notes;
    private ArrayList<File> files;
    
    public Issue() {
        this.id = "";
        this.projectId = "";
        this.title = "";
        this.cvss = 0.0;
        this.rating = Constants.RATING_LOW;
        this.isConfirmed = false;
        this.description = "";
        this.evidence = "";
        this.solution = "";
        this.hosts = new HashSet<IssueHost>();
        this.pluginIds = new HashSet<PluginId>();
        this.cves = new HashSet<String>();
        this.references = new HashSet<IssueReference>();
        this.identifiedBy = new HashSet<IdentifiedBy>();
        this.isFlagged = false;
        this.status = Constants.STATUS_GREY;
        this.lastModidifiedBy = Constants.TOOL;
        this.notes = new ArrayList<Note>();
        this.files = new ArrayList<File>();
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the cvss
     */
    public Double getCvss() {
        return cvss;
    }

    /**
     * @param cvss the cvss to set
     */
    public void setCvss(Double cvss) {
        this.cvss = cvss;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the isConfirmed
     */
    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    /**
     * @param isConfirmed the isConfirmed to set
     */
    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the evidence
     */
    public String getEvidence() {
        return evidence;
    }

    /**
     * @param evidence the evidence to set
     */
    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    /**
     * @return the solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * @return the hosts
     */
    public HashSet<IssueHost> getHosts() {
        return hosts;
    }

    /**
     * @param hosts the hosts to set
     */
    public void setHosts(HashSet<IssueHost> hosts) {
        this.hosts = hosts;
    }

    /**
     * @return the pluginIds
     */
    public HashSet<PluginId> getPluginIds() {
        return pluginIds;
    }

    /**
     * @param pluginIds the pluginIds to set
     */
    public void setPluginIds(HashSet<PluginId> pluginIds) {
        this.pluginIds = pluginIds;
    }

    /**
     * @return the cves
     */
    public HashSet<String> getCves() {
        return cves;
    }

    /**
     * @param cves the cves to set
     */
    public void setCves(HashSet<String> cves) {
        this.cves = cves;
    }

    /**
     * @return the references
     */
    public HashSet<IssueReference> getReferences() {
        return references;
    }

    /**
     * @param references the references to set
     */
    public void setReferences(HashSet<IssueReference> references) {
        this.references = references;
    }

    /**
     * @return the identifiedBy
     */
    public HashSet<IdentifiedBy> getIdentifiedBy() {
        return identifiedBy;
    }

    /**
     * @param identifiedBy the identifiedBy to set
     */
    public void setIdentifiedBy(HashSet<IdentifiedBy> identifiedBy) {
        this.identifiedBy = identifiedBy;
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
     * @return the lastModidifiedBy
     */
    public String getLastModidifiedBy() {
        return lastModidifiedBy;
    }

    /**
     * @param lastModidifiedBy the lastModidifiedBy to set
     */
    public void setLastModidifiedBy(String lastModidifiedBy) {
        this.lastModidifiedBy = lastModidifiedBy;
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
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.title);
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
        final Issue other = (Issue) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
}

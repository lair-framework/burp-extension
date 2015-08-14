package lair.models;

import com.google.gson.annotations.SerializedName;

public class AuthInterface {
    @SerializedName("_id") private String id;
    private String projectId;
    private Boolean isMultiFactor;
    private String kind;
    private String url;
    private String description;
    
    public AuthInterface() {
        this.id = "";
        this.projectId = "";
        this.isMultiFactor = false;
        this.kind = "";
        this.url = "";
        this.description = "";
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
     * @return the isMultiFactor
     */
    public Boolean getIsMultiFactor() {
        return isMultiFactor;
    }

    /**
     * @param isMultiFactor the isMultiFactor to set
     */
    public void setIsMultiFactor(Boolean isMultiFactor) {
        this.isMultiFactor = isMultiFactor;
    }

    /**
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
}
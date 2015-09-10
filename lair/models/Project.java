/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lair.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author dkottmann
 */
public class Project {
    @SerializedName("_id") private String id;
    private String name;
    private String industry;
    private String createdAt;
    private String description;
    private String owner;
    private HashSet<String> contributors;
    private ArrayList<Command> commands;
    private ArrayList<Note> notes;
    private ArrayList<String> droneLog;
    private String tool;
    private HashSet<Host> hosts;
    private HashSet<Issue> issues;
    private ArrayList<AuthInterface> authInterfaces;
    private ArrayList<Netblock> netblocks;
    private ArrayList<Person> people;
    private ArrayList<Credential> credentials;
    
    public Project() {
        this.id = "";
        this.name = "";
        this.industry = "";
        this.createdAt = "";
        this.description = "";
        this.owner = "";
        this.contributors = new HashSet<String>();
        this.commands = new ArrayList<Command>();
        this.notes = new ArrayList<Note>();
        this.droneLog = new ArrayList<String>();
        this.tool = Constants.TOOL;
        this.hosts = new HashSet<Host>();
        this.issues = new HashSet<Issue>();
        this.authInterfaces = new ArrayList<AuthInterface>();
        this.netblocks = new ArrayList<Netblock>();
        this.people = new ArrayList<Person>();
        this.credentials = new ArrayList<Credential>();
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the contributors
     */
    public HashSet<String> getContributors() {
        return contributors;
    }

    /**
     * @param contributors the contributors to set
     */
    public void setContributors(HashSet<String> contributors) {
        this.contributors = contributors;
    }

    /**
     * @return the commands
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * @param commands the commands to set
     */
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
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
     * @return the droneLog
     */
    public ArrayList<String> getDroneLog() {
        return droneLog;
    }

    /**
     * @param droneLog the droneLog to set
     */
    public void setDroneLog(ArrayList<String> droneLog) {
        this.droneLog = droneLog;
    }

    /**
     * @return the tool
     */
    public String getTool() {
        return tool;
    }

    /**
     * @param tool the tool to set
     */
    public void setTool(String tool) {
        this.tool = tool;
    }

    /**
     * @return the hosts
     */
    public HashSet<Host> getHosts() {
        return hosts;
    }

    /**
     * @param hosts the hosts to set
     */
    public void setHosts(HashSet<Host> hosts) {
        this.hosts = hosts;
    }

    /**
     * @return the issues
     */
    public HashSet<Issue> getIssues() {
        return issues;
    }

    /**
     * @param issues the issues to set
     */
    public void setIssues(HashSet<Issue> issues) {
        this.issues = issues;
    }

    /**
     * @return the authInterfaces
     */
    public ArrayList<AuthInterface> getAuthInterfaces() {
        return authInterfaces;
    }

    /**
     * @param authInterfaces the authInterfaces to set
     */
    public void setAuthInterfaces(ArrayList<AuthInterface> authInterfaces) {
        this.authInterfaces = authInterfaces;
    }

    /**
     * @return the netblocks
     */
    public ArrayList<Netblock> getNetblocks() {
        return netblocks;
    }

    /**
     * @param netblocks the netblocks to set
     */
    public void setNetblocks(ArrayList<Netblock> netblocks) {
        this.netblocks = netblocks;
    }

    /**
     * @return the people
     */
    public ArrayList<Person> getPeople() {
        return people;
    }

    /**
     * @param people the people to set
     */
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    /**
     * @return the credentials
     */
    public ArrayList<Credential> getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setCredentials(ArrayList<Credential> credentials) {
        this.credentials = credentials;
    }
}

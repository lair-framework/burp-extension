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
public class Project extends BasicDBObject {

    public Project() {
        super();
    }

    public Project(BasicDBObject base) {
        super(base);
    }

    public String getProjectName() {
        return this.getString("project_name");
    }

    public void setProjectName(String projectName) {
        this.put("project_name", projectName);
    }

    public String getIndustry() {
        return this.getString("industry");
    }

    public void setIndustry(String industry) {
        this.put("industry", industry);
    }

    public String getCreationDate() {
        return this.getString("creation_date");
    }

    public String getDescription() {
        return this.getString("description");
    }

    public void setDescription(String description) {
        this.put("description", description);
    }

    public String getOwner() {
        return this.getString("owner");
    }

    public void setOwner(String owner) {
        this.put("owner", owner);
    }

    public ArrayList<String> getContributors() {
        return (ArrayList<String>) this.get("contributors");
    }

    public void setContributors(ArrayList<String> contributors) {
        this.put("contributors", contributors);
    }

    public ArrayList<String> getCommands() {
        return (ArrayList<String>) this.get("commands");
    }

    public void setCommands(ArrayList<String> commands) {
        this.put("commands", commands);
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

    public ArrayList<String> getDroneLog() {
        return (ArrayList<String>) this.get("drone_log");
    }

    public void setDroneLog(ArrayList<String> droneLog) {
        this.put("drone_log", droneLog);
    }

    public ArrayList<Message> getMessages() {
        ArrayList<Message> ret = new ArrayList<Message>();
        for (BasicDBObject obj : (ArrayList<BasicDBObject>) this.get("messages")) {
            ret.add(new Message(obj));
        }
        return ret;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.put("messages", messages);
    }

    public ArrayList<File> getFiles() {
        ArrayList<File> ret = new ArrayList<File>();
        for (BasicDBObject obj : (ArrayList<BasicDBObject>) this.get("files")) {
            ret.add(new File(obj));
        }
        return ret;
    }

    public void setFiles(ArrayList<File> files) {
        this.put("files", files);
    }

}

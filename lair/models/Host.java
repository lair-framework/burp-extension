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
public class Host extends BasicDBObject {
    public Host() {
        super();
        this.setLongAddr(0);
        this.setAlive(true);
        this.setStatus(Constants.STATUS_GREY);
        this.setFlag(false);
    }
    
    public Host(BasicDBObject base) {
        super(base);
    }
    
    public String getId() {
        return this.getString("_id");
    }
    
    public void setId(String id) {
        this.put("_id", id);
    }
    
    public String getProjectId() {
        return this.getString("project_id");
    }
    
    public void setProjectId(String projectId) {
        this.put("project_id", projectId);
    }
    
    public Integer getLongAddr() {
        return this.getInt("long_addr");
    }
    
    public void setLongAddr(Integer longAddr) {
        this.put("long_addr", longAddr);
    }
    
    public String getStringAddr() {
        return this.getString("string_addr");
    }
    
    public void setStringAddr(String stringAddr) {
        this.put("string_addr", stringAddr);
    }
    
    public String getMacAddr() {
        return this.getString("mac_addr");
    }
    
    public void setMacAddr(String macAddr) {
        this.put("mac_addr", macAddr);
    }

    public ArrayList<String> getHostnames() {
        return (ArrayList<String>) this.get("hostnames");
    }

    public void setHostnames(ArrayList<String> hostnames) {
        this.put("hostnames", hostnames);
    }

    public ArrayList<OS> getOs() {
        ArrayList<OS> ret = new ArrayList<OS>();
        for (BasicDBObject obj : (ArrayList<BasicDBObject>) this.get("os")) {
            ret.add(new OS(obj));
        }
        return ret;
    }

    public void setOs(ArrayList<OS> os) {
        this.put("os", os);
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
    
    public boolean isAlive() {
        return this.getBoolean("alive");
    }
    
    public void setAlive(boolean alive) {
        this.put("alive", alive);
    }
        
    public String getStatus() {
        return this.getString("status");
    }
    
    public void setStatus(String status) {
        this.put("status", status);
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

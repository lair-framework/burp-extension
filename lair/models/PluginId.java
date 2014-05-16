/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.mongodb.BasicDBObject;

/**
 *
 * @author dkottmann
 */
public class PluginId extends BasicDBObject {

    public PluginId() {
        super();
        this.setTool("Burp");
    }
    
    public PluginId(BasicDBObject base) {
        super(base);
    }
    
    /**
     * @return the tool
     */
    public String getTool() {
        return this.getString("tool");
    }

    /**
     * @param tool the tool to set
     */
    public void setTool(String tool) {
        this.put("tool", tool);
    }

    /**
     * @return the id
     */
    public String getId() {
        return this.getString("id");
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.put("id", id);
    }
}

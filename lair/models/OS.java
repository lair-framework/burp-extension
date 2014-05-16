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
public class OS extends BasicDBObject {

    public OS() {
        super();
        this.setWeight(0);
        this.setFingerprint("unknown");
    }
    
    public OS(BasicDBObject base) {
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
     * @return the weight
     */
    public int getWeight() {
        return this.getInt("weight");
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.put("weight", weight);
    }

    /**
     * @return the fingerprint
     */
    public String getFingerprint() {
        return this.getString("fingerprint");
    }

    /**
     * @param fingerprint the fingerprint to set
     */
    public void setFingerprint(String fingerprint) {
        this.put("fingerprint", fingerprint);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

/**
 *
 * @author dkottmann
 */
public class OS {

    private String tool;
    private Integer weight;
    private String fingerprint;
    
    public OS() {
        this.tool = Constants.TOOL;
        this.weight = 0;
        this.fingerprint = Constants.PRODUCT_UNKNOWN;
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
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the fingerprint
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * @param fingerprint the fingerprint to set
     */
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import java.util.Objects;

/**
 *
 * @author dkottmann
 */
public class IdentifiedBy {
    private String tool;
    
    public IdentifiedBy() {
        this.tool = "";
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tool);
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
        final IdentifiedBy other = (IdentifiedBy) obj;
        if (!Objects.equals(this.tool, other.tool)) {
            return false;
        }
        return true;
    }
}

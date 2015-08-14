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
public class Command {
    private String tool;
    private String Command;
    
    public Command() {
        this.tool = Constants.TOOL;
        this.Command = "";
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
     * @return the Command
     */
    public String getCommand() {
        return Command;
    }

    /**
     * @param Command the Command to set
     */
    public void setCommand(String Command) {
        this.Command = Command;
    }
}

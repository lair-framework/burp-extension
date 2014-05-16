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
public class Message extends BasicDBObject {
    
    public Message() {
        super();
    }
    
    public Message(BasicDBObject base) {
        super(base);
    }

    /**
     * @return the user
     */
    public String getUser() {
        return this.getString("user");
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.put("user", user);
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.getString("message");
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.put("message", message);
    }
    
    
}

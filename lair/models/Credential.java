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
public class Credential extends BasicDBObject {

    public Credential() {
        super();
    }
    
    public Credential(BasicDBObject base) {
        super(base);
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return this.getString("username");
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.put("username", username);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.getString("password");
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.put("password", password);
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return this.getString("hash");
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.put("hash", hash);
    }
    
}

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
public class File extends BasicDBObject {

    public File() {
        super();
    }
    
    public File(BasicDBObject base) {
        super(base);
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return this.getString("name");
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.put("name", name);
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.getString("url");
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.put("url", url);
    }
}

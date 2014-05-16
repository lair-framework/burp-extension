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
public class Note extends BasicDBObject {
    
    public Note() {
        super();
    }
    
    public Note(BasicDBObject base) {
        super(base);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.getString("title");
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.put("title", title);
    }

    /**
     * @return the content
     */
    public String getContent() {
        return this.getString("content");
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.put("content", content);
    }

    /**
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return this.getString("last_modified_by");
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.put("last_modified_by", lastModifiedBy);
    }
    
}

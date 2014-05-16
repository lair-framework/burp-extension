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
public class HostRef extends BasicDBObject {
    
    public HostRef() {
        super();
        this.setProtocol(Constants.PROTOCOL_TCP);
    }
    
    public HostRef(BasicDBObject base) {
        super(base);
    }
    
    public String getStringAddr() {
        return this.getString("string_addr");
    }
    
    public void setStringAddr(String stringAddr) {
        this.put("string_addr", stringAddr);
    }

    /**
     * @return the port
     */
    public int getPort() {
        return this.getInt("port");
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.put("port", port);
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return this.getString("protocol");
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.put("protocol", protocol);
    }
}

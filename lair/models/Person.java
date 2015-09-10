/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.google.gson.annotations.SerializedName;
import java.util.HashSet;

/**
 *
 * @author dkottmann
 */
public class Person {
    @SerializedName("_id") private String id;
    private String projectId;
    private String principalName;
    private String samAccountName;
    private String distinguishedName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
    private String department;
    private String address;
    private HashSet<String> emails;
    private HashSet<String> phones;
    private HashSet<PersonReference> references;
    private HashSet<String> groups;
    private String lastLogon;
    private String lastLogoff;
    private HashSet<String> loggedIn;

    public Person() {
        this.id = "";
        this.projectId = "";
        this.principalName = "";
        this.samAccountName = "";
        this.distinguishedName = "";
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.displayName = "";
        this.department = "";
        this.address = "";
        this.emails = new HashSet<String>();
        this.phones = new HashSet<String>();
        this.references = new HashSet<PersonReference>();
        this.groups = new HashSet<String>();
        this.lastLogon = "";
        this.lastLogoff = "";
        this.loggedIn = new HashSet<String>();
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the principalName
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName the principalName to set
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    /**
     * @return the samAccountName
     */
    public String getSamAccountName() {
        return samAccountName;
    }

    /**
     * @param samAccountName the samAccountName to set
     */
    public void setSamAccountName(String samAccountName) {
        this.samAccountName = samAccountName;
    }

    /**
     * @return the distinguishedName
     */
    public String getDistinguishedName() {
        return distinguishedName;
    }

    /**
     * @param distinguishedName the distinguishedName to set
     */
    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the emails
     */
    public HashSet<String> getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(HashSet<String> emails) {
        this.emails = emails;
    }

    /**
     * @return the phones
     */
    public HashSet<String> getPhones() {
        return phones;
    }

    /**
     * @param phones the phones to set
     */
    public void setPhones(HashSet<String> phones) {
        this.phones = phones;
    }

    /**
     * @return the references
     */
    public HashSet<PersonReference> getReferences() {
        return references;
    }

    /**
     * @param references the references to set
     */
    public void setReferences(HashSet<PersonReference> references) {
        this.references = references;
    }

    /**
     * @return the groups
     */
    public HashSet<String> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(HashSet<String> groups) {
        this.groups = groups;
    }

    /**
     * @return the lastLogon
     */
    public String getLastLogon() {
        return lastLogon;
    }

    /**
     * @param lastLogon the lastLogon to set
     */
    public void setLastLogon(String lastLogon) {
        this.lastLogon = lastLogon;
    }

    /**
     * @return the lastLogoff
     */
    public String getLastLogoff() {
        return lastLogoff;
    }

    /**
     * @param lastLogoff the lastLogoff to set
     */
    public void setLastLogoff(String lastLogoff) {
        this.lastLogoff = lastLogoff;
    }

    /**
     * @return the loggedIn
     */
    public HashSet<String> getLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(HashSet<String> loggedIn) {
        this.loggedIn = loggedIn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lair.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author dkottmann
 */
public class Netblock {
    @SerializedName("_id") private String id;
    private String projectId;
    private String asn;
    private String asnCountryCode;
    private String asnCidr;
    private String asnDate;
    private String asnRegistry;
    private String cidr;
    private String abuseEmails;
    private String miscEmails;
    private String techEmails;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String created;
    private String updated;
    private String description;
    private String handle;
    
    public Netblock() {
        this.id = "";
        this.projectId = "";
        this.asn = "";
        this.asnCountryCode = "";
        this.asnCidr = "";
        this.asnDate = "";
        this.asnRegistry = "";
        this.cidr = "";
        this.abuseEmails = "";
        this.miscEmails = "";
        this.techEmails = "";
        this.name = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.country = "";
        this.postalCode = "";
        this.created = "";
        this.updated = "";
        this.description = "";
        this.handle = "";
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
     * @return the asn
     */
    public String getAsn() {
        return asn;
    }

    /**
     * @param asn the asn to set
     */
    public void setAsn(String asn) {
        this.asn = asn;
    }

    /**
     * @return the asnCountryCode
     */
    public String getAsnCountryCode() {
        return asnCountryCode;
    }

    /**
     * @param asnCountryCode the asnCountryCode to set
     */
    public void setAsnCountryCode(String asnCountryCode) {
        this.asnCountryCode = asnCountryCode;
    }

    /**
     * @return the asnCidr
     */
    public String getAsnCidr() {
        return asnCidr;
    }

    /**
     * @param asnCidr the asnCidr to set
     */
    public void setAsnCidr(String asnCidr) {
        this.asnCidr = asnCidr;
    }

    /**
     * @return the asnDate
     */
    public String getAsnDate() {
        return asnDate;
    }

    /**
     * @param asnDate the asnDate to set
     */
    public void setAsnDate(String asnDate) {
        this.asnDate = asnDate;
    }

    /**
     * @return the asnRegistry
     */
    public String getAsnRegistry() {
        return asnRegistry;
    }

    /**
     * @param asnRegistry the asnRegistry to set
     */
    public void setAsnRegistry(String asnRegistry) {
        this.asnRegistry = asnRegistry;
    }

    /**
     * @return the cidr
     */
    public String getCidr() {
        return cidr;
    }

    /**
     * @param cidr the cidr to set
     */
    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    /**
     * @return the abuseEmails
     */
    public String getAbuseEmails() {
        return abuseEmails;
    }

    /**
     * @param abuseEmails the abuseEmails to set
     */
    public void setAbuseEmails(String abuseEmails) {
        this.abuseEmails = abuseEmails;
    }

    /**
     * @return the miscEmails
     */
    public String getMiscEmails() {
        return miscEmails;
    }

    /**
     * @param miscEmails the miscEmails to set
     */
    public void setMiscEmails(String miscEmails) {
        this.miscEmails = miscEmails;
    }

    /**
     * @return the techEmails
     */
    public String getTechEmails() {
        return techEmails;
    }

    /**
     * @param techEmails the techEmails to set
     */
    public void setTechEmails(String techEmails) {
        this.techEmails = techEmails;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }
}

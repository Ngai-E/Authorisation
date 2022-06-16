/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOFT
 */
@Entity
@Table(name = "t_client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TClient.findAll", query = "SELECT t FROM TClient t"),
    @NamedQuery(name = "TClient.findByLgClientId", query = "SELECT t FROM TClient t WHERE t.lgClientId = :lgClientId"),
    @NamedQuery(name = "TClient.findByStrUsername", query = "SELECT t FROM TClient t WHERE t.strUsername = :strUsername"),
    @NamedQuery(name = "TClient.findByStrPassword", query = "SELECT t FROM TClient t WHERE t.strPassword = :strPassword"),
    @NamedQuery(name = "TClient.findByStrKey", query = "SELECT t FROM TClient t WHERE t.strKey = :strKey")})
public class TClient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_client_id")
    private String lgClientId;
    @Basic(optional = false)
    @Column(name = "str_username")
    private String strUsername;
    @Basic(optional = false)
    @Column(name = "str_password")
    private String strPassword;
    @Basic(optional = false)
    @Column(name = "str_key")
    private String strKey;

    public TClient() {
    }

    public TClient(String lgClientId) {
        this.lgClientId = lgClientId;
    }

    public TClient(String lgClientId, String strUsername, String strPassword, String strKey) {
        this.lgClientId = lgClientId;
        this.strUsername = strUsername;
        this.strPassword = strPassword;
        this.strKey = strKey;
    }

    public String getLgClientId() {
        return lgClientId;
    }

    public void setLgClientId(String lgClientId) {
        this.lgClientId = lgClientId;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrKey() {
        return strKey;
    }

    public void setStrKey(String strKey) {
        this.strKey = strKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgClientId != null ? lgClientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TClient)) {
            return false;
        }
        TClient other = (TClient) object;
        if ((this.lgClientId == null && other.lgClientId != null) || (this.lgClientId != null && !this.lgClientId.equals(other.lgClientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TClient[ lgClientId=" + lgClientId + " ]";
    }
    
}

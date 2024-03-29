/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SOFT
 */
@Entity
@Table(name = "t_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUsers.findAll", query = "SELECT t FROM TUsers t"),
    @NamedQuery(name = "TUsers.findByUserId", query = "SELECT t FROM TUsers t WHERE t.userId = :userId"),
    @NamedQuery(name = "TUsers.findByName", query = "SELECT t FROM TUsers t WHERE t.name = :name"),
    @NamedQuery(name = "TUsers.findByUsername", query = "SELECT t FROM TUsers t WHERE t.username = :username"),
    @NamedQuery(name = "TUsers.findByPhone", query = "SELECT t FROM TUsers t WHERE t.phone = :phone"),
    @NamedQuery(name = "TUsers.findByPassword", query = "SELECT t FROM TUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TUsers.findByRefCode", query = "SELECT t FROM TUsers t WHERE t.refCode = :refCode"),
    @NamedQuery(name = "TUsers.findByStatus", query = "SELECT t FROM TUsers t WHERE t.status = :status"),
    @NamedQuery(name = "TUsers.findByDtCreated", query = "SELECT t FROM TUsers t WHERE t.dtCreated = :dtCreated"),
    @NamedQuery(name = "TUsers.findByDtUpdated", query = "SELECT t FROM TUsers t WHERE t.dtUpdated = :dtUpdated")})
public class TUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "ref_code")
    private String refCode;
    @Column(name = "str_referred_by")
    private String strReferredBy;
    @Column(name = "status")
    private String status;
    @Column(name = "dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreated;
    @Column(name = "dt_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdated;

    public TUsers() {
    }

    public TUsers(String userId) {
        this.userId = userId;
    }

    public TUsers(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Date getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(Date dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public String getStrReferredBy() {
        return strReferredBy;
    }

    public void setStrReferredBy(String strReferredBy) {
        this.strReferredBy = strReferredBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUsers)) {
            return false;
        }
        TUsers other = (TUsers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TUsers[ userId=" + userId + " ]";
    }
    
}

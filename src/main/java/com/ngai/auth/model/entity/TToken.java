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
@Table(name = "t_token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TToken.findAll", query = "SELECT t FROM TToken t"),
    @NamedQuery(name = "TToken.findByLgTokenId", query = "SELECT t FROM TToken t WHERE t.lgTokenId = :lgTokenId"),
    @NamedQuery(name = "TToken.findByStrRefresh", query = "SELECT t FROM TToken t WHERE t.strRefresh = :strRefresh"),
    @NamedQuery(name = "TToken.findByStatus", query = "SELECT t FROM TToken t WHERE t.status = :status"),
    @NamedQuery(name = "TToken.findByDtCreated", query = "SELECT t FROM TToken t WHERE t.dtCreated = :dtCreated")})
public class TToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_token_id")
    private String lgTokenId;
    @Column(name = "str_refresh")
    private String strRefresh;
    @Column(name = "status")
    private String status;
    @Column(name = "dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreated;

    public TToken() {
    }

    public TToken(String lgTokenId) {
        this.lgTokenId = lgTokenId;
    }

    public String getLgTokenId() {
        return lgTokenId;
    }

    public void setLgTokenId(String lgTokenId) {
        this.lgTokenId = lgTokenId;
    }

    public String getStrRefresh() {
        return strRefresh;
    }

    public void setStrRefresh(String strRefresh) {
        this.strRefresh = strRefresh;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgTokenId != null ? lgTokenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TToken)) {
            return false;
        }
        TToken other = (TToken) object;
        if ((this.lgTokenId == null && other.lgTokenId != null) || (this.lgTokenId != null && !this.lgTokenId.equals(other.lgTokenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TToken[ lgTokenId=" + lgTokenId + " ]";
    }
    
}

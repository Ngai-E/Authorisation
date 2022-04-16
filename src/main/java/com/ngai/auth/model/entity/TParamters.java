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
@Table(name = "t_paramters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TParamters.findAll", query = "SELECT t FROM TParamters t"),
    @NamedQuery(name = "TParamters.findByStrKey", query = "SELECT t FROM TParamters t WHERE t.strKey = :strKey"),
    @NamedQuery(name = "TParamters.findByStrValue", query = "SELECT t FROM TParamters t WHERE t.strValue = :strValue"),
    @NamedQuery(name = "TParamters.findByStrDescription", query = "SELECT t FROM TParamters t WHERE t.strDescription = :strDescription"),
    @NamedQuery(name = "TParamters.findByBStatus", query = "SELECT t FROM TParamters t WHERE t.bStatus = :bStatus")})
public class TParamters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "str_key")
    private String strKey;
    @Column(name = "str_value")
    private String strValue;
    @Column(name = "str_description")
    private String strDescription;
    @Column(name = "b_status")
    private Boolean bStatus;

    public TParamters() {
    }

    public TParamters(String strKey) {
        this.strKey = strKey;
    }

    public String getStrKey() {
        return strKey;
    }

    public void setStrKey(String strKey) {
        this.strKey = strKey;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public Boolean getBStatus() {
        return bStatus;
    }

    public void setBStatus(Boolean bStatus) {
        this.bStatus = bStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strKey != null ? strKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TParamters)) {
            return false;
        }
        TParamters other = (TParamters) object;
        if ((this.strKey == null && other.strKey != null) || (this.strKey != null && !this.strKey.equals(other.strKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TParamters[ strKey=" + strKey + " ]";
    }
    
}

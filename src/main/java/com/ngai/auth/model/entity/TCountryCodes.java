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
@Table(name = "t_country_codes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCountryCodes.findAll", query = "SELECT t FROM TCountryCodes t"),
    @NamedQuery(name = "TCountryCodes.findByKeyCountryCode", query = "SELECT t FROM TCountryCodes t WHERE t.keyCountryCode = :keyCountryCode"),
    @NamedQuery(name = "TCountryCodes.findByStrCountryName", query = "SELECT t FROM TCountryCodes t WHERE t.strCountryName = :strCountryName")})
public class TCountryCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KEY_COUNTRY_CODE")
    private String keyCountryCode;
    @Column(name = "str_country_name")
    private String strCountryName;

    public TCountryCodes() {
    }

    public TCountryCodes(String keyCountryCode) {
        this.keyCountryCode = keyCountryCode;
    }

    public String getKeyCountryCode() {
        return keyCountryCode;
    }

    public void setKeyCountryCode(String keyCountryCode) {
        this.keyCountryCode = keyCountryCode;
    }

    public String getStrCountryName() {
        return strCountryName;
    }

    public void setStrCountryName(String strCountryName) {
        this.strCountryName = strCountryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyCountryCode != null ? keyCountryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCountryCodes)) {
            return false;
        }
        TCountryCodes other = (TCountryCodes) object;
        if ((this.keyCountryCode == null && other.keyCountryCode != null) || (this.keyCountryCode != null && !this.keyCountryCode.equals(other.keyCountryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TCountryCodes[ keyCountryCode=" + keyCountryCode + " ]";
    }
    
}

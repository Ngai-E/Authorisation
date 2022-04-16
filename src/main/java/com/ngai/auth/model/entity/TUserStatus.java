/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SOFT
 */
@Entity
@Table(name = "t_user_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUserStatus.findAll", query = "SELECT t FROM TUserStatus t"),
    @NamedQuery(name = "TUserStatus.findByLgStatus", query = "SELECT t FROM TUserStatus t WHERE t.lgStatus = :lgStatus")})
public class TUserStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lg_status")
    private String lgStatus;
    @OneToMany(mappedBy = "status")
    private List<TUsers> tUsersList;

    public TUserStatus() {
    }

    public TUserStatus(String lgStatus) {
        this.lgStatus = lgStatus;
    }

    public String getLgStatus() {
        return lgStatus;
    }

    public void setLgStatus(String lgStatus) {
        this.lgStatus = lgStatus;
    }

    @XmlTransient
    public List<TUsers> getTUsersList() {
        return tUsersList;
    }

    public void setTUsersList(List<TUsers> tUsersList) {
        this.tUsersList = tUsersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgStatus != null ? lgStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUserStatus)) {
            return false;
        }
        TUserStatus other = (TUserStatus) object;
        if ((this.lgStatus == null && other.lgStatus != null) || (this.lgStatus != null && !this.lgStatus.equals(other.lgStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ngai.auth.model.entity.TUserStatus[ lgStatus=" + lgStatus + " ]";
    }
    
}

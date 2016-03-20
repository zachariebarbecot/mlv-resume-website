/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Entity
@Table(name = "rw_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwGroup.findAll", query = "SELECT r FROM RwGroup r"),
    @NamedQuery(name = "RwGroup.findByGrpId", query = "SELECT r FROM RwGroup r WHERE r.grpId = :grpId"),
    @NamedQuery(name = "RwGroup.findByGrpName", query = "SELECT r FROM RwGroup r WHERE r.grpName = :grpName")})
public class RwGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "grp_id")
    private Integer grpId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "grp_name")
    private String grpName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grpId", fetch = FetchType.EAGER)
    private List<RwUser> rwUserList;

    public RwGroup() {
    }

    public RwGroup(Integer grpId) {
        this.grpId = grpId;
    }

    public RwGroup(Integer grpId, String grpName) {
        this.grpId = grpId;
        this.grpName = grpName;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    @XmlTransient
    public List<RwUser> getRwUserList() {
        return rwUserList;
    }

    public void setRwUserList(List<RwUser> rwUserList) {
        this.rwUserList = rwUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grpId != null ? grpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwGroup)) {
            return false;
        }
        RwGroup other = (RwGroup) object;
        if ((this.grpId == null && other.grpId != null) || (this.grpId != null && !this.grpId.equals(other.grpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwGroup[ grpId=" + grpId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Entity
@Table(name = "rw_interests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwInterests.findAll", query = "SELECT r FROM RwInterests r"),
    @NamedQuery(name = "RwInterests.findByItrId", query = "SELECT r FROM RwInterests r WHERE r.itrId = :itrId"),
    @NamedQuery(name = "RwInterests.findByItrName", query = "SELECT r FROM RwInterests r WHERE r.itrName = :itrName"),
    @NamedQuery(name = "RwInterests.findByItrContent", query = "SELECT r FROM RwInterests r WHERE r.itrContent = :itrContent"),
    @NamedQuery(name = "RwInterests.findByUsr", query = "SELECT r FROM RwInterests r WHERE r.usrId = :usrId")})
public class RwInterests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itr_id")
    private Integer itrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "itr_name")
    private String itrName;
    @Size(max = 2147483647)
    @Column(name = "itr_content")
    private String itrContent;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RwUser usrId;

    public RwInterests() {
    }

    public RwInterests(Integer itrId) {
        this.itrId = itrId;
    }

    public RwInterests(Integer itrId, String itrName) {
        this.itrId = itrId;
        this.itrName = itrName;
    }

    public Integer getItrId() {
        return itrId;
    }

    public void setItrId(Integer itrId) {
        this.itrId = itrId;
    }

    public String getItrName() {
        return itrName;
    }

    public void setItrName(String itrName) {
        this.itrName = itrName;
    }

    public String getItrContent() {
        return itrContent;
    }

    public void setItrContent(String itrContent) {
        this.itrContent = itrContent;
    }

    public RwUser getUsrId() {
        return usrId;
    }

    public void setUsrId(RwUser usrId) {
        this.usrId = usrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itrId != null ? itrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwInterests)) {
            return false;
        }
        RwInterests other = (RwInterests) object;
        if ((this.itrId == null && other.itrId != null) || (this.itrId != null && !this.itrId.equals(other.itrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwInterests[ itrId=" + itrId + " ]";
    }
    
}

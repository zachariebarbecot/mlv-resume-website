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
@Table(name = "rw_coreskill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwCoreskill.findAll", query = "SELECT r FROM RwCoreskill r"),
    @NamedQuery(name = "RwCoreskill.findByCrsId", query = "SELECT r FROM RwCoreskill r WHERE r.crsId = :crsId"),
    @NamedQuery(name = "RwCoreskill.findByCrsName", query = "SELECT r FROM RwCoreskill r WHERE r.crsName = :crsName"),
    @NamedQuery(name = "RwCoreskill.findByCrsLevel", query = "SELECT r FROM RwCoreskill r WHERE r.crsLevel = :crsLevel"),
    @NamedQuery(name = "RwCoreskill.findByUsr", query = "SELECT r FROM RwCoreskill r WHERE r.usrId = :usrId")})
public class RwCoreskill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "crs_id")
    private Integer crsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "crs_name")
    private String crsName;
    @Column(name = "crs_level")
    private Integer crsLevel;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RwUser usrId;

    public RwCoreskill() {
    }

    public RwCoreskill(Integer crsId) {
        this.crsId = crsId;
    }

    public RwCoreskill(Integer crsId, String crsName) {
        this.crsId = crsId;
        this.crsName = crsName;
    }

    public Integer getCrsId() {
        return crsId;
    }

    public void setCrsId(Integer crsId) {
        this.crsId = crsId;
    }

    public String getCrsName() {
        return crsName;
    }

    public void setCrsName(String crsName) {
        this.crsName = crsName;
    }

    public Integer getCrsLevel() {
        return crsLevel;
    }

    public void setCrsLevel(Integer crsLevel) {
        this.crsLevel = crsLevel;
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
        hash += (crsId != null ? crsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwCoreskill)) {
            return false;
        }
        RwCoreskill other = (RwCoreskill) object;
        if ((this.crsId == null && other.crsId != null) || (this.crsId != null && !this.crsId.equals(other.crsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwCoreskill[ crsId=" + crsId + " ]";
    }
    
}

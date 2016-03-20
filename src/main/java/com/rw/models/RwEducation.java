/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Entity
@Table(name = "rw_education")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwEducation.findAll", query = "SELECT r FROM RwEducation r"),
    @NamedQuery(name = "RwEducation.findByEduId", query = "SELECT r FROM RwEducation r WHERE r.eduId = :eduId"),
    @NamedQuery(name = "RwEducation.findByEduFormation", query = "SELECT r FROM RwEducation r WHERE r.eduFormation = :eduFormation"),
    @NamedQuery(name = "RwEducation.findByEduPlace", query = "SELECT r FROM RwEducation r WHERE r.eduPlace = :eduPlace"),
    @NamedQuery(name = "RwEducation.findByEduStart", query = "SELECT r FROM RwEducation r WHERE r.eduStart = :eduStart"),
    @NamedQuery(name = "RwEducation.findByEduEnd", query = "SELECT r FROM RwEducation r WHERE r.eduEnd = :eduEnd"),
    @NamedQuery(name = "RwEducation.findByEduContent", query = "SELECT r FROM RwEducation r WHERE r.eduContent = :eduContent"),
    @NamedQuery(name = "RwEducation.findByUsr", query = "SELECT r FROM RwEducation r WHERE r.usrId = :usrId")})
public class RwEducation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "edu_id")
    private Integer eduId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "edu_formation")
    private String eduFormation;
    @Size(max = 256)
    @Column(name = "edu_place")
    private String eduPlace;
    @Column(name = "edu_start")
    @Temporal(TemporalType.DATE)
    private Date eduStart;
    @Column(name = "edu_end")
    @Temporal(TemporalType.DATE)
    private Date eduEnd;
    @Size(max = 2147483647)
    @Column(name = "edu_content")
    private String eduContent;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RwUser usrId;

    public RwEducation() {
    }

    public RwEducation(Integer eduId) {
        this.eduId = eduId;
    }

    public RwEducation(Integer eduId, String eduFormation) {
        this.eduId = eduId;
        this.eduFormation = eduFormation;
    }

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    public String getEduFormation() {
        return eduFormation;
    }

    public void setEduFormation(String eduFormation) {
        this.eduFormation = eduFormation;
    }

    public String getEduPlace() {
        return eduPlace;
    }

    public void setEduPlace(String eduPlace) {
        this.eduPlace = eduPlace;
    }

    public Date getEduStart() {
        return eduStart;
    }

    public void setEduStart(Date eduStart) {
        this.eduStart = eduStart;
    }

    public Date getEduEnd() {
        return eduEnd;
    }

    public void setEduEnd(Date eduEnd) {
        this.eduEnd = eduEnd;
    }

    public String getEduContent() {
        return eduContent;
    }

    public void setEduContent(String eduContent) {
        this.eduContent = eduContent;
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
        hash += (eduId != null ? eduId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwEducation)) {
            return false;
        }
        RwEducation other = (RwEducation) object;
        if ((this.eduId == null && other.eduId != null) || (this.eduId != null && !this.eduId.equals(other.eduId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwEducation[ eduId=" + eduId + " ]";
    }
    
}

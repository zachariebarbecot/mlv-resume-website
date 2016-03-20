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
@Table(name = "rw_employment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwEmployment.findAll", query = "SELECT r FROM RwEmployment r"),
    @NamedQuery(name = "RwEmployment.findByEmpId", query = "SELECT r FROM RwEmployment r WHERE r.empId = :empId"),
    @NamedQuery(name = "RwEmployment.findByEmpJob", query = "SELECT r FROM RwEmployment r WHERE r.empJob = :empJob"),
    @NamedQuery(name = "RwEmployment.findByEmpPlace", query = "SELECT r FROM RwEmployment r WHERE r.empPlace = :empPlace"),
    @NamedQuery(name = "RwEmployment.findByEmpStart", query = "SELECT r FROM RwEmployment r WHERE r.empStart = :empStart"),
    @NamedQuery(name = "RwEmployment.findByEmpEnd", query = "SELECT r FROM RwEmployment r WHERE r.empEnd = :empEnd"),
    @NamedQuery(name = "RwEmployment.findByEmpContent", query = "SELECT r FROM RwEmployment r WHERE r.empContent = :empContent"),
    @NamedQuery(name = "RwEmployment.findByUsr", query = "SELECT r FROM RwEmployment r WHERE r.usrId = :usrId")})
public class RwEmployment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id")
    private Integer empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "emp_job")
    private String empJob;
    @Size(max = 256)
    @Column(name = "emp_place")
    private String empPlace;
    @Column(name = "emp_start")
    @Temporal(TemporalType.DATE)
    private Date empStart;
    @Column(name = "emp_end")
    @Temporal(TemporalType.DATE)
    private Date empEnd;
    @Size(max = 2147483647)
    @Column(name = "emp_content")
    private String empContent;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RwUser usrId;

    public RwEmployment() {
    }

    public RwEmployment(Integer empId) {
        this.empId = empId;
    }

    public RwEmployment(Integer empId, String empJob) {
        this.empId = empId;
        this.empJob = empJob;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpPlace() {
        return empPlace;
    }

    public void setEmpPlace(String empPlace) {
        this.empPlace = empPlace;
    }

    public Date getEmpStart() {
        return empStart;
    }

    public void setEmpStart(Date empStart) {
        this.empStart = empStart;
    }

    public Date getEmpEnd() {
        return empEnd;
    }

    public void setEmpEnd(Date empEnd) {
        this.empEnd = empEnd;
    }

    public String getEmpContent() {
        return empContent;
    }

    public void setEmpContent(String empContent) {
        this.empContent = empContent;
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
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwEmployment)) {
            return false;
        }
        RwEmployment other = (RwEmployment) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwEmployment[ empId=" + empId + " ]";
    }

}

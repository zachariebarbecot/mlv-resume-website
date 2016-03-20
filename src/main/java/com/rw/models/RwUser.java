/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Entity
@Table(name = "rw_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwUser.findAll", query = "SELECT r FROM RwUser r"),
    @NamedQuery(name = "RwUser.findByUsrId", query = "SELECT r FROM RwUser r WHERE r.usrId = :usrId"),
    @NamedQuery(name = "RwUser.findByUsrEmail", query = "SELECT r FROM RwUser r WHERE r.usrEmail = :usrEmail"),
    @NamedQuery(name = "RwUser.findByUsrPassword", query = "SELECT r FROM RwUser r WHERE r.usrPassword = :usrPassword"),
    @NamedQuery(name = "RwUser.findByUsrCreated", query = "SELECT r FROM RwUser r WHERE r.usrCreated = :usrCreated"),
    @NamedQuery(name = "RwUser.findByUsrUpdated", query = "SELECT r FROM RwUser r WHERE r.usrUpdated = :usrUpdated"),
    @NamedQuery(name = "RwUser.findByUsrView", query = "SELECT r FROM RwUser r WHERE r.usrView = :usrView")})
public class RwUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;
    @Basic(optional = false)
    @NotNull(message = "Field Email is empty")
    @Size(min = 1, max = 254)
    @Pattern(regexp = "([^.@]+)(\\.[^.@+]+)*@([^.@]+\\.)+([^.@]+)", message = "Email not valid")
    @Column(name = "usr_email")
    private String usrEmail;
    @Basic(optional = false)
    @NotNull(message = "Field Password is empty")
    @Size(min = 1, max = 64)
    @Column(name = "usr_password")
    private String usrPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrCreated;
    @Column(name = "usr_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrUpdated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_view")
    private int usrView;
    @Column(name = "usr_display_prf")
    private Integer usrDisplayPrf;
    @Column(name = "usr_display_edu")
    private Integer usrDisplayEdu;
    @Column(name = "usr_display_emp")
    private Integer usrDisplayEmp;
    @Column(name = "usr_display_crs")
    private Integer usrDisplayCrs;
    @Column(name = "usr_display_itr")
    private Integer usrDisplayItr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId", fetch = FetchType.EAGER)
    private List<RwCoreskill> rwCoreskillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId", fetch = FetchType.EAGER)
    private List<RwEmployment> rwEmploymentList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rwUser", fetch = FetchType.EAGER)
    private RwProfil rwProfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId", fetch = FetchType.EAGER)
    private List<RwEducation> rwEducationList;
    @JoinColumn(name = "grp_id", referencedColumnName = "grp_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RwGroup grpId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId", fetch = FetchType.EAGER)
    private List<RwInterests> rwInterestsList;

    public RwUser() {
    }

    public RwUser(Integer usrId) {
        this.usrId = usrId;
    }

    public RwUser(Integer usrId, String usrEmail, String usrPassword, Date usrCreated, int usrView) {
        this.usrId = usrId;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
        this.usrCreated = usrCreated;
        this.usrView = usrView;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Date getUsrCreated() {
        return usrCreated;
    }

    public void setUsrCreated(Date usrCreated) {
        this.usrCreated = usrCreated;
    }

    public Date getUsrUpdated() {
        return usrUpdated;
    }

    public void setUsrUpdated(Date usrUpdated) {
        this.usrUpdated = usrUpdated;
    }

    public int getUsrView() {
        return usrView;
    }

    public void setUsrView(int usrView) {
        this.usrView = usrView;
    }

    @XmlTransient
    public List<RwCoreskill> getRwCoreskillList() {
        return rwCoreskillList;
    }

    public void setRwCoreskillList(List<RwCoreskill> rwCoreskillList) {
        this.rwCoreskillList = rwCoreskillList;
    }

    @XmlTransient
    public List<RwEmployment> getRwEmploymentList() {
        return rwEmploymentList;
    }

    public void setRwEmploymentList(List<RwEmployment> rwEmploymentList) {
        this.rwEmploymentList = rwEmploymentList;
    }

    public RwProfil getRwProfil() {
        return rwProfil;
    }

    public void setRwProfil(RwProfil rwProfil) {
        this.rwProfil = rwProfil;
    }

    @XmlTransient
    public List<RwEducation> getRwEducationList() {
        return rwEducationList;
    }

    public void setRwEducationList(List<RwEducation> rwEducationList) {
        this.rwEducationList = rwEducationList;
    }

    public RwGroup getGrpId() {
        return grpId;
    }

    public void setGrpId(RwGroup grpId) {
        this.grpId = grpId;
    }

    @XmlTransient
    public List<RwInterests> getRwInterestsList() {
        return rwInterestsList;
    }

    public void setRwInterestsList(List<RwInterests> rwInterestsList) {
        this.rwInterestsList = rwInterestsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RwUser)) {
            return false;
        }
        RwUser other = (RwUser) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwUser[ usrId=" + usrId + " ]";
    }

    public Integer getUsrDisplayPrf() {
        return usrDisplayPrf;
    }

    public void setUsrDisplayPrf(Integer usrDisplayPrf) {
        this.usrDisplayPrf = usrDisplayPrf;
    }

    public Integer getUsrDisplayEdu() {
        return usrDisplayEdu;
    }

    public void setUsrDisplayEdu(Integer usrDisplayEdu) {
        this.usrDisplayEdu = usrDisplayEdu;
    }

    public Integer getUsrDisplayEmp() {
        return usrDisplayEmp;
    }

    public void setUsrDisplayEmp(Integer usrDisplayEmp) {
        this.usrDisplayEmp = usrDisplayEmp;
    }

    public Integer getUsrDisplayCrs() {
        return usrDisplayCrs;
    }

    public void setUsrDisplayCrs(Integer usrDisplayCrs) {
        this.usrDisplayCrs = usrDisplayCrs;
    }

    public Integer getUsrDisplayItr() {
        return usrDisplayItr;
    }

    public void setUsrDisplayItr(Integer usrDisplayItr) {
        this.usrDisplayItr = usrDisplayItr;
    }

}

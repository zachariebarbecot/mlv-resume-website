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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "rw_profil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RwProfil.findAll", query = "SELECT r FROM RwProfil r"),
    @NamedQuery(name = "RwProfil.findByPrfLname", query = "SELECT r FROM RwProfil r WHERE r.prfLname = :prfLname"),
    @NamedQuery(name = "RwProfil.findByPrfFname", query = "SELECT r FROM RwProfil r WHERE r.prfFname = :prfFname"),
    @NamedQuery(name = "RwProfil.findByPrfEmail", query = "SELECT r FROM RwProfil r WHERE r.prfEmail = :prfEmail"),
    @NamedQuery(name = "RwProfil.findByPrfJob", query = "SELECT r FROM RwProfil r WHERE r.prfJob = :prfJob"),
    @NamedQuery(name = "RwProfil.findByPrfBirthday", query = "SELECT r FROM RwProfil r WHERE r.prfBirthday = :prfBirthday"),
    @NamedQuery(name = "RwProfil.findByPrfAddress", query = "SELECT r FROM RwProfil r WHERE r.prfAddress = :prfAddress"),
    @NamedQuery(name = "RwProfil.findByPrfZip", query = "SELECT r FROM RwProfil r WHERE r.prfZip = :prfZip"),
    @NamedQuery(name = "RwProfil.findByPrfCity", query = "SELECT r FROM RwProfil r WHERE r.prfCity = :prfCity"),
    @NamedQuery(name = "RwProfil.findByPrfTel", query = "SELECT r FROM RwProfil r WHERE r.prfTel = :prfTel"),
    @NamedQuery(name = "RwProfil.findByPrfLink", query = "SELECT r FROM RwProfil r WHERE r.prfLink = :prfLink"),
    @NamedQuery(name = "RwProfil.findByPrfFacebook", query = "SELECT r FROM RwProfil r WHERE r.prfFacebook = :prfFacebook"),
    @NamedQuery(name = "RwProfil.findByPrfTwitter", query = "SELECT r FROM RwProfil r WHERE r.prfTwitter = :prfTwitter"),
    @NamedQuery(name = "RwProfil.findByPrfLinkedin", query = "SELECT r FROM RwProfil r WHERE r.prfLinkedin = :prfLinkedin"),
    @NamedQuery(name = "RwProfil.findByUsrId", query = "SELECT r FROM RwProfil r WHERE r.usrId = :usrId")})
public class RwProfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 65)
    @Column(name = "prf_lname")
    private String prfLname;
    @Size(max = 65)
    @Column(name = "prf_fname")
    private String prfFname;
    @Size(max = 254)
    @Column(name = "prf_email")
    private String prfEmail;
    @Size(max = 254)
    @Column(name = "prf_job")
    private String prfJob;
    @Column(name = "prf_birthday")
    @Temporal(TemporalType.DATE)
    private Date prfBirthday;
    @Size(max = 2147483647)
    @Column(name = "prf_address")
    private String prfAddress;
    @Size(max = 5)
    @Column(name = "prf_zip")
    private String prfZip;
    @Size(max = 65)
    @Column(name = "prf_city")
    private String prfCity;
    @Size(max = 14)
    @Column(name = "prf_tel")
    private String prfTel;
    @Size(max = 2083)
    @Column(name = "prf_link")
    private String prfLink;
    @Size(max = 64)
    @Column(name = "prf_facebook")
    private String prfFacebook;
    @Size(max = 64)
    @Column(name = "prf_twitter")
    private String prfTwitter;
    @Size(max = 64)
    @Column(name = "prf_linkedin")
    private String prfLinkedin;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_id")
    private Integer usrId;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private RwUser rwUser;

    public RwProfil() {
    }

    public RwProfil(Integer usrId) {
        this.usrId = usrId;
    }

    public String getPrfLname() {
        return prfLname;
    }

    public void setPrfLname(String prfLname) {
        this.prfLname = prfLname;
    }

    public String getPrfFname() {
        return prfFname;
    }

    public void setPrfFname(String prfFname) {
        this.prfFname = prfFname;
    }

    public String getPrfEmail() {
        return prfEmail;
    }

    public void setPrfEmail(String prfEmail) {
        this.prfEmail = prfEmail;
    }

    public String getPrfJob() {
        return prfJob;
    }

    public void setPrfJob(String prfJob) {
        this.prfJob = prfJob;
    }

    public Date getPrfBirthday() {
        return prfBirthday;
    }

    public void setPrfBirthday(Date prfBirthday) {
        this.prfBirthday = prfBirthday;
    }

    public String getPrfAddress() {
        return prfAddress;
    }

    public void setPrfAddress(String prfAddress) {
        this.prfAddress = prfAddress;
    }

    public String getPrfZip() {
        return prfZip;
    }

    public void setPrfZip(String prfZip) {
        this.prfZip = prfZip;
    }

    public String getPrfCity() {
        return prfCity;
    }

    public void setPrfCity(String prfCity) {
        this.prfCity = prfCity;
    }

    public String getPrfTel() {
        return prfTel;
    }

    public void setPrfTel(String prfTel) {
        this.prfTel = prfTel;
    }

    public String getPrfLink() {
        return prfLink;
    }

    public void setPrfLink(String prfLink) {
        this.prfLink = prfLink;
    }

    public String getPrfFacebook() {
        return prfFacebook;
    }

    public void setPrfFacebook(String prfFacebook) {
        this.prfFacebook = prfFacebook;
    }

    public String getPrfTwitter() {
        return prfTwitter;
    }

    public void setPrfTwitter(String prfTwitter) {
        this.prfTwitter = prfTwitter;
    }

    public String getPrfLinkedin() {
        return prfLinkedin;
    }

    public void setPrfLinkedin(String prfLinkedin) {
        this.prfLinkedin = prfLinkedin;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public RwUser getRwUser() {
        return rwUser;
    }

    public void setRwUser(RwUser rwUser) {
        this.rwUser = rwUser;
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
        if (!(object instanceof RwProfil)) {
            return false;
        }
        RwProfil other = (RwProfil) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rw.models.RwProfil[ usrId=" + usrId + " ]";
    }
    
}

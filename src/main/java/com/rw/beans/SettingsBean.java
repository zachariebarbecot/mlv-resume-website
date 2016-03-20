/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.beans;

import com.rw.facades.RwUserFacade;
import com.rw.utils.Hash;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "settings")
@DeclareRoles({"Administrator", "Candidate", "Employer"})
@RequestScoped
public class SettingsBean {

    private String email;
    private String password;

    @Inject
    private UserBean user;

    @EJB
    private RwUserFacade usrFacade;

    /**
     * Creates a new instance of settingsBean
     */
    public SettingsBean() {
    }

    @RolesAllowed({"Administrator", "Candidate", "Employer"})
    public String changeEmail() {
        if (!user.getUser().getUsrEmail().equals(email)) {
            if (usrFacade.findByUsrEmail(email) == null) {
                this.user.getUser().setUsrEmail(email);
                this.user.getUser().setUsrUpdated(new Date());
                this.usrFacade.edit(user.getUser());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email changed", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email already exists", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email doesn't change", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        this.email = null;
        return "toProfil";
    }

    @RolesAllowed({"Administrator", "Candidate", "Employer"})
    public String changePassword() {
        try {
            String passwordHash = Hash.hashPassword(password);
            if (!user.getUser().getUsrPassword().equals(passwordHash)) {
                this.user.getUser().setUsrPassword(passwordHash);
                this.user.getUser().setUsrUpdated(new Date());
                this.usrFacade.edit(user.getUser());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password changed", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password doesn't change", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            }
        } catch (NoSuchAlgorithmException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password doesn't change", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        this.password = null;
        return "toProfil";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

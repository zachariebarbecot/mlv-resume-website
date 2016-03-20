package com.rw.beans;

import com.rw.facades.RwGroupFacade;
import com.rw.facades.RwUserFacade;
import com.rw.models.RwUser;
import com.rw.utils.Hash;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "register")
@RequestScoped
public class RegisterBean {

    private RwUser user;
    private Integer grpId;

    @EJB
    private RwUserFacade usrFacade;
    @EJB
    private RwGroupFacade grpFacade;

    public RegisterBean() {
    }

    @PostConstruct
    public void init() {
        this.user = new RwUser();
    }

    public String doSignUp() {
        if (!user.getUsrEmail().isEmpty()) {
            if (!user.getUsrPassword().isEmpty()) {
                if (grpId != null) {
                    if (grpId == 2 || grpId == 3) {
                        this.user.setUsrCreated(new Date());
                        //this.user.setUsrDisplay(62);
                        this.user.setGrpId(grpFacade.findByGrpId(grpId));
                        RwUser testUser = usrFacade.findByUsrEmail(user.getUsrEmail());
                        if (testUser == null) {
                            try {
                                this.user.setUsrPassword(Hash.hashPassword(user.getUsrPassword()));
                                this.user.setUsrDisplayPrf(1);
                                this.user.setUsrDisplayEdu(1);
                                this.user.setUsrDisplayEmp(1);
                                this.user.setUsrDisplayCrs(1);
                                this.user.setUsrDisplayItr(1);
                                this.usrFacade.create(user);
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User is created", null));
                                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                                return "toIndex";
                            } catch (NoSuchAlgorithmException e) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password doesn't change", null));
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User already exists", null));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Group is wrong", null));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No group selected", null));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Password Field is empty", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email Field is empty", null));

        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toSignUp";
    }

    public RwUser getUser() {
        return user;
    }

    public void setUser(RwUser user) {
        this.user = user;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

}

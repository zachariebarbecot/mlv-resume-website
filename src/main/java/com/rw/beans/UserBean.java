package com.rw.beans;

import com.rw.facades.RwUserFacade;
import com.rw.models.RwUser;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "user")
@DeclareRoles({"Administrator", "Candidate", "Employer"})
@ConversationScoped
public class UserBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    private RwUser user;

    @EJB
    private RwUserFacade usrFacade;

    public UserBean() {
    }

    @PostConstruct
    public void init() {
        this.user = loginBean.getUser();
    }

    @RolesAllowed({"Administrator", "Candidate", "Employer"})
    public String displayProfil() {
        this.user = usrFacade.find(user.getUsrId());
        return "toProfil";
    }

    public RwUser getUser() {
        return user;
    }

    public void setUser(RwUser user) {
        this.user = user;
    }

}

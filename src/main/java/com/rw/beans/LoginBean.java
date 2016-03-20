package com.rw.beans;

import com.rw.facades.RwUserFacade;
import com.rw.models.RwUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "login")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    private RwUser user;
    private boolean loggedIn;

    @EJB
    private RwUserFacade usrFacade;

    public LoginBean() {
    }

    public String doLogin() {
        if (email != null && password != null) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                request.login(email, password);
                String principal = request.getUserPrincipal().toString();
                this.user = usrFacade.findByUsrEmail(principal);
                this.loggedIn = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Log in success", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            } catch (ServletException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Log in error", null));
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Field(s) empty", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        }
        this.email = password = null;
        return "toIndex";
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

    public RwUser getUser() {
        return user;
    }

    public void setUser(RwUser user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}

package com.rw.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "language")
@SessionScoped
public class LanguageBean implements Serializable {

    private Locale locale;

    @PostConstruct
    public void init() {
        this.locale = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        this.locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}

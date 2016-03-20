package com.rw.beans;

import com.rw.facades.RwCoreskillFacade;
import com.rw.facades.RwEducationFacade;
import com.rw.facades.RwEmploymentFacade;
import com.rw.facades.RwInterestsFacade;
import com.rw.facades.RwProfilFacade;
import com.rw.facades.RwUserFacade;
import com.rw.models.RwCoreskill;
import com.rw.models.RwEducation;
import com.rw.models.RwEmployment;
import com.rw.models.RwInterests;
import com.rw.models.RwProfil;
import com.rw.models.RwUser;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Named(value = "edition")
@RolesAllowed("Candidate")
@RequestScoped
public class EditionBean {

    @Inject
    private UserBean userBean;

    private RwUser user;
    private RwProfil profil;
    private List<RwEducation> education;
    private List<RwEmployment> employment;
    private List<RwCoreskill> coreskill;
    private List<RwInterests> interests;
    private RwEducation newEdu;
    private RwEducation delEdu;
    private RwEmployment newEmp;
    private RwEmployment delEmp;
    private RwCoreskill newCrs;
    private RwCoreskill delCrs;
    private RwInterests newItr;
    private RwInterests delItr;

    private int displayPrf;
    private int displayEdu;
    private int displayEmp;
    private int displayCrs;
    private int displayItr;

    @EJB
    private RwUserFacade usrFacade;
    @EJB
    private RwProfilFacade prfFacade;
    @EJB
    private RwEducationFacade eduFacade;
    @EJB
    private RwEmploymentFacade empFacade;
    @EJB
    private RwCoreskillFacade crsFacade;
    @EJB
    private RwInterestsFacade itrFacade;

    public EditionBean() {
    }

    @PostConstruct
    public void init() {
        this.user = userBean.getUser();
        this.profil = prfFacade.findByUsrId(user.getUsrId());
        if (profil == null) {
            this.profil = new RwProfil();
        }
        this.education = eduFacade.findByUsr(user);
        this.employment = empFacade.findByUsr(user);
        this.coreskill = crsFacade.findByUsr(user);
        this.interests = itrFacade.findByUsr(user);
        this.newEdu = new RwEducation();
        this.newEmp = new RwEmployment();
        this.newCrs = new RwCoreskill();
        this.newItr = new RwInterests();
        this.displayPrf = user.getUsrDisplayPrf();
        this.displayEdu = user.getUsrDisplayEdu();
        this.displayEmp = user.getUsrDisplayEmp();
        this.displayCrs = user.getUsrDisplayCrs();
        this.displayItr = user.getUsrDisplayItr();
    }

    @RolesAllowed("Candidate")
    public String changeProfil() {
        if (prfFacade.findByUsrId(userBean.getUser().getUsrId()) != null) {
            this.prfFacade.edit(profil);
        } else {
            this.profil.setUsrId(user.getUsrId());
            System.out.println(profil);
            this.prfFacade.create(profil);
        }
        if (user.getUsrDisplayPrf() != displayPrf) {
            this.user.setUsrDisplayPrf(displayPrf);
            this.usrFacade.edit(user);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Profil modified", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String changeEducation() {
        this.user = userBean.getUser();
        if (education != null) {
            this.eduFacade.editEducationList(education);
        }
        if (user.getUsrDisplayEdu() != displayEdu) {
            this.user.setUsrDisplayEdu(displayEdu);
            this.usrFacade.edit(user);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Formations modified", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String addEducation() {
        if (newEdu != null) {
            this.user = userBean.getUser();
            this.newEdu.setUsrId(user);
            this.eduFacade.create(newEdu);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Formation added", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        newEdu = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String deleteEducation() {
        if (delEdu != null) {
            this.eduFacade.remove(delEdu);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Formation deleted", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        delEdu = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String changeEmployment() {
        this.user = userBean.getUser();
        if (employment != null) {
            this.empFacade.editEmploymentList(employment);
        }
        if (user.getUsrDisplayEmp() != displayEmp) {
            this.user.setUsrDisplayEmp(displayEmp);
            this.usrFacade.edit(user);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jobs modified", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String addEmployment() {
        if (newEmp != null) {
            this.user = userBean.getUser();
            this.newEmp.setUsrId(user);
            this.empFacade.create(newEmp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Job added", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        newEmp = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String deleteEmployment() {
        if (delEmp != null) {
            this.empFacade.remove(delEmp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Job deleted", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        delEmp = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String changeCoreskill() {
        this.user = userBean.getUser();
        if (coreskill != null) {
            this.crsFacade.editCoreskillList(coreskill);
        }
        if (user.getUsrDisplayCrs() != displayCrs) {
            this.user.setUsrDisplayCrs(displayCrs);
            this.usrFacade.edit(user);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Skills modified", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String addCoreskill() {
        if (newCrs != null) {
            this.user = userBean.getUser();
            this.newCrs.setUsrId(user);
            this.crsFacade.create(newCrs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Skill added", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        newCrs = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String deleteCoreskill() {
        if (delCrs != null) {
            this.crsFacade.remove(delCrs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Skill deleted", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        newCrs = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String changeInterests() {
        this.user = userBean.getUser();
        if (interests != null) {
            this.itrFacade.editInterestsList(interests);
        }
        if (user.getUsrDisplayItr() != displayItr) {
            this.user.setUsrDisplayItr(displayItr);
            this.usrFacade.edit(user);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Interests modified", null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String addInterests() {
        if (newItr != null) {
            this.user = userBean.getUser();
            this.newItr.setUsrId(user);
            this.itrFacade.create(newItr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Interest added", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        newItr = null;
        return "toEdit";
    }

    @RolesAllowed("Candidate")
    public String deleteInterests() {
        if (delItr != null) {
            this.itrFacade.remove(delItr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Interest deleted", null));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        delItr = null;
        return "toEdit";
    }

    public RwUser getUser() {
        return user;
    }

    public void setUser(RwUser user) {
        this.user = user;
    }

    public RwProfil getProfil() {
        return profil;
    }

    public void setProfil(RwProfil profil) {
        this.profil = profil;
    }

    public List<RwEducation> getEducation() {
        return education;
    }

    public void setEducation(List<RwEducation> education) {
        this.education = education;
    }

    public List<RwEmployment> getEmployment() {
        return employment;
    }

    public void setEmployment(List<RwEmployment> employment) {
        this.employment = employment;
    }

    public List<RwCoreskill> getCoreskill() {
        return coreskill;
    }

    public void setCoreskill(List<RwCoreskill> coreskill) {
        this.coreskill = coreskill;
    }

    public List<RwInterests> getInterests() {
        return interests;
    }

    public void setInterests(List<RwInterests> interests) {
        this.interests = interests;
    }

    public int getDisplayPrf() {
        return displayPrf;
    }

    public void setDisplayPrf(int displayPrf) {
        this.displayPrf = displayPrf;
    }

    public int getDisplayEdu() {
        return displayEdu;
    }

    public void setDisplayEdu(int displayEdu) {
        this.displayEdu = displayEdu;
    }

    public int getDisplayEmp() {
        return displayEmp;
    }

    public void setDisplayEmp(int displayEmp) {
        this.displayEmp = displayEmp;
    }

    public int getDisplayCrs() {
        return displayCrs;
    }

    public void setDisplayCrs(int displayCrs) {
        this.displayCrs = displayCrs;
    }

    public int getDisplayItr() {
        return displayItr;
    }

    public void setDisplayItr(int displayItr) {
        this.displayItr = displayItr;
    }

    public RwEducation getNewEdu() {
        return newEdu;
    }

    public void setNewEdu(RwEducation newEdu) {
        this.newEdu = newEdu;
    }

    public RwEducation getDelEdu() {
        return delEdu;
    }

    public void setDelEdu(RwEducation delEdu) {
        this.delEdu = delEdu;
    }

    public RwEmployment getNewEmp() {
        return newEmp;
    }

    public void setNewEmp(RwEmployment newEmp) {
        this.newEmp = newEmp;
    }

    public RwEmployment getDelEmp() {
        return delEmp;
    }

    public void setDelEmp(RwEmployment delEmp) {
        this.delEmp = delEmp;
    }

    public RwCoreskill getNewCrs() {
        return newCrs;
    }

    public void setNewCrs(RwCoreskill newCrs) {
        this.newCrs = newCrs;
    }

    public RwCoreskill getDelCrs() {
        return delCrs;
    }

    public void setDelCrs(RwCoreskill delCrs) {
        this.delCrs = delCrs;
    }

    public RwInterests getNewItr() {
        return newItr;
    }

    public void setNewItr(RwInterests newItr) {
        this.newItr = newItr;
    }

    public RwInterests getDelItr() {
        return delItr;
    }

    public void setDelItr(RwInterests delItr) {
        this.delItr = delItr;
    }

}

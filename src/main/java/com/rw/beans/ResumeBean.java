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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Named(value = "resume")
@RequestScoped
public class ResumeBean {

    private Integer usrId;
    private RwUser user;
    private long age;
    private RwProfil profil;
    private List<RwEducation> education;
    private List<RwEmployment> employment;
    private List<RwCoreskill> coreskill;
    private List<RwInterests> interests;

    @Inject
    UserBean userBean;

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

    public ResumeBean() {
    }

    public void display() {
        if (usrId == null && userBean.getUser() != null) {
            this.usrId = userBean.getUser().getUsrId();
        }
        this.user = usrFacade.findByUsrId(usrId);
        if (user != null) {
            if (user.getGrpId().getGrpName().equals("Candidate")) {
                this.user.setUsrView(user.getUsrView() + 1);
                this.usrFacade.edit(user);
                if (user.getUsrDisplayPrf() == 1
                        || (user.getUsrDisplayPrf() == 2 && userBean.getUser() != null)) {
                    this.findProfil();
                }
                if (user.getUsrDisplayEdu() == 1
                        || (user.getUsrDisplayEdu() == 2 && userBean.getUser() != null)) {
                    this.findEducations();
                }
                if (user.getUsrDisplayEmp() == 1
                        || (user.getUsrDisplayEmp() == 2 && userBean.getUser() != null)) {
                    this.findEmployment();
                }
                if (user.getUsrDisplayCrs() == 1
                        || (user.getUsrDisplayCrs() == 2 && userBean.getUser() != null)) {
                    this.findCoreSkills();
                }
                if (user.getUsrDisplayItr() == 1
                        || (user.getUsrDisplayItr() == 2 && userBean.getUser() != null)) {
                    this.findInterests();
                }
            }
        }
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public RwUser getUser() {
        return user;
    }

    public void setUser(RwUser user) {
        this.user = user;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
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

    private void findProfil() {
        this.profil = prfFacade.findByUsrId(usrId);
        if (profil != null) {
            LocalDate now = LocalDate.now();
            if (profil.getPrfBirthday() != null) {
                this.age = ChronoUnit.YEARS.between(profil.getPrfBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        now);
            }
        }
    }

    private void findEducations() {
        this.education = eduFacade.findByUsr(user);
        if (education != null) {
            Collections.sort(education, new CustomComparatorEducation());
            Collections.reverse(education);
        }
    }

    private void findEmployment() {
        this.employment = empFacade.findByUsr(user);
        if (employment != null) {
            Collections.sort(employment, new CustomComparatorEmployment());
            Collections.reverse(employment);
        }
    }

    private void findCoreSkills() {
        this.coreskill = crsFacade.findByUsr(user);
    }

    private void findInterests() {
        this.interests = itrFacade.findByUsr(user);
    }

    private class CustomComparatorEducation implements Comparator<RwEducation> {

        @Override
        public int compare(RwEducation o1, RwEducation o2) {
            return o1.getEduEnd().compareTo(o2.getEduEnd());
        }
    }

    private class CustomComparatorEmployment implements Comparator<RwEmployment> {

        @Override
        public int compare(RwEmployment o1, RwEmployment o2) {
            return o1.getEmpEnd().compareTo(o2.getEmpEnd());
        }
    }
}

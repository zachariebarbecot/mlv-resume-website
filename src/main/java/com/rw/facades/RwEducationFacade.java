/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.facades;

import com.rw.models.RwEducation;
import com.rw.models.RwUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
@Stateless
public class RwEducationFacade extends AbstractFacade<RwEducation> {

    @PersistenceContext(unitName = "com_mlv-resume-website_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RwEducationFacade() {
        super(RwEducation.class);
    }

    public List<RwEducation> findByUsr(RwUser user) {
        Query q = em.createNamedQuery("RwEducation.findByUsr", RwEducation.class);
        q.setParameter("usrId", user);
        List<RwEducation> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void editEducationList(List<RwEducation> education) {
        education.forEach((edu) -> {
            this.em.merge(edu);
        });
    }
}

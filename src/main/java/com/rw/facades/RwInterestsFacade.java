/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.facades;

import com.rw.models.RwInterests;
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
public class RwInterestsFacade extends AbstractFacade<RwInterests> {

    @PersistenceContext(unitName = "com_mlv-resume-website_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RwInterestsFacade() {
        super(RwInterests.class);
    }

    public List<RwInterests> findByUsr(RwUser user) {
        Query q = em.createNamedQuery("RwInterests.findByUsr", RwInterests.class);
        q.setParameter("usrId", user);
        List<RwInterests> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void editInterestsList(List<RwInterests> interests) {
        interests.forEach((itr) -> {
            this.em.merge(itr);
        });
    }
}

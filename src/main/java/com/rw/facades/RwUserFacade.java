/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.facades;

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
public class RwUserFacade extends AbstractFacade<RwUser> {

    @PersistenceContext(unitName = "com_mlv-resume-website_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RwUserFacade() {
        super(RwUser.class);
    }

    public RwUser findByUsrEmail(String email) {
        Query q = em.createNamedQuery("RwUser.findByUsrEmail", RwUser.class);
        q.setParameter("usrEmail", email);
        List<RwUser> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public RwUser findByUsrId(Integer usrId) {
        Query q = em.createNamedQuery("RwUser.findByUsrId", RwUser.class);
        q.setParameter("usrId", usrId);
        List<RwUser> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

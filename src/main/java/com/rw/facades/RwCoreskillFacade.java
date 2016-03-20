/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.facades;

import com.rw.models.RwCoreskill;
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
public class RwCoreskillFacade extends AbstractFacade<RwCoreskill> {

    @PersistenceContext(unitName = "com_mlv-resume-website_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RwCoreskillFacade() {
        super(RwCoreskill.class);
    }

    public List<RwCoreskill> findByUsr(RwUser user) {
        Query q = em.createNamedQuery("RwCoreskill.findByUsr", RwCoreskill.class);
        q.setParameter("usrId", user);
        List<RwCoreskill> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    public void editCoreskillList(List<RwCoreskill> coreskill) {
        coreskill.forEach((crs) -> {
            this.em.merge(crs);
        });
    }
}

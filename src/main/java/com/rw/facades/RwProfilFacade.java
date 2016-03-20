/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rw.facades;

import com.rw.models.RwProfil;
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
public class RwProfilFacade extends AbstractFacade<RwProfil> {

    @PersistenceContext(unitName = "com_mlv-resume-website_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RwProfilFacade() {
        super(RwProfil.class);
    }

    public RwProfil findByUsrId(Integer usrId) {
        Query q = em.createNamedQuery("RwProfil.findByUsrId", RwProfil.class);
        q.setParameter("usrId", usrId);
        List<RwProfil> list = q.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

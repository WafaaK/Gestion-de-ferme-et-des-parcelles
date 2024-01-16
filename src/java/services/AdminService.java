/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author samia
 */

    @Stateless
public class AdminService {
    @PersistenceContext
    private EntityManager entityManager;
     
    
    public Admin findAdminByUsername(String userName) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.userName = :userName", Admin.class);
        query.setParameter("userName", userName);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
public boolean validateLogin(String userName, String password) {
    // Vérifier si le nom d'utilisateur et le mot de passe correspondent à l'un des comptes autorisés
    return ("admin".equals(userName) && "admin".equals(password)) ||
           ("user".equals(userName) && "user".equals(password));
}


     
}


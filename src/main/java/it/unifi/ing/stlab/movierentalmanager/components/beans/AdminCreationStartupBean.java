package it.unifi.ing.stlab.movierentalmanager.components.beans;


import it.unifi.ing.stlab.movierentalmanager.components.factory.ModelFactory;
import it.unifi.ing.stlab.movierentalmanager.model.users.Employee;
import it.unifi.ing.stlab.movierentalmanager.model.users.OfficeRole;
import it.unifi.ing.stlab.movierentalmanager.model.users.WebUser;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Singleton
@Startup
public class AdminCreationStartupBean {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    @Transactional
    public void init(){
        System.out.println("Eseguo");

        Employee admin = ModelFactory.initEmployee();
        admin.setName("admin");
        admin.setRole(OfficeRole.ADMIN);

        WebUser adminWebUser = new WebUser();
        adminWebUser.setUsername("adminUser");
        adminWebUser.setPassword("adminPass");

        admin.setWebUser(adminWebUser);

        /*em.getTransaction().begin();*/
        em.persist(admin);
        /*em.getTransaction().commit();*/

    }

}

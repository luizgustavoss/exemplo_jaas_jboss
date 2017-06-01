package jaas.jboss.utils;

import jaas.jboss.business.BusinessFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class BusinessFactoryStartupListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JaasJbossPU");
		BusinessFactory.setEntityManagerFactory(emf);	    
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		EntityManagerFactory emf = BusinessFactory.getEntityManagerFactory();
		BusinessFactory.setEntityManagerFactory(null);
	    
	    if (emf != null && emf.isOpen()) {
	      emf.close();
	    }
	}
		
}

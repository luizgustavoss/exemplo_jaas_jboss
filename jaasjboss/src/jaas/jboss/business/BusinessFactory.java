package jaas.jboss.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BusinessFactory {
	
	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private BusinessFactory(){
		
	}
	
	public static BusinessFactory getInstance(){
		return new BusinessFactory();
	}
		
	public CidadeBO getCidadeBO(){
		return CidadeBO.getInstance(entityManager);
	}
	
	
	
	public void beginTransaction() {
		if (entityManager == null || ! entityManager.isOpen()) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		if (! entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
	}
	
	  
	public void commitTransaction(boolean releaseResources) {
		if (entityManager != null && entityManager.isOpen()) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().commit();
			}
			if (releaseResources) { 
				this.entityManager.close();
				this.entityManager = null;
			}
		}
	}

	  
	public void rollbackTransaction(boolean releaseResources) {
		if (entityManager != null && entityManager.isOpen()) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			if (releaseResources) { 
				this.entityManager.close();
				this.entityManager = null;
			}
		}
	}
	
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		BusinessFactory.entityManagerFactory = entityManagerFactory;
	}
}

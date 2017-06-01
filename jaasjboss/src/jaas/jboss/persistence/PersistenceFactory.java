package jaas.jboss.persistence;

import javax.persistence.EntityManager;

public class PersistenceFactory {
			
	public static CidadeDAO getCidadeDAO(EntityManager entityManager){
		return CidadeDAO.getInstance(entityManager);
	}	
}

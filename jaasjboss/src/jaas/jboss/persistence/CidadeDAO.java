package jaas.jboss.persistence;

import jaas.jboss.entidades.Cidade;
import jaas.jboss.utils.BusinessException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CidadeDAO{

	private EntityManager entityManager;
	
	private CidadeDAO(EntityManager entityManager){	
		this.entityManager = entityManager;
	}
	
	public static CidadeDAO getInstance(EntityManager entityManager){
		return new CidadeDAO(entityManager);
	}
	
	public void cadastrar(Cidade cidade) 
		throws BusinessException, Exception{
		
		if(cidade == null){
			throw new BusinessException("Argumento nulo!");
		}
		entityManager.persist(cidade);
		entityManager.flush();
	}
	
	public void excluir(Cidade cidade) 
		throws BusinessException, Exception{
		
		if(cidade == null || cidade.getCodigo() == null){
			throw new BusinessException("Argumento nulo!");
		}
		Cidade cidadeRemover = entityManager.find(Cidade.class, cidade.getCodigo());
		entityManager.remove(cidadeRemover);
		entityManager.flush();
	}
	
	public Cidade alterar(Cidade cidade) 
		throws BusinessException, Exception{
		
		if(cidade == null){
			throw new BusinessException("Argumento nulo!");
		}
		Cidade object = entityManager.merge(cidade);
		entityManager.flush();
		return object;
	}

	public Cidade obterCidade(Integer codigo) 
		throws BusinessException, Exception{
		
		if(codigo == null){
			throw new BusinessException("Argumento nulo!");
		}
		return entityManager.find(Cidade.class, codigo);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Cidade> obterCidades(String sigla) 
		throws BusinessException, Exception{		
		
		Query query =  entityManager.createQuery("FROM Cidade cidade WHERE cidade.estado = :estado order by cidade.nome");
		query.setParameter("estado", sigla);
		List<Cidade> cidades = (ArrayList<Cidade>) query.getResultList();
		return cidades;		
	}
}

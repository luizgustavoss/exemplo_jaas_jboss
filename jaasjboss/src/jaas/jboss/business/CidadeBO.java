package jaas.jboss.business;

import jaas.jboss.entidades.Cidade;
import jaas.jboss.persistence.PersistenceFactory;
import jaas.jboss.utils.BusinessException;

import java.util.List;

import javax.persistence.EntityManager;


public class CidadeBO {
	
	private EntityManager entityManager;
	
	private CidadeBO(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public static CidadeBO getInstance(EntityManager entiyManager){
		return new CidadeBO(entiyManager);			
	}	

	public void cadastrar(Cidade cidade) 
		throws BusinessException, Exception{
		if(cidade == null){
			throw new BusinessException("Argumento nulo!");
		}				
		PersistenceFactory.getCidadeDAO(entityManager).cadastrar(cidade);
	}

	public Cidade alterar(Cidade cidade) 
		throws BusinessException, Exception{
		if(cidade == null){
			throw new BusinessException("Argumento nulo!");
		}
		
		return PersistenceFactory.getCidadeDAO(entityManager).alterar(cidade);
	}

	public void excluir(Cidade cidade) 
		throws BusinessException, Exception{
		if(cidade == null || cidade.getCodigo() == null){
			throw new BusinessException("Argumento nulo!");
		}
		PersistenceFactory.getCidadeDAO(entityManager).excluir(cidade);
		
	}

	public Cidade obterCidade(Integer codigo) 
		throws BusinessException, Exception{
		if(codigo == null){
			throw new BusinessException("Argumento nulo!");
		}
		return PersistenceFactory.getCidadeDAO(entityManager)
		.obterCidade(codigo);
	}

	public List<Cidade> obterCidades(String sigla) 
		throws BusinessException, Exception{			
		return PersistenceFactory.getCidadeDAO(entityManager)
			.obterCidades(sigla);
	}
	
}

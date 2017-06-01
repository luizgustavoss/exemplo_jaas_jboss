package jaas.jboss.business;

import jaas.jboss.entidades.Cidade;
import jaas.jboss.utils.ApplicationException;
import jaas.jboss.utils.BusinessException;

import java.util.List;


public class CidadeAPS {

	
	public static CidadeAPS getInstance(){
		
		return new CidadeAPS();
	}
	
	
	public List<Cidade> obterCidades(String sigla) 
		throws BusinessException, ApplicationException{
		
		List<Cidade> resultado = null;
		BusinessFactory factory = BusinessFactory.getInstance();
		try {
			factory.beginTransaction();
			resultado = factory.getCidadeBO().obterCidades(sigla);
			factory.commitTransaction(true);
	    } catch (BusinessException e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw e;
	    } catch (Exception e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(), e);
	    }	    
	    return resultado;
	}	
	
	public Cidade obterCidade(Integer codigo) 
		throws BusinessException, ApplicationException{
		
		Cidade resultado = null;
		BusinessFactory factory = BusinessFactory.getInstance();
		try {
			factory.beginTransaction();
			resultado = factory.getCidadeBO().obterCidade(codigo);
			factory.commitTransaction(true);
	    } catch (BusinessException e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw e;
	    } catch (Exception e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(), e);
	    }
	    return resultado;
	}
	
	public void cadastrar(Cidade cidade) 
		throws BusinessException, ApplicationException{
				
		BusinessFactory factory = BusinessFactory.getInstance();
		try {
			factory.beginTransaction();
			factory.getCidadeBO().cadastrar(cidade);
			factory.commitTransaction(true);
	    } catch (BusinessException e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw e;
	    } catch (Exception e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(), e);
	    }
	}	

	public void alterar(Cidade cidade) 
		throws BusinessException, ApplicationException{
				
		BusinessFactory factory = BusinessFactory.getInstance();
		try {
			factory.beginTransaction();
			factory.getCidadeBO().alterar(cidade);
			factory.commitTransaction(true);
	    } catch (BusinessException e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw e;
	    } catch (Exception e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(), e);
	    }
	}

	public void excluir(Cidade cidade) 
		throws BusinessException, ApplicationException{
				
		BusinessFactory factory = BusinessFactory.getInstance();
		try {
			factory.beginTransaction();
			factory.getCidadeBO().excluir(cidade);
			factory.commitTransaction(true);
	    } catch (BusinessException e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw e;
	    } catch (Exception e) {
	    	factory.rollbackTransaction(true);
	    	e.printStackTrace();
	    	throw new ApplicationException(e.getMessage(), e);
	    }
	}	
	
}

package jaas.jboss.utils;

public class BusinessException extends Exception{

	private static final long serialVersionUID = 1L;

	public BusinessException(String mensagem){
		super(mensagem);
	}
}

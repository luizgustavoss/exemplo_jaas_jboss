package jaas.jboss.utils;

public class ApplicationException extends Exception{

	private static final long serialVersionUID = 1L;

	public ApplicationException(String mensagem, Exception e){
		super(mensagem, e);
	}
}
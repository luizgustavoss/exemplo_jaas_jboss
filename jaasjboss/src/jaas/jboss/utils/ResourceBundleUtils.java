package jaas.jboss.utils;

import java.util.ResourceBundle;

public class ResourceBundleUtils {

	private ResourceBundle bundle = 
		ResourceBundle.getBundle("jaas.jboss.i18n.mensagens");
	
	public String obterMensagem(String chave){
		String mensagem = "";
		if(bundle.getString(chave) != null && !"".equals(bundle.getString(chave))){
			mensagem = bundle.getString(chave);
		}
		return mensagem;
	}
}

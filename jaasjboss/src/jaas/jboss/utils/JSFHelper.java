package jaas.jboss.utils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JSFHelper {
	
	  public enum MessageSeverity {
		ERROR, INFO, WARN;
	  }
	  
	  public static ResourceBundleUtils bundle = new ResourceBundleUtils();

	  public static synchronized ExternalContext getExternalContext() {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    return facesContext.getExternalContext();
	  }
	  
	  public static synchronized String getRequestParameter(String parameterName) {
	    Map paramMap = getExternalContext().getRequestParameterMap();
	    return (String) paramMap.get(parameterName);
	  }

	  public static synchronized String getHeaderParameter(String parameterName) {
	    Map headerMap = getExternalContext().getRequestHeaderMap();
	    return (String) headerMap.get(parameterName);
	  }
	  
	  public static synchronized Object getRequestAttribute(String attributeName) {
	    Map attrMap = getExternalContext().getRequestMap();
	    return attrMap.get(attributeName);
	  }

	  public static synchronized Object getSessionAtribute(String attributeName) {
	    Map attrMap = getExternalContext().getSessionMap();
	    return attrMap.get(attributeName);
	  }  

	  public static synchronized Object getApplicationAttribute(String attributeName) {
	    Map reqAttrMap = getExternalContext().getApplicationMap();
	    return reqAttrMap.get(attributeName);
	  }
	  
	  public static synchronized String getInitParameter(String initParameterName) {
	    return getExternalContext().getInitParameter(initParameterName);
	  }
	  
	  
	  public static synchronized void addGlobalMessage(String message, MessageSeverity severity) {
		System.out.println(bundle.obterMensagem(message));
	    FacesMessage facesMessage = new FacesMessage();
	    facesMessage.setDetail(bundle.obterMensagem(message));
	    if(severity.equals(MessageSeverity.ERROR)){
	    	facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	    }
	    else if(severity.equals(MessageSeverity.WARN)){
	    	facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
	    }
	    else if(severity.equals(MessageSeverity.INFO)){
	    	facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	    }
	    FacesContext.getCurrentInstance().addMessage(null, facesMessage);    
	  }
	  
	}


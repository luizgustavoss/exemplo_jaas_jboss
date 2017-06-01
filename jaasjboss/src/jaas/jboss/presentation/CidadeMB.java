package jaas.jboss.presentation;

import jaas.jboss.business.CidadeAPS;
import jaas.jboss.entidades.Cidade;
import jaas.jboss.utils.ApplicationException;
import jaas.jboss.utils.BusinessException;
import jaas.jboss.utils.JSFHelper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;


public class CidadeMB {

	private org.apache.log4j.Logger logger = Logger
	.getLogger(CidadeMB.class.getName());
	
	private String codigo;
	private String nome;
	private String estado;	
	private List<SelectItem> itensEstados = new ArrayList<SelectItem>();
	private List<Cidade> cidades;	
	private boolean habilitarModificacoes;
	
	private CidadeAPS businessDelegate = CidadeAPS.getInstance();;
	
	private CidadeAPS getBusinessDelegate(){
		if(businessDelegate == null){
			businessDelegate = CidadeAPS.getInstance();
		}
		return businessDelegate;
	}
	
	public CidadeMB(){
				
		try {
			limparCampos();
			configurarEstados();			
			setCidades(getBusinessDelegate().obterCidades(estado));
			setHabilitarModificacoes(false);
		} catch (BusinessException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.WARN);
	    	logger.error(e.getMessage());	    	
	    } catch (ApplicationException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.ERROR);
	    	logger.error(e.getMessage());	    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage());	    	
	    }	
	}
	
	
	private void configurarEstados(){
		
		String estados[] = {"SP", "RJ", "DF", "MG" };
		
		try {
			itensEstados = new ArrayList<SelectItem>();			
			
			for(String e : estados){
				itensEstados.add(new SelectItem(e, e));
			}
			
			setEstado(String.valueOf(estados[1]));
			
	    } catch (Exception e) {
	    	logger.error(e.getMessage());	    	
	    }
	}
	
		
	public void cmbEstadoChange(ValueChangeEvent event){
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			this.estado = (String) event.getNewValue();				
			setCidades(getBusinessDelegate().obterCidades(this.estado));
			context.renderResponse();
		} catch (BusinessException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.WARN);
	    	logger.error(e.getMessage());	    	
	    } catch (ApplicationException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.ERROR);
	    	logger.error(e.getMessage());    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    	
	    }
		
	}
	
	
	public String novo(){		
		limparCampos();
		setHabilitarModificacoes(true);
		return "gerenciaCidades";
	}	
	
	
	public String salvar(){
		
		try {
			
			Cidade cidade = new Cidade();
			cidade.setNome(this.nome);
			cidade.setEstado(this.estado);
			
			if(this.codigo!= null && !"".equals(this.codigo)){
				cidade.setCodigo(Integer.parseInt(this.codigo));
			}			
			if(cidade.getCodigo() == null){
				getBusinessDelegate().cadastrar(cidade);
			}
			else{
				getBusinessDelegate().alterar(cidade);
			}			
			setCidades(getBusinessDelegate().obterCidades(this.estado));
			limparCampos();
			setHabilitarModificacoes(false);
			JSFHelper.addGlobalMessage("msgSucessoOperacao", JSFHelper.MessageSeverity.INFO);
	    } catch (BusinessException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.WARN);
	    	logger.error(e.getMessage());	    	
	    } catch (ApplicationException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.ERROR);
	    	logger.error(e.getMessage());	    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage());	
	    	
	    }    
		return "gerenciaCidades";
	}
	
	
	public String cancelar(){
		limparCampos();
		setHabilitarModificacoes(false);
		return "gerenciaCidades";
	}
	
	
	public String alterar(){
		try{
			String codigo = JSFHelper.getRequestParameter("codigo");
			if(codigo != null){
				Cidade cidade = getBusinessDelegate().obterCidade(Integer.parseInt(codigo));
				setCodigo(cidade.getCodigo().toString());
				setNome(cidade.getNome());
				setEstado(String.valueOf(cidade.getEstado()));
				setHabilitarModificacoes(true);
			}			
		} catch (BusinessException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.WARN);
	    	logger.error(e.getMessage());	    	
	    } catch (ApplicationException e) {
	    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.ERROR);
	    	logger.error(e.getMessage());	    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage());	    	
	    }	
		return "gerenciaCidades";
	}
		

	public String excluir(){
		
		String codigo = JSFHelper.getRequestParameter("codigo");
		if(codigo != null){
			
			try {
				Cidade cidade = getBusinessDelegate().obterCidade(Integer.parseInt(codigo));
				getBusinessDelegate().excluir(cidade);				
				setCidades(getBusinessDelegate().obterCidades(this.estado));
				limparCampos();
				setHabilitarModificacoes(false);
				JSFHelper.addGlobalMessage("msgSucessoOperacao", JSFHelper.MessageSeverity.INFO);
		    } catch (BusinessException e) {
		    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.WARN);
		    	logger.error(e.getMessage());
		    } catch (ApplicationException e) {
		    	JSFHelper.addGlobalMessage(e.getMessage(), JSFHelper.MessageSeverity.ERROR);
		    	logger.error(e.getMessage());		    	
		    } catch (Exception e) {
		    	logger.error(e.getMessage());
		    }		    
		}
		return "gerenciaCidades";
	}
	
	
	private void limparCampos(){
		setCodigo(null);
		setNome("");				
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<SelectItem> getItensEstados() {
		return itensEstados;
	}

	public void setItensEstados(List<SelectItem> itensEstados) {
		this.itensEstados = itensEstados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	public boolean isHabilitarModificacoes() {
		return habilitarModificacoes;
	}


	public void setHabilitarModificacoes(boolean habilitarModificacoes) {
		this.habilitarModificacoes = habilitarModificacoes;
	}
	
	
}

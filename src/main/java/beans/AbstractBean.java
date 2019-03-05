package beans;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

import entity.Grupo;
import entity.SituacaoAluguel;
import entity.SituacaoItem;
import entity.Tipo;
import entity.Usuario;
import filter.UsuarioFilter;
import service.ServiceLocadoraException;
import service.UsuarioService;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 7887186144461468149L;

	@Inject
	private UsuarioService userService;
	
	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe);

	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe);
	}

	private void reportarMensagem(boolean isErro, String detalhe) {
		String tipo = "Sucesso!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		if (isErro) {
			tipo = "Erro!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, tipo + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public boolean isUserInRole(String role) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role);
	}
	
	//verificar se o usuário está em uma das duas roles
	public boolean isUserInRoles(String role1, String role2) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole(role1) || externalContext.isUserInRole(role2);
	}
	
	public String getUserLogin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return "";
		}
		
		return userPrincipal.getName();
	}
	
	public Usuario getUsuarioLogado() {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setLogin(getUserLogin());
		List<Usuario> users = null;
		try {
			users = userService.findBy(filter);
		} catch (ServiceLocadoraException e) {
			e.printStackTrace();
			reportarMensagemDeErro("Erro ao recuperar o usuário logado!");
		}

		if (!users.isEmpty()) {
			return users.get(0);
		}
		
		return null;
	}
	
	public Tipo[] getTipos() {
		return Tipo.values();
	}
	
	public Grupo[] getGrupos() {
		return Grupo.values();
	}
	
	public SituacaoAluguel[] getSituacaoAluguel() {
		return SituacaoAluguel.values();
	}
	
	public SituacaoItem[] getSituacaoItem() {
		return SituacaoItem.values();
	}
	
	public Grupo[] getGrupoUsuario() {
		Grupo[] grupo = new Grupo[1];
		grupo[0] = Grupo.USUARIO;
		return grupo;
	}
	
}

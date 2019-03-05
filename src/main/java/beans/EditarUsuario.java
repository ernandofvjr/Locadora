package beans;



import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Grupo;
import entity.Usuario;
import service.ServiceLocadoraException;
import service.UsuarioService;

@Named
@ViewScoped
public class EditarUsuario extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private Usuario usuario;
	

	
	@Inject
	private UsuarioService userService;
	


	public String init() {
		
		if (usuario == null) {
			usuario = new Usuario();
			verificarGrupo();
		}
		
		else {
			if(isUserInRole("FUNCIONARIO") && usuario.getGrupo() != Grupo.USUARIO) {
				reportarMensagemDeErro("Um funcionário só pode criar ou editar usuários");
				return "/paginas/protegido/funcionario/filtrar_usuario?faces-redirect=true";
				
			}
		}
		
		return null;
		
		
	}
	
		 public boolean isEdicao() {
		        return usuario != null && usuario.getId() != null;
		    }

	public String salvar() {
		if(usuario.getGrupo() == Grupo.USUARIO) {
			if(!notEmpty(usuario.getCep()) && !notEmpty(usuario.getRua()) && !notEmpty(usuario.getNum())) {
				reportarMensagemDeErro("O usuário precisa ter seus campos de endreço preenchidos");
				return null;
			}
			
		}
		
		
		try {
			
			if (isEdicao()) {
				userService.editar(usuario, false);
			} else {
				userService.salvar(usuario);
				
			}
		} catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Usuário '" + usuario.getNome() + "' salvo");

		return "/paginas/protegido/funcionario/filtrar_usuario?faces-redirect=true";
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void checarDisponibilidadeLogin() {
		try {
			userService.validarLogin(usuario);
			reportarMensagemDeSucesso(String.format("Login '%s' está disponível.", usuario.getLogin()));
		} catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	  public boolean ehUsuario() {
		return usuario != null && usuario.getGrupo() == Grupo.USUARIO;
	}
		
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
	
	public void verificarGrupo() {
		if(isUserInRole("FUNCIONARIO")) {
			usuario.setGrupo(Grupo.USUARIO);
		}
	}


}

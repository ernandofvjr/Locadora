package beans;



import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Grupo;
import entity.Usuario;
import service.ServiceLocadoraException;
import service.UsuarioService;

@Named
@ViewScoped
public class Index extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private Usuario usuario;
	
	
	public boolean ehUsuario() {
		return usuario.getGrupo() == Grupo.USUARIO;
	}

	

	@PostConstruct
	public void init() {
		
		usuario = getUsuarioLogado();
		
		
	}
	


	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}





}

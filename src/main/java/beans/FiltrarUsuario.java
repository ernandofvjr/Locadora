package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Usuario;
import filter.UsuarioFilter;
import service.ServiceLocadoraException;
import service.UsuarioService;


@Named
@ViewScoped
public class FiltrarUsuario extends AbstractBean{
	private static final long serialVersionUID = -5976838804313515033L;
	
	private List<Usuario> usuario;
	
	@Inject
	private UsuarioService service;

	private UsuarioFilter filter;
	
	
	public boolean isListUsuarioVazia() {
	    return usuario.isEmpty();
	}





	public List<Usuario> getUsuario() {
		return usuario;
	}





	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}





	public UsuarioFilter getFilter() {
		return filter;
	}


	public void setFilter(UsuarioFilter filter) {
		this.filter = filter;
	}


	@PostConstruct
	public void init() {
	    limpar();
		filtrar();
	}

    public String filtrar() {
		try {
			usuario = service.findBy(getFilter());
		} 
		catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.filter = new UsuarioFilter();
		return null;
	}
	
	public String deletar(Usuario usuario) {
        try {
            service.deletar(usuario);
        } 
        catch (ServiceLocadoraException e) {
            reportarMensagemDeErro(e.getMessage());
            return null;
        }
        reportarMensagemDeSucesso("O usuário '" + usuario.getNome() + "' foi excluído com sucesso!");
        return "/paginas/protegido/funcionario/filtrar_usuario?faces-redirect=true";
    }
}

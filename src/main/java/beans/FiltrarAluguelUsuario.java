package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Aluguel;
import entity.Usuario;
import filter.AluguelFilter;
import service.AluguelService;
import service.ServiceLocadoraException;



@Named
@ViewScoped
public class FiltrarAluguelUsuario extends AbstractBean{
	private static final long serialVersionUID = -5976838804313515033L;
	
	private List<Aluguel> aluguel;
	
	private Usuario usuario;
	
	@Inject
	private AluguelService service;

	private AluguelFilter filter;
	
	
	public boolean isListAluguelVazia() {
	    return aluguel.isEmpty();
	}





	public List<Aluguel> getAluguel() {
		return aluguel;
	}


	


	public Usuario getUsuario() {
		return usuario;
	}





	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}





	public void setAluguel(List<Aluguel> aluguel) {
		this.aluguel = aluguel;
	}





	public AluguelFilter getFilter() {
		return filter;
	}


	public void setFilter(AluguelFilter filter) {
		this.filter = filter;
	}


	@PostConstruct
	public void init() {
		usuario = getUsuarioLogado();
	    limpar();
		filtrar();
	}

    public String filtrar() {
		try {
			filter.setCpfUsuario(usuario.getCpf());
			aluguel = service.findBy(getFilter());
		} 
		catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.filter = new AluguelFilter();
		return null;
	}
	
}

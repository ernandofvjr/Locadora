package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Aluguel;
import filter.AluguelFilter;
import service.AluguelService;
import service.ServiceLocadoraException;



@Named
@ViewScoped
public class FiltrarAluguel extends AbstractBean{
	private static final long serialVersionUID = -5976838804313515033L;
	
	private List<Aluguel> aluguel;
	
	@Inject
	private AluguelService service;

	private AluguelFilter filter;
	
	
	public boolean isListAluguelVazia() {
	    return aluguel.isEmpty();
	}





	public List<Aluguel> getAluguel() {
		return aluguel;
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
	    limpar();
		filtrar();
	}

    public String filtrar() {
		try {
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
	
	public String deletar(Aluguel aluguel) {
        try {
            service.deletar(aluguel);
        } 
        catch (ServiceLocadoraException e) {
            reportarMensagemDeErro(e.getMessage());
            return null;
        }
        reportarMensagemDeSucesso("O aluguel de id '" + aluguel.getId() + "' foi excluído com sucesso!");
        return "/paginas/protegido/funcionario/filtrar_aluguel?faces-redirect=true";
    }
}

package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Item;
import filter.ItemFilter;
import service.ItemService;
import service.ServiceLocadoraException;


@Named
@ViewScoped
public class FiltrarItem extends AbstractBean{
	private static final long serialVersionUID = -5976838804313515033L;
	
	private List<Item> itens;
	
	@Inject
	private ItemService service;

	private ItemFilter filter;
	
	
	public boolean isListaItensVazia() {
	    return itens.isEmpty();
	}

	public List<Item> getItens() {
		return itens;
	}

	public ItemFilter getFilter() {
		return filter;
	}

	public void setFilter(ItemFilter filter) {
		this.filter = filter;
	}

	@PostConstruct
	public void init() {
	    limpar();
		filtrar();
	}

    public String filtrar() {
		try {
			itens = service.findBy(getFilter());
		} 
		catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.filter = new ItemFilter();
		return null;
	}
	
	public String deletar(Item item) {
        try {
            service.deletar(item);
        } 
        catch (ServiceLocadoraException e) {
            reportarMensagemDeErro(e.getMessage());
            return null;
        }
        reportarMensagemDeSucesso("O item '" + item.getNome() + "' foi excluído com sucesso!");
        return "/paginas/protegido/funcionario/admin/filtrar_item?faces-redirect=true";
    }
}

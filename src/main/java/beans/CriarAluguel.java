package beans;



import java.util.Date;
import java.util.List;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Aluguel;
import entity.Item;
import entity.SituacaoAluguel;
import entity.SituacaoItem;
import entity.Usuario;
import filter.ItemFilter;

import service.AluguelService;
import service.ItemService;
import service.ServiceLocadoraException;



@Named
@ViewScoped
public class CriarAluguel extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private Aluguel aluguel;
	
	private Usuario usuario;
	
	
	

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}


	@Inject
	private AluguelService aluguelService;
	


	public void init() {
	    limpar();
		filtrar();
		if (aluguel == null) {
			aluguel = new Aluguel();
		}

	}
	

	public String salvar() {
		try {
			if(listaAluguelVazia()) {
				reportarMensagemDeErro("O aluguel deve conter pelo menos 1 item");
				return null;
			}
			
			if(!compararDataEntrega(aluguel.getDataEntrega(), aluguel.getDataAluguel())) {
				reportarMensagemDeErro("A data de entrega deve ser maior ou igual que a data do Aluguel");
				return null;
			}
			
			if(usuario != null) {
				aluguel.setUsuario(usuario);
				aluguel.setSituacao(SituacaoAluguel.NAO_ENTRGUE);
				for(Item i: aluguel.getItens()) {
					service.editar(i);
				}
				aluguelService.salvar(aluguel);
				
			}
			else {
				reportarMensagemDeErro("O aluguel deve estar relacionado a um usuário");
				return "/paginas/protegido/funcionario/filtrar_usuario.xhtml?faces-redirect=true";
			}

		} catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Aluguel salvo");

		return "/paginas/protegido/funcionario/filtrar_aluguel.xhtml?faces-redirect=true";
	}
	
	public boolean listaAluguelVazia() {
		if(aluguel.getItens() == null) {
			return true;
		}
		
		
		for(Item i: aluguel.getItens()) {
			if(i!=null) {
				return false;
			}
				
		}
		return true;
		
	}
	
	
	
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


    public String filtrar() {
		try {
			filter.setSituacao(SituacaoItem.DISPONIVEL);
			itens = service.findBy(getFilter());
			
			
			//remover os itens buscados que já estão em aluguel
			if(aluguel != null && itens.size()>0) {
				for(Item i: aluguel.getItens()) {
					if(i != null && itens.contains(i)) {
						itens.remove(i);
					}
				}
			}

			
			
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
	
	
	public String addItemAluguel(Item item) {
		
		if(item.getSituacao()!=SituacaoItem.ALUGADO) {
			
			boolean itemJaAdd = false;
			
			for(Item i: aluguel.getItens()) {
				if(i.equals(item)) {
					itemJaAdd = true;
				}
			}
			
			
			if(!itemJaAdd) {
				item.setSituacao(SituacaoItem.ALUGADO);
				itens.remove(item);
				
				aluguel.getItens().add(item);
			}
			else {
				reportarMensagemDeErro("Este item já foi adicionado");
			}
		}
		else {
			reportarMensagemDeErro("Este item não pode ser alugado");
			
		}
		return null;
	}
	
	public String removerItemAluguel(Item item) {
		
		if(item.getSituacao()==SituacaoItem.ALUGADO) {
			item.setSituacao(SituacaoItem.DISPONIVEL);
			aluguel.getItens().remove(item);
			
		}
		else {
			reportarMensagemDeErro("Este item não pode ser removido");
			
		}
		return null;
	}
	
	
	private boolean compararDataEntrega(Date dataEntrega, Date dataCriacao) {
		if (dataEntrega == null) {
			return true;
		}
		
		return dataEntrega.compareTo(dataCriacao) >= 0;

	}




}

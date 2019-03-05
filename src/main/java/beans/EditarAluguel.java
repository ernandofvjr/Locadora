package beans;



import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Aluguel;
import entity.Item;
import entity.SituacaoAluguel;
import entity.SituacaoItem;
import service.AluguelService;
import service.ItemService;
import service.ServiceLocadoraException;


@Named
@ViewScoped
public class EditarAluguel extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779155604704232174L;

	private Aluguel aluguel;
	

	
	public Aluguel getAluguel() {
		return aluguel;
	}


	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}


	@Inject
	private AluguelService aluguelService;
	
	@Inject
	private ItemService itemService;
	


	public String init() {
		
		
		if (aluguel == null) {
			reportarMensagemDeErro("Não foi escolhido um aluguel para ser editado");
			return "/paginas/protegido/funcionario/filtrar_aluguel?faces-redirect=true";
		} 
		
		return null;
	}
	

	public String salvar() {
		if(aluguel.getId() == null) {
			reportarMensagemDeErro("Não há nenhum aluguel sendo editado");
			return null;
		}
		
		
		try {
			if(aluguel.getSituacao() == SituacaoAluguel.ENTREGUE) {
				for(Item i: aluguel.getItens()) {
					i.setSituacao(SituacaoItem.DISPONIVEL);
					itemService.editar(i);
				}
			}
			
			aluguelService.editar(aluguel);

		} catch (ServiceLocadoraException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		
		reportarMensagemDeSucesso("Aluguel editado");

		return "/paginas/protegido/funcionario/filtrar_aluguel?faces-redirect=true";
	}
	
	public boolean isEntregue() {
		if(aluguel.getSituacao() != null)
			return aluguel.getSituacao() == SituacaoAluguel.ENTREGUE;
		
		return false;
	}
	
	



	

		



}

package beans;



import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Item;
import entity.SituacaoItem;
import service.ItemService;
import service.ServiceLocadoraException;




@Named
@ViewScoped
public class EditarItem extends AbstractBean{

	private static final long serialVersionUID = -7779155604704232174L;

    private Item item;
    
    @Inject
    private ItemService service;
    
    public void init() {
        if (item == null) {
            item = new Item();
        }
    }
    
    public boolean isEdicao() {
        return item != null && item.getId() != null;
    }
    
    public String salvar(){
        try {
            if(isEdicao()) {
                service.editar(item);
            }
            else {
            	item.setSituacao(SituacaoItem.DISPONIVEL);
                service.salvar(item);
            }
        }
        catch (ServiceLocadoraException e) {
            reportarMensagemDeErro(e.getMessage());
            return null;
        }
        reportarMensagemDeSucesso("O item '" + item.getNome() + "' foi salvo com sucesso!");
        
        return "/paginas/protegido/funcionario/admin/filtrar_item?faces-redirect=true";
    }
    
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

package entity;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


import service.ItemService;
import service.ServiceLocadoraException;




@FacesConverter(forClass = Item.class)
public class ItemConverter implements Converter{
	
	@Inject
	private ItemService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return service.buscarPorId(id);
		} 
		catch (ServiceLocadoraException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (!(value instanceof Item)) {
			return null;
		}
		
		Integer id = ((Item) value).getId();
		return (id != null) ? id.toString() : null;
	}

}

package service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.ItemDAO;
import dao.PersistenciaLocadoraException;
import entity.Item;
import entity.Usuario;
import filter.ItemFilter;
import util.TransacionalCdi;

@ApplicationScoped
public class ItemService implements Serializable {

	private static final long serialVersionUID = -7803325791425670859L;
	
	@Inject
	private ItemDAO itemDAO;
	
	@TransacionalCdi
	public void salvar(Item item) throws ServiceLocadoraException {
		try {
			validarCod(item);
			itemDAO.salvar(item);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void editar(Item item) throws ServiceLocadoraException {
		
		try {
			validarCod(item);
			itemDAO.editar(item);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void deletar(Item item) throws ServiceLocadoraException {
		try {
			itemDAO.deletar(item);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public Item buscarPorId(int id) throws ServiceLocadoraException {
		try {
			return itemDAO.buscarPorId(id);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Item> buscarTodos() throws ServiceLocadoraException {
		try {
			return itemDAO.buscarTodos();
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Item> findBy(ItemFilter filter) throws ServiceLocadoraException {
		try {
			return itemDAO.findBy(filter);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	
	public void validarCod(Item item) throws ServiceLocadoraException {
		boolean jahExiste = itemDAO.existeItemComCod(item);
		if (jahExiste) {
			throw new ServiceLocadoraException("Um item com esse código já existe: " + item.getCod()); 
		}
	}
}

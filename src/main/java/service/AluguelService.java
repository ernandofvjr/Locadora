package service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import dao.AluguelDAO;
import dao.ItemDAO;
import dao.PersistenciaLocadoraException;
import entity.Aluguel;
import entity.Item;
import entity.SituacaoItem;
import filter.AluguelFilter;
import util.TransacionalCdi;
@ApplicationScoped
public class AluguelService implements Serializable {

	private static final long serialVersionUID = -7803325791425670859L;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	
	@TransacionalCdi
	public void salvar(Aluguel aluguel) throws ServiceLocadoraException {
		try {
			aluguelDAO.salvar(aluguel);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void editar(Aluguel aluguel) throws ServiceLocadoraException {
		
		try {
			aluguelDAO.editar(aluguel);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	@TransacionalCdi
	public void deletar(Aluguel aluguel) throws ServiceLocadoraException {
		try {
			aluguelDAO.deletar(aluguel);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public Aluguel buscarPorId(int id) throws ServiceLocadoraException {
		try {
			return aluguelDAO.buscarPorId(id);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Aluguel> buscarTodos() throws ServiceLocadoraException {
		try {
			return aluguelDAO.buscarTodos();
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

	public List<Aluguel> findBy(AluguelFilter filter) throws ServiceLocadoraException {
		try {
			return aluguelDAO.findBy(filter);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}
	
	public List<Aluguel> buscarPorCPF(String cpf) throws ServiceLocadoraException {
		try {
			AluguelFilter filter = new AluguelFilter();
			filter.setCpfUsuario(cpf);
			return aluguelDAO.findBy(filter);
		} 
		catch (PersistenciaLocadoraException e) {
			throw new ServiceLocadoraException(e.getMessage(), e);
		}
	}

}

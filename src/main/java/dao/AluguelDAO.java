package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entity.Aluguel;
import filter.AluguelFilter;

public class AluguelDAO extends DAO{
	
	private static final long serialVersionUID = -5887950667069769139L;
	
	public void salvar(Aluguel obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar salvar o Aluguel.", pe);
		} 
	}
	
	
	public Aluguel editar(Aluguel obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		Aluguel resultado = obj;
		try {
			resultado = em.merge(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar atualizar o Aluguel.", pe);
		}
		return resultado;
	}
	
	
	public void deletar(Aluguel obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		try {
			obj = em.find(Aluguel.class, obj.getId());
			em.remove(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar remover o Aluguel.", pe);
		}
	}
	
	public Aluguel buscarPorId(int objId) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		Aluguel resultado = null;
		
		
		try {
			resultado = em.find(Aluguel.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar o item com base no ID.", pe);
		}

		return resultado;
	}
	
	
	public List<Aluguel> buscarTodos() throws PersistenciaLocadoraException {
		return findBy(null);
	}
	public List<Aluguel> findBy(AluguelFilter filter) throws PersistenciaLocadoraException {
		
		
		EntityManager em = getEntityManager();
		List<Aluguel> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT a FROM Aluguel a WHERE 1 = 1 ";
			
			if(filter != null) {
				
				if (notEmpty(filter.getPreco())) {
					jpql += "AND a.preco = :preco ";
				}
	

				if (notEmpty(filter.getCpfUsuario())) {
					jpql += "AND a.usuario.cpf = :cpf ";
				}
	

				if (notEmpty(filter.getDataAluguelMin())) {
					jpql += "AND a.dataAluguel >= :dataAluguelMin ";
				}
				

				if (notEmpty(filter.getDataAluguelMax())) {
					jpql += "AND a.dataAluguel <= :dataAluguelMax ";
				}
			
				
				if (notEmpty(filter.getSituacao())) {
					jpql += "AND a.situacao = :situacao ";
				}
				
			}
			
			TypedQuery<Aluguel> query = em.createQuery(jpql, Aluguel.class);
			
			if(filter != null) {
				
				if (notEmpty(filter.getPreco())) {
					query.setParameter("preco", filter.getPreco());
					
				}
	

				if (notEmpty(filter.getCpfUsuario())) {
					query.setParameter("cpf", filter.getCpfUsuario());
				}
	

				if (notEmpty(filter.getDataAluguelMin())) {
					query.setParameter("dataAluguelMin", filter.getDataAluguelMin());
				}
				

				if (notEmpty(filter.getDataAluguelMax())) {
					query.setParameter("dataAluguelMax", filter.getDataAluguelMax());
				}
			
				
				if (notEmpty(filter.getSituacao())) {
					query.setParameter("situacao",  filter.getSituacao());
				}
				
			}
			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar os itens.", pe);
		}
		return resultado;
	}
	
	
	private boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
}

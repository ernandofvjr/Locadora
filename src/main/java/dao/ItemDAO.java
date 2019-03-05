package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entity.Item;
import filter.ItemFilter;




public class ItemDAO extends DAO{
	
	private static final long serialVersionUID = -5887950667069769139L;
	

	public void salvar(Item obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar salvar o item.", pe);
		} 
	}
	
	
	public Item editar(Item obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		Item resultado = obj;
		try {
			resultado = em.merge(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar atualizar o item.", pe);
		}
		return resultado;
	}
	
	
	public void deletar(Item obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		try {
			obj = em.find(Item.class, obj.getId());
			em.remove(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar remover o item.", pe);
		}
	}
	
	public Item buscarPorId(int objId) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		Item resultado = null;
		
		
		try {
			resultado = em.find(Item.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar o item com base no ID.", pe);
		}

		return resultado;
	}
	
	
	public List<Item> buscarTodos() throws PersistenciaLocadoraException {
		return findBy(null);
	}
	public List<Item> findBy(ItemFilter filter) throws PersistenciaLocadoraException {
		
		
		EntityManager em = getEntityManager();
		List<Item> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT i FROM Item i WHERE 1 = 1 ";
			
			if(filter != null) {
			
				if (notEmpty(filter.getNome())) {
					jpql += "AND i.nome LIKE :nome ";
				}
				
				if (notEmpty(filter.getCod())) {
					jpql += "AND i.cod LIKE :cod ";
				}
	
				// Categoria
				if (notEmpty(filter.getCategoria())) {
					jpql += "AND i.categoria LIKE :categoria ";
				}
	
				// Data Lancamento min
				if (notEmpty(filter.getDataLancamentoMin())) {
					jpql += "AND i.dataLancamento >= :dataLancamentoMin ";
				}
				
				// Data Lancamento max
				if (notEmpty(filter.getDataLancamentoMax())) {
					jpql += "AND i.dataLancamento <= :dataLancamentoMax ";
				}
			
				// Tipo
				if (notEmpty(filter.getTipo())) {
					jpql += "AND i.tipo = :tipo ";
				}
				
				if (notEmpty(filter.getSituacao())) {
					jpql += "AND i.situacao = :situacao ";
				}
				
			}
			
			TypedQuery<Item> query = em.createQuery(jpql, Item.class);
			
			if(filter != null) {
				if (notEmpty(filter.getNome())) {
					query.setParameter("nome", "%" + filter.getNome() + "%");
				}
				
				if (notEmpty(filter.getCod())) {
					query.setParameter("cod", "%" + filter.getCod() + "%");
				}
	
				// Categoria
				if (notEmpty(filter.getCategoria())) {
					query.setParameter("categoria", "%" + filter.getCategoria() + "%");
				}
	
				// Data Lancamento min
				if (notEmpty(filter.getDataLancamentoMin())) {
					query.setParameter("dataLancamentoMin", filter.getDataLancamentoMin());
				}
				
				// Data Lancamento max
				if (notEmpty(filter.getDataLancamentoMax())) {
					query.setParameter("dataLancamentoMax", filter.getDataLancamentoMax());
				}
			
				// Tipo
				if (notEmpty(filter.getTipo())) {
					query.setParameter("tipo",  filter.getTipo() );
				}
				
				if (notEmpty(filter.getSituacao())) {
					query.setParameter("situacao",  filter.getSituacao() );
				}
			}
			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar os itens.", pe);
		}
		return resultado;
	}
	
	public boolean existeItemComCod(Item item) {
		
		if (item == null || !notEmpty(item.getCod())) {
			return false;
		}
		
		
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Item i WHERE i.cod = :cod ";
		if (item.getId() != null) {
			jpql += "AND i != :item ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("cod", item.getCod());
		if (item.getId() != null) {
			query.setParameter("item", item);
		}
		
		Long count = query.getSingleResult();
		return count > 0;
		
	}

	private boolean equals(Object obj1, Object obj2) {
		return obj1.equals(obj2);
	}

	private boolean assertDate(Date date, Date dateLimit, boolean atLeast) {
		if (date == null) {
			return true;
		}
		if (atLeast) {
			return date.compareTo(dateLimit) >= 0;
		} else {
			// atMost
			return date.compareTo(dateLimit) <= 0;
		}
	}

	private boolean contains(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null || s2 == null) {
			return false;
		}

		return s1.toUpperCase().contains(s2.toUpperCase());
	}
	
	private boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
}

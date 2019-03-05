package dao;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import entity.Usuario;

import filter.UsuarioFilter;




public class UsuarioDAO extends DAO{
	
	private static final long serialVersionUID = -5887950667069769139L;
	

	public void salvar(Usuario obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		try {
			em.persist(obj);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		} 
	}
	
	
	public Usuario editar(Usuario obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		Usuario resultado = obj;
		try {
			resultado = em.merge(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		}
		return resultado;
	}
	
	
	public void deletar(Usuario obj) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();

		try {
			obj = em.find(Usuario.class, obj.getId());
			em.remove(obj);

		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		}
	}
	
	public Usuario buscarPorId(int objId) throws PersistenciaLocadoraException {
		EntityManager em = getEntityManager();
		Usuario resultado = null;
		
		
		try {
			resultado = em.find(Usuario.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		}

		return resultado;
	}
	
	
	public List<Usuario> buscarTodos() throws PersistenciaLocadoraException {
		return findBy(null);
	}
	public List<Usuario> findBy(UsuarioFilter filter) throws PersistenciaLocadoraException {
		
		
		EntityManager em = getEntityManager();
		List<Usuario> resultado = new ArrayList<>();

		try {
			
			String jpql = "SELECT u FROM Usuario u WHERE 1 = 1 ";
			
			if(filter != null) {
			
				if (notEmpty(filter.getNome())) {
					jpql += "AND u.nome LIKE :nome ";
				}
				
				if (notEmpty(filter.getCpf())) {
					jpql += "AND u.cpf LIKE :cpf ";
				}
				
				if (notEmpty(filter.getLogin())) {
					jpql += "AND u.login = :login ";
				}
	
				if (notEmpty(filter.getDataNascMin())) {
					jpql += "AND u.dataNasc >= :dataNascMin ";
				}
				
				if (notEmpty(filter.getDataNascMax())) {
					jpql += "AND u.dataNasc <= :dataNascMax ";
				}
			
				if (notEmpty(filter.getGrupo())) {
					jpql += "AND u.grupo = :grupo ";
				}
			}
			
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			
			if(filter != null) {
				if (notEmpty(filter.getNome())) {
					query.setParameter("nome", "%" + filter.getNome() + "%");
				}
				
				if (notEmpty(filter.getCpf())) {
					query.setParameter("cpf", "%" + filter.getCpf() + "%");
				}
				
				if (notEmpty(filter.getLogin())) {
					query.setParameter("login", filter.getLogin());
				}


				if (notEmpty(filter.getDataNascMin())) {
					query.setParameter("dataNascMin", filter.getDataNascMin());
				}

				if (notEmpty(filter.getDataNascMax())) {
					query.setParameter("dataNascMax", filter.getDataNascMax());
				}
			
				if (notEmpty(filter.getGrupo())) {
					query.setParameter("grupo",  filter.getGrupo() );
				}
			}
			resultado = query.getResultList();
			
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaLocadoraException("Ocorreu algum erro ao tentar recuperar os usuários.", pe);
		}
		return resultado;
	}
	
	public boolean existeUsuarioComLogin(Usuario user) {
		if (user == null || !notEmpty(user.getLogin())) {
			return false;
		}

		
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Usuario u WHERE u.login = :login ";
		if (user.getId() != null) {
			jpql += "AND u != :user ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", user.getLogin());
		if (user.getId() != null) {
			query.setParameter("user", user);
		}
		
		Long count = query.getSingleResult();
		return count > 0;
		
	}
	
	public boolean existeUsuarioComCPF(Usuario user) {
		if (user == null || !notEmpty(user.getCpf())) {
			return false;
		}
		
		
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Usuario u WHERE u.cpf = :cpf ";
		if (user.getId() != null) {
			jpql += "AND u != :user ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("cpf", user.getCpf());
		if (user.getId() != null) {
			query.setParameter("user", user);
		}
		
		Long count = query.getSingleResult();
		return count > 0;
		
	}
	

	
	private boolean notEmpty(Object obj) {
		return obj != null;
	}
	
	private boolean notEmpty(String obj) {
		return obj != null && !obj.trim().isEmpty();
	}
}

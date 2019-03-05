package dao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public abstract class DAO implements Serializable {

	private static final long serialVersionUID = -6950592452242312456L;
	
	@Inject
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
}

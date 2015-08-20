package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Route;

@Repository
public class RouteDAOImpl implements RouteDAO{

	@Autowired
	private EntityManager entityManager;
	
	public RouteDAOImpl(){
		
	}
	
	public RouteDAOImpl(EntityManager em){
		this.entityManager = em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	@Transactional
	public Route get(int id) {
		return getEntityManager().find(Route.class, id);
	}

	@Override
	public List<Route> list() {
		String hql = "from Route";
		Query query = getEntityManager().createQuery(hql);
		List<Route> routes = query.getResultList();
		return routes;
	}

	@Override
	@Transactional
	public void add(Route r) {
		Route already = get(r.getId());
		
		if( already == null){
			getEntityManager().persist(r);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}

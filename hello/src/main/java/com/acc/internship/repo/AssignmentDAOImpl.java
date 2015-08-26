package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Assignment;

@Repository
public class AssignmentDAOImpl implements AssignmentDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	public AssignmentDAOImpl(){
		
	}
	
	public AssignmentDAOImpl(EntityManager em){
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
	public Assignment get(int id) {
		getEntityManager().find(Assignment.class, id);
		return null;
	}

	@Override
	public List<Assignment> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignment> listRouteForUser(int id) {
		String hql = "from Assignment where driver.id =?";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter(1, id);
		List<Assignment> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public void delete(int id) {
		String hql ="delete from Assignment where id = ?";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter(1, id);
		query.executeUpdate();
		
	}

	@Override
	@Transactional
	public void update(Assignment assigment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void add(Assignment assigment) {
		getEntityManager().persist(assigment);
	}
	
}

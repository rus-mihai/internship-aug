package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Assigment;

@Repository
public class AssigmentDAOImpl implements AssigmentDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	public AssigmentDAOImpl(){
		
	}
	
	public AssigmentDAOImpl(EntityManager em){
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
	public Assigment get(int id) {
		getEntityManager().find(Assigment.class, id);
		return null;
	}

	@Override
	public List<Assigment> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assigment> listRouteForUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void update(Assigment assigment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void add(Assigment assigment) {
		getEntityManager().persist(assigment);
	}
	
}

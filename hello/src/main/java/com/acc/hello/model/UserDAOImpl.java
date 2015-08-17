package com.acc.hello.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private EntityManager entityManager;
	
	public UserDAOImpl(){
		
	}
	
	public UserDAOImpl(EntityManager em){
		this.entityManager = em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	@Override
	public List<AbstractUser> list() {
		String sql = "from AbstractUser";
		Query query = getEntityManager().createQuery(sql);
		List<AbstractUser> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@Transactional
	public AbstractUser get(int id) {
		
		return getEntityManager().find(AbstractUser.class, id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(AbstractUser u) {
		entityManager.persist(u);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}

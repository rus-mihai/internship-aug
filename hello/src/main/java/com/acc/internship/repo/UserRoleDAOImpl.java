package com.acc.internship.repo;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acc.internship.model.UserRole;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO{
	@Autowired
	private EntityManager entityManager;
	
	public UserRoleDAOImpl(){
		
	}
	
	public UserRoleDAOImpl(EntityManager em){
		this.entityManager = em;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	public List<UserRole> list(){
		Query query =  getEntityManager().createQuery("from UserRole");
		List<UserRole> roles = query.getResultList();
		
		return roles;
	}
}

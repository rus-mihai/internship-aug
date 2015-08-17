package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.AbstractUser;


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
	public AbstractUser login(String username, String password){
		String hql = "from AbstractUser d where d.username=:username and d.password=:password";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		AbstractUser u = null;
		try{
			u = (AbstractUser)query.getSingleResult();	
		}catch(NoResultException e){
			System.out.println("does not exist");
		}
		
		return u;
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
	public void add(AbstractUser u) {
		String hql = "from AbstractUser a where a.username=:username";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("username", u.getUsername());
		List<AbstractUser> users = query.getResultList();
		
		if(users == null || users.size() == 0 ){
			entityManager.persist(u);
		}else{
			System.out.println("exista deja");
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}

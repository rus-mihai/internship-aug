package com.acc.internship.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.User;


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
	public User login(String username, String password){
		String hql = "from User d where d.username=:username and d.password=:password";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		User u = null;
		try{
			u = (User)query.getSingleResult();	
		}catch(NoResultException e){
			System.out.println("does not exist");
		}
		
		return u;
	}
	
	@Override
	public List<User> list() {
		String sql = "from AbstractUser";
		Query query = getEntityManager().createQuery(sql);
		List<User> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	@Transactional
	public User get(int id) {
		
		return getEntityManager().find(User.class, id);
	}

	@Override
	@Transactional
	public void add(User u) {
		String hql = "from AbstractUser a where a.username=:username";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("username", u.getUsername());
		List<User> users = query.getResultList();
		
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
	
	@Override
	public User findByUsername(String username){
		List<User> users = new ArrayList<User>();
		
		Query query = getEntityManager().createQuery("from User where username=?");
		
		users = query.getResultList();
		
		if(users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
		
	}
	
}

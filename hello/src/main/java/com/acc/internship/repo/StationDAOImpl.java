package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Route;
import com.acc.internship.model.Station;

@Repository
public class StationDAOImpl implements StationDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	public StationDAOImpl(){
		
	}
	
	public StationDAOImpl(EntityManager em){
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
	public Station get(int id) {
		return getEntityManager().find(Station.class, id);
	}

	@Override
	public List<Station> list() {
		String hql = "from Station";
		Query query = getEntityManager().createQuery(hql);
		List<Station> stations = query.getResultList();
		return stations;
	}

	@Override
	@Transactional
	public void add(Station r) {
		Station station = get(r.getId());
		if(station == null){
			getEntityManager().persist(r);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
	

}

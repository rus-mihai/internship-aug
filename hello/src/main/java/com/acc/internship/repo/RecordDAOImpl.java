package com.acc.internship.repo;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Record;
import com.acc.internship.model.Route;

@Repository
public class RecordDAOImpl implements RecordDAO {

	@Autowired
	private EntityManager entityManager;

	public RecordDAOImpl() {

	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Record getRecord(int id) {
		getEntityManager().find(Record.class, id);
		return null;
	}

	@Override
	public List<Record> list() {
		String hql = "from Record";
		Query query = getEntityManager().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Record> records = query.getResultList();
		return records;
	}

	@Override
	@Transactional
	public void add(Record record) {
		getEntityManager().persist(record);

	}
	
	public List<Integer> listTour(Route route, int forHou) {
		String hql = "select tour from Record where routerecord=? and hour(startTime)=?";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter(1, route);
		query.setParameter(2, forHou);
		@SuppressWarnings("unchecked")
		List<Integer> tour = query.getResultList();

		return tour;
	}

	public List<Integer> listReTour(Route route, int forHou) {
		String hql = "select retour from Record where routerecord=? and hour(startTime)=?";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter(1, route);
		query.setParameter(2, forHou);
		@SuppressWarnings("unchecked")
		List<Integer> retour = query.getResultList();

		return retour;
	}

	

}

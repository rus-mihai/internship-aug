package com.acc.internship.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Assignment;
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
		List<Record> records= query.getResultList();
		return records;
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void update(Record record) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void add(Record record) {
		getEntityManager().persist(record);

	}

	@Override
	public void getAverageForRouteByHour(int idRoute, int hour) {
		String hql = "select getAverageForRouteByHour(?,?)";
		Query query = getEntityManager().createNativeQuery(hql);
		
		query.setParameter(1, hour);
		query.setParameter(2, idRoute);
		Integer duration = (Integer)query.getSingleResult();
		//System.out.println(duration);
	}

}

package com.acc.internship.repo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.internship.model.Record;

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

	@Override
	public List<String> getReportByHourForRoute(int idRoute) {
		ArrayList<String> durations = new ArrayList<String>();
		String sql = "select sec_to_time(getAverageForRouteByHour(?,?))";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter(2, idRoute);
		for(int i = 0; i<24; i++){
			query.setParameter(1, i);
			Time s = (Time)query.getSingleResult();
			if(s != null){
				System.out.println(s.toString());
			}
			//durations.add(s.toString());
		}
		
	
		return durations;
	}
	
	

}

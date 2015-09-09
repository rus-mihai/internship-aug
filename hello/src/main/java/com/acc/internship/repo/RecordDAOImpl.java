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
	public void add(Record record) {
		getEntityManager().persist(record);

	}


	@Override
	public List<Time> getReportTourByHourForRoute(int idRoute) {
		ArrayList<Time> durations = new ArrayList<Time>();
		String sql = "select sec_to_time(getAverageTourForRouteByHour(?,?))";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter(2, idRoute);
		for(int i = 0; i<24; i++){
			query.setParameter(1, i);
			Time s = (Time)query.getSingleResult();
			if(s != null){
				durations.add(s);
			}else{
				durations.add(new Time(0));
			}
		}
		return durations;
	}

	@Override
	public List<Time> getReportRetourByHourForRoute(int idRoute) {
		ArrayList<Time> durations = new ArrayList<Time>();
		String sql = "select sec_to_time(getAverageRetourForRouteByHour(?,?))";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter(2, idRoute);
		for(int i = 0; i<24; i++){
			query.setParameter(1, i);
			Time s = (Time)query.getSingleResult();
			if(s != null){
				durations.add(s);
			}else{
				durations.add(new Time(0));
			}
		}
		return durations;
	}
}

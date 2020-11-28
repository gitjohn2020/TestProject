package com.grossery.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grossery.model.UomMaster;

@Repository
public class UomDao {

	private static final Logger logger = Logger.getLogger(UomDao.class);
	@Autowired
	private SessionFactory factory;

	public boolean addUom(UomMaster uom) {
		Session session = factory.getCurrentSession();

		try {
			session.save(uom);
			return true;
		} catch (HibernateException e) {
			logger.error("error in dao", e);
			return false;
		}
	}
	
	public List<UomMaster> getAllUom(){
		
		Session session=factory.getCurrentSession();
		String hql="from UomMaster where active='Y'";
		logger.info("hql is:---"+hql);
		List<UomMaster> uomlist=null;
		try{
		Query q=session.createQuery(hql);
		uomlist=q.list();
		}catch (HibernateException e) {
			logger.error("error in dao", e);
			
		}
		return uomlist;
	}

}

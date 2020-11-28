package com.grossery.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grossery.model.HistoryMaster;
import com.grossery.model.LoginMaster;
import com.grossery.service.MailService;

@Repository
public class HistoryDao {
	private static final Logger logger = Logger.getLogger(HistoryDao.class);
	@Autowired
	private SessionFactory factory;
	
	public String addHistory( HistoryMaster history) throws Exception{
		
		Session session=factory.getCurrentSession();
		session.save(history);
		
		return "success";
	}
	
	public List<HistoryMaster> getHistoryDetails(String fromdate,String todate){
		
		Session session=factory.getCurrentSession();
		String hql="from HistoryMaster where sent_date between '"+fromdate+"'"+" and '"+todate+"'";
		logger.info("hql is:---"+hql);
		Query q=session.createQuery(hql);
		List<HistoryMaster> lst=null;
		lst=q.list();
		logger.info("history size:-"+lst.size());
		return lst;
		
	}

}

package com.grossery.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grossery.model.LoginMaster;

@Repository
public class LoginDao {
	private static final Logger logger = Logger.getLogger(LoginDao.class);
	@Autowired
	private SessionFactory factory;
	
	public String login( LoginMaster user) throws Exception{
		
		Session session=factory.getCurrentSession();
		String x="";
		String hql="from LoginMaster where uname='"+user.getUname()+"' and password='"+user.getPassword()+"' and flag='Y'";
		System.out.println("hql is:---"+hql);
		Query q=session.createQuery(hql);
		List<LoginMaster>lst=null;
		lst=q.list();
		System.out.println("list size:---"+lst.size());
		if(lst.size()>0){
			x="success";
		}else{
			x="failed";
		}
		
		return x;
	}
	
	public LoginMaster getUserDetails(String username){
		
		Session session=factory.getCurrentSession();
		String hql="from LoginMaster where uname='"+username+"' and flag='Y'";
		System.out.println("hql is:---"+hql);
		Query q=session.createQuery(hql);
		LoginMaster user=null;
		user=(LoginMaster) q.list().get(0);
		return user;
		
	}
	
	public boolean registerUser( LoginMaster user) throws Exception{
		Session session=factory.getCurrentSession();
		
		try{
		session.save(user);
		return true;
		}catch(HibernateException e){
			logger.error("error in dao", e);
		return false;	
		}
		
	}

}

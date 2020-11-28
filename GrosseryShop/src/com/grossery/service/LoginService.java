package com.grossery.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grossery.dao.LoginDao;
import com.grossery.model.LoginMaster;

@Service
@Transactional
public class LoginService {
	@Autowired
	public LoginDao dao;
	
	public String login(LoginMaster user){
		String status="";
		try {
			status= dao.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public LoginMaster getUserDetails(String username){
		
		return dao.getUserDetails(username);
		
	}
	
	public boolean registerUser(LoginMaster user) throws Exception{
		return dao.registerUser(user);
	}

}

package com.grossery.config;

import java.util.ArrayList;
import java.util.List;









import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grossery.model.LoginMaster;
import com.grossery.service.LoginService;



@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	//static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	@Autowired
	private LoginService userService;
	
	

	private List<GrantedAuthority> getGrantedAuthorities(LoginMaster user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		
			
			authorities.add(new SimpleGrantedAuthority(user.getUser_type()));
		
		return authorities;
	}



	@Override
	public UserDetails loadUserByUsername(String uname)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("userid to login------"+uname);
		LoginMaster user = userService.getUserDetails(uname);
		System.out.println("user is --"+user);
		if(user==null){
			logger.info("Username "+uname+" not found in database");
			throw new UsernameNotFoundException("Username not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getUname(), user.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(user));
	}
	
	
}

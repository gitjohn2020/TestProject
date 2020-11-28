package com.grossery.config;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.grossery.model.LoginMaster;
import com.grossery.service.LoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = Logger
			.getLogger(WebSecurityConfig.class);
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	@Autowired
	private LoginService userService;

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	//
	// http
	// .authorizeRequests()
	// .anyRequest().hasRole("ADMIN")
	// .and().formLogin().and()
	// .httpBasic()
	// .and()
	// .logout()
	// .logoutUrl("/j_spring_security_logout")
	// .logoutSuccessUrl("/")
	// ;
	// }
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		// authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/js**", "/login").permitAll()
				.antMatchers("/home", "/", "/sms", "/userhome","/uom", "/adminhome").authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.loginProcessingUrl("/login").usernameParameter("uname")
				.passwordParameter("password")
				.successHandler(this::loginSuccessHandler)
				.failureHandler(this::loginFailureHandler).and()
				.exceptionHandling().accessDeniedPage("/Access_Denied").and()
				.csrf().disable();
	}

	private void loginSuccessHandler(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		HttpSession session = request.getSession();
		String username = user.getUsername();
		LoginMaster loggedinuser = userService.getUserDetails(username);
		session.setAttribute("loggedinuser", loggedinuser);
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication
				.getAuthorities());
		logger.info("Yayy you logged in!----" + roles.toString());
		if (roles.contains("A")) {
			response.setStatus(HttpStatus.OK.value());
			response.sendRedirect("adminhome");
		}
		if (roles.contains("U")) {
			response.setStatus(HttpStatus.OK.value());
			response.sendRedirect("userhome");
		}

		// objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
	}

	private void loginFailureHandler(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException e)
			throws IOException {
		logger.info("login failed");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.sendRedirect("login?error");
		// objectMapper.writeValue(response.getWriter(), "Nopity nop!");
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}

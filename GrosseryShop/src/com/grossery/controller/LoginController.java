package com.grossery.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grossery.model.LoginMaster;
import com.grossery.service.LoginService;

@RestController
public class LoginController {
	private static final Logger logger = Logger
			.getLogger(LoginController.class);
	@Autowired
	private LoginService service;
	@Autowired
	@Lazy
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = "/loginx", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	@ResponseBody
	public String Login(@RequestBody LoginMaster user,
			HttpServletRequest request) {
		String statusString = "";
		// if (isCurrentAuthenticationAnonymous()) {
		// return "login";
		// }else{
		statusString = service.login(user);

		logger.info(statusString);
		return statusString;
		// }
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	@ResponseBody
	public boolean registerUser(@RequestBody LoginMaster user,
			HttpServletRequest request) throws Exception {
		String statusString = "";
		logger.info("registering user......");
		user.setUname(user.getPhone());
		user.setUser_type("U");
		user.setCreated_by("own");
		user.setCreated_on(new Date());
		user.setFlag("Y");
		boolean success = service.registerUser(user);
		// if (isCurrentAuthenticationAnonymous()) {
		// return "login";
		// }else{
		// statusString = service.login(user);

		logger.info(success);
		return success;
		// }
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {

			return "login";
		} else {

			return "redirect:/home";
		}
	}

	@RequestMapping(value = "/uom", method = RequestMethod.GET)
	public String sms() {

		return "/admin/uomMaster";
	}

	@RequestMapping(value = "/userhome", method = RequestMethod.GET)
	public String index() {

		return "/user/home";
	}

	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public String adminindex() {

		return "/admin/adminhome";
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String registerUser() {

		return "register";
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history() {

		return "history";
	}

	// @RequestMapping(value ="/", method = RequestMethod.GET)
	// public String login() {
	// System.out.println("i m hereeeeeeeeeeeeeeeeeeee");
	// return "login";
	// }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			// persistentTokenBasedRememberMeServices.logout(request, response,
			// auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}

		return "redirect:/login?error";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage() {
		// model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

}

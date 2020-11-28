package com.grossery.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grossery.model.HistoryMaster;
import com.grossery.model.LoginMaster;
import com.grossery.model.UomMaster;
import com.grossery.service.UomService;

@RestController
public class UomController {
	@Autowired
	UomService service;
	@RequestMapping(value = "/addUom", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	@ResponseBody
	public boolean addUom(@RequestBody UomMaster uom,HttpServletRequest request){
		uom.setActive("Y");
		LoginMaster user=(LoginMaster) request.getSession().getAttribute("loggedinuser");
		uom.setCreated_by(user.getUname());
		uom.setCreated_on(new Date());
		return service.addUom(uom);
	}
	
	@RequestMapping(value = "/getAllUom", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<List<UomMaster>> getUom(HttpServletRequest request){
		List<UomMaster> uoms=service.getAllUom();
		return new ResponseEntity<List<UomMaster>>(uoms, HttpStatus.OK);
	}

}

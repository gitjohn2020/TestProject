package com.grossery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grossery.model.HistoryMaster;
import com.grossery.model.MailContent;
import com.grossery.service.HistoryService;

@RestController
public class HistoryController {
	
	@Autowired
	HistoryService service;
	@RequestMapping(value = "/getHistory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<HistoryMaster>> getHistory(@RequestParam(value = "fromdate", required = true)String fromdate,@RequestParam(value="todate") String todate,HttpServletRequest request) throws IOException{
		
		List<HistoryMaster> historydetails=service.getHistoryDetails(fromdate, todate);
		return new ResponseEntity<List<HistoryMaster>>(historydetails, HttpStatus.OK);
	}
	

}

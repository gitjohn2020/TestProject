package com.grossery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grossery.dao.HistoryDao;
import com.grossery.model.HistoryMaster;

@Service
@Transactional
public class HistoryService {
	@Autowired
	HistoryDao historydao;
	
	public List<HistoryMaster> getHistoryDetails(String fromdate,String todate){
		return historydao.getHistoryDetails(fromdate, todate);
	}
	

}

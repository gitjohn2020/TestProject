package com.grossery.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grossery.dao.UomDao;
import com.grossery.model.UomMaster;

@Service
@Transactional
public class UomService {
	@Autowired
	UomDao uomdao;
	
	public boolean addUom(UomMaster uom) {
	return uomdao.addUom(uom);
	}
	
	public List<UomMaster> getAllUom(){
		return uomdao.getAllUom();
	}

}

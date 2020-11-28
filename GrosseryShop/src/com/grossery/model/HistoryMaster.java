package com.grossery.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="trn_history")
public class HistoryMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String source_file;
	private Date sent_date;
	private int total_success;
	private int total_failed;
	private String error_file;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource_file() {
		return source_file;
	}
	public void setSource_file(String source_file) {
		this.source_file = source_file;
	}
	public String getSent_date() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate= formatter.format(sent_date);
		return strDate;
	}
	public void setSent_date(Date sent_date) {
		this.sent_date = sent_date;
	}
	public int getTotal_success() {
		return total_success;
	}
	public void setTotal_success(int total_success) {
		this.total_success = total_success;
	}
	public int getTotal_failed() {
		return total_failed;
	}
	public void setTotal_failed(int total_failed) {
		this.total_failed = total_failed;
	}
	public String getError_file() {
		return error_file;
	}
	public void setError_file(String error_file) {
		this.error_file = error_file;
	}
	
	

}

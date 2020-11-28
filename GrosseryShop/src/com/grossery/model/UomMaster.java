package com.grossery.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_uom")
public class UomMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String uom_id;
	private String uom_nm;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String active;
	public String getUom_id() {
		return uom_id;
	}
	public void setUom_id(String uom_id) {
		this.uom_id = uom_id;
	}
	public String getUom_nm() {
		return uom_nm;
	}
	public void setUom_nm(String uom_nm) {
		this.uom_nm = uom_nm;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}

package com.yeild.ssh.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997080310560457943L;
	
	@Id
	@GeneratedValue
	private int roleid;
	private int roletypecode;
	private String rolename;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getRoletypecode() {
		return roletypecode;
	}
	public void setRoletypecode(int roletypecode) {
		this.roletypecode = roletypecode;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}

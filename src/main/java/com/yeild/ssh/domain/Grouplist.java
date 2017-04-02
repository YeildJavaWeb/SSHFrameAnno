package com.yeild.ssh.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Grouplist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2658764049468788674L;
	
	@Id
	@GeneratedValue
	private int groupid;
	private int grouptypecode;
	@Size(min=1)
	private String groupname;
	
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getGrouptypecode() {
		return grouptypecode;
	}
	public void setGrouptypecode(int grouptypecode) {
		this.grouptypecode = grouptypecode;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}

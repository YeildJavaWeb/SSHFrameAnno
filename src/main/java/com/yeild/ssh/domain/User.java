package com.yeild.ssh.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -2380729882115551971L;

	@Id
	@GeneratedValue
	private int userid;
	private String username;
	private String password;
	private int actived;
	@ManyToOne
	private Role role;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Size(min=6,max=15)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Size(min=6,max=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActived() {
		return actived;
	}
	public void setActived(int actived) {
		this.actived = actived;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}

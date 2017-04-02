package com.yeild.ssh.domain.dao;

import java.util.List;

import com.yeild.ssh.domain.User;

public interface UserDAO {
	public User getUserByName(String username);
	public int save(User user);
	public List<User> findAll();
}

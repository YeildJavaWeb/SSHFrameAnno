package com.yeild.ssh.service;

import java.util.List;

import com.yeild.ssh.domain.User;

public interface UserService {
	public User getUserByName(String username);
	public List<User> getAllUserNames();
	public void saveUsers(List<User> users);
	public int saveUser(User user);
}

package com.yeild.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeild.ssh.domain.User;
import com.yeild.ssh.domain.dao.UserDAO;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDao;
	
	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}
	
	@Override
	public List<User> getAllUserNames() {
		return userDao.findAll();
	}
	
	@Override
	public int saveUser(User user) {
		return userDao.save(user);
	}
	
	@Override
	public void saveUsers(List<User> users) {
		for(User user:users) {
			saveUser(user);
		}
	}
}

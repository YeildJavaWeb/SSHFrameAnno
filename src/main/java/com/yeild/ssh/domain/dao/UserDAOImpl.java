package com.yeild.ssh.domain.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeild.ssh.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where username=:username";
		Query<User> query = session.createQuery(hql, User.class).setParameter("username", username);
		List<User> users = query.getResultList();
		if(users.size()<1) {
			return null;
		}
		return users.get(0);
	}
	
	public int save(User user) {
		Session session = sessionFactory.getCurrentSession();
		Integer result = -1;
		result = (Integer) session.save(user);
		return result;
	}
	
	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User";
		Query<User> query = session.createQuery(hql, User.class);
		//分页查询
		query.setFirstResult(0).setMaxResults(10);
		return query.getResultList();
	}
}

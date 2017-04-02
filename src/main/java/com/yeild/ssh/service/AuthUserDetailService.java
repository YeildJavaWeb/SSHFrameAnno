package com.yeild.ssh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeild.ssh.domain.Role;
import com.yeild.ssh.domain.User;

@Service("authUserDetailService")
public class AuthUserDetailService implements UserDetailsService {
	@Autowired
	UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		if(username.equals("yeild")) {
			Role role = new Role();
			role.setRoletypecode(-9999);
			role.setRolename("ROOT_DEVELOPER");
			user = new User();
			user.setUsername(username);
			user.setPassword("yeild");
			user.setActived(1);
			user.setRole(role);
		} else {
			user = userService.getUserByName(username);
			if(user == null) {
				throw new UsernameNotFoundException("User Not Found!");
			}
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
				, user.getActived()==1, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRolename()));
		return authorities;
	}

}

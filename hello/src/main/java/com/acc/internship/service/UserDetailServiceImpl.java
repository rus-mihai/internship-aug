package com.acc.internship.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acc.internship.model.User;
import com.acc.internship.model.UserRole;
import com.acc.internship.repo.UserDAO;


@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(username);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		
		return buildUserForAuthentication(user, authorities);
		
	}
	
	private List<GrantedAuthority> buildUserAuthority(UserRole role){
		GrantedAuthority setAuths = new SimpleGrantedAuthority(role.getRole());
		
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		result.add(setAuths);
		
		return result;
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities){
		
		org.springframework.security.core.userdetails.User result = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		
		return result;
	}
	
	public UserDAO getUserDao(){
		return this.userDao;
	}
	
	public void setUserDao(UserDAO dao){
		this.userDao = dao;
	}

}

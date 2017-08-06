package com.qf.service;

import com.qf.domain.User;

public interface UserService {

	public boolean findByName(String username);
	
	public User findByNameAndPwd(String username,String password);
	
	public void save(User user);
	
	public void updateUser(User user);
	
	public User findById(int uid);
	
}

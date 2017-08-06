package com.qf.mapper;

import java.util.List;

import com.qf.domain.User;

public interface UserMapper {

	void save(User user);
	
	void deleteById(int uid);
	
	User findById(int uid);
	
	User findByName(String username);
	
	User findByNameAndPwd(String username,String password);
	
	List<User> findAll();
	
	void update(User user);
}

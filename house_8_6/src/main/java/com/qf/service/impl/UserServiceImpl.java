package com.qf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.domain.User;
import com.qf.mapper.UserMapper;
import com.qf.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public boolean findByName(String username) {
		
		return userMapper.findByName(username)!=null?true:false;
	}

	@Override
	public User findByNameAndPwd(String username, String password) {
		
		return userMapper.findByNameAndPwd(username, password);
	}

	@Override
	public void save(User user) {
		userMapper.save(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.update(user);

	}

	@Override
	public User findById(int uid) {
		
		return userMapper.findById(uid);
	}

}

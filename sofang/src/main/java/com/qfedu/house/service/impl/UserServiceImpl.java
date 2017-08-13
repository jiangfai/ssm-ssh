package com.qfedu.house.service.impl;


import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qfedu.house.domain.LoginLog;
import com.qfedu.house.domain.User;
import com.qfedu.house.dto.UserDTO;
import com.qfedu.house.persistence.LoginLogDAO;
import com.qfedu.house.persistence.UserDAO;
import com.qfedu.house.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private LoginLogDAO loginLogDAO;
	@Override
	public boolean login(UserDTO userDTO) {
		User user=userDTO.getUser();
		User u = userDAO.findByUserName(user.getUsername());
		if(u!=null){
			String pwd=DigestUtils.md5Hex(user.getPassword());
			boolean flag=pwd.equals(u.getPassword());	
			if(flag){
				user.setId(u.getId());
				user.setRealname(u.getRealname());
				user.setTel(u.getTel());
				LoginLog log=new LoginLog();
				log.setIpAddress(userDTO.getCode());
				log.setLogDate(new Date());
				log.setUser(user);
				loginLogDAO.save(log);
			}
			return flag;
		}
		
		return false;
	}

	@Override
	public boolean register(User user) {
		if(userDAO.findByUserName(user.getUsername())==null){
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			return userDAO.save(user)!=null;
		}
		return false;
	}

}

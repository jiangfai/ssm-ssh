package com.qfedu.house.service;

import com.qfedu.house.domain.User;

public interface UserService {

	boolean login(User user,String ipAddress);
	
	boolean register(User user);
}

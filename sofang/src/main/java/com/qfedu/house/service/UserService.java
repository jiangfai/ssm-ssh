package com.qfedu.house.service;

import com.qfedu.house.domain.User;
import com.qfedu.house.dto.UserDTO;

public interface UserService {

	boolean login(UserDTO userDTO);
	
	boolean register(User user);
}

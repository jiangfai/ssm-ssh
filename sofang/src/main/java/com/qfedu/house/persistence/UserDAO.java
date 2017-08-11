package com.qfedu.house.persistence;

import com.qfedu.house.domain.User;

public interface UserDAO extends BaseDAO<User, Integer>{

	User findByUserName(String username);
}

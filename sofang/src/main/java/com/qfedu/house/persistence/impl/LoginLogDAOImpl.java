package com.qfedu.house.persistence.impl;

import org.springframework.stereotype.Repository;

import com.qfedu.house.domain.LoginLog;
import com.qfedu.house.persistence.BaseDaoHibernateAdapter;
import com.qfedu.house.persistence.LoginLogDAO;
@Repository
public class LoginLogDAOImpl 
extends BaseDaoHibernateAdapter<LoginLog, Integer> 
implements LoginLogDAO{

}

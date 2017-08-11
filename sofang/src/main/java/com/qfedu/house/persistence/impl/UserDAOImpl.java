package com.qfedu.house.persistence.impl;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.qfedu.house.domain.User;
import com.qfedu.house.persistence.BaseDaoHibernateAdapter;
import com.qfedu.house.persistence.UserDAO;
@Repository
public class UserDAOImpl extends BaseDaoHibernateAdapter<User, Integer> implements UserDAO {

	

	@Override
	public User findByUserName(String username) {
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User as u where u.username=:uname",User.class)
		.setParameter("uname", username).getResultList();
		return list.size()==1 ?list.get(0):null;
	}

}

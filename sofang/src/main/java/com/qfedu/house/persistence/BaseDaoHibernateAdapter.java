package com.qfedu.house.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDaoHibernateAdapter<E, K extends Serializable>
		implements BaseDAO<E, K> {
	@Autowired
	protected SessionFactory sessionFactory;
	
	private Class<E> entityType;
	private String entityName;
	
	public BaseDaoHibernateAdapter() {
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityType = (Class<E>) pType.getActualTypeArguments()[0];
		entityName = entityType.getSimpleName();
	}
	
	@Override
	public K save(E entity) {
		return (K) sessionFactory.getCurrentSession().save(entity);
	}
	
	@Override
	public boolean deleteById(K id) {
		return sessionFactory.getCurrentSession()
				.createQuery("delete from " + entityName + " as o where o.id=:id")
				.setParameter("id", id)
				.executeUpdate() == 1;
	}

	@Override
	public void delete(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public void upate(E entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	public E findById(K id) {
		return sessionFactory.getCurrentSession().get(entityType, id);
	}

	@Override
	public List<E> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("from " + entityName, entityType)
				.getResultList();
	}
	
}

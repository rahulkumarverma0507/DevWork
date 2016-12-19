package org.coolstory.nas.dao;

import java.io.Serializable;
import java.util.List;

import org.coolstory.nas.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	
	 protected Class<? extends E> daoType;
     
	    /**
	    * By defining this class as abstract, we prevent Spring from creating 
	    * instance of this class If not defined as abstract, 
	    * getClass().getGenericSuperClass() would return Object. There would be 
	    * exception because Object class does not hava constructor with parameters.
	    */
	 public GenericDaoImpl() {
	        Type t = getClass().getGenericSuperclass();
	        ParameterizedType pt = (ParameterizedType) t;
	        daoType = (Class) pt.getActualTypeArguments()[0];
	    }
	     
	    protected Session currentSession() {
	    	return HibernateUtil.INSTANCE.getSession();
	    }
	     
	    @Override
	    public void add(E entity) {
	        currentSession().save(entity);
	    }
	     
	    @Override
	    public void saveOrUpdate(E entity) {
	        currentSession().saveOrUpdate(entity);
	    }
	     
	    @Override
	    public void update(E entity) {
	        currentSession().saveOrUpdate(entity);
	    }
	     
	    @Override
	    public void remove(E entity) {
	        currentSession().delete(entity);
	    }
	     
	    @Override
	    public E find(K key) {
	        return (E) currentSession().get(daoType, key);
	    }
	     
	    @Override
	    public List<E> getAll() {
	        return currentSession().createCriteria(daoType).list();
	    }
}

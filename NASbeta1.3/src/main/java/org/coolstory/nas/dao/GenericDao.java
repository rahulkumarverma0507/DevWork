package org.coolstory.nas.dao;

import java.util.List;

//GenericDao interface contains common methods used by multiple dao classes
public interface GenericDao<E,K> {

	public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll() ;

}

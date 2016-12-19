package org.coolstory.nas.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public enum HibernateUtil {
	
	INSTANCE;
	
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;
	
	private synchronized SessionFactory initializeSession(){
		if (sessionFactory == null){
			try {
				// load from different directory
			Configuration configuration = new Configuration();
			configuration.configure("/hibernate.cfg.xml");
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    
				return sessionFactory;
	 
			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			
			
			
		}
		return sessionFactory;
		
	}
	
	public Session getSession() {
	    Session hibernateSession = null;

	    if (sessionFactory == null) {
	        hibernateSession = initializeSession().openSession();
	    } else {
	        hibernateSession = sessionFactory.openSession();
	           }
	    return hibernateSession;
	}

}

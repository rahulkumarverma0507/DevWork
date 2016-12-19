package org.coolstory.nas.testenv;

import org.coolstory.nas.domain.Professional;
import org.coolstory.nas.utils.HibernateUtil;
import org.hibernate.Session;

public class TestingSpringHibernateBonecp {
	
	public static void method1(){
		
		Session session = HibernateUtil.INSTANCE.getSession();	
		
		System.out.println("got the session");
		session.beginTransaction();
		System.out.println("Tx begins");
		Professional professional = new Professional();
		professional.setFirstName("first");
		professional.setLastName("last");
		session.save(professional);
		System.out.println("data inserted!");
		session.getTransaction().commit();
		
		session.flush();
		session.close();
	}
	
	public static void main(String[] args) {
//		HibernateUtil hibernateUtil = null;	
		method1();
	
	}
}

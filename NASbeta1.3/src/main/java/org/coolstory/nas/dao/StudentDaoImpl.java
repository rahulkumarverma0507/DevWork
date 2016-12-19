package org.coolstory.nas.dao;

import java.util.ArrayList;
import java.util.List;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.SportBean;
import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.domain.Category;
import org.coolstory.nas.domain.Sport;
import org.coolstory.nas.domain.Student;
import org.coolstory.nas.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends GenericDaoImpl<Student, Long> implements StudentDao {

	//This method will create student and add sport category 
	public MessageBean addSportCategory(StudentBean studentBean){
	
		String sportsCategory = null;
		MessageBean message = new MessageBean();
		Session session = HibernateUtil.INSTANCE.getSession();	
		
		try{
		session.beginTransaction();
		
		Student student = new Student();
		Category categoryOne = new Category();
		
		//if student doesn't exist , create new student and add category	
		student = (Student)session.get(Student.class, studentBean.getId());
		
		if(student == null){
			
			Student newStudent = new Student();
			newStudent.setId(studentBean.getId());
			newStudent.setName(studentBean.getName());
			newStudent.setClasses(studentBean.getClasses());
			newStudent.setSection(studentBean.getSection());
			
			categoryOne.setCategoryName(studentBean.getCategoryName());
			newStudent.setCategory(categoryOne);
			
			session.saveOrUpdate(newStudent);
			session.getTransaction().commit();
			message.setMessageDescription("New Student has been added and sport category "+ studentBean.getCategoryName() +" has been assigned."  );
			
		}else{
			//If student exists then check if sport category is already assigned , if not assign new sport category else pass proper message. 
			sportsCategory = student.getCategory().getCategoryName();
			if(sportsCategory !=null && !sportsCategory.equals("")){
				
				message.setMessageDescription("Sport category "+ sportsCategory.toUpperCase() +" already exists for the student");
				return message;
				
			}else{
				//add category to the existing student
				categoryOne.setCategoryName(studentBean.getCategoryName());
				student.setCategory(categoryOne);
				
				session.saveOrUpdate(student);
				session.getTransaction().commit();
				message.setMessageDescription(studentBean.getCategoryName() +" has been assigned to the student."  );
			}
		 }
		}catch(Exception e){
			message.setMessageDescription("Exception caught::"+e.getStackTrace());
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return message;
	}
	
	//This method will fetch sports data as per id passed
	public StudentBean getStudentsDataById(Long id){
	
		StudentBean studentBean = null;
		
		try{
			
			Session session = HibernateUtil.INSTANCE.getSession();	
			session.beginTransaction();
			
			studentBean = new StudentBean();
			Student student = new Student();
			student = (Student)session.get(Student.class, id);
			
			//Fetch data if student id is not null
			if(student!=null){
			
			studentBean.setId(student.getId());
			studentBean.setName(student.getName());
			studentBean.setClasses(student.getClasses());
			studentBean.setSection(student.getSection());
			
			studentBean.setCategoryId(student.getCategory().getCategoryId());
			studentBean.setCategoryName(student.getCategory().getCategoryName());
			
			studentBean.setSportList(student.getCategory().getSportList());
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return studentBean;
	}
	
	//This method will add sports for the category assigned to the student
	public boolean addSports(Long id, List<SportBean> sportBean){
		
		boolean addSportsFlag = true;
		
		try{
			
			Session session = HibernateUtil.INSTANCE.getSession();	
			session.beginTransaction();
			
			Student student = new Student();
			student = (Student)session.get(Student.class, id);
			
			List<Sport> sports = new ArrayList<Sport>();
			
			for(SportBean a: sportBean){
				
				Sport sport = new Sport();
				sport.setSportName(a.getSportName());
				sports.add(sport);
			}
			
			
			if(Long.valueOf(student.getId()) != null && student.getId() == id){
				
				//Add to list of sports for the student.
				student.getCategory().setSportList(sports);;
			}
			
			session.saveOrUpdate(student);
			session.getTransaction().commit();
			session.close();
			
		}catch(Exception e){
			
			addSportsFlag = false;
			e.printStackTrace();
		}
		
		return addSportsFlag;
	}
}
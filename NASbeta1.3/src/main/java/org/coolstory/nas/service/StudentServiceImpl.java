package org.coolstory.nas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.SportBean;
import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.dao.GenericDao;
import org.coolstory.nas.dao.StudentDao;
import org.coolstory.nas.domain.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends GenericServiceImpl<Student, Long> implements StudentService{

	private StudentDao studentDao;
	
	public StudentServiceImpl(){
		
	}
	
	/*we allow sub entity service classes to decide which generic dao object will be injected by using @Qualifier annotation. 
      */
	@Autowired
    public StudentServiceImpl(
            @Qualifier("studentDaoImpl") GenericDao<Student, Long> genericDao) {
        super(genericDao);
        this.studentDao = (StudentDao) genericDao;
    }
	
	@Transactional
	public MessageBean addSportCategory(StudentBean studentBean){
		
		return studentDao.addSportCategory(studentBean);
	}
	
	@Transactional
	public StudentBean getStudentsDataById(Long id){
		
		return studentDao.getStudentsDataById(id);
	}
	
	@Transactional
	public boolean addSports(Long id, List<SportBean> sportBean){
		
		return studentDao.addSports(id, sportBean);
	}
}

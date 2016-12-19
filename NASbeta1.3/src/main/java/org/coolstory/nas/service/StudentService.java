package org.coolstory.nas.service;

import java.util.List;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.SportBean;
import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.domain.Student;

public interface StudentService extends GenericService<Student,Long>{

	MessageBean addSportCategory(StudentBean studentBean);
	StudentBean getStudentsDataById(Long id);
	boolean addSports(Long id, List<SportBean> sportBean);
}

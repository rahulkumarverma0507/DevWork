package org.coolstory.nas.dao;

import java.util.List;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.SportBean;
import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.domain.Student;

public interface StudentDao extends GenericDao<Student, Long> {

	MessageBean addSportCategory(StudentBean studentBean);
	StudentBean getStudentsDataById(Long id);
	boolean addSports(Long id, List<SportBean> sportBean);
}
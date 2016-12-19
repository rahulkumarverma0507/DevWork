package org.coolstory.nas;


import java.util.List;

import org.coolstory.nas.bean.CategoryBean;
import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.SportBean;
import org.coolstory.nas.bean.StudentBean;
import org.coolstory.nas.constants.StudentConstants;
import org.coolstory.nas.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {	

	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	//This will autowire sport category using @Qualifier annotation
	
	@Autowired
	@Qualifier("Athletic")
	private CategoryBean athleticCategory;
	
	@Autowired
	@Qualifier("Electronic")
	private CategoryBean electronicCategory;
	
	@Autowired
	@Qualifier("Water")
	private CategoryBean waterCategory;
	
	
	
	/**
	 * This method will fetch student data, sport's category and sports list for the student Id passed
	 * @param id
	 * @return studentBean
	 */
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public StudentBean getStudentsDataById(@PathVariable Long id){
		
		logger.info("getStudentsDataById method execution started.");
		
		StudentBean studentBean = new StudentBean();
		studentBean = studentService.getStudentsDataById(id);
		
		logger.info("getStudentsDataById method execution ended.");
		return studentBean;
	 }
	
	
	/**
	 * This method will add sports to the category as per student Id passed  
	 * @param id
	 * @param sportBean
	 * @return messageBean
	 */
	@RequestMapping(value="/addSports/{id}",method = RequestMethod.POST,headers="Accept=application/json")
	public MessageBean addSports(@PathVariable Long id, @RequestBody List<SportBean> sportBean) { 
		
		logger.info("addSports method execution started.");
		MessageBean messageBean = new MessageBean();
	
		boolean addSportsFlag = studentService.addSports(id, sportBean);
		
		if(addSportsFlag){
		  
			messageBean.setMessageDescription(StudentConstants.SPORT_SUCCESS_ADD_MESSAGE);
			
		}else{
			
			messageBean.setMessageDescription(StudentConstants.SPORT_ERROR_ADD_MESSAGE);
		}
		logger.info("addSports method execution ended.");
		return messageBean;
	}
	
	/**
	 * This method will create student and add sport category.
	 * @param studentBean
	 * @return messageBean
	 */
	@RequestMapping(value="/createStudent",method = RequestMethod.POST,headers="Accept=application/json")
	public MessageBean addSportCategory(@RequestBody StudentBean studentBean) { 
			
		    logger.info("addSportCategory method execution started.");
			MessageBean message = new MessageBean();
			
			String sportsCategory = null;
			
			//Making use of Qualifier to select the sport category for student.
			if(studentBean.getCategoryName().equalsIgnoreCase(athleticCategory.getCategoryName())){
				sportsCategory = athleticCategory.getCategoryName();
				
			}else if(studentBean.getCategoryName().equalsIgnoreCase(electronicCategory.getCategoryName())){
				sportsCategory = electronicCategory.getCategoryName();
				
			}else if(studentBean.getCategoryName().equalsIgnoreCase(waterCategory.getCategoryName())){
				sportsCategory = waterCategory.getCategoryName();
				
			}else{
				message.setMessageDescription(StudentConstants.NO_SUCH_SPORT_CATEGORY);
				return message;
			}
			
			studentBean.setCategoryName(sportsCategory);
			message = studentService.addSportCategory(studentBean);
			logger.info("addSportCategory method execution ended.");
			return message;
		}
}

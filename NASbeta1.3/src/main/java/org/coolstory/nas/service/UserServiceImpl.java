package org.coolstory.nas.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.UserBean;
import org.coolstory.nas.dao.StudentDao;
import org.coolstory.nas.dao.UserDao;
import org.coolstory.nas.domain.User;
import org.coolstory.nas.utils.DataFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public MessageBean setUserData(UserBean userBean){
		
		MessageBean message = userDao.setUserData(userBean);
		return message;
	}
	
	@Transactional
	public ArrayList getPersonsData(){
		
		ArrayList<DataFormVO> list = new ArrayList<DataFormVO>();
		list = userDao.getPersonsData();
		
		System.out.println("size of the list before return in service::"+list.get(0).getFirstName());
		
		/*for(DataFormVO users : list){
			
			System.out.println("Name in serviceImpl::"+users.getFirstName());
		}
		*/
		return list;
	}
	
	@Transactional
	public void updateSmsNotification(UserBean userBean){
		
		userDao.updateSmsNotification(userBean);
	}
	
	@Transactional
	public MessageBean setImportedData(ArrayList<UserBean> userBeanList){
		return userDao.setImportedData(userBeanList);
	}
}

package org.coolstory.nas.dao;

import java.util.ArrayList;
import java.util.List;

import org.coolstory.nas.HomeController;
import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.UserBean;
import org.coolstory.nas.domain.Student;
import org.coolstory.nas.domain.User;
import org.coolstory.nas.utils.DataFormVO;
import org.coolstory.nas.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public MessageBean setUserData(UserBean userBean){
		
		Session session = HibernateUtil.INSTANCE.getSession();
		MessageBean message = new MessageBean();
		
		try{
			
			session.beginTransaction();
			User user = new User();
			
			//if student doesn't exist , create new student and add category	
			user = (User)session.get(User.class, userBean.getMobileNumber());
			
			//if null then create new entry else update the existing entry.
			if(user == null){
				System.out.println("coming here for saving..");
				
				User newUser = new User();
				newUser.setFirstName(userBean.getFirstName());
				newUser.setMiddleName(userBean.getMiddleName());
				newUser.setLastName(userBean.getLastName());
				newUser.setMobileNumber(userBean.getMobileNumber());
				newUser.setAddress(userBean.getAddress());
				newUser.setNotificationflag(userBean.getNotificationflag());
				newUser.setEmail(userBean.getEmail());
				message.setMessageDescription("User has been created succesfully.");
				message.setFlag(true);
				session.save(newUser);
				
			}else{
				System.out.println("coming here for update..");
				user.setFirstName(userBean.getFirstName());
				user.setMiddleName(userBean.getMiddleName());
				user.setLastName(userBean.getLastName());
				user.setAddress(userBean.getAddress());
				user.setNotificationflag(userBean.getNotificationflag());
				user.setEmail(userBean.getEmail());
				session.saveOrUpdate(user);
				message.setMessageDescription("User has been updated succesfully.");
				message.setFlag(true);
			}
				
				System.out.println("before comiting..");
				session.getTransaction().commit();
			
		}catch(Exception e){
			
			e.printStackTrace();
			message.setMessageDescription("Exception caught::"+e.getStackTrace());
			message.setFlag(false);
		}
		
		return message;
	}
	
	//It will update sms notification for the given mobile number.
    public void updateSmsNotification(UserBean userBean){
		
    	Session session = HibernateUtil.INSTANCE.getSession();
       try{
			
			session.beginTransaction();
			
			System.out.println("mobile no::"+userBean.getMobileNumber()+"::notifctn flag::"+userBean.getNotificationflag());
			
			Query query = session
					.createQuery("update User user set user.notificationflag= :notificationflag where user.mobileNumber=:mobileNumber");
		
		query.setParameter("mobileNumber", userBean.getMobileNumber());
		query.setParameter("notificationflag", userBean.getNotificationflag());
		
		int updateStatus= query.executeUpdate();
		
		System.out.println("Rows updated in dao::"+updateStatus);
		
		session.getTransaction().commit();
		
       }catch(Exception e){
    	    e.printStackTrace();
			logger.error("Exception caught in updateSmsNotification::"+e.getStackTrace());
       }
	}

    
	public ArrayList getPersonsData(){
		
		Session session = HibernateUtil.INSTANCE.getSession();
		ArrayList<DataFormVO> arrListUsers = new ArrayList<DataFormVO>();
		
        try{
			
			session.beginTransaction();
			User user = new User();
			
			Query query = session.createQuery("from User");
			List<User> userList = query.list();
			
			System.out.println("size of the list::"+userList.size());
			
			for(User users : userList){
				
				DataFormVO dataFormVO = new DataFormVO();
	
				dataFormVO.setUserId(users.getUserId());
				dataFormVO.setFirstName(users.getFirstName());
				dataFormVO.setMiddleName(users.getMiddleName());
				dataFormVO.setLastName(users.getLastName());
				dataFormVO.setEmail(users.getEmail());
				dataFormVO.setMobileNumber(users.getMobileNumber());
				dataFormVO.setAddress(users.getAddress());
				dataFormVO.setNotificationflag(users.getNotificationflag());
				
				arrListUsers.add(dataFormVO);
			}
        
        }catch(Exception e){
        	
        	e.printStackTrace();
        	//userBean.setMessageDescription("Exception caught::"+e.getStackTrace());
        	//userBean.setFlag(false);
        }
        
        System.out.println("size of the list before return::"+arrListUsers.size());
        return arrListUsers;
	}
	
	public MessageBean setImportedData(ArrayList<UserBean> userBeanList){
		
		Session session = HibernateUtil.INSTANCE.getSession();
		MessageBean messageBean = new MessageBean();
		
        try{
			
			session.beginTransaction();
			User user = null;
			
			for(UserBean userBean : userBeanList){
				
				user = new User();
				user.setUserId(userBean.getUserId());
				user.setFirstName(userBean.getFirstName());
				user.setMiddleName(userBean.getMiddleName());
            	user.setLastName(userBean.getLastName());
            	user.setEmail(userBean.getEmail());
            	user.setMobileNumber(userBean.getMobileNumber());
            	user.setAddress(userBean.getAddress());
            	user.setNotificationflag(userBean.getNotificationflag());  
            	session.saveOrUpdate(user);
			}
			
			session.getTransaction().commit();
			session.close();
			
			messageBean.setFlag(true);
			messageBean.setMessageDescription("Data has been imported successfully");
			
        
        }catch(Exception e){
        	messageBean.setFlag(true);
			messageBean.setMessageDescription("Data has been imported successfully");
			logger.error("Error occurred in setImportedData in UserDaoImpl::"+e.getMessage());
			e.printStackTrace();
        }
        
        return messageBean;
	}
}

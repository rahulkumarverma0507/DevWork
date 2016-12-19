package org.coolstory.nas.dao;

import java.util.ArrayList;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.UserBean;

public interface UserDao {

	MessageBean setUserData(UserBean userBean);
	ArrayList getPersonsData();
	void updateSmsNotification(UserBean userBean);
	MessageBean setImportedData(ArrayList<UserBean> userBeanList);
}
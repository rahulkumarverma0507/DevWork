package org.coolstory.nas.service;

import java.util.ArrayList;

import org.coolstory.nas.bean.MessageBean;
import org.coolstory.nas.bean.UserBean;

public interface UserService {

	MessageBean setUserData(UserBean userBean);
	ArrayList getPersonsData();
	void updateSmsNotification(UserBean userBean);
	MessageBean setImportedData(ArrayList<UserBean> userBeanList);
}

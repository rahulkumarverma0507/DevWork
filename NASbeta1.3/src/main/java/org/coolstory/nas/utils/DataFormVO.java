package org.coolstory.nas.utils;

import java.io.Serializable;

public class DataFormVO implements Serializable{

	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String address;
	private String notificationflag;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNotificationflag() {
		return notificationflag;
	}
	public void setNotificationflag(String notificationflag) {
		this.notificationflag = notificationflag;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
    public String toString() {
        return "EmployeeVO [firstName=" + firstName
                + ", middleName=" +middleName+", lastName=" + lastName + ", email=" + email + " , mobileNumber="+ mobileNumber +", address=" +address+",notificationflag="+notificationflag+"]";
    }	
}

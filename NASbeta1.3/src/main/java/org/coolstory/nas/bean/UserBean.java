package org.coolstory.nas.bean;

// This class is the actual user class. However this class does-not implement the Person Interface.
public class UserBean {

	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String address;
	private String notificationflag;
	private String messageDescription;
	private boolean flag;
	private String personDataString;
	private String mobileNumberString;
	private String messageBody;
	
	public String getMobileNumberString() {
		return mobileNumberString;
	}
	public void setMobileNumberString(String mobileNumberString) {
		this.mobileNumberString = mobileNumberString;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getPersonDataString() {
		return personDataString;
	}
	public void setPersonDataString(String personDataString) {
		this.personDataString = personDataString;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getNotificationflag() {
		return notificationflag;
	}
	public void setNotificationflag(String notificationflag) {
		this.notificationflag = notificationflag;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}

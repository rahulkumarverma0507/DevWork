/**
 * 
 */
package org.coolstory.nas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author DELL 
 * Will use builder pattern for methods with lots of variable input
 *         parameters.
 */

@Entity
@Table(name="professionals")
public class Professional {
	
	@Id @GeneratedValue
	@Column(name="p_id")
	private long id;
	
	@Column(name="p_firstname")
	private String firstName;
	
	@Column(name="p_lastname")
	private String lastName;
	
	// These are the public pages, so we don't need username & password here.
//	private String Username; 
//	private String Password;
	
	@Column(name="p_designation")
	private String designation;
	
	@Column(name="p_desc")
	private String desc;
	
//	private Institution institution;
//	private List<PersonProperty> properties;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
//	public Institution getInstitution() {
//		return institution;
//	}
//	public void setInstitution(Institution institution) {
//		this.institution = institution;
//	}
//	public List<PersonProperty> getProperties() {
//		return properties;
//	}
//	public void setProperties(List<PersonProperty> properties) {
//		this.properties = properties;
//	}




}

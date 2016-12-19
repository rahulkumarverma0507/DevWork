package org.coolstory.nas.bean;

import java.util.ArrayList;
import java.util.List;

import org.coolstory.nas.domain.Sport;

public class StudentBean {

	private Long id;
	private String name;
	private String classes;
	private String section;
	private int categoryId;
	private String categoryName;
	
	private List<Sport> sportList = new ArrayList<Sport>();
	
	public List<Sport> getSportList() {
		return sportList;
	}
	public void setSportList(List<Sport> sportList) {
		this.sportList = sportList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}

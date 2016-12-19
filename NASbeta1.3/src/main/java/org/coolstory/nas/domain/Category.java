package org.coolstory.nas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Category")
public class Category {

	@Id @GeneratedValue
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sport> sportList = new ArrayList<Sport>();
	
	public List<Sport> getSportList() {
		return sportList;
	}

	public void setSportList(List<Sport> sportList) {
		this.sportList = sportList;
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

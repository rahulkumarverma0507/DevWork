package org.coolstory.nas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="Student")
public class Student {

	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Class")
	private String classes;
	
	@Column(name="Section")
	private String section;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sport> sportList = new ArrayList<Sport>();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Sport> getSportList() {
		return sportList;
	}

	public void setSportList(List<Sport> sportList) {
		this.sportList = sportList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
}

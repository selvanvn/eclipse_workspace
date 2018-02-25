package agile;

import java.time.LocalDate;
import java.time.Period;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

//import java.util.*;

public class Individual {

	// class for individual

	// the info list for individual
	private String id;
	private String name;
	private String givenName;
	private String surName;
	private char sex;
	private String birthDate;
	private String deathDate;
	private String child;
	private Boolean aliveStatus;
	private int age;
	private String spouse;
	// private String familyId = null;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSurName(String name) {
		this.surName = name;
	}

	public String getSurName() {
		return this.surName;
	}

	public void setGivenName(String name) {
		this.givenName = name;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public char getSex() {
		return this.sex;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate.trim();
	}

	public String getDeathDate() {
		return this.deathDate;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getSpouse() {
		return this.spouse;
	}

	public String getChild() {
		return this.child;
	}

	public String toString() {
		return getName();
	}

	public Boolean getAliveStatus() {
		return aliveStatus;
	}

	public void setAliveStatus(Boolean aliveStatus) {
		this.aliveStatus = aliveStatus;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

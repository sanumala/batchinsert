package com.satesh.batchinsert.dao;

import java.util.List;

import javax.sql.DataSource;

import com.satesh.batchinsert.model.Person;

public interface PersonDao {

	public void init(DataSource dataSource);

	public void createPersonTable(String sql);

	public void insertPersons(List<Person> persons);
	
	public Person findPersonById(int id);
	
	public List<Person> getAllPersons();
}

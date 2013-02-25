package com.satesh.batchinsert.service;

import java.util.List;

import com.satesh.batchinsert.model.Person;

public interface PersonService {

	public void createDatabaseTable(String sql);
	public void insertPersons(List<Person> persons);
	public Person findPersonById(int id);
	public List<Person> findAllPersons();
}

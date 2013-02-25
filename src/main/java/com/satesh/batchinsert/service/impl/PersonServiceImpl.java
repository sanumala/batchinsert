package com.satesh.batchinsert.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satesh.batchinsert.dao.PersonDao;
import com.satesh.batchinsert.model.Person;
import com.satesh.batchinsert.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	@Autowired
	private PersonDao personDao;

	public void createDatabaseTable(String sql) {
		if (sql != null) {
			//Since I am getting this create from enum not validating except null
			personDao.createPersonTable(sql);
		} else {
			logger.error("Its not a valid sql");
		}

	}

	public void insertPersons(List<Person> persons) {
		if(persons != null && persons.size() >=1)
		{
			personDao.insertPersons(persons);
		}
	}

	public Person findPersonById(int id) {
		return personDao.findPersonById(id);
	}

	public List<Person> findAllPersons() {
		return personDao.getAllPersons();
	}

	

}

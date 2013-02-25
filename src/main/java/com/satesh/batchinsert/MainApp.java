package com.satesh.batchinsert;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.satesh.batchinsert.enums.DatabaseEnum;
import com.satesh.batchinsert.model.Person;
import com.satesh.batchinsert.service.PersonService;

@Component
public class MainApp {

	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);
	public static void main(String... args) {
		
		/*
		 * ApplicationContext context = new
		 * ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		 * MainApp p = context.getBean(MainApp.class); p.insertPersons();
		 */

		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.satesh.batchinsert");
		MainApp p = ctx.getBean(MainApp.class);
		p.insertPersons();
	}

	@Autowired
	private PersonService personService;

	private List<Person> buildPersons() {
		List<Person> persons = new ArrayList<Person>();
		Person p = null;
		for (int i = 1; i <= 10; i++) {
			p = new Person();
			p.setId(i);
			p.setName("Name" + i);
			p.setAge(30 + i);
			if (i % 2 == 0) {
				p.setGender("M");
			} else {
				p.setGender("F");
			}
			persons.add(p);
		}
		return persons;
	}

	private void insertPersons() {
		// create table first
		personService.createDatabaseTable(DatabaseEnum.CREATE.getValue());

		// build and insert person data
		personService.insertPersons(buildPersons());

		logger.info("Finding person by id :: "
				+ personService.findPersonById(1));

		logger.info("Finding all persons :: "
				+ personService.findAllPersons());
	}

}

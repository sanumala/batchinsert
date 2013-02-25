package com.satesh.batchinsert.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.satesh.batchinsert.model.Person;

public class PersonRowMapper implements RowMapper<Person> {
	
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("ID"));
		person.setName(rs.getString("NAME"));
		person.setAge(rs.getInt("AGE"));
		person.setGender(rs.getString("GENDER"));
		return person;
	}
}

package com.satesh.batchinsert.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.satesh.batchinsert.dao.PersonDao;
import com.satesh.batchinsert.dao.mapper.PersonRowMapper;
import com.satesh.batchinsert.enums.DatabaseEnum;
import com.satesh.batchinsert.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insertPersons(final List<Person> persons) {
		jdbcTemplate.batchUpdate(DatabaseEnum.INSERT.getValue(),
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Person p = persons.get(i);
						logger.info("Persisting Person with ::: "+p);
						ps.setInt(1, p.getId());
						ps.setString(2, p.getName());
						ps.setInt(3, p.getAge());
						ps.setString(4, p.getGender());
					}

					public int getBatchSize() {
						return persons.size();
					}
				});

	}

	public void createPersonTable(String sql) {
		// this create the table in the way you have given
		jdbcTemplate.execute(sql);

	}

	public Person findPersonById(int id) {
		return jdbcTemplate.queryForObject(DatabaseEnum.SELECT_BY_ID.getValue(), new Object[]{id}, new PersonRowMapper());
	}

	public List<Person> getAllPersons() {
		return jdbcTemplate.query(DatabaseEnum.SELECT_ALL.getValue(), new PersonRowMapper());
	}

}

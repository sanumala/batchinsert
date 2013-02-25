package com.satesh.batchinsert.enums;

public enum DatabaseEnum {

	CREATE("CREATE TABLE PERSON(ID INTEGER NOT NULL, NAME VARCHAR(30) NOT NULL, AGE INTEGER NOT NULL, GENDER VARCHAR(1) NOT NULL, PRIMARY KEY (ID))"), 
	INSERT("INSERT INTO PERSON VALUES (?,?,?,?)"),
	SELECT_BY_ID("SELECT * FROM PERSON WHERE ID=?"),
	SELECT_ALL("SELECT * FROM PERSON");

	private String value;

	private DatabaseEnum(String value) {
		this.value = value;
	}
	
	 public String getValue()
	 {
		 return value;
	 }

}

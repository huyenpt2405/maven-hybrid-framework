package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import utilities.GlobalConstants;

public class UserDataMapper {
	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.getGlobalConstants().getProjectPath() + "/src/test/resources/UserData.json"), UserDataMapper .class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@JsonProperty("password")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}
	
	@JsonProperty("login")
	private Login login;
	
	static class Login {
		@JsonProperty("username")
		private String username;
		
		@JsonProperty("password")
		private String password;
	}
	
	public String getUsername() {
		return login.username;
	}
	
	public String getUserPassword() {
		return login.password;
	}
}

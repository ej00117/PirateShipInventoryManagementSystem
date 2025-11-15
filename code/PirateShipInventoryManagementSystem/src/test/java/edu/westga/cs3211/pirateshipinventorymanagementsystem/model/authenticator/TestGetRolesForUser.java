package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.authenticator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;

class TestGetRolesForUser {

	@Test
	void testNullName() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.getRolesForUser(null, "password");});
	}
	
	@Test
	void testNullPassword() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.getRolesForUser("name", null);});
	}
	
	
	@Test
	void testUserNotInSystem() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.getRolesForUser("name", "password");});
	}
	
	@Test
	void testUserIsCorrectButPasswordIsWrong() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.getRolesForUser("bob", "password");});
	}
	
	@Test
	void testPasswordIsCorrectButUserIsWrong() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.getRolesForUser("name", "pass1234");});
	}
	
	@Test
	void testUserIsInSystem() {
		Authenticator authenticator = new Authenticator();
		ArrayList<Role> roles = authenticator.getRolesForUser("stanley", "secretpass");
		assertTrue(roles.contains(Role.CREWMATE), "Checking that we got the roles from the user");
	}

}

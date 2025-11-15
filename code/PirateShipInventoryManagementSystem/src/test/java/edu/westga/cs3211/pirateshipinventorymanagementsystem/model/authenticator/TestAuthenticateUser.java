package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.authenticator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.Authenticator;

class TestAuthenticateUser {

	@Test
	void testNullName() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.authenticateUser(null, "password");});
	}
	
	@Test
	void testNullPassword() {
		Authenticator authenticator = new Authenticator();
		assertThrows(IllegalArgumentException.class, ()->{authenticator.authenticateUser("name", null);});
	}
	
	@Test
	void testUserNotInSystem() {
		Authenticator authenticator = new Authenticator();
		assertTrue(!authenticator.authenticateUser("user", "password"), "user is not in system");
		
	}
	
	@Test
	void testUserIsCorrectButPasswordWrong() {
		Authenticator authenticator = new Authenticator();
		assertTrue(!authenticator.authenticateUser("bob", "password"), "user is not in system");
		
	}
	
	@Test
	void testPasswordIsCorrectButUserWrong() {
		Authenticator authenticator = new Authenticator();
		assertTrue(!authenticator.authenticateUser("name", "pass1234"), "user is not in system");
		
	}
	
	@Test
	void testUserIsInSystem() {
		Authenticator authenticator = new Authenticator();
		assertTrue(authenticator.authenticateUser("stanley", "secretpass"), "user is in system");
		
	}

}

package edu.westga.cs3211.pirateshipinventorymanagementsystem.model.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.pirateshipinventorymanagementsystem.enums.Role;
import edu.westga.cs3211.pirateshipinventorymanagementsystem.model.User;


class TestConstructor {

	@Test
	void testNullName() {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.CREWMATE);
		assertThrows(IllegalArgumentException.class, ()->{new User(null, "password", roles);});
	}
	
	@Test
	void testNullPassword() {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.CREWMATE);
		assertThrows(IllegalArgumentException.class, ()->{new User("name", null, roles);});
	}
	
	@Test
	void testNullRoles() {
		assertThrows(IllegalArgumentException.class, ()->{new User("name", "password", null);});
	}
	
	@Test
	void testUserWithNoRoles() {
		assertThrows(IllegalArgumentException.class, ()->{new User("name", "password", new ArrayList<Role>());});
	}
	
	@Test
	void testValidUser() {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.CREWMATE);
		User user = new User("name", "password", roles);
		assertEquals("name", user.getName(), "checking name of the user");
		assertEquals("password", user.getPassword(), "checking name of the user");
		assertTrue(user.getRoles().contains(Role.CREWMATE), "checking is crewmate");
	}
	
	@Test
	void testValidCook() {
		ArrayList<Role> roles = new ArrayList<Role>();
		roles.add(Role.CREWMATE);
		roles.add(Role.COOK);
		User user = new User("name", "password", roles);
		assertEquals("name", user.getName(), "checking name of the user");
		assertEquals("password", user.getPassword(), "checking name of the user");
		assertTrue(user.getRoles().contains(Role.CREWMATE), "checking is crewmate");
		assertTrue(user.getRoles().contains(Role.COOK), "checking is cook");
	}

}

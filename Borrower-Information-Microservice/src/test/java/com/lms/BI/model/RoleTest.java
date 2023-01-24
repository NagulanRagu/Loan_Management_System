package com.lms.BI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.lms.BI.Model.Role;

@ContextConfiguration
@SpringBootTest
public class RoleTest {
    
    @Test
    public void testNoArgConstructor() {
        Role role = new Role();
        assertNotNull(role);
    }

    @Test
    public void testAllArgConstructor() {
        Role role = new Role("ROLE_USER");
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    public void testAllArgConstructorWithId() {
        Role role = new Role(2 ,"ROLE_USER");
        assertEquals(2, role.getId());
    }

    @Test
    public void testId() {
        Role role = new Role();
        role.setId(2);
        assertEquals(2, role.getId());
    }

    @Test
    public void testName() {
        Role role = new Role();
        role.setName("ROLE_USER");
        assertEquals("ROLE_USER", role.getName());
    }
}

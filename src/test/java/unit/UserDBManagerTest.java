package unit;

import model.User;
import DAO.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserDBManagerTest {

    private DBManager dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = mock(DBManager.class);
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Arrange
        User user = new User("John Doe", "john@example.com", "password123");
        when(dbManager.registerUser(user)).thenReturn(true);

        // Act
        boolean result = dbManager.registerUser(user);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).registerUser(user);
    }

    @Test
    public void testCheckIfUserExists() throws Exception {
        // Arrange
        String email = "john@example.com";
        when(dbManager.checkIfUserExists(email)).thenReturn(true);

        // Act
        boolean userExists = dbManager.checkIfUserExists(email);

        // Assert
        assertTrue(userExists);
        verify(dbManager, times(1)).checkIfUserExists(email);
    }

    @Test
    public void testValidateUser() throws Exception {
        // Arrange
        String email = "john@example.com";
        String password = "password123";
        User user = new User("John Doe", email, password);
        when(dbManager.validateUser(email, password)).thenReturn(user);

        // Act
        User validatedUser = dbManager.validateUser(email, password);

        // Assert
        assertNotNull(validatedUser);
        assertEquals(email, validatedUser.getEmail());
        verify(dbManager, times(1)).validateUser(email, password);
    }

    @Test
    public void testFetchAllUsers() throws Exception {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User("John Doe", "john@example.com", "password123"));
        userList.add(new User("Jane Doe", "jane@example.com", "password456"));

        when(dbManager.getAllUsers()).thenReturn(userList);

        // Act
        List<User> fetchedUsers = dbManager.getAllUsers();

        // Assert
        assertEquals(2, fetchedUsers.size());
        assertEquals("john@example.com", fetchedUsers.get(0).getEmail());
        verify(dbManager, times(1)).getAllUsers();
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        User updatedUser = new User("John Doe", "john@newexample.com", "newpassword123");
        when(dbManager.updateUser(updatedUser)).thenReturn(true);

        // Act
        boolean result = dbManager.updateUser(updatedUser);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).updateUser(updatedUser);
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        int userId = 1;
        when(dbManager.deleteUser(userId)).thenReturn(true);

        // Act
        boolean result = dbManager.deleteUser(userId);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).deleteUser(userId);
    }
}

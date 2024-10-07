package unit;

import model.MenuItem;
import DAO.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5 tests
public class MenuDBManagerTest {

@Mock
private DBManager dbManager; // Mock the DBManager

@BeforeEach
public void setup() {
// Setup can be used to initialize any variables if needed
}

@Test
public void testCreateMenuItem() throws Exception {
// Arrange
MenuItem item = new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course");
when(dbManager.insertMenuItem(item)).thenReturn(true);

// Act
boolean result = dbManager.insertMenuItem(item);

// Assert
assertTrue(result);
verify(dbManager, times(1)).insertMenuItem(item);
}

@Test
public void testUpdateMenuItem() throws Exception {
// Arrange
MenuItem updatedItem = new MenuItem(1, "Pasta", "Creamy pasta", 12.99, "Main Course");
when(dbManager.updateMenuItem(updatedItem)).thenReturn(true);

// Act
boolean result = dbManager.updateMenuItem(updatedItem);

// Assert
assertTrue(result);
verify(dbManager, times(1)).updateMenuItem(updatedItem);
}

@Test
public void testDeleteMenuItem() throws Exception {
// Arrange
int menuItemId = 1;
when(dbManager.deleteMenuItem(menuItemId)).thenReturn(true);

// Act
boolean result = dbManager.deleteMenuItem(menuItemId);

// Assert
assertTrue(result);
verify(dbManager, times(1)).deleteMenuItem(menuItemId);
}

@Test
public void testFetchAllMenuItems() throws Exception {
// Arrange
List<MenuItem> menuItems = new ArrayList<>();
menuItems.add(new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course"));
when(dbManager.fetchAllMenuItems()).thenReturn(menuItems);

// Act
List<MenuItem> fetchedMenuItems = dbManager.fetchAllMenuItems();

// Assert
assertNotNull(fetchedMenuItems);
assertEquals(1, fetchedMenuItems.size());
verify(dbManager, times(1)).fetchAllMenuItems();
}
}

package unit;

import DAO.DBConnector;
import DAO.DBManager;
import model.MenuItem;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MenuDBManagerTest {

    private Connection conn;
    private DBManager dbManager;

    @BeforeAll
    public void setup() throws ClassNotFoundException, SQLException {
        conn = DBConnector.getConnection();
        dbManager = new DBManager(); // Initialize DBManager
    }

    @AfterAll
    public void tearDown() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @BeforeEach
    public void beforeEachTest() throws SQLException {
        // Clean the menu table before each test
        conn.createStatement().execute("DELETE FROM menu");
    }

    @Test
    public void testConnection() {
        assertNotNull(conn); // Ensure the connection is not null
    }

    // Test case for creating a menu item
    @Test
    public void testCreateMenuItem() throws SQLException {
        MenuItem item = new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course");
        boolean result = dbManager.insertMenuItem(item);
        assertTrue(result);

        // Fetch all menu items and check if the newly created item exists
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        assertFalse(menuItems.isEmpty());
        MenuItem fetchedItem = menuItems.get(menuItems.size() - 1); // Get the last added item
        assertEquals("Pizza", fetchedItem.getName());
        assertEquals("Cheese pizza", fetchedItem.getDescription());
        assertEquals(10.99, fetchedItem.getPrice());
        assertEquals("Main Course", fetchedItem.getCategory());
    }

    // Test case for updating a menu item
    @Test
    public void testUpdateMenuItem() throws SQLException {
        // Create a new menu item
        MenuItem item = new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course");
        dbManager.insertMenuItem(item);

        // Fetch the inserted menu item to get its ID
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        MenuItem createdItem = menuItems.get(menuItems.size() - 1);

        // Update the item details
        MenuItem updatedItem = new MenuItem(createdItem.getId(), "Pasta", "Creamy pasta", 12.99, "Main Course");
        boolean result = dbManager.updateMenuItem(updatedItem);
        assertTrue(result);

        // Verify the updated details
        MenuItem fetchedItem = dbManager.getMenuItemById(updatedItem.getId());
        assertEquals("Pasta", fetchedItem.getName());
        assertEquals("Creamy pasta", fetchedItem.getDescription());
        assertEquals(12.99, fetchedItem.getPrice());
        assertEquals("Main Course", fetchedItem.getCategory());
    }

    // Test case for deleting a menu item
    @Test
    public void testDeleteMenuItem() throws SQLException {
        // Create a new menu item
        MenuItem item = new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course");
        dbManager.insertMenuItem(item);

        // Fetch the inserted menu item to get its ID
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        MenuItem createdItem = menuItems.get(menuItems.size() - 1);

        // Delete the item
        boolean result = dbManager.deleteMenuItem(createdItem.getId());
        assertTrue(result);

        // Verify that the item was deleted
        MenuItem fetchedItem = dbManager.getMenuItemById(createdItem.getId());
        assertNull(fetchedItem);
    }

    // Test case for fetching all menu items
    @Test
    public void testFetchAllMenuItems() throws SQLException {
        // Create some menu items
        dbManager.insertMenuItem(new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course"));
        dbManager.insertMenuItem(new MenuItem(0, "Ice Cream", "Vanilla ice cream", 5.99, "Dessert"));

        // Fetch all menu items
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        assertNotNull(menuItems);
        assertEquals(2, menuItems.size()); // There should be 2 items in total
    }

    // Test case for fetching menu items by category
    @Test
    public void testFetchMenuItemsByCategory() throws SQLException {
        // Create menu items
        dbManager.insertMenuItem(new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course"));
        dbManager.insertMenuItem(new MenuItem(0, "Ice Cream", "Vanilla ice cream", 5.99, "Dessert"));

        // Fetch menu items in the "Main Course" category
        List<MenuItem> mainCourseItems = dbManager.fetchMenuItemsByCategory("Main Course");
        assertNotNull(mainCourseItems);
        assertEquals(1, mainCourseItems.size());
        assertEquals("Pizza", mainCourseItems.get(0).getName());
    }

    // Test case for fetching all unique categories
    @Test
    public void testFetchAllCategories() throws SQLException {
        // Create menu items
        dbManager.insertMenuItem(new MenuItem(0, "Pizza", "Cheese pizza", 10.99, "Main Course"));
        dbManager.insertMenuItem(new MenuItem(0, "Ice Cream", "Vanilla ice cream", 5.99, "Dessert"));

        // Fetch all unique categories
        List<String> categories = dbManager.fetchAllCategories();
        assertNotNull(categories);
        assertEquals(2, categories.size()); // Should return 2 unique categories
        assertTrue(categories.contains("Main Course"));
        assertTrue(categories.contains("Dessert"));
    }
}

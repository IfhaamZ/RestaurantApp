package unit;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import model.Table;
import DAO.DBConnector;
import DAO.DBManager;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TableDBManagerTest {

    private Connection conn;
    private DBManager dbManager;

    @BeforeAll
    public void setup() throws ClassNotFoundException, SQLException {
        conn = DBConnector.getConnection();
        dbManager = new DBManager(); // Adjust based on how DBManager is initialized
    }

    @AfterAll
    public void tearDown() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @BeforeEach
    public void beforeEachTest() throws SQLException {
        conn.createStatement().execute("DELETE FROM Event"); // Clean the event table before each test
    }

    @Test
    public void testConnection() {
        assertNotNull(conn); // Ensure the connection is not null
    }

    // Test case for creating a table
    @Test
    public void testCreateTable() throws SQLException {
        Table table = new Table(0, "Available", 4, null, null, null, null);
        boolean result = dbManager.createTable(table);
        assertTrue(result);

        // Fetch all tables and check if the newly created table exists
        List<Table> tableList = dbManager.fetchAllTables();
        assertFalse(tableList.isEmpty());
        Table fetchedTable = tableList.get(tableList.size() - 1); // get the last added table
        assertEquals(4, fetchedTable.getCapacity());
        assertEquals("Available", fetchedTable.getStatus());
    }

    // Test case for updating a table
    @Test
    public void testUpdateTable() throws SQLException {
        // Create a new table
        Table table = new Table(0, "Available", 4, null, null, null, null);
        dbManager.createTable(table);

        // Fetch the table after creation to get the auto-generated ID
        List<Table> tableList = dbManager.fetchAllTables();
        Table createdTable = tableList.get(tableList.size() - 1); // get the last added table

        // Update the table details
        Table updatedTable = new Table(createdTable.getId(), "Reserved", 6, null, null, null, null);
        boolean result = dbManager.updateTable(updatedTable);
        assertTrue(result, "Update failed");

        // Verify that the table was updated correctly
        Table fetchedTable = dbManager.getTableById(updatedTable.getId());
        assertEquals(6, fetchedTable.getCapacity());
        assertEquals("Reserved", fetchedTable.getStatus());
    }

    // Test case for reserving a table
    @Test
    public void testReserveTable() throws SQLException {
        // Create a new table
        Table table = new Table(0, "Available", 4, null, null, null, null);
        dbManager.createTable(table);

        // Fetch the table after creation to get the auto-generated ID
        List<Table> tableList = dbManager.fetchAllTables();
        Table createdTable = tableList.get(tableList.size() - 1); // get the last added table

        // Reserve the table
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(1);
        boolean result = dbManager.reserveTable(createdTable.getId(), reservationTime, "John Doe", "123456789",
                "john@example.com");
        assertTrue(result, "Reservation failed");

        // Verify that the table was reserved correctly
        Table fetchedTable = dbManager.getTableById(createdTable.getId());
        assertEquals("Reserved", fetchedTable.getStatus());
        assertEquals("John Doe", fetchedTable.getReservedByName());
        assertEquals("123456789", fetchedTable.getReservedByPhone());
        assertEquals("john@example.com", fetchedTable.getReservedByEmail());
        assertNotNull(fetchedTable.getReservationTime());
    }

    // Test case for clearing reservation details
    @Test
    public void testClearReservationDetails() throws SQLException {
        // Create a new reserved table
        Table table = new Table(0, "Available", 4, null, null, null, null);
        dbManager.createTable(table);

        // Fetch the table after creation to get the auto-generated ID
        List<Table> tableList = dbManager.fetchAllTables();
        Table createdTable = tableList.get(tableList.size() - 1); // get the last added table

        LocalDateTime reservationTime = LocalDateTime.now().plusDays(1);
        dbManager.reserveTable(createdTable.getId(), reservationTime, "John Doe", "123456789", "john@example.com");

        // Clear the reservation
        boolean result = dbManager.clearReservationDetails(createdTable.getId());
        assertTrue(result, "Clear reservation failed");

        // Verify that the reservation details were cleared
        Table fetchedTable = dbManager.getTableById(createdTable.getId());
        assertNull(fetchedTable.getReservationTime());
        assertNull(fetchedTable.getReservedByName());
        assertNull(fetchedTable.getReservedByPhone());
        assertNull(fetchedTable.getReservedByEmail());
        assertEquals("Available", fetchedTable.getStatus()); // Status should be "Available"
    }

    // Test case for deleting a table
    @Test
    public void testDeleteTable() throws SQLException {
        // Create a new table
        Table table = new Table(0, "Available", 4, null, null, null, null);
        dbManager.createTable(table);

        // Fetch the table after creation to get the auto-generated ID
        List<Table> tableList = dbManager.fetchAllTables();
        Table createdTable = tableList.get(tableList.size() - 1); // get the last added table

        // Delete the table
        boolean result = dbManager.deleteTable(createdTable.getId());
        assertTrue(result, "Delete failed");

        // Verify that the table was deleted
        Table fetchedTable = dbManager.getTableById(createdTable.getId());
        assertNull(fetchedTable); // The table should no longer exist
    }

    // Test fetching all available tables
    @Test
    public void testFetchAvailableTables() throws SQLException {
        // Create some tables
        dbManager.createTable(new Table(0, "Available", 4, null, null, null, null));
        dbManager.createTable(new Table(0, "Reserved", 6, LocalDateTime.now().plusDays(1), "John Doe", "123456789",
                "john@example.com"));

        // Fetch all available tables
        List<Table> availableTables = dbManager.fetchAvailableTables();
        assertNotNull(availableTables);
        assertFalse(availableTables.isEmpty());

        // Verify that only the available table is fetched
        assertTrue(availableTables.stream().allMatch(table -> "Available".equals(table.getStatus())));
    }
}

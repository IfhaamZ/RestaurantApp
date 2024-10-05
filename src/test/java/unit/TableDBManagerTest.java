package unit;

import model.Table;
import DAO.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5 tests
public class TableDBManagerTest {

    @Mock
    private DBManager dbManager; // Mock the DBManager

    @BeforeEach
    public void setup() {
        // Setup can be used to initialize any variables if needed
    }

    @Test
    public void testCreateTable() throws Exception {
        // Arrange
        Table table = new Table(0, "Available", 4, null, null, null, null);
        when(dbManager.createTable(table)).thenReturn(true);

        // Act
        boolean result = dbManager.createTable(table);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).createTable(table);
    }

    @Test
    public void testUpdateTable() throws Exception {
        // Arrange
        Table updatedTable = new Table(1, "Reserved", 6, null, null, null, null);
        when(dbManager.updateTable(updatedTable)).thenReturn(true);

        // Act
        boolean result = dbManager.updateTable(updatedTable);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).updateTable(updatedTable);
    }

    @Test
    public void testReserveTable() throws Exception {
        // Arrange
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(1);
        when(dbManager.reserveTable(1, reservationTime, "John Doe", "123456789", "john@example.com"))
                .thenReturn(true);

        // Act
        boolean result = dbManager.reserveTable(1, reservationTime, "John Doe", "123456789", "john@example.com");

        // Assert
        assertTrue(result);
        verify(dbManager, times(1))
                .reserveTable(1, reservationTime, "John Doe", "123456789", "john@example.com");
    }

    @Test
    public void testDeleteTable() throws Exception {
        // Arrange
        int tableId = 1;
        when(dbManager.deleteTable(tableId)).thenReturn(true);

        // Act
        boolean result = dbManager.deleteTable(tableId);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).deleteTable(tableId);
    }

    @Test
    public void testFetchAvailableTables() throws Exception {
        // Arrange
        List<Table> tables = new ArrayList<>();
        tables.add(new Table(1, "Available", 4, null, null, null, null));
        when(dbManager.fetchAvailableTables()).thenReturn(tables);

        // Act
        List<Table> availableTables = dbManager.fetchAvailableTables();

        // Assert
        assertNotNull(availableTables);
        assertEquals(1, availableTables.size());
        verify(dbManager, times(1)).fetchAvailableTables();
    }
}

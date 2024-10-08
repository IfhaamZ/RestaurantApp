package unit;

import model.Inventory;
import model.InventoryAudit;
import model.Product;
import DAO.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryDBManagerTest {

    @Mock
    private DBManager dbManager; // Mock the DBManager

    @InjectMocks
    private Inventory inventory; // Inject the mocked DBManager into Inventory

    @BeforeEach
    public void setup() {
        inventory = new Inventory(); // Initialize the Inventory object before each test
    }

    @Test
    public void testCheckLowStockLevels() {
        // Arrange
        Product product1 = new Product("P002", "Apple", 2);
        Product product2 = new Product("P003", "Banana", 25);
        inventory.addProduct(product1, "Manager");
        inventory.addProduct(product2, "Manager");

        // Act
        String lowStockMessage = inventory.checkLowStockLevels();

        // Assert
        assertTrue(lowStockMessage.contains("Apple"));
        assertFalse(lowStockMessage.contains("Banana"));
    }

    @Test
    public void testViewInventoryUsageTrend() throws Exception {
        // Arrange
        String productID = "P004";
        List<InventoryAudit> mockAuditList = new ArrayList<>();
        InventoryAudit audit1 = new InventoryAudit();
        audit1.setProductID(productID);
        audit1.setOldStock(20);
        audit1.setNewStock(15);
        audit1.setUpdatedBy("Manager");
        audit1.setTimestamp(new Timestamp(System.currentTimeMillis()));

        InventoryAudit audit2 = new InventoryAudit();
        audit2.setProductID(productID);
        audit2.setOldStock(15);
        audit2.setNewStock(10);
        audit2.setUpdatedBy("Manager");
        audit2.setTimestamp(new Timestamp(System.currentTimeMillis() - 10000));

        mockAuditList.add(audit1);
        mockAuditList.add(audit2);

        when(dbManager.getInventoryAuditByProduct(productID)).thenReturn(mockAuditList);

        // Act
        List<InventoryAudit> auditList = dbManager.getInventoryAuditByProduct(productID);

        // Assert
        assertNotNull(auditList);
        assertEquals(2, auditList.size());
        assertEquals(15, auditList.get(0).getNewStock());
        assertEquals(10, auditList.get(1).getNewStock());
    }
}

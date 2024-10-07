// package unit;

// import DAO.DBManager;
// import model.Inventory;
// import model.Product;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.extension.ExtendWith;

// import java.util.LinkedHashMap;
// import java.util.Map;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5 tests
// public class InventoryDBManagerTest {

//     @Mock
//     private DBManager dbManager; // Mock the DBManager (if needed for DB operations)

//     @InjectMocks
//     private Inventory inventory; // Inject the mocked DBManager into Inventory

//     @BeforeEach
//     public void setup() {
//         // Initialize any necessary setup before each test if needed
//     }

//     @Test
//     public void testLoadProductsFromDB() throws Exception {
//         // Arrange: Mock the loading of products
//         inventory = new Inventory();

//         // Act: Load products into inventory
//         inventory.loadProductsFromDB();

//         // Assert: Verify that products were loaded from the database (in-memory)
//         assertNotNull(inventory.viewStockLevels());
//     }

//     @Test
//     public void testAddProduct() throws Exception {
//         // Arrange
//         Product product = new Product("P002", "Phone", 20);
//         String updatedBy = "Manager";

//         // Act: Add product to inventory
//         inventory.addProduct(product, updatedBy);

//         // Assert: Verify the product was added
//         assertNotNull(inventory.getProductByID("P002"));
//         assertEquals(20, inventory.getProductByID("P002").getStockQuantity());
//     }

//     @Test
//     public void testRemoveProduct() throws Exception {
//         // Arrange
//         Product product = new Product("P003", "Tablet", 5);
//         String updatedBy = "Manager";
//         inventory.addProduct(product, updatedBy);

//         // Act: Remove product from inventory
//         inventory.removeProduct("P003", updatedBy);

//         // Assert: Verify the product was removed
//         assertNull(inventory.getProductByID("P003"));
//     }

//     // @Test
//     // public void testUpdateStockLevel() throws Exception {
//     // // Arrange
//     // Product product = new Product("P001", "Monitor", 15);
//     // String updatedBy = "Manager";
//     // inventory.addProduct(product, updatedBy);

//     // // Act: Update stock level for the product
//     // boolean isUpdated = inventory.updateStockLevel("P001", 30, updatedBy);

//     // // Assert: Verify the stock was updated
//     // assertTrue(isUpdated);
//     // assertEquals(30, inventory.getProductByID("P001").getStockQuantity());
//     // }

//     @Test
//     public void testCheckLowStockLevels() {
//         // Arrange: Set up products with one low stock
//         Product product1 = new Product("P005", "Headphones", 2);
//         Product product2 = new Product("P006", "Keyboard", 15);
//         inventory.addProduct(product1, "Manager");
//         inventory.addProduct(product2, "Manager");

//         // Act: Check low stock levels
//         String lowStockMessage = inventory.checkLowStockLevels();

//         // Assert: Verify the low stock message
//         assertTrue(lowStockMessage.contains("Headphones"));
//         assertFalse(lowStockMessage.contains("Keyboard"));
//     }
// }

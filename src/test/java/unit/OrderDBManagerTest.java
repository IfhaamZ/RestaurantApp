package unit;

import model.Order;
import DAO.DBManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderDBManagerTest {

    private DBManager dbManager;

    @BeforeEach
    public void setUp() {
        dbManager = mock(DBManager.class);
    }

    @Test
    public void testInsertOrder() throws Exception {
        // Arrange
        Order order = new Order(0, "John Doe", "Product A", "pending");
        when(dbManager.insertOrder(order)).thenReturn(true);

        // Act
        boolean result = dbManager.insertOrder(order);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).insertOrder(order);
    }

    @Test
    public void testFetchAllOrders() throws Exception {
        // Arrange
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, "John Doe", "Product A", "pending"));
        orderList.add(new Order(2, "Jane Doe", "Product B", "confirmed"));

        when(dbManager.getAllOrders()).thenReturn(orderList);

        // Act
        List<Order> fetchedOrders = dbManager.getAllOrders();

        // Assert
        assertEquals(2, fetchedOrders.size());
        assertEquals("John Doe", fetchedOrders.get(0).getCustomerName());
        verify(dbManager, times(1)).getAllOrders();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        // Arrange
        Order updatedOrder = new Order(1, "John Doe", "Product A", "completed");
        when(dbManager.updateOrder(updatedOrder)).thenReturn(true);

        // Act
        boolean result = dbManager.updateOrder(updatedOrder);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).updateOrder(updatedOrder);
    }

    @Test
    public void testDeleteOrder() throws Exception {
        // Arrange
        int orderId = 1;
        when(dbManager.deleteOrder(orderId)).thenReturn(true);

        // Act
        boolean result = dbManager.deleteOrder(orderId);

        // Assert
        assertTrue(result);
        verify(dbManager, times(1)).deleteOrder(orderId);
    }
}
    
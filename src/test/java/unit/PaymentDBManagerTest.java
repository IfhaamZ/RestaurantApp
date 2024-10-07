// package unit;

// import DAO.DBManager;
// import model.Payment;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.junit.jupiter.api.extension.ExtendWith;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5 tests
// public class PaymentDBManagerTest {

//     @Mock
//     private DBManager dbManager; // Mock the DBManager

//     @BeforeEach
//     public void setup() {
//         // Setup can be used to initialize any variables if needed
//     }

// @Test
// public void testCreatePayment() throws Exception {
// // Arrange
// Payment payment = new Payment(
// null, "Card", "1234567812345678", "12", "2025", "123",
// "100.50", LocalDateTime.now().toString(), "customer");
// when(dbManager.createPayment(payment)).thenReturn(1); // Simulate successful creation returning paymentID = 1

// // Act
// int paymentID = dbManager.createPayment(payment);

// // Assert
// assertEquals(1, paymentID);
// verify(dbManager, times(1)).createPayment(payment);
// }

//     @Test
//     public void testCancelPayment() throws Exception {
//         // Arrange
//         int paymentID = 1;
//         when(dbManager.cancelPayment(paymentID)).thenReturn(true);

//         // Act
//         boolean result = dbManager.cancelPayment(paymentID);

//         // Assert
//         assertTrue(result);
//         verify(dbManager, times(1)).cancelPayment(paymentID);
//     }

//     @Test
//     public void testFetchAllPayments() throws Exception {
//         // Arrange
//         List<Payment> payments = new ArrayList<>();
//         Payment payment = new Payment(
//                 "1", "Card", "1234567812345678", "12", "2025", "123",
//                 "100.50", LocalDateTime.now().toString(), "customer");
//         payments.add(payment);

//         when(dbManager.fetchAllPayments()).thenReturn(payments);

//         // Act
//         List<Payment> fetchedPayments = dbManager.fetchAllPayments();

//         // Assert
//         assertNotNull(fetchedPayments);
//         assertEquals(1, fetchedPayments.size());
//         verify(dbManager, times(1)).fetchAllPayments();
//     }

//     @Test
//     public void testGetPaymentById() throws Exception {
//         // Arrange
//         int paymentID = 1;
//         Payment payment = new Payment(
//                 "1", "Card", "1234567812345678", "12", "2025", "123",
//                 "100.50", LocalDateTime.now().toString(), "customer");

//         when(dbManager.getPaymentById(paymentID)).thenReturn(payment);

//         // Act
//         Payment fetchedPayment = dbManager.getPaymentById(paymentID);

//         // Assert
//         assertNotNull(fetchedPayment);
//         assertEquals("Card", fetchedPayment.getMethod());
//         assertEquals("100.50", fetchedPayment.getPaymentAmount());
//         verify(dbManager, times(1)).getPaymentById(paymentID);
//     }
// }

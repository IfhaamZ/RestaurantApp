package unit;

import DAO.DBConnector;
import DAO.DBManager;
import model.Payment;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentDBManagerTest {

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
        // Clean the payments table before each test
        conn.createStatement().execute("DELETE FROM payments");
    }

    @Test
    public void testConnection() {
        assertNotNull(conn); // Ensure the connection is not null
    }

    // Test case for creating a payment
    @Test
    public void testCreatePayment() throws Exception {
        Payment payment = new Payment(null, "Card", "1234567890123456", "01", "25", "123", "100.50",
                LocalDateTime.now().toString(), "customer");
        int paymentID = dbManager.createPayment(payment);
        assertTrue(paymentID > 0);

        // Fetch the payment and check if it was inserted correctly
        Payment fetchedPayment = dbManager.getPaymentById(paymentID);
        assertNotNull(fetchedPayment);
        assertEquals("Card", fetchedPayment.getMethod());
        assertEquals("1234567890123456", fetchedPayment.getCardNumDecrypted()); // Check decrypted card number
        assertEquals("100.50", fetchedPayment.getPaymentAmount());
    }

    // Test case for cancelling a payment
    @Test
    public void testCancelPayment() throws Exception {
        // Create a payment
        Payment payment = new Payment(null, "Card", "1234567890123456", "01", "25", "123", "100.50",
                LocalDateTime.now().toString(), "customer");
        int paymentID = dbManager.createPayment(payment);

        // Cancel the payment
        boolean result = dbManager.cancelPayment(paymentID);
        assertTrue(result);

        // Fetch the cancelled payment
        Payment fetchedPayment = dbManager.getPaymentById(paymentID);
        assertNotNull(fetchedPayment);
        assertTrue(fetchedPayment.isCancelled());
    }

    // Test case for fetching all payments
    @Test
    public void testFetchAllPayments() throws Exception {
        // Create multiple payments
        dbManager.createPayment(new Payment(null, "Card", "1234567890123456", "01", "25", "123", "100.50",
                LocalDateTime.now().toString(), "customer"));
        dbManager.createPayment(new Payment(null, "Card", "6543210987654321", "02", "26", "456", "200.75",
                LocalDateTime.now().toString(), "customer"));

        // Fetch all payments
        List<Payment> payments = dbManager.fetchAllPayments();
        assertNotNull(payments);
        assertEquals(2, payments.size()); // Ensure there are 2 payments

        // Check details of the first payment
        Payment firstPayment = payments.get(0);
        assertEquals("100.50", firstPayment.getPaymentAmount());

        // Check details of the second payment
        Payment secondPayment = payments.get(1);
        assertEquals("200.75", secondPayment.getPaymentAmount());
    }
}

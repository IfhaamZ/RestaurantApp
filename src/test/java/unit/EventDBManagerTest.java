// package unit;

// import org.junit.jupiter.api.*;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.time.LocalDate;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// import model.Event;
// import DAO.DBConnector;
// import DAO.DBManager;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// public class EventDBManagerTest {

//     private Connection conn;
//     private DBManager dbManager;

//     @BeforeAll
//     public void setup() throws ClassNotFoundException, SQLException {
//         conn = DBConnector.getConnection();
//         dbManager = new DBManager(); // Adjust based on how DBManager is initialized
//     }

//     @AfterAll
//     public void tearDown() throws SQLException {
//         if (conn != null) {
//             conn.close();
//         }
//     }

//     @BeforeEach
//     public void beforeEachTest() throws SQLException {
//         conn.createStatement().execute("DELETE FROM Event"); // Clean the event table before each test
//     }

//     @Test
//     public void testConnection() {
//         assertNotNull(conn); // Ensure the connection is not null
//     }

//     @Test
//     public void testInsertEvent() throws SQLException {
//         Event event = new Event(0, "Music Night", "Enjoy live music", LocalDate.now(), "Music");
//         boolean result = dbManager.createEvent(event);
//         assertTrue(result);

//         List<Event> eventList = dbManager.fetchAllEvents();
//         assertEquals(1, eventList.size());
//         Event insertedEvent = eventList.get(0);
//         assertEquals("Music Night", insertedEvent.getName());
//         assertEquals("Enjoy live music", insertedEvent.getDescription());
//     }

//     @Test
//     public void testFetchAllEvents() throws SQLException {
//         Event event1 = new Event(0, "Music Night", "Enjoy live music", LocalDate.now(), "Music");
//         Event event2 = new Event(0, "Art Exhibition", "View various art pieces", LocalDate.now().plusDays(2), "Art");
//         dbManager.createEvent(event1);
//         dbManager.createEvent(event2);

//         List<Event> eventList = dbManager.fetchAllEvents();
//         assertEquals(2, eventList.size());

//         Event fetchedEvent = eventList.get(1);
//         assertEquals("Art Exhibition", fetchedEvent.getName());
//         assertEquals("View various art pieces", fetchedEvent.getDescription());
//     }

//     // @Test
//     // public void testUpdateEvent() throws SQLException {
//     //     Event event = new Event(0, "Music Night", "Enjoy live music", LocalDate.now(), "Music");
//     //     dbManager.createEvent(event);

//     //     Event updatedEvent = new Event(event.getId(), "Updated Music Night", "Updated Description",
//     //             LocalDate.now().plusDays(1), "Music");
//     //     boolean result = dbManager.updateEvent(updatedEvent);
//     //     assertTrue(result);

//     //     List<Event> eventList = dbManager.fetchAllEvents();
//     //     assertEquals(1, eventList.size());
//     //     Event fetchedEvent = eventList.get(0);
//     //     assertEquals("Updated Music Night", fetchedEvent.getName());
//     //     assertEquals("Updated Description", fetchedEvent.getDescription());
//     // }

//     // @Test
//     // public void testDeleteEvent() throws SQLException {
//     //     Event event = new Event(0, "Music Night", "Enjoy live music", LocalDate.now(), "Music");
//     //     dbManager.createEvent(event);

//     //     boolean result = dbManager.deleteEvent(event.getId());
//     //     assertTrue(result);

//     //     List<Event> eventList = dbManager.fetchAllEvents();
//     //     assertEquals(0, eventList.size());
//     // }

//     @Test
//     public void testSearchEvents() throws SQLException {
//         Event event1 = new Event(0, "Music Night", "Enjoy live music", LocalDate.now(), "Music");
//         Event event2 = new Event(0, "Art Exhibition", "View various art pieces", LocalDate.now().plusDays(2), "Art");
//         dbManager.createEvent(event1);
//         dbManager.createEvent(event2);

//         List<Event> result = dbManager.searchEvents("Art", LocalDate.now().plusDays(2).toString());
//         assertEquals(1, result.size());
//         Event foundEvent = result.get(0);
//         assertEquals("Art Exhibition", foundEvent.getName());
//     }
// }

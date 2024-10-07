// package unit;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// import model.Event;
// import DAO.DBManager;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) // Enable Mockito in JUnit 5 tests
// public class EventDBManagerTest {

// @Mock
// private DBManager dbManager; // Mock the DBManager

// @BeforeEach
// public void setup() {
// // Any setup logic can go here
// }

// @Test
// public void testInsertEvent() throws Exception {
// // Arrange
// Event event = new Event(0, "Music Night", "Enjoy live music",
// LocalDate.now(), "Music");
// when(dbManager.createEvent(event)).thenReturn(true); // Mock the createEvent
// call

// // Act
// boolean result = dbManager.createEvent(event);

// // Assert
// assertTrue(result); // Ensure the event creation returns true
// verify(dbManager, times(1)).createEvent(event); // Verify that createEvent
// was called once
// }

// @Test
// public void testFetchAllEvents() throws Exception {
// // Arrange
// List<Event> eventList = new ArrayList<>();
// eventList.add(new Event(0, "Music Night", "Enjoy live music",
// LocalDate.now(), "Music"));
// eventList.add(new Event(0, "Art Exhibition", "View various art pieces",
// LocalDate.now().plusDays(2), "Art"));

// when(dbManager.fetchAllEvents()).thenReturn(eventList); // Mock the
// fetchAllEvents call

// // Act
// List<Event> fetchedEvents = dbManager.fetchAllEvents();

// // Assert
// assertEquals(2, fetchedEvents.size()); // Ensure we fetched two events
// assertEquals("Music Night", fetchedEvents.get(0).getName());
// verify(dbManager, times(1)).fetchAllEvents(); // Verify that fetchAllEvents
// was called once
// }

// @Test
// public void testUpdateEvent() throws Exception {
// // Arrange
// Event updatedEvent = new Event(1, "Updated Music Night", "Updated
// Description", LocalDate.now().plusDays(1),
// "Music");
// when(dbManager.updateEvent(updatedEvent)).thenReturn(true); // Mock the
// updateEvent call

// // Act
// boolean result = dbManager.updateEvent(updatedEvent);

// // Assert
// assertTrue(result); // Ensure the event update returns true
// verify(dbManager, times(1)).updateEvent(updatedEvent); // Verify that
// updateEvent was called once
// }

// @Test
// public void testDeleteEvent() throws Exception {
// // Arrange
// int eventId = 1;
// when(dbManager.deleteEvent(eventId)).thenReturn(true); // Mock the
// deleteEvent call

// // Act
// boolean result = dbManager.deleteEvent(eventId);

// // Assert
// assertTrue(result); // Ensure the event deletion returns true
// verify(dbManager, times(1)).deleteEvent(eventId); // Verify that deleteEvent
// was called once
// }
// }

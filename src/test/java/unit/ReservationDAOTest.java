package unit;

import DAO.DBManager;
import model.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReservationDAOTest {
@Mock
private DBManager reservationDAO;

@BeforeEach
public void setup() {
}

@Test
public void testInsertReservation() throws SQLException {
Reservation reservation = new Reservation(0, "John Doe", LocalDate.now(),
LocalTime.of(18, 30), 4);
Mockito.when(reservationDAO.addReservation(reservation)).thenReturn(true);
boolean result = reservationDAO.addReservation(reservation);
Assertions.assertTrue(result);
Mockito.verify(reservationDAO, Mockito.times(1)).addReservation(reservation);
}

@Test
public void testFetchAllReservations() throws SQLException {
List<Reservation> reservationList = new ArrayList<>();
reservationList.add(new Reservation(0, "John Doe", LocalDate.now(),
LocalTime.of(18, 30), 4));
reservationList.add(new Reservation(1, "Jane Doe",
LocalDate.now().plusDays(1), LocalTime.of(19, 0), 2));

Mockito.when(reservationDAO.getAllReservations()).thenReturn(reservationList);
List<Reservation> fetchedReservations = reservationDAO.getAllReservations();
Assertions.assertEquals(2, fetchedReservations.size());
Assertions.assertEquals("John Doe", fetchedReservations.get(0).getName());
Mockito.verify(reservationDAO, Mockito.times(1)).getAllReservations();
}

@Test
public void testUpdateReservation() throws SQLException {
Reservation updatedReservation = new Reservation(1, "Updated Name",
LocalDate.now().plusDays(2),
LocalTime.of(20, 0), 5);
Mockito.when(reservationDAO.updateReservation(updatedReservation)).thenReturn(true);
boolean result = reservationDAO.updateReservation(updatedReservation);
Assertions.assertTrue(result);
Mockito.verify(reservationDAO,
Mockito.times(1)).updateReservation(updatedReservation);
}

@Test
public void testDeleteReservation() throws SQLException {
int reservationId = 1;
String reservationName = "John Doe";
Mockito.doNothing().when(reservationDAO).deleteReservation(reservationId,
reservationName);
reservationDAO.deleteReservation(reservationId, reservationName);
Mockito.verify(reservationDAO,
Mockito.times(1)).deleteReservation(reservationId, reservationName);
}
}

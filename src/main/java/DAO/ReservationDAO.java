package DAO;

import model.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

public class ReservationDAO {

    // 예약 추가 메서드
    public boolean addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (name, reservation_date, reservation_time, guests) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            // LocalDate와 LocalTime을 MySQL의 Date와 Time으로 변환
            st.setString(1, reservation.getName());
            st.setDate(2, Date.valueOf(reservation.getDate()));  // LocalDate -> java.sql.Date
            st.setTime(3, Time.valueOf(reservation.getTime()));  // LocalTime -> java.sql.Time
            st.setInt(4, reservation.getGuests());

            return st.executeUpdate() > 0;
        }
    }
}

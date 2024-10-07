package DAO;

import model.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


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

    // 예약 삭제 메서드
    public void deleteReservation(int reservationId, String reservationName) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id = ? AND name = ?";
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, reservationId);
            preparedStatement.setString(2, reservationName);
            preparedStatement.executeUpdate();
        }
    }

    // 예약 업데이트 메서드
    public boolean updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservations SET name = ?, reservation_date = ?, reservation_time = ?, guests = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, reservation.getName());
            st.setDate(2, Date.valueOf(reservation.getDate()));
            st.setTime(3, Time.valueOf(reservation.getTime()));
            st.setInt(4, reservation.getGuests());
            st.setInt(5, reservation.getId());

            

            return st.executeUpdate() > 0;
        }
    }

     // 모든 예약 데이터를 가져오는 메서드 추가
    public List<Reservation> getAllReservations() throws SQLException {
        String sql = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate date = rs.getDate("reservation_date").toLocalDate();
                LocalTime time = rs.getTime("reservation_time").toLocalTime();
                int guests = rs.getInt("guests");

                // Reservation 객체 생성 및 리스트에 추가
                reservations.add(new Reservation(id, name, date, time, guests));
            }
        }

        return reservations;
    }
}

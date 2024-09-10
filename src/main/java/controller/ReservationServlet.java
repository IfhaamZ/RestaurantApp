package controller;

import DAO.ReservationDAO;
import model.Reservation;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationServlet extends HttpServlet {
    private ReservationDAO reservationDAO;

    public void init() {
        reservationDAO = new ReservationDAO();
    }

    // POST 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            insertReservation(request, response);  // 예약 삽입 메서드 호출
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // 예약 데이터를 데이터베이스에 저장하는 메서드
    private void insertReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        int guests = Integer.parseInt(request.getParameter("guests"));

        // Reservation 객체 생성 및 DB 저장
        Reservation newReservation = new Reservation(0, name, date, time, guests);
        reservationDAO.addReservation(newReservation);  // DAO를 통해 데이터베이스에 저장

        // 예약 완료 후, 확인 페이지로 리다이렉트
        response.sendRedirect("ViewReservation.jsp");
    }
}

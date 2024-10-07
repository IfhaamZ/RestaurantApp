package controller;

import DAO.ReservationDAO;
import model.Reservation;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationServlet extends HttpServlet {
    private ReservationDAO reservationDAO;

    public void init() {
        reservationDAO = new ReservationDAO();
    }

      // GET 요청 처리 (ReservationServlet?action=list에 대한 GET 요청 처리)
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  String action = request.getParameter("action");
  try {
      switch (action) {
          case "list":
              listReservations(request, response);
              break;
          default:
              response.sendRedirect("error.jsp");
              break;
      } 
  }   catch (SQLException e) {
      throw new ServletException(e);
  }
}

    // POST 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "insert":
                    insertReservation(request, response);
                    break;
                case "update":
                    updateReservation(request, response);
                    break;
                case "delete":
                    deleteReservation(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            } 
        }   catch (SQLException e) {
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
        response.sendRedirect("ReservationConfirm.jsp");
    }

    // 예약 데이터 업데이트 메서드
    private void updateReservation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // 폼 데이터에서 값 가져오기
        int id = Integer.parseInt(request.getParameter("reservationId"));
        String name = request.getParameter("name");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));
        int guests = Integer.parseInt(request.getParameter("guests"));

        // Reservation 객체 생성
        Reservation reservation = new Reservation(id, name, date, time, guests);

        // DAO를 통해 업데이트
        boolean updated = reservationDAO.updateReservation(reservation);

        // 업데이트 결과에 따른 페이지 이동
        if (updated) {
            response.sendRedirect("UpdateConfirm.jsp");  // 성공 시 업데이트 확인 페이지로 리다이렉트
        } else {
            response.sendRedirect("error.jsp");  // 실패 시 에러 페이지로 이동
        }
    }

     // 예약 삭제 메서드
     private void deleteReservation(HttpServletRequest request, HttpServletResponse response)
     throws SQLException, IOException, ServletException {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
         String reservationName = request.getParameter("reservationName");

        // DAO를 통해 예약 삭제
        reservationDAO.deleteReservation(reservationId, reservationName);

        // 삭제 완료 후, 확인 페이지로 리다이렉트
        response.sendRedirect("CancelConfirm.jsp");
    }

     // 모든 예약 데이터를 가져와서 JSP 페이지로 전달하는 메서드
    private void listReservations(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // DAO를 통해 모든 예약 리스트를 가져옴
        List<Reservation> listReservations = reservationDAO.getAllReservations();

        // 예약 리스트를 request 객체에 속성으로 추가
        request.setAttribute("listReservations", listReservations);

        // ViewReservation.jsp 페이지로 요청을 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewReservation.jsp");
        dispatcher.forward(request, response);
    }
}

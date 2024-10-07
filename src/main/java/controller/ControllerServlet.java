package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet {
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/reservation":
                // 예약 관련 처리
                handleReservationRequests(request, response);
                break;
            default:
                // 기본 페이지
                showDefaultPage(request, response);
                break;
        }
    }

    private void handleReservationRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 전달할 action 값을 설정
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";  // 기본값 설정 (필요에 따라 수정)
        }

        // `action` 파라미터를 `ReservationServlet`으로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReservationServlet?action=" + action);
        dispatcher.forward(request, response);
    }

    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}

package controller;

import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/orderlist", "/ordernew", "/ordersave"})  // 경로 수정
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Order> orders;

    public void init() {
        orders = new ArrayList<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/ordernew":
                    showNewOrderForm(request, response);
                    break;
                case "/ordersave":
                    saveOrder(request, response);
                    break;
                case "/orderlist":
                    listOrders(request, response);
                    break;
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/orderList.jsp");  // JSP 파일 경로 수정
        dispatcher.forward(request, response);
    }

    private void showNewOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/orderForm.jsp");  // JSP 파일 경로 수정
        dispatcher.forward(request, response);
    }

    private void saveOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String customerName = request.getParameter("customerName");
        String dishName = request.getParameter("dishName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Order order = new Order(orders.size() + 1, customerName, dishName, quantity, new java.util.Date());
        orders.add(order);
        response.sendRedirect("orderlist");
    }
}

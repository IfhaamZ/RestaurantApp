package controller;

import DAO.DBManager;
import model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private DBManager dbManager;

    public void init() throws ServletException {
        try {
            dbManager = new DBManager();
        } catch (Exception e) {
            throw new ServletException(e);
        }
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
                // Customer Actions
                case "/placeorder":
                    showOrderForm(request, response);
                    break;
                case "/insertorder":
                    insertOrder(request, response);
                    break;
                case "/confirmorder":
                    showOrderConfirmation(request, response);
                    break;

                // Staff Actions
                case "/stafforders":
                    listOrders(request, response);
                    break;
                case "/staffeditorder":
                    showEditOrderForm(request, response);
                    break;
                case "/staffupdateorder":
                    updateOrder(request, response);
                    break;
                case "/staffdeleteorder":
                    deleteOrder(request, response);
                    break;

                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Customer View: Show order form to customers
    private void showOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderForm.jsp");
        dispatcher.forward(request, response);
    }

    // Customer View: Insert order placed by customer
    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String customerName = request.getParameter("customerName");
        String orderDetails = request.getParameter("orderDetails");
        String status = "pending"; // Default status for new orders

        Order newOrder = new Order(0, customerName, orderDetails, status);
        dbManager.insertOrder(newOrder);
        response.sendRedirect("confirmorder");
    }

    // Customer View: Show order confirmation
    private void showOrderConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderConfirmation.jsp");
        dispatcher.forward(request, response);
    }

    // Staff View: List all orders for staff
    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> orders = dbManager.getAllOrders();
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("staffOrderList.jsp");
        dispatcher.forward(request, response);
    }

    // Staff View: Show form to edit order
    private void showEditOrderForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order existingOrder = dbManager.getOrderById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editOrder.jsp");
        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);
    }

    // Staff View: Update order
    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String orderDetails = request.getParameter("orderDetails");
        String status = request.getParameter("status");

        Order updatedOrder = new Order(id, "", orderDetails, status); // Customer name remains unchanged
        dbManager.updateOrder(updatedOrder);
        response.sendRedirect("stafforders");
    }

    // Staff View: Delete order
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dbManager.deleteOrder(id);
        response.sendRedirect("stafforders");
    }
}

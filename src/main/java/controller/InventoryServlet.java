package controller;

import model.Inventory;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private Inventory inventory;

    // Initialize the Inventory object
    public void init() throws ServletException {
        inventory = new Inventory();
        // Add some sample products for testing
        inventory.addProduct(new Product("P001", "Apple", 50));
        inventory.addProduct(new Product("P002", "Orange", 30));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String role = request.getParameter("role"); // Get role from request

        if (action == null) {
            action = "view"; // Default action
        }

        try {
            switch (action) {
                case "view":
                    viewStockLevels(request, response, role);
                    break;
                case "update":
                    // if ("Manager".equals(role)) {
                    updateStockLevel(request, response, role);
                    // } else {
                    // request.setAttribute("message", "Access Denied: Only managers can update
                    // stock levels.");
                    // viewStockLevels(request, response, role);
                    // }
                    break;
                case "lowStockNotification":
                    // if ("Manager".equals(role)) {
                    showLowStockNotification(request, response);
                    // } else {
                    // request.setAttribute("message",
                    // "Access Denied: Only managers can view low stock notifications.");
                    // viewStockLevels(request, response, role);
                    // }
                    break;
                default:
                    viewStockLevels(request, response, role);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // U136 - View and monitor stock levels
    private void viewStockLevels(HttpServletRequest request, HttpServletResponse response, String role)
            throws ServletException, IOException {
        Map<String, Integer> stockLevels = inventory.viewStockLevels();
        request.setAttribute("stockLevels", stockLevels);
        request.setAttribute("role", role); // Pass the role to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewStockLevels.jsp");
        dispatcher.forward(request, response);
    }

    // U139 - Update stock levels manually (only for Managers)
    private void updateStockLevel(HttpServletRequest request, HttpServletResponse response, String role)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int newStock = Integer.parseInt(request.getParameter("newStock"));

        boolean isUpdated = inventory.updateStockLevel(productID, newStock);
        if (isUpdated) {
            request.setAttribute("message", "Stock updated successfully by " + role + ".");
        } else {
            request.setAttribute("message", "Failed to update stock. Invalid product ID or stock quantity.");
        }

        viewStockLevels(request, response, role);
    }

    // U137 - Receive a stock level notification (only for Managers)
    private void showLowStockNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lowStockMessage = inventory.checkLowStockLevels();
        request.setAttribute("lowStockMessage", lowStockMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lowStockNotification.jsp");
        dispatcher.forward(request, response);
    }
}

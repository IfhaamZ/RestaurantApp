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

    // Initialize the Inventory object and load products from the database
    public void init() throws ServletException {
        inventory = new Inventory();

        // Retrieve products from the database and add them to the in-memory list
        try {
            inventory.loadProductsFromDB(); // Load products from DB into memory
            System.out.println("Products loaded from database.");
        } catch (Exception e) {
            throw new ServletException("Failed to load products from the database", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String role = request.getParameter("role"); // Get role from request
        String updatedBy = request.getParameter("updatedBy"); // Get the updatedBy parameter (user performing the
                                                              // action)

        if (action == null) {
            action = "view"; // Default action
        }

        try {
            switch (action) {
                case "view":
                    viewStockLevels(request, response, role);
                    break;
                case "update":
                    updateStockLevel(request, response, role, updatedBy);
                    break;
                case "lowStockNotification":
                    showLowStockNotification(request, response);
                    break;
                case "viewStockDetail":
                    viewStockDetail(request, response);
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

    // View stock details for all products
    private void viewStockDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Integer> stockDetails = inventory.viewStockLevels();
        request.setAttribute("stockDetails", stockDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewStockDetail.jsp");
        dispatcher.forward(request, response);
    }

    // U139 - Update stock levels manually (only for Managers)
    private void updateStockLevel(HttpServletRequest request, HttpServletResponse response, String role,
            String updatedBy) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int newStock = Integer.parseInt(request.getParameter("newStock"));

        boolean isUpdated = inventory.updateStockLevel(productID, newStock, updatedBy);
        if (isUpdated) {
            request.setAttribute("message", "Stock updated successfully by " + updatedBy + ".");
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

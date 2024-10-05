package controller;

import DAO.DBManager;
import model.Inventory;
import model.InventoryAudit;
import model.Product;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class InventoryServlet extends HttpServlet {
    private Inventory inventory;

    public void init() throws ServletException {
        inventory = new Inventory();
        try {
            inventory.loadProductsFromDB(); // Load products from DB into memory
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
        String action = request.getServletPath(); // Using request.getPathInfo() to extract action from the URL path
        
        // Get the logged-in user from the session
    HttpSession session = request.getSession();
    User loggedInUser = (User) session.getAttribute("user");

    if (loggedInUser == null) {
        response.sendRedirect("login.jsp"); // Redirect to login if not logged in
        return;
    }

    String role = loggedInUser.getRole(); // Get the role of the logged-in user
    String updatedBy = loggedInUser.getName(); // Get the name of the user who is performing the action

        if (action == null || action.equals("/")) {
            action = "/inventoryview"; // Default action
        }

        try {
            switch (action) {
                case "/inventoryview":
                    viewStockLevels(request, response, role);
                    break;
                case "/inventoryupdate":
                    updateStockLevel(request, response, role, updatedBy);
                    break;
                case "/inventorylowStockNotification":
                    showLowStockNotification(request, response);
                    break;
                case "/inventoryviewStockDetail":
                    viewStockDetail(request, response);
                    break;
                case "/inventoryviewAuditTrail":
                    viewInventoryAuditTrail(request, response);
                    break;
                default:
                    viewStockLevels(request, response, role);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
    }

    // U136 - View and monitor stock levels
    private void viewStockLevels(HttpServletRequest request, HttpServletResponse response, String role)
            throws ServletException, IOException {
        Map<String, Integer> stockLevels = inventory.viewStockLevels();
        request.setAttribute("stockLevels", stockLevels);
        request.setAttribute("role", role);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewStockLevels.jsp");
        dispatcher.forward(request, response);
    }

    private void viewStockDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Product> stockDetails = inventory.viewStockLevelsWithProductID();
        request.setAttribute("stockDetails", stockDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewStockDetail.jsp");
        dispatcher.forward(request, response);
    }

    // U139 - Update stock levels manually (only for Managers)
    private void updateStockLevel(HttpServletRequest request, HttpServletResponse response, String role,
            String updatedBy) throws ServletException, IOException {
        // Check if the logged-in user is allowed to update the stock (only staff)
        if (!"staff".equals(role)) {
            request.setAttribute("message", "You do not have permission to update the stock.");
            viewStockLevels(request, response, role);
            return;
        }

        String productID = request.getParameter("productID");
        int newStock;
        try {
            newStock = Integer.parseInt(request.getParameter("newStock"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid stock quantity.");
            viewStockLevels(request, response, role);
            return;
        }

        boolean isUpdated = inventory.updateStockLevel(productID, newStock, updatedBy);
        if (isUpdated) {
            request.setAttribute("message", "Stock updated successfully by " + updatedBy + ".");
        } else {
            request.setAttribute("message", "Failed to update stock.");
        }
        viewStockLevels(request, response, role);
    }

    private void showLowStockNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lowStockMessage = inventory.checkLowStockLevels();
        request.setAttribute("lowStockMessage", lowStockMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lowStockNotification.jsp");
        dispatcher.forward(request, response);
    }

    private void viewInventoryAuditTrail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        if (productID == null || productID.isEmpty()) {
            request.setAttribute("error", "Product ID is missing.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewStockLevels.jsp");
            dispatcher.forward(request, response);
            return;
        }

        DBManager dbManager = new DBManager();
        try {
            List<InventoryAudit> auditList = dbManager.getInventoryAuditByProduct(productID);
            request.setAttribute("auditList", auditList);
            request.setAttribute("productID", productID);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/inventoryAudit.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching audit data.");
        }
    }
}

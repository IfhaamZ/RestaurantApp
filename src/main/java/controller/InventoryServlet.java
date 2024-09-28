package controller;

import DAO.DBManager;
import model.Inventory;
import model.InventoryAudit;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
            System.out.println("Products loaded from database: " + inventory.viewStockLevels());
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

        // Logging action and role for debugging
        System.out.println("Action: " + action + ", Role: " + role);

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
                case "viewAuditTrail":
                    viewInventoryAuditTrail(request, response); // New action for S126
                    break;
                default:
                    viewStockLevels(request, response, role);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the full stack trace if there is an exception
            throw new ServletException(ex);
        }
    }

    // U136 - View and monitor stock levels
    private void viewStockLevels(HttpServletRequest request, HttpServletResponse response, String role)
            throws ServletException, IOException {
        // Log fetching of stock levels
        Map<String, Integer> stockLevels = inventory.viewStockLevels();
        System.out.println("Stock levels fetched: " + stockLevels); // Log to check the data

        // Log if the stockLevels map is empty or null
        if (stockLevels == null || stockLevels.isEmpty()) {
            System.out.println("No stock levels found.");
        } else {
            System.out.println("Stock levels found: " + stockLevels.size() + " items.");
        }

        // Log the role
        System.out.println("User role: " + role);

        // Set attributes for the JSP
        request.setAttribute("stockLevels", stockLevels);
        request.setAttribute("role", role); // Pass the role to the JSP

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewStockLevels.jsp");
        dispatcher.forward(request, response);
    }

    // View stock details for all products
    private void viewStockDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Log fetching of stock details
        Map<String, Product> stockDetails = inventory.viewStockLevelsWithProductID();
        System.out.println("Stock details fetched: " + stockDetails);

        // Set stock details in request attribute
        request.setAttribute("stockDetails", stockDetails);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewStockDetail.jsp");
        dispatcher.forward(request, response);
    }

    // U139 - Update stock levels manually (only for Managers)
    private void updateStockLevel(HttpServletRequest request, HttpServletResponse response, String role,
            String updatedBy) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int newStock;

        try {
            newStock = Integer.parseInt(request.getParameter("newStock"));
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock input: " + request.getParameter("newStock"));
            request.setAttribute("message", "Invalid stock quantity.");
            viewStockLevels(request, response, role);
            return;
        }

        // Log the update attempt
        System.out.println(
                "Attempting to update product ID: " + productID + " with new stock: " + newStock + " by " + updatedBy);

        boolean isUpdated = inventory.updateStockLevel(productID, newStock, updatedBy);

        if (isUpdated) {
            System.out.println("Stock updated successfully by " + updatedBy);
            request.setAttribute("message", "Stock updated successfully by " + updatedBy + ".");
        } else {
            System.out.println("Failed to update stock. Invalid product ID or stock quantity.");
            request.setAttribute("message", "Failed to update stock. Invalid product ID or stock quantity.");
        }

        viewStockLevels(request, response, role);
    }

    // U137 - Receive a stock level notification (only for Managers)
    private void showLowStockNotification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lowStockMessage = inventory.checkLowStockLevels();
        System.out.println("Low stock notification: " + lowStockMessage);

        request.setAttribute("lowStockMessage", lowStockMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("lowStockNotification.jsp");
        dispatcher.forward(request, response);
    }

    // S126 - View Inventory Audit Trail (Show Inventory Usage Trend Data)
    private void viewInventoryAuditTrail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the product ID from the request parameter
        String productID = request.getParameter("productID");

        // Log the product ID to ensure it's being captured
        System.out.println("Viewing audit trail for product ID: " + productID);

        // Ensure the productID is not null or empty
        if (productID == null || productID.isEmpty()) {
            request.setAttribute("error", "Product ID is missing.");
            // Redirect or forward back to the inventory page or an error page
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewStockLevels.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Create an instance of DBManager to interact with the database
        DBManager dbManager = new DBManager();
        try {
            // Fetch the inventory audit data for the specific product
            List<InventoryAudit> auditList = dbManager.getInventoryAuditByProduct(productID);

            // Log the audit data fetched for debugging
            System.out.println("Audit trail for product ID " + productID + ": " + auditList);

            // Debugging log for individual audit entries
            for (InventoryAudit audit : auditList) {
                System.out.println("Old Stock: " + audit.getOldStock() + ", New Stock: " + audit.getNewStock()
                        + ", Timestamp: " + audit.getTimestamp() + ", Updated By: " + audit.getUpdatedBy());
            }

            // Check if audit data was returned
            if (auditList == null || auditList.isEmpty()) {
                request.setAttribute("message", "No audit trail found for product ID: " + productID);
            } else {
                // Set audit list and product ID as request attributes to be used in the JSP
                request.setAttribute("auditList", auditList);
                request.setAttribute("productID", productID);
            }

            // Forward the request to the JSP page to display the audit trail
            RequestDispatcher dispatcher = request.getRequestDispatcher("inventoryAudit.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            // Log any SQL exceptions
            e.printStackTrace();
            // Send an internal server error response with a message
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching audit data.");
        }
    }

}

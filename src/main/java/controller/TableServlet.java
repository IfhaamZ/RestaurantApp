package controller;

import DAO.DBManager;
import model.Table;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TableServlet extends HttpServlet {
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
                case "/tablenew":
                    showNewForm(request, response);
                    break;
                case "/tableavailability":
                    showTableAvailability(request, response);
                    break;
                case "/tableedit":
                    showEditForm(request, response);
                    break;
                case "/tableinsert":
                    insertTable(request, response);
                    break;
                case "/tabledelete":
                    deleteTable(request, response);
                    break;
                case "/tableupdate":
                    updateTable(request, response);
                    break;
                case "/tableReserve":
                    reserveTable(request, response);
                    break;
                default:
                    listTables(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // List all tables
    private void listTables(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Table> listTables = dbManager.fetchAllTables();
        request.setAttribute("tables", listTables);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableManagement.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for creating a new table
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableForm.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for editing an existing table
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("tableId"));
        Table existingTable = dbManager.getTableById(id);
        request.setAttribute("table", existingTable);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableForm.jsp");
        dispatcher.forward(request, response);
    }

    // Insert a new table
    private void insertTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String status = request.getParameter("status");

        Table newTable = new Table(0, status, capacity); // ID 0 for new tables
        dbManager.createTable(newTable);
        response.sendRedirect("tablelist");
    }

    // Update an existing table
    private void updateTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("tableId"));
        String status = request.getParameter("status");

        dbManager.updateTableStatus(id, status);
        response.sendRedirect("tablelist");
    }

    // Delete a table
    private void deleteTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("tableId"));
        dbManager.deleteTable(id);
        response.sendRedirect("tablelist");
    }

    // Reserve a table
    private void reserveTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            // Get the table ID from the request
            int tableId = Integer.parseInt(request.getParameter("tableId"));

            // Get the reservation time from the request
            String reservationTimeStr = request.getParameter("reservationTime");

            // Parse the reservation time string to a SQL DateTime format if provided
            Timestamp reservationTime = null;
            if (reservationTimeStr != null && !reservationTimeStr.isEmpty()) {
                reservationTime = Timestamp.valueOf(reservationTimeStr + ":00"); // Appending seconds if not included
            } else {
                // Handle case if reservation time is not provided
                request.setAttribute("error", "Please provide a valid reservation time.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("TableForm.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Fetch the current table status to ensure it's available before reserving
            Table existingTable = dbManager.getTableById(tableId);
            if (existingTable == null) {
                // Handle case where table is not found
                request.setAttribute("error", "Table not found.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("TableManagement.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Check if the table is already reserved
            if ("Reserved".equals(existingTable.getStatus())) {
                request.setAttribute("error", "Table is already reserved.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("TableManagement.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Update the table status to 'Reserved' and set the reservation time
            boolean updateSuccess = dbManager.reserveTable(tableId, reservationTime);
            if (updateSuccess) {
                request.setAttribute("message", "Table reserved successfully.");
            } else {
                request.setAttribute("error", "Failed to reserve the table.");
            }

            // Redirect to the table list page
            response.sendRedirect("tablelist");
        } catch (NumberFormatException e) {
            // Handle invalid table ID format
            request.setAttribute("error", "Invalid table ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("TableManagement.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            // Handle invalid reservation time format
            request.setAttribute("error", "Invalid reservation time format. Please use YYYY-MM-DD HH:MM.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("TableForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showTableAvailability(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Table> availableTables = dbManager.fetchAvailableTables(); // Example method to fetch available tables
        request.setAttribute("tables", availableTables);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableAvailability.jsp"); 
        dispatcher.forward(request, response);
    }
}

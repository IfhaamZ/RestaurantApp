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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                case "/tablereserveform":
                    showReservationForm(request, response);
                    break;
                case "/tablereserve":
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

        // Creating a new table without reservation details
        Table newTable = new Table(0, status, capacity, null, null, null, null);
        dbManager.createTable(newTable);
        response.sendRedirect("tablelist");
    }

    // Update an existing table
    private void updateTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("tableId"));
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String status = request.getParameter("status");

        // Update table without modifying reservation details
        Table updatedTable = new Table(id, status, capacity, null, null, null, null);
        dbManager.updateTable(updatedTable);
        response.sendRedirect("tablelist");
    }

    // Delete a table
    private void deleteTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("tableId"));
        dbManager.deleteTable(id);
        response.sendRedirect("tablelist");
    }

    // Show reservation form
    private void showReservationForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int tableId = Integer.parseInt(request.getParameter("tableId"));
        Table table = dbManager.getTableById(tableId);
        request.setAttribute("table", table);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableReservationForm.jsp");
        dispatcher.forward(request, response);
    }

    // Reserve a table
    private void reserveTable(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int tableId = Integer.parseInt(request.getParameter("tableId"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String reservationTimeString = request.getParameter("reservationTime");

        // Parse the reservation time from the user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime reservationTime = LocalDateTime.parse(reservationTimeString, formatter);

        // Update the table details with reservation info
        dbManager.reserveTable(tableId, reservationTime, name, phone, email);
        response.sendRedirect("index.jsp");
    }

    private void showTableAvailability(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Table> availableTables = dbManager.fetchAvailableTables();
        request.setAttribute("tables", availableTables);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TableAvailability.jsp");
        dispatcher.forward(request, response);
    }
}

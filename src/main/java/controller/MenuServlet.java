package controller;

import DAO.DBManager;
import model.MenuItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MenuServlet extends HttpServlet {
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
                case "/menuform":
                    showMenuForm(request, response);
                    break;
                case "/menuinsert":
                    insertMenuItem(request, response);
                    break;
                case "/menuedit":
                    showEditForm(request, response);
                    break;
                case "/menuupdate":
                    updateMenuItem(request, response);
                    break;
                case "/menudelete":
                    deleteMenuItem(request, response);
                    break;
                case "/menusearch":
                    searchMenuByCategory(request, response);
                    break;
                case "/staffmenusearch":
                    searchStaffMenuByCategory(request, response);
                    break;
                case "/staffMenuDisplay":
                    listStaffMenuItems(request, response);
                    break;
                default:
                    listMenuItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Display all menu items for staff
    private void listMenuItems(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        List<String> categories = dbManager.fetchAllCategories();
        request.setAttribute("menuItems", menuItems);
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayMenu.jsp");
        dispatcher.forward(request, response);
    }

    // Display all menu items for staff
    private void listStaffMenuItems(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<MenuItem> menuItems = dbManager.fetchAllMenuItems();
        List<String> categories = dbManager.fetchAllCategories(); // Fetch categories
        request.setAttribute("menuItems", menuItems);
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayStaffMenu.jsp");
        dispatcher.forward(request, response);
    }

    // Search menu items by category
    private void searchMenuByCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String category = request.getParameter("category");

        // Fetch the menu items based on the selected category
        List<MenuItem> menuItems;
        if (category == null || category.isEmpty() || category.equals("Select Category")) {
            // Fetch all menu items if no category is selected
            menuItems = dbManager.fetchAllMenuItems();
        } else {
            // Fetch items based on the selected category
            menuItems = dbManager.fetchMenuItemsByCategory(category);
        }

        List<String> categories = dbManager.fetchAllCategories(); // Fetch categories
        request.setAttribute("menuItems", menuItems);
        request.setAttribute("categories", categories); // Set categories attribute
        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayMenu.jsp");
        dispatcher.forward(request, response);
    }

    // Search Menu for Staff
    private void searchStaffMenuByCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String category = request.getParameter("category");

        // Fetch the menu items based on the selected category
        List<MenuItem> menuItems;
        if (category == null || category.isEmpty()) {
            // Fetch all menu items if no category is selected
            menuItems = dbManager.fetchAllMenuItems();
        } else {
            // Fetch items based on the selected category
            menuItems = dbManager.fetchMenuItemsByCategory(category);
        }

        // Fetch categories
        List<String> categories = dbManager.fetchAllCategories();
        // Debugging statement
        System.out.println("Fetched Categories: " + categories);

        request.setAttribute("menuItems", menuItems);
        request.setAttribute("categories", categories); // Pass categories to JSP

        RequestDispatcher dispatcher = request.getRequestDispatcher("DisplayStaffMenu.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for adding new or editing existing menu item
    private void showMenuForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuForm.jsp");
        dispatcher.forward(request, response);
    }

    // Insert a new menu item
    private void insertMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");

        MenuItem newItem = new MenuItem(0, name, description, price, category);
        dbManager.insertMenuItem(newItem);
        response.sendRedirect("menulist");
    }

    // Show form for editing a menu item
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("menuId"));
        MenuItem existingItem = dbManager.getMenuItemById(id);
        request.setAttribute("menuItem", existingItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuForm.jsp");
        dispatcher.forward(request, response);
    }

    // Update an existing menu item
    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");

        MenuItem updatedItem = new MenuItem(id, name, description, price, category);
        dbManager.updateMenuItem(updatedItem);
        response.sendRedirect("menulist");
    }

    // Delete a menu item
    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("menuId"));
        dbManager.deleteMenuItem(id);
        response.sendRedirect("menulist");
    }
}

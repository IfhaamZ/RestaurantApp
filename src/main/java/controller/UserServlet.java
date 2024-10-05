package controller;

import DAO.DBManager;
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

public class UserServlet extends HttpServlet {
    private DBManager dbManager;

    // Initialize the servlet
    public void init() throws ServletException {
        try {
            dbManager = new DBManager(); // DBManager initialized
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Handle POST requests by forwarding to doGet
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/userlogin":
                    loginUser(request, response);
                    break;
                case "/userlogout":
                    logoutUser(request, response);
                    break;
                case "/userregister":
                    registerUser(request, response);
                    break;

                case "/adminadduser":
                    showNewUserForm(request, response);
                    break;
                case "/admininsertuser":
                    insertUser(request, response);
                    break;
                case "/adminedituser":
                    showEditUserForm(request, response);
                    break;
                case "/adminupdateuser":
                    updateUser(request, response);
                    break;
                case "/admindeleteuser":
                    deleteUser(request, response);
                    break;
                case "/adminlistuser":
                    listUsers(request, response);
                    break;
                default:
                    response.sendRedirect("login.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = dbManager.validateUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store the user object in the session

            // Redirect based on the user's role
            if ("customer".equals(user.getRole())) {
                response.sendRedirect("index.jsp");
            } else if ("staff".equals(user.getRole())) {
                response.sendRedirect("staffIndex.jsp");
            } 
            else if ("admin".equals(user.getRole())) {
                response.sendRedirect("adminIndex.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    // Logout user
    private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

    // Register a new user
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Get role from form

        User newUser = new User(0, name, email, password, role); // Add role
        boolean isExistingUser = dbManager.checkIfUserExists(email);

        if (isExistingUser) {
            response.getWriter().println("<script>alert('Email already exists.');location='register.jsp';</script>");
        } else {
            boolean isRegistered = dbManager.registerUser(newUser);
            if (isRegistered) {
                response.sendRedirect("registration_success.jsp");
            } else {
                response.getWriter()
                        .println("<script>alert('Registration failed. Try again.');location='register.jsp';</script>");
            }
        }
    }

    // Show form to create a new user
    private void showNewUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
        dispatcher.forward(request, response);
    }

    // Insert a new user (admin functionality)
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); // Get role from form

        User newUser = new User(0, name, email, password, role); // Add role
        dbManager.registerUser(newUser);
        response.sendRedirect("adminlistuser");
    }

    // Show form to edit an existing user
    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = dbManager.getUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userForm.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    // Update an existing user in the UserServlet
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Retrieve the user ID from the form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        // Fetch the current user to retain the password if it's not updated
        User currentUser = dbManager.getUserById(id);

        // If the password field is empty, retain the current password
        if (password == null || password.isEmpty()) {
            password = currentUser.getPassword();
        }

        User updatedUser = new User(id, name, email, password, role); // Add role
        dbManager.updateUser(updatedUser);
        response.sendRedirect("adminlistuser");
    }

    // Delete a user
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dbManager.deleteUser(id);
        response.sendRedirect("adminlistuser");
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> users = dbManager.getAllUsers();
        request.setAttribute("users", users); // Attach the users list to the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request, response);
    }

}
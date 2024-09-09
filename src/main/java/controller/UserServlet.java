package controller;

import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/userlist", "/usernew", "/usersave"})  // 경로 수정
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<User> users;

    public void init() {
        users = new ArrayList<>();
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
                case "/usernew":
                    showNewUserForm(request, response);
                    break;
                case "/usersave":
                    saveUser(request, response);
                    break;
                case "/userlist":
                    listUsers(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userList.jsp");  // JSP 파일 경로 수정
        dispatcher.forward(request, response);
    }

    private void showNewUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userForm.jsp");  // JSP 파일 경로 수정
        dispatcher.forward(request, response);
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(users.size() + 1, username, password);
        users.add(user);
        response.sendRedirect("userlist");
    }
}
package ua.advanced.servlets;

import ua.advanced.domain.User;
import ua.advanced.service.UserService;
import ua.advanced.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.readByEmail(email);

        if(user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if(user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userEmail", email);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

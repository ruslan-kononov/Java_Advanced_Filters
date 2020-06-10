package ua.advanced.servlets;

import com.google.gson.Gson;
import ua.advanced.domain.User;
import ua.advanced.dto.UserLogin;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.readByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("userId",user.getId());
            httpSession.setAttribute("role",user.getRole());

            UserLogin userLogin = new UserLogin();
            userLogin.destinationURL = "cabinet.jsp";
            userLogin.userEmail = user.getEmail();
            String json = new Gson().toJson(userLogin);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}

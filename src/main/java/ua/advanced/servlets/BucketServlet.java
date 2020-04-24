package ua.advanced.servlets;

import ua.advanced.domain.Bucket;
import ua.advanced.service.BucketService;
import ua.advanced.service.impl.BucketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {
    BucketService bucketService = BucketServiceImpl.getBucketService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("userId");

        bucketService.create(new Bucket(userId,Integer.parseInt(productId),new Date()));

        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

}

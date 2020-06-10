package ua.advanced.servlets;

import com.google.gson.Gson;
import ua.advanced.domain.Bucket;
import ua.advanced.dto.UserOrder;
import ua.advanced.service.BucketService;
import ua.advanced.service.impl.BucketServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {
    BucketService bucketService = BucketServiceImpl.getBucketService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        Bucket productInBasket = bucketService.readAllByUserProduct(userId,Integer.parseInt(productId));
        if(productInBasket!=null){
            try {
                bucketService.update(productInBasket);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            bucketService.create(new Bucket(userId,Integer.parseInt(productId),new Date(),1));
        }

        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<UserOrder> orderList = bucketService.readAllByUser(userId);
        String json = new Gson().toJson(orderList);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bucketId = request.getParameter("bucketId");
        bucketService.delete(Integer.parseInt(bucketId));

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success");
    }
}

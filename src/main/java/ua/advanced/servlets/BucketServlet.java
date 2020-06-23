package ua.advanced.servlets;

import com.google.gson.Gson;
import ua.advanced.domain.Bucket;
import ua.advanced.domain.Product;
import ua.advanced.domain.User;
import ua.advanced.dto.UserOrder;
import ua.advanced.service.BucketService;
import ua.advanced.service.ProductService;
import ua.advanced.service.UserService;
import ua.advanced.service.impl.BucketServiceImpl;
import ua.advanced.service.impl.ProductServiceImpl;
import ua.advanced.service.impl.UserServiceImpl;

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
import java.util.stream.Collectors;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {
    BucketService bucketService = BucketServiceImpl.getBucketService();
    ProductService productService = ProductServiceImpl.getProductService();
    UserService userService = UserServiceImpl.getUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<Bucket> bucketList = bucketService.readAllByUserProduct(userId,Integer.parseInt(productId));
        if(!bucketList.isEmpty()){
            try {
                bucketService.update(bucketList.get(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            User user = userService.read(userId);
            Product product = productService.read(Integer.parseInt(productId));
            bucketService.create(new Bucket(user,product,new Date(),1));
        }
        response.setContentType("text");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<Bucket> bucketsByUser = bucketService.readAllByUser(userId);
        List<UserOrder> userOrdersList = getUserOrders(bucketsByUser);

        String json = new Gson().toJson(userOrdersList);
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

    public List<UserOrder> getUserOrders(List<Bucket> userBuckets){
        return userBuckets.stream().map(bucket -> {
            UserOrder userOrder = new UserOrder();
            userOrder.bucketId = bucket.getId();
            userOrder.name = bucket.getProduct().getName();
            userOrder.price = bucket.getProduct().getPrice();
            userOrder.quantity = bucket.getQuantity();
            userOrder.purchaseDate = bucket.getPurchaseDate();
            return userOrder;
        }).collect(Collectors.toList());
    }
}

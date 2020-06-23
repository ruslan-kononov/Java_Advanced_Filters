package ua.advanced.servlets;

import ua.advanced.domain.Product;
import ua.advanced.service.ProductService;
import ua.advanced.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductService productService = ProductServiceImpl.getProductService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        if (!productName.isEmpty() && !price.isEmpty() && !description.isEmpty()) {
            productService.create(new Product(productName,description,Double.valueOf(price)));

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("success");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.read(Integer.parseInt(productId));
        request.setAttribute("product", product);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

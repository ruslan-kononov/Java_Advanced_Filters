package ua.advanced.service.impl;

import ua.advanced.dao.ProductDao;
import ua.advanced.dao.impl.ProductDaoImpl;
import ua.advanced.domain.Product;
import ua.advanced.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDaoImpl;
    private static ProductService productService;

    private ProductServiceImpl() {
        this.productDaoImpl = new ProductDaoImpl();
    }

    public static ProductService getProductService(){
        if(productService==null){
            productService = new ProductServiceImpl();
        }
        return productService;
    }

    @Override
    public boolean create(Product product) {
        return productDaoImpl.create(product);
    }

    @Override
    public Product read(int id) {
        return productDaoImpl.read(id);
    }

    @Override
    public Product update(Product product) throws SQLException {
        return productDaoImpl.update(product);
    }

    @Override
    public void delete(int id) {
        productDaoImpl.delete(id);
    }

    @Override
    public List<Product> readAll() {
        return productDaoImpl.readAll();
    }
}

package ua.advanced;

import ua.advanced.domain.Bucket;
import ua.advanced.domain.Product;
import ua.advanced.domain.User;
import ua.advanced.service.BucketService;
import ua.advanced.service.ProductService;
import ua.advanced.service.UserService;
import ua.advanced.service.impl.BucketServiceImpl;
import ua.advanced.service.impl.ProductServiceImpl;
import ua.advanced.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        UserService userService = UserServiceImpl.getUserService();
        ProductService productService = ProductServiceImpl.getProductService();
        BucketService bucketService = BucketServiceImpl.getBucketService();


//        userService.create(new User("test1@test.com","Test1","Test1","test1","test"));
//        userService.create(new User("test2@test.com","Test2","Test2","test2","test"));
//        userService.delete(2);
//        userService.update(new User(1,"test11@test.com","Test11","Test11","test11","test"));
//        for (User user: userService.readAll()) {
//            System.out.println(user);
//        }
//
//        productService.create(new Product("productTest1","productTest1",12.99));
//        productService.create(new Product("productTest2","productTest2",10.99));
//        productService.create(new Product("productTest3","productTest3",21.99));
//        productService.update(new Product(1,"productTest2","productTest2",15.99));
//        productService.delete(2);
//        for (Product product: productService.readAll()) {
//            System.out.println(product);
//        }
//
//        Date date = new Date();
//        bucketService.create(new Bucket(1,1,date));
//        bucketService.create(new Bucket(1,3,date));
//        for (Bucket bucket:bucketService.readAll()) {
//            System.out.println(bucket);
//        }
        System.out.println(userService.readByEmail("konon@gmail"));
    }
}

package org.example.service;

import org.example.model.Clothing;
import org.example.model.Customer;
import org.example.model.Eshop;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Product;
import org.example.model.Shoes;

import java.util.ArrayList;
import java.util.List;

public class InitializeData {

    public void initialize(SkroutzManager manager) {

        Product p1 = new Clothing("11111", "T-shirt", "Adidas", "M", "Black");
        Product p2 = new Clothing("22222", "Hoodie", "Nike", "L", "Gray");
        Product p3 = new Shoes("33333", "Air Max", "Nike", 42, "White");
        Product p4 = new Shoes("44444", "Superstar", "Adidas", 41, "Black");
        Product p5 = new Product("55555", "Vitamin C", Product.CATEGORY_PHARMACY, "Solgar");
        Product p6 = new Product("66666", "Shampoo", Product.CATEGORY_PHARMACY, "Korres");

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);
        manager.addProduct(p4);
        manager.addProduct(p5);
        manager.addProduct(p6);

        Eshop e1 = new Eshop("nike.gr", "123456789", "info@nike.gr");
        Eshop e2 = new Eshop("adidas.gr", "987654321", "info@adidas.gr");
        Eshop e3 = new Eshop("pharmacyshop.gr", "456123789", "info@pharmacyshop.gr");
        Eshop e4 = new Eshop("bestfashion.gr", "741852963", "contact@bestfashion.gr");

        manager.addEshop(e1);
        manager.addEshop(e2);
        manager.addEshop(e3);
        manager.addEshop(e4);

        e1.addOrUpdateProduct(p1, 10, 19.99);
        e2.addOrUpdateProduct(p1, 8, 21.50);

        e1.addOrUpdateProduct(p2, 5, 54.99);
        e4.addOrUpdateProduct(p2, 7, 52.99);

        e1.addOrUpdateProduct(p3, 6, 119.99);
        e2.addOrUpdateProduct(p3, 4, 117.99);

        e2.addOrUpdateProduct(p4, 9, 94.99);
        e4.addOrUpdateProduct(p4, 5, 96.50);

        e3.addOrUpdateProduct(p5, 20, 14.99);
        e4.addOrUpdateProduct(p5, 12, 13.99);

        e3.addOrUpdateProduct(p6, 15, 11.99);
        e1.addOrUpdateProduct(p6, 9, 12.99);

        Customer c1 = new Customer("Maria Kollia", "maria@example.com", "maria", "1234");
        Customer c2 = new Customer("Nikos Papas", "nikos@example.com", "nikos", "1234");

        manager.addCustomer(c1);
        manager.addCustomer(c2);

        List<OrderItem> orderItems1 = new ArrayList<>();
        orderItems1.add(new OrderItem(e1.getWebsite(), p1, 1, 19.99));
        orderItems1.add(new OrderItem(e3.getWebsite(), p5, 2, 14.99));

        manager.addOrder(new Order(c1, orderItems1));

        e1.findStockItemByBarcode(p1.getBarcode()).decreaseStock(1);
        e3.findStockItemByBarcode(p5.getBarcode()).decreaseStock(2);

        List<OrderItem> orderItems2 = new ArrayList<>();
        orderItems2.add(new OrderItem(e2.getWebsite(), p3, 1, 117.99));

        manager.addOrder(new Order(c2, orderItems2));

        e2.findStockItemByBarcode(p3.getBarcode()).decreaseStock(1);
    }
}
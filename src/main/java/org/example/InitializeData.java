package org.example;

public class InitializeData {

    public void data(SkroutzManager manager) {

        Product p1 = new Clothing("111", "Tshirt", "Nike", "M", "Black");
        Product p2 = new Shoes("222", "AirMax", "Nike", "42", "White");

        Eshop e1 = new Eshop("nike.gr", "123456789", "nike@mail.com");

        e1.addProduct(p1, 10, 20);
        e1.addProduct(p2, 5, 80);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.getEshops().add(e1);
    }
}

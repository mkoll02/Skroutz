package org.example;

public class InitializeData {

    public void data(SkroutzManager manager) {

        Product p1 = new Clothing("111","Tshirt","Adidas","M","Black");
        Product p2 = new Shoes("222","AirMax","Nike",42,"White");
        Product p3 = new Product("333","Perfume","Καλλυντικά","Dior");

        Eshop e1 = new Eshop("nike.gr","123456789","nike@mail.com");
        Eshop e2 = new Eshop("adidas.gr","987654321","adidas@mail.com");

        e1.addProduct(p1,10,20);
        e1.addProduct(p2,5,80);

        e2.addProduct(p1,7,22);
        e2.addProduct(p3,15,50);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        manager.getEshops().add(e1);
        manager.getEshops().add(e2);
    }
}
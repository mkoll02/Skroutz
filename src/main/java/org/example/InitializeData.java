package org.example;

/**
 * Αρχικοποίηση ενδεικτικών δεδομένων.
 */
public class InitializeData {

    public void initialize(SkroutzManager manager) {

        Product p1 = new Clothing(
                "111",
                "Tshirt",
                "Adidas",
                "M",
                "Black"
        );

        Product p2 = new Clothing(
                "112",
                "Hoodie",
                "Nike",
                "L",
                "Gray"
        );

        Product p3 = new Shoes(
                "222",
                "AirMax",
                "Nike",
                42,
                "White"
        );

        Product p4 = new Shoes(
                "223",
                "Superstar",
                "Adidas",
                41,
                "Black"
        );

        Product p5 = new Product(
                "333",
                "Vitamin C",
                Product.CATEGORY_PHARMACY,
                "Solgar"
        );

        Product p6 = new Product(
                "334",
                "Shampoo",
                Product.CATEGORY_PHARMACY,
                "Korres"
        );

        Eshop e1 = new Eshop(
                "nike.gr",
                "123456789",
                "nike@mail.com"
        );

        Eshop e2 = new Eshop(
                "adidas.gr",
                "987654321",
                "adidas@mail.com"
        );

        Eshop e3 = new Eshop(
                "pharmacyshop.gr",
                "456123789",
                "info@pharmacyshop.gr"
        );

        e1.addOrUpdateProduct(p1, 10, 20);
        e1.addOrUpdateProduct(p2, 5, 55);
        e1.addOrUpdateProduct(p3, 7, 120);

        e2.addOrUpdateProduct(p1, 8, 22);
        e2.addOrUpdateProduct(p4, 6, 95);

        e3.addOrUpdateProduct(p5, 20, 15);
        e3.addOrUpdateProduct(p6, 15, 12);

        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);
        manager.addProduct(p4);
        manager.addProduct(p5);
        manager.addProduct(p6);

        manager.addEshop(e1);
        manager.addEshop(e2);
        manager.addEshop(e3);
    }
}
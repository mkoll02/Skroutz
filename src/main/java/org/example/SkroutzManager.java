package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class SkroutzManager {

    private ArrayList<Product> products;
    private ArrayList<Eshop> eshops;
    private Scanner sc;

    public SkroutzManager() {
        products = new ArrayList<>();
        eshops = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public ArrayList<Eshop> getEshops() { return eshops; }

    // Προσθήκη προϊόντος χωρίς duplicate barcode
    public void addProduct(Product p) {

        if (p == null) return;

        for (Product prod : products) {
            if (prod.getBarcode().equals(p.getBarcode())) {
                System.out.println("Το προϊόν υπάρχει ήδη.");
                return;
            }
        }

        products.add(p);
    }

    public void displayEshops() {
        for (Eshop e : eshops) {
            System.out.println(e);
        }
    }

    public Product findProductByBarcode() {

        System.out.print("Δώσε barcode: ");
        String barcode = sc.next();

        for (Product p : products) {
            if (p.getBarcode().equals(barcode)) {
                return p;
            }
        }

        return null;
    }

    public Eshop findEshop() {

        System.out.print("Αναζήτηση e-shop (AFM ή website): ");
        String input = sc.next();

        for (Eshop e : eshops) {
            if (e.getAfm().equals(input) ||
                    e.getWebsite().equalsIgnoreCase(input)) {
                return e;
            }
        }

        return null;
    }

    public Product createProduct() {

        System.out.println("Νέο προϊόν");

        System.out.print("barcode: ");
        String barcode = sc.next();

        System.out.print("name: ");
        String name = sc.next();

        System.out.print("category: ");
        String category = sc.next();

        System.out.print("brand: ");
        String brand = sc.next();

        if (category.equalsIgnoreCase("ρούχα")) {

            System.out.print("size (S/M/L): ");
            String size = sc.next();

            System.out.print("color: ");
            String color = sc.next();

            return new Clothing(barcode, name, brand, size, color);
        }

        if (category.equalsIgnoreCase("υποδήματα")) {

            System.out.print("size: ");
            int size = sc.nextInt();

            System.out.print("color: ");
            String color = sc.next();

            return new Shoes(barcode, name, brand, size, color);
        }

        return new Product(barcode, name, category, brand);
    }

    public int getStock() {

        while (true) {
            try {
                System.out.print("Stock: ");
                int stock = Integer.parseInt(sc.next());

                if (stock >= 0) return stock;

            } catch (Exception e) {}

            System.out.println("Δώσε σωστό αριθμό.");
        }
    }

    public double getPrice() {

        while (true) {
            try {
                System.out.print("Price: ");
                double price = Double.parseDouble(sc.next());

                if (price > 0) return price;

            } catch (Exception e) {}

            System.out.println("Δώσε σωστή τιμή.");
        }
    }
}
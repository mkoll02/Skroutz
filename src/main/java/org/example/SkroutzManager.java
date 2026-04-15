package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class SkroutzManager {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Eshop> eshops = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addProduct(Product p) { products.add(p); }

    public Product findProductByBarcode() {
        System.out.println("Παρακαλώ πληκτρολογήστε το barcode");
        String code = sc.next();

        for (Product p : products) {
            if (p.getBarcode().equals(code)) return p;
        }
        return null;
    }

    public Product createProduct() {
        System.out.println("Νέο προϊόν:");
        System.out.print("barcode: ");
        String barcode = sc.next();
        System.out.print("name: ");
        String name = sc.next();
        System.out.print("category: ");
        String category = sc.next();
        System.out.print("brand: ");
        String brand = sc.next();

        if (category.equals("ρούχα"))
            return new Clothing(barcode, name, brand, "M", "Black");

        if (category.equals("υποδήματα"))
            return new Shoes(barcode, name, brand, "42", "White");

        return new Product(barcode, name, category, brand);
    }

    public Eshop findEshop() {
        System.out.println("1. AFM");
        System.out.println("2. Website");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Δώσε AFM: ");
            String afm = sc.next();
            for (Eshop e : eshops)
                if (e.getAfm().equals(afm)) return e;
        } else {
            System.out.print("Δώσε website: ");
            String web = sc.next();
            for (Eshop e : eshops)
                if (e.getWebsite().equals(web)) return e;
        }
        return null;
    }

    public int getStockFromUser() {
        System.out.print("Stock: ");
        return sc.nextInt();
    }

    public double getPriceFromUser() {
        System.out.print("Price: ");
        return sc.nextDouble();
    }

    public String getBarcodeFromUser() {
        System.out.print("Barcode: ");
        return sc.next();
    }

    public StockItem findStockItem(Eshop shop, String barcode) {
        for (StockItem s : shop.getProducts())
            if (s.getProduct().getBarcode().equals(barcode))
                return s;
        return null;
    }

    public ArrayList<Eshop> getEshops() { return eshops; }
}
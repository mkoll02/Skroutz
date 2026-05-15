package org.example.service;

import org.example.model.Customer;
import org.example.model.Eshop;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.StockItem;

import java.util.ArrayList;
import java.util.List;

public class SkroutzManager {

    private final List<Product> products;
    private final List<Eshop> eshops;
    private final List<Customer> customers;
    private final List<Order> orders;

    public SkroutzManager() {
        products = new ArrayList<>();
        eshops = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Το προϊόν δεν μπορεί να είναι null.");
        }

        if (findProductByBarcode(product.getBarcode()) != null) {
            throw new IllegalArgumentException("Υπάρχει ήδη προϊόν με αυτό το barcode.");
        }

        products.add(product);
    }

    public void addEshop(Eshop eshop) {

        if (eshop == null) {
            throw new IllegalArgumentException("Το e-shop δεν μπορεί να είναι null.");
        }

        for (Eshop e : eshops) {
            if (e.getAfm().equals(eshop.getAfm())) {
                throw new IllegalArgumentException("Υπάρχει ήδη e-shop με αυτό το ΑΦΜ.");
            }

            if (e.getWebsite().equalsIgnoreCase(eshop.getWebsite())) {
                throw new IllegalArgumentException("Υπάρχει ήδη e-shop με αυτό το website.");
            }
        }

        eshops.add(eshop);
    }

    public void addCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Ο πελάτης δεν μπορεί να είναι null.");
        }

        if (findCustomerByUsername(customer.getUsername()) != null) {
            throw new IllegalArgumentException("Υπάρχει ήδη πελάτης με αυτό το username.");
        }

        customers.add(customer);
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Η παραγγελία δεν μπορεί να είναι null.");
        }

        orders.add(order);
    }

    public Product findProductByBarcode(String barcode) {

        if (barcode == null) {
            return null;
        }

        for (Product product : products) {
            if (product.getBarcode().equals(barcode.trim())) {
                return product;
            }
        }

        return null;
    }

    public Eshop findEshop(String input) {

        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        input = input.trim();

        for (Eshop eshop : eshops) {
            if (eshop.getAfm().equals(input)
                    || eshop.getWebsite().equalsIgnoreCase(input)) {
                return eshop;
            }
        }

        return null;
    }

    public Customer findCustomerByUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username.trim())) {
                return customer;
            }
        }

        return null;
    }

    public int countShopsSellingProduct(Product product) {

        int count = 0;

        for (Eshop eshop : eshops) {
            StockItem item = eshop.findStockItemByBarcode(product.getBarcode());

            if (item != null && item.getStock() > 0) {
                count++;
            }
        }

        return count;
    }

    public double findLowestPrice(Product product) {

        double lowestPrice = Double.MAX_VALUE;

        for (Eshop eshop : eshops) {
            StockItem item = eshop.findStockItemByBarcode(product.getBarcode());

            if (item != null && item.getStock() > 0 && item.getPrice() < lowestPrice) {
                lowestPrice = item.getPrice();
            }
        }

        if (lowestPrice == Double.MAX_VALUE) {
            return -1;
        }

        return lowestPrice;
    }

    public List<Product> searchProducts(String criterion) {

        List<Product> results = new ArrayList<>();

        if (criterion == null || criterion.trim().isEmpty()) {
            return results;
        }

        criterion = criterion.trim().toLowerCase();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(criterion)
                    || product.getCategory().toLowerCase().contains(criterion)) {
                results.add(product);
            }
        }

        return results;
    }

    public List<Eshop> findEshopsSellingProduct(Product product) {

        List<Eshop> results = new ArrayList<>();

        for (Eshop eshop : eshops) {
            StockItem item = eshop.findStockItemByBarcode(product.getBarcode());

            if (item != null && item.getStock() > 0) {
                results.add(eshop);
            }
        }

        return results;
    }

    public int countOrdersContainingProduct(Product product) {

        int count = 0;

        for (Order order : orders) {
            if (order.containsProduct(product)) {
                count++;
            }
        }

        return count;
    }

    public int countOrdersForEshop(Eshop eshop) {

        int count = 0;

        for (Order order : orders) {
            if (order.containsEshop(eshop.getWebsite())) {
                count++;
            }
        }

        return count;
    }

    public double getRevenueForEshop(Eshop eshop) {

        double revenue = 0;

        for (Order order : orders) {
            revenue += order.getAmountForEshop(eshop.getWebsite());
        }

        return revenue;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Eshop> getEshops() {
        return new ArrayList<>(eshops);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void displayEshops() {

        if (eshops.isEmpty()) {
            System.out.println("Δεν υπάρχουν e-shops.");
            return;
        }

        System.out.println("\n===== Διαθέσιμα e-shops =====");

        for (Eshop eshop : eshops) {
            System.out.println("Website: " + eshop.getWebsite()
                    + " | ΑΦΜ: " + eshop.getAfm());
        }
    }

    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Δεν υπάρχουν προϊόντα.");
            return;
        }

        System.out.println("\n===== Υπάρχοντα προϊόντα =====");

        for (Product product : products) {
            double lowestPrice = findLowestPrice(product);

            System.out.println(product
                    + " | Πωλείται σε shops: " + countShopsSellingProduct(product)
                    + " | Χαμηλότερη τιμή: "
                    + (lowestPrice == -1 ? "Δεν υπάρχει διαθέσιμο απόθεμα" : String.format("%.2f€", lowestPrice)));
        }
    }
}
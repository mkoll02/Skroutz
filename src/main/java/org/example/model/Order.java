package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int nextCode = 1;

    private final int code;
    private final Customer customer;
    private final LocalDateTime date;
    private final List<OrderItem> items;

    //Δημιουργεί νέα παραγγελία
    public Order(Customer customer, List<OrderItem> items) {

        if (customer == null) {
            throw new IllegalArgumentException("Ο πελάτης δεν μπορεί να είναι null.");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Η παραγγελία πρέπει να έχει προϊόντα.");
        }

        this.code = nextCode++;
        this.customer = customer;
        this.date = LocalDateTime.now();
        this.items = new ArrayList<>(items);
    }

    public int getCode() {
        return code;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    //Υπολογίζει το συνολικό ποσό της παραγγελίας
    public double getTotalAmount() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getLineTotal();
        }

        return total;
    }

    //Ελέγχει αν η παραγγελία περιέχει συγκεκριμένο προιόν
    public boolean containsProduct(Product product) {
        for (OrderItem item : items) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }

        return false;
    }

    //Ελέγχει αν η παραγγελία περιέχει προιόν από συγκεκριμένο eshop
    public boolean containsEshop(String website) {
        for (OrderItem item : items) {
            if (item.getWebsite().equalsIgnoreCase(website)) {
                return true;
            }
        }

        return false;
    }

    // Υπολογίζει το ποσό της παραγγελίας που αντιστοιχεί
    public double getAmountForEshop(String website) {
        double total = 0;

        for (OrderItem item : items) {
            if (item.getWebsite().equalsIgnoreCase(website)) {
                total += item.getLineTotal();
            }
        }

        return total;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "Κωδικός: " + code +
                " | Πελάτης: " + customer.getUsername() +
                " | Ημερομηνία: " + getFormattedDate() +
                " | Ποσό: " + String.format("%.2f€", getTotalAmount());
    }
}
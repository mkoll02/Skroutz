package org.example.functions;

import org.example.model.CartItem;
import org.example.model.Customer;
import org.example.model.Eshop;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Product;
import org.example.model.StockItem;
import org.example.service.SkroutzManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFunctions {

    private final Scanner sc;

    public CustomerFunctions(Scanner sc) {
        this.sc = sc;
    }

    public void searchAndOrder(SkroutzManager manager) {

        System.out.println("\n===== Λειτουργία 3: Αναζήτηση και Παραγγελία =====");

        List<CartItem> cart = new ArrayList<>();
        boolean continueSearch;

        do {
            searchProductsAndAddToCart(manager, cart);
            continueSearch = readYesNo("\nΘέλεις νέα αναζήτηση και προσθήκη προϊόντος; Ν/Ο: ");

        } while (continueSearch);

        if (cart.isEmpty()) {
            System.out.println("Το καλάθι είναι άδειο. Δεν δημιουργήθηκε παραγγελία.");
            return;
        }

        editCart(cart);

        if (cart.isEmpty()) {
            System.out.println("Το καλάθι άδειασε. Δεν δημιουργήθηκε παραγγελία.");
            return;
        }

        showCart(cart);

        if (!readYesNo("\nΕπιβεβαίωση παραγγελίας; Ν/Ο: ")) {
            System.out.println("Η παραγγελία ακυρώθηκε.");
            return;
        }

        Customer customer = loginOrRegister(manager);

        if (customer == null) {
            System.out.println("Αποτυχία σύνδεσης/εγγραφής. Η παραγγελία ακυρώθηκε.");
            return;
        }

        completeOrder(manager, customer, cart);
    }

    private void searchProductsAndAddToCart(SkroutzManager manager, List<CartItem> cart) {

        String criterion = readText("\nΑναζήτηση προϊόντος με κατηγορία ή όνομα: ");

        List<Product> results = manager.searchProducts(criterion);

        if (results.isEmpty()) {
            System.out.println("Δεν βρέθηκαν προϊόντα.");
            return;
        }

        System.out.println("\n===== Αποτελέσματα αναζήτησης =====");

        for (Product product : results) {
            double lowestPrice = manager.findLowestPrice(product);

            System.out.println("Barcode: " + product.getBarcode()
                    + " | Όνομα: " + product.getName()
                    + " | Κατηγορία: " + product.getCategory()
                    + product.getExtraInfo()
                    + " | Πλήθος e-shops: " + manager.countShopsSellingProduct(product)
                    + " | Χαμηλότερη τιμή: "
                    + (lowestPrice == -1 ? "Μη διαθέσιμο" : String.format("%.2f€", lowestPrice)));
        }

        String barcode = readText("\nΔώσε barcode προϊόντος που θέλεις να δεις: ");
        Product selectedProduct = manager.findProductByBarcode(barcode);

        if (selectedProduct == null || !results.contains(selectedProduct)) {
            System.out.println("Μη έγκυρη επιλογή προϊόντος.");
            return;
        }

        List<Eshop> shops = manager.findEshopsSellingProduct(selectedProduct);

        if (shops.isEmpty()) {
            System.out.println("Δεν υπάρχει διαθέσιμο απόθεμα για το προϊόν.");
            return;
        }

        System.out.println("\n===== Διαθέσιμα e-shops για το προϊόν =====");

        for (Eshop eshop : shops) {
            StockItem item = eshop.findStockItemByBarcode(selectedProduct.getBarcode());

            System.out.println("Website: " + eshop.getWebsite()
                    + " | Προϊόν: " + selectedProduct.getName()
                    + selectedProduct.getExtraInfo()
                    + " | Τιμή: " + String.format("%.2f€", item.getPrice())
                    + " | Διαθέσιμα τεμάχια: " + item.getStock());
        }

        String shopInput = readText("\nΔώσε website ή ΑΦΜ e-shop επιλογής: ");
        Eshop selectedShop = manager.findEshop(shopInput);

        if (selectedShop == null || !shops.contains(selectedShop)) {
            System.out.println("Μη έγκυρη επιλογή e-shop.");
            return;
        }

        StockItem selectedItem = selectedShop.findStockItemByBarcode(selectedProduct.getBarcode());
        int quantity = readQuantity(selectedItem.getStock());

        CartItem existing = findCartItem(cart, selectedShop, selectedProduct);

        if (existing != null) {
            try {
                existing.setQuantity(existing.getQuantity() + quantity);
                System.out.println("Το προϊόν υπήρχε ήδη στο καλάθι. Η ποσότητα αυξήθηκε.");
            } catch (IllegalArgumentException e) {
                System.out.println("Σφάλμα: " + e.getMessage());
            }
        } else {
            cart.add(new CartItem(selectedShop, selectedItem, quantity));
            System.out.println("Το προϊόν προστέθηκε στο καλάθι.");
        }
    }

    private CartItem findCartItem(List<CartItem> cart, Eshop eshop, Product product) {

        for (CartItem item : cart) {
            if (item.isSameProductAndShop(eshop, product)) {
                return item;
            }
        }

        return null;
    }

    private void showCart(List<CartItem> cart) {

        System.out.println("\n===== Καλάθι =====");

        double total = 0;

        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);

            System.out.println((i + 1) + ". " + item.toCartString());
            total += item.getLineTotal();
        }

        System.out.println("Συνολικό ποσό: " + String.format("%.2f€", total));
    }

    private void editCart(List<CartItem> cart) {

        while (!cart.isEmpty()) {
            showCart(cart);

            if (!readYesNo("\nΘέλεις να αλλάξεις ποσότητα προϊόντος στο καλάθι; Ν/Ο: ")) {
                return;
            }

            int position = readInt("Δώσε αριθμό γραμμής καλαθιού: ");

            if (position < 1 || position > cart.size()) {
                System.out.println("Μη έγκυρη γραμμή καλαθιού.");
                continue;
            }

            CartItem item = cart.get(position - 1);

            System.out.println("Διαθέσιμο απόθεμα: " + item.getStockItem().getStock());

            int newQuantity = readInt("Νέα ποσότητα, ή 0 για αφαίρεση: ");

            if (newQuantity == 0) {
                cart.remove(position - 1);
                System.out.println("Το προϊόν αφαιρέθηκε από το καλάθι.");
            } else {
                try {
                    item.setQuantity(newQuantity);
                    System.out.println("Η ποσότητα ενημερώθηκε.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Σφάλμα: " + e.getMessage());
                }
            }
        }
    }

    private Customer loginOrRegister(SkroutzManager manager) {

        System.out.println("\n===== Σύνδεση / Εγγραφή Πελάτη =====");
        System.out.println("1. Login");
        System.out.println("2. Εγγραφή");

        int choice = readInt("Επιλογή: ");

        if (choice == 1) {
            return login(manager);
        } else if (choice == 2) {
            return register(manager);
        } else {
            System.out.println("Μη έγκυρη επιλογή.");
            return null;
        }
    }

    private Customer login(SkroutzManager manager) {

        String username = readText("Username: ");
        String password = readText("Password: ");

        Customer customer = manager.findCustomerByUsername(username);

        if (customer == null || !customer.hasPassword(password)) {
            System.out.println("Λάθος username ή password.");
            return null;
        }

        System.out.println("Επιτυχής σύνδεση.");
        return customer;
    }

    private Customer register(SkroutzManager manager) {

        try {
            String fullName = readText("Ονοματεπώνυμο: ");
            String email = readEmail("Email: ");
            String username = readText("Username: ");

            if (manager.findCustomerByUsername(username) != null) {
                System.out.println("Υπάρχει ήδη πελάτης με αυτό το username.");
                return null;
            }

            String password = readText("Password: ");

            Customer customer = new Customer(fullName, email, username, password);
            manager.addCustomer(customer);

            System.out.println("Η εγγραφή ολοκληρώθηκε.");
            return customer;

        } catch (IllegalArgumentException e) {
            System.out.println("Σφάλμα: " + e.getMessage());
            return null;
        }
    }

    private void completeOrder(SkroutzManager manager, Customer customer, List<CartItem> cart) {

        try {
            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem cartItem : cart) {
                cartItem.getStockItem().decreaseStock(cartItem.getQuantity());

                orderItems.add(new OrderItem(
                        cartItem.getEshop().getWebsite(),
                        cartItem.getProduct(),
                        cartItem.getQuantity(),
                        cartItem.getPrice()
                ));
            }

            Order order = new Order(customer, orderItems);
            manager.addOrder(order);

            System.out.println("\nΗ παραγγελία ολοκληρώθηκε επιτυχώς.");
            System.out.println(order);

            for (OrderItem item : order.getItems()) {
                System.out.println("   " + item);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Σφάλμα κατά την παραγγελία: " + e.getMessage());
        }
    }

    private int readQuantity(int maxStock) {

        while (true) {
            int quantity = readInt("Πλήθος τεμαχίων: ");

            if (quantity <= 0) {
                System.out.println("Το πλήθος πρέπει να είναι θετικό.");
            } else if (quantity > maxStock) {
                System.out.println("Δεν υπάρχει αρκετό απόθεμα. Διαθέσιμα: " + maxStock);
            } else {
                return quantity;
            }
        }
    }

    private String readText(String message) {

        while (true) {
            System.out.print(message);

            String text = sc.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println("Το πεδίο δεν μπορεί να είναι κενό.");
        }
    }

    private String readEmail(String message) {

        while (true) {
            String email = readText(message);

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Μη έγκυρο email.");
        }
    }

    private boolean readYesNo(String message) {

        while (true) {
            System.out.print(message);
            String answer = sc.nextLine().trim();

            if (answer.equalsIgnoreCase("Ν") || answer.equalsIgnoreCase("ΝΑΙ")) {
                return true;
            }

            if (answer.equalsIgnoreCase("Ο") || answer.equalsIgnoreCase("ΟΧΙ")) {
                return false;
            }

            System.out.println("Δώσε Ν ή Ο.");
        }
    }

    private int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Δώσε σωστό ακέραιο αριθμό.");
            }
        }
    }
}
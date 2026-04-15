package org.example;

/*
 * Βασική κλάση προϊόντος.
 * Όλα τα προϊόντα του συστήματος κληρονομούν από αυτή.
 * Περιέχει τα κοινά χαρακτηριστικά που έχουν όλα τα προϊόντα.
 */
public class Product {

    // Μοναδικός κωδικός προϊόντος
    protected String barcode;

    // Όνομα προϊόντος
    protected String name;

    // Κατηγορία προϊόντος (ρούχα, υποδήματα, φαρμακείου)
    protected String category;

    // Brand προϊόντος
    protected String brand;

    /*
     * Constructor για δημιουργία προϊόντος
     */
    public Product(String barcode, String name, String category, String brand) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }

    // Getter για barcode
    public String getBarcode() {
        return barcode;
    }

    // Getter για όνομα
    public String getName() {
        return name;
    }

    // Getter για κατηγορία
    public String getCategory() {
        return category;
    }

    /*
     * Μέθοδος που επιστρέφει πληροφορίες προϊόντος
     */
    @Override
    public String toString() {
        return "Barcode: " + barcode +
                " | Name: " + name +
                " | Category: " + category +
                " | Brand: " + brand;
    }
}
package org.example;

/*
 * Κλάση Clothing που κληρονομεί από Product
 * Χρησιμοποιείται για προϊόντα κατηγορίας "ρούχα"
 */
public class Clothing extends Product {

    // Μέγεθος ρούχου
    private String size;

    // Χρώμα ρούχου
    private String color;

    /*
     * Constructor για δημιουργία ρούχου
     */
    public Clothing(String barcode, String name, String brand, String size, String color) {

        // Καλούμε τον constructor της Product
        super(barcode, name, "ρούχα", brand);

        this.size = size;
        this.color = color;
    }

    /*
     * Επιστρέφει πληροφορίες ρούχου
     */
    @Override
    public String toString() {

        return super.toString() +
                " | Size: " + size +
                " | Color: " + color;

    }
}
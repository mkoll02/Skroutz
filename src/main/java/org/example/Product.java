package org.example;

public class Product {

    // Βασικά χαρακτηριστικά προϊόντος
    private String barcode;
    private String name;
    private String category;
    private String brand;

    public Product(String barcode, String name, String category, String brand) {

        // Έλεγχος barcode (μόνο αριθμοί)
        if (barcode == null || !barcode.matches("\\d+")) {
            throw new IllegalArgumentException("Μη έγκυρο barcode (μόνο αριθμοί)");
        }
        this.barcode = barcode;

        // Έλεγχος ονόματος
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Μη έγκυρο όνομα");
        }
        this.name = name.trim();

        // Αν δεν δοθεί κατηγορία
        this.category = (category == null || category.trim().isEmpty())
                ? "άγνωστο" : category.trim();

        // Αν δεν δοθεί brand
        this.brand = (brand == null || brand.trim().isEmpty())
                ? "άγνωστο" : brand.trim();
    }

    public String getBarcode() { return barcode; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }

    @Override
    public String toString() {
        return "Barcode: " + barcode +
                " | Name: " + name +
                " | Category: " + category +
                " | Brand: " + brand;
    }
}
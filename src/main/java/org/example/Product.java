package org.example;

public class Product {

    private String barcode;
    private String name;
    private String category;
    private String brand;

    public Product(String barcode,
                   String name,
                   String category,
                   String brand) {

        // Barcode μόνο αριθμοί
        if (barcode == null || !barcode.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Μη έγκυρο barcode.");
        }

        // Έλεγχος ονόματος
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Μη έγκυρο όνομα.");
        }

        // Έλεγχος κατηγορίας
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Μη έγκυρη κατηγορία.");
        }

        category = category.trim().toLowerCase();

        if (!category.equals("ρούχα") &&
                !category.equals("υποδήματα") &&
                !category.equals("καλλυντικά")) {

            throw new IllegalArgumentException(
                    "Μη αποδεκτή κατηγορία.");
        }

        // Έλεγχος brand
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Μη έγκυρο brand.");
        }

        this.barcode = barcode.trim();
        this.name = name.trim();
        this.category = category;
        this.brand = brand.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {

        return "Barcode : " + barcode +
                "\nName     : " + name +
                "\nCategory : " + category +
                "\nBrand    : " + brand;
    }
}
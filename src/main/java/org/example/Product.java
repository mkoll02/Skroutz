package org.example;

import java.util.Objects;

// Βασική κλάση προϊόντος.
// χαρακτηριστικά όλων των προϊόντων ανεξαρτήτου κατηγορίας
public class Product {

    //Σταθερές κατηγοριες προϊόντων
    public static final String CATEGORY_CLOTHING = "ρούχα";
    public static final String CATEGORY_SHOES = "υποδήματα";
    public static final String CATEGORY_PHARMACY = "προϊόντα φαρμακείου";

    private final String barcode;
    private final String name;
    private final String category;
    private final String brand;

    // Constructor
    public Product(String barcode,
                   String name,
                   String category,
                   String brand) {

        // Έλεγχος barcode
        validateBarcode(barcode);

        this.name = normalizeText(
                name,
                "Το όνομα προϊόντος δεν μπορεί να είναι κενό.");

        this.brand = normalizeText(
                brand,
                "Το brand δεν μπορεί να είναι κενό.");

        category = normalizeText(
                category,
                "Η κατηγορία δεν μπορεί να είναι κενή.")
                .toLowerCase();

        if (!isValidCategory(category)) {

            throw new IllegalArgumentException(
                    "Μη έγκυρη κατηγορία προϊόντος.");
        }

        this.barcode = barcode.trim();
        this.category = category;
    }

    public static void validateBarcode(String barcode) {

        if (barcode == null
                || !barcode.trim().matches("\\d{5}")) {

            throw new IllegalArgumentException(
                    "Το barcode πρέπει να έχει ακριβώς 5 ψηφία.");
        }
    }

    public static boolean isValidCategory(String category) {

        if (category == null) {
            return false;
        }

        category = category.trim().toLowerCase();

        return category.equals(CATEGORY_CLOTHING)
                || category.equals(CATEGORY_SHOES)
                || category.equals(CATEGORY_PHARMACY);
    }

    protected static String normalizeText(String text,
                                          String message) {

        if (text == null
                || text.trim().isEmpty()) {

            throw new IllegalArgumentException(message);
        }

        return text.trim();
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

    public String getExtraInfo() {
        return "";
    }

    @Override
    public String toString() {

        return "Barcode: " + barcode +
                " | Όνομα: " + name +
                " | Κατηγορία: " + category +
                " | Brand: " + brand +
                getExtraInfo();
    }

    // 2 προϊόντα θεωρούνται ίδια αν έχουν ίδιο barcode
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Product other)) {
            return false;
        }

        return barcode.equals(other.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
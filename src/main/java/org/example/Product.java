package org.example;

/**
 * Βασική κλάση προϊόντος.
 */
public class Product {

    public static final String CATEGORY_CLOTHING = "ρούχα";
    public static final String CATEGORY_SHOES = "υποδήματα";
    public static final String CATEGORY_PHARMACY = "προϊόντα φαρμακείου";

    private final String barcode;
    private final String name;
    private final String category;
    private final String brand;

    public Product(String barcode,
                   String name,
                   String category,
                   String brand) {

        validateBarcode(barcode);
        validateText(name, "Μη έγκυρο όνομα.");
        validateText(category, "Μη έγκυρη κατηγορία.");
        validateText(brand, "Μη έγκυρο brand.");

        category = category.trim().toLowerCase();

        if (!isValidCategory(category)) {

            throw new IllegalArgumentException(
                    "Μη αποδεκτή κατηγορία.");
        }

        this.barcode = barcode.trim();
        this.name = name.trim();
        this.category = category;
        this.brand = brand.trim();
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

    protected static void validateBarcode(String barcode) {

        if (barcode == null
                || !barcode.trim().matches("\\d+")) {

            throw new IllegalArgumentException(
                    "Μη έγκυρο barcode.");
        }
    }

    protected static void validateText(String text,
                                       String message) {

        if (text == null
                || text.trim().isEmpty()) {

            throw new IllegalArgumentException(message);
        }
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
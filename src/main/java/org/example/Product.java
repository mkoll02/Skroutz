package org.example;

/**
 * Βασική κλάση προϊόντος.
 * Περιγράφει τα κοινά χαρακτηριστικά όλων των προϊόντων.
 */
public class Product {

    /**
     * Σταθερές επιτρεπτών κατηγοριών προϊόντων.
     */
    public static final String CATEGORY_CLOTHING = "ρούχα";
    public static final String CATEGORY_SHOES = "υποδήματα";
    public static final String CATEGORY_PHARMACY = "προϊόντα φαρμακείου";

    /**
     * Μοναδικός κωδικός προϊόντος.
     */
    private final String barcode;

    /**
     * Ονομασία προϊόντος.
     */
    private final String name;

    /**
     * Κατηγορία προϊόντος.
     */
    private final String category;

    /**
     * Brand προϊόντος.
     */
    private final String brand;

    /**
     * Constructor προϊόντος.
     *
     * @param barcode μοναδικός κωδικός
     * @param name όνομα προϊόντος
     * @param category κατηγορία προϊόντος
     * @param brand brand προϊόντος
     */
    public Product(String barcode,
                   String name,
                   String category,
                   String brand) {

        validateBarcode(barcode);

        validateText(name,
                "Το όνομα προϊόντος δεν μπορεί να είναι κενό.");

        validateText(category,
                "Η κατηγορία δεν μπορεί να είναι κενή.");

        validateText(brand,
                "Το brand δεν μπορεί να είναι κενό.");

        category = category.trim().toLowerCase();

        if (!isValidCategory(category)) {

            throw new IllegalArgumentException(
                    "Μη αποδεκτή κατηγορία προϊόντος.");
        }

        this.barcode = barcode.trim();
        this.name = name.trim();
        this.category = category;
        this.brand = brand.trim();
    }

    /**
     * Έλεγχος εγκυρότητας κατηγορίας.
     *
     * @param category κατηγορία προς έλεγχο
     * @return true αν είναι αποδεκτή
     */
    public static boolean isValidCategory(String category) {

        if (category == null) {
            return false;
        }

        category = category.trim().toLowerCase();

        return category.equals(CATEGORY_CLOTHING)
                || category.equals(CATEGORY_SHOES)
                || category.equals(CATEGORY_PHARMACY);
    }

    /**
     * Έλεγχος barcode.
     * Επιτρέπονται μόνο αριθμοί μήκους 3-20 ψηφίων.
     *
     * @param barcode barcode προς έλεγχο
     */
    public static void validateBarcode(String barcode) {

        if (barcode == null
                || !barcode.trim().matches("\\d{3,20}")) {

            throw new IllegalArgumentException(
                    "Το barcode πρέπει να περιέχει μόνο αριθμούς (3-20 ψηφία).");
        }
    }

    /**
     * Γενικός έλεγχος String.
     *
     * @param text κείμενο προς έλεγχο
     * @param errorMessage μήνυμα σφάλματος
     */
    protected static void validateText(String text,
                                       String errorMessage) {

        if (text == null
                || text.trim().isEmpty()) {

            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Getter barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Getter ονόματος.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter κατηγορίας.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Getter brand.
     */
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
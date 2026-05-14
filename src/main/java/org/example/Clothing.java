package org.example;

// Κλάση για την κατηγορία Ρούχα που κληρονομεί από την κλάση Προίον

public class Clothing extends Product {

    private final String size;
    private final String color;

    // Constructor

    public Clothing(String barcode, String name, String brand, String size, String color) {

        // constructor Product
        super(barcode, name, CATEGORY_CLOTHING, brand);

        // Έλεγχος μεγέθους
        size = normalizeText(size,
                "Το μέγεθος δεν μπορεί να είναι κενό.")
                .toUpperCase();

        if (!isValidSize(size)) {
            throw new IllegalArgumentException(
                    "Το μέγεθος ρούχου πρέπει να είναι S, M ή L.");
        }

        this.size = size;

        // Έλεγχος χρώματος
        this.color = normalizeText(color,
                "Το χρώμα δεν μπορεί να είναι κενό.");
    }

    public static boolean isValidSize(String size) {

        if (size == null) {
            return false;
        }

        size = size.trim().toUpperCase();

        return size.equals("S")
                || size.equals("M")
                || size.equals("L");
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    // Επιστρέφει πληροφορίες προϊόν
    @Override
    public String getExtraInfo() {
        return " | Μέγεθος: " + size +
                " | Χρώμα: " + color;
    }
}
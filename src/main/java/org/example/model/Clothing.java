package org.example.model;

//κληρονομεί την Product
public class Clothing extends Product {

    private final String size;
    private final String color;

    public Clothing(String barcode, String name, String brand, String size, String color) {

        super(barcode, name, CATEGORY_CLOTHING, brand);

        size = normalizeText(size, "Το μέγεθος δεν μπορεί να είναι κενό.").toUpperCase();

        if (!isValidSize(size)) {
            throw new IllegalArgumentException("Το μέγεθος ρούχου πρέπει να είναι S, M ή L.");
        }

        this.size = size;
        this.color = normalizeText(color, "Το χρώμα δεν μπορεί να είναι κενό.");
    }

    //έλεγχος μεγέθους ρούχου
    public static boolean isValidSize(String size) {
        if (size == null) {
            return false;
        }

        size = size.trim().toUpperCase();

        return size.equals("S") || size.equals("M") || size.equals("L");
    }

    @Override
    public String getExtraInfo() {
        return " | Μέγεθος: " + size + " | Χρώμα: " + color;
    }
}
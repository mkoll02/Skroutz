package org.example.model;

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

    public static boolean isValidSize(String size) {
        if (size == null) {
            return false;
        }

        size = size.trim().toUpperCase();

        return size.equals("S") || size.equals("M") || size.equals("L");
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getExtraInfo() {
        return " | Μέγεθος: " + size + " | Χρώμα: " + color;
    }
}
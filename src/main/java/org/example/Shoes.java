package org.example;

// Κλάση κατηγορία Υποδήματα που κληρονομεί από την Product
public class Shoes extends Product {

    private final int size;
    private final String color;

    public Shoes(String barcode, String name, String brand, int size, String color) {
        super(barcode, name, CATEGORY_SHOES, brand);

        if (!isValidSize(size)) {
            throw new IllegalArgumentException("Το μέγεθος υποδήματος πρέπει να είναι από 37 έως 45.");
        }

        this.size = size;
        this.color = normalizeText(color, "Το χρώμα δεν μπορεί να είναι κενό.");
    }

    public static boolean isValidSize(int size) {
        return size >= 37 && size <= 45;
    }

    public int getSize() {
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
package org.example;

/**
 * Κλάση υποδημάτων.
 */
public class Shoes extends Product {

    private static final int MIN_SIZE = 37;
    private static final int MAX_SIZE = 45;

    private final int size;
    private final String color;

    public Shoes(String barcode,
                 String name,
                 String brand,
                 int size,
                 String color) {

        super(barcode, name, CATEGORY_SHOES, brand);

        if (size < MIN_SIZE || size > MAX_SIZE) {

            throw new IllegalArgumentException(
                    "Το μέγεθος πρέπει να είναι 37-45.");
        }

        validateText(color,
                "Το χρώμα δεν μπορεί να είναι κενό.");

        this.size = size;
        this.color = color.trim();
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {

        return super.toString() +
                "\nSize     : " + size +
                "\nColor    : " + color;
    }
}
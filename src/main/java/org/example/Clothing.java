package org.example;

/**
 * Κλάση ρούχων.
 */
public class Clothing extends Product {

    private static final String SIZE_S = "S";
    private static final String SIZE_M = "M";
    private static final String SIZE_L = "L";

    private final String size;
    private final String color;

    public Clothing(String barcode,
                    String name,
                    String brand,
                    String size,
                    String color) {

        super(barcode, name, CATEGORY_CLOTHING, brand);

        validateText(size,
                "Το μέγεθος δεν μπορεί να είναι κενό.");

        validateText(color,
                "Το χρώμα δεν μπορεί να είναι κενό.");

        size = size.trim().toUpperCase();

        if (!size.equals(SIZE_S)
                && !size.equals(SIZE_M)
                && !size.equals(SIZE_L)) {

            throw new IllegalArgumentException(
                    "Επιτρεπτά μεγέθη: S, M, L.");
        }

        this.size = size;
        this.color = color.trim();
    }

    public String getSize() {
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
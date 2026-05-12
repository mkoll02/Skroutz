package org.example;

/**
 * Κλάση ρούχων.
 * Επεκτείνει την Product.
 */
public class Clothing extends Product {

    /**
     * Επιτρεπτά μεγέθη.
     */
    private static final String SIZE_S = "S";
    private static final String SIZE_M = "M";
    private static final String SIZE_L = "L";

    /**
     * Μέγεθος ρούχου.
     */
    private final String size;

    /**
     * Χρώμα ρούχου.
     */
    private final String color;

    /**
     * Constructor ρούχου.
     */
    public Clothing(String barcode,
                    String name,
                    String brand,
                    String size,
                    String color) {

        super(barcode,
                name,
                CATEGORY_CLOTHING,
                brand);

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

    /**
     * Getter μεγέθους.
     */
    public String getSize() {
        return size;
    }

    /**
     * Getter χρώματος.
     */
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
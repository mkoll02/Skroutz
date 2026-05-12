package org.example;

/**
 * Κλάση υποδημάτων.
 * Επεκτείνει την Product.
 */
public class Shoes extends Product {

    /**
     * Ελάχιστο αποδεκτό μέγεθος.
     */
    private static final int MIN_SIZE = 37;

    /**
     * Μέγιστο αποδεκτό μέγεθος.
     */
    private static final int MAX_SIZE = 45;

    /**
     * Μέγεθος παπουτσιού.
     */
    private final int size;

    /**
     * Χρώμα παπουτσιού.
     */
    private final String color;

    /**
     * Constructor υποδήματος.
     */
    public Shoes(String barcode,
                 String name,
                 String brand,
                 int size,
                 String color) {

        super(barcode,
                name,
                CATEGORY_SHOES,
                brand);

        if (size < MIN_SIZE || size > MAX_SIZE) {

            throw new IllegalArgumentException(
                    "Το μέγεθος πρέπει να είναι από 37 έως 45.");
        }

        validateText(color,
                "Το χρώμα δεν μπορεί να είναι κενό.");

        this.size = size;
        this.color = color.trim();
    }

    /**
     * Getter μεγέθους.
     */
    public int getSize() {
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
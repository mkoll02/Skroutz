package org.example;

public class Clothing extends Product {

    private String size;
    private String color;

    public Clothing(String barcode, String name, String brand, String size, String color) {

        super(barcode, name, "ρούχα", brand);

        // Επιτρέπονται μόνο S, M, L
        if (size != null &&
                (size.equalsIgnoreCase("S") ||
                        size.equalsIgnoreCase("M") ||
                        size.equalsIgnoreCase("L"))) {

            this.size = size.toUpperCase();
        } else {
            this.size = "M"; // default
        }

        this.color = (color == null || color.trim().isEmpty())
                ? "unknown" : color.trim();
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Size: " + size +
                " | Color: " + color;
    }
}
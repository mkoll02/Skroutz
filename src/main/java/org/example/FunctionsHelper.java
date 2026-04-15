package org.example;

public class FunctionsHelper {

    public void handleInsert(SkroutzManager manager) {

        Product p = manager.findProductByBarcode();

        if (p == null) {
            p = manager.createProduct();
            manager.addProduct(p);
        }

        Eshop shop = manager.findEshop();

        int stock = manager.getStockFromUser();
        double price = manager.getPriceFromUser();

        shop.addProduct(p, stock, price);

        System.out.println("Καταχώρηση:");
        shop.displayProducts();
    }

    public void handleUpdateStock(SkroutzManager manager) {

        Eshop shop = manager.findEshop();

        shop.displayProducts();

        String barcode = manager.getBarcodeFromUser();

        StockItem item = manager.findStockItem(shop, barcode);

        if (item != null) {
            int newStock = manager.getStockFromUser();
            item.setStock(newStock);
        }

        System.out.println("Updated:");
        shop.displayProducts();
    }
}
package org.example;

import java.util.Scanner;

public class Main {

    //Σταθερές
    private static final int REGISTRATION = 1;
    private static final int UPDATE_STOCK = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SkroutzManager manager = new SkroutzManager();

        InitializeData initializer = new InitializeData();
        initializer.initialize(manager);

        RegistrationFunctions registrationFunctions =
                new RegistrationFunctions(sc);

        StockFunctions stockFunctions =
                new StockFunctions(sc);

        int choice;

        System.out.println("===== Java_Skroutz System =====");

        // menu
        do {
            printMenu();
            choice = readChoice(sc);

            switch (choice) {

                case REGISTRATION ->
                        registrationFunctions
                                .insertProductsAndEshops(manager);

                case UPDATE_STOCK ->
                        stockFunctions
                                .updateStock(manager);

                case EXIT ->
                        System.out.println(
                                "Έξοδος από το πρόγραμμα.");

                default ->
                        System.out.println(
                                "Μη έγκυρη επιλογή.");
            }

        } while (choice != EXIT);

        //Κλείσιμο scanner
        sc.close();
    }

    // Εμφάνιση βασικού menu
    private static void printMenu() {

        System.out.println("\n===== Βασικό Μενού =====");
        System.out.println("1. Καταχώρηση προϊόντων και e-shops");
        System.out.println("2. Ανανέωση αποθέματος προϊόντος/ων στο e-shop");
        System.out.println("3. Έξοδος");
        System.out.print("Επιλογή: ");
    }

    // επιλογή χρήστη
    private static int readChoice(Scanner sc) {

        try {

            return Integer.parseInt(
                    sc.nextLine().trim());

        } catch (NumberFormatException e) {

            return -1;
        }
    }
}
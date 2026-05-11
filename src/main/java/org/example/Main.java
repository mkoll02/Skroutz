package org.example;

import java.util.Scanner;

/**
 * Κύρια κλάση εκτέλεσης.
 */
public class Main {

    private static final int INSERT_PRODUCT = 1;
    private static final int UPDATE_STOCK = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SkroutzManager manager =
                new SkroutzManager();

        InitializeData init =
                new InitializeData();

        init.initialize(manager);

        Functions functions =
                new Functions(sc);

        int choice;

        System.out.println(
                "===== SKROUTZ SYSTEM =====");

        do {

            printMenu();

            choice = readChoice(sc);

            switch (choice) {

                case INSERT_PRODUCT:
                    functions.insertProduct(manager);
                    break;

                case UPDATE_STOCK:
                    functions.updateStock(manager);
                    break;

                case EXIT:
                    System.out.println(
                            "Έξοδος από το σύστημα.");
                    break;

                default:
                    System.out.println(
                            "Μη έγκυρη επιλογή.");
            }

        } while (choice != EXIT);

        sc.close();
    }

    private static void printMenu() {

        System.out.println(
                "\n1. Καταχώρηση προϊόντος");

        System.out.println(
                "2. Ανανέωση stock");

        System.out.println(
                "3. Έξοδος");

        System.out.print(
                "Επιλογή: ");
    }

    private static int readChoice(Scanner sc) {

        try {

            return Integer.parseInt(
                    sc.nextLine());

        } catch (NumberFormatException e) {

            return -1;
        }
    }
}
package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SkroutzManager manager = new SkroutzManager();

        InitializeData init = new InitializeData();
        init.data(manager);

        FunctionsHelper helper = new FunctionsHelper();
        Functions functions = new Functions(helper);

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("--- Skroutz System ---");

        do {
            System.out.println("\n1. Καταχώρηση προϊόντος");
            System.out.println("2. Ανανέωση stock");
            System.out.println("3. Έξοδος");
            System.out.print("Επιλογή: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> functions.function1(manager);
                case 2 -> functions.function2(manager);
                case 3 -> System.out.println("Έξοδος");
                default -> System.out.println("Λάθος επιλογή");
            }

        } while (choice != 3);
    }
}
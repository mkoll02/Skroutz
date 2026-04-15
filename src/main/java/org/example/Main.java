package org.example;

import java.util.Scanner;

public class it2024131

    public static void main(String[] args) {

        SkroutzManager manager = new SkroutzManager();
        InitializeData init = new InitializeData();
        init.data(manager);

        FunctionsHelper helper = new FunctionsHelper();
        Functions functions = new Functions(helper);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Καταχώρηση προϊόντος");
            System.out.println("2. Ανανέωση αποθέματος");
            System.out.println("0. Έξοδος");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    functions.function1(manager);
                    break;
                case 2:
                    functions.function2(manager);
                    break;
            }

        } while (choice != 0);
    }
}
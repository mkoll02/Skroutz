package org.example;

import org.example.functions.*;
import org.example.service.InitializeData;
import org.example.service.SkroutzManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("~Καλώς ήρθατε στην εφαρμογή Java_Skroutz~");

        Scanner sc = new Scanner(System.in);
        SkroutzManager manager = new SkroutzManager();
        new InitializeData().initialize(manager);

        RegistrationFunctions registrationFunctions =
                new RegistrationFunctions(sc);

        StockFunctions stockFunctions =
                new StockFunctions(sc);

        CustomerFunctions customerFunctions =
                new CustomerFunctions(sc);

        ReportFunctions reportFunctions =
                new ReportFunctions(sc);

        FileFunctions fileFunctions =
                new FileFunctions();

        int choice;

        //μενού μέχρι ο χρήστης να επιλέξει έξοδο
        do {

            printMenu();

            choice = readChoice(sc);

            switch (choice) {

                case 1 ->
                        registrationFunctions.insertProductsAndEshops(manager);

                case 2 ->
                        stockFunctions.updateStock(manager);

                case 3 ->
                        customerFunctions.searchAndOrder(manager);

                case 4 ->
                        reportFunctions.showReports(manager);

                case 5 ->
                        fileFunctions.saveEshopsToFile(manager);

                case 6 ->
                        System.out.println("Έξοδος.");

                default ->
                        System.out.println("Μη έγκυρη επιλογή.");
            }

        } while (choice != 6);

        sc.close();
    }

    private static void printMenu() {

        System.out.println("\n===== Βασικό Μενού =====");
        System.out.println("1. Καταχώρηση προϊόντων και e-shops");
        System.out.println("2. Ανανέωση αποθέματος");
        System.out.println("3. Αναζήτηση και παραγγελία");
        System.out.println("4. Αναφορές");
        System.out.println("5. Αποθήκευση σε αρχείο");
        System.out.println("6. Έξοδος");
        System.out.print("Επιλογή: ");
    }
    
    private static int readChoice(Scanner sc) {

        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
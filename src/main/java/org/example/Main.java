package org.example;

import org.example.functions.CustomerFunctions;
import org.example.functions.FileFunctions;
import org.example.functions.RegistrationFunctions;
import org.example.functions.ReportFunctions;
import org.example.functions.StockFunctions;
import org.example.service.InitializeData;
import org.example.service.SkroutzManager;

import java.util.Scanner;

public class Main {

    private static final int REGISTRATION = 1;
    private static final int UPDATE_STOCK = 2;
    private static final int SEARCH_AND_ORDER = 3;
    private static final int REPORTS = 4;
    private static final int SAVE_TO_FILE = 5;
    private static final int EXIT = 6;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SkroutzManager manager = new SkroutzManager();
        new InitializeData().initialize(manager);

        RegistrationFunctions registrationFunctions = new RegistrationFunctions(sc);
        StockFunctions stockFunctions = new StockFunctions(sc);
        CustomerFunctions customerFunctions = new CustomerFunctions(sc);
        ReportFunctions reportFunctions = new ReportFunctions(sc);
        FileFunctions fileFunctions = new FileFunctions();

        int choice;

        System.out.println("===== Java_Skroutz System =====");

        do {
            printMenu();
            choice = readChoice(sc);

            switch (choice) {
                case REGISTRATION ->
                        registrationFunctions.insertProductsAndEshops(manager);

                case UPDATE_STOCK ->
                        stockFunctions.updateStock(manager);

                case SEARCH_AND_ORDER ->
                        customerFunctions.searchAndOrder(manager);

                case REPORTS ->
                        reportFunctions.showReports(manager);

                case SAVE_TO_FILE ->
                        fileFunctions.saveEshopsToFile(manager);

                case EXIT ->
                        System.out.println("Έξοδος από το πρόγραμμα.");

                default ->
                        System.out.println("Μη έγκυρη επιλογή.");
            }

        } while (choice != EXIT);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Βασικό Μενού =====");
        System.out.println("1. Καταχώρηση προϊόντων και e-shops");
        System.out.println("2. Ανανέωση αποθέματος προϊόντος/ων στο e-shop");
        System.out.println("3. Αναζήτηση και παραγγελία προϊόντος/ων");
        System.out.println("4. Αναζήτηση πληροφοριών - Αναφορές");
        System.out.println("5. Αποθήκευση τρέχουσας κατάστασης σε αρχείο");
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
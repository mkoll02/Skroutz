package org.example.functions;

import org.example.model.Eshop;
import org.example.service.SkroutzManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileFunctions {

    private static final String FOLDER_NAME = "files";
    private static final String FILE_NAME = "files/eshops_report.txt";

    public void saveEshopsToFile(SkroutzManager manager) {

        File folder = new File(FOLDER_NAME);

        if (!folder.exists()) {
            folder.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Eshop eshop : manager.getEshops()) {
                writer.println(
                        eshop.getWebsite() + ", " +
                                eshop.getAfm() + ", " +
                                eshop.getProductCount() + ", " +
                                manager.countOrdersForEshop(eshop) + ", " +
                                String.format("%.2f ευρώ", manager.getRevenueForEshop(eshop))
                );
            }

            System.out.println("Η τρέχουσα κατάσταση των e-shops αποθηκεύτηκε στο αρχείο: " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Σφάλμα κατά την αποθήκευση αρχείου: " + e.getMessage());
        }
    }
}
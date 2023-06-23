/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.imi;

/**
 *
 * @author carlos
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class comparadorFitxers {
    private static final String arxiuDiferencies = "/home/carlos/Documentos/AppsIMI/diferencias.txt";
    private static final String arxiuNomsDiferencies = "/home/carlos/Documentos/AppsIMI/nombres_diferencias.txt";
    private static int contadorDiferencies = 0;

    public static void main(String[] args) throws IOException {
        String fitxer1 = "/home/carlos/Documentos/AppsIMI/recursos-usuari.componentSPSCUESBSPA.html";
        String fitxer2 = "/home/carlos/Documentos/AppsIMI/recursos-usuari.componentSPSSIRIUSSPA.html";

        List<String> liniesFitxer1 = llegirFitxer(fitxer1);
        List<String> liniesFitxer2 = llegirFitxer(fitxer2);

        comparaArxius(liniesFitxer1, liniesFitxer2);

        if (contadorDiferencies > 10) {
            System.out.println(fitxer1 + fitxer2);
        }
    }

    private static List<String> llegirFitxer(String filePath) {
        List<String> linies = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                linies.add(linia);
            }
        } catch (IOException e) {
            System.err.println("Error al llegir l'arxiu: " + filePath);
            e.printStackTrace();
        }

        return linies;
    }

    private static void comparaArxius(List<String> arxiuLinies1, List<String> arxiuLinies2) throws IOException {
        int numLinies = Math.max(arxiuLinies1.size(), arxiuLinies2.size());

        try (FileWriter writer = new FileWriter(arxiuDiferencies, true)) {
            for (int i = 0; i < numLinies; i++) {
                if (i < arxiuLinies1.size() && i < arxiuLinies2.size()) {
                    if (!arxiuLinies1.get(i).equals(arxiuLinies2.get(i))) {
                        writer.write("Diferencia en la linea " + (i + 1) + ":\n");
                        writer.write("Arxiu 1: " + arxiuLinies1.get(i) + "\n");
                        writer.write("Arxiu 2: " + arxiuLinies2.get(i) + "\n");
                        writer.write("\n");
                        contadorDiferencies++;
                    }
                } else if (i < arxiuLinies1.size()) {
                    writer.write("El arxiu 2 no te la linea " + (i + 1) + "\n");
                    writer.write("Arxiu 1: " + arxiuLinies1.get(i) + "\n");
                    writer.write("\n");
                    contadorDiferencies++;
                } else if (i < arxiuLinies2.size()) {
                     writer.write("El arxiu 1 no te la linea " + (i + 1) + "\n");
                    writer.write("Arxiu 2: " + arxiuLinies1.get(i) + "\n");
                    writer.write("\n");
                    contadorDiferencies++;
                } 
            }
   
    }
    }
}
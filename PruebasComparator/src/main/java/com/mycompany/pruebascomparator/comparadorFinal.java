/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebascomparator;

/**
 *
 * @author carlos & jhamel 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    

public class comparadorFinal extends JFrame {
    private static Container contents;
    private static final String arxiuComparacions = "/home/carlos/Documentos/AppsIMI/comparaciones.txt";
    //Acuerdate de crear un nuevo archivo cuando el Jhamel me de su parte del codigo
    public static void main(String[] args) {
        String nomFitxer1 = "/home/carlos/Documentos/AppsIMI/a.txt";
        String nomFitxer2 = "/home/carlos/Documentos/AppsIMI/d.txt";

        List<String> liniesFitxer1 = llegirFitxer(nomFitxer1);
        List<String> liniesFitxer2 = llegirFitxer(nomFitxer2);

        comparaArxius(liniesFitxer1, liniesFitxer2, nomFitxer1, nomFitxer2);
    }

comparadorFinal(int alto, int ancho){
    setTitle("Comparador De Archivos");
    setLocation(380,50);
    JPanel panel = new JPanel();
    contents = getContentPane();
    contents.add(panel, BorderLayout.CENTER);
    pack();
    setVisible(true);
    
}

public void contruirMenu(){
    JMenuBar barraMenu = new JMenuBar();
    setJMenuBar(barraMenu);
    JMenu opciones = new JMenu("Opciones Menu");
    barraMenu.add(opciones);
    
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

    private static void comparaArxius(List<String> arxiuLinies1, List<String> arxiuLinies2, String nomFitxer1, String nomFitxer2) {
        int numLinies = Math.max(arxiuLinies1.size(), arxiuLinies2.size());
        int diferencias = 0;

        for (int i = 0; i < numLinies; i++) {
            if (i < arxiuLinies1.size() && i < arxiuLinies2.size()) {
                if (!arxiuLinies1.get(i).equals(arxiuLinies2.get(i))) {
                    diferencias++;
                    System.out.println("Diferencia en la linea " + (i + 1) + ":");
                    System.out.println("Arxiu 1: " + arxiuLinies1.get(i));
                    System.out.println("Arxiu 2: " + arxiuLinies2.get(i));
                    System.out.println();
                }
            } else if (i < arxiuLinies1.size()) {
                diferencias++;
                System.out.println("El arxiu 2 no te la linea " + (i + 1));
                System.out.println("Arxiu 1: " + arxiuLinies1.get(i));
                System.out.println();
            } else if (i < arxiuLinies2.size()) {
                diferencias++;
                System.out.println("El arxiu 1 no te la linea " + (i + 1));
                System.out.println("Arxiu 2: " + arxiuLinies2.get(i));
                System.out.println();
            }
        }

        if (diferencias > 10) {
            arxiuComparacions(nomFitxer1, nomFitxer2);
        }
    }

    private static void arxiuComparacions(String nomFitxer1, String nomFitxer2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arxiuComparacions, true))) {
            writer.append("Els següens arxius tenen el codi diferent: ");
            writer.append(nomFitxer1);
            writer.append(", ");
            writer.append(nomFitxer2);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escriure en el arxiu de comparacions.");
            e.printStackTrace();
        }
    }
}
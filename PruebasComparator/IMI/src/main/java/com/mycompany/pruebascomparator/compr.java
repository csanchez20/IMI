/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pruebascomparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class compr extends javax.swing.JFrame {
    private static final String arxiuComparacions = "/home/carlos/Documentos/AppsIMI/noms_comparacions.txt";

    /**
     * Creates new form comparadorPrueba
     */
    public compr() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Archivo 1");

        jLabel2.setText("Archivo 2");

        jButton2.setText("Buscar archivo 1 ...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar archivo 2 ...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }

    public String nomFitxer1 = "";
    public String nomFitxer2 = "";

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser src = new JFileChooser();
        int option = src.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            nomFitxer1 = src.getSelectedFile().getPath();
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser src2 = new JFileChooser();
        int option2 = src2.showOpenDialog(this);
        if (option2 == JFileChooser.APPROVE_OPTION) {
            nomFitxer2 = src2.getSelectedFile().getPath();
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        List<String> liniesFitxer1 = llegirFitxer(nomFitxer1);
        List<String> liniesFitxer2 = llegirFitxer(nomFitxer2);
        if (evt.getSource() == jButton1) {
            comparaArxius(liniesFitxer1, liniesFitxer2, nomFitxer1, nomFitxer2);
        }
    }

    public static List<String> llegirFitxer(String filePath) {
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

    public void comparaArxius(List<String> arxiuLinies1, List<String> arxiuLinies2, String nomFitxer1, String nomFitxer2) {
        int numLinies = Math.max(arxiuLinies1.size(), arxiuLinies2.size());
        int diferencias = 0;

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numLinies; i++) {
            if (i < arxiuLinies1.size() && i < arxiuLinies2.size()) {
                if (!arxiuLinies1.get(i).equals(arxiuLinies2.get(i))) {
                    diferencias++;
                    output.append("Diferencia en la linea ").append(i + 1).append(":\n");
                    output.append("Arxiu 1: ").append(arxiuLinies1.get(i)).append("\n");
                    output.append("Arxiu 2: ").append(arxiuLinies2.get(i)).append("\n\n");
                }
            } else if (i < arxiuLinies1.size()) {
                diferencias++;
                output.append("El arxiu 2 no te la linea ").append(i + 1).append("\n");
                output.append("Arxiu 1: ").append(arxiuLinies1.get(i)).append("\n\n");
            } else if (i < arxiuLinies2.size()) {
                diferencias++;
                output.append("El arxiu 1 no te la linea ").append(i + 1).append("\n");
                output.append("Arxiu 2: ").append(arxiuLinies2.get(i)).append("\n\n");
            }
        }

        if (diferencias > 10) {
            arxiuComparacions(nomFitxer1, nomFitxer2);
        }

        jTextArea1.setText(output.toString());
    }

    public void arxiuComparacions(String nomFitxer1, String nomFitxer2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arxiuComparacions, true))) {
            writer.append("Els següens arxius tenen el codi diferent:\n");
            writer.append(nomFitxer1);
            writer.append(",\n ");
            writer.append(nomFitxer2);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escriure en el arxiu de comparacions.");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(compr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(compr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(compr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(compr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration                   
}


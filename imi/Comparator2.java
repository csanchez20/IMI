/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.imi;

import java.io.*;

/**
 *
 * @author jfunes
 */
public class Comparator2{
    public static void LeerFichero(File file1, File file2){
        try{
            String NumLineaDist="";
            if(file1.exists() && file2.exists()){
                BufferedReader r1= new BufferedReader(new FileReader(file1));
                BufferedReader r2= new BufferedReader(new FileReader(file2));
                String sCadena1="";
                String sCadena2="";
                System.out.println("Comparando Ficheros " + file1.getName() + " y " + file2.getName());
                int c1=0;
                int c2=0;
                int c3=0;
                while(sCadena1!=null || sCadena2!=null){
                    sCadena1=r1.readLine();
                    sCadena2=r2.readLine();
                    if(sCadena1!=null){
                        c1++;
                    }
                    if(sCadena2!=null){
                        c2++;
                    }
                    c3++;
                    if (sCadena1 != null && sCadena2 != null) {
                        //Si no son iguales las lineas
                        if (!sCadena1.trim().toUpperCase().equals(sCadena2.trim().toUpperCase())) {
                            NumLineaDist += "," + c3;
                        }
                    } else {
                        //si no es la ultima entrada al while donde ambos son null
                        if (!(sCadena1 == null && sCadena2 == null)) {
                            NumLineaDist += "," + c3;
                        }
                    }
                    
                }
                System.out.println("Fin Comparacion ");
                System.out.println(file1.getPath()+ " Tiene " + c1 + " Lineas");
                System.out.println(file2.getPath()+ " Tiene " + c2 + " Lineas");
                System.out.println("Las Lineas Distintas son " + NumLineaDist);
                /*Cierra el flujo*/
                r1.close();
                r2.close();
            } else {
                System.out.println("Alguno De Los Ficheros No Existe");
            }
        }catch (Exception o){
            System.out.println(o.getMessage());
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        File file1 = new File("/home/carlos/Documentos/AppsIMI/fitxa-usuari.componentSPSCUESBSPA.html");
        File file2 = new File("/home/carlos/Documentos/AppsIMI/fitxa-usuari.componentSPSSIRIUSSPA.html");
        
        LeerFichero(file1, file2);
        
        
        
    }
//        FileFilter filtro = new FileFilter() {
//            @Override
//            public boolean accept(File arch) {
//                return arch.isFile();
//            }
//        };
//        
//        File carpeta = new File("/home/jfunes/NetBeansProjects/PruebasComparator/src/SPSAGENTSSPA-develop");
//        File [] archivos = carpeta.listFiles(filtro);
//        
//        if(archivos == null || archivos.length == 0){
//            System.out.println("No hay elementos en esta carpeta");
//        }else{
//            for (int i = 0; i < archivos.length; i++) {
//                File archivo = archivos[i];
//                System.out.println(archivo.getName());
//            }
//        }
            
            
            
    }


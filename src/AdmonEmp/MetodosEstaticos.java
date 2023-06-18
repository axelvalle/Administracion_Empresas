/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdmonEmp;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Gateway
 */
//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS
public class MetodosEstaticos {
    public static String fechaonly(){    
    
    Date fecha= new Date();
    
    DateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY | hh:mm:ss");
    
    return formatoFecha.format(fecha);
    
}
 public static void mostrarArchivo(String Archivo){
                try{
                File fichero = new File(Archivo);
                if(!Desktop.isDesktopSupported()){
                    JOptionPane.showMessageDialog(null,"No es compatible con el SO","ERROR",JOptionPane.ERROR);
                    return;

                }
                Desktop desk = Desktop.getDesktop();
                if(fichero.exists()){
                desk.open(fichero);
                }else{
                JOptionPane.showMessageDialog(   null, "El archivo que solicitas NO EXISTE","ERROR DE ARCHIVO",JOptionPane.ERROR);
                }
                }catch(IOException e){
                }
                }

    
    public static void eliminardelaTabla(DefaultTableModel tablaModelo, JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila != -1) { 
            tablaModelo.removeRow(fila);
        }else{
        JOptionPane.showMessageDialog(null, "PORFAVOR SELECCIONE UNA FILA DE LA TABLA");
        
        }
    }
    
    public static void guardarDatos(DefaultTableModel tablaModelo,String Archivo) {
        File f; 
        FileWriter fw;
        BufferedWriter bw;
        try {   
            f = new File(Archivo);
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < tablaModelo.getRowCount(); i++) {
                for (int j = 0; j < tablaModelo.getColumnCount(); j++) {
                    bw.write(tablaModelo.getValueAt(i, j).toString() + ",");
                }
                bw.write("\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public static void cargarTabla(DefaultTableModel tabla, String archivo) {
        try {
            File prod = new File(archivo);
            FileReader fr = new FileReader(prod);
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while ((cadena=br.readLine()) != null) {
                String[] registro = cadena.split(",");
                tabla.addRow(registro);
            }
            br.close();
            fr.close();
        } catch(IOException e) {
             System.out.println("Ha ocurrido un error");
        }
    }
    
  public static void restarCantidad(DefaultTableModel modeloFactura) {
    String rutaArchivo = "ProductosAgregados.txt";
    try {
        FileReader f = new FileReader(rutaArchivo);
        BufferedReader br = new BufferedReader(f);
        ArrayList<String> lineasArrayL = new ArrayList<>();
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            String nombreProducto = datos[0];
            int cantidadInventario = Integer.parseInt(datos[3]);  

            for (int i = 0; i < modeloFactura.getRowCount(); i++) {

                if (nombreProducto.equalsIgnoreCase((String) modeloFactura.getValueAt(i, 0))) {
                    int cantidadFactura = Integer.parseInt(modeloFactura.getValueAt(i, 1).toString());
                    cantidadInventario -= cantidadFactura;
                    break;
                }
            }

            lineasArrayL.add(datos[0] + "," + datos[1] + "," + datos[2] + "," + cantidadInventario);
        }

        FileWriter fw = new FileWriter(rutaArchivo);

        for (String nuevaLinea : lineasArrayL) {
            fw.write(nuevaLinea + "\n");
        }

        br.close();
        fw.close();

    } catch (IOException e) {
        e.printStackTrace();
     }
}




    
}


//PROYECTO DE PROGRAMACISON 2, HECHO POR AXEL VALLE ING DE SISTEMAS
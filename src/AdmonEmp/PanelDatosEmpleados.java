/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS

package AdmonEmp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


public class PanelDatosEmpleados extends JPanel {
        JLabel lblNombre,lblEdad,lblSalario,lblCodigo,lblCargo,lblTituloTabla;
        JTextField txtNomb,txtEdad,txtSalario,txtcodigo;
        DefaultTableModel modeloEmpleados;
        JTable tablaEmpleados;
        JComboBox<String> combo;
        JButton btnAgregarDatos,btnEliminar,btnLimpiarDatos,btnPlanilla;
        Font comicSansFont;
        
public PanelDatosEmpleados(){
    this.setLayout(null);
    this.setBounds(45,55,300,250);
    this.setBorder(new TitledBorder(new EtchedBorder(),"Datos Empleados"));
    this.setBackground(new Color(173, 216, 230));
    comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 16);
    
    lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(comicSansFont);
    lblNombre.setBounds(20, 50, 700, 30);
    this.add(lblNombre);
    txtNomb = new JTextField();
    txtNomb.setBounds(122, 50, 700, 30);
    txtNomb.setFont(comicSansFont);
    this.add(txtNomb);
    lblEdad = new JLabel("Edad:");
    lblEdad.setBounds(20, 100, 700, 30);
    lblEdad.setFont(comicSansFont);
    this.add(lblEdad);
    txtEdad = new JTextField("");
    txtEdad.setBounds(122, 100, 500, 30);
    txtEdad.setFont(comicSansFont);
    this.add(txtEdad);
    lblSalario = new JLabel("Salario:");
    lblSalario.setBounds(20, 150, 700, 30);
    lblSalario.setFont(comicSansFont);
    this.add(lblSalario);
    txtSalario = new JTextField();
    txtSalario.setBounds(122, 150, 500, 30);
    txtSalario.setFont(comicSansFont);
    this.add(txtSalario);
    lblCodigo = new JLabel("Codigo:");
    lblCodigo.setBounds(20, 200, 700, 30);
    lblCodigo.setFont(comicSansFont);
    this.add(lblCodigo);
    txtcodigo = new JTextField();
    txtcodigo.setBounds(122, 200, 500, 30);
    txtcodigo.setFont(comicSansFont);
    this.add(txtcodigo);
    
    
    
    modeloEmpleados = new DefaultTableModel();
    modeloEmpleados.addColumn("Nombre");
    modeloEmpleados.addColumn("Edad");
    modeloEmpleados.addColumn("Salario");
    modeloEmpleados.addColumn("Codigo");
    modeloEmpleados.addColumn("Cargo");



    tablaEmpleados = new JTable(modeloEmpleados);
    tablaEmpleados.setModel(modeloEmpleados);
    tablaEmpleados.setOpaque(true);
    tablaEmpleados.setBackground(new Color(248,248,255));
    tablaEmpleados.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));

    JScrollPane sc = new JScrollPane(tablaEmpleados);
    sc.setBounds(20,300,800,210);
    sc.getViewport().setBackground(Color.white);
    this.add(sc);
    
    MetodosEstaticos.cargarTabla(modeloEmpleados, "BDPlanilla.txt");
            
    lblTituloTabla = new JLabel("PLANILLA DE EMPLEADOS AGREGADOS");
    lblTituloTabla.setBounds(20, 270, 700, 30);
    lblTituloTabla.setFont(new Font("Arial Black", Font.BOLD, 14));
    this.add(lblTituloTabla);
    lblCargo = new JLabel("Cargo: ");
    lblCargo.setBounds(900,50,300,40);
    lblCargo.setFont(comicSansFont);
    this.add(lblCargo);
    
    combo = new JComboBox<>();
    combo.addItem("Secretaria");
    combo.addItem("Contador");
    combo.addItem("Analista");
    combo.addItem("Programador");
    combo.setBounds(970,50,450,40);
    combo.setFont(comicSansFont);
    this.add(combo);
    
    btnAgregarDatos = new JButton("Agregar");
    btnAgregarDatos.setBounds(970,300,450,40);
    btnAgregarDatos.addActionListener(new eventos());
    btnAgregarDatos.setFont(comicSansFont);
    btnAgregarDatos.setForeground(Color.BLACK);
    btnAgregarDatos.setBackground(Color.white);
    Image iconAg=new ImageIcon("boton-agregar.png").getImage();
    ImageIcon IconagEscal= new ImageIcon(iconAg.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    btnAgregarDatos.setIcon(IconagEscal);
    this.add(btnAgregarDatos);
    
    btnEliminar = new JButton("Eliminar");
    btnEliminar.setBounds(970,350,450,40);
    btnEliminar.addActionListener(new eventos());
    btnEliminar.setFont(comicSansFont);
    btnEliminar.setForeground(Color.BLACK); 
    btnEliminar.setBackground(Color.white);
    Image iconElim=new ImageIcon("eliminar.png").getImage();
    ImageIcon IconElimEscal= new ImageIcon(iconElim.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    btnEliminar.setIcon(IconElimEscal);
    this.add(btnEliminar);
    
    btnLimpiarDatos = new JButton("Limpiar");
    btnLimpiarDatos.setBounds(970, 400, 450, 40);
    btnLimpiarDatos.addActionListener(new eventos());
    btnLimpiarDatos.setFont(comicSansFont);
    btnLimpiarDatos.setForeground(Color.BLACK);
    btnLimpiarDatos.setBackground(Color.white);
    Image iconLimpiar=new ImageIcon("limpiar.png").getImage();
    ImageIcon IconLimpiarEscal= new ImageIcon(iconLimpiar.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    btnLimpiarDatos.setIcon(IconLimpiarEscal);
    this.add(btnLimpiarDatos);
    
    btnPlanilla = new JButton("Generar Planilla");
    btnPlanilla.setBounds(970, 450, 450, 40);
    btnPlanilla.addActionListener(new eventos());
    btnPlanilla.setFont(comicSansFont);
    btnPlanilla.setForeground(Color.BLACK);
    btnPlanilla.setBackground(Color.WHITE);
    Image iconGuardar=new ImageIcon("disquete.png").getImage();
    ImageIcon IconGuardarEscal= new ImageIcon(iconGuardar.getScaledInstance(30,30,Image.SCALE_SMOOTH));
    btnPlanilla.setIcon(IconGuardarEscal);
    this.add(btnPlanilla);

    
    }


public void formatoPlanilla() {
    File f;
    FileWriter fw;
    BufferedWriter bw;

    try {

        f = new File("Planilla.txt");
        fw = new FileWriter(f);
        bw = new BufferedWriter(fw);

        // Cabecera de la planilla
        bw.write("+-----------------------------------------------+\n");
        bw.write("|               AIVOSys Inc.                    |\n");
        bw.write("|             Planilla del año 2023             |\n");
        bw.write("+----------------------+------------------------+\n");
        bw.write("| Nombre               | Salario                |\n");
        bw.write("+----------------------+------------------------+\n");

        double totalSalarios = 0; // Variable para sumar los salarios de los empleados

                // Datos de la planilla
         for (int i = 0; i < tablaEmpleados.getRowCount(); i++) {
             bw.write("| " + tablaEmpleados.getValueAt(i, 0).toString() + "   " +"\t\t");
             bw.write("| " + tablaEmpleados.getValueAt(i, 2).toString()+"\t\t\t" +"| " + "\n");
             totalSalarios += Double.parseDouble(tablaEmpleados.getValueAt(i, 2).toString());
         }


        // Total de salarios
        bw.write("+----------------------+------------------------+\n");
        bw.write("| Total a pagar:       | " + totalSalarios + "                 |\n");
        bw.write("+-----------------------------------------------+\n");

        bw.close();
        fw.close();

        JOptionPane.showMessageDialog(null, "Datos guardados correctamente en el archivo Planilla.txt", "Guardado exitoso", JOptionPane.INFORMATION_MESSAGE);
        MetodosEstaticos.mostrarArchivo("Planilla.txt");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "No se pudo guardar los datos", "Error de guardado", JOptionPane.ERROR_MESSAGE);
        System.out.println("Ha ocurrido un error con el guardado");
    }
}

// Método para guardar los datos en el archivo
    
    public void agregarDatos(){
    String datos[]= new String[5];
    datos[0] = txtNomb.getText();
    datos[1] = txtEdad.getText();
    datos[2] = txtSalario.getText();
    datos[3] = txtcodigo.getText();
    datos[4] = (String) combo.getSelectedItem();
    modeloEmpleados.addRow(datos);


    }
    public void limpiardatos() {
    int numFilas = modeloEmpleados.getRowCount();
    for (int i = numFilas - 1; i >= 0; i--) {
        modeloEmpleados.removeRow(i);
    }
    txtNomb.setText("");
    txtcodigo.setText("");
    txtEdad.setText("");
    txtSalario.setText("");
}

    public class eventos implements ActionListener{

        @Override
    public void actionPerformed(ActionEvent eventos) {
        if(eventos.getSource()==btnAgregarDatos){
            // Verificar si los campos de texto están vacíos
            if(txtNomb.getText().isEmpty() || txtEdad.getText().isEmpty() || txtSalario.getText().isEmpty() || txtcodigo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.","Atención",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La acción se realizó con éxito.","Atencion",JOptionPane.INFORMATION_MESSAGE);
                agregarDatos();
                  txtNomb.setText("");
                  txtcodigo.setText("");
                  txtEdad.setText("");
                  txtSalario.setText("");
                  MetodosEstaticos.guardarDatos(modeloEmpleados, "BDPlanilla.txt");
            }
        }
        else if(eventos.getSource()==btnEliminar){

            int numeroFilas = modeloEmpleados.getRowCount();
            // Verificar si la tabla está vacía
            if(numeroFilas == 0){
                JOptionPane.showMessageDialog(null,"La tabla está vacía.");
            }
            // Verificar si se ha seleccionado una fila
            else if(tablaEmpleados.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila para eliminar.");
            } else {
                JOptionPane.showMessageDialog(null,"Se ha eliminado con éxito.");
                MetodosEstaticos.eliminardelaTabla(modeloEmpleados, tablaEmpleados);

            }
        }
        else if(eventos.getSource()==btnLimpiarDatos){
            JOptionPane.showMessageDialog(null,"Los datos han sido eliminados con exito");
            limpiardatos();
            MetodosEstaticos.guardarDatos(modeloEmpleados, "BDPlanilla.txt");
        }else if(eventos.getSource()==btnPlanilla){
            formatoPlanilla();
            } 
        } 
        }

    }

//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS

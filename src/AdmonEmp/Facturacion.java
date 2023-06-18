/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdmonEmp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gateway
 */
//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS
public class Facturacion extends JInternalFrame{
    JComboBox cbProductos;
    JTextField txtNombre, txtTelefono, txtDocumento, txtDireccion, txtPrecio, txtNombreProducto, txtCantidad, txtDescuento;
    JLabel lblTitulo, lblProductos, lblPrecio, lblNombProducto, lblCantidad, lblFecha, contenedorDeFecha, lblSubtotal, lblNumeroSubtotal, lblDescuento, lblIVA, lblNumIVA, lblTotal, lblNumTotal, lblNumDesc;
    JButton btnAgregar, btnEliminar, btnFacturar, btnLimpiar, btnCalcular;
    JCheckBox chkDescuento;
    DefaultTableModel modeloFactura;
    JTable tablaFactura;
    SpinnerNumberModel spModel;
    JSpinner spNumFactura;
    PanelDatosProductos panelP;
    public Facturacion(){
        this.add(new Panelcliente());
        this.add(new PanelFyF());
        this.add(new PanelproductosF());
        this.add(new Panelbotones());
        this.add(new Paneltotal());
        componentes();
    }
    public void componentes(){
    this.setLayout(null);
        this.setTitle("Facturación AIVOSys 2023");
        this.setClosable(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(new Color(176,196,222));
        lblTitulo = new JLabel();
        ImageIcon icono = new ImageIcon("facturacionlogo.png");
        Image imagen = icono.getImage();
        lblTitulo.setBounds(10, 1, 400, 100);
        ImageIcon iconoEscalado = new ImageIcon(imagen.getScaledInstance(lblTitulo.getWidth(),lblTitulo.getHeight(), Image.SCALE_SMOOTH));
        lblTitulo.setIcon(iconoEscalado);
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 30));
        lblTitulo.setForeground(Color.white);
        this.add(lblTitulo);
        panelP = new PanelDatosProductos();
        
        modeloFactura = new DefaultTableModel();
        modeloFactura.addColumn("Producto");
        modeloFactura.addColumn("Cantidad");
        modeloFactura.addColumn("Precio");
        modeloFactura.addColumn("Subtotal");
        
        tablaFactura = new JTable(modeloFactura);
        tablaFactura.setModel(modeloFactura);
        tablaFactura.setOpaque(true);
        JScrollPane sc = new JScrollPane(tablaFactura);
        sc.setBounds(570,100,630,240);
        sc.getViewport().setBackground(Color.white);
        this.add(sc);
    
    }
    public void limpiarDatos() {
        
    int filas = modeloFactura.getRowCount();
    for (int i = filas - 1; i >= 0; i--) {
    modeloFactura.removeRow(i);
}
    
    // Establece todos a sus valores iniciales
    lblNumeroSubtotal.setText("0,00");
    lblNumIVA.setText("0,00");
    txtDescuento.setText("0,00");
    lblNumTotal.setText("0,00");
    lblNumDesc.setText("0,00");
    txtCantidad.setText("");
    txtNombre.setText("");
    txtDocumento.setText("");
    txtCantidad.setText("");
    txtTelefono.setText("");
    txtDireccion.setText("");
    txtDescuento.setEnabled(false);
    chkDescuento.setSelected(false);
    
    }
    
     public void ActualizarDTCB(){
     CargarDatosCB ab = new CargarDatosCB();
        ab.start();     
     }

    
   public void agregarDatosTablas() {
    String[] fila = new String[4];
    fila[0] = txtNombreProducto.getText();
    fila[1] = txtCantidad.getText();
    fila[2] = txtPrecio.getText();
    
    // Validación de txtCantidad para asegurarnos de que es un número
    if (!txtCantidad.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido.","Error de CANTIDAD",JOptionPane.ERROR_MESSAGE);
        return;
    }

    float cantidad = Float.parseFloat(txtCantidad.getText());
    float valor = Float.parseFloat(txtPrecio.getText());
    float total = cantidad * valor;
    fila[3] = String.valueOf(total);
    modeloFactura.addRow(fila);
    calcularTotal();
    
}

        // La función "calcularTotal" calcula el subtotal, IVA, descuento y total de una factura.
        public void calcularTotal() {
        // Inicializa la variable subtotal en 0.
        float subtotal = 0;
        // Itera a través de cada fila del modelo de factura (tabla).
        for (int i = 0; i < modeloFactura.getRowCount(); i++) {
        // Añade el valor de la columna 3 de cada fila al subtotal.
        subtotal += Float.parseFloat(modeloFactura.getValueAt(i, 3).toString());
        }
        
        // Muestra el subtotal en una etiqueta de texto con formato de 2 decimales.
        lblNumeroSubtotal.setText(String.format("%.2f", subtotal));

        // Calcula el IVA como el 15% del subtotal.
        float iva = subtotal * 0.15f;
        // Muestra el IVA en una etiqueta de texto con formato de 2 decimales.
        lblNumIVA.setText(String.format("%.2f", iva));

        // Verifica si el checkbox de descuento está seleccionado.
        // Si está seleccionado, calcula el descuento que el usuario ponga del subtotal; de lo contrario, el descuento es 0.
        float descuento = chkDescuento.isSelected() ? subtotal * Float.parseFloat(txtDescuento.getText())/100: 0;
        // Muestra el descuento en una etiqueta de texto con formato de 2 decimales.
        lblNumDesc.setText(String.format("%.2f", descuento));

        // Calcula el total como la suma del subtotal y el IVA, menos el descuento.
        float total = subtotal + iva - descuento;
        // Muestra el total en una etiqueta de texto con formato de 2 decimales.
        lblNumTotal.setText(String.format("%.2f", total));

        
        }
        public void crearFactura(){
            //declaracion de los parametros
            File f; 
            FileWriter fw;
            BufferedWriter bf;
            //detalles de la factura utilizando get
            try{
               

                f = new File("FACTURA " + spNumFactura.getValue() + ".txt");

                 fw=new FileWriter(f);
                 bf = new BufferedWriter(fw);
                 bf.write("-----------------------------------------------------------------\n");
                 bf.write("\t\tEmpresa AIVO Systems Incorporated\n\n");
                 bf.write("\tNIT.180903\n");
                 bf.write("\tDirección: Bo.Carlos Fonseca.\n");
                 bf.write("\tTelefono: 2255-2020\n");
                 bf.write("\tNUMERO DE FACTURA N° " + spNumFactura.getValue()+"\n");
                 bf.write("=================================================================\n");
                 bf.write("Fecha y Hora:       "+contenedorDeFecha.getText()+"\n");
                 bf.write("Cliente:            "+txtNombre.getText()+"\n");
                 bf.write("Documento:          "+txtDocumento.getText()+"\n");
                 bf.write("Dirección           "+txtDireccion.getText()+"\n");
                 bf.write("Numero:             "+txtTelefono.getText()+"\n");
                 bf.write("=================================================================\n");
                 bf.write("Producto"+"       "+"Cantidad"+"    "+"Precio"+"   "+"Subtotal"+"\n");
                 bf.write("_________________________________________________________________\n");
                //buscar datos en la tabla e imprimirlos
                  for(int fila=0;fila<tablaFactura.getRowCount();fila++){
                     for(int columna=0;columna<tablaFactura.getColumnCount();columna++){
                        bf.write((String)tablaFactura.getValueAt(fila, columna)+"\t");
                     }
                    bf.newLine(); //hace un salto de linea
                 }
                 bf.write("\n");
                 bf.write("=================================================================\n");
                 bf.write("Subtotal "+ lblNumeroSubtotal.getText()+"\n");
                 bf.write("- Descuento del " + txtDescuento.getText()+ "%" + ":  "+ lblNumDesc.getText()+"\n");
                 bf.write("IVA 15%: " + lblNumIVA.getText()+"\n");
                 bf.write("TOTAL: "+lblNumTotal.getText()+"\n");
                 bf.write("================== MUCHAS GRACIAS POR SU COMPRA =================\n");
                 bf.write("===================PROGRAMACION 2 - 2M1-S========================\n");

                 bf.close(); //cierra el buffer y manda los datos al TXT
                 fw.close();
                 JOptionPane.showMessageDialog(null, "Su Factura ha sido REALIZADA CON EXITO");
                 MetodosEstaticos.mostrarArchivo("FACTURA "+spNumFactura.getValue() +".txt");
            }catch(IOException ex){
                System.out.println("ocurrio un error"+ex); //por si llega a haber algun error
            }
          }

        public void agregarDTdeCBaTXT(){
            String itemcbproducto = (String) cbProductos.getSelectedItem();            
            try {
                // Abrir el archivo y crear los objetos FileReader y BufferedReader
                File f = new File("ProductosAgregados.txt");
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);               
                // Leer el archivo línea por línea y buscar el elemento seleccionado
                String cadena;
                while ((cadena = bf.readLine()) != null) {
                    String[] registro = cadena.split(",");
                    if (registro[0].equals(itemcbproducto)) {
                        // Si se encuentra el elemento seleccionado, actualizar los valores de los campos de texto
                        txtNombreProducto.setText(registro[0]);
                        txtPrecio.setText(registro[2]);
                    }
                }
                
                // Cerrar los objetos FileReader y BufferedReader
                bf.close();
                fr.close();
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
            }            
            
}


    public class Panelcliente extends JPanel{ 

        public Panelcliente(){
        this.setLayout(null);
        this.setBounds(10, 100, 540, 90);
        this.setBackground(Color.white);
        this.setBorder(new TitledBorder (new EtchedBorder(),"Datos del cliente"));
        Font P1Font = new Font("Arial", Font.PLAIN, 16);
        
        JLabel lbl1= new JLabel("Nombre:");
        lbl1.setBounds(10,15,100,30);
        lbl1.setFont(P1Font);
        this.add(lbl1);
        txtNombre = new JTextField("");
        txtNombre.setBounds(110,15,150,30);
         this.add(txtNombre); 
         
         
        JLabel lbl2= new JLabel("Direccion:");
        lbl2.setBounds(10,50,100,30);
        lbl2.setFont(P1Font);
        this.add(lbl2);
        txtDireccion= new JTextField("");
        txtDireccion.setBounds(110,50,150,30);
         this.add(txtDireccion);
         
         
        JLabel lbl3= new JLabel("Documento:");
        lbl3.setBounds(290,12,100,30);
        lbl3.setFont(P1Font);
         this.add(lbl3);
        txtDocumento= new JTextField("");
        txtDocumento.setBounds(380,12,150,30);
         this.add(txtDocumento);
         
         
        JLabel lbl4= new JLabel("Telefono:");
        lbl4.setBounds(300,50,100,30);
        lbl4.setFont(P1Font);
        this.add(lbl4);
        txtTelefono= new JTextField("");
        txtTelefono.setBounds(380,50,150,30);
        this.add(txtTelefono);
         
        }
    }
    
    public class PanelFyF extends JPanel{
        public PanelFyF(){
        this.setLayout(null);
        
        this.setBounds(10, 230, 540, 100);
        this.setBackground(Color.WHITE);
        this.setBorder(new TitledBorder (new EtchedBorder(),"Datos de la factura"));
        Font P1Font = new Font("Arial", Font.PLAIN, 16);
        
        
         JLabel lbl2= new JLabel("Factura N°");
        lbl2.setBounds(10,30,200,30);
        lbl2.setFont(P1Font);
        lbl2.setForeground(new Color(0,0,0));
         this.add(lbl2);
         
        spModel = new SpinnerNumberModel(0, 0, 100, 1);
        spNumFactura = new JSpinner(spModel);
        spNumFactura.setBounds(140, 30, 50, 30);
        spNumFactura.setForeground(new Color(0, 0, 0));
        spNumFactura.setFont(P1Font);

        // Crear un editor no editable
        JFormattedTextField txt = ((JSpinner.DefaultEditor) spNumFactura.getEditor()).getTextField();
        txt.setEditable(false);

        this.add(spNumFactura);


  
        lblFecha= new JLabel("Fecha:");
        lblFecha.setBounds(260,30,150,30);
        lblFecha.setFont(P1Font);
        lblFecha.setForeground(new Color(0,0,0));
        this.add(lblFecha);
        contenedorDeFecha = new JLabel(MetodosEstaticos.fechaonly());
        contenedorDeFecha.setBounds(340,30,400,30);
        contenedorDeFecha.setFont(P1Font);
        contenedorDeFecha.setForeground(new Color(0,0,0));
        this.add(contenedorDeFecha);
         
}

        }
    
    public class PanelproductosF extends JPanel implements ItemListener{ 
        public PanelproductosF(){
        this.setLayout(null);
        this.setBorder(new EtchedBorder());
        this.setBounds(10, 360, 540, 90);
        this.setBackground(Color.white);
        this.setBorder(new TitledBorder (new EtchedBorder(),"Productos"));
        Font P1Font = new Font("Arial", Font.PLAIN, 16);
        
        lblProductos = new JLabel("Productos:");
        lblProductos.setBounds(10,15,100,30);
        lblProductos.setFont(P1Font);
        this.add(lblProductos);
        
        cbProductos=new JComboBox();
        cbProductos.setBounds(110,15,150,30);
        cbProductos.setFont(P1Font);
        cbProductos.addItemListener(this);
        this.add(cbProductos);
         
         
        lblPrecio= new JLabel("Precio:");
        lblPrecio.setBounds(10,50,100,30);
        lblPrecio.setFont(P1Font);
        this.add(lblPrecio);
        txtPrecio= new JTextField("");
        txtPrecio.setBounds(110,50,150,30);
        txtPrecio.setEditable(false);
         this.add(txtPrecio);
         
         
        lblNombProducto= new JLabel("Nombre:");
        lblNombProducto.setBounds(300,12,100,30);
        lblNombProducto.setFont(P1Font);
         this.add(lblNombProducto);
        txtNombreProducto= new JTextField("");
        txtNombreProducto.setBounds(380,12,150,30);
        txtNombreProducto.setEditable(false);
         this.add(txtNombreProducto);
         
         
        lblCantidad= new JLabel("Cantidad:");
        lblCantidad.setBounds(300,50,100,30);
        lblCantidad.setFont(P1Font);
        this.add(lblCantidad);
        txtCantidad= new JTextField("");
        txtCantidad.setBounds(380,50,150,30);
        this.add(txtCantidad);
         
        }

        @Override
        public void itemStateChanged(ItemEvent ie) {
            if (ie.getStateChange() == ItemEvent.SELECTED) {
            agregarDTdeCBaTXT();
        }
        }
    }
    
    public class Panelbotones extends JPanel{ 

    public Panelbotones(){
        this.setLayout(null);
        this.setBorder(new EtchedBorder());
        this.setBounds(10, 480, 540, 110);
        this.setBackground(Color.white);
        this.setBorder(new TitledBorder (new EtchedBorder()));
        Font P2Font = new Font("Arial",Font.PLAIN,20);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 15, 250, 80);
        Image iconAgre=new ImageIcon("mas.png").getImage();
        ImageIcon IconAgreEscal= new ImageIcon(iconAgre.getScaledInstance(70,70,Image.SCALE_SMOOTH));
        btnAgregar.setIcon(IconAgreEscal);
        btnAgregar.setBackground(Color.white);
        btnAgregar.setFont(P2Font);
        btnAgregar.addActionListener(new EventosFactura());
        this.add(btnAgregar);
        
        btnEliminar = new JButton("Eliminar");
        Image iconElim=new ImageIcon("menos.png").getImage();
        ImageIcon IconElimEscal= new ImageIcon(iconElim.getScaledInstance(70,70,Image.SCALE_SMOOTH));
        btnEliminar.setIcon(IconElimEscal);
        btnEliminar.setBounds(280, 15, 250, 80);
        btnEliminar.setFont(P2Font);
        btnEliminar.setBackground(Color.white);
        btnEliminar.addActionListener(new EventosFactura());
        this.add(btnEliminar);
        
        
    }
    }
        public class Paneltotal extends JPanel implements ItemListener{ 

            public Paneltotal(){
                this.setLayout(null);
                this.setBorder(new EtchedBorder());
                this.setBounds(570, 360, 630, 230);
                this.setBackground(Color.white);
                this.setBorder(new TitledBorder (new EtchedBorder(),"Finalizacion"));
                Font P2Font = new Font("Arial", Font.PLAIN, 17);

                lblSubtotal = new JLabel("Subtotal:");
                lblSubtotal.setBounds(20,10,100,100);
                lblSubtotal.setFont(P2Font);
                this.add(lblSubtotal);
                
                lblNumeroSubtotal = new JLabel("0,00");
                lblNumeroSubtotal.setBounds(120,10,100,100);
                lblNumeroSubtotal.setFont(P2Font);
                this.add(lblNumeroSubtotal);
            
                lblDescuento = new JLabel("Descuento %");
                lblDescuento.setBounds(20, 50, 100, 100);
                lblDescuento.setFont(P2Font);
                this.add(lblDescuento);
                txtDescuento = new JTextField("0");
                txtDescuento.setEnabled(false);
                txtDescuento.setBounds(130,85, 40, 30);
                this.add(txtDescuento);
                lblNumDesc = new JLabel("0,00");
                lblNumDesc.setBounds(180,85,150,30);
                lblNumDesc.setFont(P2Font);
                this.add(lblNumDesc);
                chkDescuento = new JCheckBox("Aplicar Descuento");
                chkDescuento.setBounds(250, 85, 150, 30);
                chkDescuento.setBackground(Color.white);
                chkDescuento.addItemListener(this);
                this.add(chkDescuento);
                lblIVA = new JLabel("IVA 15%");
                lblIVA.setBounds(20, 90, 100, 100);
                lblIVA.setFont(P2Font);
                this.add(lblIVA);
                lblNumIVA = new JLabel("0,00");
                lblNumIVA.setBounds(120, 90, 100, 100);
                lblNumIVA.setFont(P2Font);
                this.add(lblNumIVA);
                btnCalcular = new JButton("calcular descuento");
                btnCalcular.setBounds(250,50,120,20);
                btnCalcular.setBackground(Color.white);
                btnCalcular.addActionListener(new EventosFactura());
                this.add(btnCalcular);
                lblTotal = new JLabel("TOTAL:");
                lblTotal.setBounds(20,130,100,100);
                lblTotal.setFont(P2Font);
                this.add(lblTotal);
                
                lblNumTotal = new JLabel("0,00");
                lblNumTotal.setBounds(120, 130, 100, 100);
                lblNumTotal.setFont(P2Font);
                this.add(lblNumTotal);
                
                btnLimpiar = new JButton("Limpiar");
                Image iconLimpio = new ImageIcon("limpio.png").getImage();
                ImageIcon iconoLimpioEscalado = new ImageIcon(iconLimpio.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                btnLimpiar.setIcon(iconoLimpioEscalado);
                btnLimpiar.setFont(P2Font);
                btnLimpiar.addActionListener(new EventosFactura());
                btnLimpiar.setBackground(Color.white);
                btnLimpiar.setBounds(400, 50, 200, 70);
                this.add(btnLimpiar);
                
                btnFacturar = new JButton("Facturar");
                Image iconImprimi=new ImageIcon("factura.png").getImage();
                ImageIcon IconImprirEscal= new ImageIcon(iconImprimi.getScaledInstance(70,70,Image.SCALE_SMOOTH));
                btnFacturar.setBounds(400,130,200,70);
                btnFacturar.setIcon(IconImprirEscal);
                btnFacturar.setFont(P2Font);
                btnFacturar.addActionListener(new EventosFactura());
                btnFacturar.setBackground(Color.white);
                this.add(btnFacturar);

            }

        @Override
       public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            txtDescuento.setEnabled(true);
            calcularTotal();
        } else {
            txtDescuento.setEnabled(false);
            txtDescuento.setText("0");
            lblNumDesc.setText("0,00");
            calcularTotal();
        }
    }
            }
        
    public class CargarDatosCB extends Thread{
    public void run() {      
            for (int i = cbProductos.getItemCount() - 1; i >= 0; i--) {
            cbProductos.removeItemAt(i);
            }
            txtCantidad.setText("");
            txtPrecio.setText("");
            txtNombreProducto.setText("");
    try {
         File pspd ;
         BufferedReader br;
         FileReader fr;
         pspd = new File("ProductosAgregados.txt");
         fr = new FileReader(pspd);  
         br = new BufferedReader(fr);
        String cadena= br.readLine();
        while (cadena!=null){
        String reg[] = cadena.split(",");
        cbProductos.addItem(reg[0]);
        cadena=br.readLine();
        }
        br.close();
        fr.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }            
}

public class EventosFactura implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource()==btnAgregar){
                if(txtCantidad.getText().isEmpty()||txtPrecio.getText().isEmpty()||txtNombreProducto.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"AUN NO HAY PRODUCTOS AGREGADOS o NO HA PUESTO UNA CANTIDAD","Error",JOptionPane.ERROR_MESSAGE);
                }else{ 
                    agregarDatosTablas();
                    txtCantidad.setText("");
                }
                
            }else if(ae.getSource()==btnEliminar){
                    MetodosEstaticos.eliminardelaTabla(modeloFactura, tablaFactura);
                    calcularTotal();
            }else if(ae.getSource()==btnLimpiar){
            limpiarDatos();
            
            JOptionPane.showMessageDialog(null,"Se han borrado todos los datos con exito","LIMPIEZA EXITOSA",JOptionPane.INFORMATION_MESSAGE);
            }   else if(ae.getSource()==btnFacturar){
                if(txtNombre.getText().isEmpty()||txtDocumento.getText().isEmpty()||txtTelefono.getText().isEmpty()||txtDireccion.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "ERROR: Ingrese los datos del CLIENTE","ERROR DE CLIENTE",JOptionPane.ERROR_MESSAGE);
                    
                }
                else { spNumFactura.setValue((int) spNumFactura.getValue() + 1); 
                crearFactura();
                MetodosEstaticos.restarCantidad(modeloFactura);
                limpiarDatos();
                }
                
            }else if(ae.getSource()==btnCalcular){
                calcularTotal();
                        }
        }

     } 
}
//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS
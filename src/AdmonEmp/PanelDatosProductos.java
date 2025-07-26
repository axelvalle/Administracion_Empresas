//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS


package AdmonEmp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


public class PanelDatosProductos extends JPanel {
    JLabel lblProducto, lblProv, lblPrecio, lblCantidad,lblNota;
    JTextField txtProducto, txtPrecio, txtCantidad;
    DefaultTableModel modeloProd;
    JTable tablaProd;
    JButton btnAgregar,btnLimpiar,btnEliminar,btnGuardar;
    JComboBox<String> cbProv;
    
    public PanelDatosProductos() {
        this.setLayout(null);
        this.setBounds(45, 55, 300, 250);
        this.setBorder(new TitledBorder(new EtchedBorder(), "Datos Productos"));
        this.setBackground(new Color(64,224,208));
        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 16);

        lblNota = new JLabel("*NOTA: Recuerda guardar los datos de la tabla a la base de datos");
        lblNota.setBounds(20, 350, 700, 30);
        lblNota.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(lblNota);
        lblProducto = new JLabel("Producto");
        lblProducto.setBounds(20, 50, 700, 30);
        lblProducto.setFont(comicSansFont);
        this.add(lblProducto);
        txtProducto = new JTextField("");
        txtProducto.setBounds(122, 50, 700, 30);
        txtProducto.setFont(comicSansFont);
        this.add(txtProducto);
        lblProv = new JLabel("Proveedor:");
        lblProv.setBounds(20, 100, 700, 30);
        lblProv.setFont(comicSansFont);
        this.add(lblProv);
        
        cbProv = new JComboBox<>();
        cbProv.addItem("Cargill Centroamérica");
        cbProv.addItem("Alimentos Prosalud");
        cbProv.addItem("Grupo M");
        cbProv.addItem("Corporación Multi Inversiones (CMI)");
        cbProv.addItem("Grupo Lala");
        cbProv.addItem("Nestlé Centroamérica");
        cbProv.addItem("Grupo Pellas");
        cbProv.addItem("Corporación Pipasa");
        cbProv.addItem("Distribuidora La Universal");
        cbProv.addItem("Comercializadora Miraflores");
        cbProv.addItem("Agropecuaria La Trinidad");
        cbProv.addItem("Inversiones Montecarlo");
        cbProv.setBounds(122, 100, 700, 30);
        cbProv.setFont(comicSansFont);
        this.add(cbProv);
        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 150, 700, 30);
        lblPrecio.setFont(comicSansFont);
        this.add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(122, 150, 700, 30);
        txtPrecio.setFont(comicSansFont);
        this.add(txtPrecio);
        lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 200, 700, 30);
        lblCantidad.setFont(comicSansFont);
        this.add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(122, 200, 700, 30);
        txtCantidad.setFont(comicSansFont);
        this.add(txtCantidad);

        modeloProd= new DefaultTableModel();
        modeloProd.addColumn("Producto");
        modeloProd.addColumn("Proveedor");
        modeloProd.addColumn("Precio");
        modeloProd.addColumn("Cantidad");
        

        tablaProd = new JTable(modeloProd);
        tablaProd.setModel(modeloProd);
        tablaProd.setOpaque(true);
        tablaProd.setBackground(new Color(248,248,255));
        tablaProd.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        JScrollPane sc = new JScrollPane(tablaProd);
        sc.setBounds(827,20,590,500);
        sc.getViewport().setBackground(Color.white);
        this.add(sc);

        btnAgregar = new JButton();
        btnAgregar.setBounds(122, 250, 100, 50);
        btnAgregar.setFont(comicSansFont);
        btnAgregar.addActionListener(new eventosprod());
        btnAgregar.setBackground(Color.WHITE);
        Image iconAg=new ImageIcon("boton-agregar.png").getImage();
        ImageIcon IconagEscal= new ImageIcon(iconAg.getScaledInstance(30,30,Image.SCALE_SMOOTH));
        btnAgregar.setIcon(IconagEscal);
        this.add(btnAgregar);
        
        btnEliminar = new JButton();
        btnEliminar.setBounds(252, 250, 100, 50);
        btnEliminar.setFont(comicSansFont);
        btnEliminar.setBackground(Color.white);
        btnEliminar.addActionListener(new eventosprod());
        Image iconElim=new ImageIcon("eliminar.png").getImage();
        ImageIcon IconElimEscal= new ImageIcon(iconElim.getScaledInstance(30,30,Image.SCALE_SMOOTH));
        btnEliminar.setIcon(IconElimEscal);
        this.add(btnEliminar);

        btnLimpiar = new JButton();
        btnLimpiar.setBounds(362, 250, 100, 50);
        btnLimpiar.setFont(comicSansFont);
        btnLimpiar.setBackground(Color.white);
        btnLimpiar.addActionListener(new eventosprod());
        Image iconLimpiar=new ImageIcon("limpiar.png").getImage();
        ImageIcon IconLimpiarEscal= new ImageIcon(iconLimpiar.getScaledInstance(30,30,Image.SCALE_SMOOTH));
        btnLimpiar.setIcon(IconLimpiarEscal);
        this.add(btnLimpiar);

        btnGuardar = new JButton();
        btnGuardar.setBounds(472, 250, 100, 50);
        btnGuardar.setFont(comicSansFont);
        btnGuardar.setBackground(Color.white);
        btnGuardar.addActionListener(new eventosprod());
        Image iconGuardar=new ImageIcon("disquete2.png").getImage();
        ImageIcon IconGuardarEscal= new ImageIcon(iconGuardar.getScaledInstance(30,30,Image.SCALE_SMOOTH));
        btnGuardar.setIcon(IconGuardarEscal);
        this.add(btnGuardar);
        
        MetodosEstaticos.cargarTabla(modeloProd, "ProductosAgregados.txt");
    }
    
   
    
    
    public void agregarDatos(){
    String datos[]= new String[4]; // Cambiado a 4 porque solo hay 4 columnas
    datos[0] = txtProducto.getText();
    datos[1] = (String) cbProv.getSelectedItem();
    // Validación de txtPrecio y txtCantidad para asegurarnos de que son números válidos
    if (!txtPrecio.getText().matches("\\d+(\\.\\d{1,2})?")) {
        JOptionPane.showMessageDialog(null, "El precio debe ser un número válido con un máximo de dos decimales.", "Error de PRECIO", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!txtCantidad.getText().matches("\\d+(\\.\\d{1,2})?")) {
        JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido con un máximo de dos decimales.", "Error de CANTIDAD", JOptionPane.ERROR_MESSAGE);
        return;
    }
    datos[2] = txtPrecio.getText();
    datos[3] = txtCantidad.getText();
    modeloProd.addRow(datos);

    JOptionPane.showMessageDialog(null, "La acción se realizó con éxito.","AGREGACION EXITOSA",JOptionPane.INFORMATION_MESSAGE);
}
   
    
    public void limpiardatos() {

    int numFilas = modeloProd.getRowCount();
    for (int i = numFilas - 1; i >= 0; i--) {
        modeloProd.removeRow(i);
    }
    txtCantidad.setText("");
    txtProducto.setText("");
    txtPrecio.setText("");
    
}   
    public class eventosprod implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           if(ae.getSource()==btnAgregar){
        // Verificar si los campos de texto están vacíos
        if(txtCantidad.getText().isEmpty() || txtProducto.getText().isEmpty() || txtPrecio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.","Atención",JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            agregarDatos();
            txtProducto.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");
        }
    } else if(ae.getSource()==btnEliminar){
        int numRows = modeloProd.getRowCount();
        // Verificar si la tabla está vacía
        if(numRows == 0){
            JOptionPane.showMessageDialog(null,"La tabla no contiene ningun producto.");
        }
        // Verificar si se ha seleccionado una fila
        else if(tablaProd.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila para eliminar.");
        } else {
            JOptionPane.showMessageDialog(null,"Se ha eliminado con éxito.");
            MetodosEstaticos.eliminardelaTabla(modeloProd, tablaProd);
            
        }
    }else if(ae.getSource()==btnLimpiar){
        
        limpiardatos();
        JOptionPane.showMessageDialog(null,"Se ha limpiado con exito","LIMPIEZA",JOptionPane.INFORMATION_MESSAGE);
        
     
    } else if(ae.getSource()==btnGuardar){
        MetodosEstaticos.guardarDatos(modeloProd, "ProductosAgregados.txt");
        JOptionPane.showMessageDialog(null, "LOS PRODUCTOS SE HAN GUARDADO EXITOSAMENTE","GUARDADO EXITOSO",JOptionPane.INFORMATION_MESSAGE);
    }
    
    }
        }
    
    }
    

//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS

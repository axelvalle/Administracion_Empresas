package AdmonEmp;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaInventario extends JDialog {

    public JLabel lblInventario;
    public JTable tablaProd;
    public DefaultTableModel modeloProd;
    public TablaInventario() {
        setTitle("Inventario");
        setBounds(100, 100, 650, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        modeloProd = new DefaultTableModel();
        modeloProd.addColumn("Producto");
        modeloProd.addColumn("Proveedor");
        modeloProd.addColumn("Precio");
        modeloProd.addColumn("Cantidad");

        tablaProd = new JTable(modeloProd);
        tablaProd.setOpaque(true);
        tablaProd.setBackground(new Color(248, 248, 255));
        tablaProd.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        JScrollPane sc = new JScrollPane(tablaProd);
        sc.setBounds(20, 60, 600, 500);
        sc.getViewport().setBackground(Color.white);
        this.add(sc);
        
        MetodosEstaticos.cargarTabla(modeloProd, "ProductosAgregados.txt");
    }}
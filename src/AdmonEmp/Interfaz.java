/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS
package AdmonEmp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author Gateway
    
 */
public final class Interfaz extends JFrame{
    
    JToolBar barraArriba,barraAbajo;
    JButton btneditar,btnsalir,btnguardar,btnfactura;
    JMenuBar menubar;
    JMenu mnufile,mnutool,mnuprocess,mnusettings;
    JMenuItem jmnguardar,jmnsalir,derechos,terms,avisolegal,jmnfacturacion,jmnlistadofac,jmnInventario;
    JLabel lblfecha,titulo,subtitulo,logo;
    String fechacadena;
    JTabbedPane pestañas;
    Font comicSansFont;
    JDesktopPane desk;
    Facturacion fact;
    PanelDatosEmpleados panel;
    PanelDatosProductos panelProductos;
    TablaInventario ti;
    
    public Interfaz(){
        components();
            }
    
    public void components(){
    this.setTitle("Sistema de administracion de empresas: AIVOSys");
        this.setSize(1448,865);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(255, 242, 204));
        desk= new JDesktopPane();
        fact=new Facturacion();
        desk.add(fact);
        desk.setBackground(new Color(255,250,250));
        fact.setBounds(20, 40, 1250, 700);
        this.setContentPane(desk);

                comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 16);

                titulo = new JLabel("SISTEMA DE ADMINISTRACION DE EMPRESAS");
                    titulo.setBounds(10,70,900,50);
                    titulo.setFont(new Font("Century Gothic",Font.PLAIN,30));
                    this.add(titulo);
                subtitulo = new JLabel("Derechos de autor: Axel Valle, IES-UNI, PROGRAMACION 2");
                    subtitulo.setBounds(10,95,900,50);
                    subtitulo.setFont(comicSansFont);

                    logo = new JLabel();
                        logo.setBounds(1000,50,400,136);
                        ImageIcon icono = new ImageIcon("aivosys.png"); 
                        Image imagen = icono.getImage(); 
                        ImageIcon iconoEscalado = new ImageIcon(imagen.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH)); // escala la imagen al tamaño del label
                        logo.setIcon(iconoEscalado); 
                        this.add(logo);
                    this.add(subtitulo);

                barraArriba=new JToolBar();
                    barraArriba.setBounds(0,0,2000,40);
                    barraArriba.setBorder(new EtchedBorder());
                    barraArriba.setBackground(new Color(64,224,208));
                    this.add(barraArriba);
                barraAbajo=new JToolBar();
                    barraAbajo.setBounds(0,774,1440,40);
                    barraAbajo.setBorder(new EtchedBorder());
                    barraAbajo.setBackground(new Color(92, 197, 240));
                    this.add(barraAbajo);

                    
                        lblfecha = new JLabel(MetodosEstaticos.fechaonly());
                        lblfecha.setFont(comicSansFont);
                    barraAbajo.add(lblfecha);

                    barraAbajo.setLayout(new BorderLayout());
                    barraAbajo.add("East",lblfecha);
                    JLabel copyright = new JLabel("AIVOSys Inc. Copyright 2023");
                        copyright.setFont(comicSansFont);
                    barraAbajo.add(copyright);

                panel = new PanelDatosEmpleados();
                panelProductos = new PanelDatosProductos();
                Image iconEmp=new ImageIcon("empleados.png").getImage();
                    ImageIcon IconEmpEsc = new ImageIcon(iconEmp.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                    JLabel labelIMG = new JLabel("Empleados", IconEmpEsc, JLabel.CENTER);
                labelIMG.setFont(comicSansFont);

                pestañas = new JTabbedPane();
                    pestañas.setBounds(10, 160, 1430, 600);
                    pestañas.setBackground(new Color(220,220,220));
                    pestañas.addTab(null, panel);
                    pestañas.setTabComponentAt(0, labelIMG);


                Image iconProd = new ImageIcon("productos.png").getImage();
                    ImageIcon IconProdEsc = new ImageIcon(iconProd.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                    JLabel iconProdlbl = new JLabel("Productos",IconProdEsc,JLabel.CENTER);
                    iconProdlbl.setFont(comicSansFont);

                pestañas.addTab("Productos", panelProductos);
                pestañas.setTabComponentAt(1, iconProdlbl);

               

                this.add(pestañas);

                menu();


                btnsalir= new JButton("Salir");
                    btnsalir.setBounds(10,100,100,100);
                    Image iconSal=new ImageIcon("Salir.png").getImage();
                    ImageIcon IconsalEscal= new ImageIcon(iconSal.getScaledInstance(30,30,Image.SCALE_SMOOTH));
                    btnsalir.setIcon(IconsalEscal);
                    btnsalir.addActionListener(new EventosDeJM());
                    btnsalir.setFont(comicSansFont);
                    barraArriba.add(btnsalir);

                btnguardar= new JButton("Guardar");
                    btnguardar.setBounds(110,100,100,100);
                    Image iconGua=new ImageIcon("Botton guardar.png").getImage();
                    ImageIcon IconEscGua= new ImageIcon(iconGua.getScaledInstance(30,30,Image.SCALE_SMOOTH));
                    btnguardar.setIcon(IconEscGua);
                    btnguardar.addActionListener(new EventosDeJM());
                    btnguardar.setFont(comicSansFont);
                    barraArriba.add(btnguardar);

                btneditar= new JButton("Editar");
                    btneditar.setBounds(210,100,100,100);
                    Image iconEdi=new ImageIcon("Editar.png").getImage();
                    ImageIcon IconEditar= new ImageIcon(iconEdi.getScaledInstance(30,30,Image.SCALE_SMOOTH));
                    btneditar.setIcon(IconEditar);
                    btneditar.setFont(comicSansFont);
                    btneditar.addActionListener(new EventosDeJM());
                    barraArriba.add(btneditar);
                btnfactura = new JButton("Facturar");
                    btnfactura.setBounds(310,100,100,100);
                    Image iconFac = new ImageIcon("facticon.png").getImage();
                    ImageIcon IconFacEsc = new ImageIcon(iconFac.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                    btnfactura.setIcon(IconFacEsc);
                    btnfactura.setFont(comicSansFont);
                    btnfactura.addActionListener(new EventosDeJM());
                    barraArriba.add(btnfactura);
    }
                public void menu(){
                    menubar = new JMenuBar();
                    this.setJMenuBar(menubar);
                    mnufile= new JMenu("Archivos");
                    mnutool = new JMenu("Herramientas");
                    jmnlistadofac = new JMenuItem("Ver Listado de Facturas");
                    mnutool.add(jmnlistadofac);
                    mnuprocess = new JMenu("Procesos");
                        jmnfacturacion=new JMenuItem("Facturacion");
                        jmnfacturacion.addActionListener(new EventosDeJM());
                        jmnInventario = new JMenuItem("Inventario");
                        jmnInventario.addActionListener(new EventosDeJM());
                    mnuprocess.add(jmnInventario);
                    mnuprocess.add(jmnfacturacion);
                        jmnguardar = new JMenuItem("Guardar");
                        jmnguardar.addActionListener(new EventosDeJM());
                        jmnsalir = new JMenuItem("Salir");
                    mnusettings =  new JMenu("Opciones");
                        derechos = new JMenuItem("Copyright Disclaimer");
                        avisolegal = new JMenuItem("Aviso Legal");
                        avisolegal.addActionListener(new EventosDeJM());
                        terms = new JMenuItem("Terminos y Condiciones");
                        terms.addActionListener(new EventosDeJM());
                    mnusettings.add(derechos);
                    mnusettings.add(avisolegal);
                    mnusettings.add(terms);
                        derechos.addActionListener(new EventosDeJM());
                        jmnsalir.addActionListener(new EventosDeJM());
                    mnufile.add(jmnguardar);
                    mnufile.add(jmnsalir);
                    menubar.add(mnufile);
                    menubar.add(mnutool);
                    menubar.add(mnuprocess);
                    menubar.add(mnusettings);
                    mnufile.addSeparator();
                    mnutool.addSeparator();
                    mnuprocess.addSeparator();
                }
               
                    public void salir(){
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "ADVERTENCIA!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                if (opcion == JOptionPane.YES_OPTION) {
                                    System.exit(0);


                                }
                    }
                    
                    

                    public class EventosDeJM implements ActionListener {

                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            if (ae.getSource() == btnsalir) {
                              salir();
                            }else if(ae.getSource()==jmnsalir){
                              salir();
                            } else if (ae.getSource()==derechos){
                                 JOptionPane.showMessageDialog(null, "GRUPO 2M1-S","ADVERTENCIA",JOptionPane.INFORMATION_MESSAGE);
                            } else if (ae.getSource()==avisolegal){
                                  JOptionPane.showMessageDialog(null, "creado por axel","AVISO LEGAL",JOptionPane.INFORMATION_MESSAGE);

                            }else if (ae.getSource()==terms){
                                  JOptionPane.showMessageDialog(null, "Hola Mundo","TERMINOS Y CONDICIONES DEL USUARIO",JOptionPane.INFORMATION_MESSAGE);

                                } else if(ae.getSource()==jmnfacturacion){
                                fact.setVisible(true);
                                fact.ActualizarDTCB();
                                
                                } else if(ae.getSource()==btnguardar){
                                JOptionPane.showMessageDialog(null, "Los datos se guardan automaticamente a la hora de modificar la tabla","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                                } else if(ae.getSource()==jmnguardar){
                                JOptionPane.showMessageDialog(null, "Los datos se guardan automaticamente a la hora de modificar la tabla","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
                                } else if(ae.getSource()==btneditar){
                                JOptionPane.showMessageDialog(null,"Por el momento no se puede editar ningun dato","ERROR DE EDITAR",JOptionPane.ERROR_MESSAGE);
                                
                                
                                }else if(ae.getSource()==btnfactura){
                                fact.setVisible(true);
                                fact.ActualizarDTCB();
                                
                                }else if(ae.getSource()==jmnInventario){
                                ti = new TablaInventario();
                                ti.setVisible(true);
                                
                                
                                }
                                  
                            

                            }
                        }
                    }


//PROYECTO DE PROGRAMACION 2, HECHO POR AXEL VALLE ING DE SISTEMAS

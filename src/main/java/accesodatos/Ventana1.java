
package accesodatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import org.bson.Document;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.swing.JTable;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Ventana1 extends javax.swing.JFrame {
    
    boolean usar_xml = false; // true = leer y escribir .xml --- false = leer y escribir .dat
    boolean mensajes_debug = false;  // true = activa mensajes para saber si lee y escribe XML o DAT

    DefaultTableModel tabla_empresas;
    ArrayList<Empresa> lista_empresas;
    boolean modificar_empresa = false;
    int indice_empresa = 0;
    
    DefaultTableModel tabla_departamentos;
    ArrayList<Departamento> lista_departamentos;
    boolean modificar_departamento = false;
    int indice_departamento = 0;

    DefaultTableModel tabla_empleados;
    ArrayList<Empleado> lista_empleados;
    boolean modificar_empleado = false;
    int indice_empleado = 0;

    

    public Ventana1() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException {
        initComponents();
        
        //MongoClient mongoClient = new MongoClient() {};
        //MongoDatabase db = mongoClient.getDatabase("basededatos");

        tabla_empresas = new DefaultTableModel();
        lista_empresas = new ArrayList<>();
        tabla_empresas = (DefaultTableModel) jTable_empresa.getModel();
        
        tabla_departamentos = new DefaultTableModel();
        lista_departamentos = new ArrayList<>();
        tabla_departamentos = (DefaultTableModel) jTable_departamento.getModel();
        jComboBox_empresa.removeAllItems();
        
        tabla_empleados = new DefaultTableModel();
        lista_empleados = new ArrayList<>();
        tabla_empleados = (DefaultTableModel) jTable_empleado.getModel();
        jComboBox_departamento.removeAllItems();
        
        // Cargamos los datos
        actualizar_inputs();

        String url_bd = "jdbc:sqlite:D:/DAM/2o/ad/interfaz2/com.mycompany_JugadoresMongoDB_jar_1.0-SNAPSHOT/";
        String nombreBD = "base_de_datos.db";
        String sql_query_empresas = "CREATE TABLE IF NOT EXISTS empresas (\n"
                    + "	id_empresa integer PRIMARY KEY,\n"
                    + "	nif_empresa text NOT NULL,\n"
                    + "	nombre_empresa text NOT NULL,\n"
                    + "	direccion_empresa text NOT NULL,\n"
                    + "	telefono_empresa text NOT NULL,\n"
                    + "	email_empresa text NOT NULL\n"
                    + ");";
        String sql_query_departamentos = "CREATE TABLE IF NOT EXISTS departamentos (\n"
                    + "	fk_id_empresa integer NOT NULL,\n"
                    + "	id_departamento integer PRIMARY KEY,\n"
                    + "	nombre text NOT NULL,\n"
                    + "	descripcion text NOT NULL,\n"
                    + "	horario text NOT NULL,\n"
                    + "	numero_oficina text NOT NULL,\n"
                    + "	email text NOT NULL\n"
                    + ");";
        String sql_query_empleados = "CREATE TABLE IF NOT EXISTS empleados (\n"
                    + "	fk_id_departamento integer NOT NULL,\n"
                    + "	id_empleado integer PRIMARY KEY,\n"
                    + "	nombre text NOT NULL,\n"
                    + "	apellidos text NOT NULL,\n"
                    + "	fecha_nacimiento text NOT NULL,\n"
                    + "	email text NOT NULL,\n"
                    + "	telefono text NOT NULL\n"
                    + ");";
        
        //crearNuevaBD(url_bd, nombreBD);
//        crearNuevaTabla(url_bd, nombreBD, "empresas", sql_query_empresas);
//        for(int i = 0; i < lista_empresas.size(); i++){
//            insertarEmpresa(url_bd, nombreBD, "empresas", lista_empresas.get(i));
//        }
//        crearNuevaTabla(url_bd, nombreBD, "departamentos", sql_query_departamentos);
//        for(int i = 0; i < lista_departamentos.size(); i++){
//            insertarDepartamento(url_bd, nombreBD, "departamentos", lista_departamentos.get(i));
//        }
//        crearNuevaTabla(url_bd, nombreBD, "empleados", sql_query_empleados);
//        for(int i = 0; i < lista_empleados.size(); i++){
//            JOptionPane.showMessageDialog(null, "Empleado: " + lista_empleados.get(i).getNombre() + " || id: " + lista_empleados.get(i).getId_empleado());
//            insertarEmpleado(url_bd, nombreBD, "empleados", lista_empleados.get(i));
//        }
//        conectarBD(url_bd, nombreBD);
        // getDatosBD(url_bd, nombreBD, lista_empresas, lista_departamentos, lista_empleados);
        actualizar_tablas();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_persona = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_empresa = new javax.swing.JTable();
        jLabel_nif_empresa = new javax.swing.JLabel();
        jLabel_nombre_empresa = new javax.swing.JLabel();
        jLabel_direccion_empresa = new javax.swing.JLabel();
        jTextField_nif_empresa = new javax.swing.JTextField();
        jTextField_nombre_empresa = new javax.swing.JTextField();
        jTextField_direccion_empresa = new javax.swing.JTextField();
        jTextField_telefono_empresa = new javax.swing.JTextField();
        jTextField_email_empresa = new javax.swing.JTextField();
        jButton_guardar_empresa = new javax.swing.JButton();
        jButton_cancelar_empresa = new javax.swing.JButton();
        jButton_borrar_empresa = new javax.swing.JButton();
        jButton_modificar_empresa = new javax.swing.JButton();
        jButton_aniadir_empresa = new javax.swing.JButton();
        jLabel_email_empresa = new javax.swing.JLabel();
        jLabel_telefono_empresa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_departamento = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_departamento = new javax.swing.JTable();
        jLabel_nombre_departamento = new javax.swing.JLabel();
        jLabel_descripcion_departamento = new javax.swing.JLabel();
        jLabel_horario_departamento = new javax.swing.JLabel();
        jLabel_numero_de_oficina = new javax.swing.JLabel();
        jLabel_email_departamento = new javax.swing.JLabel();
        jLabel_empresa_departamento = new javax.swing.JLabel();
        jTextField_nombre_departamento = new javax.swing.JTextField();
        jTextField_descripcion_departamento = new javax.swing.JTextField();
        jTextField_horario_departamento = new javax.swing.JTextField();
        jTextField_numero_de_oficina = new javax.swing.JTextField();
        jTextField_email_departamento = new javax.swing.JTextField();
        jComboBox_empresa = new javax.swing.JComboBox<>();
        jButton_guardar_departamento = new javax.swing.JButton();
        jButton_cancelar_departamento = new javax.swing.JButton();
        jButton_borrar_departamento = new javax.swing.JButton();
        jButton_modificar_departamento = new javax.swing.JButton();
        jButton_aniadir_departamento = new javax.swing.JButton();
        jPanel_empleado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_empleado = new javax.swing.JTable();
        jLabel_nombre_empleado = new javax.swing.JLabel();
        jLabel_apellidos_empleado = new javax.swing.JLabel();
        jLabel_fecha_nac_empleado = new javax.swing.JLabel();
        jLabel_email_empleado = new javax.swing.JLabel();
        jLabel_telefono_empleado = new javax.swing.JLabel();
        jLabel_departamento_empleado = new javax.swing.JLabel();
        jTextField_nombre_empleado = new javax.swing.JTextField();
        jTextField_apellidos_empleado = new javax.swing.JTextField();
        jTextField_fecha_nacimiento_empleado = new javax.swing.JTextField();
        jTextField_email_empleado = new javax.swing.JTextField();
        jTextField_telefono_empleado = new javax.swing.JTextField();
        jComboBox_departamento = new javax.swing.JComboBox<>();
        jButton_guardar_empleado = new javax.swing.JButton();
        jButton_cancelar_empleado = new javax.swing.JButton();
        jButton_aniadir_empleado = new javax.swing.JButton();
        jButton_modificar_empleado = new javax.swing.JButton();
        jButton_borrar_empleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable_empresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIF", "Nombre", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_empresaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_empresa);

        jLabel_nif_empresa.setText("NIF:");

        jLabel_nombre_empresa.setText("Nombre:");

        jLabel_direccion_empresa.setText("Dirección:");

        jTextField_nif_empresa.setEditable(false);
        jTextField_nif_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nif_empresaActionPerformed(evt);
            }
        });
        jTextField_nif_empresa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField_nif_empresaPropertyChange(evt);
            }
        });

        jTextField_nombre_empresa.setEditable(false);
        jTextField_nombre_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_empresaActionPerformed(evt);
            }
        });

        jTextField_direccion_empresa.setEditable(false);

        jTextField_telefono_empresa.setEditable(false);

        jTextField_email_empresa.setEditable(false);

        jButton_guardar_empresa.setText("Guardar");
        jButton_guardar_empresa.setEnabled(false);
        jButton_guardar_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_empresaActionPerformed(evt);
            }
        });

        jButton_cancelar_empresa.setText("Cancelar");
        jButton_cancelar_empresa.setEnabled(false);
        jButton_cancelar_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_empresaActionPerformed(evt);
            }
        });

        jButton_borrar_empresa.setText("Borrar");
        jButton_borrar_empresa.setEnabled(false);
        jButton_borrar_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_empresaActionPerformed(evt);
            }
        });

        jButton_modificar_empresa.setText("Modificar");
        jButton_modificar_empresa.setEnabled(false);
        jButton_modificar_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_empresaActionPerformed(evt);
            }
        });

        jButton_aniadir_empresa.setText("Añadir");
        jButton_aniadir_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_empresaActionPerformed(evt);
            }
        });

        jLabel_email_empresa.setText("Email:");

        jLabel_telefono_empresa.setText("Teléfono");

        jLabel1.setText("He dejado el ID para que se pueda comprobar fácilmente que Borra adecuadamente");

        javax.swing.GroupLayout jPanel_personaLayout = new javax.swing.GroupLayout(jPanel_persona);
        jPanel_persona.setLayout(jPanel_personaLayout);
        jPanel_personaLayout.setHorizontalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_nif_empresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_nif_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_nombre_empresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_direccion_empresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_direccion_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_telefono_empresa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_telefono_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_email_empresa)
                                .addGap(70, 70, 70)
                                .addComponent(jTextField_email_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_cancelar_empresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_guardar_empresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel_personaLayout.setVerticalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_empresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_empresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nif_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nif_empresa))
                .addGap(18, 18, 18)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nombre_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nombre_empresa))
                .addGap(18, 18, 18)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_direccion_empresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_direccion_empresa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_guardar_empresa)
                    .addComponent(jTextField_telefono_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_telefono_empresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelar_empresa)
                    .addComponent(jTextField_email_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_email_empresa))
                .addGap(37, 37, 37))
        );

        jTabbedPane.addTab("Empresa", jPanel_persona);

        jTable_departamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_departamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_departamentoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_departamento);
        if (jTable_departamento.getColumnModel().getColumnCount() > 0) {
            jTable_departamento.getColumnModel().getColumn(0).setResizable(false);
            jTable_departamento.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_nombre_departamento.setText("Nombre:");

        jLabel_descripcion_departamento.setText("Descripción:");

        jLabel_horario_departamento.setText("Horario:");

        jLabel_numero_de_oficina.setText("Número de oficina:");

        jLabel_email_departamento.setText("Email:");

        jLabel_empresa_departamento.setText("Empresa:");

        jTextField_nombre_departamento.setEditable(false);
        jTextField_nombre_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_departamentoActionPerformed(evt);
            }
        });

        jTextField_descripcion_departamento.setEditable(false);

        jTextField_horario_departamento.setEditable(false);

        jTextField_numero_de_oficina.setEditable(false);

        jTextField_email_departamento.setEditable(false);
        jTextField_email_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_email_departamentoActionPerformed(evt);
            }
        });

        jComboBox_empresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
        jComboBox_empresa.setEnabled(false);
        jComboBox_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_empresaActionPerformed(evt);
            }
        });

        jButton_guardar_departamento.setText("Guardar");
        jButton_guardar_departamento.setEnabled(false);
        jButton_guardar_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_departamentoActionPerformed(evt);
            }
        });

        jButton_cancelar_departamento.setText("Cancelar");
        jButton_cancelar_departamento.setEnabled(false);
        jButton_cancelar_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_departamentoActionPerformed(evt);
            }
        });

        jButton_borrar_departamento.setText("Borrar");
        jButton_borrar_departamento.setEnabled(false);
        jButton_borrar_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_departamentoActionPerformed(evt);
            }
        });

        jButton_modificar_departamento.setText("Modificar");
        jButton_modificar_departamento.setEnabled(false);
        jButton_modificar_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_departamentoActionPerformed(evt);
            }
        });

        jButton_aniadir_departamento.setText("Añadir");
        jButton_aniadir_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_departamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_departamentoLayout = new javax.swing.GroupLayout(jPanel_departamento);
        jPanel_departamento.setLayout(jPanel_departamentoLayout);
        jPanel_departamentoLayout.setHorizontalGroup(
            jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_departamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_departamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_departamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                                .addComponent(jLabel_empresa_departamento)
                                .addGap(140, 140, 140))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_departamentoLayout.createSequentialGroup()
                                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_numero_de_oficina, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_email_departamento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_horario_departamento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_descripcion_departamento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_nombre_departamento, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(86, 86, 86)))
                        .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_numero_de_oficina, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                                    .addComponent(jTextField_email_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_guardar_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                                    .addComponent(jComboBox_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(jButton_cancelar_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextField_horario_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_descripcion_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nombre_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_departamentoLayout.setVerticalGroup(
            jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_departamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_departamento))
                    .addGroup(jPanel_departamentoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nombre_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nombre_departamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_descripcion_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_descripcion_departamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_horario_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_horario_departamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_numero_de_oficina)
                    .addComponent(jTextField_numero_de_oficina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_email_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_email_departamento)
                    .addComponent(jButton_guardar_departamento))
                .addGap(18, 18, 18)
                .addGroup(jPanel_departamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_empresa_departamento)
                    .addComponent(jButton_cancelar_departamento))
                .addGap(36, 36, 36))
        );

        jTabbedPane.addTab("Departamento", jPanel_departamento);

        jTable_empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_empleadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_empleado);

        jLabel_nombre_empleado.setText("Nombre:");

        jLabel_apellidos_empleado.setText("Apellidos:");

        jLabel_fecha_nac_empleado.setText("Fecha de nacimiento:");

        jLabel_email_empleado.setText("Email:");

        jLabel_telefono_empleado.setText("Teléfono:");

        jLabel_departamento_empleado.setText("Departamento:");

        jTextField_nombre_empleado.setEditable(false);
        jTextField_nombre_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_empleadoActionPerformed(evt);
            }
        });

        jTextField_apellidos_empleado.setEditable(false);

        jTextField_fecha_nacimiento_empleado.setEditable(false);

        jTextField_email_empleado.setEditable(false);

        jTextField_telefono_empleado.setEditable(false);

        jComboBox_departamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
        jComboBox_departamento.setEnabled(false);
        jComboBox_departamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_departamentoActionPerformed(evt);
            }
        });

        jButton_guardar_empleado.setText("Guardar");
        jButton_guardar_empleado.setEnabled(false);
        jButton_guardar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_empleadoActionPerformed(evt);
            }
        });

        jButton_cancelar_empleado.setText("Cancelar");
        jButton_cancelar_empleado.setEnabled(false);
        jButton_cancelar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_empleadoActionPerformed(evt);
            }
        });

        jButton_aniadir_empleado.setText("Añadir");
        jButton_aniadir_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_empleadoActionPerformed(evt);
            }
        });

        jButton_modificar_empleado.setText("Modificar");
        jButton_modificar_empleado.setEnabled(false);
        jButton_modificar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_empleadoActionPerformed(evt);
            }
        });

        jButton_borrar_empleado.setText("Borrar");
        jButton_borrar_empleado.setEnabled(false);
        jButton_borrar_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_empleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_empleadoLayout = new javax.swing.GroupLayout(jPanel_empleado);
        jPanel_empleado.setLayout(jPanel_empleadoLayout);
        jPanel_empleadoLayout.setHorizontalGroup(
            jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_empleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_empleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_empleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                        .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_empleadoLayout.createSequentialGroup()
                                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_apellidos_empleado)
                                    .addComponent(jLabel_nombre_empleado))
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_telefono_empleado)
                                .addComponent(jLabel_departamento_empleado)
                                .addComponent(jLabel_email_empleado)
                                .addComponent(jLabel_fecha_nac_empleado)))
                        .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField_telefono_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_cancelar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_guardar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_empleadoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField_apellidos_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_fecha_nacimiento_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_email_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(103, 103, 103)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel_empleadoLayout.setVerticalGroup(
            jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_empleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_empleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE))
                    .addGroup(jPanel_empleadoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_empleado)
                    .addComponent(jTextField_nombre_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_apellidos_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_apellidos_empleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_fecha_nacimiento_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_fecha_nac_empleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_email_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_email_empleado))
                .addGap(12, 12, 12)
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_telefono_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_guardar_empleado)
                    .addComponent(jLabel_telefono_empleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_empleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelar_empleado)
                    .addComponent(jComboBox_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_departamento_empleado))
                .addGap(47, 47, 47))
        );

        jTabbedPane.addTab("Empleado", jPanel_empleado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("Ciudad");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_departamentoActionPerformed
        
    }//GEN-LAST:event_jComboBox_departamentoActionPerformed

    private void jButton_borrar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_empleadoActionPerformed
        clic_borrar_empleado();
    }//GEN-LAST:event_jButton_borrar_empleadoActionPerformed

    private void jButton_modificar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_empleadoActionPerformed
        clic_modificar_empleado();
    }//GEN-LAST:event_jButton_modificar_empleadoActionPerformed

    private void jButton_aniadir_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_empleadoActionPerformed
        activar_campos_aniadir_o_modificar_empleado();
    }//GEN-LAST:event_jButton_aniadir_empleadoActionPerformed

    private void jButton_cancelar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_empleadoActionPerformed
        clic_cancelar_empleado();
    }//GEN-LAST:event_jButton_cancelar_empleadoActionPerformed

    private void jButton_guardar_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_empleadoActionPerformed
        clic_guardar_empleado();
    }//GEN-LAST:event_jButton_guardar_empleadoActionPerformed

    private void jTextField_nombre_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_empleadoActionPerformed

    }//GEN-LAST:event_jTextField_nombre_empleadoActionPerformed

    private void jTable_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_empleadoMouseClicked
        clic_tabla_empleado();
    }//GEN-LAST:event_jTable_empleadoMouseClicked

    private void jButton_aniadir_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_departamentoActionPerformed
        activar_campos_aniadir_o_modificar_departamento();
    }//GEN-LAST:event_jButton_aniadir_departamentoActionPerformed

    private void jButton_modificar_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_departamentoActionPerformed
        clic_modificar_departamento();
    }//GEN-LAST:event_jButton_modificar_departamentoActionPerformed

    private void jButton_borrar_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_departamentoActionPerformed
        clic_borrar_departamento();
    }//GEN-LAST:event_jButton_borrar_departamentoActionPerformed
    
    private void jButton_cancelar_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_departamentoActionPerformed
        clic_cancelar_departamento();
    }//GEN-LAST:event_jButton_cancelar_departamentoActionPerformed

    private void jButton_guardar_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_departamentoActionPerformed
        clic_guardar_departamento();
    }//GEN-LAST:event_jButton_guardar_departamentoActionPerformed

    private void jTextField_nombre_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_departamentoActionPerformed

    }//GEN-LAST:event_jTextField_nombre_departamentoActionPerformed

    private void jComboBox_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_empresaActionPerformed
        
    }//GEN-LAST:event_jComboBox_empresaActionPerformed

    private void jTable_departamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_departamentoMouseClicked
        clic_tabla_departamento();
    }//GEN-LAST:event_jTable_departamentoMouseClicked

    private void jButton_aniadir_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_empresaActionPerformed
        activar_campos_aniadir_o_modificar_empresa();
    }//GEN-LAST:event_jButton_aniadir_empresaActionPerformed

    private void jButton_modificar_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_empresaActionPerformed
        clic_modificar_empresa();
    }//GEN-LAST:event_jButton_modificar_empresaActionPerformed

    private void jButton_borrar_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_empresaActionPerformed
        clic_borrar_empresa();
    }//GEN-LAST:event_jButton_borrar_empresaActionPerformed

    private void jButton_cancelar_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_empresaActionPerformed
        clic_cancelar_empresa();
    }//GEN-LAST:event_jButton_cancelar_empresaActionPerformed

    private void jButton_guardar_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_empresaActionPerformed
        clic_guardar_empresa();        
    }//GEN-LAST:event_jButton_guardar_empresaActionPerformed

    private void jTextField_nif_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nif_empresaActionPerformed
           
    }//GEN-LAST:event_jTextField_nif_empresaActionPerformed
    private void jTable_empresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_empresaMouseClicked
        clic_tabla_empresa();
    }//GEN-LAST:event_jTable_empresaMouseClicked

    private void jTextField_nif_empresaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField_nif_empresaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nif_empresaPropertyChange

    private void jTextField_email_departamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_email_departamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_email_departamentoActionPerformed

    private void jTextField_nombre_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_empresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombre_empresaActionPerformed

    private void actualizar_tablas(){
        limpiar_tabla_empresas();
        listar_empresas();
        limpiar_tabla_departamentos();
        listar_departamentos();
        limpiar_tabla_empleados();
        listar_empleados();
    }
    // Para mostrar un listado de empresas en una jTable
    public void listar_empresas(){
        
        Object[] fila_empresa = new Object[3];
        
        for(int i = 0; i < lista_empresas.size(); i++){
            // Almacenamos los datos del vector de empresas en el Object que muestra la fila
            fila_empresa[0] = lista_empresas.get(i).getNif_empresa();
            fila_empresa[1] = lista_empresas.get(i).getNombre_empresa();
            fila_empresa[2] = lista_empresas.get(i).getId_empresa();
            // La info almacenada en el Objeto se la agregamos en una nueva fila a la DefaultTableModel
            tabla_empresas.addRow(fila_empresa);
        }
        // Al modelo jTable del Desing, le mandamos la DefaultTableModel actualizada
        jTable_empresa.setModel(tabla_empresas);
    }
    
    // Para limpiar el jTable y poder actualizar
    public void limpiar_tabla_empresas(){
        for(int i = 0; i < tabla_empresas.getRowCount(); i++){
            tabla_empresas.removeRow(i);
            // Borramos siempre la primera fila (en bucle!) porque es un ArrayList redimensionándose automáticamente !!
            i = i - 1;
        }
    }

    private void limpiar_text_fields_empresa(){
        jTextField_nif_empresa.setText("");
        jTextField_nombre_empresa.setText("");
        jTextField_direccion_empresa.setText("");
        jTextField_telefono_empresa.setText("");
        jTextField_email_empresa.setText("");
    }
    
    private void activar_campos_aniadir_o_modificar_empresa(){
        jButton_aniadir_empresa.setEnabled(false);
        jButton_modificar_empresa.setEnabled(false);
        jButton_borrar_empresa.setEnabled(false);
        limpiar_text_fields_empresa();
        activar_text_fields_empresa(true);
        jButton_guardar_empresa.setEnabled(true);
        jButton_cancelar_empresa.setEnabled(true);
    }
    
    private void desactivar_campos_aniadir_empresa(){
        jButton_aniadir_empresa.setEnabled(true);
        if(modificar_empresa == true){
            jButton_modificar_empresa.setEnabled(true);
            jButton_borrar_empresa.setEnabled(true);
        }else{
            jButton_modificar_empresa.setEnabled(false);
            jButton_borrar_empresa.setEnabled(false);
        }
        limpiar_text_fields_empresa();
        activar_text_fields_empresa(false);
        jButton_guardar_empresa.setEnabled(false);
        jButton_cancelar_empresa.setEnabled(false);
    }
    
    private void activar_text_fields_empresa(boolean activado){
        jTextField_nif_empresa.setEditable(activado);
        jTextField_nombre_empresa.setEditable(activado);
        jTextField_direccion_empresa.setEditable(activado);
        jTextField_telefono_empresa.setEditable(activado);
        jTextField_email_empresa.setEditable(activado);
    }
    
    private void guardar_empresa_nueva(){
        String nuevo_nif_empresa = jTextField_nif_empresa.getText();
        String nuevo_nombre_empresa = jTextField_nombre_empresa.getText();
        String nueva_direccion_empresa = jTextField_direccion_empresa.getText();
        String nuevo_telefono_empresa = jTextField_telefono_empresa.getText();
        String nuevo_email_empresa = jTextField_email_empresa.getText();
        
        Empresa nueva_empresa = new Empresa();
        nueva_empresa.setNif_empresa(nuevo_nif_empresa);
        nueva_empresa.setNombre_empresa(nuevo_nombre_empresa);
        nueva_empresa.setDireccion_empresa(nueva_direccion_empresa);
        nueva_empresa.setTelefono_empresa(nuevo_telefono_empresa);
        nueva_empresa.setEmail_empresa(nuevo_email_empresa);
        
        lista_empresas.add(nueva_empresa);
    }
    
    private void guardar_empresa_modificada(){
        String nuevo_nif_empresa = jTextField_nif_empresa.getText();
        String nuevo_nombre_empresa = jTextField_nombre_empresa.getText();
        String nueva_direccion_empresa = jTextField_direccion_empresa.getText();
        String nuevo_telefono_empresa = jTextField_telefono_empresa.getText();
        String nuevo_email_empresa = jTextField_email_empresa.getText();
        
        lista_empresas.get(indice_empresa).setNif_empresa(nuevo_nif_empresa);
        lista_empresas.get(indice_empresa).setNombre_empresa(nuevo_nombre_empresa);
        lista_empresas.get(indice_empresa).setDireccion_empresa(nueva_direccion_empresa);
        lista_empresas.get(indice_empresa).setTelefono_empresa(nuevo_telefono_empresa);
        lista_empresas.get(indice_empresa).setEmail_empresa(nuevo_email_empresa);
    }
        
    private void clic_guardar_empresa(){
        if(modificar_empresa == false){
            guardar_empresa_nueva();
        }
        if(modificar_empresa == true){
            guardar_empresa_modificada();
        }
        
        desactivar_campos_aniadir_empresa();
        actualizar_tablas();
        jTable_empresa.setEnabled(true);
        modificar_empresa = false;
        actualizar_outputs();
    }
    
    private void clic_cancelar_empresa(){
        desactivar_campos_aniadir_empresa();
        jTable_empresa.setEnabled(true);
        modificar_empresa = false;
    }
    
    private void clic_tabla_empresa(){

        if(tabla_empresas.getRowCount() > 0 && modificar_empresa == false)
        {
            jButton_modificar_empresa.setEnabled(true);
            jButton_borrar_empresa.setEnabled(true);
            indice_empresa = encontrar_empresa_de_jTable();
            mostrar_empresa_en_text_field(indice_empresa);

        }
        else
        {
            jButton_borrar_empresa.setEnabled(false);
            jButton_modificar_empresa.setEnabled(false);
        }
    }
    
    private void clic_modificar_empresa(){
        modificar_empresa = true;
        jTable_empresa.setEnabled(false);
        activar_campos_aniadir_o_modificar_empresa();
        mostrar_empresa_en_text_field(encontrar_empresa_de_jTable());
        activar_text_fields_empresa(true);
    }
    
    private void borrar_todos_empleados_del_departamento(int id_departamento){
        for(int i = lista_empleados.size()-1; i > 0; i--){
            if(lista_empleados.get(i).getFk_id_departamento() == id_departamento){
                lista_empleados.remove(i);
            }
        }
    }
    
    private void borrar_todos_departamentos_de_empresa(int id_empresa){
        for(int k = lista_departamentos.size()-1; k > 0; k--){
            if(lista_departamentos.get(k).getFk_id_empresa() == id_empresa){
                borrar_todos_empleados_del_departamento(lista_departamentos.get(k).getId_departamento());
                lista_departamentos.remove(k);
            }
        }  
    }
    
    private void clic_borrar_empresa() {
        
        borrar_todos_departamentos_de_empresa(lista_empresas.get(encontrar_empresa_de_jTable()).getId_empresa());
        lista_empresas.remove(encontrar_empresa_de_jTable());
        
        limpiar_text_fields_empresa();
        actualizar_tablas();
        actualizar_outputs();
    }
    
    private int encontrar_empresa_de_jTable(){
        String nif_empresa_a_buscar = jTable_empresa.getValueAt(jTable_empresa.getSelectedRow(), 0).toString();
        boolean empresa_encontrada = false;
        int i;
        for(i = 0; i < lista_empresas.size() && empresa_encontrada == false; i++){
            if(nif_empresa_a_buscar.equals(lista_empresas.get(i).getNif_empresa())){
                empresa_encontrada = true;
            }
        }
        return i-1;
    }
    
    private void mostrar_empresa_en_text_field(int i){
        jTextField_nif_empresa.setText(lista_empresas.get(i).getNif_empresa());
        jTextField_nombre_empresa.setText(lista_empresas.get(i).getNombre_empresa());
        jTextField_direccion_empresa.setText(lista_empresas.get(i).getDireccion_empresa());
        jTextField_telefono_empresa.setText(lista_empresas.get(i).getTelefono_empresa());
        jTextField_email_empresa.setText(lista_empresas.get(i).getEmail_empresa());
    }
    
    private void actualizar_outputs(){
        if(usar_xml == true){
            if(mensajes_debug){JOptionPane.showMessageDialog(this, "Escribiendo .XML");}
            escribirXML_empresas(lista_empresas);
            escribirXML_departamentos(lista_departamentos);
            escribirXML_empleados(lista_empleados);
        }else{
            if(mensajes_debug){JOptionPane.showMessageDialog(this, "Escribiendo .DAT");}
            EmpresaSerializableOutput(lista_empresas);
            DepartamentoSerializableOutput(lista_departamentos);
            EmpleadoSerializableOutput(lista_empleados);
        }
            
    }
    private void actualizar_inputs() throws IOException, FileNotFoundException, ClassNotFoundException{
        if(usar_xml == true){
            if(mensajes_debug){JOptionPane.showMessageDialog(this, "Leyendo .XML");}
            leerXML_empresas(lista_empresas);
            leerXML_departamentos(lista_departamentos);
            leerXML_empleados(lista_empleados);
        }else{
            if(mensajes_debug){JOptionPane.showMessageDialog(this, "Leyendo .DAT");}
            EmpresaSerializableInput();
            DepartamentoSerializableInput();
            lista_empleados = EmpleadoSerializableInput();
        }
        actualizar_tablas();
    }
    
    private void escribirXML_empresas(ArrayList<Empresa> listado_empresas) {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation dom = builder.getDOMImplementation();
                documento = dom.createDocument(null,  "xml", null);

                Element raiz = documento.createElement("empresas"); // creado <empresas>
                documento.getDocumentElement().appendChild(raiz); // asignado dentro de <xml>
 
                Element nodoEmpresa = null, nodoDatosEmpresa = null;
                Text texto = null;
                for (int i = 0; i < listado_empresas.size(); i++) {
                        nodoEmpresa = documento.createElement("empresa");   // creado <empresa>
                        raiz.appendChild(nodoEmpresa);  // asignado dentro de <empresas>

                        nodoDatosEmpresa = documento.createElement("id");   // creado <id>
                        nodoEmpresa.appendChild(nodoDatosEmpresa);  // asignado dentro de <empresa>
                        texto = documento.createTextNode(String.valueOf(listado_empresas.get(i).getId_empresa()));    // extraemos info y almacenamos
                        nodoDatosEmpresa.appendChild(texto);    // introducimos texto en <id></id>
                        
                        nodoDatosEmpresa = documento.createElement("nif");
                        nodoEmpresa.appendChild(nodoDatosEmpresa);
                        texto = documento.createTextNode(listado_empresas.get(i).getNif_empresa());
                        nodoDatosEmpresa.appendChild(texto);

                        nodoDatosEmpresa = documento.createElement("nombre");
                        nodoEmpresa.appendChild(nodoDatosEmpresa);
                        texto = documento.createTextNode(listado_empresas.get(i).getNombre_empresa());
                        nodoDatosEmpresa.appendChild(texto);
                        
                        nodoDatosEmpresa = documento.createElement("direccion");
                        nodoEmpresa.appendChild(nodoDatosEmpresa);
                        texto = documento.createTextNode(listado_empresas.get(i).getDireccion_empresa());
                        nodoDatosEmpresa.appendChild(texto);
                        
                        nodoDatosEmpresa = documento.createElement("telefono");
                        nodoEmpresa.appendChild(nodoDatosEmpresa);
                        texto = documento.createTextNode(listado_empresas.get(i).getTelefono_empresa());
                        nodoDatosEmpresa.appendChild(texto);
                        
                        nodoDatosEmpresa = documento.createElement("email");
                        nodoEmpresa.appendChild(nodoDatosEmpresa);
                        texto = documento.createTextNode(listado_empresas.get(i).getEmail_empresa());
                        nodoDatosEmpresa.appendChild(texto);
                }

                Source source = new DOMSource(documento);
                Result resultado = new StreamResult(new File("empresas.xml"));

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, resultado);

        } catch (ParserConfigurationException | TransformerException pce) {
        }
    }
    
    private void leerXML_empresas(ArrayList<Empresa> listado_empresas) {
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        String nombre_del_xml = "empresas.xml";
        
        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                documento = builder.parse(new File(nombre_del_xml));

                // Primero, aseguramos que el listado está limpio
                listado_empresas.clear();
                
                // Recorre cada uno de los nodos 'empresa'
                NodeList empresas = documento.getElementsByTagName("empresa");
                for (int i = 0; i < empresas.getLength(); i++) {
                        Node empresa = empresas.item(i);
                        Element elemento = (Element) empresa;

                        int Id_empresa_xml = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                        String Nif_empresa_xml = elemento.getElementsByTagName("nif").item(0).getChildNodes().item(0).getNodeValue();
                        String Nombre_empresa_xml = elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                        String Direccion_empresa_xml = elemento.getElementsByTagName("direccion").item(0).getChildNodes().item(0).getNodeValue();
                        String Telefono_empresa_xml = elemento.getElementsByTagName("telefono").item(0).getChildNodes().item(0).getNodeValue();
                        String Email_empresa_xml = elemento.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
                        
                        Empresa empresa_xml = new Empresa(true, Id_empresa_xml, Nif_empresa_xml, Nombre_empresa_xml, Direccion_empresa_xml, Telefono_empresa_xml, Email_empresa_xml);
                        lista_empresas.add(empresa_xml);
                        
                        //JOptionPane.showMessageDialog(this, "nif: " + elemento.getElementsByTagName("nif").item(0).getChildNodes().item(0).getNodeValue());
                        //JOptionPane.showMessageDialog(this, "nombre: " + elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
                }                
        } catch (ParserConfigurationException | IOException | SAXException pce) {
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_del_xml);
        }
    }
    
    public void EmpresaSerializableOutput(ArrayList<Empresa> listado_empresas){
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        String nombre_dat = "empresas.dat";
        
        try{
            fos = new FileOutputStream(nombre_dat);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(listado_empresas);
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al crear el fichero " + nombre_dat);
        }
        
    }
    
    public void EmpresaSerializableInput() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String nombre_dat = "empresas.dat";
        
        try{
            fis = new FileInputStream(nombre_dat);
            ois = new ObjectInputStream(fis);
            
            lista_empresas.clear();
            lista_empresas = (ArrayList<Empresa>) ois.readObject();
            
            //JOptionPane.showMessageDialog(this, "Nombre empresa 1: " + lista_empresas.get(0).getNombre_empresa());
            fis.close();
            ois.close();
            
        }catch(IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_dat);
        }
        //JOptionPane.showMessageDialog(this, "Nombre empresa 2: " + lista_empresas.get(0).getNombre_empresa());
    }

    
    /*
    ================= DEPARTAMENTOS =============================
    */
    public void listar_departamentos(){
        
        Object[] fila_departamento = new Object[2];
        
        for(int i = 0; i < lista_departamentos.size(); i++){
            fila_departamento[0] = lista_departamentos.get(i).getNombre();
            fila_departamento[1] = lista_departamentos.get(i).getEmail();
            tabla_departamentos.addRow(fila_departamento);
        }
        jTable_departamento.setModel(tabla_departamentos);
    }

    public void limpiar_tabla_departamentos(){
        for(int i = 0; i < tabla_departamentos.getRowCount(); i++){
            tabla_departamentos.removeRow(i);
            // Borramos siempre la primera fila (en bucle!) porque es un ArrayList redimensionándose automáticamente !!
            i = i - 1;
        }
    }
    
    private void limpiar_text_fields_departamento(){
        jTextField_nombre_departamento.setText("");
        jTextField_descripcion_departamento.setText("");
        jTextField_horario_departamento.setText("");
        jTextField_numero_de_oficina.setText("");
        jTextField_email_departamento.setText("");
        jComboBox_empresa.removeAllItems();
    }
    
    private void activar_campos_aniadir_o_modificar_departamento(){
        jButton_aniadir_departamento.setEnabled(false);
        jButton_modificar_departamento.setEnabled(false);
        jButton_borrar_departamento.setEnabled(false);
        limpiar_text_fields_departamento();
        activar_text_fields_departamento(true);
        jButton_guardar_departamento.setEnabled(true);
        jButton_cancelar_departamento.setEnabled(true);
        jTable_departamento.setEnabled(false);
    }
    
    private void desactivar_campos_aniadir_departamento(){
        jButton_aniadir_departamento.setEnabled(true);
        if(modificar_departamento == true){
            jButton_modificar_departamento.setEnabled(true);
            jButton_borrar_departamento.setEnabled(true);
        }else{
            jButton_modificar_departamento.setEnabled(false);
            jButton_borrar_departamento.setEnabled(false);
        }
        limpiar_text_fields_departamento();
        activar_text_fields_departamento(false);
        jButton_guardar_departamento.setEnabled(false);
        jButton_cancelar_departamento.setEnabled(false);
    }
    
    private void listar_empresas_combobox(){
        jComboBox_empresa.removeAllItems();
        if(modificar_departamento == false){
            for(int i = 0; i < lista_empresas.size(); i++){
                jComboBox_empresa.addItem(lista_empresas.get(i).getNombre_empresa());
            }
        }else{
            asignar_empresa_combobox(encontrar_departamento_de_jTable());
            
            for(int i = 0; i < lista_empresas.size(); i++){
                if(jComboBox_empresa.getSelectedItem().toString().equals(lista_empresas.get(i).getNombre_empresa())){
                    
                }else{
                    jComboBox_empresa.addItem(lista_empresas.get(i).getNombre_empresa());
                }
            }
        }
       
   }
    
    private void activar_text_fields_departamento(boolean activado){
        jTextField_nombre_departamento.setEditable(activado);
        jTextField_descripcion_departamento.setEditable(activado);
        jTextField_horario_departamento.setEditable(activado);
        jTextField_numero_de_oficina.setEditable(activado);
        jTextField_email_departamento.setEditable(activado);
        jComboBox_empresa.setEnabled(activado);
        if(activado == true){
            listar_empresas_combobox();
        }
    }
    
    private void guardar_departamento_nuevo(){
        
        int nuevo_fk_id_empresa = 0;
        boolean empresa_encontrada = false;
        for(int j = 0; j < lista_empresas.size() && empresa_encontrada == false; j++){
            if(lista_empresas.get(j).getNombre_empresa().equals(jComboBox_empresa.getSelectedItem().toString())){
                empresa_encontrada = true;
                nuevo_fk_id_empresa = lista_empresas.get(j).getId_empresa();
            }
        }
        String nuevo_nombre_departamento = jTextField_nombre_departamento.getText();
        String nueva_descripcion_departamento = jTextField_descripcion_departamento.getText();
        String nuevo_horario_departamento = jTextField_horario_departamento.getText();
        String nuevo_numero_de_oficina = jTextField_numero_de_oficina.getText();
        String nuevo_email_departamento = jTextField_email_departamento.getText();
        
        Departamento nuevo_departamento = new Departamento();
        nuevo_departamento.setFk_id_empresa(nuevo_fk_id_empresa);
        nuevo_departamento.setNombre(nuevo_nombre_departamento);
        nuevo_departamento.setDescripcion(nueva_descripcion_departamento);
        nuevo_departamento.setHorario(nuevo_horario_departamento);
        nuevo_departamento.setNumero_oficina(nuevo_numero_de_oficina);
        nuevo_departamento.setEmail(nuevo_email_departamento);
        
        lista_departamentos.add(nuevo_departamento);
    }
    
    private void guardar_departamento_modificado(){
        
        int nuevo_fk_id_empresa = 0;
        boolean empresa_encontrada = false;
        for(int j = 0; j < lista_empresas.size() && empresa_encontrada == false; j++){
            if(lista_empresas.get(j).getNombre_empresa().equals(jComboBox_empresa.getSelectedItem().toString())){
                empresa_encontrada = true;
                nuevo_fk_id_empresa = lista_empresas.get(j).getId_empresa();
            }
        }
        String nuevo_nombre_departamento = jTextField_nombre_departamento.getText();
        String nueva_descripcion_departamento = jTextField_descripcion_departamento.getText();
        String nuevo_horario_departamento = jTextField_horario_departamento.getText();
        String nuevo_numero_de_oficina = jTextField_numero_de_oficina.getText();
        String nuevo_email_departamento = jTextField_email_departamento.getText();
        
        lista_departamentos.get(indice_departamento).setFk_id_empresa(nuevo_fk_id_empresa);
        lista_departamentos.get(indice_departamento).setNombre(nuevo_nombre_departamento);
        lista_departamentos.get(indice_departamento).setDescripcion(nueva_descripcion_departamento);
        lista_departamentos.get(indice_departamento).setHorario(nuevo_horario_departamento);
        lista_departamentos.get(indice_departamento).setNumero_oficina(nuevo_numero_de_oficina);
        lista_departamentos.get(indice_departamento).setEmail(nuevo_email_departamento);
    }
        
    private void clic_guardar_departamento(){
        if(modificar_departamento == false){
            guardar_departamento_nuevo();
        }
        if(modificar_departamento == true){
            guardar_departamento_modificado();
        }
        
        desactivar_campos_aniadir_departamento();
        actualizar_tablas();
        jTable_departamento.setEnabled(true);
        modificar_departamento = false;
        actualizar_outputs();
    }
    
    private void clic_cancelar_departamento(){
        desactivar_campos_aniadir_departamento();
        jTable_departamento.setEnabled(true);
        modificar_departamento = false;
    }
    
    private void clic_tabla_departamento(){

        if(tabla_departamentos.getRowCount() > 0 && modificar_departamento == false)
        {
            jButton_modificar_departamento.setEnabled(true);
            jButton_borrar_departamento.setEnabled(true);
            mostrar_departamento_en_text_field(encontrar_departamento_de_jTable());
            indice_departamento = encontrar_departamento_de_jTable();

        }
        else
        {
            jButton_borrar_departamento.setEnabled(false);
            jButton_modificar_departamento.setEnabled(false);
        }
    }
    
    private void clic_modificar_departamento(){
        modificar_departamento = true;
        activar_campos_aniadir_o_modificar_departamento();
        mostrar_departamento_en_text_field(encontrar_departamento_de_jTable());
        //nombre_empresa_buscar = jComboBox_empresa.getSelectedItem().toString();
        activar_text_fields_departamento(true);
    }
    
    private void clic_borrar_departamento() {
        
        borrar_todos_empleados_del_departamento(lista_departamentos.get(encontrar_departamento_de_jTable()).getId_departamento());
        lista_departamentos.remove(encontrar_departamento_de_jTable());
        desactivar_campos_aniadir_departamento();
        limpiar_text_fields_departamento();
        actualizar_tablas();
        actualizar_outputs();
    }
    
    private int encontrar_departamento_de_jTable(){
        String email_departamento_a_buscar = jTable_departamento.getValueAt(jTable_departamento.getSelectedRow(), 1).toString();
        boolean departamento_encontrado = false;
        int posicion_index = 0;
        for(int i = 0; i < lista_departamentos.size() && departamento_encontrado == false; i++){
            if(email_departamento_a_buscar.equals(lista_departamentos.get(i).getEmail())){
                departamento_encontrado = true;
                posicion_index = i;
            }
        }
        indice_departamento = posicion_index;
        return posicion_index;
    }
    
    private void asignar_empresa_combobox(int posicion_index){
        jComboBox_empresa.removeAllItems();
        boolean empresa_encontrada = false;
        for(int j = 0; j < lista_empresas.size() && empresa_encontrada == false; j++){
            if(lista_empresas.get(j).getId_empresa() == lista_departamentos.get(posicion_index).getFk_id_empresa()){
                empresa_encontrada = true;
                jComboBox_empresa.addItem(lista_empresas.get(j).getNombre_empresa());
            }
        }
    }
    
    private void mostrar_departamento_en_text_field(int posicion_index){
        jTextField_nombre_departamento.setText(lista_departamentos.get(posicion_index).getNombre());
        jTextField_descripcion_departamento.setText(lista_departamentos.get(posicion_index).getDescripcion());
        jTextField_horario_departamento.setText(lista_departamentos.get(posicion_index).getHorario());
        jTextField_numero_de_oficina.setText(lista_departamentos.get(posicion_index).getNumero_oficina());
        jTextField_email_departamento.setText(lista_departamentos.get(posicion_index).getEmail());
        asignar_empresa_combobox(posicion_index);
        
    }
    
    private void escribirXML_departamentos(ArrayList<Departamento> listado_departamentos) {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation dom = builder.getDOMImplementation();
                documento = dom.createDocument(null,  "xml", null);

                Element raiz = documento.createElement("departamentos"); // creado <departamentos>
                documento.getDocumentElement().appendChild(raiz); // asignado dentro de <xml>
 
                Element nodoDepartamento = null, nodoDatosDepartamento = null;
                Text texto = null;
                for (int i = 0; i < listado_departamentos.size(); i++) {
                        nodoDepartamento = documento.createElement("departamento");   // creado <departamento>
                        raiz.appendChild(nodoDepartamento);  // asignado dentro de <departamentos>

                        nodoDatosDepartamento = documento.createElement("fk_id_empresa");   // creado <fk_id_empresa>
                        nodoDepartamento.appendChild(nodoDatosDepartamento);  // asignado dentro de <departamento>
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getFk_id_empresa()));    // extraemos info y almacenamos
                        nodoDatosDepartamento.appendChild(texto);    // introducimos texto en <fk_id_empresa></fk_id_empresa>
                        
                        nodoDatosDepartamento = documento.createElement("id_departamento");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getId_departamento()));
                        nodoDatosDepartamento.appendChild(texto);
                        
                        nodoDatosDepartamento = documento.createElement("nombre");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getNombre()));
                        nodoDatosDepartamento.appendChild(texto);
                        
                        nodoDatosDepartamento = documento.createElement("descripcion");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getDescripcion()));
                        nodoDatosDepartamento.appendChild(texto);
                        
                        nodoDatosDepartamento = documento.createElement("horario");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getHorario()));
                        nodoDatosDepartamento.appendChild(texto);
                        
                        nodoDatosDepartamento = documento.createElement("numero_oficina");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getNumero_oficina()));
                        nodoDatosDepartamento.appendChild(texto);
                        
                        nodoDatosDepartamento = documento.createElement("email");
                        nodoDepartamento.appendChild(nodoDatosDepartamento);
                        texto = documento.createTextNode(String.valueOf(listado_departamentos.get(i).getEmail()));
                        nodoDatosDepartamento.appendChild(texto);
                }

                Source source = new DOMSource(documento);
                Result resultado = new StreamResult(new File("departamentos.xml"));

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, resultado);

        } catch (ParserConfigurationException | TransformerException pce) {
        }
    }
    
    private void leerXML_departamentos(ArrayList<Departamento> listado_departamentos) {
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        String nombre_del_xml = "departamentos.xml";
        
        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                documento = builder.parse(new File(nombre_del_xml));

                // Primero, aseguramos que el listado está limpio
                listado_departamentos.clear();
                
                // Recorre cada uno de los nodos 'empresa'
                NodeList departamentos = documento.getElementsByTagName("departamento");
                for (int i = 0; i < departamentos.getLength(); i++) {
                        Node departamento = departamentos.item(i);
                        Element elemento = (Element) departamento;

                        int fk_id_empresa_xml = Integer.parseInt(elemento.getElementsByTagName("fk_id_empresa").item(0).getChildNodes().item(0).getNodeValue());
                        int id_departamento_xml = Integer.parseInt(elemento.getElementsByTagName("id_departamento").item(0).getChildNodes().item(0).getNodeValue());
                        String nombre_xml = elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                        String descripcion_xml = elemento.getElementsByTagName("descripcion").item(0).getChildNodes().item(0).getNodeValue();
                        String horario_xml = elemento.getElementsByTagName("horario").item(0).getChildNodes().item(0).getNodeValue();
                        String numero_oficina_xml = elemento.getElementsByTagName("numero_oficina").item(0).getChildNodes().item(0).getNodeValue();
                        String email_xml = elemento.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
                        
                        Departamento departamento_xml = new Departamento(true, fk_id_empresa_xml, id_departamento_xml, nombre_xml, descripcion_xml, horario_xml, numero_oficina_xml, email_xml);
                        lista_departamentos.add(departamento_xml);
                }                
        } catch (ParserConfigurationException | IOException | SAXException pce) {
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_del_xml);
        }
    }

    public void DepartamentoSerializableOutput(ArrayList<Departamento> listado_departamentos){
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        String nombre_dat = "departamentos.dat";
        
        try{
            fos = new FileOutputStream(nombre_dat);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(listado_departamentos);
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al crear el fichero " + nombre_dat);
        }
        
    }
    
    public void DepartamentoSerializableInput(){
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String nombre_dat = "departamentos.dat";
        
        try{
            fis = new FileInputStream(nombre_dat);
            ois = new ObjectInputStream(fis);
            
            lista_departamentos.clear();
            lista_departamentos = (ArrayList<Departamento>) ois.readObject();
            
            fis.close();
            ois.close();
            
        }catch(IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_dat);
        }
    }
    
    
    /*
    ================= EMPLEADOSS =============================
    */
    public void listar_empleados(){
        
        Object[] fila_empleado = new Object[3];
        
        for(int i = 0; i < lista_empleados.size(); i++){
            fila_empleado[0] = lista_empleados.get(i).getNombre();
            fila_empleado[1] = lista_empleados.get(i).getApellidos();
            fila_empleado[2] = lista_empleados.get(i).getTelefono();
            tabla_empleados.addRow(fila_empleado);
        }
        jTable_empleado.setModel(tabla_empleados);
    }

    public void limpiar_tabla_empleados(){
        for(int i = 0; i < tabla_empleados.getRowCount(); i++){
            tabla_empleados.removeRow(i);
            // Borramos siempre la primera fila (en bucle!) porque es un ArrayList redimensionándose automáticamente !!
            i = i - 1;
        }
    }
    
    private void limpiar_text_fields_empleado(){
        jTextField_nombre_empleado.setText("");
        jTextField_apellidos_empleado.setText("");
        jTextField_fecha_nacimiento_empleado.setText("");
        jTextField_email_empleado.setText("");
        jTextField_telefono_empleado.setText("");
        jComboBox_departamento.removeAllItems();
    }
    
    private void activar_campos_aniadir_o_modificar_empleado(){
        jButton_aniadir_empleado.setEnabled(false);
        jButton_modificar_empleado.setEnabled(false);
        jButton_borrar_empleado.setEnabled(false);
        limpiar_text_fields_empleado();
        activar_text_fields_empleado(true);
        jButton_guardar_empleado.setEnabled(true);
        jButton_cancelar_empleado.setEnabled(true);
        jTable_empleado.setEnabled(false);
    }
    
    private void desactivar_campos_aniadir_empleado(){
        jButton_aniadir_empleado.setEnabled(true);
        if(modificar_empleado == true){
            jButton_modificar_empleado.setEnabled(true);
            jButton_borrar_empleado.setEnabled(true);
        }else{
            jButton_modificar_empleado.setEnabled(false);
            jButton_borrar_empleado.setEnabled(false);
        }
        limpiar_text_fields_empleado();
        activar_text_fields_empleado(false);
        jButton_guardar_empleado.setEnabled(false);
        jButton_cancelar_empleado.setEnabled(false);
    }
    
    private void listar_departamentos_combobox(){
        jComboBox_departamento.removeAllItems();
        if(modificar_empleado == false){
            for(int i = 0; i < lista_departamentos.size(); i++){
                jComboBox_departamento.addItem(lista_departamentos.get(i).getNombre());
            }
        }else{
            asignar_departamento_combobox(encontrar_empleado_de_jTable());
            
            for(int i = 0; i < lista_departamentos.size(); i++){
                if(jComboBox_departamento.getSelectedItem().toString().equals(lista_departamentos.get(i).getNombre())){
                    
                }else{
                    jComboBox_departamento.addItem(lista_departamentos.get(i).getNombre());
                }
            }
        }
       
   }
    
    private void activar_text_fields_empleado(boolean activado){
        jTextField_nombre_empleado.setEditable(activado);
        jTextField_apellidos_empleado.setEditable(activado);
        jTextField_fecha_nacimiento_empleado.setEditable(activado);
        jTextField_email_empleado.setEditable(activado);
        jTextField_telefono_empleado.setEditable(activado);
        jComboBox_departamento.setEnabled(activado);
        if(activado == true){
            listar_departamentos_combobox();
        }
    }
    
    private void guardar_empleado_nuevo(){
        
        int nuevo_fk_id_departamento = 0;
        boolean departamento_encontrado = false;
        for(int j = 0; j < lista_departamentos.size() && departamento_encontrado == false; j++){
            if(lista_departamentos.get(j).getNombre().equals(jComboBox_departamento.getSelectedItem().toString())){
                departamento_encontrado = true;
                nuevo_fk_id_departamento = lista_departamentos.get(j).getId_departamento();
            }
        }
        String nuevo_nombre = jTextField_nombre_empleado.getText();
        String nuevos_apellidos = jTextField_apellidos_empleado.getText();
        String nueva_fecha_nacimiento = jTextField_fecha_nacimiento_empleado.getText();
        String nuevo_email = jTextField_email_empleado.getText();
        String nuevo_telefono = jTextField_telefono_empleado.getText();
       
        Empleado nuevo_empleado = new Empleado();
        nuevo_empleado.setFk_id_departamento(nuevo_fk_id_departamento);
        nuevo_empleado.setNombre(nuevo_nombre);
        nuevo_empleado.setApellidos(nuevos_apellidos);
        nuevo_empleado.setFecha_nacimiento(nueva_fecha_nacimiento);
        nuevo_empleado.setEmail(nuevo_email);
        nuevo_empleado.setTelefono(nuevo_telefono);
        
        lista_empleados.add(nuevo_empleado);
    }
    
    private void guardar_empleado_modificado(){
        
        int nuevo_fk_id_departamento = 0;
        boolean departamento_encontrado = false;
        for(int j = 0; j < lista_departamentos.size() && departamento_encontrado == false; j++){
            if(lista_departamentos.get(j).getNombre().equals(jComboBox_departamento.getSelectedItem().toString())){
                departamento_encontrado = true;
                nuevo_fk_id_departamento = lista_departamentos.get(j).getId_departamento();
            }
        }
        String nuevo_nombre = jTextField_nombre_empleado.getText();
        String nuevos_apellidos = jTextField_apellidos_empleado.getText();
        String nueva_fecha_nacimiento = jTextField_fecha_nacimiento_empleado.getText();
        String nuevo_email = jTextField_email_empleado.getText();
        String nuevo_telefono = jTextField_telefono_empleado.getText();
        
        lista_empleados.get(indice_empleado).setFk_id_departamento(nuevo_fk_id_departamento);
        lista_empleados.get(indice_empleado).setNombre(nuevo_nombre);
        lista_empleados.get(indice_empleado).setApellidos(nuevos_apellidos);
        lista_empleados.get(indice_empleado).setFecha_nacimiento(nueva_fecha_nacimiento);
        lista_empleados.get(indice_empleado).setEmail(nuevo_email);
        lista_empleados.get(indice_empleado).setTelefono(nuevo_telefono);
    }
        
    private void clic_guardar_empleado(){
        if(modificar_empleado == false){
            guardar_empleado_nuevo();
        }
        if(modificar_empleado == true){
            guardar_empleado_modificado();
        }
        
        desactivar_campos_aniadir_empleado();
        actualizar_tablas();
        jTable_empleado.setEnabled(true);
        modificar_empleado = false;
        actualizar_outputs();
    }
    
    private void clic_cancelar_empleado(){
        desactivar_campos_aniadir_empleado();
        jTable_empleado.setEnabled(true);
        modificar_empleado = false;
    }
    
    private void clic_tabla_empleado(){

        if(tabla_empleados.getRowCount() > 0 && modificar_empleado == false)
        {
            jButton_modificar_empleado.setEnabled(true);
            jButton_borrar_empleado.setEnabled(true);
            mostrar_empleado_en_text_field(encontrar_empleado_de_jTable());
            indice_empleado = encontrar_empleado_de_jTable();

        }
        else
        {
            jButton_borrar_empleado.setEnabled(false);
            jButton_modificar_empleado.setEnabled(false);
        }
    }
    
    private void clic_modificar_empleado(){
        modificar_empleado = true;
        activar_campos_aniadir_o_modificar_empleado();
        mostrar_empleado_en_text_field(encontrar_empleado_de_jTable());
        //nombre_empresa_buscar = jComboBox_empresa.getSelectedItem().toString();
        activar_text_fields_empleado(true);
    }
    
    private void clic_borrar_empleado() {
        lista_empleados.remove(encontrar_empleado_de_jTable());
        desactivar_campos_aniadir_empleado();
        limpiar_text_fields_empleado();
        actualizar_tablas();
        actualizar_outputs();
    }
    
    private int encontrar_empleado_de_jTable(){
        String telefono_empleado_a_buscar = jTable_empleado.getValueAt(jTable_empleado.getSelectedRow(), 2).toString();
        boolean empleado_encontrado = false;
        int posicion_index = 0;
        for(int i = 0; i < lista_empleados.size() && empleado_encontrado == false; i++){
            if(telefono_empleado_a_buscar.equals(lista_empleados.get(i).getTelefono())){
                empleado_encontrado = true;
                posicion_index = i;
            }
        }
        indice_empleado = posicion_index;
        return posicion_index;
    }
    
    private void asignar_departamento_combobox(int posicion_index){
        jComboBox_departamento.removeAllItems();
        boolean departamento_encontrado = false;
        for(int j = 0; j < lista_departamentos.size() && departamento_encontrado == false; j++){
            if(lista_departamentos.get(j).getId_departamento() == lista_empleados.get(posicion_index).getFk_id_departamento()){
                departamento_encontrado = true;
                jComboBox_departamento.addItem(lista_departamentos.get(j).getNombre());
            }
        }
    }
    
    private void mostrar_empleado_en_text_field(int posicion_index){
        jTextField_nombre_empleado.setText(lista_empleados.get(posicion_index).getNombre());
        jTextField_apellidos_empleado.setText(lista_empleados.get(posicion_index).getApellidos());
        jTextField_fecha_nacimiento_empleado.setText(lista_empleados.get(posicion_index).getFecha_nacimiento());
        jTextField_email_empleado.setText(lista_empleados.get(posicion_index).getEmail());
        jTextField_telefono_empleado.setText(lista_empleados.get(posicion_index).getTelefono());
        asignar_departamento_combobox(posicion_index);
        
    }
    
    private void escribirXML_empleados(ArrayList<Empleado> listado_empleados) {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        String nombre_del_xml = "empleados.xml";

        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation dom = builder.getDOMImplementation();
                documento = dom.createDocument(null,  "xml", null);

                Element raiz = documento.createElement("empleados"); // creado <empleados>
                documento.getDocumentElement().appendChild(raiz); // asignado dentro de <xml>
 
                Element nodoEmpleado = null, nodoDatosEmpleado = null;
                Text texto = null;
                for (int i = 0; i < listado_empleados.size(); i++) {
                        nodoEmpleado = documento.createElement("empleado");   // creado <empleado>
                        raiz.appendChild(nodoEmpleado);  // asignado dentro de <empleados>

                        nodoDatosEmpleado = documento.createElement("fk_id_departamento");   // creado <fk_id_departamento>
                        nodoEmpleado.appendChild(nodoDatosEmpleado);  // asignado dentro de <departamento>
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getFk_id_departamento()));// extraemos info y almacenamos
                        nodoDatosEmpleado.appendChild(texto);    // introducimos texto en <fk_id_departamento></fk_id_departamento>
                        
                        nodoDatosEmpleado = documento.createElement("id_empleado");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getId_empleado()));
                        nodoDatosEmpleado.appendChild(texto);
                        
                        nodoDatosEmpleado = documento.createElement("nombre");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getNombre()));
                        nodoDatosEmpleado.appendChild(texto);
                        
                        nodoDatosEmpleado = documento.createElement("apellidos");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getApellidos()));
                        nodoDatosEmpleado.appendChild(texto);
                        
                        nodoDatosEmpleado = documento.createElement("fecha_nacimiento");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getFecha_nacimiento()));
                        nodoDatosEmpleado.appendChild(texto);
                        
                        nodoDatosEmpleado = documento.createElement("email");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getEmail()));
                        nodoDatosEmpleado.appendChild(texto);
                        
                        nodoDatosEmpleado = documento.createElement("telefono");
                        nodoEmpleado.appendChild(nodoDatosEmpleado);
                        texto = documento.createTextNode(String.valueOf(listado_empleados.get(i).getTelefono()));
                        nodoDatosEmpleado.appendChild(texto);
                }

                Source source = new DOMSource(documento);
                Result resultado = new StreamResult(new File(nombre_del_xml));

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, resultado);

        } catch (ParserConfigurationException | TransformerException pce) {
            JOptionPane.showMessageDialog(this, "Error al crear el fichero " + nombre_del_xml);
        }
    }
    
    private void leerXML_empleados(ArrayList<Empleado> listado_empleados) {
     
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        String nombre_del_xml = "empleados.xml";
        
        try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                documento = builder.parse(new File(nombre_del_xml));

                // Primero, aseguramos que el listado está limpio
                listado_empleados.clear();
                
                // Recorre cada uno de los nodos 'empresa'
                NodeList empleados = documento.getElementsByTagName("empleado");
                for (int i = 0; i < empleados.getLength(); i++) {
                        Node empleado = empleados.item(i);
                        Element elemento = (Element) empleado;

                        int fk_id_departamento_xml = Integer.parseInt(elemento.getElementsByTagName("fk_id_departamento").item(0).getChildNodes().item(0).getNodeValue());
                        int id_empleado_xml = Integer.parseInt(elemento.getElementsByTagName("id_empleado").item(0).getChildNodes().item(0).getNodeValue());
                        String nombre_xml = elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                        String apellidos_xml = elemento.getElementsByTagName("apellidos").item(0).getChildNodes().item(0).getNodeValue();
                        String fecha_nacimiento_xml = elemento.getElementsByTagName("fecha_nacimiento").item(0).getChildNodes().item(0).getNodeValue();
                        String email_xml = elemento.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
                        String telefono_xml = elemento.getElementsByTagName("telefono").item(0).getChildNodes().item(0).getNodeValue();
                        
                        Empleado empleado_xml = new Empleado(true, fk_id_departamento_xml, id_empleado_xml, nombre_xml, apellidos_xml, fecha_nacimiento_xml, email_xml, telefono_xml);
                        lista_empleados.add(empleado_xml);
                }                
        } catch (ParserConfigurationException | IOException | SAXException pce) {
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_del_xml);
        }
    }
    
    public void EmpleadoSerializableOutput(ArrayList<Empleado> listado_empleados){
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        String nombre_dat = "empleados.dat";
        
        try{
            fos = new FileOutputStream(nombre_dat);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(listado_empleados);
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al crear el fichero " + nombre_dat);
        }
        
    }
    
    public ArrayList<Empleado> EmpleadoSerializableInput(){
        
        ArrayList<Empleado> aEmpleados = new ArrayList<>();
        String nombre_dat = "empleados.dat";
        
        try(
            FileInputStream fis = new FileInputStream(nombre_dat);
            ObjectInputStream ois = new ObjectInputStream(fis)
        )
        {
            aEmpleados = (ArrayList<Empleado>) ois.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aEmpleados;
        /*
        try{
            fis = new FileInputStream(nombre_dat);
            ois = new ObjectInputStream(fis);
            
            lista_empleados.clear();
            lista_empleados = (ArrayList<Empleado>) ois.readObject();
            
            fis.close();
            ois.close();
        }catch(IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(this, "Error al leer el fichero " + nombre_dat);
        }
        */
    }
    
    public static void crearNuevaBD(String url_bd, String nombreBD) {
        String url = url_bd + nombreBD;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                JOptionPane.showMessageDialog(null, "The driver name is " + meta.getDriverName());
                JOptionPane.showMessageDialog(null, "Se ha creado la Base de Datos: " + nombreBD);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (crearNuebaBD): " + e.getMessage());
        }
    }
    
    public static void crearNuevaTabla(String url_bd, String nombreBD, String nombreTabla, String sql_query) {
        // SQLite connection string
        String url = url_bd + nombreBD;
                
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql_query);
            JOptionPane.showMessageDialog(null, "Creada la Tabla: " + nombreTabla);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (crearNuevaTabla): " + e.getMessage());
        }
    }
    
    private Connection conectarBD(String url_bd, String nombreBD) {
        // SQLite connection string
        String url = url_bd + nombreBD;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //JOptionPane.showMessageDialog(null, "Conectado a la Base de Datos: " + nombreBD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (conectarBD): " + e.getMessage());
        }
        return conn;
    }
    
    public void insertarEmpresa(String url_bd, String nombreBD, String nombre_tabla, Empresa empresa) {
        String sql = "INSERT INTO " + nombre_tabla + "(id_empresa,nif_empresa,nombre_empresa,direccion_empresa,telefono_empresa,email_empresa) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empresa.getId_empresa());
            pstmt.setString(2, empresa.getNif_empresa());
            pstmt.setString(3, empresa.getNombre_empresa());
            pstmt.setString(4, empresa.getDireccion_empresa());
            pstmt.setString(5, empresa.getTelefono_empresa());
            pstmt.setString(6, empresa.getEmail_empresa());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (insertarEmpresa): " + e.getMessage());
        }
    }
    
    // "departamentos"
    // [fk_id_empresa, id_departamento]
    // [int, int]
    // [1, 5]
    
    
    public void insertarDepartamento(String url_bd, String nombreBD, String nombre_tabla, Departamento departamento) {
        String sql = "INSERT INTO " + nombre_tabla + "(fk_id_empresa,id_departamento,nombre,descripcion,horario,numero_oficina,email) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departamento.getFk_id_empresa());
            pstmt.setInt(2, departamento.getId_departamento());
            pstmt.setString(3, departamento.getNombre());
            pstmt.setString(4, departamento.getDescripcion());
            pstmt.setString(5, departamento.getHorario());
            pstmt.setString(6, departamento.getNumero_oficina());
            pstmt.setString(7, departamento.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (insertarDepartamento): " + e.getMessage());
        }
    }
    
    public void insertarEmpleado(String url_bd, String nombreBD, String nombre_tabla, Empleado empleado) {
        String sql = "INSERT INTO " + nombre_tabla + "(fk_id_departamento,id_empleado,nombre,apellidos,fecha_nacimiento,email,telefono) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, empleado.getFk_id_departamento());
            pstmt.setInt(2, empleado.getId_empleado());
            pstmt.setString(3, empleado.getNombre());
            pstmt.setString(4, empleado.getApellidos());
            pstmt.setString(5, empleado.getFecha_nacimiento());
            pstmt.setString(6, empleado.getEmail());
            pstmt.setString(7, empleado.getTelefono());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (insertarEmpleado): " + e.getMessage());
        }
    }
    
    public void getDatosBD(String url_bd, String nombreBD, ArrayList<Empresa> listado_empresas, ArrayList<Departamento> listado_departamentos, ArrayList<Empleado> listado_empleados){
        String sql_empresas = "SELECT id_empresa, nif_empresa, nombre_empresa, direccion_empresa, telefono_empresa, email_empresa FROM empresas";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql_empresas)){

                listado_empresas.clear();
                // loop through the result set
                while (rs.next()) {
                    int id_empresa_bd = rs.getInt("id_empresa");
                    String nif_empresa = rs.getString("nif_empresa");
                    String nombre_empresa = rs.getString("nombre_empresa");
                    String direccion_empresa = rs.getString("direccion_empresa");
                    String telefono_empresa = rs.getString("telefono_empresa");
                    String email_empresa = rs.getString("email_empresa");
                    Empresa empresa = new Empresa(true, id_empresa_bd, nif_empresa, nombre_empresa, direccion_empresa, telefono_empresa, email_empresa);
                    lista_empresas.add(empresa);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (getDatosBD) empresas: " + e.getMessage());
        }
        
        String sql_departamentos = "SELECT fk_id_empresa, id_departamento, nombre, descripcion, horario, numero_oficina, email FROM departamentos";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql_departamentos)){

                listado_departamentos.clear();
                // loop through the result set
                while (rs.next()) {
                    int fk_id_empresa = rs.getInt("fk_id_empresa");
                    int id_departamento = rs.getInt("id_departamento");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String horario = rs.getString("horario");
                    String numero_oficina = rs.getString("numero_oficina");
                    String email = rs.getString("email");
                    Departamento departamento = new Departamento(true, fk_id_empresa, id_departamento, nombre, descripcion, horario, numero_oficina, email);
                    listado_departamentos.add(departamento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (getDatosBD) departamentos: " + e.getMessage());
        }
        
        String sql_empleados = "SELECT fk_id_departamento, id_empleado, nombre, apellidos, fecha_nacimiento, email, telefono FROM empleados";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql_empleados)){

                listado_empleados.clear();
                // loop through the result set
                while (rs.next()) {
                    int fk_id_departamento = rs.getInt("fk_id_departamento");
                    int id_empleado = rs.getInt("id_empleado");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    String fecha_nacimiento = rs.getString("fecha_nacimiento");
                    String email = rs.getString("email");
                    String telefono = rs.getString("telefono");
                    Empleado empleado = new Empleado(true, fk_id_departamento, id_empleado, nombre, apellidos, fecha_nacimiento, email, telefono);
                    listado_empleados.add(empleado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (getDatosBD) empleados: " + e.getMessage());
        }
    }
    
    public void actualizarEmpresa(String url_bd, String nombreBD, int id_empresa, String nif_empresa, String nombre_empresa, String direccion_empresa, String telefono_empresa, String email_empresa) {
        String sql = "UPDATE empresas SET id_empresa = ? , "
                + "WHERE id_empresa = ?";
        //String sql = "DELETE FROM warehouses WHERE id = ?";
        try (Connection conn = this.conectarBD(url_bd, nombreBD);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id_empresa);
            pstmt.setString(2, nif_empresa);
            pstmt.setString(3, nombre_empresa);
            pstmt.setString(4, direccion_empresa);
            pstmt.setString(5, telefono_empresa);
            pstmt.setString(6, email_empresa);
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error (actualizarEmpresa): " + e.getMessage());
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_aniadir_departamento;
    private javax.swing.JButton jButton_aniadir_empleado;
    public javax.swing.JButton jButton_aniadir_empresa;
    private javax.swing.JButton jButton_borrar_departamento;
    private javax.swing.JButton jButton_borrar_empleado;
    public javax.swing.JButton jButton_borrar_empresa;
    public javax.swing.JButton jButton_cancelar_departamento;
    private javax.swing.JButton jButton_cancelar_empleado;
    private javax.swing.JButton jButton_cancelar_empresa;
    public javax.swing.JButton jButton_guardar_departamento;
    private javax.swing.JButton jButton_guardar_empleado;
    private javax.swing.JButton jButton_guardar_empresa;
    private javax.swing.JButton jButton_modificar_departamento;
    private javax.swing.JButton jButton_modificar_empleado;
    public javax.swing.JButton jButton_modificar_empresa;
    public javax.swing.JComboBox<String> jComboBox_departamento;
    public javax.swing.JComboBox<String> jComboBox_empresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_apellidos_empleado;
    private javax.swing.JLabel jLabel_departamento_empleado;
    private javax.swing.JLabel jLabel_descripcion_departamento;
    private javax.swing.JLabel jLabel_direccion_empresa;
    private javax.swing.JLabel jLabel_email_departamento;
    private javax.swing.JLabel jLabel_email_empleado;
    private javax.swing.JLabel jLabel_email_empresa;
    private javax.swing.JLabel jLabel_empresa_departamento;
    private javax.swing.JLabel jLabel_fecha_nac_empleado;
    private javax.swing.JLabel jLabel_horario_departamento;
    private javax.swing.JLabel jLabel_nif_empresa;
    private javax.swing.JLabel jLabel_nombre_departamento;
    private javax.swing.JLabel jLabel_nombre_empleado;
    private javax.swing.JLabel jLabel_nombre_empresa;
    private javax.swing.JLabel jLabel_numero_de_oficina;
    private javax.swing.JLabel jLabel_telefono_empleado;
    private javax.swing.JLabel jLabel_telefono_empresa;
    private javax.swing.JPanel jPanel_departamento;
    private javax.swing.JPanel jPanel_empleado;
    private javax.swing.JPanel jPanel_persona;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable_departamento;
    public javax.swing.JTable jTable_empleado;
    public javax.swing.JTable jTable_empresa;
    public javax.swing.JTextField jTextField_apellidos_empleado;
    private javax.swing.JTextField jTextField_descripcion_departamento;
    private javax.swing.JTextField jTextField_direccion_empresa;
    private javax.swing.JTextField jTextField_email_departamento;
    public javax.swing.JTextField jTextField_email_empleado;
    private javax.swing.JTextField jTextField_email_empresa;
    public javax.swing.JTextField jTextField_fecha_nacimiento_empleado;
    private javax.swing.JTextField jTextField_horario_departamento;
    private javax.swing.JTextField jTextField_nif_empresa;
    private javax.swing.JTextField jTextField_nombre_departamento;
    public javax.swing.JTextField jTextField_nombre_empleado;
    private javax.swing.JTextField jTextField_nombre_empresa;
    private javax.swing.JTextField jTextField_numero_de_oficina;
    public javax.swing.JTextField jTextField_telefono_empleado;
    private javax.swing.JTextField jTextField_telefono_empresa;
    // End of variables declaration//GEN-END:variables
    
    public void formWindowClosing(java.awt.event.WindowEvent evt){
        
    }

    

}
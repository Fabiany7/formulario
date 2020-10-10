import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;

import java.util.ArrayList;

import org.apache.log4j.Logger;


public class ViewCiclos extends JFrame implements  FocusListener{

    private static final Logger LOGGER = Logger.getLogger(ViewCiclos.class);

    JLabel txtTitulo;
    JButton boton1;
    JButton boton2;
    JLabel txtCodeCiclo;
    JTextField inCodeCiclo;
    JLabel txtNameCiclo;
    JTextField inNameCiclo;
    DefaultTableModel tableModel;
    JTable table;

    public ViewCiclos(){

        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("ciclos");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        txtTitulo = new JLabel("CREAR CICLOS");
        txtTitulo.setBounds(250, 10, 150, 30);
        panel.add(txtTitulo);
        txtCodeCiclo = new JLabel("CODIGO CICLO");
        txtCodeCiclo.setBounds(60, 55, 150, 30);
        panel.add(txtCodeCiclo);
        inCodeCiclo = new JTextField();
        inCodeCiclo.setBounds(180, 58, 150, 20);
        panel.add(inCodeCiclo);
        txtNameCiclo = new JLabel("NOMBRE CICLO");
        txtNameCiclo.setBounds(60, 95, 150, 30);
        panel.add(txtNameCiclo);
        inNameCiclo = new JTextField();
        inNameCiclo.setBounds(180, 98, 150, 20);
        panel.add(inNameCiclo);
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(50, 430, 150, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(e -> {agregar();});
        panel.add(boton1);
        boton2 = new JButton("CERRAR");
        boton2.setBounds(380, 430, 150, 30);
        boton2.addActionListener(e -> {salida(e);});
        boton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(boton2);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> {selectTable();});
        table.addFocusListener(this);
        tableModel.addColumn("Código");
        tableModel.addColumn("Descripción");
        addCiclos(tableModel);
        table.setBounds(150, 320, 320, 150);
        Border b = BorderFactory.createLineBorder(Color.black);
        table.setBorder(b);
        JInternalFrame ifm = new JInternalFrame();
        ifm.setBounds(130, 180, 350, 180);
        JScrollPane sp = new JScrollPane(table);
        ifm.add(sp);
        ifm.setVisible(true);
        BasicInternalFrameUI bi = (BasicInternalFrameUI) ifm.getUI();
        bi.setNorthPane(null);
        panel.add(ifm);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        boton1.setEnabled(true);
    }

    private void agregar() {
        Writer writer = null;
        try {
            writer = new FileWriter("./txt/datosCiclos.txt", true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Ciclos ciclos = new Ciclos();
        ArrayList<JTextField> datos = new ArrayList<JTextField>();

        datos.add(inCodeCiclo);
        datos.add(inNameCiclo);
        ciclos.setCodigoCiclo(datos.get(0).getText());
        ciclos.setNombreCiclo(datos.get(1).getText());

        try {
            writer.write(ciclos.getCodigoCiclo() + ";" + ciclos.getNombreCiclo() + "\n");
            writer.flush();
            datos.get(0).setText("");
            datos.get(1).setText("");
        } catch (IOException e1) {
            LOGGER.error("ERROR AL INTENTAR OBTENER EL RECURSO ",e1);
        }


    }

    private void selectTable() {
        if (table.getSelectedRow() > -1) {
            System.out.println(table.getSelectedRow());
            inNameCiclo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
            inCodeCiclo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            boton1.setEnabled(false);
        }
    }

    private void salida(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == boton2) {
            System.exit(1);
        }
    }

    public void addCiclos(DefaultTableModel tableModel) {
        try {
            FileReader rd = new FileReader("./txt/datosCiclos.txt");
            BufferedReader br = new BufferedReader(rd);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(";");
                tableModel.addRow(datos);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AL INTENTAR OBTENER EL RECURSO DATACICLOS ",e);
        }
    }
}

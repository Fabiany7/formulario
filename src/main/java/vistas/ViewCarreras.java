package vistas;

import modelos.Carreras;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class ViewCarreras extends JFrame implements  FocusListener,ActionListener{

    JLabel txtTitulo;
    JButton boton1;
    JButton boton2;
    JButton boton3;
    JButton boton4;
    JLabel txtCodigo;
    JTextField inCodigo;
    JLabel txtNombre;
    JTextField inNombre;
    JLabel txtJornada;
    JTextField inJornada;
    JLabel txtSede;
    JTextField inSede;
    DefaultTableModel tableModel;
    JTable table;

    public ViewCarreras(){
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("modelos.Carreras");
        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(null);
        txtTitulo = new JLabel("CREAR CARRERAS");
        txtTitulo.setBounds(250, 10, 150, 30);
        panel.add(txtTitulo);
        txtCodigo = new JLabel("Codigo Carrera");
        txtCodigo.setBounds(60, 55, 150, 30);
        panel.add(txtCodigo);
        inCodigo = new JTextField();
        inCodigo.setBounds(180, 58, 150, 20);
        panel.add(inCodigo);
        txtNombre = new JLabel("Nombre Carrera");
        txtNombre.setBounds(60, 95, 150, 30);
        panel.add(txtNombre);
        inNombre = new JTextField();
        inNombre.setBounds(180, 98, 150, 20);
        panel.add(inNombre);
        txtJornada = new JLabel("Jornada");
        txtJornada.setBounds(60, 140, 150, 30);
        panel.add(txtJornada);
        inJornada = new JTextField();
        inJornada.setBounds(180, 143, 150, 20);
        panel.add(inJornada);
        txtSede = new JLabel("Sede");
        txtSede.setBounds(60, 185, 150, 30);
        panel.add(txtSede);
        inSede = new JTextField();
        inSede.setBounds(180, 188, 150, 20);
        panel.add(inSede);
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(40, 430, 110, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(e -> agregarCarrera());
        panel.add(boton1);
        boton4 = new JButton("ACTUALIZAR");
        boton4.setBounds(170, 430, 110, 30);
        boton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton4.addActionListener(e -> { actualizaDatos(); });
        panel.add(boton4);
        boton3 = new JButton("BORRAR");
        boton3.setBounds(300, 430, 110, 30);
        boton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton3.addActionListener(e -> { borrarDatos(); });
        panel.add(boton3);
        boton2 = new JButton("CERRAR");
        boton2.setBounds(430, 430, 110, 30);
        boton2.addActionListener(this);
        boton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(boton2);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> selectTabla());
        table.addFocusListener(this);
        tableModel.addColumn("Código");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Jornada");
        tableModel.addColumn("Sede");
        addCiclos(tableModel);
        table.setBounds(150, 320, 320, 150);
        Border b = BorderFactory.createLineBorder(Color.black);
        table.setBorder(b);
        JInternalFrame ifm = new JInternalFrame();
        ifm.setBounds(130, 230, 350, 180);
        JScrollPane sp = new JScrollPane(table);
        ifm.add(sp);
        ifm.setVisible(true);
        BasicInternalFrameUI bi = (BasicInternalFrameUI) ifm.getUI();
        bi.setNorthPane(null);
        panel.add(ifm);

    }

    public void agregarCarrera(){

        ArrayList<JTextField> datos = new ArrayList<>();
        Carreras carreras = new Carreras();
        Writer writer = null;
        try {
            writer = new FileWriter("./txt/datosCarrera.txt",true);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        datos.add(inCodigo);
        datos.add(inNombre);
        datos.add(inJornada);
        datos.add(inSede);

        carreras.setCodigoCarrera(datos.get(0).getText());
        carreras.setNombreCarrera(datos.get(1).getText());
        carreras.setJornada(datos.get(2).getText());
        carreras.setNombreSede(datos.get(3).getText());

        try {
            assert writer != null;
            writer.write(carreras.getCodigoCarrera() + ";" + carreras.getNombreCarrera() +";" +carreras.getJornada() + ";" + carreras.getNombreSede() + "\n");
            writer.flush();
            writer.close();
            if (this.tableModel.getRowCount() > 0){
                for (int i = this.tableModel.getRowCount() -1; i >= 0 ; i--){
                    this.tableModel.removeRow(i);
                }
            }
            addCiclos(this.tableModel);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        boton1.setEnabled(true);
    }
    private void selectTabla() {
        if (table.getSelectedRow() > -1){
            System.out.println(table.getSelectedRow());
            inNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString()) ;
            inCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString()) ;
            inJornada.setText(table.getValueAt(table.getSelectedRow(), 2).toString()) ;
            inSede.setText(table.getValueAt(table.getSelectedRow(), 3).toString()) ;
            boton1.setEnabled(false);
        }
    }

    public void addCiclos(DefaultTableModel tableModel) {
        try{

            FileReader rd =new FileReader("./txt/datosCarrera.txt");
            BufferedReader br = new BufferedReader(rd);
            String line;
            while ((line = br.readLine()) != null ){
                String[] datos = line.split(";");
                tableModel.addRow(datos);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == boton2) {
            //System.exit(1);
            dispose();
        }
    }

    public void borrarDatos(){
        String codigo = "";
        codigo = this.inCodigo.getText();
        try {
            File fl = new File("./txt/datosCarrera.txt");
            File flt = new File("./txt/datosCarreraTemp.txt");
            FileReader fr = new FileReader(fl);
            FileWriter fw = new FileWriter(flt);
            BufferedReader br = new BufferedReader(fr);
            String line= "";
            while (( line = br.readLine()) != null){
                String[] datos = line.split(";");
                if (!datos[0].equals(codigo)){
                    fw.write(line + '\n');
                }
            }
            br.close();
            fr.close();
            fw.close();
            Files.copy(flt.toPath(), fl.toPath(), StandardCopyOption.REPLACE_EXISTING);
            flt.delete();
            if (this.tableModel.getRowCount() > 0){
                for (int i = this.tableModel.getRowCount() -1; i >= 0 ; i--){
                    this.tableModel.removeRow(i);
                }
            }
            addCiclos(this.tableModel);
        }catch (IOException io){
            io.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void actualizaDatos(){
        String codigo = "";
        String valor = "";
        String jornada = "";
        String sede = "";
        codigo = this.inCodigo.getText();
        valor = this.inNombre.getText();
        jornada =  this.inJornada.getText();
        sede =  this.inSede.getText();
        try {
            File fl = new File("./txt/datosCarrera.txt");
            File flt = new File("./txt/datosCarreraTemp.txt");
            FileReader fr = new FileReader(fl);
            FileWriter fw = new FileWriter(flt);
            BufferedReader br = new BufferedReader(fr);
            String line= "";
            while (( line = br.readLine()) != null){
                String[] datos = line.split(";");
                if (!datos[0].equals(codigo)){
                    fw.write(line + '\n');
                }else {
                    fw.write(codigo+";"+valor +";"+jornada + ";"+sede+ '\n');
                }
            }
            br.close();
            fr.close();
            fw.close();
            Files.copy(flt.toPath(), fl.toPath(), StandardCopyOption.REPLACE_EXISTING);
            flt.delete();
            if (this.tableModel.getRowCount() > 0){
                for (int i = this.tableModel.getRowCount() -1; i >= 0 ; i--){
                    this.tableModel.removeRow(i);
                }
            }
            addCiclos(this.tableModel);
        }catch (IOException io){
            io.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

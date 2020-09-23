import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ViewDatosBasicos extends JFrame {

    public ViewDatosBasicos() throws IOException {

        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("datos basicos");
        PanelMenu panel = new PanelMenu();
        add(panel);

    }

}

class PanelMenu extends JPanel implements ActionListener, ChangeListener {
    JLabel txtIngreso;
    JLabel txtName;
    JTextField inName;
    JLabel txtLastName;
    JTextField inLastName;
    JButton boton1;
    JButton boton2;
    JTextField inDocumento;
    JLabel txtDocumento;
    JLabel txtGenero;
    JRadioButton opFemenino;
    JRadioButton opMasculino;
    JComboBox actividades;
    JComboBox carreras;
    JComboBox semestres;

    public PanelMenu() throws IOException {

        setLayout(null);
        txtIngreso = new JLabel("FORMULARIO");
        txtIngreso.setBounds(250, 10, 150, 30);
        add(txtIngreso);
        txtName = new JLabel("NOMBRE");
        txtName.setBounds(60, 55, 150, 30);
        add(txtName);
        inName = new JTextField();
        inName.setBounds(160, 58, 150, 20);
        add(inName);
        txtLastName = new JLabel("APELLIDO");
        txtLastName.setBounds(60, 95, 150, 30);
        add(txtLastName);
        inLastName = new JTextField();
        inLastName.setBounds(160, 98, 150, 20);
        add(inLastName);
        txtDocumento = new JLabel("DOCUMENTO");
        txtDocumento.setBounds(60, 140, 150, 30);
        add(txtDocumento);
        inDocumento = new JTextField();
        inDocumento.setBounds(160, 143, 150, 20);
        add(inDocumento);
        txtGenero = new JLabel("GENERO");
        txtGenero.setBounds(60, 170, 150, 30);
        add(txtGenero);
        opFemenino = new JRadioButton("femenino");
        opFemenino.setBounds(160, 170, 150, 30);
        opFemenino.addChangeListener(this);
        add(opFemenino);
        opMasculino = new JRadioButton("masculino");
        opMasculino.setBounds(320, 170, 150, 30);
        opMasculino.addChangeListener(this);
        add(opMasculino);
        // definicion de actividad economica
        txtGenero = new JLabel("Actividad: ");
        txtGenero.setBounds(60, 200, 150, 30);
        add(txtGenero);
        // combo box actividad economica
        String s1[] = {
                Constantes.EMPLEADO,
                Constantes.ESTUDIANTE,
                Constantes.INDEPENDIENTE,
                Constantes.TRABAJADOR
        };
        actividades = new JComboBox(s1);
        actividades.setBounds(160,210,150,20);
        add(actividades);
        // fin actividades
        // definicion de actividad Carrera
        txtGenero = new JLabel("Carrera: ");
        txtGenero.setBounds(60, 240, 150, 30);
        add(txtGenero);
        // combo box Carrera
        List<String> listaCar = new ArrayList<>();
        Utilitario util = new Utilitario();
        util.datos("carrera").stream().forEach((x) -> listaCar.add(x.split(";")[1]) );
        carreras = new JComboBox(listaCar.toArray());
        carreras.setBounds(160,250,150,20);
        add(carreras);
        // fin carreras
        // definicion de actividad Semestre
        txtGenero = new JLabel("Semestre: ");
        txtGenero.setBounds(60, 280, 150, 30);
        add(txtGenero);
        // combo box Semestre
        List<String> listaSem = new ArrayList<>();
        util.datos("semestre").stream().forEach((x) -> listaSem.add(x.split(";")[1]) );
        semestres = new JComboBox(listaSem.toArray());
        semestres.setBounds(160,290,150,20);
        add(semestres);
        // fin carreras
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(50, 370, 150, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(new Agregar(inName, inLastName, inDocumento));
        add(boton1);
        boton2 = new JButton("CERRAR");
        boton2.setBounds(380, 370, 150, 30);
        boton2.addActionListener(this);
        boton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(boton2);


    }




    public void stateChanged(ChangeEvent e) {

        if (opFemenino.isSelected() == true) {
            Agregar.actualizaSexo("FEMENINO");
            opMasculino.setSelected(false);
        } else if (opMasculino.isSelected() == true) {
            Agregar.actualizaSexo("MASCULINO");
            opFemenino.setSelected(false);
        }
    }


    public void actionPerformed(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == boton2) {
            System.exit(1);
        }


    }

}


class Agregar implements ActionListener {
    private static Estudiante estude = new Estudiante();
    ArrayList<JTextField> datos = new ArrayList<JTextField>();
    Writer writer = new FileWriter("./txt/datosBasicos.txt",true);
    private static final Logger LOGGER = Logger.getLogger(Agregar.class);

    public Agregar(JTextField name, JTextField lastName, JTextField documento) throws IOException {
        this.datos.add(name);
        this.datos.add(lastName);
        this.datos.add(documento);
    }

    public void actionPerformed(ActionEvent e) {

        estude.setNombre(datos.get(0).getText());
        estude.setApellido(datos.get(1).getText());
        estude.setDocumento(datos.get(2).getText());
        try {
//            writer.write("Nombre :" + estude.getNombre() + "\n"+ "Apellido :" + estude.getApellido() +"\n" +"Documento:  "+estude.getDocumento()+ "\n" + "Genero: "+ estude.getSexo());
            writer.write(estude.getNombre() + ";" +estude.getApellido()+ ";" +estude.getDocumento()+ ";" +estude.getSexo()+ "\n");
            writer.close();
        } catch (IOException e1) {
            LOGGER.error( "ERROR AL CERRAR WRITER ",e1 );
        }

    }

    public static void actualizaSexo(String sexo) {
        estude.setSexo(sexo);
    }
}
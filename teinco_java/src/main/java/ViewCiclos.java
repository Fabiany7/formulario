import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.log4j.Logger;


public class ViewCiclos extends JFrame{

    public ViewCiclos() throws IOException {
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("ciclos");
        PanelMenuCiclos panel = new PanelMenuCiclos();
        add(panel);
    }
}

class PanelMenuCiclos extends JPanel implements ActionListener, ChangeListener {

    JLabel txtTitulo;
    JButton boton1;
    JButton boton2;
    JLabel txtCodeCiclo;
    JTextField inCodeCiclo;
    JLabel txtNameCiclo;
    JTextField inNameCiclo;

    public PanelMenuCiclos() throws IOException {
        setLayout(null);
        txtTitulo = new JLabel("CREAR CICLOS");
        txtTitulo.setBounds(250, 10, 150, 30);
        add(txtTitulo);
        txtCodeCiclo = new JLabel("CODIGO CICLO");
        txtCodeCiclo.setBounds(60, 55, 150, 30);
        add(txtCodeCiclo);
        inCodeCiclo = new JTextField();
        inCodeCiclo.setBounds(180, 58, 150, 20);
        add(inCodeCiclo);
        txtNameCiclo = new JLabel("NOMBRE CICLO");
        txtNameCiclo.setBounds(60, 95, 150, 30);
        add(txtNameCiclo);
        inNameCiclo = new JTextField();
        inNameCiclo.setBounds(180, 98, 150, 20);
        add(inNameCiclo);
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(50, 230, 150, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(new AgregarCiclo(inCodeCiclo,inNameCiclo));
        add(boton1);
        boton2 = new JButton("CERRAR");
        boton2.setBounds(380, 230, 150, 30);
        boton2.addActionListener(this);
        boton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(boton2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == boton2) {
            System.exit(1);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
class AgregarCiclo implements ActionListener {
    Writer writer = new FileWriter("//home//fmadrigal//Escritorio//teinco_java//txt//datosCiclos.txt");
    Ciclos ciclos = new Ciclos();
    ArrayList<JTextField> datos = new ArrayList<JTextField>();
    private static final Logger LOGGER = Logger.getLogger(AgregarCiclo.class);

    public AgregarCiclo(JTextField inCodeCiclo,JTextField inNameCiclo) throws IOException {
        this.datos.add(inCodeCiclo);
        this.datos.add(inNameCiclo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ciclos.setCodigoCiclo(datos.get(0).getText());
        ciclos.setNombreCiclo(datos.get(1).getText());
        try {
            writer.write("Codigo Ciclo :" + ciclos.getCodigoCiclo() + "\n"+ "Nombre Ciclo :" + ciclos.getNombreCiclo());
            writer.close();
        } catch (IOException e1) {
            LOGGER.error("Error al intentar escribir o cerrar ",e1);
        }

    }
}
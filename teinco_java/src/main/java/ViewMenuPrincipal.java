import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewMenuPrincipal extends JFrame{

    public ViewMenuPrincipal() {
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("formulario estudiante");
        PanelMenuInicio panel = new PanelMenuInicio();
        add(panel);
    }


}

class PanelMenuInicio extends JPanel implements ActionListener, ChangeListener {

    private static final Logger LOGGER = Logger.getLogger(PanelMenuCiclos.class);
    JLabel txtTitulo;
    JButton btnDatos;
    JButton btnCarrera;
    JButton btnSemestre;
    JButton btnCiclos;
    JButton btnSalir;

    public PanelMenuInicio(){
        setLayout(null);
        txtTitulo = new JLabel("MENÚ PRINCIPAL");
        txtTitulo.setBounds(250, 10, 150, 30);
        add(txtTitulo);
        btnDatos = new JButton("CREAR DATOS BÁSICOS");
        btnDatos.setBounds(170, 50, 250, 30);
        btnDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDatos.addActionListener(this);
        add(btnDatos);
        btnCarrera = new JButton("CREAR CARRERAS");
        btnCarrera.setBounds(170, 90, 250, 30);
        btnCarrera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCarrera.addActionListener(this);
        add(btnCarrera);
        btnSemestre = new JButton("CREAR SEMESTRE");
        btnSemestre.setBounds(170, 130, 250, 30);
        btnSemestre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSemestre.addActionListener(this);
        add(btnSemestre);
        btnCiclos = new JButton("CREAR CICLOS");
        btnCiclos.setBounds(170, 170, 250, 30);
        btnCiclos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCiclos.addActionListener(this);
        add(btnCiclos);
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(260, 220, 80, 30);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(this);
        add(btnSalir);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == btnSalir) {
            System.exit(1);
        }
        if(btnPulsado == btnDatos){
            ViewDatosBasicos viewDatosBasicos = null;
            try {
                viewDatosBasicos = new ViewDatosBasicos();
                viewDatosBasicos.setVisible(true);
            } catch (IOException e1) {
                LOGGER.error("ERROR AL OBTENER LA VISTA DE DATOS BASICOS ",e1);
            }
        }
        if(btnPulsado == btnCarrera){
            ViewCarreras viewCarreras = null;
            try {
                viewCarreras = new ViewCarreras();
                viewCarreras.setVisible(true);
            } catch (IOException e2) {
                LOGGER.error("ERROR AL OBTENER LA VISTA DE CARRERAS",e2);
            }
        }
        if(btnPulsado == btnSemestre){
            ViewSemestres viewSemestres = null;
            try {
                viewSemestres = new ViewSemestres();
                viewSemestres.setVisible(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
             if(btnPulsado == btnCiclos){
            ViewCiclos viewCiclos = null;
            try {
                viewCiclos = new ViewCiclos();
                viewCiclos.setVisible(true);
            } catch (IOException e3) {
                LOGGER.error("ERROR AL OBTENER LA VISTA DE CICLO",e3);
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
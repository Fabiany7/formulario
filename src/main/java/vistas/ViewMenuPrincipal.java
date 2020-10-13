package vistas;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewMenuPrincipal extends JFrame implements ActionListener, ChangeListener{

    private static final Logger LOGGER = Logger.getLogger(ViewMenuPrincipal.class);
    JLabel txtTitulo;
    JButton btnDatos;
    JButton btnCarrera;
    JButton btnSemestre;
    JButton btnCiclos;
    JButton btnSalir;

    public ViewMenuPrincipal() {
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("formulario estudiante");
        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(null);
        txtTitulo = new JLabel("MENÚ PRINCIPAL");
        txtTitulo.setBounds(250, 10, 150, 30);
        panel.add(txtTitulo);
        btnDatos = new JButton("CREAR DATOS BÁSICOS");
        btnDatos.setBounds(170, 50, 250, 30);
        btnDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDatos.addActionListener(this);
        panel.add(btnDatos);
        btnCarrera = new JButton("CREAR CARRERAS");
        btnCarrera.setBounds(170, 90, 250, 30);
        btnCarrera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCarrera.addActionListener(this);
        panel.add(btnCarrera);
        btnSemestre = new JButton("CREAR SEMESTRE");
        btnSemestre.setBounds(170, 130, 250, 30);
        btnSemestre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSemestre.addActionListener(this);
        panel.add(btnSemestre);
        btnCiclos = new JButton("CREAR CICLOS");
        btnCiclos.setBounds(170, 170, 250, 30);
        btnCiclos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCiclos.addActionListener(this);
        panel.add(btnCiclos);
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(260, 220, 80, 30);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(this);
        panel.add(btnSalir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnPulsado = e.getSource();
        if (btnPulsado == btnSalir) {
            System.exit(1);
        }
        if(btnPulsado == btnDatos){
            ViewDatosBasicos viewDatosBasicos;
            try {
                viewDatosBasicos = new ViewDatosBasicos();
                viewDatosBasicos.setVisible(true);
            } catch (IOException e1) {
                LOGGER.error("ERROR AL OBTENER LA VISTA DE DATOS BASICOS ",e1);
            }
        }
        if(btnPulsado == btnCarrera){
            ViewCarreras viewCarreras;
            viewCarreras = new ViewCarreras();
            viewCarreras.setVisible(true);
        }
        if(btnPulsado == btnSemestre){
            ViewSemestres viewSemestres;
            try {
                viewSemestres = new ViewSemestres();
                viewSemestres.setVisible(true);
            } catch (IOException e3) {
                LOGGER.error("ERROR AL OBTENER LA VISTA DE CARRERAS",e3);
            }
        }
        if(btnPulsado == btnCiclos){
            ViewCiclos viewCiclos;
            viewCiclos = new ViewCiclos();
            viewCiclos.setVisible(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
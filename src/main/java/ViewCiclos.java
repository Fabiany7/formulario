import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;


public class ViewCiclos extends JFrame{

    public ViewCiclos() throws IOException {
        setSize(600, 550);
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
    DefaultTableModel tableModel;
    JTable table;

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
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        //table.getSelectionModel().addListSelectionListener(new SeleccionaCiclo(inCodeCiclo,inNameCiclo));
        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() > -1){
                inNameCiclo.setText(table.getValueAt(table.getSelectedRow(), 1).toString()) ;
                inCodeCiclo .setText(table.getValueAt(table.getSelectedRow(), 0).toString()) ;
            }
        });
        tableModel.addColumn("Código");
        tableModel.addColumn("Descripción");
        addCiclos(tableModel);
        table.setBounds(150,320, 320,150);
        Border b = BorderFactory.createLineBorder(Color.black);
        table.setBorder(b);
        JInternalFrame ifm= new JInternalFrame();
        ifm.setBounds(130,280,350,180);
        JScrollPane sp = new JScrollPane(table);
        ifm.add(sp);
        ifm.setVisible(true);
        BasicInternalFrameUI bi = (BasicInternalFrameUI)ifm.getUI();
        bi.setNorthPane(null);
        add(ifm);
    }
    public void addCiclos(DefaultTableModel tableModel) {
        try{
            //File fl = new File("./txt/datosCiclos.txt");
            FileReader rd =new FileReader("./txt/datosCiclos.txt");
            BufferedReader br = new BufferedReader(rd);
            String line = "";
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
            System.exit(1);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}

class AgregarCiclo implements ActionListener {
    Writer writer = new FileWriter("./txt/datosCiclos.txt", true);
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
            writer.write(ciclos.getCodigoCiclo() + ";"+ciclos.getNombreCiclo()+ "\n");
            writer.flush();
            datos.get(0).setText("");
            datos.get(1).setText("");
        } catch (IOException e1) {
            LOGGER.error("Error al intentar escribir o cerrar ",e1);
        }catch (Exception ex){
            LOGGER.error("fallo en proceso", ex);
        }

    }
    public void  checkOpen(){
        try {
            Writer writer2 = new FileWriter("./txt/datosCiclos.txt", true);
            System.out.println("ok");
        }catch (Exception e){
            LOGGER.error("archivo");
        }
    }
}
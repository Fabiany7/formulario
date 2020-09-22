import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;

public class ViewCarreras extends JFrame{
    public ViewCarreras() throws IOException {
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("Carreras");
        PanelMenuCarrera panel = new PanelMenuCarrera();
        add(panel);
    }
}
class PanelMenuCarrera extends JPanel implements ActionListener, ChangeListener {

    JLabel txtTitulo;
    JButton boton1;
    JButton boton2;
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

    public PanelMenuCarrera() throws IOException {
        setLayout(null);
        txtTitulo = new JLabel("CREAR CARRERAS");
        txtTitulo.setBounds(250, 10, 150, 30);
        add(txtTitulo);
        txtCodigo = new JLabel("Codigo Carrera");
        txtCodigo.setBounds(60, 55, 150, 30);
        add(txtCodigo);
        inCodigo = new JTextField();
        inCodigo.setBounds(180, 58, 150, 20);
        add(inCodigo);
        txtNombre = new JLabel("Nombre Carrera");
        txtNombre.setBounds(60, 95, 150, 30);
        add(txtNombre);
        inNombre = new JTextField();
        inNombre.setBounds(180, 98, 150, 20);
        add(inNombre);
        txtJornada = new JLabel("Jornada");
        txtJornada.setBounds(60, 140, 150, 30);
        add(txtJornada);
        inJornada = new JTextField();
        inJornada.setBounds(180, 143, 150, 20);
        add(inJornada);
        txtSede = new JLabel("Sede");
        txtSede.setBounds(60, 185, 150, 30);
        add(txtSede);
        inSede = new JTextField();
        inSede.setBounds(180, 188, 150, 20);
        add(inSede);
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(50, 450, 150, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(new AgregarCarrera(inCodigo,inNombre,inJornada,inSede));
        add(boton1);
        boton2 = new JButton("CERRAR");
        boton2.setBounds(380, 450, 150, 30);
        boton2.addActionListener(this);
        boton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(boton2);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        //table.getSelectionModel().addListSelectionListener(new SeleccionaCiclo(inCodeCiclo,inNameCiclo));
        table.getSelectionModel().addListSelectionListener(e -> {
            if (table.getSelectedRow() > -1){
                System.out.println(table.getSelectedRow());
                inNombre.setText(table.getValueAt(table.getSelectedRow(), 1).toString()) ;
                inCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString()) ;
                inJornada.setText(table.getValueAt(table.getSelectedRow(), 2).toString()) ;
                inSede.setText(table.getValueAt(table.getSelectedRow(), 3).toString()) ;
                boton1.setEnabled(false);
            }

        });
        table.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                boton1.setEnabled(true);
            }
        });
        tableModel.addColumn("Código");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Jornada");
        tableModel.addColumn("Sede");
        addCiclos(tableModel);
        table.setBounds(150,320, 320,150);
        Border b = BorderFactory.createLineBorder(Color.black);
        table.setBorder(b);
        JInternalFrame ifm= new JInternalFrame();
        ifm.setBounds(130,230,350,180);
        JScrollPane sp = new JScrollPane(table);
        ifm.add(sp);
        ifm.setVisible(true);
        BasicInternalFrameUI bi = (BasicInternalFrameUI)ifm.getUI();
        bi.setNorthPane(null);
        add(ifm);
    }
    public void addCiclos(DefaultTableModel tableModel) {
        try{

            FileReader rd =new FileReader("./txt/datosCarrera.txt");
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
class AgregarCarrera implements ActionListener {
    ArrayList<JTextField> datos = new ArrayList<JTextField>();
    Carreras carreras = new Carreras();
    Writer writer = new FileWriter("./txt/datosCarrera.txt",true);

    public AgregarCarrera(JTextField inCodigo,JTextField inNombre,JTextField inJornada,JTextField inSede) throws IOException {
        this.datos.add(inCodigo);
        this.datos.add(inNombre);
        this.datos.add(inJornada);
        this.datos.add(inSede);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         carreras.setCodigoCarrera(datos.get(0).getText());
         carreras.setNombreCarrera(datos.get(1).getText());
         carreras.setJornada(datos.get(2).getText());
         carreras.setNombreSede(datos.get(3).getText());

        try {
            writer.write(carreras.getCodigoCarrera() + ";" + carreras.getNombreCarrera() +";" +carreras.getJornada() + ";" + carreras.getNombreSede() + "\n");
            writer.flush();
            for (int i = 0 ; i < datos.size(); i++){
                datos.get(i).setText("");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
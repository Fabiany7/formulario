import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ViewSemestres extends JFrame{


    public ViewSemestres() throws IOException {
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 100);
        setTitle("Semestre");
        PanelMenuSemestre panel = new PanelMenuSemestre();
        add(panel);
    }

}
class PanelMenuSemestre extends JPanel implements ActionListener, ChangeListener {

    Reader reader = new FileReader("./txt/datosCiclos.txt");
    BufferedReader br = new BufferedReader(reader);
    String linea;
    String select = "tecnico";
    JLabel txtTitulo;
    JButton boton1;
    JButton boton2;
    JLabel txtCodigo;
    JTextField inCodigo;
    JLabel txtNombre;
    JTextField inNombre;
    JLabel txtCiclo;
    JComboBox inCiclo;
    DefaultTableModel tableModel;

    public PanelMenuSemestre() throws IOException {
        setLayout(null);
        txtTitulo = new JLabel("CREAR SEMESTRE");
        txtTitulo.setBounds(250, 10, 150, 30);
        add(txtTitulo);
        txtCodigo = new JLabel("Codigo Semestre");
        txtCodigo.setBounds(60, 55, 150, 30);
        add(txtCodigo);
        inCodigo = new JTextField();
        inCodigo.setBounds(200, 58, 150, 20);
        add(inCodigo);
        txtNombre = new JLabel("Nombre Semestre");
        txtNombre.setBounds(60, 95, 150, 30);
        add(txtNombre);
        inNombre = new JTextField();
        inNombre.setBounds(200, 98, 150, 20);
        add(inNombre);
        txtCiclo = new JLabel("Ciclo");
        txtCiclo.setBounds(60, 140, 150, 30);
        add(txtCiclo);
        inCiclo = new JComboBox();
        inCiclo.setBounds(200, 143, 150, 20);
        while((linea=br.readLine())!=null){
            if(linea.contains(";")){
                inCiclo.addItem(linea.substring(2));

            }
        }
        inCiclo.addActionListener(this);
        add(inCiclo);
        boton1 = new JButton("AGREGAR");
        boton1.setBounds(50, 230, 150, 30);
        boton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton1.addActionListener(new AgregarSemestre(inCodigo,inNombre));
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
        select = (inCiclo.getSelectedItem().toString());
        AgregarSemestre.actualizaState(select);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

}
class AgregarSemestre implements ActionListener {
    ArrayList<JTextField> datos = new ArrayList<JTextField>();
    Ciclos ciclos = new Ciclos();
    Semestre semestre = new Semestre();
    private static String inCiclo;
    Writer writer = new FileWriter("./txt/datosSemestre.txt",true);

    public AgregarSemestre(JTextField inCodigo,JTextField inNombre) throws IOException {
        this.datos.add(inCodigo);
        this.datos.add(inNombre);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.ciclos.setNombreCiclo(inCiclo);
        System.out.println(ciclos.getNombreCiclo());
        semestre.setSemestre(Integer.parseInt(datos.get(0).getText()));
        semestre.setPeriodo(datos.get(1).getText());
        semestre.setCiclos(ciclos);

        try {
            writer.write( semestre.getSemestre()+ ";" +semestre.getPeriodo()+ ";" +semestre.getCiclos().getNombreCiclo()+ "\n" );
            writer.flush();
            datos.get(0).setText("");
            datos.get(1).setText("");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    public static void actualizaState(String inCiclo){
        AgregarSemestre.inCiclo = inCiclo;
    }
}
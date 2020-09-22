import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilitario {


    public List<String> datos(String flat){

        List<String> carreras = new ArrayList<>();
        FileReader FR;
        try {
            if(flat.equals("semestre")){
                FR=new FileReader("./txt/datosSemestre.txt");
            }else{
                FR = new FileReader("./txt/datosCarrera.txt");
            }
            BufferedReader BR = new BufferedReader(FR);
            String dato = "";
            while ((dato = BR.readLine() ) != null ){
                carreras.add(dato);
            }

        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"error al leer semestres " + e.getLocalizedMessage());
        }
        return carreras;
    }
}

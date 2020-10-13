package controlador;

import org.apache.log4j.BasicConfigurator;
import vistas.ViewMenuPrincipal;

public class Formulario {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        ViewMenuPrincipal viewMenuPrincipal = new ViewMenuPrincipal();
        viewMenuPrincipal.setVisible(true);
    }

}

import org.apache.log4j.BasicConfigurator;

public class Formulario {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        ViewMenuPrincipal viewMenuPrincipal = new ViewMenuPrincipal();
        viewMenuPrincipal.setVisible(true);
    }

}

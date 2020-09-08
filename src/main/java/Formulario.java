import java.util.Arrays;
import java.util.List;

public class Formulario {


    public static void main(String[] args) {

        ViewMenuPrincipal viewMenuPrincipal = new ViewMenuPrincipal();
        viewMenuPrincipal.setVisible(true);
        List<String> stooges = Arrays.asList ("Larry", "Moe", "Curly");
        System.out.println("array");
        System.out.println(stooges);
    }

}

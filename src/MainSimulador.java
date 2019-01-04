import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSimulador extends Application
{
    private static Computador miPc;
    private static ContenedorAux datosMiPC;
    private static String file;
    public static void main(String[] args) throws IOException {
        file=args[0];
        //file ="/home/onibushi/Escritorio/Repo Local/tarea-AC-DCd/instrucciones.txt";
        datosMiPC = new ContenedorAux();
        miPc=new Computador(file,datosMiPC);
        Application.launch();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pantalla miPantalla = new Pantalla(datosMiPC,miPc);
    }
}

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSimulador extends Application
{
    private static Computador miPc;
    public static void main(String[] args) throws IOException {
        //String file=args[1];
        String file ="D:/repo git local 2/tarea-AC-DCd/instrucciones.txt";
        miPc=new Computador(file);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Pantalla miPantalla = new Pantalla(miPc);
    }
}

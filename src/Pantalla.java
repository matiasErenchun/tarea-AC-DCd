import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pantalla
{
    private Computador miPC;

    public Pantalla(Computador miPC)
    {
        this.miPC = miPC;
        this.inicio();
    }

    public void inicio()
    {

        BorderPane panelPrincipal= new BorderPane();// creamos el border pane
        Scene pantalla = new Scene(panelPrincipal);// creamos al esena donde  colocaremso el border `pana
        Stage miStage= new Stage();

        HBox contenedorTablas = new HBox();
        VBox contenedorRom = new VBox();// en ente box vertical se agegaran el botn rom y la tabla rom
        VBox contenedorRam =new VBox();
        Button romButton = new Button("-Rom-");
        romButton.setDisable(true);
        Button ramButton = new Button("-Ram-");
        ramButton.setDisable(true);
        HBox.setHgrow(contenedorTablas, Priority.ALWAYS);// se define la prioridad de llenado de espacio que tiene el nodo  dentro de su contenedor.


        contenedorRom.getChildren().addAll(romButton);
        contenedorRam.getChildren().addAll(ramButton);
        contenedorTablas.getChildren().addAll(contenedorRom,contenedorRam);
        panelPrincipal.setCenter(contenedorTablas);

        miStage.setScene(pantalla);//agregamos scene a la pantalla
        miStage.show();//mostramos la pantalla
    }

}

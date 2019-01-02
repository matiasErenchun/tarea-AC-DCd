import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jdk.nashorn.internal.objects.annotations.Property;



public class Pantalla
{
    private ContenedorAux miPC;
    private Computador elPC;
    private TableView tablaRom;
    private TableView tablaRam;
    private TextField valorRegistroA;
    private TextField valorRegistroD;
    private TextField valorPC;
    private Stage miStage;

    public Pantalla(ContenedorAux miPC, Computador elPC)
    {
        this.elPC = elPC;
        this.tablaRam = new TableView();
        this.tablaRom= new TableView();
        this.miPC = miPC;
        this.cargarTablaRom();
        this.cargarTablaRam();
        this.miStage= new Stage();
        this.inicio();
    }

    public void inicio()
    {

        BorderPane panelPrincipal= new BorderPane();// creamos el border pane
        Scene pantalla = new Scene(panelPrincipal);// creamos al esena donde  colocaremso el border `pana


        HBox contenedorTablas = new HBox();
        VBox contenedorRom = new VBox();// en ente box vertical se agegaran el botn rom y la tabla rom
        VBox contenedorRam =new VBox();
        Button romButton = new Button("-Rom-");
        romButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(romButton,Priority.ALWAYS);//le damos prioridad al boton para que cresca

        ScrollPane scrollRom =new ScrollPane();//scroll de la tabla Rom

        scrollRom.setContent(this.tablaRom);
        Button ramButton = new Button("-Ram-");
        ramButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(ramButton,Priority.ALWAYS);//le damos prioridad al boton para que cresca

        ScrollPane scrollRam = new ScrollPane();

        scrollRam.setContent(this.tablaRam);// agregamos la tabla al scroll panel correspondiente
        HBox.setHgrow(contenedorTablas, Priority.ALWAYS);// se define la prioridad de llenado de espacio que tiene el nodo  dentro de su contenedor.

        contenedorTablas.setAlignment(Pos.CENTER);//metodo que determina el alineamiento de los elementos
        contenedorRom.getChildren().addAll(romButton, scrollRom);//agregamos los elementos de rom al conenedor rom
        contenedorRam.getChildren().addAll(ramButton, scrollRam);// agregamos los elementos de ram al conenedor ram


        Button registroA = new Button();
        registroA.setText("---A---");
        registroA.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(registroA,Priority.ALWAYS);

        Button registroD = new Button();
        registroD.setText("---D---");
        registroD.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(registroD,Priority.ALWAYS);

        Button pC = new Button();
        pC.setText("---PC---");
        pC.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(pC,Priority.ALWAYS);

        this.valorRegistroA = new TextField();
        this.valorRegistroA.setText(this.miPC.getA());
        this.valorRegistroA.setEditable(false);

        this.valorRegistroD=new TextField();
        this.valorRegistroD.setText(this.miPC.getD());
        this.valorRegistroD.setEditable(false);

        this.valorPC = new TextField();
        this.valorPC.setText(this.miPC.getStringPC());
        this.valorPC.setEditable(false);

        Button unPaso = new Button("siguiente Paso");
        unPaso.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(unPaso,Priority.ALWAYS);

        Button todo = new Button("correr todo");
        todo.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(todo,Priority.ALWAYS);

        Button espacioEvitarErrores = new Button();
        espacioEvitarErrores.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(espacioEvitarErrores,Priority.ALWAYS);
        espacioEvitarErrores.setDisable(true);


        unPaso.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if(elPC.getPC()>elPC.getRomSize()-1)
                {

                }
                else
                {
                    elPC.ejecutarInstruccion();
                    refresh();
                }


            }
        });

        todo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if(elPC.getPC()>0 )
                {
                    elPC.resetPC();
                    miPC.setPC(0);
                    elPC.resetAll();
                    miPC.resetAll();
                    refresh();
                    elPC.ejecutarPrograma();
                    refresh();


                }
                else
                {
                    if(elPC.getPC()==0)
                    {
                        elPC.ejecutarPrograma();
                        refresh();
                    }
                }

            }
        });






        VBox contenedorRegistros = new VBox();
        contenedorRegistros.setAlignment(Pos.TOP_CENTER);
        contenedorRegistros.getChildren().addAll(registroA, this.valorRegistroA,registroD,this.valorRegistroD,pC,this.valorPC,unPaso,espacioEvitarErrores,todo);
        contenedorTablas.getChildren().addAll(contenedorRom,contenedorRam,contenedorRegistros);//agregamos los contenedores de rom y ram al contenedor de tablas

        panelPrincipal.setCenter(contenedorTablas);//agregamos  el contenedor de tablas al centro de nuestro panel.

        miStage.setScene(pantalla);//agregamos scene a la pantalla
        miStage.show();//mostramos la pantalla
    }

    public void cargarTablaRom()
    {
        ContenedorLinea [] stringRom;
        stringRom =this.elPC.getRom();
        TableColumn<Integer,String>linea = new TableColumn<>("Nº de Linea");
        TableColumn<String,String>instruccion = new TableColumn<>("Instruccion");
        linea.setCellValueFactory(new PropertyValueFactory<>("indice"));
        instruccion.setCellValueFactory(new PropertyValueFactory<>("instruccion"));
        for (ContenedorLinea s: stringRom)
        {
            tablaRom.getItems().add(s);
        }

        tablaRom.getColumns().addAll(linea,instruccion);

    }

    public void cargarTablaRam()
    {

        ContenedorLinea [] stringRam;
        stringRam =this.miPC.getRam();
        TableColumn<Integer,String>linea = new TableColumn<>("Nº Bloque");
        TableColumn<String,String>instruccion = new TableColumn<>("Contenido Memoria");
        linea.setCellValueFactory(new PropertyValueFactory<>("indice"));
        instruccion.setCellValueFactory(new PropertyValueFactory<>("instruccion"));
        tablaRam.getItems().removeAll(linea,instruccion);
        for (ContenedorLinea s: stringRam)
        {
            tablaRam.getItems().add(s);
        }

         tablaRam.getColumns().addAll(linea,instruccion);

    }

    public void refrechTable()
    {
        ContenedorLinea [] stringRam;
        stringRam =this.miPC.getRam();
        int i=0;
        for (ContenedorLinea s: stringRam)
        {
            tablaRam.getItems().set(i,s);
            i++;
        }
    }


    public void refresh()
    {
        this.tablaRam.setEditable(true);
        this.valorRegistroA.setText(this.miPC.getA());
        this.valorRegistroD.setText(this.miPC.getD());
        this.valorPC.setText(this.miPC.getStringPC());
        this.refrechTable();
    }


}

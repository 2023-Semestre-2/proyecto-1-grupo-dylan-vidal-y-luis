package controllers;

//Módulo de importaciones.
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * Definición del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class registroProductosController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private Button botBuscar;
    @FXML
    private Button botAgregar;
    @FXML
    private Button botModificar;
    @FXML
    private Button botRegresar;

    /**
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método para mostrar la ventana para buscar un producto.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionBuscarProducto(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "ventanaBuscarProducto");
    }

    /**
     * Método para mostrar la ventana para agregar un producto.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionAgregarProducto(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "agregarProducto");
    }


    /**
     * Método para mostrar la ventana para modificar un producto.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionModificarProducto(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "ventanaModificarProducto");
    }

    /**
     * Método para mostrar la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "ventanaPrincipal");
    }
}

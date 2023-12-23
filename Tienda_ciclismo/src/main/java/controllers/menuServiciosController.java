package controllers;

//Módulo de importaciones.
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * Definición del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class menuServiciosController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botRegresar;

    /**
     * Inicializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * *
     * Método para mostrar la ventana de buscar servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionBuscarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "VentanaBuscarServicio");
    }

    /**
     * *
     * Método para mostrar la ventana de agregar un servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionAgregarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "ventanaAgregarServicio");
    }


    /**
     * *
     * Método para mostrar la ventana para modificar un servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void opcionModificarServicio(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "ventanaModificarServicio");
    }

    /**
     * *
     * Método para mostrar la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "ventanaPrincipal");
    }

}

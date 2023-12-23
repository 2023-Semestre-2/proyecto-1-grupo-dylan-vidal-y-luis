package controllers;

//Módulo de importaciones
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import login.App;
import static controllers.RegistroArticulosController.esNumerico;

/**
 * Controlador de la ventana
 *
 * @author Vidal Flores
 */
public class AgregarProductoController implements Initializable {

    //Definicion de variables
    @FXML
    private Button botAgregar;
    @FXML
    private TextField textProducto;
    @FXML
    private Button botRegresar;
    @FXML
    private MenuBar menuArticulo;
    @FXML
    private Menu opcionNewArticulo;

    private ArrayList listaProductos;

    /**
     * Inicializador del controlador.
     */
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * *
     * Método para mostrar la ventana para agregar artículos.
     *
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void mostrarVentanaAgregar() throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "registroArticulos");

    }

    /**
     * *
     * Método para poder salir de la ventana actual.
     *
     * @param event Evento que acciona el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "registroProductos");
    }

    /**
     * *
     * Método para agregar un producto
     */
    @FXML
    private void nuevoProducto() {
        if (!esNumerico(textProducto.getText()) && !textProducto.getText().isEmpty()) {
            String info = textProducto.getText().strip();
            int codigo = App.cantProductos() + 1;
            tipoProducto newObjeto = new tipoProducto(codigo, info);
            App.guardarProducto(newObjeto);
            textProducto.setText(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuevo producto agregado.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar solamente texto para el nuevo producto.");
            alert.show();
        }
    }

}

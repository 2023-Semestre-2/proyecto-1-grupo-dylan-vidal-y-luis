package controllers;

//Módulo de importaciones.
import clases.Cliente;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * Inicializador del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class VentanaAgregarServicioController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private Button botAgregar;
    @FXML
    private Button botRegresar;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private DatePicker dateRecibido;
    @FXML
    private TextField textMarca;
    @FXML
    private DatePicker dateEntrega;
    @FXML
    private TextField textPrecio;
    @FXML
    private Text textEstado;
    @FXML
    private TextArea textObservaciones;

    private ArrayList<String> Nombresclientes;
    private ArrayList<Cliente> clientes;
    @FXML
    private ComboBox<String> codClientes;
    @FXML
    private Button botLimpiar;

    /**
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textEstado.setText("Abierto");
        Nombresclientes = formatoClientes(App.devolverClientes());
        clientes = App.devolverClientes();
        codClientes.getItems().addAll(Nombresclientes);

    }

    /**
     * *
     * Método para devolver a la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "menuServicios");
    }


    /**
     * *
     * Método para cambiar el formato del ArrayList de los clientes para
     * mostrarlo al usuario.
     *
     * @param lista ArrayList con los objetos "Cliente".
     * @return ArrayList con el formato para mostrar al usuario.
     */
    private ArrayList formatoClientes(ArrayList<Cliente> lista) {
        ArrayList<String> info = new ArrayList();
        for (Cliente cliente : lista) {
            info.add(cliente.getNombre());
        }
        return info;

    }

    /**
     * *
     * Método para validar las fechas ingresadas por el usuario, que la de
     * ingreso sea una fecha anterior a la de entrega.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void validarFecha(ActionEvent event) {
        try {
            LocalDate ingreso = dateRecibido.getValue();
            LocalDate entrega = dateEntrega.getValue();
            if (ingreso != null && entrega != null) {
                if (ingreso.isBefore(entrega)) {
                } else {
                    dateRecibido.setValue(null);
                    dateEntrega.setValue(null);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "La fecha de entrega debe ser posterior a la fecha de recibido.");
                    alert.show();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    /**
     * *
     * Método para agregar un nuevo servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void agregar(ActionEvent event) {
        if (codClientes.getValue() != null) {
            int codigoCliente = buscarCliente(String.valueOf(codClientes.getValue()));
            String marca = textMarca.getText().strip();
            String descripcion = textDescripcion.getText().strip();
            String observaciones = textObservaciones.getText().strip();
            LocalDate ingreso = dateRecibido.getValue();
            LocalDate entrega = dateEntrega.getValue();
            if (validarTexto(marca) && validarTexto(descripcion) && validarTexto(observaciones)) {
                if (validarPrecio(textPrecio.getText())) {
                    int precio = Integer.parseInt(textPrecio.getText());
                    int codigo = App.cantServicios() + 1;
                    servicio newServicio = new servicio(codigo, codigoCliente, marca, descripcion, precio, ingreso, entrega, observaciones, true);
                    App.guardarServicio(newServicio);
                    limpiar();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El servicio ha sido registrado satisfactoriamente.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Falta información de la bicicleta.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar al cliente.");
            alert.show();
        }

    }

    /**
     * Método para válidar que el precio ingresado por el usuario sea
     * exclusivamente númerico
     *
     * @param precio String ingresado por el usuario.
     * @return Booleano de si es válido o no la información es correcta.
     */
    private boolean validarPrecio(String precio) {
        if (!esNumerico(precio)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar solamente números enteros para el precio.");
            alert.show();
            return false;
        }
        return true;
    }

    /**
     * *
     * Método para válidar el texto ingresado por el usuario.
     *
     * @param texto String ingresado por el usuario.
     * @return Booleano de si es válido o no la información.
     */
    private boolean validarTexto(String texto) {
        if (texto != null) {
            return true;
        }
        return false;
    }

    /**
     * *
     * Método para buscar un cliente en específico en el ArrayList
     *
     * @param nombre Nombre del cliente
     * @return objeto cliente requerido.
     */
    private int buscarCliente(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre)) {
                return cliente.getCodigo();
            }
        }
        return -1;
    }

    /**
     * *
     * Método para borrar la información ingresada por el usuario sin guardarla.
     *
     */
    @FXML
    private void limpiar() {
        textDescripcion.setText(null);
        dateRecibido.setValue(null);
        textMarca.setText(null);
        dateEntrega.setValue(null);
        textPrecio.setText(null);
        textEstado.setText(null);
        textObservaciones.setText(null);
        codClientes.setValue(null);
    }
}

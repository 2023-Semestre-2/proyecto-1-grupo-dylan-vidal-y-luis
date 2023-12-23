package controllers;

//Módulo de importaciones
import clases.Cliente;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.App;

/**
 * Controlador para la ventana de agregar cliente.
 *
 * @author Dylan Meza
 */
public class AgregarClienteController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private Button btAgregar;
    @FXML
    private TextField entryNombre;
    @FXML
    private TextField entryApellido;
    @FXML
    private TextField entryTelefono;
    @FXML
    private TextField entryCorreo;
    @FXML
    private TextField entryDistrito;
    @FXML
    private TextField entryCanton;
    @FXML
    private ComboBox<String> comboProvincia;
    @FXML
    private DatePicker fechaNacimiento;

    /**
     * Inicializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboProvincia.getItems().addAll("San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón");
        comboProvincia.setValue("San José");
        fechaNacimiento.setValue(LocalDate.now());

    }

    /**
     * *
     * Método para borrar toda la informacíon ingresa por el usuario sin
     * guardarla.
     *
     * @param event Evento que acciona el método.
     */
    @FXML
    private void limpiarVentana(ActionEvent event) {
        entryNombre.clear();
        entryApellido.clear();
        entryTelefono.clear();
        entryCorreo.clear();
        entryDistrito.clear();
        entryCanton.clear();
        comboProvincia.setValue("San José");
        fechaNacimiento.setValue(LocalDate.now());
    }

    /**
     * *
     * Método para obtener la información ingresada por el usuario y registrar
     * la cliente
     *
     * @param event Evento para accionar cuando se presione el botón
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     *
     */
    @FXML
    private void agregarCliente(ActionEvent event) throws IOException {
        
        String nombre = entryNombre.getText();
        String telefono = entryTelefono.getText();
        String apellido = entryApellido.getText();
        String correo = entryCorreo.getText();
        String provincia = comboProvincia.getValue();
        String canton = entryCanton.getText();
        String distrito = entryDistrito.getText();

        List<Cliente> clientes = App.getClientes();
        int contar = App.buscarCodigoDisponible((ArrayList) clientes);

        //validar que la información este de forma correcta para crear el objeto.
        if (nombre != null && !nombre.isEmpty() && apellido != null && !apellido.isEmpty() && telefono != null && !telefono.isEmpty() && correo != null && !correo.isEmpty() && distrito != null && !distrito.isEmpty() && canton != null && !canton.isEmpty()) {
            if (validar_telefono(telefono)) {
                if (validarCorreo(correo)) {

                    Cliente cliente = new Cliente(contar + 1, nombre.strip(), apellido.strip(), telefono.strip(), correo.strip(), provincia.strip(), canton.strip(), distrito.strip(), getFecha());
                    App.guardarCliente(cliente);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se ha registrado correctamente el cliente");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El formato del correo eletrónico ingresado es incorrecto.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El número ingresado no es invalido.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Falta información para poder ingresar al sistema.");
            alert.show();
        }
    }

    /**
     * *
     * Método para poder regresar a la ventana anterior a la actual
     *
     * @param event Evento para ejecutar cuando se accione el botón
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(btAgregar), "registroCliente");
    }

    /**
     * *
     * Método para validar el formato del número de teléfono
     *
     * @param telefono Número de teléfono a validar
     * @return Booleano si el formato es correcto o no.
     */
    private boolean validar_telefono(String telefono) {
        if (telefono.length() == 8 && telefono.matches("[2|4|6|8]\\d+")) {
            return true;
        }
        return false;
    }

    /**
     * *
     * Método para obtener el objeto localDate de la fecha
     *
     * @return La fecha de nacimiento del cliente.
     */
    private LocalDate getFecha() {
        LocalDate fecha = fechaNacimiento.getValue();
        return fecha;
    }

    public static boolean validarCorreo(String correo) {
        if (correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            return true;
        } else {
            return false;
        }

    }
}

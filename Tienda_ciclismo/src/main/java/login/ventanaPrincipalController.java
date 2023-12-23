package login;

//Módulo de importaciones
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import login.App;

/**
 * El controlador de la ventana principal proporciona métodos para manejar eventos y acciones en la interfaz de usuario.
 * Controla la navegación entre ventanas y las acciones asociadas a los botones.
 * 
 * @author Vidal Flores
 */
public class ventanaPrincipalController {

    @FXML
    private Button botClientes;
    @FXML
    private Button botFacturacion;
    @FXML
    private Button botServicios;
    @FXML
    private Button botSalir;
    @FXML
    private Button botProductos;
    
    /**
     * Maneja el evento de clic en el botón "Salir" para cambiar a la ventana de inicio de sesión.
     *
     * @throws IOException Si hay un error durante la carga de la ventana.
     */
    @FXML
    private void salir() throws IOException{
        App.cambiarVentana(getStage(), "ventanalogin");
    }
    
    /**
     * Maneja el evento de clic en el botón "Registrar Productos" para cambiar a la ventana de registro de productos.
     *
     * @param event El evento de clic.
     * @throws IOException Si hay un error durante la carga de la ventana.
     */
    @FXML
    private void opcionRegistrarProductos(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(), "registroProductos");
    }

    /**
     * Maneja el evento de clic en el botón "Registrar Clientes" para cambiar a la ventana de registro de clientes.
     *
     * @param event El evento de clic.
     * @throws IOException Si hay un error durante la carga de la ventana.
     */
    @FXML
    private void opcionRegistrarClientes(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(), "registroCliente");
    }

    /**
     * Maneja el evento de clic en el botón "Realizar Factura".
     *
     * @param event El evento de clic.
     */
    @FXML
    private void opcionRealizarFactura(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(),"registroFactura");
    }

    /**
     * Maneja el evento de clic en el botón "Registrar Servicios".
     *
     * @param event El evento de clic.
     */
    @FXML
    private void opcionRegistrarServicios(ActionEvent event) throws IOException {
        App.cambiarVentana(getStage(), "menuServicios");
    }
    
    /**
     * Obtiene la referencia a la ventana actual (Stage).
     *
     * @return La instancia de Stage correspondiente a la ventana actual.
     */
    private Stage getStage(){
        Stage stage = (Stage) botProductos.getScene().getWindow();
        return stage;
    }
}
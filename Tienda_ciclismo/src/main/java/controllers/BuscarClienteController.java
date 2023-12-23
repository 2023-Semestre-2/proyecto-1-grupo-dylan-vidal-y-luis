package controllers;

//Módulo de importaciones
import clases.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import login.App;

/**
 * Contradolar de la ventana donde se busca clientes.
 *
 * @author Dylan Meza
 */
public class BuscarClienteController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar
    @FXML
    private Label nombre;
    @FXML
    private Label apellido;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private Label provincia;
    @FXML
    private Label canton;
    @FXML
    private Label distrito;
    @FXML
    private Label fechaN;
    @FXML
    private Button btBuscar;
    @FXML
    private TextField obtenerDato;
    @FXML
    private Button botEliminar;
    @FXML
    private Button botLimpiar;
    
    private Cliente clienteSeleccionado;

    /**
     * Inicizalidor del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * *
     * Método para poder salir de la ventana actual.
     *
     * @param event Evento para ejecutar el método cuando se accione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(btBuscar), "registroCliente");
    }

    /**
     * *
     * Método para mostrar la información del cliente seleccionado.
     *
     * @param event Evento para ejecutar el método cuando se accione el botón.
     */
    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        String dato = obtenerDato.getText();
        boolean existe = false;

        if (dato != null && !dato.isEmpty()) {
            List<Cliente> clientes = App.getClientes();
            for (Cliente cliente : clientes) {
                try {
                    if (cliente.getCodigo() == Integer.parseInt(dato.strip())) {
                        nombre.setText("Nombre: " + cliente.getNombre());
                        apellido.setText("Apellido: " + cliente.getApellido());
                        telefono.setText("Telefono: " + cliente.getTelefono());
                        correo.setText("Correo electronico: " + cliente.getCorreo());
                        provincia.setText("Provincia: " + cliente.getProvincia());
                        canton.setText("Cantón: " + cliente.getCanton());
                        distrito.setText("Distrito: " + cliente.getDistrito());
                        fechaN.setText("Fecha de nacimiento: " + cliente.getFechaNacimiento());
                        existe = true;
                        botEliminar.setDisable(false);
                        clienteSeleccionado = cliente;
                    }
                } catch (NumberFormatException e) {
                    if (cliente.getNombre().equals(dato.strip())) {
                        nombre.setText("Nombre: " + cliente.getNombre());
                        apellido.setText("Apellido: " + cliente.getApellido());
                        telefono.setText("Telefono: " + cliente.getTelefono());
                        correo.setText("Correo electronico: " + cliente.getCorreo());
                        provincia.setText("Provincia: " + cliente.getProvincia());
                        canton.setText("Cantón: " + cliente.getCanton());
                        distrito.setText("Distrito: " + cliente.getDistrito());
                        fechaN.setText("Fecha de nacimiento: " + cliente.getFechaNacimiento());
                        existe = true;
                        botEliminar.setDisable(false);
                        clienteSeleccionado = cliente;
                    }
                }
            }
            if (!existe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente no encontrado.");
                alert.show();
            }

        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        ArrayList codigos = App.getArticulosCodFacturados();
        if (!codigos.contains(clienteSeleccionado.getCodigo())) {
            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("¿Estás seguro de eliminar el cliente?");

            alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    App.eliminarCliente(clienteSeleccionado.getCodigo());
                    limpiar(event);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "El cliente se eliminó satisfactoriamente.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No se eliminó al cliente.");
                    alert.show();
                }
            });

            /*App.eliminarCliente(clienteSeleccionado.getCodigo());
            limpiar(event);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "El cliente se eliminó satisfocriamente.");
            alert.show();*/
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "El cliente no se puede eliminar, porque ya está facturado.");
            alert.show();
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        nombre.setText("Nombre: ");
        apellido.setText("Apellido: ");
        telefono.setText("Telefono: ");
        correo.setText("Correo electronico: ");
        provincia.setText("Provincia: ");
        canton.setText("Cantón: ");
        distrito.setText("Distrito: ");
        fechaN.setText("Fecha de nacimiento: ");
        botEliminar.setDisable(false);
        clienteSeleccionado = null;
        obtenerDato.clear();

    }


}

package controllers;

//Módulo de importaciones.
import clases.Cliente;
import clases.articulo;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarProductoController.filtrarPorCodigo;
import static controllers.VentanaBuscarProductoController.filtrarPorNombre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import login.App;

/**
 * Definición del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class VentanaBuscarServicioController implements Initializable {

    //Definición de las variables y elementos gráficos a utilizar.
    @FXML
    private Button botRegresar;
    @FXML
    private GridPane gridInformacion;

    private ArrayList clientes;
    @FXML
    private MenuItem busqCodCliente;
    @FXML
    private MenuItem busqNombCliente;
    @FXML
    private MenuItem botEliminar;
    @FXML
    private Menu menuEliminar;

    /**
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientes = App.devolverClientes();
    }

    /**
     * *
     * Método para realizar la búsqueda por el código del cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void busquedaCodigoCliente(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda de servicios por código de cliente.");
        dialog.setHeaderText("Ingrese el código del cliente:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(codigoCliente -> {
            if (esNumerico(codigoCliente)) {
                ArrayList servicios = App.getServicios();

                ArrayList elementosMostrar = filtrarPorCodigo(servicios, Integer.parseInt(codigoCliente));
                gridInformacion.getChildren().clear();
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                    menuEliminar.setDisable(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado productos con el valor ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar solamente números enteros.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para realizar la búsqueda por el nombre del cliente.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void busquedaNombreCliente(ActionEvent event) {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por nombre del cliente.");
        dialog.setHeaderText("Ingrese el nombre del cliente:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nombreCliente -> {
            if (!nombreCliente.isEmpty()) {
                ArrayList servicios = App.getServicios();

                ArrayList elementosMostrar = filtrarPorNombre(servicios, nombreCliente.strip(), clientes);
                if (!elementosMostrar.isEmpty()) {
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                    menuEliminar.setDisable(false);
                } else {
                    //listViewElementos.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado servicios asociados al cliente ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes el nombre del producto a buscar.");
                alert.show();
            }
        }
        );
    }

    /**
     * *
     * Método para poder volver a la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "menuServicios");
    }

    /**
     * *
     * Método para colocar los titulos del gridPane
     *
     */
    private void colocarLabels() {
        ArrayList etiquetas = new ArrayList();
        etiquetas.add("Código");
        etiquetas.add("Marca de bicicleta");
        etiquetas.add("Descripción de bicicleta");
        etiquetas.add("Precio");
        etiquetas.add("Fecha de recibido");
        etiquetas.add("Fecha de entrega");
        etiquetas.add("Observaciones");
        etiquetas.add("Estado");

        RowConstraints rowConstraints = new RowConstraints();
        gridInformacion.getRowConstraints().add(0, rowConstraints);

        for (int i = 0; i < 8; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            gridInformacion.getChildren().add(newLabel);
        }
    }

    /**
     * *
     * Método para mostrar la información del servicio seleccionado.
     *
     * @param elementos ArraList con la información a mostrar.
     */
    private void colocarInformacion(ArrayList elementos) {
        int fila = 1;
        int columna = 0;
        int contador = 0;

        for (int i = 0; i < elementos.size(); i++) {
            if (columna == 8) {
                columna = 0;
                contador = 0;
                fila++;
            }
            Node elementoNode;
            if (contador == 2 || contador == 6) {

                TextArea textArea = new TextArea(elementos.get(i).toString());
                textArea.setWrapText(true);
                textArea.setEditable(false);
                elementoNode = textArea;
            } else if (contador == 7) {

                if (elementos.get(i).equals("true")) {
                    elementoNode = new Label("Abierto");
                } else {
                    elementoNode = new Label("Cerrado");
                }
            } else {
                elementoNode = new Label(elementos.get(i).toString());
            }

            GridPane.setHalignment(elementoNode, HPos.CENTER);
            GridPane.setValignment(elementoNode, VPos.CENTER);
            GridPane.setRowIndex(elementoNode, fila);
            GridPane.setColumnIndex(elementoNode, columna);

            gridInformacion.getChildren().add(elementoNode);
            contador++;
            columna++;
        }

    }

    /**
     * *
     * Método para filtrar el ArrayList de los objetos
     *
     * @param servicios ArrayList con los objetos servicio
     * @param filtro código del servicio a buscar.
     * @return ArrayList con la información requerida.
     */
    public static ArrayList filtrarPorCodigo(ArrayList<servicio> servicios, int filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (servicio servicio : servicios) {
            if (servicio.getCodigoCliente() == filtro) {
                ArrayList<String> listaInfo = servicio.mostrarTodo();
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(1, listaInfo.size()));
                elementos.addAll(subLista);
            }
        }
        return elementos;

    }

    /**
     * *
     * Método para filtrar el ArrayLisr de los objetos.
     *
     * @param servicios ArrayList con los objetos servicio.
     * @param filtro nombre del cliente a quien se le realizó el servicio.
     * @param clientes ArrayList con los objetos cliente
     * @return ArrayList con la información requerida.
     */
    public static ArrayList filtrarPorNombre(ArrayList<servicio> servicios, String filtro, ArrayList<Cliente> clientes) {
        ArrayList<String> elementos = new ArrayList<>();
        int codigo = buscarCodigoCliente(clientes, filtro);
        return filtrarPorCodigo(servicios, codigo);

    }

    /**
     * *
     * Método para buscar por el código del cliente.
     *
     * @param clientes ArrayList con los objetos cliente.
     * @param filtro Código del cliente a buscar el servicio.
     * @return código del cliente requerido.
     */
    private static int buscarCodigoCliente(ArrayList<Cliente> clientes, String filtro) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(filtro)) {
                return cliente.getCodigo();
            }
        }
        return -1;
    }

    @FXML
    private void eliminarServicio(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar servicio con el código.");
        dialog.setHeaderText("Ingrese el código del servicio a eliminar:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            ArrayList<servicio> listaServicios = App.getServicios();
            ArrayList facturados = App.getCodigosServiciosFacturados();
            if (esNumerico(input) && input != null) {
                for (servicio elementoServicio : listaServicios) {
                    if (elementoServicio.getCodigoServicio() == Integer.parseInt(input)) {
                        if (!elementoServicio.isEstado()) {
                            Alert alerta = new Alert(AlertType.CONFIRMATION);
                            alerta.setTitle("Confirmación");
                            alerta.setHeaderText("¿Está seguro de eliminar el servicio?");

                            alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                            alerta.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.YES) {
                                    App.eliminarServicio(Integer.parseInt(input));
                                    gridInformacion.getChildren().clear();
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "El servicio se ha eliminado satisfactoriamente.");
                                    alert.show();
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.WARNING, "No se eliminó al servicio.");
                                    alert.show();
                                }
                            });
                        }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "El servicio no se puede eliminar, porque ya está facturado.");
                        alert.show();
                        break;
                        } 
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar carácteres válidos.");
                alert.show();
            }
        });
    }
}

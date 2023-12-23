package controllers;

//Módulo de importaciones
import clases.Cliente;
import clases.articulo;
import clases.servicio;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarServicioController.filtrarPorCodigo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.App;

/**
 * Definición del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class VentanaModificarServicioController implements Initializable {

    //Definción de variables y elementos gráficos a utilizar.
    @FXML
    private Button botRegresar;
    @FXML
    private MenuItem botMarca;
    @FXML
    private MenuItem botDescripcion;
    @FXML
    private MenuItem botPrecio;
    @FXML
    private MenuItem botRecibido;
    @FXML
    private MenuItem botEntrega;
    @FXML
    private MenuItem botObservaciones;
    @FXML
    private Text textCodigoServicio;
    @FXML
    private Text textMarca;
    @FXML
    private Text textPrecio;
    @FXML
    private Text textFechaRecibido;
    @FXML
    private Text textFechaEntrega;
    @FXML
    private Text textEstado;
    @FXML
    private Button botLimpiar;
    @FXML
    private Button mostrar;
    @FXML
    private TextArea textDescripcion;
    @FXML
    private TextArea textObservaciones;

    private servicio objetoModificar;

    private ArrayList<String> serviciosString;
    private ArrayList<servicio> servicios;
    @FXML
    private MenuBar menuOpciones;

    /**
     * Inicializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviciosString = formatoClientes(App.devolverClientes());
        servicios = App.getServicios();
    }

    /**
     * *
     * Método para regresar a la ventana anterior.
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
     * Método para cambiar la marca del servicio
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarMarca(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de marca del servicio.");
        dialog.setHeaderText("Ingrese la nueva marca del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null && !input.isEmpty()) {
                objetoModificar.setMarcaBici(input);
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La marca ha sido modificada exitosamente.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar una marca válida.");
                alert.show();
            }

        });
    }

    /**
     * *
     * Método para cambiar la descripción del servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarDescripcion(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de descripción de la bicicleta.");
        dialog.setHeaderText("Ingrese la nueva descripción de la bicicleta:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null && !input.isEmpty()) {
                objetoModificar.setDescripcion(input.strip());
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La descripción ha sido modificada exitosamente.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar una descripción válida.");
                alert.show();
            }

        });
    }

    /**
     * *
     * Método para cambiar el precio del servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarPrecio(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cambio de precio del servicio.");
        dialog.setHeaderText("Ingrese el nuevo precio del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null && esNumerico(input)) {
                objetoModificar.setPrecio(Integer.parseInt(input));
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El precio ha sido modificado exitosamente.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un precio válido.");
                alert.show();
            }

        });
    }

    /**
     * *
     * Método para cambiar la fecha de recibido del servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarFechaRecibido(ActionEvent event) {
        
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        Button aceptar = new Button("ACEPTAR");
        aceptar.setOnAction(e -> {
            LocalDate fechaNueva = datePicker.getValue();
            if (fechaNueva != null) {
                if (fechaNueva.isBefore(objetoModificar.getFechaEntrega())) {
                    objetoModificar.setFechaRecibido(fechaNueva);
                    mostrarLabel(objetoModificar);
                    dialogStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar una fecha anterior a la fecha de entrega.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar la nueva fecha");
                alert.show();
            }
        });

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(10));
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().addAll(datePicker, aceptar);

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    /**
     * *
     * Método para cambiar la fecha de entrega del servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarFechaEntrega(ActionEvent event) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        Button aceptar = new Button("ACEPTAR");
        aceptar.setOnAction(e -> {
            LocalDate fechaNueva = datePicker.getValue();
            if (fechaNueva != null) {
                if (fechaNueva.isAfter(objetoModificar.getFechaRecibido())) {
                    objetoModificar.setFechaEntrega(fechaNueva);
                    mostrarLabel(objetoModificar);
                    dialogStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar una fecha posterior a la fecha de recibido.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes selecionar la nueva fecha");
                alert.show();
            }
        });

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(10));
        dialogLayout.setAlignment(Pos.CENTER);
        dialogLayout.getChildren().addAll(datePicker, aceptar);

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    /**
     * *
     * Método para cambiar las observaciones del servicio.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarObservaciones(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevas observaciones para del servicio.");
        dialog.setHeaderText("Ingrese la nueva descripción del servicio:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (input != null && !input.isEmpty()) {
                objetoModificar.setObservaciones(input);
                mostrarLabel(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Las observaciones han sido modificadas exitosamente.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar observaciones válidas.");
                alert.show();
            }

        });
    }

    /**
     * *
     * Método para borrar la informacion mostrada.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void limpiar(ActionEvent event) {
        textCodigoServicio.setText(null);
        textMarca.setText(null);
        textPrecio.setText(null);
        textFechaRecibido.setText(null);
        textFechaEntrega.setText(null);
        textEstado.setText(null);
        textDescripcion.setText(null);
        textObservaciones.setText(null);
        objetoModificar = null;
        menuOpciones.setDisable(true);
       
    }

    /**
     * *
     * Método para mostrar un servicio en específico.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void mostrar(ActionEvent event) {
        ChoiceDialog<String> serviciosDisponibles = new ChoiceDialog<>(" ", serviciosString);
        serviciosDisponibles.setTitle("Servicios disponibles");
        serviciosDisponibles.setHeaderText("Seleccione el servicio a modificar:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> input = serviciosDisponibles.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        input.ifPresent(opcionUser -> {
            if (!opcionUser.equals(" ")) {
                objetoModificar = obtenerServicioEspecifico(servicios, Integer.parseInt(opcionUser.substring(0, 1)));
                mostrarLabel(objetoModificar);
                menuOpciones.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una opción.");
                alert.show();
            }
        });

    }

    /**
     * *
     * Método para obtener un servicio específico
     *
     * @param servicios ArrayList con los objectos servicio
     * @param filtro codigo del servicio a buscar
     * @return servicio en especifico.
     */
    private servicio obtenerServicioEspecifico(ArrayList<servicio> servicios, int filtro) {
        for (servicio servicio : servicios) {
            if (servicio.getCodigoServicio() == filtro) {
                return servicio;
            }
        }
        return null;
    }

    /**
     * *
     * Método para dar formato a los clientes para mostrarlos en el combobox
     *
     * @param clientes ArrayList con los objetos cliente.
     * @return ArrayList con los strings para el comboBox.
     */
    private ArrayList<String> formatoClientes(ArrayList<Cliente> clientes) {
        ArrayList<String> clientesComboBox = new ArrayList();
        for (Cliente cliente : clientes) {
            int codigo = cliente.getCodigo();
            String nombre = cliente.getNombre();
            String etiqueta = String.valueOf(codigo) + " - " + nombre;
            clientesComboBox.add(etiqueta);
        }
        return clientesComboBox;
    }

    /**
     * *
     * Método para completar los labels con la información.
     *
     * @param objetoModificar objeto servicio para extraer la información.
     */
    private void mostrarLabel(servicio objetoModificar) {
        textCodigoServicio.setText(String.valueOf(objetoModificar.getCodigoServicio()));
        textMarca.setText(objetoModificar.getMarcaBici());
        textPrecio.setText(String.valueOf(objetoModificar.getPrecio()));
        textDescripcion.setText(objetoModificar.getDescripcion());
        textFechaRecibido.setText(objetoModificar.getFechaRecibido().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")));
        textFechaEntrega.setText(objetoModificar.getFechaEntrega().format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")));
        if (objetoModificar.isEstado()) {
            textEstado.setText("Abierto");
        } else {
            textEstado.setText("Cerrado");
        }
        textObservaciones.setText(objetoModificar.getObservaciones());
    }
}

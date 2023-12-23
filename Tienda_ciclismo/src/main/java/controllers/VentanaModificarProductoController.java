package controllers;

//Módulo de importaciones
import clases.articulo;
import static controllers.RegistroArticulosController.esNumerico;
import static controllers.VentanaBuscarProductoController.filtrarPorCodigo;
import static controllers.VentanaBuscarProductoController.filtrarPorNombre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * Definción del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class VentanaModificarProductoController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private Button botRegresar;
    @FXML
    private Text textTipoProducto;
    @FXML
    private Text textNombreArticulo;
    @FXML
    private Text textCategoria;
    @FXML
    private Text textTamanno;
    @FXML
    private Text textMarca;
    @FXML
    private Text textPrecio;
    @FXML
    private Text textCant;
    @FXML
    private Button mostrar;
    @FXML
    private Button botLimpiar;

    private articulo objetoModificar;
    @FXML
    private MenuItem botTipoProducto;
    @FXML
    private MenuItem botNombre;
    @FXML
    private MenuItem botCategoria;
    @FXML
    private MenuItem botTamanno;
    @FXML
    private MenuItem botMarca;
    @FXML
    private MenuItem botPrecio;
    @FXML
    private MenuItem botCantidad;

    private List<String> productosDisponibles;
    private String[] categorias = {"Bicicletas", "Suplementos", "Accesorios"};
    private String[] tamannos = {"12", "16", "22", "26", "27", "27.5", "29"};
    @FXML
    private Menu menuModificar;

    /**
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * *
     * Método para poder regresar a la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos.
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botLimpiar), "registroProductos");
    }

    /**
     * *
     * Método para mostrar la información del objeto producto a modificar.
     *
     */
    @FXML
    private void mostrar() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mostrar información a modificar.");
        dialog.setHeaderText("Ingrese el artículo a modificar:");

        Optional<String> result = dialog.showAndWait();

        ArrayList<articulo> articulos = App.devolverArticulos();

        result.ifPresent(codigoProducto -> {
            articulo infoMostrar = obtenerObjetoEspecifico(articulos, codigoProducto);
            if (infoMostrar != null) {
                //objetoModificar = infoMostrar;
                objetoModificar = infoMostrar;
                completarLabels(infoMostrar);
                menuModificar.setDisable(false);
            } else {
                objetoModificar = null;
                Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ingresado no existe.");
                alert.show();
            }

        });

    }

    /**
     * *
     * Método completar los label de la GUI.
     *
     * @param articulo objeto articulo para mostrar la información.
     */
    private void completarLabels(articulo articulo) {
        textTipoProducto.setText(articulo.getNombreProducto());
        textNombreArticulo.setText(articulo.getNombreArticulo());
        textCategoria.setText(articulo.getTipo());
        textTamanno.setText(articulo.getTamanno());
        textMarca.setText(articulo.getMarca());
        textPrecio.setText(String.valueOf(articulo.getPrecio()));
        textCant.setText(String.valueOf(articulo.getCantidad()));

    }

    /**
     * *
     * Método para borrar la información mostrada.
     *
     */
    @FXML
    private void limpiar() {
        textTipoProducto.setText(" ");
        textNombreArticulo.setText(" ");
        textCategoria.setText(" ");
        textTamanno.setText(" ");
        textMarca.setText(" ");
        textPrecio.setText(String.valueOf(" "));
        textCant.setText(String.valueOf(" "));
        objetoModificar = null;
        menuModificar.setDisable(true);

    }

    /**
     * *
     * Método para cambiar el tipo de producto del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarTipoProducto(ActionEvent event) {
        productosDisponibles = App.verProductos();
        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", productosDisponibles);
        dialog.setTitle("Nuevo tipo de producto");
        dialog.setHeaderText("Seleccione el nuevo tipo de producto:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> result = dialog.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        result.ifPresent(opcion -> {
            if (objetoModificar != null && !opcion.equals(" ")) {
                objetoModificar.setNombreProducto(opcion);
                completarLabels(objetoModificar);
                App.actualizarArticulo();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Se cambió exitosamente el tipo de producto.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar alguna opción.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para cambiar el nombre del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarNombre(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo nombre para el artículo.");
        dialog.setHeaderText("Ingrese el nuevo nombre del artículo:");

        Optional<String> result = dialog.showAndWait();

        ArrayList<articulo> articulos = App.devolverArticulos();

        result.ifPresent(input -> {
            articulo infoMostrar = obtenerObjetoEspecifico(articulos, objetoModificar.getNombreArticulo());
            if (infoMostrar != null) {
                objetoModificar.setNombreArticulo(input);
                completarLabels(infoMostrar);
                App.actualizarArticulo();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "El nombre del artículo ha sido modificado satisfactoriamente.");
                alert.show();
            } else {
                objetoModificar = null;
                Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ingresado no existe.");
                alert.show();
            }

        });
    }

    /**
     * *
     * Método para cambiar la categoría del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarCategoria(ActionEvent event) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(" ", categorias);
        dialog.setTitle("Nueva categoría para el artículo");
        dialog.setHeaderText("Seleccione la nueva categoría:");

        // Muestra el diálogo y espera que el usuario haga una elección
        Optional<String> result = dialog.showAndWait();

        // Maneja la opción seleccionada (si el usuario hizo una elección)
        result.ifPresent(opcion -> {
            if (objetoModificar != null && !opcion.equals(" ")) {
                if (opcion.equals("Bicicletas") && objetoModificar.getTipo().equals("Bicicletas")) {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ya tiene esa categoría asignada.");
                    alert.show();
                } else if (opcion.equals("Bicicletas") && !objetoModificar.getTipo().equals("Bicicletas")) {

                    ChoiceDialog<String> tamanno = new ChoiceDialog<>(" ", tamannos);
                    tamanno.setTitle("Tamaños disponibles");
                    tamanno.setHeaderText("Seleccione el tamaño de la categoría:");

                    // Muestra el diálogo y espera que el usuario haga una elección
                    Optional<String> input = tamanno.showAndWait();

                    // Maneja la opción seleccionada (si el usuario hizo una elección)
                    input.ifPresent(opcionUser -> {
                        if (!opcionUser.equals(" ")) {
                            objetoModificar.setTipo(opcion);
                            objetoModificar.setTamanno(opcionUser);
                            App.actualizarArticulo();
                            completarLabels(objetoModificar);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "La categoría y el tamaño han sido modificados exitosamente.");
                            alert.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una opción.");
                            alert.show();
                        }

                    });
                } else if (objetoModificar.getTipo().equals(opcion)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ya tiene esa categoría asignada.");
                    alert.show();
                } else {

                    objetoModificar.setTipo(opcion);
                    objetoModificar.setTamanno("0");
                    App.actualizarArticulo();
                    completarLabels(objetoModificar);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "La categoría ha sido modificada exitosamente.");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar alguna opción.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para cambiar el tamaño del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarTamanno(ActionEvent event) {
        if (objetoModificar.getTipo().equals("Bicicletas")) {
            ChoiceDialog<String> tamanno = new ChoiceDialog<>(" ", tamannos);
            tamanno.setTitle("Tamaños disponibles");
            tamanno.setHeaderText("Seleccione el tamaño de la categoría:");

            // Muestra el diálogo y espera que el usuario haga una elección
            Optional<String> input = tamanno.showAndWait();

            // Maneja la opción seleccionada (si el usuario hizo una elección)
            input.ifPresent(opcionUser -> {
                if (!opcionUser.equals(" ")) {
                    objetoModificar.setTamanno(opcionUser);
                    App.actualizarArticulo();
                    completarLabels(objetoModificar);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "El tamaño del artículo ha sido modificado exitosamente.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar una opción.");
                    alert.show();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "El artículo seleccionado no cuenta con esta característica disponible.");
            alert.show();
        }
    }

    /**
     * *
     * Método para cambiar la marca del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarMarca(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nueva marca  para el artículo..");
        dialog.setHeaderText("Ingrese la nueva marca del artículo:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (!input.isEmpty()) {
                objetoModificar.setMarca(input);
                App.actualizarArticulo();
                completarLabels(objetoModificar);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "La marca del artículo ha sido modificada exitosamente.");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar carácteres válidos.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para cambiar el precio del artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarPrecio(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo precio del artículo..");
        dialog.setHeaderText("Ingrese el nuevo precio del artículo:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (!input.isEmpty()) {
                if (esNumerico(input)) {
                    objetoModificar.setPrecio(Integer.parseInt(input));
                    App.actualizarArticulo();
                    completarLabels(objetoModificar);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "El precio del artículo ha sido modificado exitosamente.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar solamente números.");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar carácteres válidos.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para cambiar la cantida de artículos.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void cambiarCantidad(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nueva cantidad de artículos.");
        dialog.setHeaderText("Ingrese la nueva cantidad de artículos:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (!input.isEmpty()) {
                if (esNumerico(input)) {
                    objetoModificar.setCantidad(Integer.parseInt(input));
                    App.actualizarArticulo();
                    completarLabels(objetoModificar);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "La cantidad de artículos ha sido modificada exitosamente.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar solamente números.");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar caracteres válidos.");
                alert.show();
            }
        });
    }

    /**
     * *
     * Método para obtener un objeto específico.
     *
     * @param articulos ArrayList con los objetos articulo
     * @param filtro código o nombre del artículo a buscar.
     * @return objeto artículo requerido.
     */
    private articulo obtenerObjetoEspecifico(ArrayList<articulo> articulos, String filtro) {
        for (articulo articulo : articulos) {
            if (filtro.equals(String.valueOf(articulo.getCodigoArticulo())) || filtro.equals(articulo.getNombreArticulo())) {
                return articulo;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo ingresado no existe.");
        alert.show();
        return null;
    }
}

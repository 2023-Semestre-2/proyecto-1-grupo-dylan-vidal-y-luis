package controllers;

//Módulo de importaciones.
import clases.articulo;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import login.App;
import controllers.RegistroArticulosController;
import static controllers.RegistroArticulosController.esNumerico;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * Definición del controlador de la ventana.
 *
 * @author Vidal Flores
 */
public class VentanaBuscarProductoController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private MenuItem busqCodProducto;
    @FXML
    private MenuItem busqNombProducto;
    @FXML
    private Button botRegresar;
    @FXML
    private GridPane gridInformacion;
    @FXML
    private MenuItem botElimArticuloCodigo;
    @FXML
    private MenuItem botElimNombreArticulo;
    @FXML
    private Menu menuEliminar;

    /**
     * Inializador del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * *
     * Método para cambiar a la ventana anterior.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botRegresar), "registroProductos");
    }

    /**
     * *
     * Método para buscar un producto por el código.
     *
     */
    @FXML
    public void busquedaCodigoProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por Código de Artículos.");
        dialog.setHeaderText("Ingrese el código del artículo:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(codigoProducto -> {
            if (esNumerico(codigoProducto)) {
                ArrayList articulos = App.devolverArticulos();
                System.out.println(articulos);

                ArrayList elementosMostrar = filtrarPorCodigo(articulos, Integer.parseInt(codigoProducto));
                gridInformacion.getChildren().clear();
                if (!elementosMostrar.isEmpty()) {
                    limpiar();
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                    menuEliminar.setDisable(false);
                } else {
                    //listViewElementos.getItems().clear();
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
     * Método para buscar un producto por el nombre.
     *
     */
    @FXML
    public void busquedaNombreProducto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Búsqueda por Nombre de Producto");
        dialog.setHeaderText("Ingrese el nombre del producto:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(codigoProducto -> {
            if (!codigoProducto.isEmpty()) {
                ArrayList productos = App.devolverArticulos();
                System.out.println(codigoProducto);

                ArrayList elementosMostrar = filtrarPorNombre(productos, codigoProducto);
                if (!elementosMostrar.isEmpty()) {
                    limpiar();
                    colocarLabels();
                    colocarInformacion(elementosMostrar);
                    menuEliminar.setDisable(false);

                } else {
                    //listViewElementos.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se han encontrado productos con el valor ingresado.");
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
     * Método para buscar en el ArrayList por el código.
     *
     * @param articulos ArrayList con los objetos articulo
     * @param filtro código del objeto a buscar
     * @return objeto requerido.
     */
    public static ArrayList filtrarPorCodigo(ArrayList<articulo> articulos, int filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo producto : articulos) {
            if (producto.getCodigo() == filtro) {
                ArrayList<String> listaInfo = producto.mostrarTodo();
                System.out.println(listaInfo + "listainfo");
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(2, listaInfo.size()));
                elementos.addAll(subLista);
                System.out.println(subLista + "sublista");
                //String infoAMostrar = ordenarLista(listaInfo);
                //elementos.add(infoAMostrar);
            }
        }
        return elementos;

    }

    /**
     * *
     *
     * @param articulos ArrayList con los objetos articulo.
     * @param filtro nombre del objeto a buscar.
     * @return objeto requerido
     */
    public static ArrayList filtrarPorNombre(ArrayList<articulo> articulos, String filtro) {
        ArrayList<String> elementos = new ArrayList<>();
        for (articulo articulo : articulos) {
            if (articulo.getNombreProducto().equals(filtro)) {
                ArrayList<String> listaInfo = articulo.mostrarTodo();
                System.out.println(listaInfo + "listainfo");
                ArrayList<String> subLista = new ArrayList<>(listaInfo.subList(2, listaInfo.size()));
                elementos.addAll(subLista);
                System.out.println(subLista + "sublista");
            }
        }
        return elementos;

    }

    /**
     * *
     * Método para colocar los titulos del gridPane.
     *
     */
    private void colocarLabels() {

        ArrayList etiquetas = new ArrayList();

        // Agregar títulos por defecto a las columnas del GridPane
        etiquetas.add("Código del artículo");
        etiquetas.add("Nombre del artículo");
        etiquetas.add("Categoría");
        etiquetas.add("Tamaño");
        etiquetas.add("Marca");
        etiquetas.add("Precio");
        etiquetas.add("Cantidad");
        // Agrega más títulos según sea necesario
        for (int i = 0; i < 7; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            gridInformacion.getChildren().add(newLabel);

        }

    }

    /**
     * *
     * Método para mostrar la información en el gridPane de la interfaz gráfica.
     *
     * @param elementos
     */
    private void colocarInformacion(ArrayList elementos) {
        int fila = 1;  // Empieza en la fila siguiente a los títulos
        int columna = 0;  // Empieza en la fila siguiente a los títulos
        for (Object elemento : elementos) {
            Label label = new Label(elemento.toString());
            if (columna == 7) {
                columna = 0;
                fila++;
            }
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            GridPane.setConstraints(label, columna, fila);
            gridInformacion.getChildren().add(label);
            columna++;
        }
    }

    private void limpiar() {
        gridInformacion.getChildren().clear();
    }

    @FXML
    private void eliminarPorCodigo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar artículo con el código.");
        dialog.setHeaderText("Ingrese el código del artículo a eliminar:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            if (esNumerico(input)) {
                ArrayList codigos = App.getCodigosArticulos();
                if (codigos.contains(Integer.parseInt(input))) {
                    ArrayList facturados = App.getArticulosCodFacturados();
                    System.out.println(facturados);
                    if (!facturados.contains(Integer.parseInt(input))) {
                        Alert alerta = new Alert(AlertType.CONFIRMATION);
                        alerta.setTitle("Confirmación");
                        alerta.setHeaderText("¿Está seguro de eliminar el artículo?");

                        alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                        alerta.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.YES) {
                                App.eliminarArticuloCodigo(Integer.parseInt(input));
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "El artículo ha sido eliminado satisfactoriamente.");
                                alert.show();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING, "No se eliminó al artículo.");
                                alert.show();
                            }
                        });
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo no se puede eliminar, porque se encuentra facturado.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningún artículo con el código ingresado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar solamente números enteros.");
                alert.show();
            }
        });
    }

    @FXML
    private void eliminarPorNombre(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar artículo con el nombre.");
        dialog.setHeaderText("Ingrese el nombre del artículo a eliminar:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(input -> {
            ArrayList codigos = App.getNombresArticulos();
            if (codigos.contains(input)) {
                ArrayList facturados = App.getNombArticulosFacturados();
                System.out.println(facturados);
                if (!facturados.contains(input)) {
                    Alert alerta = new Alert(AlertType.CONFIRMATION);
                    alerta.setTitle("Confirmación");
                    alerta.setHeaderText("¿Está seguro de eliminar el artículo?");

                    alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                    alerta.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            App.eliminarArticuloNombre(input);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "El artículo ha sido eliminado satisfactoriamente.");
                            alert.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "No se eliminó al artículo.");
                            alert.show();
                        }
                    });

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El artículo no se puede eliminar, porque se encuentra facturado.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningún artículo con el código ingresado.");
                alert.show();
            }

        });
    }
}

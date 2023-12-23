package controllers;

//Módulo de importaciones
import clases.articulo;
import clases.tipoProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.App;

/**
 * Inicializador del controlador de la ventana para el registro de artículos.
 *
 * @author Vidal Flores
 */
public class RegistroArticulosController implements Initializable {

    //Definición de variables y elementos gráficos a utilizar.
    @FXML
    private ComboBox<String> tipoProducto;
    @FXML
    private TextField nombreArticulo;
    @FXML
    private ComboBox<String> tiposArticulo;
    @FXML
    private TextField marcaArticulo;
    @FXML
    private TextField precioArticulo;
    @FXML
    private TextField cantidadArticulo;
    @FXML
    private Button botAgregar;
    @FXML
    private Button botRegresar;
    @FXML
    private Text tamanno;
    @FXML
    private ComboBox<String> tamannoArticulo;

    private ArrayList<String> productosDisponibles;

    private String tamannNuevoArticulo;

    private String[] tipos = {"Bicicletas", "Suplementos", "Accesorios"};
    private String pTipoArticulo;
    private String pNombreArticulo;
    private String pCategoriaArticulo;
    private String pTamannoArticulo;
    private String pMarcaArticulo;
    private int pPrecioArticulo;
    private int pCantArticulo;

    /**
     * Inicializador del controlador de la ventana.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productosDisponibles = App.verProductos();
        tipoProducto.getItems().addAll(productosDisponibles);
        tiposArticulo.getItems().addAll(tipos);
    }

    /**
     * *
     * Método para crear un artículo.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     */
    @FXML
    private void crearNuevoArticulo(ActionEvent event) {
        if (validarDatos()) {
            int codigo = App.buscarCodigoProducto(pTipoArticulo);
            int codigoArticulo = App.cantArticulos() + 1;
            articulo newArticulo = new articulo(App.buscarCodigoDisponible(App.devolverArticulos()), pNombreArticulo, pCategoriaArticulo, pTamannoArticulo, pMarcaArticulo, pPrecioArticulo, pCantArticulo, codigo, pTipoArticulo);
            borrarDatos();
            App.guardarArticulo(newArticulo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuevo articulo agregado.");
            alert.show();
        } else {
            System.out.println("No se guardó la información");
        }
    }

    /**
     * *
     * Método para regresar a la ventana actual.
     *
     * @param event Evento para accionar el método cuando se presione el botón.
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAgregar), "agregarProducto");
    }


    /**
     * *
     * Método para habilitar las opciones de tamaño si se selecciona un valor en
     * específico, sino se asigna un valor por default.
     *
     */
    @FXML
    public void habilitarComboBox() {
        if ("Bicicletas".equals(tiposArticulo.getValue())) {
            String[] tamannos = {"12", "16", "22", "26", "27", "27.5", "29"};
            tamannoArticulo.getItems().addAll(tamannos);
            tamannoArticulo.setDisable(false);
        } else {
            tamannoArticulo.getItems().clear();
            tamannoArticulo.setDisable(true);
            tamannNuevoArticulo = "0";
        }
    }

    /**
     * *
     * Método para validar la información ingresada por el usuario
     *
     * @return Booleano si todos los datos son correctos.
     */
    public boolean validarDatos() {
        if (tipoProducto.getValue() != null) {
            pTipoArticulo = tipoProducto.getValue();
            if (!nombreArticulo.getText().isEmpty() && !esNumerico(nombreArticulo.getText())) {
                pNombreArticulo = nombreArticulo.getText().strip();
                if (tiposArticulo.getValue() != null) {
                    pCategoriaArticulo = tiposArticulo.getValue();
                    if (tamannoArticulo.getValue() != null || tamannoArticulo.isDisable()) {
                        if (tamannoArticulo.isDisable()) {
                            pTamannoArticulo = "0";
                        } else {
                            pTamannoArticulo = tamannoArticulo.getValue();
                        }
                        if (!marcaArticulo.getText().isEmpty() && !esNumerico(marcaArticulo.getText())) {
                            pMarcaArticulo = marcaArticulo.getText().strip();
                            if (!precioArticulo.getText().isEmpty() && esNumerico(precioArticulo.getText())) {
                                pPrecioArticulo = Integer.parseInt(precioArticulo.getText().strip());
                                if (!cantidadArticulo.getText().isEmpty() && esNumerico(cantidadArticulo.getText())) {
                                    pCantArticulo = Integer.parseInt(cantidadArticulo.getText().strip());
                                    return true;
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un número entero para la cantidad de articulos.");
                                    alert.show();
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar un número entero para el precio del articulo.");
                                alert.show();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Debe ingresar la marca del articulo.");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Debe selecionar un tamaño.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar la categoria del articulo.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Debes ingresar el nombre del nuevo articulo.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Debes seleccionar un tipo de producto para el nuevo articulo.");
            alert.show();
        }
        return false;

    }

    /**
     * *
     * Método para borrar la información ingresada por el usuario sin guardar.
     *
     */
    public void borrarDatos() {
        pTipoArticulo = null;
        pNombreArticulo = null;
        pCategoriaArticulo = null;
        pTamannoArticulo = null;
        pMarcaArticulo = null;
        pPrecioArticulo = 0;
        pCantArticulo = 0;
        tipoProducto.setValue(null);
        nombreArticulo.setText(null);
        tiposArticulo.setValue(null);
        tamannoArticulo.setValue(null);
        tamannoArticulo.setDisable(true);
        marcaArticulo.setText(null);
        precioArticulo.setText(null);
        cantidadArticulo.setText(null);
    }

    /**
     * *
     * Método para validar si un string es númerico o no.
     *
     * @param cadena String a válidar.
     * @return Booleano si es númerico o no.
     */
    public static boolean esNumerico(String cadena) {
        // Expresión regular que verifica si la cadena contiene solo dígitos
        String regex = "\\d+";
        return cadena.matches(regex);
    }

}

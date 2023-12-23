package controllers;

//Modulos importados
import clases.Cliente;
import clases.Factura;
import clases.articulo;
import clases.servicio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import login.App;

/**
 * FXML Controller class
 *
 * @author Dylan Meza
 */
public class BuscarFacturaCodigoNombreController implements Initializable {

    @FXML
    private Label nombre;
    @FXML
    private Button btBuscar;
    @FXML
    private TextField obtenerDato;
    @FXML
    private Button botLimpiar;
    @FXML
    private Button botAnular;
    @FXML
    private GridPane descripcionProducto;
    @FXML
    private Label total;

    private int j = 1;
    private int precioTotal = 0;
    private int codigoFactura = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList etiquetas = new ArrayList();

        etiquetas.add("Descripción");
        etiquetas.add("Cantidad");
        etiquetas.add("Precio");
        for (int i = 0; i < 3; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            descripcionProducto.getChildren().add(newLabel);

        }
    }

    /**
     * Metodo para regresar a la ventana anterior
     *
     * @param event
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(botAnular), "registroFactura");
    }

    /**
     * Metodo que se encarga de realizar la busqueda
     *
     * @param event
     */
    @FXML
    private void mostrarBusqueda(ActionEvent event) {
        String dato = obtenerDato.getText();
        boolean existe = false;
        if (dato != null && !dato.isEmpty()) {
            List<Cliente> clientes = App.getClientes();
            List<Factura> facturas = App.getFactura();
            ArrayList<servicio> Servicios = App.getServicios();
            ArrayList<articulo> Articulos = App.devolverArticulos();

            try {
                for (Factura factura : facturas) {
                    if(factura.getNumeroFactura() == Integer.parseInt(dato.strip()) && factura.getEstado().equals("Valida")) {
                        btBuscar.setDisable(true);
                        codigoFactura = factura.getNumeroFactura();
                        montarInfo(factura,Articulos,Servicios);
                        for(Cliente cliente : clientes) {
                            if(cliente.getCodigo() == factura.getCodigoCliente()) {
                                existe = true;
                                nombre.setText("Nombre de cliente: " + cliente.getNombre() + " " + cliente.getApellido());
                                total.setText("Total: " + precioTotal);
                                break;
                            }
                        }
                    break;
                    }
                }
            } catch(NumberFormatException e) {
                for(Cliente cliente : clientes) {
                    if(cliente.getNombre().equals(dato.strip())) {
                        for(Factura factura : facturas) {
                            if(factura.getCodigoCliente() == cliente.getCodigo()){
                                codigoFactura = factura.getNumeroFactura();
                                montarInfo(factura,Articulos,Servicios);
                            }
                        }
                        btBuscar.setDisable(true);
                        existe = true;
                        nombre.setText("Nombre de cliente: " + cliente.getNombre() + " " + cliente.getApellido());
                        total.setText("Total: " + precioTotal);
                    }
                }
            }
            if (!existe) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Factura no encontrada.");
                alert.show();
            }

        }
    }

    /**
     * Metodo para limpiar la informacion en pantalla
     *
     * @param event
     */
    @FXML
    private void limpiar(ActionEvent event) {
        
        btBuscar.setDisable(false);
        descripcionProducto.getChildren().clear();
        nombre.setText("Nombre de cliente: ");
        total.setText("Total: ");
        j = 1;
        codigoFactura = 0;
        
        ArrayList etiquetas = new ArrayList();

        etiquetas.add("Descripción");
        etiquetas.add("Cantidad");
        etiquetas.add("Precio");
        for (int i = 0; i < 3; i++) {
            Label newLabel = new Label(etiquetas.get(i).toString());
            GridPane.setConstraints(newLabel, i, 0);
            GridPane.setHalignment(newLabel, HPos.CENTER);
            GridPane.setValignment(newLabel, VPos.CENTER);
            descripcionProducto.getChildren().add(newLabel);
        }
    }

    /**
     * Metodo para anular una factura
     *
     * @param event
     */
    @FXML
    private void anularFactura(ActionEvent event) {
        if(codigoFactura == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha seleccionado una factura");
            alert.show();
        }else{
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setHeaderText("¿Seguro que desea anular la factura?");
            ButtonType botonSi = new ButtonType("Sí");
            ButtonType botonNo = new ButtonType("No");
            confirmacion.getButtonTypes().setAll(botonSi, botonNo);

            confirmacion.showAndWait().ifPresent(respuesta -> {
                if(respuesta == botonSi) {
                    List<Factura> facturas = App.getFactura();
                    for(Factura factura : facturas) {
                        if(factura.getNumeroFactura() == codigoFactura){
                            factura.setEstado("Anulado");
                            App.actualizarFactura();
                            break;
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Factura anulada");
                    alert.show();
                }
            });
        }
    }

    /**
     * Metodo para mostrar la informacion en pantalla
     * 
     * @param factura Objeto factura encontrada
     * @param Articulos De articulos
     * @param Servicios Lista de servicios
     */
    private void montarInfo(Factura factura, ArrayList<articulo> Articulos,ArrayList<servicio> Servicios) {
        List<List> codigosArticulos = factura.getArticuloXcantidad();
        List<Integer> codigosServicios = factura.getCodigoServicio();
        for (List<Integer> codigo : codigosArticulos) {
            for (articulo elementoArticulo : Articulos) {
                if (codigo.get(0).equals(elementoArticulo.getCodigoArticulo())) {
                    String nombreArticulo = elementoArticulo.getNombreProducto() + " " + elementoArticulo.getNombreArticulo();
                    Integer cantidad = codigo.get(1);
                    Integer total = elementoArticulo.getPrecio() * codigo.get(1);

                    ArrayList mostrar = new ArrayList();
                    mostrar.add(nombreArticulo);
                    mostrar.add(cantidad);
                    mostrar.add(total);
                    precioTotal += total;
                    for (int i = 0; i < 3; i++) {
                        Label newLabel = new Label(mostrar.get(i).toString());
                        GridPane.setConstraints(newLabel, i, j);
                        GridPane.setHalignment(newLabel, HPos.CENTER);
                        GridPane.setValignment(newLabel, VPos.CENTER);
                        descripcionProducto.getChildren().add(newLabel);
                    }
                    j++;
                    break;
                }
            }
        }
        for (Integer codigo : codigosServicios) {
            for (servicio elementoServicio : Servicios) {
                if (codigo.equals(elementoServicio.getCodigoServicio())) {
                    ArrayList mostrar = new ArrayList();
                    mostrar.add("Servicio de mantenimiento:" + elementoServicio.getMarcaBici());
                    mostrar.add(1);
                    mostrar.add(elementoServicio.getPrecio());
                    precioTotal += elementoServicio.getPrecio();
                    for (int i = 0; i < 3; i++) {
                        Label newLabel = new Label(mostrar.get(i).toString());
                        GridPane.setConstraints(newLabel, i, j);
                        GridPane.setHalignment(newLabel, HPos.CENTER);
                        GridPane.setValignment(newLabel, VPos.CENTER);
                        descripcionProducto.getChildren().add(newLabel);
                    }
                    j++;
                    break;
                }
            }
        }
    }
}

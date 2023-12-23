package controllers;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import login.App;

/**
 *
 * @author Dylan Meza
 */
public class BuscarFacturaFechaController implements Initializable{

    @FXML
    private Button botAnular;

    @FXML
    private Button botLimpiar;

    @FXML
    private Button btBuscar;

    @FXML
    private ComboBox<String> comboFactura;

    @FXML
    private GridPane descripcionProducto;

    @FXML
    private DatePicker fecha;

    @FXML
    private Label nombre;

    @FXML
    private Label total;

    List<Cliente> clientes = App.getClientes();
    List<Factura> facturas = App.getFactura();
    ArrayList<servicio> Servicios = App.getServicios();
    ArrayList<articulo> Articulos = App.devolverArticulos();
    private int j = 1;
    private int precioTotal = 0;
    private int codigoFactura = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboFactura.setVisible(false);
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
     * Metodo para anular la factura encontrada
     * 
     * @param event Al hacer click
     */
    @FXML
    void anularFactura(ActionEvent event) {
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
     * Obtiene la fecha seleccionada del dataPicker
     * 
     * @param event al seleccionar una fecha
     */
    @FXML
    void buscarFecha(ActionEvent event) {
        for(Factura factura:facturas){
            if(factura.getFechaFactura().equals(fecha.getValue()) && factura.getEstado().equals("Valida")){
                System.out.println("Encontro la factura");
                for(Cliente cliente : clientes){
                    System.out.println("Bucle cliente");
                    if(cliente.getCodigo() == factura.getCodigoCliente()){
                        System.out.println("Encontro el cliente en factura");
                        comboFactura.getItems().add("Números factura: "+factura.getNumeroFactura()+" Cliente: "+cliente.getNombre()+" "+cliente.getApellido());
                    }
                }
                fecha.setVisible(false);
                comboFactura.setVisible(true);
            }
        }
        if(comboFactura.getItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se han encontrado facturas del dia seleccionado");
            alert.show();
        }
    }

    /**
     * Limpia la informacion mostrada en pantalla
     * 
     * @param event Al hacer click
     */
    @FXML
    void limpiar(ActionEvent event) {
        fecha.setVisible(true);
        comboFactura.setVisible(false);
        comboFactura.getItems().clear();
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
     * Muestra la opcion seleccionada del comboBox
     * 
     * @param event Al hacer click
     */
    @FXML
    void mostrarBusqueda(ActionEvent event) {
        if(comboFactura.getValue()!=null){
            btBuscar.setDisable(true);
            String[] partes = comboFactura.getValue().split(" ");
            String codigo = partes[2].trim();
            String dato = String.valueOf(fecha.getValue());
            boolean existe = false;
            if(dato != null && !dato.isEmpty()) {
                for (Factura factura : facturas) {
                    if(factura.getNumeroFactura() == Integer.parseInt(codigo)) {
                        codigoFactura = factura.getNumeroFactura();
                        montarInfo(factura,Articulos,Servicios);
                        total.setText("Total: "+precioTotal);
                    }
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No se a seleccionado ninguna facturas");
            alert.show();
        }
        
    }
    
    /**
     * Metodo para regresar a la ventana anterior
     * 
     * @param event Al hacer click
     * @throws IOException 
     */
    @FXML
    void regresar(ActionEvent event) throws IOException {
        App.cambiarVista(App.getStage(btBuscar), "registroFactura");
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

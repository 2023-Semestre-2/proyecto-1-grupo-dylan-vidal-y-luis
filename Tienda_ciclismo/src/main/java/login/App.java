package login;

//Módulo de importaciones
import archivos.cargarArchivo;
import archivos.guardarArchivo;
import clases.Cliente;
import clases.Factura;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import clases.articulo;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import clases.articulo;
import clases.servicio;
import clases.tipoProducto;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import javafx.scene.control.Button;

/**
 * Inicizalidor de la aplicación
 */
public class App extends Application {

    //Definicion y carga de información para la aplicación.
    private static Scene scene;
    private static ArrayList<tipoProducto> infoProductos = new ArrayList();
    private static ArrayList<articulo> infoArticulos = new ArrayList();
    private static ArrayList<servicio> infoServicios = new ArrayList();
    private static List<Cliente> infoClientes = new ArrayList();
    private static List<Factura> infoFactura = new ArrayList();

    /**
     * Método para iniciar la aplicación.
     *
     * @param stage stage para la ventana
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ventanalogin"), 590, 400);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Método para cambiar las ventanas.
     *
     * @param stage stage para cambiar la ventana
     * @param fxml archivo fxml a cargar
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    static void cambiarVentana(Stage stage, String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    /**
     * Método para cargar archivos fxml
     *
     * @param fxml archivo fxml a cargar
     * @return el archivo cargado
     * @throws IOException Excepciones en el caso de que falle alguna llamada a
     * otros métodos
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método main para iniciar la aplicación.
     *
     * @param args args para la ejecución de una aplicación java
     */
    public static void main(String[] args) {

        infoClientes = cargarArchivo.leerClientes();
        infoFactura = cargarArchivo.leerFactura();
        infoProductos = cargarArchivo.leerProducto();
        infoArticulos = cargarArchivo.leerArticulo();
        infoServicios = cargarArchivo.leerServicio();
        
        launch();

    }

    /**
     * Método para acceder a cambiarVentana de manera pública.
     *
     * @param stage
     * @param fxml
     * @throws IOException
     */
    public static void cambiarVista(Stage stage, String fxml) throws IOException {
        cambiarVentana(stage, fxml);
    }

    /**
     * Método para devolver el ArrayList con los objetos productos.
     *
     * @return ArrayList con los objetos productos.
     */
    public static ArrayList devolverInfo() {
        return infoProductos;
    }

    /**
     * Método para guardar un objeto producto
     *
     * @param objeto objeto a guardar
     */
    public static void guardarProducto(tipoProducto objeto) {
        infoProductos.add(objeto);
        System.out.println(objeto.infoProducto());
        guardarArchivo.guardarProducto(infoProductos);
    }

    /**
     * Método para guardar un artículo.
     *
     * @param objeto objeto a guardar
     */
    public static void guardarArticulo(articulo objeto) {
        infoArticulos.add(objeto);
        System.out.println(objeto.mostrarTodo());
        guardarArchivo.guardarArticulo(infoArticulos);
    }

    /**
     * Método para obtener el ArrayList de los productos.
     *
     * @return ArrayList con el string de los productos.
     */
    public static ArrayList<String> verProductos() {
        ArrayList<String> disponibles = new ArrayList<>();
        for (tipoProducto producto : infoProductos) {
            disponibles.add(producto.getNombreProducto());
        }
        System.out.println(disponibles);
        return disponibles;

    }

    /**
     * Método para devolver la cantidad de productos
     *
     * @return
     */
    public static int cantProductos() {
        return infoProductos.size();
    }

    /**
     * Método para devolver la cantidad de productos
     *
     * @return infoFactura el largo de la lista de facturas
     */
    public static int cantFactura() {
        return infoFactura.size();
    }

    /**
     * Método para buscar el código de un producto.
     *
     * @param producto el nombre del producto
     * @return código del producto.
     */
    public static int buscarCodigoProducto(String producto) {
        for (tipoProducto elemento : infoProductos) {
            if (elemento.getNombreProducto().equals(producto)) {
                return elemento.getCodigo();
            }
        }
        return -1;
    }

    /**
     * Método para devolver la cantidad de artículos.
     *
     * @return la cantidad de artículos.
     */
    public static int cantArticulos() {
        return infoArticulos.size();
    }

    /**
     * Método para devolver el ArrayList con los objeto artículo.
     *
     * @return ArrayList con los objetos artículo.
     */
    public static ArrayList devolverArticulos() {
        return infoArticulos;
    }

    /**
     * Método para devolver el ArrayList de los clientes.
     *
     * @return ArrayList con los objetos cliente.
     */
    public static ArrayList devolverClientes() {
        return (ArrayList) infoClientes;
    }

    /**
     * Método para obtener el ArrayList con los servicios.
     *
     * @return ArrayList con los objetos servicio.
     */
    public static ArrayList<servicio> getServicios() {
        return infoServicios;
    }

    /**
     * Método para devolver la cantidad de servicios.
     *
     * @return la cantidad de servicios.
     */
    public static int cantServicios() {
        return infoServicios.size();
    }

    /**
     * Método para guardar un servicio nuevo
     *
     * @param newServicio objeto servicio a guardar
     */
    public static void guardarServicio(servicio newServicio) {
        infoServicios.add(newServicio);
        System.out.println(newServicio.mostrarTodo());
        guardarArchivo.guardarServicios(infoServicios);
    }

    /**
     * Método para guardar un nuevo cliente.
     *
     * @param newCliente objeto cliente a guardar
     */
    public static void guardarCliente(Cliente newCliente) {
        infoClientes.add(newCliente);
        System.out.println(newCliente.toString());
        guardarArchivo.guardarCliente(infoClientes);
    }

    /**
     * Método para guardar una nueva factura.
     *
     * @param newFactura objeto Factura a guardar
     */
    public static void guardarFactura(Factura newFactura) {
        infoFactura.add(newFactura);
        System.out.println(newFactura.toString());
        guardarArchivo.guardarFactura(infoFactura);
    }

    /**
     * Método devolver el ArrayList de los clientes
     *
     * @return ArrayList con los objetos cliente
     */
    public static List<Cliente> getClientes() {
        return infoClientes;
    }

    /**
     * Método devolver el ArrayList de los clientes
     *
     * @return ArrayList con los objetos cliente
     */
    public static List<Factura> getFactura() {
        return infoFactura;
    }

    /**
     * Método para modificar un cliente.
     *
     * @param indice posicion del objeto en el ArrayList.
     * @param newCliente nuevo objeto cliente a modificar.
     */
    public static void modificarCliente(int indice, Cliente newCliente) {
        infoClientes.set(indice, newCliente);
        archivos.guardarArchivo.guardarCliente(infoClientes);
    }

    /**
     * *
     * Método para extraer el código del cliente del string mostrado al usuario.
     *
     * @param cadenaCodigo String del comboBox mostrado al usuario.
     * @return El código del cliente seleccionado.
     */
    public static int obtenerCodigoCliente(String cadenaCodigo) {

        String[] partes = cadenaCodigo.split(":");
        String codigoCliente = partes[1].trim();
        int codigo = Integer.parseInt(codigoCliente);

        return codigo;
    }

    /**
     * *
     * Método para extraer el nombre de un producto o servicio del cliente del
     * string mostrado al usuario.
     *
     * @param cadenaCodigo String del comboBox mostrado al usuario.
     * @param i entero con la posicion del nnombre
     * @return El nombre buscado.
     */
    public static String obtenerNombre(String cadenaCodigo, int i) {

        String[] partes = cadenaCodigo.split(":");
        String nombreBuscado = partes[i].trim();

        return nombreBuscado;
    }

    /**
     * Obtiene la ventana
     * 
     * @param boton elemento de la ventana
     * @return ventana actual donde se encuentra el boton
     */
    public static Stage getStage(Button boton) {
        Stage stage = (Stage) boton.getScene().getWindow();
        return stage;
    }


    public static ArrayList getArticulosCodFacturados() {
        ArrayList facturados = new ArrayList();
        for (Factura factura : infoFactura) {
            for (int codigo : factura.getCodigoArticulo()) {
                facturados.add(codigo);
            }
        }
        return facturados;
    }

    public static ArrayList getNombArticulosFacturados() {

        ArrayList<Integer> facturadosCod = getArticulosCodFacturados();
        ArrayList<String> facturados = new ArrayList<>();
        for (articulo articulo : infoArticulos) {
            for (int codigo : facturadosCod) {
                if (articulo.getCodigoArticulo() == codigo) {
                    facturados.add(articulo.getNombreArticulo());
                }

            }
        }
        return facturados;
    }

    public static ArrayList getCodigosArticulos() {
        ArrayList codigos = new ArrayList();
        for (articulo articulo : infoArticulos) {
            codigos.add(articulo.getCodigoArticulo());
        }
        return codigos;
    }

    public static ArrayList getNombresArticulos() {
        ArrayList nombres = new ArrayList();
        for (articulo articulo : infoArticulos) {
            nombres.add(articulo.getNombreArticulo());
        }
        return nombres;

    }

    public static ArrayList getCodigosClientesFacturados() {
        ArrayList codigos = new ArrayList();
        for (Factura factura : infoFactura) {
            codigos.add(factura.getCodigoCliente());
        }
        return codigos;

    }

    public static ArrayList getCodigosServiciosFacturados() {
        ArrayList codigos = new ArrayList();
        for (Factura factura : infoFactura) {
            codigos.add(factura.getCodigoServicio());
        }
        return codigos;
    }

    public static int buscarCodigoDisponible(ArrayList lista) {
        ArrayList<Integer> codigos = new ArrayList<>();
        Object objeto = lista.get(0);
        if (objeto instanceof Cliente) {
            for (Object objetoCliente : lista) {
                Cliente cliente = (Cliente) objetoCliente;
                codigos.add(cliente.getCodigo());
            }
            return siguienteCodigoDisponible(codigos);

        } else if (objeto instanceof Factura) {
            for (Object objetoFactura : lista) {
                Factura factura = (Factura) objetoFactura;
                codigos.add(factura.getNumeroFactura());
            }
            return siguienteCodigoDisponible(codigos);

        } else if (objeto instanceof articulo) {
            for (Object objetoArticulo : lista) {
                articulo articulo = (articulo) objetoArticulo;
                codigos.add(articulo.getCodigoArticulo());
            }
            return siguienteCodigoDisponible(codigos);
        } else {
            for (Object objetoServicio : lista) {
                servicio servicio = (servicio) objetoServicio;
                codigos.add(servicio.getCodigoServicio());
            }
            return siguienteCodigoDisponible(codigos);
        }
    }

    private static int siguienteCodigoDisponible(ArrayList<Integer> codigos) {
        int mayor = codigos.get(0);
        for (Integer num:codigos){
            if(num>mayor){
            mayor=num;
            }
        }
        return mayor + 1;
    }
    
    public static void eliminarCliente(int codigo){
        int indice=0;
        for(Cliente cliente: infoClientes){
            if (cliente.getCodigo() == codigo){
                infoClientes.remove(indice);
                break;
            }
            indice++;
        }
    }
    public static void eliminarArticuloCodigo(int codigo){
        int indice=0;
        for(articulo articulo: infoArticulos){
            if (articulo.getCodigoArticulo() == codigo){
                infoArticulos.remove(indice);
                break;
            }
            indice++;
        }
    }
    public static void eliminarArticuloNombre(String nombre){
        int indice=0;
        System.out.println(nombre);
        for(articulo articulo: infoArticulos){
            if (articulo.getNombreArticulo().equals(nombre)){
                System.out.println(articulo.getNombreArticulo());
                System.out.println(infoArticulos);
                infoArticulos.remove(indice);
                break;
            }
            indice++;
        }
    }
    public static void eliminarServicio(int codigo){
        int indice=0;
        System.out.println(codigo);
        for(servicio servicio: infoServicios){
            if (servicio.getCodigoServicio() == codigo){
                System.out.println((char) servicio.getCodigoServicio());
                System.out.println(infoServicios);
                infoServicios.remove(indice);
                break;
            }
            indice++;
        }
    }
    
    /**
     * Actualiza la factura con el valor Anulado
     */
    public static void actualizarFactura() {
        guardarArchivo.guardarFactura(infoFactura);
    }
    
    /**
     * Actualiza los valores de los articulos
     */
    public static void actualizarArticulo() {
        guardarArchivo.guardarArticulo(infoArticulos);
    }
    
    /**
     * Actualiza los valores de los servicios
     */
    public static void actualizarServicio() {
        guardarArchivo.guardarServicios(infoServicios);
    }
}

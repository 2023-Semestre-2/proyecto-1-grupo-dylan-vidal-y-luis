package archivos;

//Módulo de importaciones
import clases.Cliente;
import clases.Factura;
import clases.articulo;
import clases.servicio;
import clases.tipoProducto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Clase para guardar información en los archivos
 * 
 * @author Vidal Flores
 */
public class guardarArchivo {

    /***
     * Método para guardar la información de los clientes registrados en el programa.
     * 
     * @param clientes Lista de objetos Cliente para guardar en disco.
     */
    public static void guardarCliente(List<Cliente> clientes) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Registro del módulo para manejar LocalDate
        objectMapper.registerModule(new JavaTimeModule());

        try {
            // Guarda la lista de clientes en un archivo JSON
            File file = new File("src/main/java/archivos/archivoscliente.json");
            System.out.println(clientes);
            objectMapper.writeValue(file, clientes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
     * Método para guardar la información de las facturas registradas en el programa.
     * 
     * @param factura Lista de objetos Factura para guardar en disco.
     */
    public static void guardarFactura(List<Factura> factura) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/java/archivos/archivosfacturas.json");
            System.out.println(factura);
            objectMapper.writeValue(file, factura);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
     * Método para guardar la información de los articulos registrados en el programa.
     * 
     * @param Articulo Lista de objetos articulo para guardar en disco.
     */
    public static void guardarArticulo(ArrayList<articulo> Articulo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/java/archivos/archivosArticulo.json");
            System.out.println(Articulo);
            objectMapper.writeValue(file, Articulo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
     * Método para guardar la información de los productos registrados en el programa.
     * 
     * @param Producto Lista de objetos tipoProducto para guardar en disco.
     */
    public static void guardarProducto(ArrayList<tipoProducto> Producto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/java/archivos/archivosProducto.json");
            System.out.println(Producto);
            objectMapper.writeValue(file, Producto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
     * Método para guardar la información de los servicios registrados en el programa.
     * 
     * @param Servicio Lista de objetos Factura para guardar en disco.
     */
    public static void guardarServicios(ArrayList<servicio> Servicio) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/java/archivos/archivosServicio.json");
            System.out.println(Servicio);
            objectMapper.writeValue(file, Servicio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

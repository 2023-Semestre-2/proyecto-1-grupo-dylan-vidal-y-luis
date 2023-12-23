package clases;

//Definición de importaciones
import java.util.ArrayList;

/**
 *
 * @author Vidal Flores
 */
public class tipoProducto {

    //Definición de variables a utilizar
    private int codigo;
    private String nombreProducto;

    /**
     * constructor vacio
     */
    public tipoProducto() {
    }
    
    /**
     * *
     * Método constructor del objeto.
     *
     * @param codigo Código unico asignado al tipo de producto.
     * @param nombreProducto Nombre del producto.
     */
    public tipoProducto(int codigo, String nombreProducto) {

        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
    }

    /**
     * *
     * Método para obtener el código del objeto.
     *
     * @return Código del objeto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * *
     * Método para cambiar el código del objeto.
     *
     * @param codigo Nuevo código para el producto.
     */
    public void setCodigo(int codigo) {

        this.codigo = codigo;
    }

    /**
     * *
     * Método para obtener el nombre del producto.
     *
     * @return Nombre del producto a crear.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * *
     * Método para cambiar el nombre del producto.
     *
     * @param nombreProducto Nuevo nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * *
     * Método para mostrar toda la información del producto.
     *
     * @return ArrayList con toda la información
     */
    public ArrayList infoProducto() {
        ArrayList informacion = new ArrayList();
        informacion.add(this.codigo);
        informacion.add(this.nombreProducto);
        return informacion;
    }

}

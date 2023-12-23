package clases;

//Definición de importaciones
import java.util.ArrayList;

/**
 * Clase para el registro de articulos.
 *
 * @author Vidal Flores
 */
public class articulo extends tipoProducto {
    
    //Definición de variables a utilizar.
    private int codigoArticulo;
    private String nombreArticulo;
    private String tipo;
    private String tamanno;
    private String marca;
    private int precio;
    private int cantidad;

    /**
     * constructor vacio con los parametros de la superclase
     */
    public articulo() {
    }

    /***
     * Método constructor para generar un nuevo objeto.
     * 
     * @param codigoArticulo Código único asignado al artículo.
     * @param nombreArticulo Nombre del nuevo artículo.
     * @param tipo Categoría del artículo.
     * @param tamanno Tamaño del artículo (si corresponde según la categoria).
     * @param marca Marca del artículo.
     * @param precio Precio del artículo.
     * @param cantidad Cantidad de artículos a registrar.
     * @param codigo Código heredado según el tipo de producto al que pertenezca.
     * @param nombreProducto Nombre del tipo de producto asignado al artículo.
     */
    public articulo(int codigoArticulo, String nombreArticulo, String tipo, String tamanno, String marca, int precio, int cantidad, int codigo, String nombreProducto) {
        super(codigo, nombreProducto);
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.tipo = tipo;
        this.tamanno = tamanno;
        this.marca = marca;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    /***
     * Método para obtener el código único del artículo
     * 
     * @return Código asignado al artículo
     */
    public int getCodigoArticulo() {
        return codigoArticulo;
    }
    
    /***
     * Método para cambiar el código del artículo
     * 
     * @param codigoArticulo Nuevo código para el artículo
     */
    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    /***
     * Método para obtener el nombre el artículo.
     * 
     * @return Nombre del artículo asignado.
     */
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    /***
     * Método para cambiar el nombre del artículo.
     * 
     * @param nombreArticulo Nuevo nombre para el artículo.
     */
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    /***
     * Método para obtener la categoría del artículo
     * 
     * @return Categoría del artículo.
     */
    public String getTipo() {
        return tipo;
    }

    /***
     * Método para cambiar la categoría del artículo.
     * 
     * @param tipo La nueva categoría a colocar en el objeto.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /***
     * Método para obtener el tamaño del artículo(si posee).
     * 
     * @return Tamaño del artículo.
     */
    public String getTamanno() {
        return tamanno;
    }

    /***
     * Método para cambiar el tamaño del artículo.
     * 
     * @param tamanno El nuevo tamaño del artículo.
     */
    public void setTamanno(String tamanno) {
        this.tamanno = tamanno;
    }

    /***
     * Método para obtener la marca del objeto.
     * 
     * @return La marca del objeto.
     */
    public String getMarca() {
        return marca;
    }

    /***
     * Método para cambiar la marca del objeto.
     * 
     * @param marca La nueva marca a colocar del objeto.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /***
     * Método para obtener el precio del artículo
     * 
     * @return El precio del artículo.
     */
    public int getPrecio() {
        return precio;
    }

    /***
     * Método para cambiar el precio al artículo.
     * 
     * @param precio Nuevo precio del artículo a colocar.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /***
     * Método para obtener la cantidad de artículos registrados.
     * 
     * @return Cantidad de artículos
     */
    public int getCantidad() {
        return cantidad;
    }

    /***
     * Método para cambiar la cantidad de artículos
     * 
     * @param cantidad Nueva cantidad de artículos.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    /***
     * Método para poder mostrar toda la información en un ArrayList
     * 
     * @return ArrayList con toda la información
     */
    public ArrayList mostrarTodo(){
        
        ArrayList info = super.infoProducto();
        info.add(this.codigoArticulo);
        info.add(this.nombreArticulo);
        info.add(this.tipo);
        info.add(this.tamanno);
        info.add(this.marca);
        info.add(this.precio);
        info.add(this.cantidad);
        return info;     
    }
}

package clases;

import java.time.LocalDate;
import java.util.List;

/**
 *Clase para el registro de facturas.
 * 
 * @author Dylan Meza
 */
public class Factura {
    
    //Definición de variables a utilizar.
    private int numeroFactura;
    private int codigoCliente;
    private List<Integer> codigoServicio;
    private LocalDate fechaFactura;
    private String estado; //Valido Anulado
    private int total;
    private List<Integer> codigoArticulo;
    private List<List> articuloXcantidad;

    /**
     * Constructor vacio
     */
    public Factura() {
    }

    /**
     * Constructor de la clase con parametros
     * 
     * @param numeroFactura numero de factura, se incrementa de 1 en 1
     * @param codigoCliente codigo del cliente
     * @param fechaFactura fecha de facturación
     * @param estado estado de la factura Valido o Anulado
     * @param total precio total de todos los articulos en la factura
     * @param codigoArticulo codigos de articulos en la factura
     * @param codigoServicios codigos de servicios en la factura 
     * @param articuloXcantidad codigo de articulos con la cantidad que se facturo
     */
    public Factura(int numeroFactura, int codigoCliente, LocalDate fechaFactura, String estado, int total, List<Integer> codigoArticulo,List<Integer> codigoServicios, List<List> articuloXcantidad) {
        this.numeroFactura = numeroFactura;
        this.codigoCliente = codigoCliente;
        this.fechaFactura = fechaFactura;
        this.estado = estado;
        this.total = total;
        this.codigoArticulo = (List<Integer>) codigoArticulo;
        this.codigoServicio = (List<Integer>) codigoServicios;
        this.articuloXcantidad = (List<List>) articuloXcantidad;
    }

    /**
     * Metodo para obtener la lista de articulos por cantidad
     * 
     * @return lista de listas con codigo de articulo y cantidad
     */
    public List<List> getArticuloXcantidad() {
        return articuloXcantidad;
    }

    /**
     * Metodo para introducir la lista de articulos por cantidad a la factura
     * 
     * @param articuloXcantidad lista de listas con codigo de articulo y cantidad
     */
    public void setArticuloXcantidad(List<List> articuloXcantidad) {
        this.articuloXcantidad = articuloXcantidad;
    }

    /**
     * Metodo para obtener la lista de codigos de servicios
     * 
     * @return Lista de codigos de servicio
     */
    public List<Integer> getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Metodo para introcucir la lista de codigos de servicio
     * 
     * @param codigoServicio Lista de codigos de servicio
     */
    public void setCodigoServicio(List<Integer> codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    /**
     * Metodo para obetener el numero de la factura
     * 
     * @return numero de factura
     */
    public int getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Metodo para introducir el numero de la factura
     * 
     * @param numeroFactura numero de la factura
     */
    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    /**
     * Metodo para obtener el codigo del cliente
     * 
     * @return codigo del cliente
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Metodo para introducir el codigo de cliente
     * 
     * @param codigoCliente codigo de cliente
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * Metodo para obtener la fecha de facturacion
     * 
     * @return fecha de facturacion en formato LocalDate
     */
    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    /**
     * Metodo para introducir la fecha de facturacion
     * 
     * @param fechaFactura fecha de facturacion en formato LocalDate
     */
    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * Metodo para obtener el estado de la factura
     * 
     * @return Estado de la factura Valido o Anulado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Metodo para introducir el estado de la factura
     * 
     * @param estado Estado de la factura Valido o Anulado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Metodo para obtener el precio total a pagar
     * 
     * @return precio total a pagar
     */
    public int getTotal() {
        return total;
    }

    /**
     * Metodo para introducir el precio total de una factura
     * 
     * @param total precio total de una factura
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Metodo para obtener la lista de codigos de los articulos de la factura
     * 
     * @return lista con codigos de articulos
     */
    public List<Integer> getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * Metodo para introducir la lista de codigos de los articulos de la factura
     * 
     * @param codigoArticulo lista con codigos de articulos
     */
    public void setCodigoArticulo(List<Integer> codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    /**
     * Metodo para obtener una cadena de strings con la informacion de la factura
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Factura{" + "numeroFactura=" + numeroFactura + ", codigoCliente=" + codigoCliente + ", fechaFactura=" + fechaFactura + ", estado=" + estado + ", total=" + total + ", codigoArticulo=" + codigoArticulo +"codigoServicio:"+ codigoServicio+'}';
    }
    
}

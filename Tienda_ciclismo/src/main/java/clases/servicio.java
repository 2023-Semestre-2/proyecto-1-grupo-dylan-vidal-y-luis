package clases;

//Definición de importaciones
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Vidal Flores
 */
public class servicio {

    //Definición de variables a utilizar
    private int codigoServicio;
    private int codigoCliente;
    private String marcaBici;
    private String descripcion;
    private int precio;
    private LocalDate fechaRecibido;
    private LocalDate fechaEntrega;
    private String observaciones;
    private boolean estado;

    /**
     * constructor vacio
     */
    public servicio() {
    }
    
    /**
     * *
     * Método constructor para generar un nuevo objeto servicio
     *
     * @param codigoServicio Código único para asignar al servicio.
     * @param codigoCliente Código del cliente a quien se le realizará el
     * servicio.
     * @param marcaBici Marca de la bicicleta a realizar el servicio.
     * @param descripcion Descripción de la bicicleta.
     * @param precio Precio del servicio a realizar.
     * @param fechaRecibido Fecha de cuando se recibió la bicicleta.
     * @param fechaEntrega Fecha de cuando se entregará la bicicleta.
     * @param observaciones Observaciones adicionales para el servicio.
     * @param estado Estado del servicios abierto o cerrado.
     */
    public servicio(int codigoServicio, int codigoCliente, String marcaBici, String descripcion, int precio, LocalDate fechaRecibido, LocalDate fechaEntrega, String observaciones, boolean estado) {
        this.codigoServicio = codigoServicio;
        this.codigoCliente = codigoCliente;
        this.marcaBici = marcaBici;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaRecibido = fechaRecibido;
        this.fechaEntrega = fechaEntrega;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    /**
     * *
     * Método para obtener el código del servicio.
     *
     * @return Número de servicio asignado.
     */
    public int getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * *
     * Método para cambiar el código del servicio.
     *
     * @param codigoServicio Nuevo código a asignar.
     */
    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    /**
     * *
     * Método para obtener el código del cliente asignado al servicio.
     *
     * @return Código del cliente del servicio.
     */
    public int getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * *
     * Método para cambiar el código del cliente.
     *
     * @param codigoCliente Nuevo código del cliente a asignar.
     */
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * *
     * Método para obtener la marca de la bicicleta.
     *
     * @return Marca de la bicicleta a realizar el servicio.
     */
    public String getMarcaBici() {
        return marcaBici;
    }

    /**
     * *
     * Método para cambiar la marca de la bicicleta.
     *
     * @param marcaBici Nueva marca de la bicicleta
     */
    public void setMarcaBici(String marcaBici) {
        this.marcaBici = marcaBici;
    }

    /**
     * *
     * Método para obtener la descripción del servicio.
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * *
     * Método para cambiar la descripción del servicio
     *
     * @param descripcion Nueva descripción del servicio.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * *
     * Método para obtener el precio del servicio.
     *
     * @return Precio del servicio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * *
     * Método para cambiar el precio del servicio.
     *
     * @param precio Nuevo precio del servicio.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * *
     * Método para obtener la fecha que se recibió la bicicleta.
     *
     * @return Fecha de cuando se recibió la bicicleta.
     */
    public LocalDate getFechaRecibido() {
        return fechaRecibido;
    }

    /**
     * *
     * Método para cambiar la fecha de cuando se recibió la bicicleta
     *
     * @param fechaRecibido Nueva fecha de cuando se recibió la bicicleta.
     */
    public void setFechaRecibido(LocalDate fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    /**
     * *
     * Método para obtener la fecha de entrega del servicio.
     *
     * @return Fecha de entrega del servicio.
     */
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * *
     * Método para cambiar la fecha de entrega del servicio.
     *
     * @param fechaEntrega Nueva fecha de entrega del servicio.
     */
    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * *
     * Método para obtener las observaciones del servicio
     *
     * @return Las observaciones del servicio.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * *
     * Método para cambiar las observaciones del servicio.
     *
     * @param observaciones Las nuevas observaciones a asignar.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * *
     * Método para saber el estado del servicio
     *
     * @return Estado del servicio
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * *
     * Método para colocar un estado al servicio.
     *
     * @param estado Nuevo estado al servicio
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * *
     * Método para obtener la información completa del objeto.
     *
     * @return ArrayList con toda la información del objeto.
     */
    public ArrayList mostrarTodo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(String.valueOf(this.codigoCliente));
        info.add(String.valueOf(this.codigoServicio));
        info.add(this.marcaBici);
        info.add(this.descripcion);
        info.add(String.valueOf(this.precio));
        info.add(this.fechaRecibido.toString());
        info.add(this.fechaEntrega.toString());
        info.add(this.observaciones);
        info.add(String.valueOf(this.estado));
        return info;
    }

}

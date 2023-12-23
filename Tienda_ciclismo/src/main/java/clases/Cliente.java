package clases;

//Módulo de importaciones
import java.time.LocalDate;

/**
 * Clase para el registro de clientes.
 *
 * @author Dylan Meza
 *
 */
public class Cliente {

    //Definición de variables a utilizar.
    private int codigo;
    private String nombre;
    private String apellido;
    private int telefono;
    private String correo;
    private String provincia;
    private String canton;
    private String distrito;
    private LocalDate fechaNacimiento;

     /**
      * Constructor vacio para objeto cliente.
      */
    public Cliente() {
    }

    /**
     * 
     * Método contructor del objeto Cliente.
     *
     * @param codigo Código único asignado a cada cliente.
     * @param nombre Nombre del cliente a registrar.
     * @param apellido Apellido del cliente.
     * @param telefono Número de telefono del cliente.
     * @param correo Correo electrónico del cliente.
     * @param provincia Provincia donde vive el cliente.
     * @param canton Cantón de origen del cliente.
     * @param distrito Distrito de residencia del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     */
    public Cliente(int codigo, String nombre, String apellido, String telefono, String correo, String provincia, String canton, String distrito, LocalDate fechaNacimiento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = Integer.parseInt(telefono);
        this.correo = correo;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * 
     * Método para devolver el código del cliente.
     *
     * @return Código del cliente.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * 
     * Método para colocar un nuevo código al cliente.
     *
     * @param codigo Nuevo código a colocar al objeto.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Método para obtener el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para cambiar el nombre al objeto
     * 
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método para obtener el apellido del cliente.
     * 
     * @return El apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Método para colocar un nuevo apellido al objeto.
     * 
     * @param apellido Nuevo apellido del cliente.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Método para obtener el teléfono del cliente.
     * 
     * @return El número telefónico del cliente.
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Método para cambiar el número de telefóno del objeto.
     * 
     * @param telefono El nuevo número telefónico.
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Método para obtener el correo electrónico asignado.
     * 
     * @return El correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método para cambiar el correo electrónico del cliente.
     * 
     * @param correo El nuevo correo eletrónico a asignar.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método para obtener la provincia del cliente.
     * 
     * @return Provincia del cliente.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Método para cambiar la provincia del cliente.
     * 
     * @param provincia Nueva provincia a colocar para el cliente.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Método para obtener el cantón del cliente.
     * 
     * @return Cantón asignado al cliente.
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Método para cambiar el cantón del cliente.
     * 
     * @param canton Nuevo cantón del cliente.
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     * Método para obtener el distrito del cliente.
     * 
     * @return El distrito asignado al cliente.
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Método para cambiar el distrito del cliente.
     * 
     * @param distrito Nuevo distrito a colocar para el cliente.
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Método para obtener la fecha de nacimiento 
     * 
     * @return La fecha de nacimiento del cliente.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método para cambiar la fecha de nacimiento del cliente.
     * 
     * @param fechaNacimiento Nueva fecha de nacimiento del objeto.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Sobreescritura del método toSTring para mostrar correctamente la información.
     * 
     * @return Información con el formato correcto.
     */
    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo + ", provincia=" + provincia + ", canton=" + canton + ", distrito=" + distrito + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

}

package login;

//Módulo de importaciones
import archivos.cargarArchivo;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import validacionDatos.validarInformacion;
import archivos.cargarArchivo;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;

/**
 * La clase VentanaLogin controla la lógica y la interacción de la ventana de inicio de sesión.
 * 
 * @author Vidal Flores
 */
public class ventanaLogin {
    
    //Definición de variables y elementos gráficos a utilizar.
    String usuario;
    String contrasenna;
    
    private Map<String, String> credenciales = new HashMap<>() ;

    @FXML
    private Button botLimpiar;
    @FXML
    private Button botIngresar;
    @FXML
    private Button botSalir;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textContrasena;
    
    /**
     * Constructor de la clase VentanaLogin. Inicializa las credenciales al cargar información del archivo "usuarios.csv".
     */
    public ventanaLogin(){
        credenciales = infoVentanaLogin();
    }
    
    /**
     * Obtiene la información de credenciales desde el archivo "usuarios.csv".
     *
     * @return Un mapa que asocia nombres de usuario con contraseñas.
     */
    private Map infoVentanaLogin(){
        credenciales = cargarArchivo.cargarInformacion("usuarios.csv");
        return credenciales;
    }
    
    /**
     * Maneja el evento de obtener el usuario y verificar las credenciales al presionar el botón "Ingresar".
     *
     * @throws IOException Si hay un error durante la carga de la siguiente ventana.
     */
    @FXML
    private void obtenerUsuario() throws IOException {
        if (textUsuario != null && !textUsuario.getText().isEmpty() && textContrasena != null && !textContrasena.getText().isEmpty()) {
            usuario = textUsuario.getText();
            contrasenna = textContrasena.getText();
            if (validarInformacion.loginUsuario(usuario, contrasenna, (HashMap<String, String>) credenciales)){
                sesionIniciada();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Falta información para poder ingresar al sistema.");
            alert.show();
        }
    }
    
    /**
     * Limpia el contenido de los campos de usuario y contraseña al presionar el botón "Limpiar".
     */
    @FXML
    private void borrarContenido(){
    textUsuario.clear();
    textContrasena.clear();
    }
   
    /**
     * Cierra la aplicación al presionar el botón "Salir".
     */
    @FXML
    private void salir(){
        Stage stage = (Stage) botSalir.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Cambia a la ventana principal al iniciar sesión correctamente.
     *
     * @throws IOException Si hay un error durante la carga de la siguiente ventana.
     */
    private void sesionIniciada() throws IOException{
        Stage stage = (Stage) botIngresar.getScene().getWindow();
        App.cambiarVentana(stage,"ventanaPrincipal");
    }
}

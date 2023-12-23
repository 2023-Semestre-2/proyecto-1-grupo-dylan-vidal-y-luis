package validacionDatos;

//Módulo de importaciones
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author Vidal Flores
 */
public class validarInformacion {
    
    /**
     * Método para válidar la información para la ventana de LogIn
     * 
     * @param usuario string que ingresa el usuario.
     * @param contra string que ingresa el usuario
     * @param credenciales Diccionario con las credenciales registradas.
     * @return Booleano si la información es válida o no.
     */
    public static boolean loginUsuario(String usuario, String contra, HashMap<String, String> credenciales) {
        // Verifica si el usuario existe en el HashMap
        if (credenciales.containsKey(usuario)) {
            // Verifica si la contraseña coincide
            if (credenciales.get(usuario).equals(contra)) {
                return true; // Credenciales correctas
            }
        } 
        return false;
    }
}

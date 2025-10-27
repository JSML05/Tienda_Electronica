/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Seguridad;

import java.util.HashMap;
import java.util.Map;
import jodd.crypt.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Asus
 */
public class Eliminar_Persona_Seguridad_test {
 
        // ===== Simulación de base de datos de usuarios =====
        private static final Map<String, String> usuarios = new HashMap<>();
        private static final Map<String, String> roles = new HashMap<>();

        // ----- Configuración inicial -----
        @BeforeAll
        public static void setup() {
            // Hash de la contraseña "12345"
            String hashAdmin = BCrypt.hashpw("12345", BCrypt.gensalt());
            String hashCliente = BCrypt.hashpw("12345", BCrypt.gensalt());

            // Guardamos usuarios con sus contraseñas cifradas
            usuarios.put("Admin", hashAdmin);
            usuarios.put("Cliente", hashCliente);

            // Roles asociados
            roles.put("Admin", "ADMIN");
            roles.put("Cliente", "CLIENTE");
        }

        // ===== Verificar credenciales de forma segura =====
        private boolean verificarCredenciales(String usuario, String contraseña) {
            String hashGuardado = usuarios.get(usuario);
            if (hashGuardado == null) {
                return false;
            }
            return BCrypt.checkpw(contraseña, hashGuardado);
        }

        // ===== Verificar permisos =====
        private boolean tienePermisoEliminar(String usuario) {
            String rol = roles.get(usuario);
            return rol != null && rol.equals("ADMIN");
        }

        // ===== Simulación del proveedor =====
        private static class PersonaProvider {

            static boolean EliminarPersona(String tabla, String uid) {
                // Simula eliminación exitosa
                System.out.println("Persona con UID " + uid + " eliminada de tabla " + tabla);
                return true;
            }
        }

        // ====== TEST: Acceso NO autorizado ======
        @Test
        @DisplayName("PS-004: Acceso no autorizado al eliminar persona")
        public void testEliminarPersonaNoAutorizado() {
            String uid = "9867";
            String usuario = "Cliente";
            String contraseña = "12345";

            // Verificación de credenciales
            boolean autenticado = verificarCredenciales(usuario, contraseña);
            boolean autorizado = tienePermisoEliminar(usuario);

            boolean resultadoEliminacion;

            if (autenticado && autorizado) {
                resultadoEliminacion = PersonaProvider.EliminarPersona("Persona", uid);
            } else {
                resultadoEliminacion = false;
            }

            // Resultado esperado: no se permite eliminar
            Assertions.assertFalse(resultadoEliminacion,
                    "Error: se permitió eliminar con usuario no autorizado.");

            System.out.println("Correcto: el sistema bloqueó la eliminación para usuario no autorizado.");
        }

        // ====== TEST: Acceso AUTORIZADO (Admin) ======
        @Test
        @DisplayName("PS-005: Acceso autorizado (Admin) al eliminar persona")
        public void testEliminarPersonaAutorizado() {
            String uid = "9867";
            String usuario = "Admin";
            String contraseña = "12345";

            boolean autenticado = verificarCredenciales(usuario, contraseña);
            boolean autorizado = tienePermisoEliminar(usuario);

            boolean resultadoEliminacion;

            if (autenticado && autorizado) {
                resultadoEliminacion = PersonaProvider.EliminarPersona("Persona", uid);
            } else {
                resultadoEliminacion = false;
            }

            // Resultado esperado: debe permitir la eliminación
            Assertions.assertTrue(resultadoEliminacion,
                    "Error: el administrador no pudo eliminar.");

            System.out.println(" Correcto: el administrador pudo eliminar exitosamente.");
        }
    }

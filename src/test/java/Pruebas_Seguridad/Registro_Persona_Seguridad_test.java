/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Seguridad;

import CapaDatos.Logica_Conexion.PersonaDAO;
import CapaLogicaNegocio.Helper.HelperValidacion;
import CapaLogicaNegocio.Logica_Negocio.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Registro_Persona_Seguridad_test {

    @Test
    @DisplayName("PS-003 - Intento de inyección SQL al registrar persona (OWASP)")
    public void testInyeccionSQLNoEliminaTabla() {
        PersonaDAO dao = new PersonaDAO();

        // Datos maliciosos simulando inyección
        String nombreMalicioso = "Daniel'); DROP TABLE persona; --";
        Persona p = new Persona(
                nombreMalicioso,
                "Pérez",
                "cra 7",
                "108978936",
                "ComputadorAcer,34345f",
                "9056",
                "image1"
        );

        try {
            // Paso 1: Validación de entrada (OWASP Input Validation)
            int caracteres = HelperValidacion.RetornarValorCE(nombreMalicioso);
            assertTrue(caracteres > 0, 
                "El HelperValidacion debe detectar caracteres especiales en el nombre.");

            if (caracteres > 0) {
                System.out.println("[Bloqueado] Entrada maliciosa detectada. Registro cancelado.");
            } else {
                int resultado = dao.add(p);
                fail("El sistema no debió permitir registrar una persona con datos maliciosos. Resultado: " + resultado);
            }

            //Paso 2: Verificar que la tabla persona sigue existiendo (OWASP A03:2021 Injection)
            try (Connection con = CapaDatos.Logica_Conexion.Conexion.getConnection();
                 PreparedStatement ps = con.prepareStatement("SHOW TABLES LIKE 'persona'");
                 ResultSet rs = ps.executeQuery()) {

                boolean existe = rs.next();
                assertTrue(existe, "La tabla 'persona' debe seguir existiendo. No debe haberse eliminado.");
            }

            //Paso 3: Verificar que no se guardó la persona
            Persona personaRecuperada = dao.getPersona("9056");
            assertNull(personaRecuperada, 
                "La persona con datos maliciosos no debe haberse guardado en la base de datos.");

        } catch (Exception e) {
            fail("Error inesperado durante la prueba de seguridad: " + e.getMessage());
        }
    }
}


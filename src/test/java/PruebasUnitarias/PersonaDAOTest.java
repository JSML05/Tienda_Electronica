/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebasUnitarias;

/**
 *
 * @author Sebastian
 */
import CapaDatos.Logica_Conexion.PersonaDAO;
import CapaLogicaNegocio.Logica_Negocio.Persona;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class PersonaDAOTest {

    private static PersonaDAO personaDAO;

    @BeforeAll
    static void setUp() {
        personaDAO = new PersonaDAO();
    }

    @Test
    void testEliminarPersonaPorUid() throws SQLException {
        // --- Preparación ---
        // Creamos una persona para garantizar que exista en la BD
        Persona persona = new Persona(
                "Juan",
                "Pérez",
                "Cra 22A # 2-79",
                "12002312333",
                "Consola PS5",
                "65434",   
                "Image3"
        );

        int insertados = personaDAO.add(persona);
        assertEquals(1, insertados, "Debe insertar correctamente la persona antes de eliminar");

        // --- Ejecución ---
        int eliminado = personaDAO.delete("65434");

        // --- Verificación ---
        assertEquals(1, eliminado, "Debe eliminar exactamente un registro con el UID");

        Persona eliminada = personaDAO.getPersona("65434");
        assertNull(eliminada, "La persona eliminada no debe existir en la base de datos");

        // --- Mensaje esperado ---
        System.out.println("Persona eliminada con Uid 65434");
    }
     @Test
    void testEliminarPersonaPorUid_NoExistente() throws SQLException {
        // Act: intentar eliminar una persona que no existe
        int eliminado = personaDAO.delete("00000");

        // Assert: no debería eliminar nada
        assertEquals(0, eliminado, "No debe eliminar nada si el UID no existe");
    }
}


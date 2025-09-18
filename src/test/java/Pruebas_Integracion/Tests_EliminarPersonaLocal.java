/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Integracion;

import CapaDatos.Logica_Conexion.Conexion;
import CapaDatos.Logica_Conexion.PersonaDAO;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Sebastian Manquillo
 */
public class Tests_EliminarPersonaLocal {
    
    private PersonaDAO personaDAO; // PersonaDAO para interactuar con la base de datos
    
    /**
     * Configuración inicial antes de cada prueba.
     * Este método se ejecuta antes de cada test para preparar el entorno de prueba.
     * @throws SQLException Si ocurre un error al establecer la conexión o insertar datos
     */
    @BeforeEach
    public void setUp() throws SQLException {
        // Establecer conexión con la base de datos
        PersonaDAO.con = Conexion.getConnection();
        personaDAO = new PersonaDAO();
        
        // Limpiar datos de prueba anteriores para evitar interferencias
        limpiarDatosPrueba();
        
        // Insertar datos de prueba
        insertarDatoPrueba("TEST_001", "Juan", "Lara");
        insertarDatoPrueba("TEST_002", "Maria", "Muñoz");
    }
    
    /**
     * Limpieza después de cada prueba.
     * Este método se ejecuta después de cada test para eliminar los datos de prueba
     * y mantener la base de datos en un estado consistente.
     * @throws SQLException Si ocurre un error al limpiar los datos
     */
    @AfterEach
    public void tearDown() throws SQLException {
        // Limpiar datos de prueba después de cada test
        limpiarDatosPrueba();
    }
    
    /**
     * Prueba para verificar el estado de la conexión a la base de datos.
     * Este test no realiza validaciones pero proporciona información útil para ejecutar los test.
     * @throws SQLException Si ocurre un error al acceder a la conexión
     */
    @Test
    public void testDebugConexion() throws SQLException {
        System.out.println("Conexión: " + (PersonaDAO.con != null ? "ACTIVA" : "NULA"));
        System.out.println("AutoCommit: " + PersonaDAO.con.getAutoCommit());
        System.out.println("Cerrada: " + PersonaDAO.con.isClosed());
        System.out.println("Base de datos: " + PersonaDAO.con.getCatalog());
    }
    
    /**
     * Prueba para verificar la eliminación exitosa de una persona existente.
     * Este test valida que el método delete() de la clase PersonaDao elimine correctamente un registro
     * cuando se proporciona un UID válido que existe en la base de datos.
     * @throws SQLException Si ocurre un error durante la operación de eliminación
     */
    @Test
    public void testEliminarPersonaExistente() throws SQLException {
        // Ejecución: Intentar eliminar la persona con UID "TEST_001"
        int filasAfectadas = personaDAO.delete("TEST_001");
        
        // Verificación: Debería haberse eliminado exactamente 1 fila
        assertEquals(1, filasAfectadas, "Debería eliminar 1 fila");
    }
    
    /**
     * Prueba para verificar el comportamiento al intentar eliminar una persona que no existe.
     * Este test valida que el método delete() de la clase PersonaDAO no afecte ningún registro cuando se proporciona
     * un UID que no existe en la base de datos.
     * @throws SQLException Si ocurre un error durante la operación de eliminación
     */
    @Test
    public void testEliminarPersonaInexistente() throws SQLException {
        // Ejecución: Intentar eliminar una persona con un UID que no existe
        int filasAfectadas = personaDAO.delete("TEST_NO_EXISTE");
        
        // Verificación: No debería eliminarse ninguna fila
        assertEquals(0, filasAfectadas, "No debería eliminar ninguna fila");
    }
    
     /**
     * Prueba para verificar el comportamiento al intentar eliminar con un UID nulo.
     * Este test valida que el método delete() de la clase PersonaDAO maneje adecuadamente el valor nulo
     * como parámetro de entrada.
     * @throws SQLException Si ocurre un error durante la operación de eliminación
     */
    @Test
    public void testEliminarPersonaConUidNulo() throws SQLException {
        // Ejecución: Intentar eliminar con un UID nulo
        int filasAfectadas = personaDAO.delete(null);
        
        // Verificación: No debería eliminarse ninguna fila cuando el UID es nulo
        assertEquals(0, filasAfectadas, "No debería eliminar ninguna fila con UID nulo");
    }
    
    // Métodos auxiliares
     /**
     * Limpia todos los datos de prueba de la base de datos.
     * Elimina todos los registros de la tabla persona cuyo UID comienza con "TEST_".
     */
    private void limpiarDatosPrueba() throws SQLException {
        try (Statement stmt = PersonaDAO.con.createStatement()) {
            stmt.executeUpdate("DELETE FROM persona WHERE Uid LIKE 'TEST_%'");
        }
    }
    
    /**
     * Inserta un registro de prueba en la base de datos.
     * Crea una persona con datos básicos y algunos valores predeterminados para pruebas.
     */
    private void insertarDatoPrueba(String uid, String nombre, String apellido) throws SQLException {
        try (Statement stmt = PersonaDAO.con.createStatement()) {
            String sql = String.format(
                "INSERT INTO persona (Uid, Nombre, Apellido, Direccion, Cedula, Producto, Nom_img) " +
                "VALUES ('%s', '%s', '%s', 'Direccion prueba', '12345678', 'producto1,test,123', 'test.jpg')",
                uid, nombre, apellido
            );
            stmt.executeUpdate(sql);
        }
    }
    
}

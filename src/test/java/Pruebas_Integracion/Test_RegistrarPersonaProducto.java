/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Integracion;

import CapaDatos.Logica_Conexion.Conexion;
import CapaDatos.Logica_Conexion.PersonaDAO;
import CapaLogicaNegocio.Logica_Negocio.Persona;
import CapaLogicaNegocio.Logica_Negocio.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Sebastian Manquillo
 */
public class Test_RegistrarPersonaProducto {
    
    private PersonaDAO personaDAO; // PersonaDAO para interactuar con la base de datos
    
     /**
     * Configuración inicial antes de cada prueba.
     * Este método se ejecuta antes de cada test para preparar el entorno de prueba.
     * @throws SQLException Si ocurre un error al establecer la conexión
     */
    @BeforeEach
    public void setUp() throws SQLException {
        // Establecer conexión con la base de datos
        PersonaDAO.con = Conexion.getConnection();
        personaDAO = new PersonaDAO();
        
        // Limpiar datos de prueba anteriores para evitar interferencias
        limpiarDatosPrueba();
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
     * Prueba para verificar el registro exitoso de una persona con productos asociados.
     * Este test valida que el método add() de la clase PersonaDAO inserte correctamente un registro de persona
     * con una lista de productos convertida a string.
     * @throws SQLException Si ocurre un error durante la operación de inserción
     */
    @Test
    public void testRegistroPersonaConProductos() throws SQLException {
        // Configuración: Crear lista de productos de prueba
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Laptop", "Dell", "DL12345XYZ"));
        productos.add(new Producto("Celular", "Samsung", "SM67890ABC"));
        
        // Crear objeto Persona con datos de prueba
        Persona persona = new Persona();
        persona.setUid("TEST_NUEVO_001");
        persona.setNombre("Carlos");
        persona.setApellido("Manquillo");
        persona.setDireccion("Calle123");
        persona.setCedula("87654321");
        persona.setProducto(convertirProductosAString(productos)); // Convertir productos a string
        persona.setNom_img("image8.jpg");
        
        // Ejecución: Insertar persona en la base de datos
        int resultado = personaDAO.add(persona);
        
        // Verificación: Debería haberse insertado exactamente 1 fila
        assertEquals(1, resultado, "Debería insertar 1 fila");
        
        // Verificación adicional: Comprobar que los datos se pueden recuperar correctamente
        Persona personaRecuperada = personaDAO.getPersona("TEST_NUEVO_001");
        assertNotNull(personaRecuperada, "La persona debería existir en la BD");
        assertEquals("Carlos", personaRecuperada.getNombre(), "Nombre debería coincidir");
        assertEquals("Manquillo", personaRecuperada.getApellido(), "Apellido debería coincidir");
    }
    
    /**
     * Prueba para verificar el registro exitoso de una persona sin productos asociados.
     * Este test valida que el método add() de la clase PersonaDAO funcione correctamente cuando no se proporcionan
     * productos (cadena vacía).
     * @throws SQLException Si ocurre un error durante la operación de inserción
     */
    @Test
    public void testRegistroPersonaSinProductos() throws SQLException {
        // Configuración: Crear persona sin productos
        Persona persona = new Persona();
        persona.setUid("TEST_NUEVO_002");
        persona.setNombre("Ana");
        persona.setApellido("Torres");
        persona.setDireccion("Avenida Test 456");
        persona.setCedula("11223344C");
        persona.setProducto("");
        persona.setNom_img("foto_ana.jpg");
        
        // Ejecución: Insertar persona en la base de datos
        int resultado = personaDAO.add(persona);
        
        // Verificación: Debería haberse insertado exactamente 1 fila
        assertEquals(1, resultado, "Debería insertar 1 fila");
        
        // Verificación adicional: Comprobar que los datos se pueden recuperar correctamente
        Persona personaRecuperada = personaDAO.getPersona("TEST_NUEVO_002");
        assertNotNull(personaRecuperada, "La persona debería existir");
        assertEquals("Ana", personaRecuperada.getNombre());
    }
    
        /**
     * Prueba para verificar el manejo adecuado de intentos de registro con UID duplicado.
     * Este test valida que el sistema lance una excepción SQLException cuando se intenta
     * insertar una persona con un UID que ya existe en la base de datos.
     * @throws SQLException Si ocurre un error durante la operación de inserción
     */
    @Test
    public void testRegistroPersonaConUidDuplicado() throws SQLException {
        // Primero insertamos una persona con un UID específico
        Persona persona1 = crearPersonaBasica("TEST_DUPLICADO", "Juan", "Duplicado");
        personaDAO.add(persona1);
        
        // Intentamos insertar otra con el mismo UID
        Persona persona2 = crearPersonaBasica("TEST_DUPLICADO", "Maria", "Duplicado");
        
        // Verificación: Debería lanzarse una excepción por violación de constraint única
        SQLException exception = assertThrows(SQLException.class, () -> {
            personaDAO.add(persona2);
        });
        
        // Verificación adicional: La excepción debería contener información sobre duplicado
        assertTrue(exception.getMessage().contains("Duplicate"), 
                  "Debería ser error de constraint única");
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
    
    // Crea un objeto Persona con datos básicos para pruebas.
    private Persona crearPersonaBasica(String uid, String nombre, String apellido) {
        Persona persona = new Persona();
        persona.setUid(uid);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDireccion("Direccion " + uid);
        persona.setCedula("CEDULA_" + uid);
        persona.setProducto("producto_test");
        persona.setNom_img("img_" + uid + ".jpg");
        return persona;
    }
    
     /**
     * Convierte una lista de objetos Producto a una cadena de texto con formato específico.
     * El formato resultante es: "nombre,marca,serial;nombre2,marca2,serial2"
     */
    private String convertirProductosAString(ArrayList<Producto> productos) {
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            if (sb.length() > 0) {
                sb.append(";"); // Separador entre productos
            }
            sb.append(producto.getNombre())
              .append(",") // Separador entre campos del producto
              .append(producto.getMarca())
              .append(",")
              .append(producto.getSerial());
        }
        return sb.toString();
    }
}

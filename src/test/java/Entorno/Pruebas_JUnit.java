/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entorno;

import CapaLogicaNegocio.Helper.HelperValidacion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Santiago Lopez
 */
public class Pruebas_JUnit {

    @Test
    public void testCadenaVacia() {
        String input = "";
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(1, resultado); // 1 (vacía) + 0 (no hay especiales) + 0 (no hay letras)
    }

    @Test
    public void testSoloLetras() {
        String input = "HolaMundo";
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(0 + 0 + 9, resultado); // 0 (no vacía) + 0 (ningún especial) + 9 letras
    }

    @Test
    public void testConCaracterEspecial() {
        String input = "Hola@mundo";
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        // Letras: 9, Especial: 1 ('@'), Vacío: 0
        assertEquals(0 + 1 + 9, resultado);
    }

    @Test
    public void testConVariosEspeciales() {
        String input = "Hola!mundo+.";
        // Letras: 9, Especiales: 3 (! + .)
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(0 + 3 + 9, resultado);
    }

    @Test
    public void testConNumeroYNoEspecial() {
        String input = "Hola123";
        // Letras: 4, Especiales: 0 (los números no están en la lista), Vacío: 0
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(0 + 0 + 4, resultado);
    }

    @Test
    public void testSoloCaracteresEspeciales() {
        String input = "@+;.";
        // Letras: 0, Especiales: 4
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(0 + 4 + 0, resultado);
    }

    @Test
    public void testConEspaciosYLetras() {
        String input = "Hola mundo";
        // Letras: 9 (espacios no cuentan), Especiales: 0
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(0 + 0 + 9, resultado);
    }

    @Test
    public void testSoloCaracteresEspecialesIncorrecto() {
        String input = "@+;."; // Sigue teniendo 4 caracteres especiales
        int resultado = HelperValidacion.ValidarTodoLetra(input);
        assertEquals(5, resultado); // Aquí está el error: esperamos 5, pero es 4 realmente
    }
    
    @Test
    public void testConTodos() {
        String input = "@Hola123: mundo!";
        int resultado = HelperValidacion.ValidarTodo(input);
        // CE: '@', ':', ' ', '!' → 4
        // Números: 1, 2, 3 → 3
        // Vacía: no → 0
        assertEquals( + 3 + 4, resultado);
    }

}

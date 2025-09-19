/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebasUnitarias;

/**
 *
 * @author Sebastian
 */
// Archivo: HelperValidacionTest.java
import CapaLogicaNegocio.Helper.HelperValidacion;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelperValidacionTest {

     // ---------- PRUEBAS PARA ValidarVacio ----------
    @Test
    void testValidarVacio_CadenaVacia() {
        assertEquals(1, HelperValidacion.ValidarVacio(""),
                "Debe retornar 1 cuando la cadena está vacía");
    }

    @Test
    void testValidarVacio_CadenaNoVacia() {
        assertEquals(0, HelperValidacion.ValidarVacio("Texto"),
                "Debe retornar 0 cuando la cadena NO está vacía");
    }

    // ---------- PRUEBAS PARA RetornaValor (cuenta números) ----------
    @Test
    void testRetornaValor_ConNumeros() {
        assertEquals(3, HelperValidacion.RetornaValor("Juan123"),
                "Debe contar 3 números en la cadena");
    }

    @Test
    void testRetornaValor_SinNumeros() {
        assertEquals(0, HelperValidacion.RetornaValor("Juan"),
                "Debe retornar 0 cuando no hay números");
    }

    // ---------- PRUEBAS PARA RetornarValorCEV2 (caracteres especiales) ----------
    @Test
    void testRetornarValorCEV2_ConEspeciales() {
        assertTrue(HelperValidacion.RetornarValorCEV2("Cra 22A # 2-79") >0,
                "Debe encontrar caracteres especiales en la dirección");
    }

    @Test
    void testRetornarValorCEV2_SinEspeciales() {
        assertEquals(0, HelperValidacion.RetornarValorCEV2("DireccionNormal"),
                "No debe detectar caracteres especiales en texto normal");
    }

    // ---------- PRUEBAS PARA ValidarTodo ----------
    @Test
    void testValidarTodo_CadenaValida() {
        assertTrue(HelperValidacion.ValidarTodo("Juan") > 0,
                "Debe retornar > 0 porque hay letras válidas");
    }

    @Test
    void testValidarTodo_CadenaInvalida() {
        assertTrue(HelperValidacion.ValidarTodo("Juan12@") > 1,
                "Debe retornar > 1 porque tiene números y caracteres especiales");
    }

    @Test
    void testValidarTodo_CadenaVacia() {
        assertEquals(1, HelperValidacion.ValidarTodo(""),
                "Debe retornar 1 cuando la cadena está vacía");
    }
}


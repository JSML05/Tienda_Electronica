/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Usabilidad;

/**
 *
 * @author Sebastian Manquillo
 */
public class LogicaSUS {
    
    // Metodo para calcular el puntaje SUS
    public static double calcularSUS(int[] respuestas) {
    if (respuestas.length != 10) throw new IllegalArgumentException("Debe haber 10 respuestas");

    double suma = 0;
    for (int i = 0; i < respuestas.length; i++) {
        int valor = respuestas[i];
        if (i % 2 == 0) {
            // Pregunta positiva: resta 1
            suma += (valor - 1);
        } else {
            // Pregunta negativa: resta de 5
            suma += (5 - valor);
        }
    }
    return suma * 2.5; // Escala a 0–100
}
}

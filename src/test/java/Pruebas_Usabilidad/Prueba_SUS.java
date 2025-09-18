/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas_Usabilidad;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebastian Manquillo
 */
public class Prueba_SUS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] preguntas = {
            "1. Me gustaria usar este sistema con frecuencia.",
            "2. Encontre el sistema innecesariamente complejo.",
            "3. Pense que el sistema era facil de usar.",
            "4. Creo que necesitaria ayuda de una persona tecnica para usar el sistema.",
            "5. Las funciones del sistema estaban bien integradas.",
            "6. Hubo demasiada inconsistencia en el sistema.",
            "7. Imagino que la mayoria de las personas aprenderian a usar este sistema muy rapido.",
            "8. Encontre el sistema muy engorroso de usar.",
            "9. Me senti muy confiado usando el sistema.",
            "10. Necesite aprender muchas cosas antes de poder usar el sistema."
        };

        int[] respuestas = new int[10];

        System.out.println("===== CUESTIONARIO SUS =====");
        System.out.println("Responda cada pregunta con un numero del 1 al 5:");
        System.out.println("1 = Totalmente en desacuerdo, 5 = Totalmente de acuerdo\n");

        for (int i = 0; i < preguntas.length; i++) {
            int respuesta = 0;
            do {
                System.out.print(preguntas[i] + " (1-5): ");
                while (!scan.hasNextInt()) {
                    System.out.print("Por favor, ingrese un número entre 1 y 5: ");
                    scan.next();
                }
                respuesta = scan.nextInt();
            } while (respuesta < 1 || respuesta > 5);

            respuestas[i] = respuesta;
        }
        
        double puntajeSUS;
        puntajeSUS = LogicaSUS.calcularSUS(respuestas);
        
        System.out.println("\n===== RESULTADO =====");
        System.out.printf("El puntaje SUS del sistema es: " + puntajeSUS + "\n");

        if (puntajeSUS >= 68) {
            System.out.println("Usabilidad aceptable.");
        } else {
            System.out.println("Usabilidad por debajo del promedio.");
        }


    }
    
}

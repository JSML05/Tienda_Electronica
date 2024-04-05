/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import java.util.ArrayList;

/**
 *
 * @author jsml
 */
public class HelperValidacion {

    //Validar numeros en una cadena de texto
    public static int RetornaValor(String Texto) {
        int numero = 0;
        int letra = 0;

        char[] ch = new char[Texto.length()];
        for (int i = 0; i < Texto.length(); i++) {
            ch[i] = Texto.charAt(i);
            Boolean flag = Character.isDigit(ch[i]);
            if (flag) {
                numero++;
            } else {
                letra++;
            }
        }

        return numero;
    }

    //Validar caracteres especiales en una cadena de texto
    public static int RetornarValorCE(String Texto) {
        int Caracter = 0;
        ArrayList<Character> Lista_caracter = new ArrayList<>();

        Lista_caracter.add('@');
        Lista_caracter.add('-');
        Lista_caracter.add('/');
        Lista_caracter.add(';');
        Lista_caracter.add(':');
        Lista_caracter.add('"');
        Lista_caracter.add('!');
        Lista_caracter.add(' ');
        

        for (int i = 0; i < Texto.length(); i++) {
            boolean flag = Character.isLetter(Texto.charAt(i));
            if (!flag) {
                for (int j = 0; j < Lista_caracter.size(); j++) {
                    if (Lista_caracter.get(j).compareTo(Texto.charAt(i)) == 0) {
                        Caracter++;
                    }
                }
            }
        }

        return Caracter;
    }

    //Validar letras en una cadena de texto
    public static int RetornarValorLetra(String Texto) {
        int Letra = 0;

        for (int i = 0; i < Texto.length(); i++) {
            char caracter = Texto.charAt(i);
            if (Character.isLetter(caracter)) {
                Letra++;
            }
        }
        return Letra;
    }

    //Validar caracteres especiales en el campo direccion
    public static int RetornarValorCE2(String Texto) {
        int Caracter = 0;
        ArrayList<Character> Lista_caracter = new ArrayList<>();

        Lista_caracter.add('@');
        Lista_caracter.add(';');
        Lista_caracter.add(',');
        Lista_caracter.add('.');
        Lista_caracter.add('+');
        Lista_caracter.add('_');
        Lista_caracter.add('*');
        Lista_caracter.add('~');
        Lista_caracter.add('/');
        Lista_caracter.add('"');
        Lista_caracter.add(':');
        Lista_caracter.add('!');
        Lista_caracter.add('=');

        for (int i = 0; i < Texto.length(); i++) {
            boolean flag = Character.isLetter(Texto.charAt(i));
            if (!flag) {
                for (int j = 0; j < Lista_caracter.size(); j++) {
                    if (Lista_caracter.get(j).compareTo(Texto.charAt(i)) == 0) {
                        Caracter++;
                    }
                }
            }
        }

        return Caracter;
    }

    //Validar vacio
    public static boolean ValidarVacio(String cadena) {
        if (cadena.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}

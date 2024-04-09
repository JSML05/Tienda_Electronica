/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Logica_Cliente;

import Helpers.HelperValidacion;
import Logica_Conexion.Conexion;
import Logica_Negocio.Persona;
import Logica_Negocio.Producto;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jsml
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Conexion.Conectar();
        //Declaracion de variables
        //Persona
        String Nombre = "", Apellido = "", Direccion = "", Cedula = "", Producto = "", Uid = "", Nom_img = "";

        //Producto
        String NombreProducto = "", Marca = "", Serial = "";
        int Num_pro = 0;
        String producto = "";

        //Funciones
        int retorno_num = 0, retorno_ce = 0, retorno_letra = 0, retorno_ce2 = 0, retorno_ced = 0, retorno_vac = 0;

        Scanner scan = new Scanner(System.in);
        int opc = 0;
        ArrayList<Persona> Lista_personas = new ArrayList<>();
        ArrayList<Producto> ProductosGlobal = null;
        Persona objPersona;
        Producto objProducto;

        do {
            System.out.println("Opciones: ");
            System.out.println("1. Registrar persona");
            System.out.println("2. Mostrar los datos de una persona y los computadores que posee");
            System.out.println("3. Mostrar toda la lista de personas");
            System.out.println("4. Salir");

            do {
                try {

                    System.out.println("Digite la opcion");
                    opc = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException e) {

                    System.out.println("Opcion no valida ");
                    scan.next();

                    break;
                }
            } while (opc <= 0);

            switch (opc) {
                case 1:
                    //Registrar
                    //Nombre
                    System.out.println("Digite el nombre de la persona: ");
                    Nombre = scan.nextLine();

                    retorno_vac = HelperValidacion.ValidarVacio(Nombre);
                    while (retorno_vac != 0) {
                        System.out.println("Digite el nombre de la persona: ");
                        Nombre = scan.nextLine();
                        retorno_vac = HelperValidacion.ValidarVacio(Nombre);
                    }

                    retorno_vac = HelperValidacion.ValidarVacio(Nombre);
                    retorno_num = HelperValidacion.RetornaValor(Nombre);
                    retorno_ce = HelperValidacion.RetornarValorCEV2(Nombre);

                    while (retorno_num != 0 || retorno_ce != 0 || retorno_vac != 0) {
                        System.out.println("Digite el nombre de la persona: ");
                        Nombre = scan.nextLine();

                        retorno_vac = HelperValidacion.ValidarVacio(Nombre);
                        retorno_num = HelperValidacion.RetornaValor(Nombre);
                        retorno_ce = HelperValidacion.RetornarValorCEV2(Nombre);
                    }
                    //Fin nombre

                    //Apellido
                    System.out.println("Digite el apellido de la persona: ");
                    Apellido = scan.nextLine();

                    retorno_vac = HelperValidacion.ValidarVacio(Apellido);
                    while (retorno_vac != 0) {
                        System.out.println("Digite el apellido de la persona: ");
                        Apellido = scan.nextLine();
                        retorno_vac = HelperValidacion.ValidarVacio(Apellido);
                    }

                    retorno_vac = HelperValidacion.ValidarVacio(Apellido);
                    retorno_num = HelperValidacion.RetornaValor(Apellido);
                    retorno_ce = HelperValidacion.RetornarValorCEV2(Apellido);

                    while (retorno_num != 0 || retorno_ce != 0 || retorno_vac != 0) {
                        System.out.println("Digite el apellido de la persona: ");
                        Apellido = scan.nextLine();

                        retorno_vac = HelperValidacion.ValidarVacio(Apellido);
                        retorno_num = HelperValidacion.RetornaValor(Apellido);
                        retorno_ce = HelperValidacion.RetornarValorCEV2(Apellido);
                    }
                    //Fin apellido

                    //Cedula
                    System.out.println("Digite el numero de cedula de la persona: ");
                    Cedula = scan.nextLine();

                    retorno_vac = HelperValidacion.ValidarVacio(Cedula);
                    while (retorno_vac != 0) {
                        System.out.println("Digite el numero de cedula de la persona: ");
                        Cedula = scan.nextLine();
                        retorno_vac = HelperValidacion.ValidarVacio(Cedula);
                    }

                    retorno_vac = HelperValidacion.ValidarVacio(Cedula);
                    retorno_letra = HelperValidacion.RetornarValorLetra(Cedula);
                    retorno_ce = HelperValidacion.RetornarValorCEV2(Cedula);
                    //retorno_ced = Persona.VerificarCedula(Lista_personas, Cedula);

                    while (retorno_letra != 0 || retorno_ce != 0 || retorno_ced != 0 || retorno_vac != 0) {
                        if (retorno_ced != 0) {
                            System.out.println("El numero de cedula ingresado ya se encuentra registrado");
                        }

                        System.out.println("Digite el numero de cedula de la persona: ");
                        Cedula = scan.nextLine();

                        retorno_vac = HelperValidacion.ValidarVacio(Cedula);
                        retorno_letra = HelperValidacion.RetornarValorLetra(Cedula);
                        retorno_ce = HelperValidacion.RetornarValorCEV2(Cedula);
                        //retorno_ced = Persona.VerificarCedula(Lista_personas, Cedula);
                    }
                    //Fin cedula

                    //Direccion
                    System.out.println("Digite la direccion de la persona: ");
                    Direccion = scan.nextLine();

                    retorno_vac = HelperValidacion.ValidarVacio(Direccion);
                    while (retorno_vac != 0) {
                        System.out.println("Digite la direccion de la persona: ");
                        Direccion = scan.nextLine();
                        retorno_vac = HelperValidacion.ValidarVacio(Direccion);
                    }

                    retorno_vac = HelperValidacion.ValidarVacio(Direccion);
                    retorno_ce2 = HelperValidacion.RetornarCEDireccionV2(Direccion);

                    while (retorno_ce2 != 0 || retorno_vac != 0) {
                        System.out.println("Digite la direccion de la persona: ");
                        Direccion = scan.nextLine();

                        retorno_ce2 = HelperValidacion.RetornarCEDireccionV2(Direccion);
                        retorno_vac = HelperValidacion.ValidarVacio(Direccion);
                    }
                    //Fin direccion

                    //Inicio imagen
                    System.out.println("Digite el nombre de la imagen de la persona: ");
                    Nom_img = scan.nextLine();

                    retorno_vac = HelperValidacion.ValidarVacio(Nom_img);
                    while (retorno_vac != 0) {
                        System.out.println("Digite el nombre de la imagen de la persona: ");
                        Nom_img = scan.nextLine();
                        retorno_vac = HelperValidacion.ValidarVacio(Nom_img);
                    }

                    retorno_vac = HelperValidacion.ValidarVacio(Nom_img);
                    retorno_ce2 = HelperValidacion.RetornarCEDireccionV2(Nom_img);

                    while (retorno_ce2 != 0 || retorno_vac != 0) {
                        System.out.println("Digite el nombre de la imagen de la persona: ");
                        Nom_img = scan.nextLine();

                        retorno_ce2 = HelperValidacion.RetornarCEDireccionV2(Nom_img);
                        retorno_vac = HelperValidacion.ValidarVacio(Nom_img);
                    }

                    ArrayList<Producto> Productos = new ArrayList<>();

                    do {
                        try {
                            System.out.println("Numero de productos que se desea añadirle: ");
                            Num_pro = scan.nextInt();

                        } catch (InputMismatchException e) {
                            System.out.println("Error");
                        }
                        scan.nextLine();
                    } while (Num_pro <= 0);

                    int retorno_can = HelperValidacion.ValidarCantidadRango(Num_pro);

                    //Creacion de la lista de computadores
                    if (retorno_can == 1) {
                        for (int i = 0; i < Num_pro; i++) {

                            //Nombre
                            System.out.println("Digite el nombre del producto " + (i + 1) + " :");
                            NombreProducto = scan.nextLine();

                            retorno_vac = HelperValidacion.ValidarVacio(NombreProducto);
                            while (retorno_vac != 0) {
                                System.out.println("Digite el nombre del producto " + (i + 1) + " :");
                                NombreProducto = scan.nextLine();
                                retorno_vac = HelperValidacion.ValidarVacio(NombreProducto);
                            }

                            retorno_vac = HelperValidacion.ValidarVacio(NombreProducto);
                            retorno_num = HelperValidacion.RetornaValor(NombreProducto);
                            retorno_ce = HelperValidacion.RetornarValorCEV2(NombreProducto);

                            while (retorno_num != 0 || retorno_ce != 0 || retorno_vac != 0) {
                                System.out.println("Digite el nombre del producto " + (i + 1) + " :");
                                NombreProducto = scan.nextLine();

                                retorno_vac = HelperValidacion.ValidarVacio(NombreProducto);
                                retorno_num = HelperValidacion.RetornaValor(NombreProducto);
                                retorno_ce = HelperValidacion.RetornarValorCEV2(NombreProducto);
                            }

                            //Marca
                            System.out.println("Digite la marca del producto " + (i + 1) + " :");
                            Marca = scan.nextLine();

                            retorno_vac = HelperValidacion.ValidarVacio(Marca);
                            while (retorno_vac != 0) {
                                System.out.println("Digite la marca del producto " + (i + 1) + " :");
                                Marca = scan.nextLine();
                                retorno_vac = HelperValidacion.ValidarVacio(Marca);
                            }

                            retorno_vac = HelperValidacion.ValidarVacio(Marca);
                            retorno_num = HelperValidacion.RetornaValor(Marca);
                            retorno_ce = HelperValidacion.RetornarValorCEV2(Marca);

                            while (retorno_num != 0 || retorno_ce != 0 || retorno_vac != 0) {
                                System.out.println("Digite la marca del producto " + (i + 1) + " :");
                                Marca = scan.nextLine();

                                retorno_vac = HelperValidacion.ValidarVacio(Marca);
                                retorno_num = HelperValidacion.RetornaValor(Marca);
                                retorno_ce = HelperValidacion.RetornarValorCEV2(Marca);
                            }
                            //fin marca

                            //Serial
                            System.out.println("Digite el serial del producto " + (i + 1) + " :");
                            Serial = scan.nextLine();

                            retorno_vac = HelperValidacion.ValidarVacio(Serial);
                            while (retorno_vac != 0) {
                                System.out.println("Digite el serial del producto " + (i + 1) + " :");
                                Serial = scan.nextLine();
                                retorno_vac = HelperValidacion.ValidarVacio(Serial);
                            }

                            retorno_vac = HelperValidacion.ValidarVacio(Serial);
                            retorno_ce = HelperValidacion.RetornarValorCEV2(Serial);

                            while (retorno_vac != 0 || retorno_ce != 0) {
                                System.out.println("Digite el serial del producto " + (i + 1) + " :");
                                Serial = scan.nextLine();

                                retorno_vac = HelperValidacion.ValidarVacio(Serial);
                                retorno_ce = HelperValidacion.RetornarValorCEV2(Serial);
                            }
                            //Fin serial
                            objProducto = new Producto(NombreProducto, Marca, Serial);
                            Productos.add(objProducto);
                        }
                        ProductosGlobal = Productos;
                        Productos = null;
                    } else {
                        System.out.println("Cantidad invalida");
                        break;
                    }
                    for (int i = 0; i < ProductosGlobal.size(); i++) {
                        producto += ProductosGlobal.get(i).getNombre() + "," + ProductosGlobal.get(i).getMarca() + "," + ProductosGlobal.get(i).getSerial() + ";";

                    }
                    int id = (int) (Math.random() * 100000);

                    objPersona = new Persona(Nombre, Apellido, Direccion, Cedula, producto, String.valueOf(id), Nom_img);
                    Lista_personas.add(objPersona);
                    objPersona.setProductos(Productos);
                    Helpers.HelperRegistro.RegistrarPersonaNube(objPersona, id, producto);
                    producto = "";
                    break;

                default:
                    throw new AssertionError();

                //Conexion.Conectar();
            }
        } while (opc != 6);

    }

}

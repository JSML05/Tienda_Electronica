/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogicaNegocio.Logica_Negocio;

import java.util.ArrayList;

/**
 *
 * @author jsml
 */
public class Persona {

    //Declaracion de atributos 
    public String Nombre, Apellido, Direccion, Cedula, Producto, Uid, Nom_img;
    public ArrayList<Producto> productos;

    //Constructor no parametrizado
    public Persona() {
    }

    //Constructor parametrizado
    public Persona(String Nombre, String Apellido, String Direccion, String Cedula, String Producto, String Uid, String Nom_img) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Cedula = Cedula;
        this.Producto = Producto;
        this.Uid = Uid;
        this.Nom_img = Nom_img;
    }

    //Setters y Getters
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

    public String getNom_img() {
        return Nom_img;
    }

    public void setNom_img(String Nom_img) {
        this.Nom_img = Nom_img;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

}

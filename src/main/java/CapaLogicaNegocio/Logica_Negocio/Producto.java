/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogicaNegocio.Logica_Negocio;

/**
 *
 * @author jsml
 */
public class Producto {

    //Declaracion de atributos 
    public String Nombre, Marca, Serial;

    //Constructor no parametrizado
    public Producto() {
    }

    //Constructor parametrizo
    public Producto(String Nombre, String Marca, String Serial) {
        this.Nombre = Nombre;
        this.Marca = Marca;
        this.Serial = Serial;
    }

    //Setters y getters

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }
    
}

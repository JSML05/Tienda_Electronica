/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogicaNegocio.Logica_Negocio;

/**
 *
 * @author jsml
 */
public abstract class Usuario {

    //Declaracion de atributos
    private String Usu, Contra;

    //Constructor parametrizado
    public Usuario(String Usu, String Contra) {
        this.Usu = Usu;
        this.Contra = Contra;
    }

    //Setter y getters
    public String getUsu() {
        return Usu;
    }

    public void setUsu(String Usu) {
        this.Usu = Usu;
    }

    public String getContra() {
        return Contra;
    }

    public void setContra(String Contra) {
        this.Contra = Contra;
    }

    public abstract boolean LogOn(String usuario, String contraseña);

}

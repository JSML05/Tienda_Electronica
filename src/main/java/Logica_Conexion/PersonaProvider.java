/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica_Conexion;

import Logica_Negocio.Persona;
import Logica_Negocio.Producto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;

import java.util.Map;

/**
 *
 * @author jsml
 */
public class PersonaProvider {

    CollectionReference refence; //establecer coneccion con el repositorio para poder agregar
    public static Firestore db; //para poder conectarme a la data base

    public static boolean GuardarPersona(String coleccion, String documento, Map<String, Object> data) {
        db = FirestoreClient.getFirestore();
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);//subir datos al archivo .json
            System.out.println("Guardado Correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return false;
    }

//consultar si hay un id repetido 
    public static boolean RetornarUid(String uid) {
        ArrayList<String> uids = new ArrayList<>();
        boolean rta = true;

        try {
            CollectionReference persona = Conexion.db.collection("Persona");
            ApiFuture<QuerySnapshot> querySnap = persona.get(); //navegar entre lo que tenemos doumentos-colecion

            for (DocumentSnapshot document : querySnap.get().getDocuments()) {
                uids.add(document.getString("uid"));
            }

            for (int i = 0; i < uids.size(); i++) {
                if (uid.equals(uids.get(i))) {
                    return rta;
                } else {
                    return !rta;
                }

            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return rta;

    }

    public static ArrayList CargarInfoPersona() {
        Persona objPer;
        Producto Produ;

        ArrayList<Persona> Lspersona = new ArrayList<>();
        ArrayList<Producto> Lsproducto = new ArrayList<>();

        try {
            CollectionReference persona = Conexion.db.collection("Persona");
            ApiFuture<QuerySnapshot> querySnap = persona.get();

            for (DocumentSnapshot document : querySnap.get().getDocuments()) {
                objPer = new Persona(document.getString("Nombre"), document.getString("Apellido"),
                        document.getString("Direccion"), document.getString("Cedula"),
                        document.getString("Productos"), document.getString("uid"),
                        document.getString("Nom_img"));
                Lspersona.add(objPer);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return Lspersona;
    }

    public static Persona CargarInfoPersonaCodigo(String codigo) {
        Persona objPer;
        Persona objPer1 = null;

        ArrayList<Persona> Lspersona = new ArrayList<>();
        ArrayList<Producto> Lsproducto = new ArrayList<>();

        try {
            CollectionReference persona = Conexion.db.collection("Persona");
            ApiFuture<QuerySnapshot> querySnap = persona.get();

            for (DocumentSnapshot document : querySnap.get().getDocuments()) {
                objPer = new Persona(document.getString("Nombre"), document.getString("Apellido"),
                        document.getString("Direccion"), document.getString("Cedula"),
                        document.getString("Producto"), document.getString("Uid"),
                        document.getString("Nom_img"));
                Lspersona.add(objPer);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (int i = 0; i < Lspersona.size(); i++) {
            if (codigo.equals(Lspersona.get(i).getUid())) {
                objPer1 = Lspersona.get(i);

            }

        }
        return objPer1;

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas_Rendimiento;

import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;

/**
 *
 * @author Sebastian Manquillo
 */
public class Generador_Token_Firebase {
    
    private static final String SERVICE_ACCOUNT_PATH = 
            "C:\\Users\\Lenovo\\OneDrive\\Documentos\\NetBeansProjects\\Tienda_Electronica\\tienda-electronica.json";

    public static String getAccessTokenFromServiceAccount() {
        try {
            FileInputStream serviceAccount = new FileInputStream(SERVICE_ACCOUNT_PATH);

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount)
                    .createScoped("https://www.googleapis.com/auth/datastore");

            credentials.refreshIfExpired();
            String token = credentials.getAccessToken().getTokenValue();
            return token;

        } catch (Exception e) {
            System.err.println("Error al obtener token de Firebase:");
            e.printStackTrace();
            return null;
        }
    }
}

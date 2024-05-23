/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_Cliente;

import GUI_Admin.*;
import Helpers.HelperImpresion;
import Logica_Conexion.Conexion;
import Logica_Conexion.PersonaProvider;
import Logica_Negocio.Persona;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Santiago Lopez
 */
public class BuscarPersonaCliente extends javax.swing.JFrame {

    /**
     * Creates new form BuscarPersona
     */
    ArrayList<Persona> lspersonasnube;
    Persona objper = null;
    String pathc;
    String pathc1;

    public String s;
    public String s1;
    public String osName = System.getProperty("os.name").toLowerCase();

    public BuscarPersonaCliente() {
        initComponents();
        System.out.println(osName);
        Path currentRelativePath = Paths.get("");
        if (osName.equals("linux")) {
            s = currentRelativePath.toAbsolutePath().toString();
            s1 = currentRelativePath.toAbsolutePath().toString();
            pathc1 = s1 + "//Images//" + "Background" + ".jpg";
            establecerImagenBack();

        } else if (osName.equals("windows 11")) {
            s = currentRelativePath.toAbsolutePath().toString();
            s1 = currentRelativePath.toAbsolutePath().toString();
            pathc1 = s1 + "\\Images\\" + "Background" + ".jpg";
            establecerImagenBack();

        }
        if (osName.equals(" windows 10")) {
            s = currentRelativePath.toAbsolutePath().toString();
            s1 = currentRelativePath.toAbsolutePath().toString();
            pathc1 = s1 + "\\Images\\" + "Background" + ".jpg";
            establecerImagenBack();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscar Persona");
        setPreferredSize(new java.awt.Dimension(400, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 121, 121, 121));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar Persona");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 14, -1, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 255, 204));
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 68, -1, -1));

        jScrollPane1.setViewportView(jTextPane1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, 236, 164));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 69, 130, -1));

        jButton1.setBackground(new java.awt.Color(0, 255, 204));
        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 254, -1, -1));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        lspersonasnube = PersonaProvider.CargarInfoPersona();
        String codigo = jTextField1.getText();
        objper = PersonaProvider.CargarInfoPersonaCodigo(codigo);
        if (objper == null) {
            jTextField1.setBorder(new LineBorder(Color.RED, 2));
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        } else {
            jTextField1.setBorder(new LineBorder(Color.BLACK, 1));
            String res = HelperImpresion.ImprimirInfoInterfazNube(lspersonasnube, codigo);
            jTextPane1.setText(res);
            pathc = s + "\\Images\\" + objper.getNom_img() + ".jpg";
            System.out.println(pathc);
            establecerImagen();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuCliente menu = new MenuCliente();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void establecerImagen() {

        Image img = null;
        try {
            File file = new File(pathc);
            img = ImageIO.read(new File(pathc));
            //5. Setear la imagen al JLabel
            jLabel3.setIcon(new ImageIcon(img));
        } catch (IOException ioexception) {
            System.err.println(ioexception);
        }
    }

    public void establecerImagenBack() {

        Image img = null;
        try {
            File file = new File(pathc1);
            img = ImageIO.read(new File(pathc1));
            //5. Setear la imagen al JLabel
            jLabel4.setIcon(new ImageIcon(img));
        } catch (IOException ioexception) {
            System.err.println(ioexception);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarPersonaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarPersonaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarPersonaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarPersonaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarPersonaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import util.ConexionMysql;

/**
 *
 * @author Rodo
 */
public class VentanaBaseDatos extends javax.swing.JFrame {
    // Variables de clase.
    private ConexionMysql conexionLocal;
    private String baseDatos;
    private JCheckBox[] jCheckBoxVariables;
    private String[] nombreVariables;
    private static String rutaImagenTemporal;
    
    /**
     * 
     * @param conexion 
     */
    public VentanaBaseDatos(ConexionMysql conexion, String rutaImagenTemporal) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        this.rutaImagenTemporal = rutaImagenTemporal;
        
        initComponents();
       
        /* 
         * Se guarda la conexion en una variable de clase, para luego usar
         * en toda la aplicacion.
         */
        conexionLocal = conexion;
        
        try{
            // Abrimos la conexion a la base de datos.
            conexionLocal.abrirConexion();
            
            // Recuperamos el nombre de la base de datos.
            baseDatos = conexionLocal.getBaseDatos();
        
            if(baseDatos.equals("")) {
                /*
                 * Si la base de datos no tiene nombre,
                 * entonces se cargan todas las base de datos del
                 * servidor mysql local.
                 */ 
                cargarBasesDatos();
            }else{
                // Se agrega en el combobox el nombre de la base de datos.
                jComboBoxBD.addItem(baseDatos);
                
                // Se cargan las tablas de un combobox.
                cargarTablas(baseDatos);
            }
        }catch(Exception e){
        }finally{
            if(conexionLocal != null){
                try{
                    // Se cierra la base de datos.
                    conexionLocal.terminarConexion();
                    
                    System.out.println("Se cerro la conexion a la base de datos local!!!");
                }catch(SQLException ex){   
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * 
     */
    public VentanaBaseDatos(String rutaImagenTemporal) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        this.rutaImagenTemporal = rutaImagenTemporal;
        
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelBasesDatos = new javax.swing.JPanel();
        jComboBoxBD = new javax.swing.JComboBox();
        jPanelTablas = new javax.swing.JPanel();
        jComboBoxTablas = new javax.swing.JComboBox();
        jPanelVariables = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Base de Datos locales");
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        jComboBoxBD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxBDItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBasesDatosLayout = new javax.swing.GroupLayout(jPanelBasesDatos);
        jPanelBasesDatos.setLayout(jPanelBasesDatosLayout);
        jPanelBasesDatosLayout.setHorizontalGroup(
            jPanelBasesDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBoxBD, 0, 129, Short.MAX_VALUE)
        );
        jPanelBasesDatosLayout.setVerticalGroup(
            jPanelBasesDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBasesDatosLayout.createSequentialGroup()
                .addComponent(jComboBoxBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jComboBoxTablas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTablasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelTablasLayout = new javax.swing.GroupLayout(jPanelTablas);
        jPanelTablas.setLayout(jPanelTablasLayout);
        jPanelTablasLayout.setHorizontalGroup(
            jPanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelTablasLayout.setVerticalGroup(
            jPanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablasLayout.createSequentialGroup()
                .addComponent(jComboBoxTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 292, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelVariablesLayout = new javax.swing.GroupLayout(jPanelVariables);
        jPanelVariables.setLayout(jPanelVariablesLayout);
        jPanelVariablesLayout.setHorizontalGroup(
            jPanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );
        jPanelVariablesLayout.setVerticalGroup(
            jPanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanelBasesDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBasesDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelVariables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Siguiente");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxBDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxBDItemStateChanged
        // TODO add your handling code here:
        baseDatos = jComboBoxBD.getSelectedItem().toString();
        cargarTablas(jComboBoxBD.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxBDItemStateChanged

    private void jComboBoxTablasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTablasItemStateChanged
        if(jComboBoxTablas.getSelectedItem()!=null) {
            cargarVariablesTabla(jComboBoxTablas.getSelectedItem().toString());
        }
        
    }//GEN-LAST:event_jComboBoxTablasItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nombreVariables = new String[jCheckBoxVariables.length];
      
        for(int x = 0; x < jCheckBoxVariables.length;x++){    
            if(jCheckBoxVariables[x].isSelected()){
                nombreVariables[x] = jCheckBoxVariables[x].getText();
                System.out.println(nombreVariables[x]);
            }
        }
        
        // Ir a la ventanaCrearTexto.
        VentanaCrearTexto ventanaCrearTexto = new VentanaCrearTexto(rutaImagenTemporal);
        ventanaCrearTexto.setLocationRelativeTo(this);
	ventanaCrearTexto.setVisible(true);
        
        // Se cierra la VentanaBaseDatos.
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaBaseDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBaseDatos(rutaImagenTemporal).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxBD;
    private javax.swing.JComboBox jComboBoxTablas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBasesDatos;
    private javax.swing.JPanel jPanelTablas;
    private javax.swing.JPanel jPanelVariables;
    // End of variables declaration//GEN-END:variables
    
   /**
    * Carga las baseDatos en un combobox de un servidor mysql.
    */
    private void cargarBasesDatos(){
        // Objetos.
        ResultSet ejecutarQueryBases =  null;
        
        try{ 
            ArrayList bases = new ArrayList();
            ejecutarQueryBases = conexionLocal.ejecutarQuery("Show Databases");
            
            while(ejecutarQueryBases.next()){
                bases.add(ejecutarQueryBases.getString(1));
                System.out.println(ejecutarQueryBases.getString(1));
            }
            for(int x = 0; x < bases.size(); x++) {
                jComboBoxBD.addItem(bases.get(x).toString());
            }
        }catch(SQLException ex){
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ejecutarQueryBases != null){
                try{
                    // Se cierra el ResultSet (ejecutarQueryBases).
                    ejecutarQueryBases.close();
                    
                    System.out.println("Se cierra el ResultSet de la base de datos!!!");
                }catch(SQLException ex){ 
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception ex){  
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Carga las tablas en un combobox de la BaseDatos.
     * @param BD 
     */
    private void cargarTablas(String BD) {
        // Objetos.
        ResultSet ejecutarQueryTablas = null;
        
        try {
            jComboBoxTablas.removeAllItems();
            ArrayList tablas = new ArrayList();
            ejecutarQueryTablas = conexionLocal.ejecutarQuery("show tables in "+BD);
            
            while(ejecutarQueryTablas.next()){
                tablas.add(ejecutarQueryTablas.getString(1));
                System.out.println(ejecutarQueryTablas.getString(1));
            }
            for(int x = 0; x < tablas.size(); x++) {
                jComboBoxTablas.addItem(tablas.get(x).toString());
            }
        }catch(SQLException ex) {
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ejecutarQueryTablas != null){
                try{
                    // Se cierra el ResultSet (ejecutarQueryTablas).
                    ejecutarQueryTablas.close();
                    
                    System.out.println("Se cierra el ResultSet de las tablas!!!");
                }catch(SQLException ex){ 
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception ex){  
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void cargarVariablesTabla(String tabla) {
        // Objetos.
        ResultSet useBase = null;
        ResultSet ejecutarQueryTablas = null;
        
        try {
            jPanelVariables.removeAll();
            ArrayList variables= new ArrayList();
            ArrayList tipoDato= new ArrayList();
            useBase = conexionLocal.ejecutarQuery("use " + baseDatos);
            ejecutarQueryTablas = conexionLocal.ejecutarQuery("DESCRIBE " + tabla);
            
            while(ejecutarQueryTablas.next()){
                variables.add(ejecutarQueryTablas.getString(1));
                tipoDato.add(ejecutarQueryTablas.getString(2));
                System.out.println(ejecutarQueryTablas.getString(1)+"------->"+ejecutarQueryTablas.getString(2));
            }
            jCheckBoxVariables= new JCheckBox[variables.size()];
            
            for(int x = 0; x < variables.size(); x++) {
              jCheckBoxVariables[x] = new JCheckBox(variables.get(x).toString());
              jCheckBoxVariables[x].setBounds(0, 35*(x+1), 100, 25);
              jPanelVariables.add(jCheckBoxVariables[x]);
              jPanelVariables.repaint();
              jButton1.setEnabled(true);
           }
        }catch(SQLException ex) {
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ejecutarQueryTablas != null){
                try{
                    // Se cierra el ResultSet (ejecutarQueryTablas).
                    ejecutarQueryTablas.close();
                    
                    System.out.println("Se cierra el ResultSet de las tablas!!!");
                }catch(SQLException ex){ 
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }catch(Exception ex){  
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
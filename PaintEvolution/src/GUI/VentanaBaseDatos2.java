/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Auxiliar.Constantes;
import baseDatos.ConexionMysql;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author proyectosbeta
 */
public class VentanaBaseDatos2 extends VentanaComun {
    // Variables de clase.
    private static ConexionMysql conexionLocal;
    private String baseDatos;
    private JCheckBox[] jCheckBoxVariables;
    private ArrayList listaNombreColumnas;
    private static String rutaImagenTemporal;
    
   /**
     * Constructor con dos parametros (conexion y rutaImagenTemporal)
     * @param conexion
     * @param rutaImagenTemporal 
     */
    public VentanaBaseDatos2(ConexionMysql conexion, String rutaImagenTemporal) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        VentanaBaseDatos2.rutaImagenTemporal = rutaImagenTemporal;
        
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
            /*
            if(conexionLocal != null){
                try{
                    // Se cierra la base de datos.
                    conexionLocal.terminarConexion();
                    
                    System.out.println("Se cerro la conexion a la base de datos local!!!");
                }catch(SQLException ex){   
                     Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
        }
    }
    
    /**
    * Carga las baseDatos en un combobox de un servidor mysql.
    */
    private void cargarBasesDatos(){
        // Objetos.
        ResultSet ejecutarQueryBases;
        
        try{ 
            ArrayList bases = new ArrayList();
            ejecutarQueryBases = conexionLocal.ejecutarQuery("Show Databases");
            
            while(ejecutarQueryBases.next()){
                bases.add(ejecutarQueryBases.getString(1));
                //System.out.println(ejecutarQueryBases.getString(1));
            }
            for(int x = 0; x < bases.size(); x++) {
                jComboBoxBD.addItem(bases.get(x).toString());
            }
        }catch(SQLException ex){
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            /*
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
            * */
        }
    }

    /**
     * Carga las tablas en un combobox de la BaseDatos.
     * @param BD 
     */
    private void cargarTablas(String BD) {
        // Objetos.
        ResultSet ejecutarQueryTablas;
        
        try {
            jComboBoxTablas.removeAllItems();
            ArrayList tablas = new ArrayList();
            
            ejecutarQueryTablas = conexionLocal.ejecutarQuery("show tables in "+BD);
            
            while(ejecutarQueryTablas.next()){
                tablas.add(ejecutarQueryTablas.getString(1));
                //System.out.println(ejecutarQueryTablas.getString(1));
            }
            for(int x = 0; x < tablas.size(); x++) {
                jComboBoxTablas.addItem(tablas.get(x).toString());
            }
        }catch(SQLException ex) {
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            /*
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
            * */
        }
    }

    /**
     * 
     * @param tabla 
     */
    private void cargarCampos(String tabla) {
        // Objetos.
        ResultSet useBase;
        ResultSet ejecutarQueryTablas;
        
        try {
            jPanelVariables.removeAll();
            ArrayList campos = new ArrayList();
            
            // Abrir conexion a base de datos mysql local.
            //conexionLocal.abrirConexion();
            
            useBase = conexionLocal.ejecutarQuery("use " + baseDatos);
            ejecutarQueryTablas = conexionLocal.ejecutarQuery("DESCRIBE " + tabla);
            
            while(ejecutarQueryTablas.next()){
                campos.add(ejecutarQueryTablas.getString(1));
            }
            jCheckBoxVariables = new JCheckBox[campos.size()];
            
            System.out.println("La cantidad de campos es: " + campos.size());
            jPanelVariables.repaint();
            
            for(int x = 0; x < campos.size(); x++) {
                jCheckBoxVariables[x] = new JCheckBox(campos.get(x).toString());
                jCheckBoxVariables[x].setBounds(0, 35*(x+1), 100, 25);
                //jPanelVariables.add(jCheckBoxVariables[x]);
                //jPanelVariables.repaint();
            }
            if(campos.size() < 7){
                for (int i = 0; i < campos.size(); i++){
                    jPanelVariables.add (jCheckBoxVariables[i]);  // Añade los checkbo de 1 en 1.
                    jPanelVariables.repaint();
                }
            }else{
                 int cantidadFilas = campos.size() / 3;
                 // Colocación en el contenedor
                 jPanelVariables.setLayout (new GridLayout (cantidadFilas, 3));  // 3 filas y 3 columnas

                 for (int i = 0; i < campos.size(); i++){
                     jPanelVariables.add (jCheckBoxVariables[i]);  // Añade los checkbo de 1 en 1.
                     jPanelVariables.repaint();
                 }
            }
            jButtonSiguiente.setEnabled(true);
        }catch(SQLException ex) {
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            /*
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
            * */
        }
    }
    
    /**
     * Metodo privado
     * @param tabla
     * @param listaNombreColumnas
     * @return 
     */
    private ResultSet devolverRegistros(String tabla, ArrayList listaNombreColumnas){
        // Objetos.
        ResultSet ejecutarQueryRegistro = null;
        String columnaConsulta = devuelveColumnasConsulta(listaNombreColumnas);
        
        try {
            // Abrir conexion a base de datos mysql local.
            //conexionLocal.abrirConexion();
            conexionLocal.setBaseDatos(jComboBoxBD.getSelectedItem().toString());
            
            //System.out.println("El valor de la base de datos es: " + this.baseDatos);
            
            // Realiza la consulta sql.
            ejecutarQueryRegistro = conexionLocal.ejecutarQuery("select " + 
                columnaConsulta + " from " + tabla);
        }catch(SQLException ex) {
             Logger.getLogger(VentanaBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        }
        return ejecutarQueryRegistro;
    }
    
    /**
     * Metodo privado.
     * @param listaNombreColumnas
     * @return 
     */
    private String devuelveColumnasConsulta(ArrayList listaNombreColumnas){
        // Objetos.
        String columnas = "";
        Iterator iterador = listaNombreColumnas.iterator();
        
        while(iterador.hasNext()){
            columnas = columnas + (String)iterador.next() + ",";
        }
        return columnas.substring(0, columnas.length() - 1);
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
        jPanelPrincipal = new javax.swing.JPanel();
        jButtonSiguiente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxBD = new javax.swing.JComboBox();
        jComboBoxTablas = new javax.swing.JComboBox();
        jPanelVariables = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccionar tabla y campos");

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jButtonSiguiente.setText("Siguiente");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        jComboBoxBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBDActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBoxBD);

        jComboBoxTablas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTablasItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBoxTablas);

        javax.swing.GroupLayout jPanelVariablesLayout = new javax.swing.GroupLayout(jPanelVariables);
        jPanelVariables.setLayout(jPanelVariablesLayout);
        jPanelVariablesLayout.setHorizontalGroup(
            jPanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelVariablesLayout.setVerticalGroup(
            jPanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSiguiente))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(jPanelVariables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelVariables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSiguiente))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        // Se crea la instancia de una ArrayList.
        listaNombreColumnas = new ArrayList();
      
        for(int x = 0; x < jCheckBoxVariables.length;x++){    
            if(jCheckBoxVariables[x].isSelected()){
                listaNombreColumnas.add(jCheckBoxVariables[x].getText());
            }
        }
        if(!listaNombreColumnas.isEmpty()){
            // Obtener la tabla seleccionada del combobox (jComboBoxTablas).
            String tabla = (String)jComboBoxTablas.getSelectedItem(); 
            
            /*
             * Se carga en un resultSet todos los registros de la tabla
             * que el usuario eligio en un combobox.
             */
            ResultSet resultSetRegistros = devolverRegistros(tabla, listaNombreColumnas);
            
            // Ir a la ventanaTablaRegistro.
            VentanaTablaRegistro ventanaTablaRegistro = new VentanaTablaRegistro(rutaImagenTemporal, listaNombreColumnas, resultSetRegistros);
            ventanaTablaRegistro.setLocationRelativeTo(this);
            ventanaTablaRegistro.setVisible(true);
            
             // Se cierra la VentanaBaseDatos.
            this.dispose();
        }else{
            String mensaje = "Seleccione una varible del checkbox!!!";
            JOptionPane.showMessageDialog(this, mensaje,
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO + 
                Constantes.TITULO_PROGRAMA, JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSiguienteActionPerformed

    private void jComboBoxBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBDActionPerformed
        this.baseDatos = jComboBoxBD.getSelectedItem().toString();
        cargarTablas(jComboBoxBD.getSelectedItem().toString());
    }//GEN-LAST:event_jComboBoxBDActionPerformed

    private void jComboBoxTablasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTablasItemStateChanged
        if(jComboBoxTablas.getSelectedItem() != null) {
            cargarCampos(jComboBoxTablas.getSelectedItem().toString());
        }
    }//GEN-LAST:event_jComboBoxTablasItemStateChanged

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
            java.util.logging.Logger.getLogger(VentanaBaseDatos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBaseDatos2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaBaseDatos2(conexionLocal, rutaImagenTemporal).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JComboBox jComboBoxBD;
    private javax.swing.JComboBox jComboBoxTablas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelVariables;
    // End of variables declaration//GEN-END:variables
}

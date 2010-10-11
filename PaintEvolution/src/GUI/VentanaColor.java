package GUI;

import java.awt.Color;
import javax.swing.JColorChooser;

/**
 *@(#)VentanaColor.java
 * Creado 06/09/2010, 03:36:37 AM
 *
 * @author fires
 * @version 1.00
 * @since 1.6
 */
/**
 * Clase VentanaColor donde se crean objetos Color.
 * @since 1.6
 */
public class VentanaColor extends javax.swing.JDialog {
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Color del Texto
     * @since 1.6
     */
    private Color colorTexto;


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye una Ventana de Color.
     * @since 1.6
     */
    public VentanaColor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jColorChooserColores.setColor(Color.BLACK); // color negro como predeterminado
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooserColores = new javax.swing.JColorChooser();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setToolTipText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jColorChooserColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jColorChooserColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        setColorTexto(jColorChooserColores.getColor());
        setVisible(false);
}//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jColorChooserColores.setColor(getColorTexto());
        setVisible(false);
}//GEN-LAST:event_jButtonCancelarActionPerformed

    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve el color del Texto.
     *
     * @return El color del Texto
     * @since 1.6
     */
    public Color getColorTexto() {
        return colorTexto;
    }

    /**
     * Establece el color del Texto.
     *
     * @param colorTexto El color del Texto
     * @since 1.6
     */
    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

    /**
     * Devuelve el color chooser.
     *
     * @return El color chooser
     * @since 1.6
     */
    public JColorChooser getJColorChooserColores(){
        return jColorChooserColores;
    }


   /**
    * Donde se ejecuta la Ventana Color.
    *
    * @param args La linea de argumentos del comando
    * @since 1.6
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaColor dialog = new VentanaColor(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JColorChooser jColorChooserColores;
    // End of variables declaration//GEN-END:variables

}
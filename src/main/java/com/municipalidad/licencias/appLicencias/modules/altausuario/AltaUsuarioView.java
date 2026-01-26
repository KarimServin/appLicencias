
package com.municipalidad.licencias.appLicencias.modules.altausuario;



import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class AltaUsuarioView extends javax.swing.JFrame {
    
  
    private static final int PASS_MIN = 8;
    private static final int PASS_MAX = 16;
    

    public AltaUsuarioView() {

        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);
        
    }
    
    
    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
    
    public void setAceptarAction(ActionListener listener) {
        aceptarButton.addActionListener(listener);
    }
    
    public String getNombreUsuario() {
        return usuarioField.getText().trim();
    }
    
    public char[] getPassword() {
        return fieldPassword.getPassword();   
    }
    
    public boolean isAdmin() {
        return privilegiosCheckBox.isSelected();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelLogoSF = new javax.swing.JLabel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        labelContrasenia = new javax.swing.JLabel();
        privilegiosCheckBox = new javax.swing.JCheckBox();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        fieldPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(482, 176));

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelMenuPrincipal.setText("ALTA DE USUARIO");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(labelLogoSF)
                .addGap(40, 40, 40))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        labelUsuario.setText("Usuario:");

        labelContrasenia.setText("Contraseña:");

        privilegiosCheckBox.setText("Conceder privilegios de Superusuario");

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        jLabel1.setText("Ingrese las credenciales de acceso para el nuevo usuario.");

        jButton1.setText("👁");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(288, 288, 288)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(privilegiosCheckBox)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUsuario)
                            .addComponent(labelContrasenia))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContrasenia)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(privilegiosCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean validarCamposUsuario() {
        String nombreUsuario = getNombreUsuario();
        char[] passwordChars = getPassword();
        
        String password = new String(passwordChars).trim();

        if (nombreUsuario.isEmpty()) {
            Dialogs.error(this,"El campo usuario no puede estar vacío.");
            return false;
        }

        if (password.isEmpty()) {
            Dialogs.error(this,"El campo contraseña no puede estar vacío.");
            return false;
        }

        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelContrasenia;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JCheckBox privilegiosCheckBox;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}

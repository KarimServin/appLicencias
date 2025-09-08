package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.UsuarioController;
import com.municipalidad.licencias.appLicencias.dto.CredencialesLoginDTO;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import java.awt.Toolkit;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.JOptionPane;


public class PantallaLogin extends javax.swing.JFrame {

    private final Consumer<CredencialesLoginDTO> onLogin;
    
    public PantallaLogin(Consumer<CredencialesLoginDTO>  procesadorLogin) {
        
        this.onLogin = procesadorLogin;
 
      
        initComponents(); 
     
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login - Sistema de Emisión de Licencias");
        
    }

    //No tocar
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JLabel();
        campoPassword = new javax.swing.JPasswordField();
        campoUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(239, 239, 239));
        jPanel1.setForeground(new java.awt.Color(221, 221, 221));

        txtUsuario.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtUsuario.setText("Usuario");

        txtContrasenia.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtContrasenia.setForeground(new java.awt.Color(153, 153, 153));
        txtContrasenia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtContrasenia.setText("Contraseña");

        campoPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        campoUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        btnIngresar.setBackground(new java.awt.Color(71, 197, 155));
        btnIngresar.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        txtTitulo.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(102, 102, 102));
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("Sistema de Emisión de Licencias");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(101, 101, 101))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoPassword)
                            .addComponent(campoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnIngresar)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {campoPassword, campoUsuario});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtContrasenia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnIngresar)
                .addGap(28, 28, 28))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imagenPuenteColgante.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        
        if(!camposNoVacios())
            return;
        
            CredencialesLoginDTO credenciales = new CredencialesLoginDTO(campoUsuario.getText(),campoPassword.getPassword());
            onLogin.accept(credenciales);
            JOptionPane.showMessageDialog(this, "Login exitoso");
            this.dispose();            

    }//GEN-LAST:event_btnIngresarActionPerformed

    
    private boolean camposNoVacios() {
        String usuario = campoUsuario.getText().trim();
        char[] password = campoPassword.getPassword();
        
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese su nombre de usuario", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                campoUsuario.requestFocus();
                return false;
            }
            
            if (password.length == 0) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese su contraseña", 
                    "Campo requerido", 
                    JOptionPane.WARNING_MESSAGE);
                campoPassword.requestFocus();
                return false;
            }
            
            return true;
            
    } 
    

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtContrasenia;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}

package com.municipalidad.licencias.appLicencias.modules.modificarusuario;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ModificarUsuarioView extends javax.swing.JFrame {

    public ModificarUsuarioView() {
        initComponents();
        configurarBotonVerContrasenia();
        setListRenderer();
    }
     public void setConfimarAction(ActionListener listener) {
        confirmarButton.addActionListener(listener);
    }

    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    // recibe DTOs
    public void cargarUsuariosEnLista(List<UsuarioDTO> usuarios) {
        DefaultListModel<UsuarioDTO> model = new DefaultListModel<>();
        for (UsuarioDTO u : usuarios) model.addElement(u);
        listaUsuarios.setModel(model);
    }

    // devuelve DTO (id, username, etc.)
    public UsuarioDTO getUsuarioSeleccionado() {
        return listaUsuarios.getSelectedValue();
    }

    private void configurarBotonVerContrasenia() {
        char echoChar = nuevaContraseniaField.getEchoChar();
        verContraseniaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                nuevaContraseniaField.setEchoChar((char) 0);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                nuevaContraseniaField.setEchoChar(echoChar);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                nuevaContraseniaField.setEchoChar(echoChar);
            }
        });
    }
    
    private void setListRenderer() {
        listaUsuarios.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );
                if (value instanceof UsuarioDTO usuario) {
                    setText(usuario.getNombreUsuario()); // SOLO el nombre visible
                } else {
                    setText(""); // por si llega null u otro tipo
                }
                return c;
            }
        });
    }
    
    public void setConfirmarEnabled(boolean enabled) {
        confirmarButton.setEnabled(enabled);
    }
    
    public boolean isMantenerNombreUsuario() {
        return mantenerNombreUsuarioCheckbox.isSelected();
    }

    public void setNuevoNombreUsuarioEnabled(boolean enabled) {
        nuevoNombreUsuarioField.setEnabled(enabled);
    }

    public String getNuevoNombreUsuario() {
        return nuevoNombreUsuarioField.getText();
    }

    public char[] getNuevaContrasenia() {
        return nuevaContraseniaField.getPassword();
    }

    public boolean isSuperusuario() {
        return superUsuarioCheckbox.isSelected();
    }

public void mostrarMensaje(String msg) {
    javax.swing.JOptionPane.showMessageDialog(this, msg);
}

    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nuevoNombreUsuarioField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nuevaContraseniaField = new javax.swing.JPasswordField();
        superUsuarioCheckbox = new javax.swing.JCheckBox();
        cancelarButton = new javax.swing.JButton();
        confirmarButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        mantenerNombreUsuarioCheckbox = new javax.swing.JCheckBox();
        verContraseniaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre de nuevo usuario:");

        jLabel2.setText("Contraseña de nuevo usuario:");

        superUsuarioCheckbox.setText("Otorgar privilegios de superusuario");

        cancelarButton.setText("Cancelar");

        confirmarButton.setText("Confirmar");

        jLabel5.setText("Seleccione el usuario que desea modificar:");

        jScrollPane1.setViewportView(listaUsuarios);

        jLabel6.setText("Usted ha seleccionado al usuario _ para su modificación.");

        mantenerNombreUsuarioCheckbox.setText("Mantener nombre de usuario actual");

        verContraseniaButton.setText("👁");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(superUsuarioCheckbox)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoNombreUsuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nuevaContraseniaField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(verContraseniaButton))))
                    .addComponent(mantenerNombreUsuarioCheckbox))
                .addGap(0, 167, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 278, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(confirmarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelarButton)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nuevoNombreUsuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nuevaContraseniaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verContraseniaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(superUsuarioCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mantenerNombreUsuarioCheckbox)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(confirmarButton))
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("MODIFICAR USUARIO");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton confirmarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<UsuarioDTO> listaUsuarios;
    private javax.swing.JCheckBox mantenerNombreUsuarioCheckbox;
    private javax.swing.JPasswordField nuevaContraseniaField;
    private javax.swing.JTextField nuevoNombreUsuarioField;
    private javax.swing.JCheckBox superUsuarioCheckbox;
    private javax.swing.JButton verContraseniaButton;
    // End of variables declaration//GEN-END:variables

   
}

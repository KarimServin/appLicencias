package com.municipalidad.licencias.appLicencias.modules.listarlicenciasvigentes;

import javax.swing.SwingUtilities;


public class ListarLicenciasVigentesController {

    private ListarLicenciasVigentesView view;
    
   
    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new ListarLicenciasVigentesView();
            view.setVisible(true);
        });
        setListeners();
        }
    
    private void setListeners() {
    
        view.setFiltrarAction(e -> aplicarFiltro());
        view.setEmitirInformeAction (e -> emitirInforme());
        view.setCerrarAction(e -> cerrar()); 
    }
    
    private void aplicarFiltro() {}
    private void emitirInforme() {}
    private void cerrar() {
        view.dispose();
    }
}

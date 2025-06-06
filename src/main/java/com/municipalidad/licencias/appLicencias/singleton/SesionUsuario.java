
package com.municipalidad.licencias.appLicencias.singleton;

import com.municipalidad.licencias.appLicencias.model.Usuario;

public class SesionUsuario {
    private static Usuario usuarioAdministrativo;
    
    public static void setUsuarioActual(Usuario u){
        usuarioAdministrativo = u;
    }
    
    public static Usuario getUsuarioActual(){
        return usuarioAdministrativo;
    }
}

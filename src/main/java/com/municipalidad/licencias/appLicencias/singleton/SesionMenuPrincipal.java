
package com.municipalidad.licencias.appLicencias.singleton;

import com.municipalidad.licencias.appLicencias.ui.PantallaMenuPrincipal;

public class SesionMenuPrincipal {
    private static PantallaMenuPrincipal menuPrincipal;
    
    public static void setMenu(PantallaMenuPrincipal menu){
        menuPrincipal = menu;
    }
    
    public static PantallaMenuPrincipal getMenu(){
        return menuPrincipal;
    }
    
    public static void setVisible(boolean tof){
        menuPrincipal.setVisible(tof);
    }
}

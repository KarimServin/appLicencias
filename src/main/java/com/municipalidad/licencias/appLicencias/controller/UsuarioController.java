package com.municipalidad.licencias.appLicencias.controller;

import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public Usuario login(String nombreUsuario, String contrasenia) {
        return usuarioService.validarCredenciales(nombreUsuario, contrasenia);
    }

    public Usuario obtenerUsuario(String nombreUsuario) {
        return usuarioService.buscarPorNombreUsuario(nombreUsuario);
    }
    
    public void altaUsuario(String nombreUsuario, String contrasenia, boolean esSuperusuario){
    usuarioService.guardarUsuario(nombreUsuario, contrasenia, esSuperusuario);
    }
}  
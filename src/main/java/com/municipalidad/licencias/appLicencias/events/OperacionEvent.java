package com.municipalidad.licencias.appLicencias.events;

public class OperacionEvent {

    private final Object source;
    private final String usuario;
    private final String tipoOperacion;
    private final String detalle;

    public OperacionEvent(Object source, String usuario, String tipoOperacion, String detalle) {
        this.source = source;
        this.usuario = usuario;
        this.tipoOperacion = tipoOperacion;
        this.detalle = detalle;
    }

    public Object getSource() {
        return source;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public String getDetalle() {
        return detalle;
    }
}

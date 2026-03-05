package com.municipalidad.licencias.appLicencias.exception;


public class ServiceException extends RuntimeException {


    public ServiceException() {
    }

    public ServiceException(String msg) {
        super(msg);
    }
}

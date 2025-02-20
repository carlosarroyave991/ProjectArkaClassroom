package com.arka.classroom.arka.project.Aplication.Services.exception;

/**@author Carlos Arroyave
 * Se utiliza para generar mensajes a la hora de tener una excepcion
 */
public class GeneralException extends RuntimeException{
    private static final Long serialVerionUID = 1L;

    public GeneralException(String mensaje){
        super(mensaje);
    }
}

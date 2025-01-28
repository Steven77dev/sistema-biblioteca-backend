package com.growby.biblioteca.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private int codigo;
    private String mensaje;

    private Object respuesta;

    public Response() {
       this.setCodigo(0);
    }

    public static Response error() {
        Response apiResponse = new Response();
        apiResponse.setMensaje("ERROR");
       return apiResponse;
    }

    public static Response error(String mensaje) {
        Response apiResponse = new Response();
        apiResponse.setCodigo(-1);
        apiResponse.setMensaje(mensaje);
        return apiResponse;
    }

    public static Response mensaje(int codigo, Object respuesta) {
        Response response = new Response();
        response.setCodigo(codigo);
        response.setRespuesta(respuesta);
        return response;
    }


    public static Response mensaje(int codigo, String mensaje, Object respuesta) {
        Response response = new Response();
        response.setCodigo(codigo);
        response.setMensaje(mensaje);
        response.setRespuesta(respuesta);
        return response;
    }

    public static Response exito(String mensaje, Object respuesta) {
        Response response = new Response();
        response.setCodigo(200);
        response.setMensaje(mensaje);
        response.setRespuesta(respuesta);
        return response;
    }


    public static Response parametrosIncorrectos() {
        Response response = new Response();
        response.setCodigo(-1);
        response.setMensaje("PARAMETROS_INCORRECTOS");
        return  response;
    }

    public static Response noExisteUsuario() {
        Response response = new Response();
        response.setCodigo(-1);
        response.setMensaje("USUARIO_NO_EXISTE");
        return  response;
    }

    public static Response noHayResultados(String mensaje) {
        Response response = new Response();
        response.setCodigo(-1);
        response.setMensaje(mensaje==null ? "No existen resultados" : mensaje);
        return  response;
    }

}

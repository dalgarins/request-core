package com.request.core.conexion;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.request.core.schema.DataRequest;
import com.request.core.schema.Opcion;
import com.request.core.schema.Request;
import com.squareup.okhttp.OkHttpClient;

import java.util.LinkedList;

/**
 * Created by NoaD on 07/06/2015.
 */
public class ConexionGenerica<T> extends ConnectionHttp<T> {

    public ConexionGenerica(DataRequest data, Request request, OkHttpClient httpClient, LinkedList<Opcion> arrayData) {

        super(data, request, httpClient, arrayData);
        this.run();
    }

    /**
     * Obtiene un objeto de la respuesta de la peticion que ha sido dada en formato Gson
     * @return T con el nuevo objeto
     */
    public T getResponseOfRequest(){

        Gson g = new GsonBuilder().create();
        return g.fromJson(getResponse(), getType());
    }

}

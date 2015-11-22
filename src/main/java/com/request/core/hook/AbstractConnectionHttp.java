package com.request.core.hook;


import com.request.core.schema.DataRequest;
import com.request.core.schema.Opcion;
import com.request.core.schema.Request;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public abstract class AbstractConnectionHttp extends HookAdapter {

    private DataRequest dataPeticion;
    private Request request;

    private FormEncodingBuilder arrayData = new FormEncodingBuilder();

    private OkHttpClient httpClient;
    private Response httpResponse;
    private com.squareup.okhttp.Request.Builder builderRequest;

    String response = "";

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public DataRequest getDataPeticion() {
        return dataPeticion;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setDataPeticion(DataRequest dataPeticion) {
        this.dataPeticion = dataPeticion;
    }

    public String getResponse() {
        return response;
    }

    public FormEncodingBuilder getArrayData() {
        return arrayData;
    }

    /**
     * Agrega los Headers a una Peticion
     * ejemplo:   Accept:
     *            Cookie:
     *            Host:
     */
    private void addHeaders() {
        builderRequest = new com.squareup.okhttp.Request.Builder().url(request.getUrl());

        if (dataPeticion != null){
            for (Opcion op: dataPeticion.getPublicHeaders()) {
                builderRequest.addHeader(op.getKey(), op.getValue());
            }
        }
        for (Opcion op: request.getHeaders()) {
            builderRequest.addHeader(op.getKey(), op.getValue());
        }
    }

    @Override
    public void start() {
        try {

            addHeaders();

            //Valida si se manejara la respuesta de la peticione
            /*if (request.isVerbose()) {
                responseHandler = new BasicResponseHandler();
            }*/

            //Ejecuta la peticion segun su tipo sea Get o Post
            if (request.getHttpMethod().trim().equalsIgnoreCase(Request.HTTP_METHOD_POST)) {

                builderRequest.post(arrayData.build());
            }

            if (request.isVerbose()) {
                httpResponse = httpClient.newCall(builderRequest.build()).execute();
                response = httpResponse.body().string();
                //response = httpClient.execute(httpGet, responseHandler);
            } else {
                httpResponse = httpClient.newCall(builderRequest.build()).execute();
            }

        } catch (BindException be) {
            System.err.println("Error de Conexion! " + be.getMessage());
        } catch (SocketException se) {
            System.err.println("Error Abriendo Socked! " + se.getMessage());
        } catch (UnknownHostException ue) {
            System.err.println("No se Enuentra el Host! " + ue.getMessage());
        } catch (IOException ex) {
            System.err.println("Error de I/O " + ex.getMessage());
        }
    }

    public int getResponseCode(){

        if (httpResponse != null){
            return httpResponse.code();
        }
        return -1;
    }

}

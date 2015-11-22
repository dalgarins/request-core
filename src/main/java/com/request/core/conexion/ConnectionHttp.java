package com.request.core.conexion;


import com.google.common.reflect.TypeToken;
import com.request.core.hook.AbstractConnectionHttp;
import com.request.core.schema.DataRequest;
import com.request.core.schema.Opcion;
import com.request.core.schema.Request;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.LinkedList;



/**
 * Los objetos de esta clase, pueden realizar peticiones get o post hacia un servidor
 * @author user
 */
public class ConnectionHttp<T> extends AbstractConnectionHttp {

    LinkedList<Opcion> arrayData;
    private TypeToken<T> typeToken = new TypeToken<T>(getClass()){};
    private Type type = typeToken.getType();

    /**
     * Constructor del objeto
     * @param data DataRequest en el cual estan todas las peticiones previamente configuradas
     * @param request Request es la peticion actual a realizar
     * @param httpClient HttpClient cliente de peticiones http
     * @param arrayData LinkedList<Opcion> con los parametros de la peticion
     */
    public ConnectionHttp(DataRequest data, Request request, OkHttpClient httpClient, LinkedList<Opcion> arrayData) {
        
        setHttpClient(httpClient);
        setDataPeticion(data);
        setRequest(request);
        this.arrayData = arrayData;
    }

    @Override
    public void beforeDo() {
        addDataToSend();
    }

    /**
     * Agrega los parametros para realizar la peticion peticion
     */
    protected void addDataToSend() {
        if (arrayData != null) {
            arrayData.addAll(getRequest().getRequestForm());
            if (getRequest().getHttpMethod().trim().equalsIgnoreCase(Request.HTTP_METHOD_GET)) {
                for (Opcion opc : arrayData) {
                    getRequest().setUrl(getRequest().getUrl().concat(opc.getKey() + "=" + opc.getValue() + "&"));
                }
                getRequest().setUrl(getRequest().getUrl().substring(0, getRequest().getUrl().length() - 1));
            } else if (getRequest().getHttpMethod().trim().equalsIgnoreCase(Request.HTTP_METHOD_POST)) {
                for (Opcion opc : arrayData) {
                    getArrayData().add(opc.getKey(), opc.getValue());
                }
            }
        }
    }

    /**
     * Obtiene el tipo del objeto actual
     * @return Type con el tipo de objeto
     */
    public Type getType() {
        return type;
    }

}

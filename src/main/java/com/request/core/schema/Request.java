package com.request.core.schema;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Esta clase es la representacion de una peticion
 * a una pagina web, la cual sera configurada a traves 
 * de un archivo xml.
 * @author NoaD
 */
@XStreamAlias("SchemaRequest")
public class Request implements Serializable {
    
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    
    private long idRequest; //id unico de la peticion
    private String url; //url a la que se desea acceder
    private String httpMethod;//metodo http, pueser get o post
    
    private LinkedList<Opcion> headers;//parametros de cabecera de la peticion
    private LinkedList<Opcion> requestForm;//parametros que se enviaran a traves del httpMethod elegido
    private LinkedList<Opcion> settings;//parametros que seran usados al momento de programar para algun calculo
    
    private boolean verbose;//si es true, imprime toda la respuesta de la peticion
    private long startTime;//tiempo de inicio de la peticion
    private long leadTime;//tiempo de espera para repetir la peticion
    
    private boolean hasMoreRequest;/*si es true, ejecuta la proxima peticion, 
                                        antes de volver a ejecutar la peticion que realizo la llamada*/
    private long idNextRequest;//id de la proxima peticion a ejecutar, en caso de que hasMoreRequest sea true

    /**
     * Constructor Vacio, con parametros por defecto. 
     */
    public Request() {
        
        idRequest = 1;
        url = "http://prueba.com";
        httpMethod = "post";
        
        headers = new LinkedList<Opcion>();
        requestForm = new LinkedList<Opcion>();
        settings = new LinkedList<Opcion>();
        
        verbose = true;
        startTime = 0;
        leadTime = 5;
        hasMoreRequest = false;
        idNextRequest = 0;        
    }

    /**
     * Obtiene el Id de la Peticion
     * @return long con el numero de Id
     */
    public long getIdRequest() {
        return idRequest;
    }

    /**
     * Modifica el Id de la Peticion
     * @param idRequest es el nuevo id
     */
    public void setIdRequest(long idRequest) {
        this.idRequest = idRequest;
    }

    /**
     * Obtiene la url de la peticion
     * @return String con la url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Modifica la url de la peticion
     * @param url es la url nueva
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Obtiene el tipo de metodo con el que se realiza la peticion
     * @return String con el tipo de metodo, <b> POST </b> o <b>GET</b>
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * Modifica el tipo de metodo de la Peticion
     * @param httpMethod contien el metodo nuevo: <b> POST </b> o <b>GET</b>
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * Obtine la lista con los parametros de cabecera de la peticion especifica
     * @return LinkedList<Opcion> con los parametros de la cabecera
     */
    public LinkedList<Opcion> getHeaders() {
        return headers;
    }

    /**
     * Modifica los parametros de la cabecera de la peticion especifica
     * @param headers contiene los nuevos parametros
     */
    public void setHeaders(LinkedList<Opcion> headers) {
        this.headers = headers;
    }

    /**
     * Obtiene la lista de parametros que se mandaran, en el cuerpo de la peticion, que seran enviados
     * por su respectivo metodo Http
     * @return LinkedList<Opcion> con los parametros del cuerpo de la peticion
     */
    public LinkedList<Opcion> getRequestForm() {
        return requestForm;
    }

    /**
     * Modifica los parametros que se enviaran por Http
     * @param requestForm contiene los nuevoa parametros 
     */
    public void setRequestForm(LinkedList<Opcion> requestForm) {
        this.requestForm = requestForm;
    }

    /**
     * Obtiene la lista de parametros que no se enviaran en la peticion,
     * sino que seran usados al momento de programar la aplicacion
     * @return LinkedList<Opcion> con los parametros
     */
    public LinkedList<Opcion> getSettings() {
        return settings;
    }

    /**
     * Modifica los parametos de ajustes de la aplicacion
     * @param settings contienen los nuevos parametros
     */
    public void setSettings(LinkedList<Opcion> settings) {
        this.settings = settings;
    }

    /**
     * Obtiene la flag que indica si imprime o no la respuesta
     * @return true si la aplicacion muestra la respuesta de la peticion
     */
    public boolean isVerbose() {
        return verbose;
    }

    /**
     * Modifica el flag de impresion de la respuesta
     * @param verbose contien el valor de modificacion, si es true imprime la respuesta
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * Obtiene el valor de inicio de la peticion desde el momento que inicia la aplicacion
     * @return long con el tiempo en segundos, 0 indica que incia al momento de lanzar la aplicacion
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Modifica el valor del tiempo de inicio de la peticion
     * @param startTime es el tiempo que espera la peticion por primera vez, para iniciar,
     * 0 indica que incia al momento de lanzar la aplicacion
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Obtiene el valor del tiempo de espera entre cada peticion
     * @return long con el valor en segundos, del tiempo de espera entre cada peticion
     */
    public long getLeadTime() {
        return leadTime;
    }

    /**
     * Modifica el valor del tiempo de espera entre peticiones
     * @param leadTime es el tiempo de espera que se desea usar en segundos
     */
    public void setLeadTime(long leadTime) {
        this.leadTime = leadTime;
    }

    /**
     * Obtien el flag que indica, si despues de esta peticion se deben realizar otras peticiones
     * @return true, si se realizaran mas peticiones
     */
    public boolean isHasMoreRequest() {
        return hasMoreRequest;
    }

    /**
     * Modifica el flag que indica si se realizaran mas peticiones
     * @param hasMoreRequest contiene el nuevo valor, <b>true</b> si se realizaran mas peticiones 
     */
    public void setHasMoreRequest(boolean hasMoreRequest) {
        this.hasMoreRequest = hasMoreRequest;
    }

    /**
     * Obtiene el Id de la proxima peticion a ejecutar
     * @return long con el identificador unico de la peticion
     */
    public long getIdNextRequest() {
        return idNextRequest;
    }

    /**
     * Modifica el Id de la proxima peticion a ejecutar, para cambiar el orden de ejecuciones
     * @param idNextRequest contiene el Id de la peticion que se realizara 
     */
    public void setIdNextRequest(long idNextRequest) {
        this.idNextRequest = idNextRequest;
    }    
}

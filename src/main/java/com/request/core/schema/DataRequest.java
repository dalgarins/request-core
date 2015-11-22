package com.request.core.schema;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.extended.NamedMapConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Esta Clase se Encarga de Manejar la lista de peticiones de manera
 * estructurada, y son leidos de un archivo xml
 *
 * @author NoaD
 */
@XStreamAlias("Data")
public class DataRequest implements Serializable {

    @XStreamAlias("PublicHeaders")
    private LinkedList<Opcion> publicHeaders;/*Contiene Cabeceras como Cookie, User-Agent
                                               Publicas para toda las Peticiones
                                                 */

    @XStreamAlias("RequestsList")
    private HashMap<String, Request> requestList;/*Contiene las Peticiones diferencias por un ID,
                                                  la peticion principal y la primera es la de ID  main
                                                 */
    
    private boolean loop;//si es true, las peticiones se ejecutan hasta que se cierre la aplicacion
    private int iterations;//si loop es false, las peticiones se repetiran la cantidad de veces aqui aparecidas

    /**
     * Contructor Generico del Objeto, para modificar sus valores
     * es necesario acceder a cada atributo.
     */
    public DataRequest() {

        requestList = new HashMap<String, Request>();
        publicHeaders = new LinkedList<Opcion>();
        loop = false;
        iterations = 0;
    }

    /**
     * Obtiene el valor de repeticion de la peticion
     * @return true si las peticiones se repetiran
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Modifica el valor de loop
     * @param loop 
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Obtiene el numero de iteraciones
     * @return int con el numero de iteracion
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Modifica la cantidad de iteraciones
     * @param iterations 
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }    

    /**
     * Obtiene los Headers publicos que usaran todas las peticiones
     * @return LinkedList<Opcion> 
     */
    public LinkedList<Opcion> getPublicHeaders() {
        return publicHeaders;
    }

    /**
     * Obtiene las Peticiones diferenciadas por un ID, 
     * la peticion principal y la primera es la de ID  main
     * @return HashMap<String, Request>
     */
    public HashMap<String, Request> getRequestList() {
        return requestList;
    }

    /**
     * Lee el archivo de configuraciones, donde se han especificado las
     * peticiones
     *
     * @param path contiene la direccion del archivo
     * @return DataRequest con la informacion obtenida
     */
    public DataRequest readXmlData(String path) {
        XStream xstream = new XStream(new DomDriver());
        File f = new File(path);
        BufferedReader entrada;
        DataRequest data = new DataRequest();

        xstream.processAnnotations(DataRequest.class);
        xstream.processAnnotations(Request.class);
        xstream.processAnnotations(Opcion.class);

        NamedMapConverter namedMapConverter = new NamedMapConverter(xstream.getMapper(), "NodeRequest", "ID", String.class, "Shema", String.class);
        xstream.registerConverter(namedMapConverter);
        
        try {
            entrada = new BufferedReader(new FileReader(f));
            if (entrada.ready()) {
                data = (DataRequest) xstream.fromXML(entrada);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

    /**
     * Lee el archivo de configuraciones, donde se han especificado las
     * peticiones
     *
     * @param input contiene la direccion del archivo
     * @return DataRequest con la informacion obtenida
     */
    public DataRequest readXmlDataFromResource(InputStream input) {

        XStream xstream = new XStream(new DomDriver());

        BufferedReader entrada;
        DataRequest data = new DataRequest();

        xstream.processAnnotations(DataRequest.class);
        xstream.processAnnotations(Request.class);
        xstream.processAnnotations(Opcion.class);

        NamedMapConverter namedMapConverter = new NamedMapConverter(xstream.getMapper(), "NodeRequest", "ID", String.class, "Shema", String.class);
        xstream.registerConverter(namedMapConverter);

        try {
            entrada = new BufferedReader(new InputStreamReader(input));
            if (entrada.ready()) {
                data = (DataRequest) xstream.fromXML(entrada);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }

}

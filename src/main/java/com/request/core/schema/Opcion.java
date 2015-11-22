package com.request.core.schema;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Permite el uso de cadenas del tipo
 * key, value. y ser manejadas por las listas 
 * de configuracion de cada peticion
 * @author NoaD
 */
@XStreamAlias("Opcion")
public class Opcion implements Serializable {
    
    String key;//identificador del nodo
    String value;//contenido del nodo

    /**
     * Constructor vacio con parametros por defecto;
     */
    public Opcion() {
    }

    /**
     * Contructor con los parametros
     * @param key es el identificador unico
     * @param value es el valor 
     */
    public Opcion(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Obtiene el key del Nodo.
     * @return String con el key
     */
    public String getKey() {
        return key;
    }

    /**
     * Modifica el key del Nodo
     * @param key contie el nuevo key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Obtiene el value del Nodo
     * @return String con el valor
     */
    public String getValue() {
        return value;
    }

    /**
     * Modifica el value del Nodo
     * @param value contien el nuevo valor
     */
    public void setValue(String value) {
        this.value = value;
    }   
}

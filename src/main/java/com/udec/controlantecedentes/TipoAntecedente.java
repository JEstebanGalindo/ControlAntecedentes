/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlantecedentes;

/**
 *Clase TipoAntecedente
 * @author julian esteban vallejo galindo
 */
public class TipoAntecedente implements java.io.Serializable{
    private static final long serialVersionUID = -5499918610561500376L;
    private String descripcion;
    private String nombre;
    private char tipo;

    /**
     * Constructor TipoAntecedente
     * @param descripcion guarda descripcion 
     * @param nombre guarda el nombre del antecedente
     * @param tipo guarda el tipo del antecedente
     */
    public TipoAntecedente(String descripcion, String nombre, char tipo) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    /**
     * getter tipo
     * @return tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * setter tipo
     * @param tipo recibe tipo
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**
     * getter descripcion
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * setter descripcion
     * @param descripcion recibe descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * getter nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setter nombre
     * @param nombre recibe nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  
}

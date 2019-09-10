/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlantecedentes;

/**
 *Clase Persona
 * @author julian esteban vallejo galindo
 */
public class Persona implements java.io.Serializable{
    private static final long serialVersionUID = -5499918610561500376L;
    private String nombre;
    private long cedula;
    private int edad;
    private String genero;

    /**
     * Constructor Persona
     * @param nombre guarda nombre
     * @param cedula guarda cedula
     * @param edad guarda edad
     * @param genero guarda genero
     */
    public Persona(String nombre, long cedula, int edad, String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.genero = genero;
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

    /**
     * getter cedula
     * @return cedula
     */
    public long getCedula() {
        return cedula;
    }

    /**
     * setter cedula
     * @param cedula recibe cedula
     */
    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    /**
     * getter edad
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * setter edad
     * @param edad recibe edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * getter genero
     * @return genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * setter genero
     * @param genero  recibe genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}

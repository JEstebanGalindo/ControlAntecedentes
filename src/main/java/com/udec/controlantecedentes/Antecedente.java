/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlantecedentes;

import java.util.Date;

/**
 *Clase del Antecedente
 * @author julian esteban vallejo galindo
 */
public class Antecedente implements java.io.Serializable{
    
    private static final long serialVersionUID = -5499918610561500376L;
    private long cedula;
    private Date fecha;
    private String descripcion;
    private String descripcionTipo;
    private String nombreCaracteristico;
    private char tipo;

    /**
     * Constructor de Antecedente
     * @param cedula guarda la cedula
     * @param fecha guarda la fecha
     * @param descripcion guarda la descripcion
     * @param descripcionTipo guarda el tipo del antecedente
     * @param nombreCaracteristico guarda el nombre caracteristico 
     * @param tipo guarda el tipo del antecedente
     */
    public Antecedente(long cedula, Date fecha, String descripcion, String descripcionTipo, String nombreCaracteristico, char tipo) {
        this.cedula = cedula;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.descripcionTipo = descripcionTipo;
        this.nombreCaracteristico = nombreCaracteristico;
        this.tipo = tipo;
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
     * getter fecha
     * @return fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * setter fecha
     * @param fecha recibe fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * getter descripciontipo
     * @return descripcionTipo
     */
    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    /**
     * setter descripcionTipo
     * @param descripcionTipo recibe descripcionTipo
     */
    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    /**
     * getter nombreCaracteristico
     * @return nombreCaracteristico
     */
    public String getNombreCaracteristico() {
        return nombreCaracteristico;
    }

    /**
     * setter nombreCaracteristico
     * @param nombreCaracteristico recibe nombreCaracteristico
     */
    public void setNombreCaracteristico(String nombreCaracteristico) {
        this.nombreCaracteristico = nombreCaracteristico;
    }

    /**
     * getter Tipo
     * @return Tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * setter Tipo
     * @param tipo recibe Tipo
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    
    
}

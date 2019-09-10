/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlantecedentes;

/**
 *metodo de inicio
 * @author julian esteban vallejo galindo
 */
public class Inicio {
    
    /**
     * metodo llama a logica menu inicio
     */
    public Inicio(){
        
        Logica logica = new Logica();
        logica.menuInicio();
    }

    /**
     * Inicio Programa
     * @param args 
     */
    public static void main(String[] args) {       
        
        Inicio inicio = new Inicio();
    }
}

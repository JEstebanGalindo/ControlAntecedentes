/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlantecedentes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.nio.file.Files.delete;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase de Logica
 * @author julian esteban vallejo galindo
 */
public class Logica {
    List<Persona> lista = new ArrayList();
    List<Persona> listaTemporal = new ArrayList();
    
    List<TipoAntecedente> listaTipoAntecedentes = new ArrayList();
    List<TipoAntecedente> listaTipoTemporal = new ArrayList();
    
    List<Antecedente> listaAntecedente = new ArrayList();
    List<Antecedente> listaAntecedenteTemporal = new ArrayList();
    
    
    List<Long> cedulasRegistradas = new ArrayList();
    List<Long> cedulasTemporal = new ArrayList();
    
    Scanner datosPersona = new Scanner(System.in);
    Scanner menu = new Scanner(System.in);
    Scanner editar = new Scanner(System.in);
    Scanner visualizar = new Scanner(System.in);
    private int opcionMenu = 0;
    private int opcioneditar;

    /**
     * metodo del menu de inicio
     */
    public void menuInicio(){
        while (opcionMenu != 8) {            
            System.out.println("1. Registrar Personas");
            System.out.println("2. Editar Personas");
            System.out.println("3. Registrar Tipo Antecedente");
            System.out.println("4. Registrar Antecedentes");
            System.out.println("5. Eliminar Antecedentes");
            System.out.println("6. Visualizar Antecedentes");
            System.out.println("7. Salir");
            opcionMenu = Integer.parseInt(menu.nextLine());
            if (opcionMenu == 1) {
                ingresarPersona();
            }else if (opcionMenu == 2){
                encontrarPersona();
            }else if (opcionMenu == 3){
                ingresarTipoAntecedente();
            }else if (opcionMenu == 4){
                ingresarAntecedente();
            }else if (opcionMenu == 5){
                eliminarAntecedente();
            }else if (opcionMenu == 6){
                visualizarAntecedentes();
            }
        }
    }
   
    /**
     * metodo para ingresar una persona
     */
    public void ingresarPersona(){
        deserializarLista();
        deserializarCedulas();
        System.out.println("Ingrese un nombre: ");
        String nombrePersona = datosPersona.nextLine();
        System.out.println("Ingrese una cedula: ");
        long cedula = Long.parseLong(datosPersona.nextLine());
        System.out.println("Ingrese una edad: ");
        int edad = Integer.parseInt(datosPersona.nextLine());
        System.out.println("Ingrese un genero: ");
        String genero = datosPersona.nextLine();
        if (listaTemporal.isEmpty()){
            Persona persona = new Persona(nombrePersona, cedula, edad, genero);
            listaTemporal.add(persona);
            cedulasTemporal.add(cedula);         
        }else if(cedulasTemporal.contains(cedula)){
            System.out.println("Esta persona ya esta Registrada en el sistema: ");
        }else{
            Persona persona = new Persona(nombrePersona, cedula, edad, genero);
            listaTemporal.add(persona);
            cedulasTemporal.add(cedula); 
        }
        serializarLista(listaTemporal);
        serializarCedulas(cedulasTemporal);
    }

    /**
     * metodo que encuentra la posicion de una persona  en la lista o si exite al menos
     */
    public void encontrarPersona(){
        deserializarLista();
        deserializarCedulas();
        Scanner encontrar = new Scanner(System.in);
        System.out.println("Ingrese la cedula de la persona que desea editar: ");
        long cedula = Long.parseLong(encontrar.nextLine());
        int index = -1;
        for(int i=0; i<cedulasTemporal.size(); i++){           
            if(cedulasTemporal.get(i) == cedula) {
                index = i;        
            }
        }
        if(index == -1){
            System.out.println("Esta persona no se encuentra registrada en el sistema...");
        }else{
            editarPersona(index);
        }
        serializarLista(listaTemporal);
        serializarCedulas(cedulasTemporal);
    }
    
    /**
     * metodo que edita los datos de una persona
     * @param index indicador de posicion
     */
    public void editarPersona(int index){       
        while (opcioneditar != 4) {       
            System.out.println("1. Editar Nombre");
            System.out.println("2. Editar Edad");
            System.out.println("3. Editar Genero");
            System.out.println("4. Salir de edicion");
            opcioneditar = Integer.parseInt(editar.nextLine());
            if (opcioneditar == 1) {
                System.out.println("Nombre actual de la persona es :" + listaTemporal.get(index).getNombre());
                System.out.println("Ingrese un nuevo nombre: ");
                String nombrePersona = editar.nextLine();
                listaTemporal.get(index).setNombre(nombrePersona);
            }else if (opcioneditar == 2){
                System.out.println("Edad actual de la persona es :" + listaTemporal.get(index).getEdad());
                System.out.println("Ingrese una nueva edad: ");
                int edad = Integer.parseInt(editar.nextLine());
                listaTemporal.get(index).setEdad(edad);
            }else if (opcioneditar == 3){
                System.out.println("Genero actual de la persona es :" + listaTemporal.get(index).getGenero());
                System.out.println("Ingrese un nuevo genero: ");
                String genero = editar.nextLine();
                listaTemporal.get(index).setGenero(genero);
            } 
        }
    }
    
    /**
     * metodo que sirve para ingresar un tipo de antecedente
     */
    public void ingresarTipoAntecedente(){
        deserializarTipoAntecedente();
        Scanner tipoAntecedente = new Scanner(System.in);
        TipoAntecedente antecedente;
        System.out.println("Ingrese un Nombre Caracteristico: ");
        String nombreAntedente = tipoAntecedente.nextLine();
        System.out.println("Ingrese una breve descripcion: ");
        String descripcion = tipoAntecedente.nextLine();
        System.out.println("Ingrese una tipo: (Positivo(p)/Negativo(n) ");
        char tipo = tipoAntecedente.nextLine().charAt(0);
        antecedente = new TipoAntecedente(descripcion, nombreAntedente, tipo);
        listaTipoTemporal.add(antecedente);
        serializarListaTipoAntecedentes(listaTipoTemporal);
    }
    
    /**
     * metodo que sirve para ingresar un antecedete
     */
    public void ingresarAntecedente(){
        deserializar();
        Scanner ingresoAntecedente = new Scanner(System.in);
        System.out.println("Ingrese la cedula de la persona que se le asignara el Antecedente: ");
        long cedula = Long.parseLong(ingresoAntecedente.nextLine());
        if(cedulasTemporal.contains(cedula)){
            Date fecha = new Date();
            System.out.println("Ingrese una breve descripcion: ");
            String descripcion = ingresoAntecedente.nextLine();
            System.out.println("------ Tipos de Antecedentes ------");
            mostrarTiposAntecedentes();
            System.out.println("Seleccione un Tipo (Id): ");
            int opcion = Integer.parseInt(ingresoAntecedente.nextLine());
            String descripcionTipo = listaTipoTemporal.get(opcion).getDescripcion();
            String nombreTipo = listaTipoTemporal.get(opcion).getNombre();
            char tipo = listaTipoTemporal.get(opcion).getTipo();
            Antecedente antecedente = new Antecedente(cedula, fecha, descripcion, descripcionTipo, nombreTipo, tipo);
            listaAntecedenteTemporal.add(antecedente);           
        }else{
            System.out.println("Esta persona no se encuentra registrada en el sistema...");
        }
        serializarCedulas(cedulasTemporal);
        serializarListaTipoAntecedentes(listaTipoTemporal);
        serializarListaAntecedente(listaAntecedenteTemporal);
    }
    
    /**
     * metodo que llama a deserializar cedulas antecedentes y tipos
     */
    public void deserializar(){
        deserializarCedulas();
        deserializarTipoAntecedente();
        deserializarListaAntecedente();  
    }
    
    /**
     * metodo que muestra los tipos de antecedentes
     */
    public void mostrarTiposAntecedentes(){
        for(int i=0; i<listaTipoTemporal.size(); i++){  
            System.out.println("Numero Id Antecedente: "+ i);
            System.out.println("Nombre Nombre Tipo Antecedente: " + listaTipoTemporal.get(i).getNombre());            
            System.out.println("Descripcion Tipo Antecedente: "+ listaTipoTemporal.get(i).getDescripcion());  
            System.out.println("Tipo Antecedente p/n: "+ listaTipoTemporal.get(i).getTipo());             
            System.out.println("-----------------------------------");
        }
    }
    
    /**
     * metodo que sirve para eliminar los antecedentes de una persona
     */
    public void eliminarAntecedente(){
        Scanner eliminar = new Scanner(System.in);
        visualizarAntecedentes();
        deserializarListaAntecedente();
        System.out.println("Digite el ID ANTECEDENTE que desea eliminar: ");
        int id = Integer.parseInt(eliminar.nextLine());
        if(listaAntecedenteTemporal.get(id).getTipo() == 'n'){
            listaAntecedenteTemporal.remove(id);
            System.out.println("Antecedente eliminado.");
        }else{
            System.out.println("-- Solo puede eliminar Negativos(n) --");
        }
        serializarListaAntecedente(listaAntecedenteTemporal);
    }
    
    /**
     * metodo que sirve para ver los antecedentes de una persona
     */
    public void visualizarAntecedentes(){
        deserializarVerAntecedentes();
        System.out.println("Ingrese la cedula de la persona que desea ver sus Antecedentes: ");
        long cedula = Long.parseLong(visualizar.nextLine());
        if(cedulasTemporal.contains(cedula)) {
            if(listaAntecedenteTemporal.isEmpty()){
                System.out.println("No hay antecedentes asignados a personas... ");
            }else{
                for(int i = 0 ; i < listaAntecedenteTemporal.size(); i++){
                    if(listaAntecedenteTemporal.get(i).getCedula() == cedula){                                          
                        System.out.println("ID ANTECENENTE: " + i);
                        System.out.println("Cedula: " + listaAntecedenteTemporal.get(i).getCedula());
                        System.out.println("Fecha: " + listaAntecedenteTemporal.get(i).getFecha());
                        System.out.println("Descripcion : " + listaAntecedenteTemporal.get(i).getDescripcion());
                        System.out.println("Tipo Antecedente: " + listaAntecedenteTemporal.get(i).getNombreCaracteristico());
                        System.out.println("Descripcion del Tipo: " + listaAntecedenteTemporal.get(i).getDescripcionTipo());
                        System.out.println("Tipo (p/n): " + listaAntecedenteTemporal.get(i).getTipo());
                        System.out.println("------------------------------------------------------------------------");
                    }
                }
            }
        }else{
            System.out.println("Persona no esta registrada en el sistema... ");
        }
        serializarVerAnetecedentes();
    } 
    
    /**
     * metodo que llama a deserializar a cedulas y antecedentes
     */
    public void deserializarVerAntecedentes(){
        deserializarCedulas();
        deserializarListaAntecedente();
    }
    
    /**
     * metodo que llama a serializar cedula y antecedente
     */
    public void serializarVerAnetecedentes(){
        serializarCedulas(cedulasTemporal);
        serializarListaAntecedente(listaAntecedenteTemporal);
    }

    /**
     * metodo que serializa la lista de personas
     * @param lista recibe lista de personas
     */
    public void serializarLista(List<Persona> lista){
        try {
            FileOutputStream fos = new FileOutputStream("per7.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fos);
            salida.writeObject(lista);
            salida.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * metod que deserealiza la lista de personas
     */
    public void deserializarLista(){
        try {
            FileInputStream is = new FileInputStream("per7.txt");
            ObjectInput oi = new ObjectInputStream(is);
            lista = (ArrayList) oi.readObject();
            oi.close();            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i =0; i<lista.size(); i++) {  
            if(lista.get(i) instanceof Persona) { 
                String nombre = ((Persona) lista.get(i)).getNombre(); 
                long cedula = ((Persona) lista.get(i)).getCedula(); 
                int edad = ((Persona) lista.get(i)).getEdad(); 
                String genero = ((Persona) lista.get(i)).getGenero();                   
                Persona persona = new Persona(nombre,cedula,edad,genero);
                listaTemporal.add(persona);                 
            }
        }
    }
    
    /**
     * metodo que serializa la lista de cedulas
     * @param cedulas lista de cedulas
     */
    public void serializarCedulas(List<Long> cedulas){
        try {
            FileOutputStream fos = new FileOutputStream("ced7.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fos);
            salida.writeObject(cedulas);
            salida.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     * metodo que deserealiza la lista de cedulas
     */
    public void deserializarCedulas(){
        try {
            FileInputStream is = new FileInputStream("ced7.txt");
            ObjectInput oi = new ObjectInputStream(is);
            cedulasRegistradas = (ArrayList) oi.readObject();
            oi.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (int i=0; i<cedulasRegistradas.size(); i++) {               
                long cedula = ((cedulasRegistradas.get(i))); 
                if(cedulasTemporal.contains(cedula)){               
                }else{
                    cedulasTemporal.add(cedula);  
                }                          
	}
    }

    /**
     * metodo que serializa la lista de los tipos de antecedentes
     * @param tipoAntecedentes lista de tipos de antecedentes
     */
    public void serializarListaTipoAntecedentes(List<TipoAntecedente> tipoAntecedentes){
        try {
            FileOutputStream fos = new FileOutputStream("TipoA7.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fos);
            salida.writeObject(tipoAntecedentes);
            salida.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
    }
     
    /**
     * metodo que deserealiza los Tiposde ANtecedentes
     */
    public void deserializarTipoAntecedente(){
        try {
            FileInputStream is = new FileInputStream("TipoA7.txt");
            ObjectInput oi = new ObjectInputStream(is);
            listaTipoAntecedentes = (ArrayList) oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i =0; i<listaTipoAntecedentes.size(); i++) {  
            if(listaTipoAntecedentes.get(i) instanceof TipoAntecedente) { 
                String descripcion = ((TipoAntecedente) listaTipoAntecedentes.get(i)).getDescripcion(); 
                String nombre = ((TipoAntecedente) listaTipoAntecedentes.get(i)).getNombre(); 
                char tipo = ((TipoAntecedente) listaTipoAntecedentes.get(i)).getTipo();                   
                TipoAntecedente tipoAntecedente = new TipoAntecedente(descripcion, nombre, tipo);                            
                listaTipoTemporal.add(tipoAntecedente);   
            }
        }
    }  
       
    /**
     * metodo que serializa la lista Antecedentes
     * @param antecedentes lista de Antecedentes
     */
    public void serializarListaAntecedente(List<Antecedente> antecedentes){
        try {
            FileOutputStream fos = new FileOutputStream("ant7.txt");
            ObjectOutputStream salida = new ObjectOutputStream(fos);
            salida.writeObject(antecedentes);
            salida.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     * metodo que deserealiza la lista Antecedente del archivo
     */
    public void deserializarListaAntecedente(){
        try {
            FileInputStream is = new FileInputStream("ant7.txt");
            ObjectInput oi = new ObjectInputStream(is);
            listaAntecedente = (ArrayList) oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i =0; i<listaAntecedente.size(); i++) {  
            if(listaAntecedente.get(i) instanceof Antecedente) { 
                long cedula = ((Antecedente) listaAntecedente.get(i)).getCedula(); 
                Date fecha = ((Antecedente) listaAntecedente.get(i)).getFecha(); 
                String descripcion = ((Antecedente) listaAntecedente.get(i)).getDescripcion();  
                String descripcionTipo = ((Antecedente) listaAntecedente.get(i)).getDescripcionTipo(); 
                String nombreCaracteristico = ((Antecedente) listaAntecedente.get(i)).getNombreCaracteristico();  
                char tipo = ((Antecedente) listaAntecedente.get(i)).getTipo();  
                Antecedente antecedente = new Antecedente(cedula, fecha,descripcion,descripcionTipo, nombreCaracteristico, tipo);
                listaAntecedenteTemporal.add(antecedente);                                 
            }
        }
    }
}

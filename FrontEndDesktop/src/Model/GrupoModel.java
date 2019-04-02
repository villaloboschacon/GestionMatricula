/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Grupo;
import Entidades.Profesor;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Steven Villalobos
 */
public class GrupoModel extends java.util.Observable{
    private Grupo actual;
    private ComboBoxModel profesores;
    private HashMap<String,String> errores;
    private String mensaje;
    private int modo;
    
    public void init(Profesor[] profesores)
    {
        setProfesores(profesores);
        setActual(new Grupo());
        clearErrors();
    }
    public void setProfesores(Profesor[] profesores){
        this.profesores = new DefaultComboBoxModel(profesores);
        setChanged();
        notifyObservers();    
    }
    public ComboBoxModel getProfesores(){
        return this.profesores;
    }
    public Grupo getActual() {
        return actual;
    }

    public void setActual(Grupo actual) {
        this.actual = actual;
        setChanged();
        notifyObservers();  
    }
    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<String,String>());
        setMensaje("");  
    }
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
}

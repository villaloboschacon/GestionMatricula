/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entidades.Grupo;
import TableModel.GrupoTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Steven Villalobos
 */
public class GruposModel extends java.util.Observable{
    private Grupo filtro;
    private GrupoTableModel tableGrupos;
    private HashMap<String,String> errores;
    private String mensaje;
    public GruposModel()
    {

    }
    public void setGrupos(List<Grupo> grupos)
    {
        int[] cols={GrupoTableModel.CODIGO,GrupoTableModel.CODIGOCURSO,GrupoTableModel.PROFESOR,GrupoTableModel.HORARIO};
        this.tableGrupos = new GrupoTableModel(grupos, cols);  
        setChanged();
        notifyObservers();
    }
    public void init()
    {
        filtro = new Grupo();
        List<Grupo> rows = new ArrayList<Grupo>();
        this.setGrupos(rows);
        clearErrors();
    }
    
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
    public Grupo getFiltro() {
        return filtro;
    }
    public GrupoTableModel getGrupoTableModel() {
        return tableGrupos;
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
}

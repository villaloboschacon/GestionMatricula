/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import Control.Control;
import Entidades.Alumno;
import Main.FrontEndDesktop;
import Model.AlumnoModel;
import Model.AlumnosModel;
import View.AlumnosView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class AlumnosController {
    private AlumnosModel model;
    private AlumnosView view;
    private Control control;
    
    public AlumnosController(Control control, AlumnosModel model, AlumnosView view)
    {
        model.init();
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void buscar() throws GlobalException
    {
        model.getFiltro().setCedula("");
        model.clearErrors();
        List<Alumno> rows;
        try
        {
            rows = control.listarAlumnos();
            if (rows.isEmpty())
            {
                model.getErrores().put("buscarButton", "Ningun registro coincide");
                model.setMensaje("Ningún registro coincide con la busqueda.");
            }
            model.setAlumnos(rows);
        }
        catch(GlobalException gl)
        {
            throw new GlobalException("Error al recuperar datos.");
        }
    }
    public void agregar()
    {
        AlumnoModel alumnoModel = FrontEndDesktop.ALUMNO_VIEW.getModel();
        alumnoModel.clearErrors();
        alumnoModel.setModo(FrontEndDesktop.MODO_AGREGAR);
        alumnoModel.setActual(new Alumno());
        FrontEndDesktop.ALUMNO_VIEW.setVisible(true);
        FrontEndDesktop.ALUMNO_VIEW.toFront();
    }
    
    public void editar(int row)
    {
        AlumnoModel alumnoModel = FrontEndDesktop.ALUMNO_VIEW.getModel();
        alumnoModel.clearErrors();
        Alumno alumno = model.getAlumnoTableModel().getRowAt(row);
        alumnoModel.setModo(FrontEndDesktop.MODO_EDITAR);
        alumnoModel.setActual(alumno);
        FrontEndDesktop.ALUMNO_VIEW.setVisible(true);
        FrontEndDesktop.ALUMNO_VIEW.toFront();
    }
    public void borrar(int row) throws GlobalException{
        Alumno seleccionado = model.getAlumnoTableModel().getRowAt(row); 
        try 
        {
            control.eliminarAlumno(seleccionado.getCedula());
        } 
        catch (GlobalException | SQLException ex)
        {
            
        }
        this.buscar();
    }
}
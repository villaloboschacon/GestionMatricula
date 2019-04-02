/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import Control.Control;
import Entidades.Profesor;
import Main.FrontEndDesktop;
import Model.ProfesorModel;
import Model.ProfesoresModel;
import View.ProfesoresView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class ProfesoresController {
    private ProfesoresModel model;
    private ProfesoresView view;
    private Control control;

    public ProfesoresController(Control control, ProfesoresModel model, ProfesoresView view)
    {
        model.init();
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void buscar() throws GlobalException, SQLException
    {
        model.getFiltro().setCedula("");
        model.clearErrors();
        List<Profesor> rows;
        rows = control.listaProfesores();
        if (rows.isEmpty())
        {
            model.getErrores().put("buscarButton", "Ningun registro coincide");
            model.setMensaje("Ningún registro coincide con la busqueda.");
        }
        model.setProfesores(rows);
    }
    public void agregar()
    {
        ProfesorModel profesorModel = FrontEndDesktop.PROFESOR_VIEW.getModel();
        profesorModel.clearErrors();
        profesorModel.setModo(FrontEndDesktop.MODO_AGREGAR);
        profesorModel.setActual(new Profesor());
        FrontEndDesktop.PROFESOR_VIEW.setVisible(true);
        FrontEndDesktop.PROFESOR_VIEW.toFront();
    }
    
    public void editar(int row)
    {
        ProfesorModel profesorModel = FrontEndDesktop.PROFESOR_VIEW.getModel();
        profesorModel.clearErrors();
        Profesor profesor = model.getProfesorTableModel().getRowAt(row);
        profesorModel.setModo(FrontEndDesktop.MODO_EDITAR);
        profesorModel.setActual(profesor);
        FrontEndDesktop.PROFESOR_VIEW.setVisible(true);
        FrontEndDesktop.PROFESOR_VIEW.toFront();
    }
    public void borrar(int row) throws GlobalException, SQLException{
        Profesor seleccionado = model.getProfesorTableModel().getRowAt(row); 
        try{
            control.eliminarProfesor(seleccionado.getCedula());
        } 
        catch (GlobalException | SQLException ex)
        {
            
        }
        this.buscar();
    }
}
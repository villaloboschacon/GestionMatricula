/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import Control.Control;
import Entidades.Grupo;
import Main.FrontEndDesktop;
import Model.GrupoModel;
import Model.GruposModel;
import View.GruposView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class GruposController {
    private GruposModel model;
    private GruposView view;
    private Control control;
    
    public GruposController(Control control, GruposModel model, GruposView view)
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
        model.getFiltro().setCodigo("");
        model.clearErrors();
        List<Grupo> rows;
        try
        {
            rows = control.listaGrupos();
            if (rows.isEmpty())
            {
                model.getErrores().put("buscarButton", "Ningun registro coincide");
                model.setMensaje("Ningún registro coincide con la busqueda.");
            }
            model.setGrupos(rows);
        }
        catch(GlobalException gl)
        {
            throw new GlobalException("Error al recuperar datos.");
        }
        catch(SQLException sql)
        {
            throw new SQLException("Error al recuperar datos.");
        }
    }
    public void agregar()
    {
        GrupoModel grupoModel = FrontEndDesktop.GRUPO_VIEW.getModel();
        grupoModel.clearErrors();
        grupoModel.setModo(FrontEndDesktop.MODO_AGREGAR);
        grupoModel.setActual(new Grupo());
        FrontEndDesktop.GRUPO_VIEW.setVisible(true);
        FrontEndDesktop.GRUPO_VIEW.toFront();
    }
    
    public void editar(int row)
    {
        GrupoModel grupoModel = FrontEndDesktop.GRUPO_VIEW.getModel();
        grupoModel.clearErrors();
        Grupo grupo = model.getGrupoTableModel().getRowAt(row);
        grupoModel.setModo(FrontEndDesktop.MODO_EDITAR);
        grupoModel.setActual(grupo);
        FrontEndDesktop.GRUPO_VIEW.setVisible(true);
        FrontEndDesktop.GRUPO_VIEW.toFront();
    }
    public void borrar(int row) throws GlobalException, SQLException{
        Grupo seleccionado = model.getGrupoTableModel().getRowAt(row); 
        try 
        {
            control.eliminarProfesor(seleccionado.getCodigo());
        } 
        catch (GlobalException | SQLException ex)
        {
            
        }
        this.buscar();
    }
}

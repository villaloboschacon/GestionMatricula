/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import AccesoDatos.GlobalException;
import Control.Control;
import Entidades.Grupo;
import Entidades.Profesor;
import Main.FrontEndDesktop;
import Model.GrupoModel;
import Model.GruposModel;
import View.GrupoView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class GrupoController {
    private Control control;
    private GrupoModel model;
    private GrupoView view;
    
    public GrupoController(Control control, GrupoModel model, GrupoView view) throws GlobalException, SQLException {
        model.init(control.listaProfesores().toArray(new Profesor[0]));
        this.control = control;
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void actualizaModel() throws GlobalException, SQLException{
        this.model.init(control.listaProfesores().toArray(new Profesor[0]));        
    }
    public void GuardarCurso(){
        GruposModel gruposModel = FrontEndDesktop.GRUPOS_VIEW.getModel();
        model.clearErrors();
        Grupo grupo = new Grupo();
        
        grupo.setProfesor((Profesor)view.getProfesorComboBox());
        if (view.getProfesorComboBox() == null){
            model.getErrores().put("Profesor", "Profesor requerido.");
        }
        grupo.setCodigo(view.getCodigoText());
        if(view.getCodigoText().isEmpty()){
            model.getErrores().put("Codigo", "Codigo requerido.");
        }
        grupo.setHorario(view.getHorarioText());
        if(view.getHorarioText().isEmpty()){
            model.getErrores().put("Horario","Horario requerido.");
        }
        grupo.setCodigoCurso((String)view.getCodigoCursoComboBox());
        if (view.getCodigoCursoComboBox() == null){
            model.getErrores().put("CodigoCurso", "El codigo del curso es requerido.");
        }
        if (model.getErrores().isEmpty()){
            try{
                List<Grupo> listaGrupos;
                switch(model.getModo()){
                    case FrontEndDesktop.MODO_AGREGAR:
                        control.insertarGrupo(grupo);
                        model.setMensaje("Grupo agregado con exito.");
                        model.setActual(new Grupo());                        
                        listaGrupos = control.listaGrupos();       
                        gruposModel.setGrupos(listaGrupos);
                        FrontEndDesktop.GRUPOS_VIEW.setModel(gruposModel);
                        view.setVisible(false);
                        break;
                    case FrontEndDesktop.MODO_EDITAR:
                        control.editarGrupo(grupo);
                        model.setMensaje("Grupo editado con exito.");
                        model.setActual(grupo);
                        listaGrupos = control.listaGrupos();       
                        gruposModel.setGrupos(listaGrupos);
                        view.setVisible(false);
                        break;
                }
            }
            catch(Exception e){
                model.getErrores().put("Codigo", "Codigo de grupo ya registrada.");
                model.setMensaje("Codigo ya registrado.");
                model.setActual(grupo);
            }
        }
        else{
            model.setMensaje("ESPACIOS VACIOS.");
            model.setActual(grupo);
        }
        
    }
}

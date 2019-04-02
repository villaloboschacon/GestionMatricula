/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import AccesoDatos.GlobalException;
import Control.Control;
import Controller.AlumnoController;
import Controller.AlumnosController;
import Controller.GrupoController;
import Controller.GruposController;
import Controller.ProfesorController;
import Controller.ProfesoresController;
import Model.AlumnoModel;
import Model.AlumnosModel;
import Model.GrupoModel;
import Model.GruposModel;
import Model.ProfesorModel;
import Model.ProfesoresModel;
import View.AlumnoView;
import View.AlumnosView;
import View.GrupoView;
import View.GruposView;
import View.ProfesorView;
import View.ProfesoresView;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Steven Villalobos
 */
public class FrontEndDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws GlobalException, SQLException {
        
        Control control = Control.instance(); 
        
        AlumnosView alumnosView = new AlumnosView();
        AlumnoView alumnoView = new AlumnoView();
        
        AlumnosModel alumnosModel = new AlumnosModel();
        AlumnoModel alumnoModel = new AlumnoModel();
        
        AlumnosController controller = new AlumnosController(control,alumnosModel,alumnosView);
        AlumnoController alumnoController = new AlumnoController(control,alumnoModel,alumnoView);
        
        ProfesorView profesorView = new ProfesorView();
        ProfesoresView profesoresView = new ProfesoresView();
        
        ProfesorModel profesorModel = new ProfesorModel();
        ProfesoresModel profesoresModel = new ProfesoresModel();
        
        ProfesoresController profesoresController = new ProfesoresController(control,profesoresModel,profesoresView);
        ProfesorController profesorController = new ProfesorController(control,profesorModel,profesorView);
        
        GrupoView grupoView = new GrupoView();
        GruposView gruposView = new GruposView();
        
        GruposModel gruposModel = new GruposModel();
        GrupoModel grupoModel = new GrupoModel();
        
        GruposController gruposController = new GruposController(control,gruposModel,gruposView);
        GrupoController grupoController = new GrupoController(control,grupoModel,grupoView);
        GRUPOS_VIEW = gruposView;
        GRUPO_VIEW = grupoView;
        PROFESORES_VIEW = profesoresView;
        PROFESOR_VIEW = profesorView;
        ALUMNOS_VIEW = alumnosView;
        ALUMNO_VIEW = alumnoView;
        MainView main = new MainView(ALUMNOS_VIEW,ALUMNO_VIEW,PROFESORES_VIEW,PROFESOR_VIEW,GRUPOS_VIEW,GRUPO_VIEW);
        main.setVisible(true);
    }
    public static GruposView GRUPOS_VIEW;
    public static GrupoView GRUPO_VIEW;
    public static ProfesorView PROFESOR_VIEW;
    public static ProfesoresView PROFESORES_VIEW;
    public static AlumnosView ALUMNOS_VIEW;
    public static AlumnoView ALUMNO_VIEW;
    public static  final int  MODO_AGREGAR = 0;
    public static final int MODO_EDITAR = 1;
    
    public static Border BORDER_ERROR = BorderFactory.createLineBorder(Color.red);
    public static Border BORDER_NOBORDER = BorderFactory.createLineBorder(Color.red);
}

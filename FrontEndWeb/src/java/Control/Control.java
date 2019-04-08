/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.GlobalException;
import AccesoDatos.ServicioAlumno;
import AccesoDatos.ServicioGrupo;
import AccesoDatos.ServicioProfesor;
import Entidades.Alumno;
import Entidades.Grupo;
import Entidades.Profesor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class Control {
    private final ServicioAlumno servicioAlumno;
    private final ServicioProfesor servicioProfesor;
    private final ServicioGrupo servicioGrupo;
    private static Control uniqueInstance;
    public static Control instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Control();
        }
        return uniqueInstance;
    }
    public Control()
    {
        servicioProfesor = new ServicioProfesor();
        servicioAlumno = new ServicioAlumno();
        servicioGrupo = new ServicioGrupo();
    }
    //ALUMNOS
    public void insertarAlumno(Alumno alumno) throws GlobalException,SQLException{
        servicioAlumno.insertarAlumno(alumno);
    }
    public void eliminarAlumno(String cedula) throws GlobalException,SQLException{
        servicioAlumno.eliminarAlumno(cedula);
    }
    public void editarAlumno(Alumno alumno) throws GlobalException,SQLException{
        servicioAlumno.editarAlumno(alumno);
    }
    public List listarAlumnos() throws GlobalException{
        return servicioAlumno.listarAlumnos();
    }
    //PROFESORES
    public boolean insertarProfesor(Profesor profesor) throws GlobalException, SQLException{
        return servicioProfesor.insertarProfesor(profesor);
    }
    public boolean eliminarProfesor(String cedula) throws GlobalException,SQLException{
        return servicioProfesor.eliminarProfesor(cedula);
    }
    public boolean editarProfesor(Profesor profesor) throws GlobalException,SQLException{
        return servicioProfesor.editarProfesor(profesor);
    }
    public List<Profesor> listaProfesores(){
        return servicioProfesor.listaProfesores();
    }
    public Profesor buscarProfesor(String cedula){
        return servicioProfesor.getProfesor(cedula);
    }
    public List<Profesor> buscarProfesores(String busqueda){
        return servicioProfesor.buscarProfesores(busqueda);
    }
    public List<Profesor> buscarProfesoresPor(String tipo,String busqueda)throws GlobalException, SQLException{
        return servicioProfesor.buscarProfesoresPor(tipo,busqueda);
    }
    //GRUPOS
    public void insertarGrupo(Grupo grupo) throws GlobalException, SQLException{
        servicioGrupo.insertarGrupo(grupo);
    }
    public void eliminarGrupo(String cedula) throws GlobalException,SQLException{
        servicioGrupo.eliminarGrupo(cedula);
    }
    public void editarGrupo(Grupo grupo) throws GlobalException, SQLException{
        servicioGrupo.editarGrupo(grupo);
    }
    public List listaGrupos() throws GlobalException, SQLException{
        return servicioGrupo.listarGrupos();
    }
}

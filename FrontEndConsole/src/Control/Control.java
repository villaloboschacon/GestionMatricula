/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import AccesoDatos.GlobalException;
import AccesoDatos.ServicioAlumno;
import Entidades.Alumno;
import java.util.Collection;

/**
 *
 * @author Steven Villalobos
 */
public class Control {
    private ServicioAlumno servicioAlumno;
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
        servicioAlumno = new ServicioAlumno();
    }
    public boolean insertarAlumno(Alumno alumno) throws GlobalException
    {
        return servicioAlumno.insertarAlumno(alumno);
    }
    public boolean eliminarAlumno(String cedula) throws GlobalException
    {
        return servicioAlumno.eliminarAlumno(cedula);
    }
    public boolean editarAlumno(Alumno alumno) throws GlobalException
    {
        return servicioAlumno.editarAlumno(alumno);
    }
    public Collection listarAlumnos() throws GlobalException{
        return servicioAlumno.listarAlumnos();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Alumno;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;



/**
 *
 * @author Steven Villalobos
 */
public class ServicioAlumno extends Servicio{
    private static final String INSERTARALUMNO = "{call INSERTARALUMNO(?,?,?,?,?)}";
    private static final String ELIMINARALUMNO = "{call ELIMINARALUMNO(?)}";
    private static final String MODIFICARALUMNO = "{call modificarAlumno(?,?,?,?,?)}";
    private static final String LISTARALUMNOS = "{?=call listarAlumnos()}";
    
    public boolean insertarAlumno(Alumno alumno) throws GlobalException  	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        
        try 
        {
            pstmt = conexion.prepareCall(INSERTARALUMNO);
            pstmt.setString(1,alumno.getCedula());
            pstmt.setString(2,alumno.getNombre());
            pstmt.setString(3,alumno.getTelefono());
            pstmt.setString(4,alumno.getEmail());
            pstmt.setDate(5,alumno.getFecha());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new GlobalException("Número de identificación duplicado.");
        } 
        finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                desconectar();
                
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Error al desconectar.");
            }
            return true;
        }
        
    }
    public List listarAlumnos() throws GlobalException  	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        ResultSet rs = null;
	ArrayList coleccion = new ArrayList();
	Alumno alumno = null;
        try
        {
                pstmt = conexion.prepareCall(LISTARALUMNOS);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                        alumno = new Alumno(rs.getString("cedula"),
                                            rs.getString("nombre"),
                                            rs.getString("telefono"),
                                            rs.getString("email"),
                                            rs.getDate("fecha_nacimiento"));
                        coleccion.add(alumno);
                }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new GlobalException("Error al recuperar datos.\n");
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos.");
            }
        }
        if (coleccion == null || coleccion.isEmpty())
        {
            throw new GlobalException("No hay datos.");
        }
        return coleccion;
        
    }
    public boolean eliminarAlumno(String cedula) throws GlobalException  	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        
        try 
        {
            pstmt = conexion.prepareCall(ELIMINARALUMNO);
            pstmt.setString(1,cedula);
            //pstmt.setString(2,alumno.getNombre());
            //pstmt.setString(3,alumno.getTelefono());
            //pstmt.setString(4,alumno.getEmail());
            //pstmt.setString(5,alumno.getFecha());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new GlobalException("Número de identificación duplicado.");
        } 
        finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                desconectar();
                
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Error al desconectar.");
            }
            return true;
        }
        
    }
    public boolean editarAlumno(Alumno alumno) throws GlobalException  	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.");
        }
        CallableStatement pstmt=null;
        
        try 
        {
            pstmt = conexion.prepareCall(MODIFICARALUMNO);
            pstmt.setString(1,alumno.getCedula());
            pstmt.setString(2,alumno.getNombre());
            pstmt.setString(3,alumno.getTelefono());
            pstmt.setString(4,alumno.getEmail());
            pstmt.setDate(5,alumno.getFecha());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            throw new GlobalException("Número de identificación duplicado.");
        } 
        finally 
        {
            try 
            {
                if (pstmt != null) 
                {
                    pstmt.close();
                }
                desconectar();
                
            } 
            catch (SQLException e) 
            {
                throw new GlobalException("Error al desconectar.");
            }
            return true;
        }
        
    }
}

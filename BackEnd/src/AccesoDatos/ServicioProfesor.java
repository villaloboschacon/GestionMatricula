/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Profesor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Steven Villalobos
 */
public class ServicioProfesor extends Servicio {
    private static final String INSERTARPROFESOR = "{call INSERTARPROFESOR(?,?,?,?)}";
    private static final String ELIMINARPROFESOR = "{call ELIMINARPROFESOR(?)}";
    private static final String MODIFICARPROFESOR = "{call MODIFICARPROFESOR(?,?,?,?)}";
    private static final String LISTARPROFESORES = "{?=call LISTARPROFESOR()}";
    
    public void insertarProfesor(Profesor profesor) throws GlobalException, SQLException
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            throw new GlobalException("No se ha localizado el driver.\n");
        } 
        catch (SQLException e) 
        {
            throw new GlobalException("La base de datos no se encuentra disponible.\n");
        }
        CallableStatement pstmt=null;
        try 
        {
            pstmt = conexion.prepareCall(INSERTARPROFESOR);
            pstmt.setString(1,profesor.getCedula());
            pstmt.setString(2,profesor.getNombre());
            pstmt.setString(3,profesor.getTelefono());
            pstmt.setString(4,profesor.getEmail());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) 
            {
                throw new GlobalException("No se pudo insertar el alumno.\n");
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
        }
    }
    public void editarProfesor(Profesor profesor) throws GlobalException,SQLException
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
            pstmt = conexion.prepareCall(MODIFICARPROFESOR);
            pstmt.setString(1,profesor.getCedula());
            pstmt.setString(2,profesor.getNombre());
            pstmt.setString(3,profesor.getTelefono());
            pstmt.setString(4,profesor.getEmail());
            
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
        } 
    }
    public List listaProfesores() 	
    {
        try 
        {
            conectar();
        } 
        catch (ClassNotFoundException e) 
        {
            try{
                throw new GlobalException("No se ha localizado el driver.");
            } 
            catch (GlobalException ex)
            {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (SQLException e) 
        {
            try {
                throw new GlobalException("La base de datos no se encuentra disponible.");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CallableStatement pstmt=null;
        ResultSet rs = null;
	ArrayList coleccion = new ArrayList();
	Profesor profesor = null;
        try
        {
                pstmt = conexion.prepareCall(LISTARPROFESORES);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                        profesor = new Profesor(rs.getString("cedula"),
                                            rs.getString("nombre"),
                                            rs.getString("telefono"),
                                            rs.getString("email")
                        );
                        coleccion.add(profesor);
                }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            try {
                throw new GlobalException("Error al recuperar datos.\n");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                try {
                    throw new GlobalException("Estatutos invalidos o nulos.");
                } catch (GlobalException ex) {
                    Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (coleccion == null || coleccion.isEmpty())
        {
            try {
                throw new GlobalException("No hay datos.");
            } catch (GlobalException ex) {
                Logger.getLogger(ServicioProfesor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return coleccion;
        
    }
    public void eliminarProfesor(String cedula) throws GlobalException,SQLException  	
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
            pstmt = conexion.prepareCall(ELIMINARPROFESOR);
            pstmt.setString(1,cedula);
            
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
        }     
    }
}

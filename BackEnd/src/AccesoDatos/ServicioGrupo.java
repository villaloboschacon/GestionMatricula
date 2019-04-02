/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Alumno;
import Entidades.Grupo;
import Entidades.Profesor;
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
public class ServicioGrupo extends Servicio{
    private static final String INSERTARGRUPO = "{call INSERTARGRUPO(?,?,?,?)}";
    private static final String ELIMINARGRUPO = "{call ELIMINARGRUPO(?)}";
    private static final String MODIFICARGRUPO = "{call MODIFICARGRUPO(?,?,?,?)}";
    private static final String LISTARGRUPOS = "{?=call LISTARGRUPOS()}";  
    
    public void insertarGrupo(Grupo grupo) throws GlobalException{
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
            pstmt = conexion.prepareCall(INSERTARGRUPO);
            pstmt.setString(1,grupo.getCodigo());
            pstmt.setString(2,grupo.getCodigoCurso());
            pstmt.setString(4,grupo.getHorario());
            pstmt.setString(3,grupo.getProfesor().getCedula());          
            
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
    public void editarGrupo(Grupo grupo) throws GlobalException{
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
            pstmt = conexion.prepareCall(MODIFICARGRUPO);
            pstmt.setString(1,grupo.getCodigo());
            pstmt.setString(2,grupo.getCodigoCurso());
            pstmt.setString(4,grupo.getHorario());
            pstmt.setString(3,grupo.getProfesor().getCedula());          
            
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
    public void eliminarGrupo(String idGrupo) throws GlobalException{
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
    }
    public List<Grupo> listarGrupos() throws GlobalException{
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
	Grupo grupo = null;
        try
        {
                pstmt = conexion.prepareCall(LISTARGRUPOS);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    coleccion.add(grupo(rs));
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
    private Profesor profesor(ResultSet rs){
        try 
        {
            Profesor profesor = new Profesor();
            profesor.setCedula(rs.getString("CEDULA_PROFESOR"));
            profesor.setNombre(rs.getString("Nombre"));
            profesor.setTelefono(rs.getString("Telefono"));
            profesor.setEmail(rs.getString("Email"));
            return profesor;
        } 
        catch (SQLException ex)
        {
            return null;
        }
    }
    private Grupo grupo(ResultSet rs){
        try 
        {
            Grupo grupo = new Grupo();
            grupo.setCodigo(rs.getString("COD_GRUPO"));
            grupo.setCodigoCurso(rs.getString("CODIGO_CURSO"));
            grupo.setHorario(rs.getString("HORARIO"));

            grupo.setProfesor(profesor(rs));
            return grupo;
        } 
        catch (SQLException ex)
        {
            return null;
        }
    }
    private Alumno alumno(ResultSet rs){
        try 
        {
            Alumno alumno = new Alumno();
            alumno.setCedula(rs.getString("cedula"));
            alumno.setNombre(rs.getString("nombre"));
            alumno.setTelefono(rs.getString("telefono"));
            alumno.setEmail(rs.getString("email"));
            alumno.setFecha(rs.getDate("FECHA_NACIMIENTO"));
            return alumno;
        } 
        catch (SQLException ex)
        {
            return null;
        }
    }
}

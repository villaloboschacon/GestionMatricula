/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import AccesoDatos.GlobalException;
import Control.Control;
import Entidades.Alumno;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Steven Villalobos
 */
public class FrontEndConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Control control = Control.instance();    
         System.out.print("Bienvenido al sistema de control de alumnos.\n---------------------------------------------------\n");
         boolean flag = true;
         while(flag)
         {
            System.out.print("1.Lista de alumnos.\n2.Agregar estudiante.\n3.Editar estudiante.\n4.Eliminar estudiante.\n5.Salir del sistema.\n¿Cual opción desea realizar?\n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch(input)
            {
               case "1":
                    System.out.print("Lista de estudiantes.\n------------------------------------------------------------------------------------------------\n");
                    System.out.print("Cédula\t\tNombre\t\tTeléfono\tCorreo electronico.\t\tFecha\n");
                    System.out.print("------------------------------------------------------------------------------------------------\n");
                    try
                    {
                        if(!control.listarAlumnos().isEmpty())
                        {
                            ArrayList<Alumno> lista = (ArrayList<Alumno>) control.listarAlumnos();
                            for (Alumno alumno : lista) 
                            {
                                System.out.print(alumno.getCedula()+"\t");
                                System.out.print(alumno.getNombre()+"\t");
                                System.out.print(alumno.getTelefono()+"\t");
                                System.out.print(alumno.getEmail()+"\t");
                                System.out.print(alumno.getFecha()+"\n");
                                alumno.getCedula();
                            }
                        }
                    }
                    catch(GlobalException gl)
                    {
                        
                    }
                    

                    System.out.print("------------------------------------------------------------------------------------------------\n");
                    break;
               case "2":
                   Alumno alumno = new Alumno();
                   System.out.print("Ingrese la información del estudiante.\nCédula\n");
                   Scanner alumnoInput = new Scanner(System.in);
                   String cedAlumno =scanner.nextLine();
                   alumno.setCedula(cedAlumno);
                   alumno.setEmail(cedAlumno);
                   alumno.setFecha(new Date(2019,1,1));
                   alumno.setTelefono(cedAlumno);
                   alumno.setNombre(cedAlumno);
                   
                   try
                   {
                       control.insertarAlumno(alumno);
                   }
                   catch(GlobalException gl)
                   {
                        GlobalException globalException = new GlobalException("que loco.");
                   }
                   
                    break;
               case "3":
                        System.out.print("Ingrese la cédula del estudiante que quiere eliminar.\n");
                        Scanner editar = new Scanner(System.in);
                        String editarinput = scanner.nextLine();
                        System.out.print("--------------------\n");
                        System.out.print("Eliminado con exito.\n");
                        System.out.print("--------------------\n");
                    break;
               case "4":
                        System.out.print("Ingrese la cédula del estudiante que quiere ediat.\n");
                        Scanner eliminar = new Scanner(System.in);
                        String eliminarinput = scanner.nextLine();
                        System.out.print("--------------------\n");
                        System.out.print("Editado con exito.\n");
                        System.out.print("--------------------\n");
                    break;
               case "5": flag = false;
                    break;
               default: System.out.print("Opción no valida.\n");
                   break;

            }
         }
    }
}

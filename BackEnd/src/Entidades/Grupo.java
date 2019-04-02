/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Steven Villalobos
 */
public class Grupo {
    private String codigo;
    private String codigoCurso;
    private String horario;
    private Profesor profesor;

    public Grupo() {       
        this.codigo = "";
        this.codigoCurso = "";
        this.horario = "";
        this.profesor = new Profesor();
    }

    public Grupo(String codigo, String codigoCurso, String horario, Profesor profesor) {
        this.codigo = codigo;
        this.codigoCurso = codigoCurso;
        this.horario = horario;
        this.profesor = profesor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public String getHorario() {
        return horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motogt.modelo;

/**
 *
 * @author Maria Camila
 */
public class Usuario {
    private short  id;
    private String nombre;
    private String identificacion;
    private String nacionalidad;
    private String piloto;
    private int idMoto;
    private String tipoMoto;
    private String colorMoto;
    private String cilindraje;
    private String tiempoClasificacion;
    private String email;
    private String password;
 
    public Usuario() {
    }
    
     public Usuario (short id,String nombre,String identificacion,String nacionalidad,String piloto,int idMoto,String tipoMoto,String colorMoto,
             String cilindraje,String tiempoClasificacion,String email,String password)
     {
      this.id = id;
      this.nombre = nombre;
      this.identificacion = identificacion;
      this.nacionalidad = nacionalidad;
      this.piloto = piloto;
      this.idMoto = idMoto;
      this.tipoMoto = tipoMoto;
      this.colorMoto = colorMoto;
      this.cilindraje = cilindraje;
      this.tiempoClasificacion = tiempoClasificacion;
      this.email = email;
      this.password = password;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getIdMoto() {
        return idMoto;
    }

    public void setIdMoto(int idMoto) {
        this.idMoto = idMoto;
    }

    public String getTipoMoto() {
        return tipoMoto;
    }

    public void setTipoMoto(String tipoMoto) {
        this.tipoMoto = tipoMoto;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }
    

    public String getColorMoto() {
        return colorMoto;
    }

    public void setColorMoto(String colorMoto) {
        this.colorMoto = colorMoto;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTiempoClasificacion() {
        return tiempoClasificacion;
    }

    public void setTiempoClasificacion(String tiempoClasificacion) {
        this.tiempoClasificacion = tiempoClasificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

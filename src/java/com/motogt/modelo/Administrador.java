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
public class Administrador {
    private String admiNombre;

    public String getAdmiNombre() {
        return admiNombre;
    }

    public void setAdmiNombre(String admiNombre) {
        this.admiNombre = admiNombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    private Usuario usuario;
    
    public Administrador() {
    }
    
    
}

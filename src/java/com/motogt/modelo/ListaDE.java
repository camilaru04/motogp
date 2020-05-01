/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motogt.modelo;

import com.motogt.excepciones.UsuarioExcepcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Maria Camila
 */
    public class ListaDE implements Serializable{
  private NodoDE cabeza;

    public ListaDE() {
    }

    public NodoDE getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoDE cabeza) {
        this.cabeza = cabeza;
    }
    
    public void adicionarNodo(Usuario usuario)
    {
        if(cabeza ==null)
        {
            cabeza = new NodoDE(usuario);
        }
        else
        {
            //Lamo a mi ayudante
            NodoDE temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new NodoDE(usuario));
            temp.getSiguiente().setAnterior(temp);
            
        }        
    }
    public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            NodoDE temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
     public String obtenerListadoUsuarios()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
        return listarUsuarios("");
    }
    
    public String listarUsuarios(String listado)
    {
        if(cabeza !=null)
        {
            NodoDE temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay Usuarios";
    }
    
    
     public List obtenerListaUsuarios()
    {
        List<Usuario> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarUsuarios(listado);
        return listado;
    }
    
    public void listarUsuario(List listado)
    {
        if(cabeza !=null)
        {
            NodoDE temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();                
            }            
        }
        
    }

    private void listarUsuarios(List<Usuario> listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void eliminarUsuario(short id ) throws UsuarioExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getId()==id)
            {
                cabeza=cabeza.getSiguiente();
                cabeza.setAnterior(null);
                return;
            }
            else
            {
                NodoDE temp=cabeza;
                while(temp.getSiguiente()!=null)
                {
                    if(temp.getSiguiente().getDato().getId()== id)
                    {
                        //el que sigue es el que hay que eliminar
                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
                        if(temp.getSiguiente()!=null)
                            temp.getSiguiente().setAnterior(temp);
                        return;
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new UsuarioExcepcion("El id "+ id +" no existe en la lista");
            }
        }
        throw new UsuarioExcepcion("La lista de usuarios está vacía");
    }
    
      public Usuario obtenerUsuario(short id ) throws UsuarioExcepcion
    {
        if(cabeza !=null)
        {
            if(cabeza.getDato().getId()==id)
            {                
                return cabeza.getDato();
            }
            else
            {
                NodoDE temp=cabeza;
                while(temp!=null)
                {
                    if(temp.getDato().getId()== id)
                    {                                                
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }
                
                throw new UsuarioExcepcion("El id "+id +" no existe en la lista");
            }
        }
        throw new UsuarioExcepcion("La id de infantes está vacía");
    }
   
}
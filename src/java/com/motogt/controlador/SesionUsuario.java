/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motogt.controlador;

import com.motogt.excepciones.UsuarioExcepcion;
import com.motogt.modelo.ListaDE;
import com.motogt.modelo.NodoDE;
import com.motogt.modelo.Usuario;
import com.motogt.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author Maria Camila
 */
@Named(value = "sesionUsuario")
@SessionScoped
public class SesionUsuario implements Serializable {
    private ListaDE listaUsuario;
    private Usuario usuario;
    private String alInicio="1";
    private boolean deshabilitarFormulario=true;
    private NodoDE ayudante;   
    private String textoVista="Gráfico";
    
    private List listadoUsuario;
    
    private DefaultDiagramModel model;
    
    private short idEliminar;

    private short UsuarioSeleccionado;
    
    private Usuario UsuarioDiagrama;
    /**
     * Creates a new instance of SesionUsuario
     */
    public SesionUsuario() {
        
    }
//    @PostConstruct
//    private void inicializar(){
//        listaUsuario = new ListaDE();        
//        //LLenado de la bds
//        listaUsuario.adicionarNodo(new Usuario());
//        ayudante = listaUsuario.getCabeza();
//        usuario = ayudante.getDato();     
//        //Me llena el objeto List para la tabla
//        listadoUsuario = listaUsuario.obtenerListaUsuarios();
//        pintarLista();
//    }

    public List getListadoUsuario() {
        return listadoUsuario;
    }

    public void setListadoUsuario(List listadoUsuario) {
        this.listadoUsuario = listadoUsuario;
    }

    public short getUsuarioSeleccionado() {
        return UsuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(short UsuarioSeleccionado) {
        this.UsuarioSeleccionado = UsuarioSeleccionado;
    }

    public Usuario getUsuarioDiagrama() {
        return UsuarioDiagrama;
    }

    public void setUsuarioDiagrama(Usuario carreraDiagrama) {
        this.UsuarioDiagrama = carreraDiagrama;
    }
    public DiagramModel getModel() {
        return model;
    }
     
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
         
        if(label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
         
        return conn;
    }

    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }


    public short getIdEliminar() {
        return idEliminar;
    }

    public void setIdEliminar(short idEliminar) {
        this.idEliminar = idEliminar;
    }
    
    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

    public ListaDE getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ListaDE listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }
    
    public void guardarUsuario()
    {
        //obtiene el consecutivo
        usuario.setId((short)(listaUsuario.contarNodos()+1));
        if(alInicio.compareTo("1")==0)
        {
            listaUsuario.adicionarNodo(usuario);
        }
        else
        {
            listaUsuario.adicionarNodo(usuario);
        }  
        //Vuelvo a llenar la lista para la tabla
        listadoUsuario = listaUsuario.obtenerListaUsuarios();
        pintarLista();
        deshabilitarFormulario=true;
        JsfUtil.addSuccessMessage("El infante se ha guardado exitosamente");
        
    }
    
    public void empezarSesion(){}   
 
    public void habilitarFormulario()
    {
        deshabilitarFormulario=false;
        usuario = new Usuario();
    }
    
    public void revisarUsuario(){}
    
    public void regresar()
    {
        if(ayudante.getAnterior()!=null)
        {
            ayudante = ayudante.getAnterior();
            usuario = ayudante.getDato();
        }        
    }
        
    public void adelantar()
    {
        if(ayudante.getSiguiente()!=null)
        {
            ayudante = ayudante.getSiguiente();
            usuario = ayudante.getDato();
        }        
    }

    public void cambiarVistaUsuario()
    {
        if(textoVista.compareTo("Tabla")==0)
        {
            textoVista = "Gráfico";
        }
        else
        {
            textoVista = "Tabla";
        }
    }
    
    public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaUsuario.getCabeza() != null) {
            //llamo a mi ayudante
            NodoDE temp = listaUsuario.getCabeza();
            int posX=2;
            int posY=2;
            //recorro la lista de principio a fin
            while(temp !=null)
            {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo()+" "+
                        temp.getDato().getNombre(), 
                        posX+"em", posY+"em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
                
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                model.addElement(ele);                    
                temp=temp.getSiguiente();
                posX=  posX+5;
                posY= posY+6;
            }            
           
            //Pinta las flechas            
            for(int i=0; i < model.getElements().size() -1; i++)
            {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1), 
                        model.getElements().get(i+1).getEndPoints().get(0), "Sig"));
                
                
                model.connect(createConnection(model.getElements().get(i+1).getEndPoints().get(2), 
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }
            
        }
    }
    
    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");
         
        UsuarioSeleccionado = Short.valueOf(id.replaceAll("frmInfante:diagrama-", ""));
        
    }

    public void eliminarPiloto()
    {
        if(idEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaUsuario.eliminarUsuario(idEliminar);
                JsfUtil.addSuccessMessage("Usuario"+idEliminar +" eliminado.");
            }
            catch(UsuarioExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+idEliminar+ " no es válido");
        }
    }
    
    public void crearPiloto(){}
    public void actualizarPiloto(){}
    public void consultarPiloto(){}
    
    public void eliminarCarrera()
    {
        if(idEliminar >0)
        {
            //llamo el eliminar de la lista
            try{
                listaUsuario.eliminarUsuario(idEliminar);
                JsfUtil.addSuccessMessage("Usuario"+idEliminar +" eliminado.");
            }
            catch(UsuarioExcepcion e)
            {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        }
        else
        {
            JsfUtil.addErrorMessage("El código a eliminar "+idEliminar+ " no es válido");
        }
    } 
    
    public void crearCarrera(){}
    public void actualizarCarrera(){}
    public void consultarCarrera(){}
    
    public void obtenerUsuarioDiagrama()
    {
        try {
            UsuarioDiagrama = listaUsuario.obtenerUsuario(UsuarioSeleccionado);
        } catch (UsuarioExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void comenzarCarrera(){}
    
    public void finalizarCarrera()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Usuario infTemporal = listaUsuario.obtenerUsuario(UsuarioSeleccionado);
            // Eliminar el nodo
            listaUsuario.obtenerUsuario(UsuarioSeleccionado);
            // Adicionarlo al final
            listaUsuario.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (UsuarioExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void salirCarrera()
    {
        try {
            ///Buscar el infante y guardar los datos en una variable temporal
            Usuario infTemporal = listaUsuario.obtenerUsuario(UsuarioSeleccionado);
            // Eliminar el nodo
            listaUsuario.eliminarUsuario(UsuarioSeleccionado);
            // Adicionarlo al inicio
            listaUsuario.adicionarNodo(infTemporal);
            
            pintarLista();
        } catch (UsuarioExcepcion ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
    
}

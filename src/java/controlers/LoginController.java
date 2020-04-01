/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;


import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Negro
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable{

    private Usuario usuario;
    private Usuario usuarioAutenticado=null;
    List<Usuario>listado;
    
    private final static Logger LOGGER=Logger.getLogger("controller.LoginController");
    
    @EJB
    private UsuarioDAO ejbDAO;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        
        usuario=new Usuario();
        
    }

    public void login() throws IOException{
        
        FacesContext ctx =FacesContext.getCurrentInstance();
        
        Usuario res = ejbDAO.encontrarUsuarioPorLogin(usuario.getCorreo(), usuario.getClave());
        
        if (res!=null){
            LOGGER.log(Level.INFO, "BIENVENIDO");
            ctx.getExternalContext().redirect("home.xhtml"); 
        }else{
            LOGGER.log(Level.SEVERE, "NO ENCONTRADO");
            ctx.getExternalContext().redirect("index.xhtml"); 
        }
        usuario=new Usuario();
    }
    public List<Usuario>getListado(){
        listado=ejbDAO.listar();
        return listado;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO getEjbDAO() {
        return ejbDAO;
    }

    public void setEjbDAO(UsuarioDAO ejbDAO) {
        this.ejbDAO = ejbDAO;
    }
    
   
    
}

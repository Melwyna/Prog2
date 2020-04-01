/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import model.Usuario;

/**
 *
 * @author Negro
 */
@Stateless
public class UsuarioDAO {

    private final static Logger LOGGER = Logger.getLogger("dao.UsuarioDAO");

    private EntityManagerFactory factory;
    private EntityManager em;

    public void crear(Usuario entity) {

        em.persist(entity);

    }

    public void editar(Usuario entity) {

        em.merge(entity);

    }

    public void eliminar(Usuario entity) {

        em.remove(em.merge(entity));

    }

    public Usuario encontrarUsuario(String correo) {

        return em.find(Usuario.class, correo);

    }

    public void crearConexion() {
        factory = Persistence.createEntityManagerFactory("Prog2PU");
        em = factory.createEntityManager();

    }

    public void cerrarConexion() {

        em.close();
    }

    public Usuario encontrarUsuarioPorLogin(String correo, String clave) {

        crearConexion();
        TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.correo= :mail AND u.clave= :pass",Usuario.class);
        q.setParameter("mail", correo);
        q.setParameter("pass", clave);
        Usuario res = new Usuario();
        try {
            res = q.getSingleResult();
            return (Usuario) res;
        } catch (NoResultException ex) {
            LOGGER.severe("ERROR AL CONSULTAR " + ex.getMessage() + "\n\n" + correo + "\n\n" + clave);
            return null;
        } catch (NonUniqueResultException ex2) {
            LOGGER.severe("ERROR AL CONSULTAR . DUPLICADO");
            return null;
        } catch (ClassCastException ex) {
            LOGGER.info("Object classloader: " + res.getClass().getClassLoader());
            LOGGER.info("Target class classloader: " + Usuario.class.getClassLoader());
            if (res.getClass().getClassLoader() != Usuario.class.getClassLoader()) {
                LOGGER.severe("Different classloaders detected!");

            }
            return null;
        } finally {
            LOGGER.info("CONEXIÓN CERRADA");
            cerrarConexion();
        }

    }

    public List<Usuario> listar() {

        crearConexion();
        Query q = em.createQuery("SELECT u FROM Usuario u");

        try {
            return q.getResultList();
        } catch (Exception ex) {
            LOGGER.severe("ERROR AL CONSULTAR");
            return null;
        } finally {
            LOGGER.info("CONEXIÓN CERRADA");
            cerrarConexion();
        }

    }

}

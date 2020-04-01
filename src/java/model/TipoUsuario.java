/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Negro
 */
@Entity
@Table(name="TIPO_USUARIO")
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Basic(optional=false)
    @NotNull
    @Column(name="ID")
    private Integer id;
    
    @Basic(optional=false)
    @Size(min=5, max=15)
    @Column(name="NOMBRE_TIPO")
    private String nombreTipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tipoUsuarioId")
    private List<Usuario>usuariosTipo;

    public List<Usuario> getUsuariosTipo() {
        return usuariosTipo;
    }

    public void setUsuariosTipo(List<Usuario> usuariosTipo) {
        this.usuariosTipo = usuariosTipo;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoUsuario[ id=" + id + " ]";
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
    
}

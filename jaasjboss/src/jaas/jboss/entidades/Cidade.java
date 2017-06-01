/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jaas.jboss.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luiz Gustavo
 */
@Entity
@Table(name = "cidade")
@NamedQueries({@NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"), @NamedQuery(name = "Cidade.findByCodigo", query = "SELECT c FROM Cidade c WHERE c.codigo = :codigo"), @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.nome = :nome"), @NamedQuery(name = "Cidade.findByEstado", query = "SELECT c FROM Cidade c WHERE c.estado = :estado")})
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "estado")
    private String estado;

    public Cidade() {
    }

    public Cidade(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jaas.jboss.entidades.Cidade[codigo=" + codigo + "]";
    }

}

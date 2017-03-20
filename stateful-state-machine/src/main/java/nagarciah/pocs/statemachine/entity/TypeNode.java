/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nagarciah.pocs.statemachine.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 */
@Entity
public class TypeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTYPE_NODE")
    private Integer idtypeNode;

    @Size(max = 45)
    @Column(name = "NAME")
    private String name;
    
    @Size(max = 45)
    @Column(name = "DETAIL")
    private String detail;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeNodeIdtypeNode")
    private Collection<Node> nodeCollection;

    public TypeNode() {
    }

    public TypeNode(Integer idtypeNode) {
        this.idtypeNode = idtypeNode;
    }

    public Integer getIdtypeNode() {
        return idtypeNode;
    }

    public void setIdtypeNode(Integer idtypeNode) {
        this.idtypeNode = idtypeNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }
}

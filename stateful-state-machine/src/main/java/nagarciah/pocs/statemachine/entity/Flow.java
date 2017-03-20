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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 */
@Entity
public class Flow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDFLOW")
    private Integer idflow;
    
    @Size(max = 45)
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flowIdflow")
    private Collection<Node> nodeCollection;
    
    @JoinColumn(name = "MENU_IDMENU", referencedColumnName = "IDMENU")
    @ManyToOne(optional = false)
    private Menu menuIdmenu;

    public Flow() {
    }

    public Flow(Integer idflow) {
        this.idflow = idflow;
    }

    public Integer getIdflow() {
        return idflow;
    }

    public void setIdflow(Integer idflow) {
        this.idflow = idflow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public Menu getMenuIdmenu() {
        return menuIdmenu;
    }

    public void setMenuIdmenu(Menu menuIdmenu) {
        this.menuIdmenu = menuIdmenu;
    }
}

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMENU")
    private Integer idmenu;

    @Size(max = 45)
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "NODOINIT_IDNODO")
    private Integer nodoinitIdnodo;
    
    @JoinColumn(name = "SERVICES_IDSERVICES", referencedColumnName = "IDSERVICES")
    @ManyToOne(optional = false)
    private Services servicesIdservices;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuIdmenu")
    private Collection<Flow> flowCollection;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNodoinitIdnodo() {
        return nodoinitIdnodo;
    }

    public void setNodoinitIdnodo(Integer nodoinitIdnodo) {
        this.nodoinitIdnodo = nodoinitIdnodo;
    }

    public Services getServicesIdservices() {
        return servicesIdservices;
    }

    public void setServicesIdservices(Services servicesIdservices) {
        this.servicesIdservices = servicesIdservices;
    }

    public Collection<Flow> getFlowCollection() {
        return flowCollection;
    }

    public void setFlowCollection(Collection<Flow> flowCollection) {
        this.flowCollection = flowCollection;
    }
    
}

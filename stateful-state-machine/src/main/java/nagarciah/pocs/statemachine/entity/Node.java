/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nagarciah.pocs.statemachine.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@Entity
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDNODE")
    private Integer idnode;

    @Size(max = 45)
    @Column(name = "NAME")
    
    private String name;
    @JoinColumn(name = "FLOW_IDFLOW", referencedColumnName = "IDFLOW")
    @ManyToOne(optional = false)
    
    private Flow flowIdflow;
    @JoinColumn(name = "TYPE_NODE_IDTYPE_NODE", referencedColumnName = "IDTYPE_NODE")
    @ManyToOne(optional = false)
    private TypeNode typeNodeIdtypeNode;

    public Node() {
    }

    public Node(Integer idnode) {
        this.idnode = idnode;
    }

    public Integer getIdnode() {
        return idnode;
    }

    public void setIdnode(Integer idnode) {
        this.idnode = idnode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flow getFlowIdflow() {
        return flowIdflow;
    }

    public void setFlowIdflow(Flow flowIdflow) {
        this.flowIdflow = flowIdflow;
    }

    public TypeNode getTypeNodeIdtypeNode() {
        return typeNodeIdtypeNode;
    }

    public void setTypeNodeIdtypeNode(TypeNode typeNodeIdtypeNode) {
        this.typeNodeIdtypeNode = typeNodeIdtypeNode;
    }
}

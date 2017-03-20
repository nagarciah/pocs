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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@Entity
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPROPERTY")
    private Integer idproperty;

    @Size(max = 45)
    @Column(name = "TYPE")
    private String type;

    public Property() {
    }

    public Property(Integer idproperty) {
        this.idproperty = idproperty;
    }

    public Integer getIdproperty() {
        return idproperty;
    }

    public void setIdproperty(Integer idproperty) {
        this.idproperty = idproperty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    
}

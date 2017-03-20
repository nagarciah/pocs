package com.nagarciah.trainning.statemachine.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Flow implements Serializable {
	private static final long serialVersionUID = 5221242146873497303L;
	
	@Id
	@GeneratedValue
	Long id;
	String name;

	@OneToMany(mappedBy="parentFlow", fetch=FetchType.EAGER)
	Collection<Node> nodes;
	
	@OneToMany(mappedBy="parentFlow", fetch=FetchType.EAGER)
	Collection<Transition> transitions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Collection<Node> nodes) {
		this.nodes = nodes;
	}
	public Collection<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(Collection<Transition> transitions) {
		this.transitions = transitions;
	}
}

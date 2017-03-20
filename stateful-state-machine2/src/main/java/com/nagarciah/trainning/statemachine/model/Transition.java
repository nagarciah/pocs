package com.nagarciah.trainning.statemachine.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Transition implements Serializable {
	
	private static final long serialVersionUID = -4794143381310544868L;
	
	@Id
	@GeneratedValue
	Long id;
	String className;
	
	@OneToOne(fetch=FetchType.EAGER)
	Node fromNode;

	@OneToOne(fetch=FetchType.EAGER)
	Node toNode;
	
	@ManyToOne
	@JoinColumn(name="flow_id")
	Flow parentFlow;
	
	@Transient
	Condition condition;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Node getFromNode() {
		return fromNode;
	}
	public void setFromNode(Node from) {
		this.fromNode = from;
	}
	public Node getToNode() {
		return toNode;
	}
	public void setToNode(Node to) {
		this.toNode = to;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String evaluatingMethodQualifiedName) {
		this.className = evaluatingMethodQualifiedName;
	}
	public Flow getParentFlow() {
		return parentFlow;
	}
	public void setParentFlow(Flow parentFlow) {
		this.parentFlow = parentFlow;
	}
	public Condition getCondition() {
		if(condition==null && getClassName()!=null){
			condition = new Condition(getClassName());
		}
		return condition;
	}
}

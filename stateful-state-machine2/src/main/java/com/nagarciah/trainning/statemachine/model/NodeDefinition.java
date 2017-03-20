package com.nagarciah.trainning.statemachine.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Node implements Serializable {
	
	private static final long serialVersionUID = -5369597852503644996L;
	
	@Id
	@GeneratedValue
	Long id;

	String name;
	String description;
	String nodeProcessorClassName;

	@OneToOne
	@JoinColumn(name="node_type_id")
	NodeType nodeType;
	
	@ManyToOne
	@JoinColumn(name="flow_id")
	Flow parentFlow;
	
	@ElementCollection(fetch=FetchType.EAGER)
	Map<String, String> attributes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public Flow getParentFlow() {
		return parentFlow;
	}

	public void setParentFlow(Flow parentFlow) {
		this.parentFlow = parentFlow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getNodeProcessorClassName() {
		return nodeProcessorClassName;
	}

	public void setNodeProcessorClassName(String nodeProcessorClassName) {
		this.nodeProcessorClassName = nodeProcessorClassName;
	}
}

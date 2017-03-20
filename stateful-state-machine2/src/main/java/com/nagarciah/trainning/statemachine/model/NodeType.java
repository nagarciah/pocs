package com.nagarciah.trainning.statemachine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NodeType implements Serializable {
	
	private static final long serialVersionUID = -7548642948565565900L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	String modelClassName;
	String friendlyName = "Definicion de nodo base.";
	String description = "Una plantilla para encapsular los atributos comunes y sirve de base para definiciones de nodo mas complejas.";
	
	@Column(name="node_usage")
	String usage = "No se debe usar directamente, deben crearse subclasesque agreguen atributos especificos de nuevos tipos de nodo.";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getModelClassName() {
		return modelClassName;
	}

	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	};
}

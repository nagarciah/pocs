package com.nagarciah.trainning.statemachine.service;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import com.nagarciah.trainning.statemachine.model.Node;
import com.nagarciah.trainning.statemachine.model.NodeType;
import com.nagarciah.trainning.statemachine.util.BeanAsMap;

@Service
public class NodeFactory {

	// TODO Leerlo de base de datos si se suministra un repository al factory
	public Object createNodeModel(Node node) {
		try {
			String nodeClassName = node.getNodeType().getModelClassName();
			Map<String, String> attributes = node.getAttributes();
			
			Class<?> nodeModelClass = ClassUtils.resolveClassName(nodeClassName, ClassUtils.getDefaultClassLoader());
			Object nodeModel = BeanUtils.instantiateClass(nodeModelClass);
			BeanAsMap.asMap(nodeModel).putAll(attributes);
			return nodeModel;
		} catch (Exception e) {
			throw new NodeFactoryException("Error creando nodo", e);
		}
	}
	
	/* TODO Cambiar a CreateNodeModel
	public NodeType createNode(String nodeClassName, Map<String, String> params) {
		try {
			Class<?> nodeClass = ClassUtils.resolveClassName(nodeClassName, ClassUtils.getDefaultClassLoader());
			NodeType node = (NodeType) BeanUtils.instantiateClass(nodeClass);
			BeanAsMap.asMap(node).putAll(params);
			return node;
		} catch (Exception e) {
			throw new NodeFactoryException("Error creando nodo", e);
		}
	}*/
}

package com.nagarciah.trainning.statemachine.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nagarciah.trainning.statemachine.Application;
import com.nagarciah.trainning.statemachine.model.Flow;
import com.nagarciah.trainning.statemachine.model.Node;
import com.nagarciah.trainning.statemachine.nodes.FormNode;
import com.nagarciah.trainning.statemachine.service.FlowFactory;
import com.nagarciah.trainning.statemachine.service.NodeFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NodeFactoryTests {
	
	@Autowired
	NodeFactory nodeFactory;
	
	@Autowired
	FlowFactory flowFactory;

	@Test
	public void createFormNode() {
		Flow flow = flowFactory.createFlow(1L);
		
		assertThat(flow.getNodes(), is(iterableWithSize(greaterThan(0))));
		
		Node node = flow.getNodes().iterator().next();
		String nodeClassName = node.getNodeType().getModelClassName();
		Map<String, String> attributes = node.getAttributes(); 
		
		
		Object nodeModel = nodeFactory.createNodeModel(node);
		
		assertThat(nodeModel, instanceOf(FormNode.class));
		
		FormNode formNode = (FormNode) nodeModel;
		
		assertThat(
				formNode.getClass().getName(), 
				equalTo(nodeClassName));
		
		assertThat(
				formNode.getLabel(), 
				equalToIgnoringCase(attributes.get("label")));
		
		assertThat(
				formNode.getInput(), 
				equalToIgnoringCase(attributes.get("input")));
	}
	/*
	@Test
	public void createFormNode() {
		String nodeClassName = FormStep.class.getName();
		Map<String, String> params = new HashMap<String, String>();
		params.put("label", "Formulario de prueba");
		params.put("input", "Input de prueba");
		
		Node node = nodeFactory.createNode(nodeClassName, params);
		
		assertThat(node, instanceOf(FormStep.class));
		
		FormStep formNode = (FormStep) node;
		
		assertThat(
				formNode.getClass().getName(), 
				equalTo(FormStep.class.getName()));
		
		assertThat(
				formNode.getLabel(), 
				equalToIgnoringCase(params.get("label")));
		
		assertThat(
				formNode.getInput(), 
				equalToIgnoringCase(params.get("input")));
	}*/
}

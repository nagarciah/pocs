package com.nagarciah.trainning.statemachine.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nagarciah.trainning.statemachine.Application;
<<<<<<< HEAD
import com.nagarciah.trainning.statemachine.FlowNotFoundException;
import com.nagarciah.trainning.statemachine.model.FlowDefinition;
import com.nagarciah.trainning.statemachine.model.NodeDefinition;
import com.nagarciah.trainning.statemachine.model.TransitionDefinition;
import com.nagarciah.trainning.statemachine.model.runtime.Conversation;
=======
import com.nagarciah.trainning.statemachine.model.Condition;
import com.nagarciah.trainning.statemachine.model.Flow;
import com.nagarciah.trainning.statemachine.model.Node;
import com.nagarciah.trainning.statemachine.model.Transition;
>>>>>>> refs/remotes/origin/master
import com.nagarciah.trainning.statemachine.service.FlowFactory;
<<<<<<< HEAD


=======
import com.nagarciah.trainning.statemachine.service.FlowNotFoundException;
>>>>>>> refs/remotes/origin/master

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FlowFactoryTests {

	@Autowired
	FlowFactory flowFactory;

	@Test(expected = FlowNotFoundException.class)
	public void flowNotFound() {
		flowFactory.loadFlowDefinition(10000L);
	}

	@Test
<<<<<<< HEAD
	public void loadSimpleFlow() {
=======
	public void createSimpleFlow() {
>>>>>>> refs/remotes/origin/master
		Long flowId = 1L;
<<<<<<< HEAD
		
		FlowDefinition flow = flowFactory.loadFlowDefinition(flowId);
		
=======

		Flow flow = flowFactory.createFlow(flowId);

>>>>>>> refs/remotes/origin/master
		// Verifica flujo
		assertNotNull(flow);
		assertThat(flow.getId(), equalTo(flowId));
		assertThat(flow.getName(), notNullValue());
<<<<<<< HEAD
		
		// Verifica nodos
		assertThat(flow.getNodes(), is(iterableWithSize(equalTo(2))));
		flow.getNodes().forEach(
				node -> assertThat(node, instanceOf(NodeDefinition.class)));
		
		// Verifica transiciones
		assertThat(flow.getTransitions(), is(iterableWithSize(equalTo(1))));
		
		flow.getTransitions().forEach(
				transition -> assertThat(transition, instanceOf(TransitionDefinition.class)));
		
		TransitionDefinition t = flow.getTransitions().iterator().next();
		
		assertThat(t.getFromNode().getId(), equalTo(1L));
		assertThat(t.getToNode().getId(), equalTo(2L));
		
		// Verifica condicion
		assertThat(t.getEvaluator(), notNullValue());
	}
	
	@Test
	public void buildExecutableFlow(){
		FlowDefinition flow = flowFactory.loadFlowDefinition(1L);
		Conversation c =  new Conversation();
		
		flow.getNodes().stream().filter(
				n -> n.getId() == c.getCurrentNodeId());
=======

		// Verifica nodos
		assertThat(flow.getNodes(), is(iterableWithSize(equalTo(2))));
		flow.getNodes().forEach(node -> assertThat(node, instanceOf(Node.class)));

		// Verifica transiciones
		assertThat(flow.getTransitions(), is(iterableWithSize(equalTo(1))));

		flow.getTransitions().forEach(transition -> {
			assertThat(transition, instanceOf(Transition.class));
			assertThat(transition.getFromNode().getId(), equalTo(1L));
			assertThat(transition.getToNode().getId(), equalTo(2L));

			// Verifica condicion
			assertThat(transition.getClassName(), not(isEmptyOrNullString()));
			assertThat(transition.getCondition(), instanceOf(Condition.class));
		});
>>>>>>> refs/remotes/origin/master
	}

}

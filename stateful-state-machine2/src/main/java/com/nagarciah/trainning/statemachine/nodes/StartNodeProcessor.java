package com.nagarciah.trainning.statemachine.nodes;

import com.nagarciah.trainning.statemachine.model.NodeProcessor;


public class StartNodeProcessor implements NodeProcessor<StartNode> {

	@Override
	public void process(StartNode form) {
		System.out.println("Procesando nodo inicial");
	}
}

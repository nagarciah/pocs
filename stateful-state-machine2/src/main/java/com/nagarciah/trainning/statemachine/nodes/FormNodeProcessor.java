package com.nagarciah.trainning.statemachine.nodes;

import com.nagarciah.trainning.statemachine.model.NodeProcessor;


public class FormNodeProcessor implements NodeProcessor<FormNode> {

	@Override
	public void process(FormNode form) {
		System.out.println("Procesando nodo form " + form.getLabel());
	}
}

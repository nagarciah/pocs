package com.nagarciah.trainning.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.nagarciah.trainning.statemachine.FlowNotFoundException;
import com.nagarciah.trainning.statemachine.model.FlowDefinition;
=======
import com.nagarciah.trainning.statemachine.model.Flow;
>>>>>>> refs/remotes/origin/master
import com.nagarciah.trainning.statemachine.repository.FlowRepository;

@Service
public class FlowFactory {

	@Autowired
	FlowRepository flowRepository;

	public FlowDefinition loadFlowDefinition(Long flowId) {
		FlowDefinition flow = flowRepository.findById(flowId)
				.orElseThrow(FlowNotFoundException::new);
		
		return flow;
	}
}

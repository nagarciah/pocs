package com.nagarciah.trainning.statemachine.model;

public interface NodeProcessor<T> {
	public void process(T stepInfo);
}

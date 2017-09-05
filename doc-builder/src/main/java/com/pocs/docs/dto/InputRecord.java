package com.pocs.docs.dto;

import java.io.Serializable;
import java.util.Map;

public class InputRecord implements Serializable {

	private static final long serialVersionUID = -8801035960284485581L;

	public static enum InputSource {
		QUEUE, CSV, WS
	}
	
	public static enum OutputTarget {
		QUEUE, CSV, WS
	}
	
	protected InputSource inputSource;
	protected OutputTarget outputTarget;
	protected String templateId;
	protected String outputFileName;
	protected Map<String, Object> fields;
	
	public InputRecord(){
		
	}
	
	public InputRecord(InputSource inputSource, OutputTarget outputTarget, String templateId, String outputFileName,
			Map<String, Object> fields) {
		super();
		this.inputSource = inputSource;
		this.outputTarget = outputTarget;
		this.templateId = templateId;
		this.outputFileName = outputFileName;
		this.fields = fields;
	}
	
	public InputSource getInputSource() {
		return inputSource;
	}
	public void setInputSource(InputSource inputSource) {
		this.inputSource = inputSource;
	}
	public OutputTarget getOutputTarget() {
		return outputTarget;
	}
	public void setOutputTarget(OutputTarget outputTarget) {
		this.outputTarget = outputTarget;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	public Map<String, Object> getFields() {
		return fields;
	}
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputRecord [inputSource=");
		builder.append(inputSource);
		builder.append(", outputTarget=");
		builder.append(outputTarget);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append(", outputFileName=");
		builder.append(outputFileName);
		builder.append(", fields=");
		builder.append(fields);
		builder.append("]");
		return builder.toString();
	}
}

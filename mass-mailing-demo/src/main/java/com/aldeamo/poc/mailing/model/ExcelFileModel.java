package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelFileModel {
	private File file;
	private List<String> headers;
	private List<Map<String, Object>> content;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<Map<String, Object>> getContent() {
		return content;
	}
	public void setContent(List<Map<String, Object>> contents) {
		this.content = contents;
	}
	public Object getCell(int rowIndex, String columnName) {
		return this.getContent().get(rowIndex).get(columnName);
	}
}

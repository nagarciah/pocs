package com.pocs.docs.dto;

import java.io.File;

public class OutputRecord {
	File file;
	String mediaType;
	
	public OutputRecord(File file, String mediaType) {
		super();
		this.file = file;
		this.mediaType = mediaType;
	}
	
	public File getFile() {
		return file;
	}
	public String getMediaType() {
		return mediaType;
	}
}

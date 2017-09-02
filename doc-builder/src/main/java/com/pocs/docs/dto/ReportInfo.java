package com.pocs.docs.dto;

import java.io.File;

public class ReportInfo {
	File file;
	String mediaType;
	
	public ReportInfo(File file, String mediaType) {
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

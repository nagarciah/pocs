package com.pocs.docs.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CsvRecordFieldSetMapper implements FieldSetMapper<Map<String, Object>> {

	@Override
	public Map<String, Object> mapFieldSet(FieldSet fieldset) throws BindException {
		// TODO Ver cual es el mapa m[as eficiente para usar y si se necesita thread safety
		Map<String, Object> fileRecord = new HashMap<String, Object>();
		fileRecord.put("outFileName", fieldset.readString("Nombre"));
		for(String field : fieldset.getNames()){
			fileRecord.put(field, fieldset.readString(field));
		}
		
		return fileRecord;
	}
}

package com.pocs.docs.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.pocs.docs.dto.InputRecord;
import com.pocs.docs.dto.InputRecord.InputSource;
import com.pocs.docs.dto.InputRecord.OutputTarget;

public class CsvRecordFieldSetMapper implements FieldSetMapper<InputRecord> {

	@Override
	public InputRecord mapFieldSet(FieldSet fieldset) throws BindException {
		// TODO Ver cual es el mapa m[as eficiente para usar y si se necesita thread safety
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("outFileName", fieldset.readString("Nombre"));
		for(String field : fieldset.getNames()){
			fields.put(field, fieldset.readString(field));
		}
		
		return new InputRecord(
				InputSource.CSV, 
				OutputTarget.QUEUE, 
				"simple.jrxml", 
				fieldset.readString("Nombre"), 
				fields);
	}
}

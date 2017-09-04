package com.pocs.docs.conf;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.pocs.docs.batch.CsvRecordFieldSetMapper;
import com.pocs.docs.batch.ItemProcessorListener;
import com.pocs.docs.batch.JobListener;
import com.pocs.docs.batch.PdfProcessor;
import com.pocs.docs.dto.InputRecord;
import com.pocs.docs.dto.OutputRecord;


@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
	
	public static final String MAIN_JOB_NAME = "fileGeneratorJob";
	public static final String CTX_CSV_FILE = "CTX_CSV_FILE";
	public static final String CSV_PROCESS_STEP_NAME = "createPdf";
	
	private final Logger log = LoggerFactory.getLogger(BatchConfig.class);
	
	@Autowired
	protected JobBuilderFactory jobs;

	@Autowired
	protected StepBuilderFactory stepBuilderFactory;

	@Value("${batch.input.file}")
	protected File inputFile;

	@Value("${batch.input.linesToSkip}")
	protected int inputlinesToSkip;
	
	@Value("${batch.input.delimiter}")
	protected String csvInputDelimiter;

	
	@Value("${batch.input.columns}")
	protected String[] csvColumns;
	
		
	@Bean(MAIN_JOB_NAME)
	@Autowired
	public Job mainJob(PdfProcessor pdfProcessor) throws IOException{
		Step step = stepBuilderFactory.get(CSV_PROCESS_STEP_NAME)
			.<InputRecord, OutputRecord>chunk(1)
			.reader(reader())
			.processor(pdfProcessor)
			.writer(writer())
			.faultTolerant()
			.listener(new ItemProcessorListener())
			//.skip(Exception.class)
			//.skip(RuntimeException.class)
			//.skipLimit(1000000)
			//.skipPolicy(new AlwaysSkipItemSkipPolicy()) // Intentará procesar tantos items como sea posible
			.build();
		
		return jobs.get(MAIN_JOB_NAME)
				.listener(new JobListener())
				.start(step)
				.build();
	}


	// TODO Podr[ia cambiarse por un lector de Mongo o DB SQL, cola, usando factory
	public ItemReader<InputRecord> reader(){
		FlatFileItemReader<InputRecord> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(inputlinesToSkip); // Omite las N primeras líneas que se asume, pueden ser títulos
		//reader.setResource(new FileSystemResource(inputFile));
		reader.setResource(new ClassPathResource("data.csv"));
		reader.setLineMapper(lineMapper());
		return reader;
	}

	public LineMapper<InputRecord> lineMapper() {
		DefaultLineMapper<InputRecord> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(csvInputDelimiter);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(csvColumns);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new CsvRecordFieldSetMapper());

		return lineMapper;
	}

    // TODO Y este deber[ia enviar un mensaje a una cola con los datos del PDF
    public ItemWriter<OutputRecord> writer() throws IOException {
    	return new ItemWriterAdapter<OutputRecord>(){
			@Override
			public void write(List<? extends OutputRecord> items) throws Exception {
				items.forEach(i -> log.info("Escribiendo item {}", i));
			}
    	};
    }

	/*@Override
	public void setDataSource(@Qualifier("batchDatasource") DataSource dataSource) {
		super.setDataSource(dataSource);
	}*/
}

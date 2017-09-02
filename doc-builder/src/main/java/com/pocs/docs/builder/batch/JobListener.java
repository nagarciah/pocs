package com.pocs.docs.builder.batch;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;

import com.pocs.docs.builder.conf.BatchConfig;

public class JobListener implements JobExecutionListener {
	private final Log log = LogFactory.getLog(JobListener.class);
	
	/*@Autowired
	ErrorNotifierService errorService;*/
	
	public void beforeJob(JobExecution jobExecution) {
		StringBuilder jobLog = new StringBuilder();
		jobLog.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		jobLog.append("  Job iniciado");
		addParametersToLog(jobLog, jobExecution);
	}


	public void afterJob(JobExecution jobExecution) {
		long skipCount = 0;
		
		if(log.isInfoEnabled()){
			StringBuilder jobLog = new StringBuilder();
			jobLog.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
			jobLog.append("Protocol for " + jobExecution.getJobInstance().getJobName() + " \n");
			jobLog.append("  Iniciado    : " + jobExecution.getStartTime() + "\n");
			jobLog.append("  Finalizado  : " + jobExecution.getEndTime() + "\n");
			jobLog.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
			jobLog.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
			jobLog.append("  Status      : " + jobExecution.getStatus() + "\n");
			jobLog.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
	
			addParametersToLog(jobLog, jobExecution);
	
			for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
				jobLog.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
				jobLog.append("Step " + stepExecution.getStepName() + " \n");
				jobLog.append("WriteCount: " + stepExecution.getWriteCount() + "\n");
				jobLog.append("Commits: " + stepExecution.getCommitCount() + "\n");
				jobLog.append("SkipCount: " + stepExecution.getSkipCount() + "\n");
				jobLog.append("Rollbacks: " + stepExecution.getRollbackCount() + "\n");
				jobLog.append("Filter: " + stepExecution.getFilterCount() + "\n");
				jobLog.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");				
			}
			log.info(jobLog.toString());
		}
		
		for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
			if(BatchConfig.CSV_PROCESS_STEP_NAME.equals(stepExecution.getStepName()) && stepExecution.getSkipCount()>0){
				skipCount = stepExecution.getSkipCount();
				log.warn("ADVERTENCIA: " + skipCount + " registros no pudieron ser procesados. Revise la base de datos y el log para ver los detalles, corríjalos y reintente el procesamiento únicamente de esos registros fallidos.");
			}
		}
		

		if(jobExecution.getStatus()==BatchStatus.FAILED){
			log.error("***************** El procesamiento fallo, errores a continuacion ********************************");
			log.error(jobExecution.getExitStatus().getExitDescription());
			log.error("*************************************************************************************************");
			//errorService.notify("El procesamiento falló. Para mayores detalles, consulte el log y la base de datos", JobListener.class, null);
		}else if(skipCount > 0){
			//errorService.notify("Algunos registros no fueron procesados. Para mayores detalles, consulte el log y la base de datos", JobListener.class, null);
		}
	}

	private void addParametersToLog(StringBuilder jobLog, JobExecution jobExecution){
		jobLog.append("Job-Parameter: \n");
		JobParameters jp = jobExecution.getJobParameters();
		for (Iterator<Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext();) {
			Entry<String, JobParameter> entry = iter.next();
			jobLog.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
		}
		jobLog.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
	}
}
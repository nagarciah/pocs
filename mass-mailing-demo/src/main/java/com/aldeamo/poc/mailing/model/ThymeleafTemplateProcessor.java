package com.aldeamo.poc.mailing.model;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@Component
public class ThymeleafTemplateProcessor implements EmailTemplateProcessor {
	
	private final Log log = LogFactory.getLog(ThymeleafTemplateProcessor.class);

	@Value("${templates.processor.thymeleaf.baseFolder}")
	String templatesFolder;
	
	@Value("${templates.processor.thymeleaf.templateMode}")
	String templateMode;
	
	@Value("${templates.processor.thymeleaf.templateSuffix}")
	String templateSuffix;
	
	TemplateEngine engine;
	
	@PostConstruct
	public void initialize(){
		if(!templatesFolder.endsWith(File.separator)){
			templatesFolder += File.separator;
		}
		
		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setTemplateMode(templateMode);
		resolver.setPrefix(templatesFolder);
		resolver.setSuffix(templateSuffix);
		engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		engine.initialize();
	}
	
	@Override
	public String setHtmlContent(EmailMessage message, EmailTemplate emailTemplate, Map<String, Object> variables){
		// TODO Quiza dejar el locale configurable desde properties
		IContext context = new Context(Locale.getDefault(), variables);
		
		String html;
		StringBuilder templateName = new StringBuilder(emailTemplate.getHtmlFilename());
		
		if(emailTemplate.getTemplateSubdir() != null && emailTemplate.getTemplateSubdir().length()>0){
			templateName.setLength(0); // Borra el contenido del StringBuilder
			templateName.append(emailTemplate.getTemplateSubdir())
				.append(File.separator)
				.append(emailTemplate.getHtmlFilename()); 
		}
			
		try{
			html = engine.process(templateName.toString(), context);
		}catch(TemplateInputException e){
			throw new EmailProcessingException(
					MessageFormat.format(
						"No se ha encontrado la plantilla: customerId={0}, customerTemplateId={1}, custom, error={2}",
						emailTemplate.getCustomerId(),
						emailTemplate.getCustomerTemplateId(),
						e.getMessage()), 
					e);
		}catch(TemplateProcessingException e){
			throw new EmailProcessingException("Error procesando plantilla de correo. Revise si está suministrando todas las variables que se usan en la plantilla, que la sintaxis es correcta y que la plantilla existe: " + e.getMessage(), e);
		}
	
		Document htmlContent = Jsoup.parse(html);
		Elements images = htmlContent.select("img");
		
		// Calcula el directorio de imágenes: 1) configurado en db, 2) dir de plantilla, 3) dir general de plantillas
		final String templateSubdir = emailTemplate.getTemplateSubdir() != null ? emailTemplate.getTemplateSubdir() + File.separator : "";
		final String imagesSubdir = emailTemplate.getImagesSubpath() != null ? emailTemplate.getImagesSubpath() + File.separator : "";
		
		// Agrega los archivos in-line
		images.stream().forEach(img -> {
			if(img.hasAttr("src") && !img.attr("src").startsWith("cid:")){
				String src = img.attr("src");
				String filename = new StringBuilder()
						.append(templatesFolder)
						.append(File.separator)
						.append(templateSubdir)
						.append(imagesSubdir)
						.append(src)
						.toString();
				
				File inline = new File(filename);
				if(!inline.exists() && log.isWarnEnabled()){
					log.warn("No se encontró el archivo incrustado en el email. Se omitirá: " + inline.getAbsolutePath());
				}
				message.addInlineFile(src, inline);
				
				img.attr("src", "cid:"+src);
			}
		});
		
		message.setHtmlContent(htmlContent.html());
		
		return htmlContent.html();
	}
}

package com.aldeamo.poc.mailing.model;

import java.util.Map;

public interface EmailTemplateProcessor {

	String setHtmlContent(EmailMessage message, EmailTemplate emailTemplate, Map<String, Object> variables);

}

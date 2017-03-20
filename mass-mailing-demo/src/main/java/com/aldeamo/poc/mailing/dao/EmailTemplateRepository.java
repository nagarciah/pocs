package com.aldeamo.poc.mailing.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.aldeamo.poc.mailing.model.EmailTemplate;

@Component
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

	public List<EmailTemplate> findAllByCustomerId(String customerId);
	public List<EmailTemplate> findAllByCustomerIdAndCustomerTemplateId(String customerId, String customerTemplateId); 
	
}

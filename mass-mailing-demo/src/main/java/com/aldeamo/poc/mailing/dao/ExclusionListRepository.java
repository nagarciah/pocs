package com.aldeamo.poc.mailing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeamo.poc.mailing.model.ExclusionListEntry;

@Repository
public interface ExclusionListRepository extends JpaRepository<ExclusionListEntry, Long> {
	public ExclusionListEntry findOneByCustomerIdAndCustomerListIdAndRecipientEmail(String customerId, String customerListId, String recipientEmail);
	public ExclusionListEntry findOneByCustomerIdAndRecipientEmail(String customerId, String recipientEmail);
}

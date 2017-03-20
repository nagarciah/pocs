package com.aldeamo.poc.mailing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeamo.poc.mailing.model.EmailSendingRequest;

@Repository
public interface SendingRepository extends JpaRepository<EmailSendingRequest, Long> {

}

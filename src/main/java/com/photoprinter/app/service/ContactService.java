package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.ContactDto;
import com.photoprinter.app.exceptionHandling.ServiceException;


public interface ContactService {
	Observable<ResponseEntity<ContactDto>> createContact(final ContactDto contactDto) throws ServiceException;
	Observable<ResponseEntity<ContactDto>> updateContact(final String contactId, final ContactDto contactDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteContact(final String contactId) throws ServiceException;
	Observable<ResponseEntity<ContactDto>> getContact(final String contactId) throws ServiceException;
	Observable<ResponseEntity<List<ContactDto>>> getAllContacts(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<ContactDto>>> getAllContacts(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
	
}

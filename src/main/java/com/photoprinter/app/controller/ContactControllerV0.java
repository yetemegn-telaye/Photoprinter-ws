package com.photoprinter.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.photoprinter.app.dto.ContactDto;
import com.photoprinter.app.service.ContactService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/contacts"
        @class: ContactControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Contact, plus all operations related to Contact
*/
@RestController
@RequestMapping(path = "v0/contacts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ContactControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactService contactService;

	/**
	    @endpoint: "POST /"
        @method: createContact
        @parameters: contactDto
        @return: DeferredResult<ResponseEntity<ContactDto>>  - Contact
        @description: Creates new Contact entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<ContactDto>> createContact(@RequestBody final ContactDto contactDto) throws Throwable {
		DeferredResult<ResponseEntity<ContactDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ContactDto>> response = contactService.createContact(contactDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{contactId}"
        @method: updateContact
        @parameters: contactDto
        @return: DeferredResult<ResponseEntity<ContactDto>> - Contact
        @description: Updates Contact
    */
	@RequestMapping(path = "/{contactId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<ContactDto>> updateContact(@PathVariable("contactId") final String contactId, @RequestBody final ContactDto contactDto)  throws Throwable {

		DeferredResult<ResponseEntity<ContactDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ContactDto>> response = contactService.updateContact(contactId, contactDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{contactId}"
        @method: deleteContact
        @parameters: contactId
        @return: DeferredResult<ResponseEntity<Void>> deleteContact - Void
        @description: Removes Contact
    */
	@RequestMapping(path= "/{contactId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteContact(@PathVariable("contactId") final String contactId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = contactService.deleteContact(contactId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{contactId}"
        @method: getContact
        @parameters: contactId
        @return: DeferredResult<ResponseEntity<ContactDto>> getContact - Contact
        @description: gets a Contact
    */
	@RequestMapping(path= "/{contactId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<ContactDto>> getContact(@PathVariable("contactId") final String contactId) throws Throwable {

		DeferredResult<ResponseEntity<ContactDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ContactDto>> response = contactService.getContact(contactId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getContacts
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<ContactDto>>> - List<Contact>
        @description: gets list of all Contacts
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<ContactDto>>> getAllContacts(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<ContactDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<ContactDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = contactService.getAllContacts(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = contactService.getAllContacts(0, limit, sortBy);
        		else
        			response = contactService.getAllContacts(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	
	
}

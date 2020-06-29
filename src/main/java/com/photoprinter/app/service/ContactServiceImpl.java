package com.photoprinter.app.service;

import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import com.photoprinter.app.dto.ContactDto;
import com.photoprinter.app.model.Contact;
import com.photoprinter.app.repository.ContactRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Client;
import com.photoprinter.app.model.Shop;



@Service
public class ContactServiceImpl implements ContactService {

	@Autowired 
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<ContactDto>> createContact(final ContactDto contactDto) throws ServiceException {
		if(contactDto == null)
			throw new ServiceException(new MalformedRequestException("Contact")); 
		Contact savedContact = contactRepository.save(modelMapper.map(contactDto, Contact.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedContact, ContactDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<ContactDto>> updateContact(String contactId, ContactDto contactDto) throws ServiceException {
		if(contactDto == null)
			throw new ServiceException(new MalformedRequestException("Contact"));
		Contact currentContact = contactRepository.findOne(contactId);
		if(currentContact == null)
			throw new ServiceException(new ResourceNotFoundException(Contact.class));
				
		currentContact.setPhoneNumber(contactDto.getPhoneNumber());
		
		currentContact.setClient(modelMapper.map(contactDto.getClient(), Client.class));
		
		currentContact.setShop(modelMapper.map(contactDto.getShop(), Shop.class));
		
		currentContact.setEmailAddress(contactDto.getEmailAddress());

		Contact savedContact = contactRepository.save(currentContact);
		if(savedContact == null)
			throw new DataAccessResourceFailureException("Contact Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedContact, ContactDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteContact(final String contactId) throws ServiceException {
		if(contactId == null || contactRepository.getOne(contactId) == null)
			throw new ServiceException(new ResourceNotFoundException("Contact"));
		contactRepository.delete(contactRepository.getOne(contactId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<ContactDto>> getContact(final String contactId) throws ServiceException {
		Contact contact = contactRepository.getOne(contactId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(contact, ContactDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<ContactDto>>> getAllContacts(final List<String> sortAttributes) throws ServiceException {
		List<Contact> contacts = null;
		Page<Contact> contactsPage = null;
		if(sortAttributes == null) {
			contacts = contactRepository.findAll();
		} else {
			contactsPage = contactRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			contacts = contactsPage.getContent();	
		}
		return getResults(contacts);
	}
	
	@Override
	public Observable<ResponseEntity<List<ContactDto>>> getAllContacts(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Contact> contacts = null;
		Page<Contact> contactsPage = null;
		contactsPage = contactRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		contacts = contactsPage.getContent();	
		return getResults(contacts);
	}
	
	
	private Observable<ResponseEntity<List<ContactDto>>> getResults(final List<Contact> contacts) throws ServiceException{
		List<ContactDto> contactsFound = new ArrayList<>();
		if (contacts == null || contacts.isEmpty())
			return Observable.just(new ResponseEntity<>(contactsFound, HttpStatus.NOT_FOUND));
		for(Contact contact : contacts){
			contactsFound.add(modelMapper.map(contact, ContactDto.class));
		}	
		return Observable.just(new ResponseEntity<>(contactsFound, HttpStatus.OK));
	}
	
	
}

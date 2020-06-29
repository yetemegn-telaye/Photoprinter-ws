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
import com.photoprinter.app.dto.ClientDto;
import com.photoprinter.app.model.Client;
import com.photoprinter.app.repository.ClientRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Contact;
import com.photoprinter.app.model.User;
import com.photoprinter.app.model.Order;
import com.photoprinter.app.model.Address;
import com.photoprinter.app.model.Imageinfo;

import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.dto.ImageinfoDto;


@Service
public class ClientServiceImpl implements ClientService {

	@Autowired 
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<ClientDto>> createClient(final ClientDto clientDto) throws ServiceException {
		if(clientDto == null)
			throw new ServiceException(new MalformedRequestException("Client")); 
		Client savedClient = clientRepository.save(modelMapper.map(clientDto, Client.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedClient, ClientDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<ClientDto>> updateClient(String clientId, ClientDto clientDto) throws ServiceException {
		if(clientDto == null)
			throw new ServiceException(new MalformedRequestException("Client"));
		Client currentClient = clientRepository.findOne(clientId);
		if(currentClient == null)
			throw new ServiceException(new ResourceNotFoundException(Client.class));
				
		currentClient.setContact(modelMapper.map(clientDto.getContact(), Contact.class));
		
		currentClient.setLastName(clientDto.getLastName());
		
		currentClient.setUser(modelMapper.map(clientDto.getUser(), User.class));
		java.lang.reflect.Type OrdersSet  = new TypeToken<Set<Order>>() {}.getType();	
		currentClient.setOrders(modelMapper.map(clientDto.getOrders(), OrdersSet));
		
		currentClient.setFirstName(clientDto.getFirstName());
		java.lang.reflect.Type AddresssSet  = new TypeToken<Set<Address>>() {}.getType();	
		currentClient.setAddresss(modelMapper.map(clientDto.getAddresss(), AddresssSet));
		
		currentClient.setMiddleName(clientDto.getMiddleName());
		java.lang.reflect.Type ImageInfosSet  = new TypeToken<Set<Imageinfo>>() {}.getType();	
		currentClient.setImageInfos(modelMapper.map(clientDto.getImageInfos(), ImageInfosSet));

		Client savedClient = clientRepository.save(currentClient);
		if(savedClient == null)
			throw new DataAccessResourceFailureException("Client Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedClient, ClientDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteClient(final String clientId) throws ServiceException {
		if(clientId == null || clientRepository.getOne(clientId) == null)
			throw new ServiceException(new ResourceNotFoundException("Client"));
		clientRepository.delete(clientRepository.getOne(clientId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<ClientDto>> getClient(final String clientId) throws ServiceException {
		Client client = clientRepository.getOne(clientId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(client, ClientDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<ClientDto>>> getAllClients(final List<String> sortAttributes) throws ServiceException {
		List<Client> clients = null;
		Page<Client> clientsPage = null;
		if(sortAttributes == null) {
			clients = clientRepository.findAll();
		} else {
			clientsPage = clientRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			clients = clientsPage.getContent();	
		}
		return getResults(clients);
	}
	
	@Override
	public Observable<ResponseEntity<List<ClientDto>>> getAllClients(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Client> clients = null;
		Page<Client> clientsPage = null;
		clientsPage = clientRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		clients = clientsPage.getContent();	
		return getResults(clients);
	}
	
	
	private Observable<ResponseEntity<List<ClientDto>>> getResults(final List<Client> clients) throws ServiceException{
		List<ClientDto> clientsFound = new ArrayList<>();
		if (clients == null || clients.isEmpty())
			return Observable.just(new ResponseEntity<>(clientsFound, HttpStatus.NOT_FOUND));
		for(Client client : clients){
			clientsFound.add(modelMapper.map(client, ClientDto.class));
		}	
		return Observable.just(new ResponseEntity<>(clientsFound, HttpStatus.OK));
	}
	
		
	@Override
	public Observable<ResponseEntity<Set<OrderDto>>> getClientOrders(final String clientId) throws ServiceException {
		Set<OrderDto> orderDtos = new HashSet();
		Client client = clientRepository.getOne(clientId);
		if(clientId == null || client == null)
			return Observable.just(new ResponseEntity<>(orderDtos, HttpStatus.NOT_FOUND));
		for(Order order: client.getOrders()) {
			orderDtos.add(modelMapper.map(order, OrderDto.class));
		}
		return Observable.just(new ResponseEntity<>(orderDtos, HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<Set<AddressDto>>> getClientAddresss(final String clientId) throws ServiceException {
		Set<AddressDto> addressDtos = new HashSet();
		Client client = clientRepository.getOne(clientId);
		if(clientId == null || client == null)
			return Observable.just(new ResponseEntity<>(addressDtos, HttpStatus.NOT_FOUND));
		for(Address address: client.getAddresss()) {
			addressDtos.add(modelMapper.map(address, AddressDto.class));
		}
		return Observable.just(new ResponseEntity<>(addressDtos, HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<Set<ImageinfoDto>>> getClientImageinfos(final String clientId) throws ServiceException {
		Set<ImageinfoDto> imageInfoDtos = new HashSet();
		Client client = clientRepository.getOne(clientId);
		if(clientId == null || client == null)
			return Observable.just(new ResponseEntity<>(imageInfoDtos, HttpStatus.NOT_FOUND));
		for(Imageinfo imageInfo: client.getImageInfos()) {
			imageInfoDtos.add(modelMapper.map(imageInfo, ImageinfoDto.class));
		}
		return Observable.just(new ResponseEntity<>(imageInfoDtos, HttpStatus.OK));
	}

}

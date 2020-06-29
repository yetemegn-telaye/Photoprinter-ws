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
import com.photoprinter.app.dto.ClientDto;
import com.photoprinter.app.service.ClientService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;
import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.dto.ImageinfoDto;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/clients"
        @class: ClientControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Client, plus all operations related to Client
*/
@RestController
@RequestMapping(path = "v0/clients", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClientControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClientService clientService;

	/**
	    @endpoint: "POST /"
        @method: createClient
        @parameters: clientDto
        @return: DeferredResult<ResponseEntity<ClientDto>>  - Client
        @description: Creates new Client entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<ClientDto>> createClient(@RequestBody final ClientDto clientDto) throws Throwable {
		DeferredResult<ResponseEntity<ClientDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ClientDto>> response = clientService.createClient(clientDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{clientId}"
        @method: updateClient
        @parameters: clientDto
        @return: DeferredResult<ResponseEntity<ClientDto>> - Client
        @description: Updates Client
    */
	@RequestMapping(path = "/{clientId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<ClientDto>> updateClient(@PathVariable("clientId") final String clientId, @RequestBody final ClientDto clientDto)  throws Throwable {

		DeferredResult<ResponseEntity<ClientDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ClientDto>> response = clientService.updateClient(clientId, clientDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{clientId}"
        @method: deleteClient
        @parameters: clientId
        @return: DeferredResult<ResponseEntity<Void>> deleteClient - Void
        @description: Removes Client
    */
	@RequestMapping(path= "/{clientId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteClient(@PathVariable("clientId") final String clientId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = clientService.deleteClient(clientId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{clientId}"
        @method: getClient
        @parameters: clientId
        @return: DeferredResult<ResponseEntity<ClientDto>> getClient - Client
        @description: gets a Client
    */
	@RequestMapping(path= "/{clientId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<ClientDto>> getClient(@PathVariable("clientId") final String clientId) throws Throwable {

		DeferredResult<ResponseEntity<ClientDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ClientDto>> response = clientService.getClient(clientId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getClients
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<ClientDto>>> - List<Client>
        @description: gets list of all Clients
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<ClientDto>>> getAllClients(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<ClientDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<ClientDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = clientService.getAllClients(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = clientService.getAllClients(0, limit, sortBy);
        		else
        			response = clientService.getAllClients(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	   /**
        @endpoint: "GET /{clientId}/orders"
        @method: getClientOrders
        @parameters: clientId
        @return: ResponseEntity<Set<OrderDto>>  - Set<Order>
        @description: gets list of all Orders that belongs to a Client
    */
	@RequestMapping(path= "/{clientId}/orders", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<OrderDto>>> getClientOrders(@PathVariable("clientId") final String clientId) throws Throwable {
		DeferredResult<ResponseEntity<Set<OrderDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<OrderDto>>> response =  clientService.getClientOrders(clientId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}
   /**
        @endpoint: "GET /{clientId}/addresss"
        @method: getClientAddresss
        @parameters: clientId
        @return: ResponseEntity<Set<AddressDto>>  - Set<Address>
        @description: gets list of all Addresss that belongs to a Client
    */
	@RequestMapping(path= "/{clientId}/addresss", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<AddressDto>>> getClientAddresss(@PathVariable("clientId") final String clientId) throws Throwable {
		DeferredResult<ResponseEntity<Set<AddressDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<AddressDto>>> response =  clientService.getClientAddresss(clientId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}
   /**
        @endpoint: "GET /{clientId}/imageInfos"
        @method: getClientImageinfos
        @parameters: clientId
        @return: ResponseEntity<Set<ImageinfoDto>>  - Set<Imageinfo>
        @description: gets list of all Imageinfos that belongs to a Client
    */
	@RequestMapping(path= "/{clientId}/imageInfos", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<ImageinfoDto>>> getClientImageinfos(@PathVariable("clientId") final String clientId) throws Throwable {
		DeferredResult<ResponseEntity<Set<ImageinfoDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<ImageinfoDto>>> response =  clientService.getClientImageinfos(clientId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	
}

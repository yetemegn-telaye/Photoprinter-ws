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
import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.service.AddressService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/addresss"
        @class: AddressControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Address, plus all operations related to Address
*/
@RestController
@RequestMapping(path = "v0/addresss", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AddressControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AddressService addressService;

	/**
	    @endpoint: "POST /"
        @method: createAddress
        @parameters: addressDto
        @return: DeferredResult<ResponseEntity<AddressDto>>  - Address
        @description: Creates new Address entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<AddressDto>> createAddress(@RequestBody final AddressDto addressDto) throws Throwable {
		DeferredResult<ResponseEntity<AddressDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<AddressDto>> response = addressService.createAddress(addressDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{addressId}"
        @method: updateAddress
        @parameters: addressDto
        @return: DeferredResult<ResponseEntity<AddressDto>> - Address
        @description: Updates Address
    */
	@RequestMapping(path = "/{addressId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<AddressDto>> updateAddress(@PathVariable("addressId") final String addressId, @RequestBody final AddressDto addressDto)  throws Throwable {

		DeferredResult<ResponseEntity<AddressDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<AddressDto>> response = addressService.updateAddress(addressId, addressDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{addressId}"
        @method: deleteAddress
        @parameters: addressId
        @return: DeferredResult<ResponseEntity<Void>> deleteAddress - Void
        @description: Removes Address
    */
	@RequestMapping(path= "/{addressId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteAddress(@PathVariable("addressId") final String addressId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = addressService.deleteAddress(addressId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{addressId}"
        @method: getAddress
        @parameters: addressId
        @return: DeferredResult<ResponseEntity<AddressDto>> getAddress - Address
        @description: gets a Address
    */
	@RequestMapping(path= "/{addressId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<AddressDto>> getAddress(@PathVariable("addressId") final String addressId) throws Throwable {

		DeferredResult<ResponseEntity<AddressDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<AddressDto>> response = addressService.getAddress(addressId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getAddresss
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<AddressDto>>> - List<Address>
        @description: gets list of all Addresss
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<AddressDto>>> getAllAddresss(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<AddressDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<AddressDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = addressService.getAllAddresss(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = addressService.getAllAddresss(0, limit, sortBy);
        		else
        			response = addressService.getAllAddresss(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	
	
}

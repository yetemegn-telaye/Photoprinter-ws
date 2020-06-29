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
import com.photoprinter.app.dto.ShopimageinfoDto;
import com.photoprinter.app.service.ShopimageinfoService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/shopimageinfos"
        @class: ShopimageinfoControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Shopimageinfo, plus all operations related to Shopimageinfo
*/
@RestController
@RequestMapping(path = "v0/shopimageinfos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ShopimageinfoControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShopimageinfoService shopimageinfoService;

	/**
	    @endpoint: "POST /"
        @method: createShopimageinfo
        @parameters: shopimageinfoDto
        @return: DeferredResult<ResponseEntity<ShopimageinfoDto>>  - Shopimageinfo
        @description: Creates new Shopimageinfo entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<ShopimageinfoDto>> createShopimageinfo(@RequestBody final ShopimageinfoDto shopimageinfoDto) throws Throwable {
		DeferredResult<ResponseEntity<ShopimageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopimageinfoDto>> response = shopimageinfoService.createShopimageinfo(shopimageinfoDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{shopimageinfoId}"
        @method: updateShopimageinfo
        @parameters: shopimageinfoDto
        @return: DeferredResult<ResponseEntity<ShopimageinfoDto>> - Shopimageinfo
        @description: Updates Shopimageinfo
    */
	@RequestMapping(path = "/{shopimageinfoId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<ShopimageinfoDto>> updateShopimageinfo(@PathVariable("shopimageinfoId") final String shopimageinfoId, @RequestBody final ShopimageinfoDto shopimageinfoDto)  throws Throwable {

		DeferredResult<ResponseEntity<ShopimageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopimageinfoDto>> response = shopimageinfoService.updateShopimageinfo(shopimageinfoId, shopimageinfoDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{shopimageinfoId}"
        @method: deleteShopimageinfo
        @parameters: shopimageinfoId
        @return: DeferredResult<ResponseEntity<Void>> deleteShopimageinfo - Void
        @description: Removes Shopimageinfo
    */
	@RequestMapping(path= "/{shopimageinfoId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteShopimageinfo(@PathVariable("shopimageinfoId") final String shopimageinfoId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = shopimageinfoService.deleteShopimageinfo(shopimageinfoId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{shopimageinfoId}"
        @method: getShopimageinfo
        @parameters: shopimageinfoId
        @return: DeferredResult<ResponseEntity<ShopimageinfoDto>> getShopimageinfo - Shopimageinfo
        @description: gets a Shopimageinfo
    */
	@RequestMapping(path= "/{shopimageinfoId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<ShopimageinfoDto>> getShopimageinfo(@PathVariable("shopimageinfoId") final String shopimageinfoId) throws Throwable {

		DeferredResult<ResponseEntity<ShopimageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopimageinfoDto>> response = shopimageinfoService.getShopimageinfo(shopimageinfoId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getShopimageinfos
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<ShopimageinfoDto>>> - List<Shopimageinfo>
        @description: gets list of all Shopimageinfos
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<ShopimageinfoDto>>> getAllShopimageinfos(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<ShopimageinfoDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<ShopimageinfoDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = shopimageinfoService.getAllShopimageinfos(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = shopimageinfoService.getAllShopimageinfos(0, limit, sortBy);
        		else
        			response = shopimageinfoService.getAllShopimageinfos(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	
	
}

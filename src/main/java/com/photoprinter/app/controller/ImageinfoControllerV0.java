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
import com.photoprinter.app.dto.ImageinfoDto;
import com.photoprinter.app.service.ImageinfoService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/imageinfos"
        @class: ImageinfoControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Imageinfo, plus all operations related to Imageinfo
*/
@RestController
@RequestMapping(path = "v0/imageinfos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ImageinfoControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ImageinfoService imageinfoService;

	/**
	    @endpoint: "POST /"
        @method: createImageinfo
        @parameters: imageinfoDto
        @return: DeferredResult<ResponseEntity<ImageinfoDto>>  - Imageinfo
        @description: Creates new Imageinfo entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<ImageinfoDto>> createImageinfo(@RequestBody final ImageinfoDto imageinfoDto) throws Throwable {
		DeferredResult<ResponseEntity<ImageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ImageinfoDto>> response = imageinfoService.createImageinfo(imageinfoDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{imageinfoId}"
        @method: updateImageinfo
        @parameters: imageinfoDto
        @return: DeferredResult<ResponseEntity<ImageinfoDto>> - Imageinfo
        @description: Updates Imageinfo
    */
	@RequestMapping(path = "/{imageinfoId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<ImageinfoDto>> updateImageinfo(@PathVariable("imageinfoId") final String imageinfoId, @RequestBody final ImageinfoDto imageinfoDto)  throws Throwable {

		DeferredResult<ResponseEntity<ImageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ImageinfoDto>> response = imageinfoService.updateImageinfo(imageinfoId, imageinfoDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{imageinfoId}"
        @method: deleteImageinfo
        @parameters: imageinfoId
        @return: DeferredResult<ResponseEntity<Void>> deleteImageinfo - Void
        @description: Removes Imageinfo
    */
	@RequestMapping(path= "/{imageinfoId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteImageinfo(@PathVariable("imageinfoId") final String imageinfoId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = imageinfoService.deleteImageinfo(imageinfoId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{imageinfoId}"
        @method: getImageinfo
        @parameters: imageinfoId
        @return: DeferredResult<ResponseEntity<ImageinfoDto>> getImageinfo - Imageinfo
        @description: gets a Imageinfo
    */
	@RequestMapping(path= "/{imageinfoId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<ImageinfoDto>> getImageinfo(@PathVariable("imageinfoId") final String imageinfoId) throws Throwable {

		DeferredResult<ResponseEntity<ImageinfoDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ImageinfoDto>> response = imageinfoService.getImageinfo(imageinfoId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getImageinfos
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<ImageinfoDto>>> - List<Imageinfo>
        @description: gets list of all Imageinfos
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<ImageinfoDto>>> getAllImageinfos(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<ImageinfoDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<ImageinfoDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = imageinfoService.getAllImageinfos(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = imageinfoService.getAllImageinfos(0, limit, sortBy);
        		else
        			response = imageinfoService.getAllImageinfos(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	
	
}

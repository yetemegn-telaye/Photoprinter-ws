package com.photoprinter.app.controller;

import com.photoprinter.app.dto.*;
import com.photoprinter.app.model.Shopimageinfo;
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
import com.photoprinter.app.service.ShopService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/shops"
        @class: ShopControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Shop, plus all operations related to Shop
*/
@RestController
@RequestMapping(path = "v0/shops", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ShopControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShopService shopService;

	/**
	    @endpoint: "POST /"
        @method: createShop
        @parameters: shopDto
        @return: DeferredResult<ResponseEntity<ShopDto>>  - Shop
        @description: Creates new Shop entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<ShopDto>> createShop(@RequestBody final ShopDto shopDto) throws Throwable {
		DeferredResult<ResponseEntity<ShopDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopDto>> response = shopService.createShop(shopDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{shopId}"
        @method: updateShop
        @parameters: shopDto
        @return: DeferredResult<ResponseEntity<ShopDto>> - Shop
        @description: Updates Shop
    */
	@RequestMapping(path = "/{shopId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<ShopDto>> updateShop(@PathVariable("shopId") final String shopId, @RequestBody final ShopDto shopDto)  throws Throwable {

		DeferredResult<ResponseEntity<ShopDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopDto>> response = shopService.updateShop(shopId, shopDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{shopId}"
        @method: deleteShop
        @parameters: shopId
        @return: DeferredResult<ResponseEntity<Void>> deleteShop - Void
        @description: Removes Shop
    */
	@RequestMapping(path= "/{shopId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteShop(@PathVariable("shopId") final String shopId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = shopService.deleteShop(shopId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{shopId}"
        @method: getShop
        @parameters: shopId
        @return: DeferredResult<ResponseEntity<ShopDto>> getShop - Shop
        @description: gets a Shop
    */
	@RequestMapping(path= "/{shopId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<ShopDto>> getShop(@PathVariable("shopId") final String shopId) throws Throwable {

		DeferredResult<ResponseEntity<ShopDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<ShopDto>> response = shopService.getShop(shopId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getShops
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<ShopDto>>> - List<Shop>
        @description: gets list of all Shops
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<ShopDto>>> getAllShops(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<ShopDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<ShopDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = shopService.getAllShops(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = shopService.getAllShops(0, limit, sortBy);
        		else
        			response = shopService.getAllShops(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	   /**
        @endpoint: "GET /{shopId}/shopImgInfos"
        @method: getShopImageinfos
        @parameters: shopId
        @return: ResponseEntity<Set<ImageinfoDto>>  - Set<Imageinfo>
        @description: gets list of all Imageinfos that belongs to a Shop
    */
	@RequestMapping(path= "/{shopId}/shopImgInfos", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<ShopimageinfoDto>>> getShopImageinfos(@PathVariable("shopId") final String shopId) throws Throwable {
		DeferredResult<ResponseEntity<Set<ShopimageinfoDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<ShopimageinfoDto>>> response =  shopService.getShopImageinfos(shopId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}
   /**
        @endpoint: "GET /{shopId}/orders"
        @method: getShopOrders
        @parameters: shopId
        @return: ResponseEntity<Set<OrderDto>>  - Set<Order>
        @description: gets list of all Orders that belongs to a Shop
    */
	@RequestMapping(path= "/{shopId}/orders", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<OrderDto>>> getShopOrders(@PathVariable("shopId") final String shopId) throws Throwable {
		DeferredResult<ResponseEntity<Set<OrderDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<OrderDto>>> response =  shopService.getShopOrders(shopId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}
   /**
        @endpoint: "GET /{shopId}/addresss"
        @method: getShopAddresss
        @parameters: shopId
        @return: ResponseEntity<Set<AddressDto>>  - Set<Address>
        @description: gets list of all Addresss that belongs to a Shop
    */
	@RequestMapping(path= "/{shopId}/addresss", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<AddressDto>>> getShopAddresss(@PathVariable("shopId") final String shopId) throws Throwable {
		DeferredResult<ResponseEntity<Set<AddressDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<AddressDto>>> response =  shopService.getShopAddresss(shopId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	
}

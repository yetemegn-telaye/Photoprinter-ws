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
import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.service.OrderService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;
import com.photoprinter.app.dto.ImageinfoDto;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/orders"
        @class: OrderControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a Order, plus all operations related to Order
*/
@RestController
@RequestMapping(path = "v0/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	/**
	    @endpoint: "POST /"
        @method: createOrder
        @parameters: orderDto
        @return: DeferredResult<ResponseEntity<OrderDto>>  - Order
        @description: Creates new Order entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<OrderDto>> createOrder(@RequestBody final OrderDto orderDto) throws Throwable {
		DeferredResult<ResponseEntity<OrderDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<OrderDto>> response = orderService.createOrder(orderDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{orderId}"
        @method: updateOrder
        @parameters: orderDto
        @return: DeferredResult<ResponseEntity<OrderDto>> - Order
        @description: Updates Order
    */
	@RequestMapping(path = "/{orderId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<OrderDto>> updateOrder(@PathVariable("orderId") final String orderId, @RequestBody final OrderDto orderDto)  throws Throwable {

		DeferredResult<ResponseEntity<OrderDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<OrderDto>> response = orderService.updateOrder(orderId, orderDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{orderId}"
        @method: deleteOrder
        @parameters: orderId
        @return: DeferredResult<ResponseEntity<Void>> deleteOrder - Void
        @description: Removes Order
    */
	@RequestMapping(path= "/{orderId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteOrder(@PathVariable("orderId") final String orderId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = orderService.deleteOrder(orderId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{orderId}"
        @method: getOrder
        @parameters: orderId
        @return: DeferredResult<ResponseEntity<OrderDto>> getOrder - Order
        @description: gets a Order
    */
	@RequestMapping(path= "/{orderId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<OrderDto>> getOrder(@PathVariable("orderId") final String orderId) throws Throwable {

		DeferredResult<ResponseEntity<OrderDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<OrderDto>> response = orderService.getOrder(orderId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getOrders
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<OrderDto>>> - List<Order>
        @description: gets list of all Orders
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<OrderDto>>> getAllOrders(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<OrderDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<OrderDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = orderService.getAllOrders(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = orderService.getAllOrders(0, limit, sortBy);
        		else
        			response = orderService.getAllOrders(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	   /**
        @endpoint: "GET /{orderId}/imageInfos"
        @method: getOrderImageinfos
        @parameters: orderId
        @return: ResponseEntity<Set<ImageinfoDto>>  - Set<Imageinfo>
        @description: gets list of all Imageinfos that belongs to a Order
    */
	@RequestMapping(path= "/{orderId}/imageInfos", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<ImageinfoDto>>> getOrderImageinfos(@PathVariable("orderId") final String orderId) throws Throwable {
		DeferredResult<ResponseEntity<Set<ImageinfoDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<ImageinfoDto>>> response =  orderService.getOrderImageinfos(orderId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	
}

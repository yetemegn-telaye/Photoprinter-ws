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
import com.photoprinter.app.dto.UserDto;
import com.photoprinter.app.service.UserService;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.logger.Log;
import com.photoprinter.app.dto.ShopDto;

import org.springframework.web.context.request.async.DeferredResult;
import io.reactivex.Observable;
import java.util.Set;

/**
        @author: Abel Lemma
        @version: 0
        @created_on: <<created_on>>
	    @path: "/v0/users"
        @class: UserControllerV0
        @description: Handles CRUD operations, extended retrieval operations to get entities
        that belongs a User, plus all operations related to User
*/
@RestController
@RequestMapping(path = "v0/users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerV0 {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	/**
	    @endpoint: "POST /"
        @method: createUser
        @parameters: userDto
        @return: DeferredResult<ResponseEntity<UserDto>>  - User
        @description: Creates new User entry
	*/
	@RequestMapping(method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<UserDto>> createUser(@RequestBody final UserDto userDto) throws Throwable {
		DeferredResult<ResponseEntity<UserDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<UserDto>> response = userService.createUser(userDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "PUT /{userId}"
        @method: updateUser
        @parameters: userDto
        @return: DeferredResult<ResponseEntity<UserDto>> - User
        @description: Updates User
    */
	@RequestMapping(path = "/{userId}", method= RequestMethod.PUT)
	public DeferredResult<ResponseEntity<UserDto>> updateUser(@PathVariable("userId") final String userId, @RequestBody final UserDto userDto)  throws Throwable {

		DeferredResult<ResponseEntity<UserDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<UserDto>> response = userService.updateUser(userId, userDto);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "DELETE /{userId}"
        @method: deleteUser
        @parameters: userId
        @return: DeferredResult<ResponseEntity<Void>> deleteUser - Void
        @description: Removes User
    */
	@RequestMapping(path= "/{userId}", method= RequestMethod.DELETE)
	public DeferredResult<ResponseEntity<Void>> deleteUser(@PathVariable("userId") final String userId)  throws Throwable {

		DeferredResult<ResponseEntity<Void>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Void>> response = userService.deleteUser(userId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /{userId}"
        @method: getUser
        @parameters: userId
        @return: DeferredResult<ResponseEntity<UserDto>> getUser - User
        @description: gets a User
    */
	@RequestMapping(path= "/{userId}", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<UserDto>> getUser(@PathVariable("userId") final String userId) throws Throwable {

		DeferredResult<ResponseEntity<UserDto>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<UserDto>> response = userService.getUser(userId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	/**
	    @endpoint: "GET /"
        @method: getUsers
        @optional-parameters: page, limit, sortBy
        @return: DeferredResult<ResponseEntity<List<UserDto>>> - List<User>
        @description: gets list of all Users
    */
	@RequestMapping(method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<List<UserDto>>> getAllUsers(@RequestParam(name = "page", required = false) final Integer page, @RequestParam(name = "limit", required = false) final Integer limit, @RequestParam(name="sortBy", required = false) final List<String> sortBy)  throws Throwable {
		DeferredResult<ResponseEntity<List<UserDto>>> deferred = new DeferredResult<>();
        		Observable<ResponseEntity<List<UserDto>>> response = null;

        		//If limit is not provided
        		if(limit == null) {
        			response = userService.getAllUsers(sortBy);
        			response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        			return deferred;
        		}
        		//If page is not provided
        		if(page == null)
        			response = userService.getAllUsers(0, limit, sortBy);
        		else
        			response = userService.getAllUsers(page, limit, sortBy);
        		response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        		return deferred;
	}
	
	   /**
        @endpoint: "GET /{userId}/shops"
        @method: getUserShops
        @parameters: userId
        @return: ResponseEntity<Set<ShopDto>>  - Set<Shop>
        @description: gets list of all Shops that belongs to a User
    */
	@RequestMapping(path= "/{userId}/shops", method= RequestMethod.GET)
	public DeferredResult<ResponseEntity<Set<ShopDto>>> getUserShops(@PathVariable("userId") final String userId) throws Throwable {
		DeferredResult<ResponseEntity<Set<ShopDto>>> deferred = new DeferredResult<>();
        Observable<ResponseEntity<Set<ShopDto>>> response =  userService.getUserShops(userId);
        response.blockingSubscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
	}

	
}

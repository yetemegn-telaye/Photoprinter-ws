package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.UserDto;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.dto.ShopDto;


public interface UserService {
	Observable<ResponseEntity<UserDto>> createUser(final UserDto userDto) throws ServiceException;
	Observable<ResponseEntity<UserDto>> updateUser(final String userId, final UserDto userDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteUser(final String userId) throws ServiceException;
	Observable<ResponseEntity<UserDto>> getUser(final String userId) throws ServiceException;
	Observable<ResponseEntity<List<UserDto>>> getAllUsers(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<UserDto>>> getAllUsers(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
		Observable<ResponseEntity<Set<ShopDto>>> getUserShops(final String userId) throws ServiceException;

}

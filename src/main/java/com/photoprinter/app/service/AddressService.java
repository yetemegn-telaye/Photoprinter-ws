package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.exceptionHandling.ServiceException;


public interface AddressService {
	Observable<ResponseEntity<AddressDto>> createAddress(final AddressDto addressDto) throws ServiceException;
	Observable<ResponseEntity<AddressDto>> updateAddress(final String addressId, final AddressDto addressDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteAddress(final String addressId) throws ServiceException;
	Observable<ResponseEntity<AddressDto>> getAddress(final String addressId) throws ServiceException;
	Observable<ResponseEntity<List<AddressDto>>> getAllAddresss(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<AddressDto>>> getAllAddresss(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
	
}

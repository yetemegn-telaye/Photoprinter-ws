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
import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.model.Address;
import com.photoprinter.app.repository.AddressRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Client;
import com.photoprinter.app.model.Shop;



@Service
public class AddressServiceImpl implements AddressService {

	@Autowired 
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<AddressDto>> createAddress(final AddressDto addressDto) throws ServiceException {
		if(addressDto == null)
			throw new ServiceException(new MalformedRequestException("Address")); 
		Address savedAddress = addressRepository.save(modelMapper.map(addressDto, Address.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedAddress, AddressDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<AddressDto>> updateAddress(String addressId, AddressDto addressDto) throws ServiceException {
		if(addressDto == null)
			throw new ServiceException(new MalformedRequestException("Address"));
		Address currentAddress = addressRepository.findOne(addressId);
		if(currentAddress == null)
			throw new ServiceException(new ResourceNotFoundException(Address.class));
				
		currentAddress.setKebele(addressDto.getKebele());
		
		currentAddress.setCity(addressDto.getCity());
		
		currentAddress.setCountry(addressDto.getCountry());
		
		currentAddress.setStreetAddress(addressDto.getStreetAddress());
		
		currentAddress.setZipCode(addressDto.getZipCode());
		
		currentAddress.setClient(modelMapper.map(addressDto.getClient(), Client.class));
		
		currentAddress.setShop(modelMapper.map(addressDto.getShop(), Shop.class));
		
		currentAddress.setWoreda(addressDto.getWoreda());
		
		currentAddress.setLocation(addressDto.getLocation());

		Address savedAddress = addressRepository.save(currentAddress);
		if(savedAddress == null)
			throw new DataAccessResourceFailureException("Address Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedAddress, AddressDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteAddress(final String addressId) throws ServiceException {
		if(addressId == null || addressRepository.getOne(addressId) == null)
			throw new ServiceException(new ResourceNotFoundException("Address"));
		addressRepository.delete(addressRepository.getOne(addressId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<AddressDto>> getAddress(final String addressId) throws ServiceException {
		Address address = addressRepository.getOne(addressId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(address, AddressDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<AddressDto>>> getAllAddresss(final List<String> sortAttributes) throws ServiceException {
		List<Address> addresss = null;
		Page<Address> addresssPage = null;
		if(sortAttributes == null) {
			addresss = addressRepository.findAll();
		} else {
			addresssPage = addressRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			addresss = addresssPage.getContent();	
		}
		return getResults(addresss);
	}
	
	@Override
	public Observable<ResponseEntity<List<AddressDto>>> getAllAddresss(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Address> addresss = null;
		Page<Address> addresssPage = null;
		addresssPage = addressRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		addresss = addresssPage.getContent();	
		return getResults(addresss);
	}
	
	
	private Observable<ResponseEntity<List<AddressDto>>> getResults(final List<Address> addresss) throws ServiceException{
		List<AddressDto> addresssFound = new ArrayList<>();
		if (addresss == null || addresss.isEmpty())
			return Observable.just(new ResponseEntity<>(addresssFound, HttpStatus.NOT_FOUND));
		for(Address address : addresss){
			addresssFound.add(modelMapper.map(address, AddressDto.class));
		}	
		return Observable.just(new ResponseEntity<>(addresssFound, HttpStatus.OK));
	}
	
	
}

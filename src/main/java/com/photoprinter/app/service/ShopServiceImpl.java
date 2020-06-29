package com.photoprinter.app.service;

import com.photoprinter.app.dto.*;
import com.photoprinter.app.model.*;
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

import com.photoprinter.app.repository.ShopRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;


@Service
public class ShopServiceImpl implements ShopService {

	@Autowired 
	private ShopRepository shopRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<ShopDto>> createShop(final ShopDto shopDto) throws ServiceException {
		if(shopDto == null)
			throw new ServiceException(new MalformedRequestException("Shop")); 
		Shop savedShop = shopRepository.save(modelMapper.map(shopDto, Shop.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedShop, ShopDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<ShopDto>> updateShop(String shopId, ShopDto shopDto) throws ServiceException {
		if(shopDto == null)
			throw new ServiceException(new MalformedRequestException("Shop"));
		Shop currentShop = shopRepository.findOne(shopId);
		if(currentShop == null)
			throw new ServiceException(new ResourceNotFoundException(Shop.class));
				
		currentShop.setWorkingDays(shopDto.getWorkingDays());
		
		currentShop.setName(shopDto.getName());
		java.lang.reflect.Type ShopImgInfosSet  = new TypeToken<Set<Imageinfo>>() {}.getType();	
		currentShop.setShopImgInfos(modelMapper.map(shopDto.getShopImgInfos(), ShopImgInfosSet));
		
		currentShop.setWorkingHrStart(shopDto.getWorkingHrStart());
		java.lang.reflect.Type OrdersSet  = new TypeToken<Set<Order>>() {}.getType();	
		currentShop.setOrders(modelMapper.map(shopDto.getOrders(), OrdersSet));
		java.lang.reflect.Type AddresssSet  = new TypeToken<Set<Address>>() {}.getType();	
		currentShop.setAddresss(modelMapper.map(shopDto.getAddresss(), AddresssSet));
		
		currentShop.setDeliveryOpt(shopDto.getDeliveryOpt());
		
		currentShop.setContact(modelMapper.map(shopDto.getContact(), Contact.class));
		
		currentShop.setWorkingHrEnd(shopDto.getWorkingHrEnd());

		Shop savedShop = shopRepository.save(currentShop);
		if(savedShop == null)
			throw new DataAccessResourceFailureException("Shop Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedShop, ShopDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteShop(final String shopId) throws ServiceException {
		if(shopId == null || shopRepository.getOne(shopId) == null)
			throw new ServiceException(new ResourceNotFoundException("Shop"));
		shopRepository.delete(shopRepository.getOne(shopId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<ShopDto>> getShop(final String shopId) throws ServiceException {
		Shop shop = shopRepository.getOne(shopId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(shop, ShopDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<ShopDto>>> getAllShops(final List<String> sortAttributes) throws ServiceException {
		List<Shop> shops = null;
		Page<Shop> shopsPage = null;
		if(sortAttributes == null) {
			shops = shopRepository.findAll();
		} else {
			shopsPage = shopRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			shops = shopsPage.getContent();	
		}
		return getResults(shops);
	}
	
	@Override
	public Observable<ResponseEntity<List<ShopDto>>> getAllShops(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Shop> shops = null;
		Page<Shop> shopsPage = null;
		shopsPage = shopRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		shops = shopsPage.getContent();	
		return getResults(shops);
	}
	
	
	private Observable<ResponseEntity<List<ShopDto>>> getResults(final List<Shop> shops) throws ServiceException{
		List<ShopDto> shopsFound = new ArrayList<>();
		if (shops == null || shops.isEmpty())
			return Observable.just(new ResponseEntity<>(shopsFound, HttpStatus.NOT_FOUND));
		for(Shop shop : shops){
			shopsFound.add(modelMapper.map(shop, ShopDto.class));
		}	
		return Observable.just(new ResponseEntity<>(shopsFound, HttpStatus.OK));
	}
	
		
	@Override
	public Observable<ResponseEntity<Set<ShopimageinfoDto>>> getShopImageinfos(final String shopId) throws ServiceException {
		Set<ShopimageinfoDto> shopImgInfoDtos = new HashSet();
		Shop shop = shopRepository.getOne(shopId);
		if(shopId == null || shop == null)
			return Observable.just(new ResponseEntity<>(shopImgInfoDtos, HttpStatus.NOT_FOUND));
		for(Shopimageinfo shopImgInfo: shop.getShopImgInfos()) {
			shopImgInfoDtos.add(modelMapper.map(shopImgInfo, ShopimageinfoDto.class));
		}
		return Observable.just(new ResponseEntity<>(shopImgInfoDtos, HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<Set<OrderDto>>> getShopOrders(final String shopId) throws ServiceException {
		Set<OrderDto> orderDtos = new HashSet();
		Shop shop = shopRepository.getOne(shopId);
		if(shopId == null || shop == null)
			return Observable.just(new ResponseEntity<>(orderDtos, HttpStatus.NOT_FOUND));
		for(Order order: shop.getOrders()) {
			orderDtos.add(modelMapper.map(order, OrderDto.class));
		}
		return Observable.just(new ResponseEntity<>(orderDtos, HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<Set<AddressDto>>> getShopAddresss(final String shopId) throws ServiceException {
		Set<AddressDto> addressDtos = new HashSet();
		Shop shop = shopRepository.getOne(shopId);
		if(shopId == null || shop == null)
			return Observable.just(new ResponseEntity<>(addressDtos, HttpStatus.NOT_FOUND));
		for(Address address: shop.getAddresss()) {
			addressDtos.add(modelMapper.map(address, AddressDto.class));
		}
		return Observable.just(new ResponseEntity<>(addressDtos, HttpStatus.OK));
	}

}

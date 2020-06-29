package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import com.photoprinter.app.dto.*;
import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.exceptionHandling.ServiceException;


public interface ShopService {
	Observable<ResponseEntity<ShopDto>> createShop(final ShopDto shopDto) throws ServiceException;
	Observable<ResponseEntity<ShopDto>> updateShop(final String shopId, final ShopDto shopDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteShop(final String shopId) throws ServiceException;
	Observable<ResponseEntity<ShopDto>> getShop(final String shopId) throws ServiceException;
	Observable<ResponseEntity<List<ShopDto>>> getAllShops(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<ShopDto>>> getAllShops(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
		Observable<ResponseEntity<Set<ShopimageinfoDto>>> getShopImageinfos(final String shopId) throws ServiceException;
	Observable<ResponseEntity<Set<OrderDto>>> getShopOrders(final String shopId) throws ServiceException;
	Observable<ResponseEntity<Set<AddressDto>>> getShopAddresss(final String shopId) throws ServiceException;

}

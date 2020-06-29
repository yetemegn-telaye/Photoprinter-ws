package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.ShopimageinfoDto;
import com.photoprinter.app.exceptionHandling.ServiceException;


public interface ShopimageinfoService {
	Observable<ResponseEntity<ShopimageinfoDto>> createShopimageinfo(final ShopimageinfoDto shopimageinfoDto) throws ServiceException;
	Observable<ResponseEntity<ShopimageinfoDto>> updateShopimageinfo(final String shopimageinfoId, final ShopimageinfoDto shopimageinfoDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteShopimageinfo(final String shopimageinfoId) throws ServiceException;
	Observable<ResponseEntity<ShopimageinfoDto>> getShopimageinfo(final String shopimageinfoId) throws ServiceException;
	Observable<ResponseEntity<List<ShopimageinfoDto>>> getAllShopimageinfos(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<ShopimageinfoDto>>> getAllShopimageinfos(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
	
}

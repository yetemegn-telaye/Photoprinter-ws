package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.ImageinfoDto;
import com.photoprinter.app.exceptionHandling.ServiceException;


public interface ImageinfoService {
	Observable<ResponseEntity<ImageinfoDto>> createImageinfo(final ImageinfoDto imageinfoDto) throws ServiceException;
	Observable<ResponseEntity<ImageinfoDto>> updateImageinfo(final String imageinfoId, final ImageinfoDto imageinfoDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteImageinfo(final String imageinfoId) throws ServiceException;
	Observable<ResponseEntity<ImageinfoDto>> getImageinfo(final String imageinfoId) throws ServiceException;
	Observable<ResponseEntity<List<ImageinfoDto>>> getAllImageinfos(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<ImageinfoDto>>> getAllImageinfos(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
	
}

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
import com.photoprinter.app.dto.ShopimageinfoDto;
import com.photoprinter.app.model.Shopimageinfo;
import com.photoprinter.app.repository.ShopimageinfoRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Shop;



@Service
public class ShopimageinfoServiceImpl implements ShopimageinfoService {

	@Autowired 
	private ShopimageinfoRepository shopimageinfoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<ShopimageinfoDto>> createShopimageinfo(final ShopimageinfoDto shopimageinfoDto) throws ServiceException {
		if(shopimageinfoDto == null)
			throw new ServiceException(new MalformedRequestException("Shopimageinfo")); 
		Shopimageinfo savedShopimageinfo = shopimageinfoRepository.save(modelMapper.map(shopimageinfoDto, Shopimageinfo.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedShopimageinfo, ShopimageinfoDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<ShopimageinfoDto>> updateShopimageinfo(String shopimageinfoId, ShopimageinfoDto shopimageinfoDto) throws ServiceException {
		if(shopimageinfoDto == null)
			throw new ServiceException(new MalformedRequestException("Shopimageinfo"));
		Shopimageinfo currentShopimageinfo = shopimageinfoRepository.findOne(shopimageinfoId);
		if(currentShopimageinfo == null)
			throw new ServiceException(new ResourceNotFoundException(Shopimageinfo.class));
				
		currentShopimageinfo.setImagePrice(shopimageinfoDto.getImagePrice());
		
		currentShopimageinfo.setImageSize(shopimageinfoDto.getImageSize());
		
		currentShopimageinfo.setShop(modelMapper.map(shopimageinfoDto.getShop(), Shop.class));

		Shopimageinfo savedShopimageinfo = shopimageinfoRepository.save(currentShopimageinfo);
		if(savedShopimageinfo == null)
			throw new DataAccessResourceFailureException("Shopimageinfo Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedShopimageinfo, ShopimageinfoDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteShopimageinfo(final String shopimageinfoId) throws ServiceException {
		if(shopimageinfoId == null || shopimageinfoRepository.getOne(shopimageinfoId) == null)
			throw new ServiceException(new ResourceNotFoundException("Shopimageinfo"));
		shopimageinfoRepository.delete(shopimageinfoRepository.getOne(shopimageinfoId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<ShopimageinfoDto>> getShopimageinfo(final String shopimageinfoId) throws ServiceException {
		Shopimageinfo shopimageinfo = shopimageinfoRepository.getOne(shopimageinfoId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(shopimageinfo, ShopimageinfoDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<ShopimageinfoDto>>> getAllShopimageinfos(final List<String> sortAttributes) throws ServiceException {
		List<Shopimageinfo> shopimageinfos = null;
		Page<Shopimageinfo> shopimageinfosPage = null;
		if(sortAttributes == null) {
			shopimageinfos = shopimageinfoRepository.findAll();
		} else {
			shopimageinfosPage = shopimageinfoRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			shopimageinfos = shopimageinfosPage.getContent();	
		}
		return getResults(shopimageinfos);
	}
	
	@Override
	public Observable<ResponseEntity<List<ShopimageinfoDto>>> getAllShopimageinfos(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Shopimageinfo> shopimageinfos = null;
		Page<Shopimageinfo> shopimageinfosPage = null;
		shopimageinfosPage = shopimageinfoRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		shopimageinfos = shopimageinfosPage.getContent();	
		return getResults(shopimageinfos);
	}
	
	
	private Observable<ResponseEntity<List<ShopimageinfoDto>>> getResults(final List<Shopimageinfo> shopimageinfos) throws ServiceException{
		List<ShopimageinfoDto> shopimageinfosFound = new ArrayList<>();
		if (shopimageinfos == null || shopimageinfos.isEmpty())
			return Observable.just(new ResponseEntity<>(shopimageinfosFound, HttpStatus.NOT_FOUND));
		for(Shopimageinfo shopimageinfo : shopimageinfos){
			shopimageinfosFound.add(modelMapper.map(shopimageinfo, ShopimageinfoDto.class));
		}	
		return Observable.just(new ResponseEntity<>(shopimageinfosFound, HttpStatus.OK));
	}
	
	
}

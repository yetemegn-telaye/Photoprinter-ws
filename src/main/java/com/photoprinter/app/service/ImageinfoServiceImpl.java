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
import com.photoprinter.app.dto.ImageinfoDto;
import com.photoprinter.app.model.Imageinfo;
import com.photoprinter.app.repository.ImageinfoRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Order;



@Service
public class ImageinfoServiceImpl implements ImageinfoService {

	@Autowired 
	private ImageinfoRepository imageinfoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<ImageinfoDto>> createImageinfo(final ImageinfoDto imageinfoDto) throws ServiceException {
		if(imageinfoDto == null)
			throw new ServiceException(new MalformedRequestException("Imageinfo")); 
		Imageinfo savedImageinfo = imageinfoRepository.save(modelMapper.map(imageinfoDto, Imageinfo.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedImageinfo, ImageinfoDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<ImageinfoDto>> updateImageinfo(String imageinfoId, ImageinfoDto imageinfoDto) throws ServiceException {
		if(imageinfoDto == null)
			throw new ServiceException(new MalformedRequestException("Imageinfo"));
		Imageinfo currentImageinfo = imageinfoRepository.findOne(imageinfoId);
		if(currentImageinfo == null)
			throw new ServiceException(new ResourceNotFoundException(Imageinfo.class));
				
		currentImageinfo.setImageSize(imageinfoDto.getImageSize());
		
		currentImageinfo.setImage(imageinfoDto.getImage());
		
		currentImageinfo.setCount(imageinfoDto.getCount());
		
		currentImageinfo.setOrder(modelMapper.map(imageinfoDto.getOrder(), Order.class));

		Imageinfo savedImageinfo = imageinfoRepository.save(currentImageinfo);
		if(savedImageinfo == null)
			throw new DataAccessResourceFailureException("Imageinfo Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedImageinfo, ImageinfoDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteImageinfo(final String imageinfoId) throws ServiceException {
		if(imageinfoId == null || imageinfoRepository.getOne(imageinfoId) == null)
			throw new ServiceException(new ResourceNotFoundException("Imageinfo"));
		imageinfoRepository.delete(imageinfoRepository.getOne(imageinfoId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<ImageinfoDto>> getImageinfo(final String imageinfoId) throws ServiceException {
		Imageinfo imageinfo = imageinfoRepository.getOne(imageinfoId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(imageinfo, ImageinfoDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<ImageinfoDto>>> getAllImageinfos(final List<String> sortAttributes) throws ServiceException {
		List<Imageinfo> imageinfos = null;
		Page<Imageinfo> imageinfosPage = null;
		if(sortAttributes == null) {
			imageinfos = imageinfoRepository.findAll();
		} else {
			imageinfosPage = imageinfoRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			imageinfos = imageinfosPage.getContent();	
		}
		return getResults(imageinfos);
	}
	
	@Override
	public Observable<ResponseEntity<List<ImageinfoDto>>> getAllImageinfos(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Imageinfo> imageinfos = null;
		Page<Imageinfo> imageinfosPage = null;
		imageinfosPage = imageinfoRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		imageinfos = imageinfosPage.getContent();	
		return getResults(imageinfos);
	}
	
	
	private Observable<ResponseEntity<List<ImageinfoDto>>> getResults(final List<Imageinfo> imageinfos) throws ServiceException{
		List<ImageinfoDto> imageinfosFound = new ArrayList<>();
		if (imageinfos == null || imageinfos.isEmpty())
			return Observable.just(new ResponseEntity<>(imageinfosFound, HttpStatus.NOT_FOUND));
		for(Imageinfo imageinfo : imageinfos){
			imageinfosFound.add(modelMapper.map(imageinfo, ImageinfoDto.class));
		}	
		return Observable.just(new ResponseEntity<>(imageinfosFound, HttpStatus.OK));
	}
	
	
}

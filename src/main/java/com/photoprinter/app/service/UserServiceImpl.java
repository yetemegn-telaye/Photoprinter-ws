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
import com.photoprinter.app.dto.UserDto;
import com.photoprinter.app.model.User;
import com.photoprinter.app.repository.UserRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Client;
import com.photoprinter.app.model.Shop;

import com.photoprinter.app.dto.ShopDto;


@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<UserDto>> createUser(final UserDto userDto) throws ServiceException {
		if(userDto == null)
			throw new ServiceException(new MalformedRequestException("User")); 
		User savedUser = userRepository.save(modelMapper.map(userDto, User.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<UserDto>> updateUser(String userId, UserDto userDto) throws ServiceException {
		if(userDto == null)
			throw new ServiceException(new MalformedRequestException("User"));
		User currentUser = userRepository.findOne(userId);
		if(currentUser == null)
			throw new ServiceException(new ResourceNotFoundException(User.class));
				
		currentUser.setClient(modelMapper.map(userDto.getClient(), Client.class));
		
		currentUser.setUserName(userDto.getUserName());
		
		currentUser.setRole(userDto.getRole());
		java.lang.reflect.Type ShopsSet  = new TypeToken<Set<Shop>>() {}.getType();	
		currentUser.setShops(modelMapper.map(userDto.getShops(), ShopsSet));
		
		currentUser.setPassword(userDto.getPassword());

		User savedUser = userRepository.save(currentUser);
		if(savedUser == null)
			throw new DataAccessResourceFailureException("User Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteUser(final String userId) throws ServiceException {
		if(userId == null || userRepository.getOne(userId) == null)
			throw new ServiceException(new ResourceNotFoundException("User"));
		userRepository.delete(userRepository.getOne(userId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<UserDto>> getUser(final String userId) throws ServiceException {
		User user = userRepository.getOne(userId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(user, UserDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<UserDto>>> getAllUsers(final List<String> sortAttributes) throws ServiceException {
		List<User> users = null;
		Page<User> usersPage = null;
		if(sortAttributes == null) {
			users = userRepository.findAll();
		} else {
			usersPage = userRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			users = usersPage.getContent();	
		}
		return getResults(users);
	}
	
	@Override
	public Observable<ResponseEntity<List<UserDto>>> getAllUsers(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<User> users = null;
		Page<User> usersPage = null;
		usersPage = userRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		users = usersPage.getContent();	
		return getResults(users);
	}
	
	
	private Observable<ResponseEntity<List<UserDto>>> getResults(final List<User> users) throws ServiceException{
		List<UserDto> usersFound = new ArrayList<>();
		if (users == null || users.isEmpty())
			return Observable.just(new ResponseEntity<>(usersFound, HttpStatus.NOT_FOUND));
		for(User user : users){
			usersFound.add(modelMapper.map(user, UserDto.class));
		}	
		return Observable.just(new ResponseEntity<>(usersFound, HttpStatus.OK));
	}
	
		
	@Override
	public Observable<ResponseEntity<Set<ShopDto>>> getUserShops(final String userId) throws ServiceException {
		Set<ShopDto> shopDtos = new HashSet();
		User user = userRepository.getOne(userId);
		if(userId == null || user == null)
			return Observable.just(new ResponseEntity<>(shopDtos, HttpStatus.NOT_FOUND));
		for(Shop shop: user.getShops()) {
			shopDtos.add(modelMapper.map(shop, ShopDto.class));
		}
		return Observable.just(new ResponseEntity<>(shopDtos, HttpStatus.OK));
	}

}

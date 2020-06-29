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
import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.model.Order;
import com.photoprinter.app.repository.OrderRepository;
import org.springframework.dao.DataAccessResourceFailureException;
import com.photoprinter.app.exceptionHandling.*;
import io.reactivex.Observable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.photoprinter.app.model.Imageinfo;

import com.photoprinter.app.dto.ImageinfoDto;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired 
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Observable<ResponseEntity<OrderDto>> createOrder(final OrderDto orderDto) throws ServiceException {
		if(orderDto == null)
			throw new ServiceException(new MalformedRequestException("Order")); 
		Order savedOrder = orderRepository.save(modelMapper.map(orderDto, Order.class));
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedOrder, OrderDto.class), HttpStatus.CREATED));
	}
	
	@Override
	public Observable<ResponseEntity<OrderDto>> updateOrder(String orderId, OrderDto orderDto) throws ServiceException {
		if(orderDto == null)
			throw new ServiceException(new MalformedRequestException("Order"));
		Order currentOrder = orderRepository.findOne(orderId);
		if(currentOrder == null)
			throw new ServiceException(new ResourceNotFoundException(Order.class));
				
		currentOrder.setStatus(orderDto.getStatus());
		java.lang.reflect.Type ImageInfosSet  = new TypeToken<Set<Imageinfo>>() {}.getType();	
		currentOrder.setImageInfos(modelMapper.map(orderDto.getImageInfos(), ImageInfosSet));
		
		currentOrder.setDate(orderDto.getDate());

		Order savedOrder = orderRepository.save(currentOrder);
		if(savedOrder == null)
			throw new DataAccessResourceFailureException("Order Not Saved");
		return Observable.just(new ResponseEntity<>(modelMapper.map(savedOrder, OrderDto.class), HttpStatus.ACCEPTED));
	}
	
	@Override
	public Observable<ResponseEntity<Void>> deleteOrder(final String orderId) throws ServiceException {
		if(orderId == null || orderRepository.getOne(orderId) == null)
			throw new ServiceException(new ResourceNotFoundException("Order"));
		orderRepository.delete(orderRepository.getOne(orderId));
		return Observable.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}
	
	@Override
	public Observable<ResponseEntity<OrderDto>> getOrder(final String orderId) throws ServiceException {
		Order order = orderRepository.getOne(orderId);
		return Observable.just(new ResponseEntity<>(modelMapper.map(order, OrderDto.class), HttpStatus.OK));
	}
	
	@Override
	public Observable<ResponseEntity<List<OrderDto>>> getAllOrders(final List<String> sortAttributes) throws ServiceException {
		List<Order> orders = null;
		Page<Order> ordersPage = null;
		if(sortAttributes == null) {
			orders = orderRepository.findAll();
		} else {
			ordersPage = orderRepository.findAll(new PageRequest(0, 100, ServiceUtils.getSortCriteria(sortAttributes)));
			orders = ordersPage.getContent();	
		}
		return getResults(orders);
	}
	
	@Override
	public Observable<ResponseEntity<List<OrderDto>>> getAllOrders(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException {
		List<Order> orders = null;
		Page<Order> ordersPage = null;
		ordersPage = orderRepository.findAll(new PageRequest(page, limit, ServiceUtils.getSortCriteria(sortAttributes)));
		orders = ordersPage.getContent();	
		return getResults(orders);
	}
	
	
	private Observable<ResponseEntity<List<OrderDto>>> getResults(final List<Order> orders) throws ServiceException{
		List<OrderDto> ordersFound = new ArrayList<>();
		if (orders == null || orders.isEmpty())
			return Observable.just(new ResponseEntity<>(ordersFound, HttpStatus.NOT_FOUND));
		for(Order order : orders){
			ordersFound.add(modelMapper.map(order, OrderDto.class));
		}	
		return Observable.just(new ResponseEntity<>(ordersFound, HttpStatus.OK));
	}
	
		
	@Override
	public Observable<ResponseEntity<Set<ImageinfoDto>>> getOrderImageinfos(final String orderId) throws ServiceException {
		Set<ImageinfoDto> imageInfoDtos = new HashSet();
		Order order = orderRepository.getOne(orderId);
		if(orderId == null || order == null)
			return Observable.just(new ResponseEntity<>(imageInfoDtos, HttpStatus.NOT_FOUND));
		for(Imageinfo imageInfo: order.getImageInfos()) {
			imageInfoDtos.add(modelMapper.map(imageInfo, ImageinfoDto.class));
		}
		return Observable.just(new ResponseEntity<>(imageInfoDtos, HttpStatus.OK));
	}

}

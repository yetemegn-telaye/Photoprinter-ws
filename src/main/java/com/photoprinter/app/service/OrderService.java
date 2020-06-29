package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.dto.ImageinfoDto;


public interface OrderService {
	Observable<ResponseEntity<OrderDto>> createOrder(final OrderDto orderDto) throws ServiceException;
	Observable<ResponseEntity<OrderDto>> updateOrder(final String orderId, final OrderDto orderDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteOrder(final String orderId) throws ServiceException;
	Observable<ResponseEntity<OrderDto>> getOrder(final String orderId) throws ServiceException;
	Observable<ResponseEntity<List<OrderDto>>> getAllOrders(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<OrderDto>>> getAllOrders(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
		Observable<ResponseEntity<Set<ImageinfoDto>>> getOrderImageinfos(final String orderId) throws ServiceException;

}

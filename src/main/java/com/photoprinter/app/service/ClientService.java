package com.photoprinter.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import io.reactivex.Observable;

import com.photoprinter.app.dto.ClientDto;
import com.photoprinter.app.exceptionHandling.ServiceException;
import com.photoprinter.app.dto.OrderDto;
import com.photoprinter.app.dto.AddressDto;
import com.photoprinter.app.dto.ImageinfoDto;


public interface ClientService {
	Observable<ResponseEntity<ClientDto>> createClient(final ClientDto clientDto) throws ServiceException;
	Observable<ResponseEntity<ClientDto>> updateClient(final String clientId, final ClientDto clientDto) throws ServiceException;
	Observable<ResponseEntity<Void>> deleteClient(final String clientId) throws ServiceException;
	Observable<ResponseEntity<ClientDto>> getClient(final String clientId) throws ServiceException;
	Observable<ResponseEntity<List<ClientDto>>> getAllClients(final List<String> sortAttributes) throws ServiceException;
	Observable<ResponseEntity<List<ClientDto>>> getAllClients(final Integer page, final Integer limit, final List<String> sortAttributes) throws ServiceException;
		Observable<ResponseEntity<Set<OrderDto>>> getClientOrders(final String clientId) throws ServiceException;
	Observable<ResponseEntity<Set<AddressDto>>> getClientAddresss(final String clientId) throws ServiceException;
	Observable<ResponseEntity<Set<ImageinfoDto>>> getClientImageinfos(final String clientId) throws ServiceException;

}

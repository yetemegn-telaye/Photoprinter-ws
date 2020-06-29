package com.photoprinter.app.exceptionHandling;


import com.photoprinter.app.exceptionHandling.model.ExceptionEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GenericExceptionMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ExceptionEntity> handleUnkownException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				ExceptionMsgURI.UNKOWON_EXCEPTION);
	}

	@ExceptionHandler(value = { DataAccessException.class })
	protected ResponseEntity<ExceptionEntity> handleDataIntegrityViolationException(DataAccessException ex,
																					WebRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), ex.getRootCause().getMessage(), HttpStatus.CONFLICT,
				ExceptionMsgURI.DATA_ACCESS_VIOLATION);
	}

	@ExceptionHandler(value = { DuplicateResourceException.class })
	protected ResponseEntity<ExceptionEntity> handleDuplicateStudentException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), ex.getMessage(), HttpStatus.CONFLICT,
				ExceptionMsgURI.DUPLICATE_RESOURCE);
	}

	// handler for StudentNotFoundException thrown by the controller.
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<ExceptionEntity> handleNotFoundException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), ex.getMessage(), HttpStatus.NOT_FOUND,
				ExceptionMsgURI.RESOURCE_NOT_FOUND);
	}
	
	@ExceptionHandler(value = { MalformedRequestException.class })
	protected ResponseEntity<ExceptionEntity> handleMalformedRequestException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return handleException(ex.getMessage(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				ExceptionMsgURI.MALFORMED_REQUEST);
	}
	
	
	/*@ExceptionHandler(value = { ServiceException.class })
	protected ResponseEntity<ExceptionEntity> handleServiceException(Exception ex, WebRequest request) throws Throwable {
		// LOGGER.error(ex.getMessage(), ex);
		switch(ex.getCause().getClass().getSimpleName()){
			case "ResourceNotFoundException":
				return handleException(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.NOT_FOUND,
						ExceptionMsgURI.RESOURCE_NOT_FOUND);
			case "DuplicateResourceException":
				return handleException(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.CONFLICT,
						ExceptionMsgURI.DUPLICATE_RESOURCE);
			case "MalformedRequestException":
				return handleException(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.CONFLICT,
						ExceptionMsgURI.DUPLICATE_RESOURCE);
		}
		return handleException(ex.getMessage(), ex.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				ExceptionMsgURI.UNKOWON_EXCEPTION);
	}*/

	// common method for all exception categories.
	private ResponseEntity<ExceptionEntity> handleException(String userMessage, String internalMessage,
                                                            HttpStatus status, ExceptionMsgURI uri) {
		return new ResponseEntity<ExceptionEntity>(
				new ExceptionEntity(userMessage, internalMessage, status.value(), uri.getValue()), status);
	}
}

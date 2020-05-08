package com.portal.fotografia.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portal.fotografia.service.exception.AuthorizationException;
import com.portal.fotografia.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandarError err = new StandarError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), "Não encontrado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandarError> authorization(AuthorizationException e, HttpServletRequest request) {
		StandarError err = new StandarError(System.currentTimeMillis(), 
				HttpStatus.FORBIDDEN.value(), "Acesso Negado",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}

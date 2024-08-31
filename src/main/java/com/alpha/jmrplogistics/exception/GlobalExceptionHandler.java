package com.alpha.jmrplogistics.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.jmrplogistics.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<Object>> handleAddressNotFoundException(AddressNotFoundException ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponseStructure<Object>> handleOrderNotFoundException(OrderNotFoundException ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnloadingNotFoundException.class)
	public ResponseEntity<ResponseStructure<Object>> handleUnloadingNotFoundException(UnloadingNotFoundException ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		ResponseStructure<Map<String, String>> responseStructure = new ResponseStructure<>();
		Map<String, String> errorMessages = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMessages.put(fieldName, errorMessage);
		});

		responseStructure.setStatuscode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("Validation failed");
		responseStructure.setData(errorMessages);

		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<Object>> handleGenericException(Exception ex) {
		ResponseStructure<Object> responseStructure = new ResponseStructure<>();
		responseStructure.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		responseStructure.setMessage("An unexpected error occurred: " + ex.getMessage());
		responseStructure.setData(null);
		return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

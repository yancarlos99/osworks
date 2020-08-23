package br.com.osworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.osworks.domain.exception.EntidadeNaoEncontradaExcpetion;
import br.com.osworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDatHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaExcpetion.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(NegocioException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDatHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var campos = new ArrayList<Campo>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError)error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Campo(nome, mensagem));
		}
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão invalidos."
				+ "Faça o preenchimento correto e tente novamente");
		problema.setDatHora(OffsetDateTime.now());
		problema.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}

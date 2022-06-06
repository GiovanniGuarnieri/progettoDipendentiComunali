package it.epicode.dipendenti.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GestioneErroriControllerAdvice {
	
	
	/**
	 * Gestione centralizzata degli errori di validazione
	 * @param ex
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity erroriInvalidazioni(MethodArgumentNotValidException ex) {
		log.info("erroriInvalidazioni");
		List<ObjectError> le = ex.getAllErrors();
		//Object result =	le.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		List<String>messaggiDiErrore = new ArrayList<>();
		for(ObjectError e : le) {
			messaggiDiErrore.add(e.getDefaultMessage());
		}
		return new ResponseEntity(messaggiDiErrore ,HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Gestione centralizzata degli errori di ricerca
	 * @param nsee
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity erroriDiRicerca(NotFoundException nsee) {
		
		return new ResponseEntity("si è verificato un errore " + nsee.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Gestione centralizzata degli errori di esistenza
	 * @param nsee
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ElementAlreadyPresentException.class)
	public ResponseEntity erroriDiEsistenza(ElementAlreadyPresentException nsee) {
		
		return new ResponseEntity("si è verificato un errore " + nsee.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	/**
	 * Gestione automatizzata degli errori di ricerca delle value
	 * @param nsee
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity erroriDiEsistenza(NoSuchElementException nsee) {
		
		return new ResponseEntity("si è verificato un errore " + nsee.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
}

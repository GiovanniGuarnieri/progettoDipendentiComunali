package it.epicode.dipendenti.errors;

/**
 * Creazione eccezione  per un elemento gia presente
 */
@SuppressWarnings("serial")
public class ElementAlreadyPresentException extends Exception {

	public ElementAlreadyPresentException(String message) {
		super(message);
		
	}
	

}

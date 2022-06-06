package it.epicode.dipendenti.errors;
/**
 * Creazione eccezione per un elemento non trovato
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {

	public NotFoundException(String message) {
		super(message);
		
	}

}

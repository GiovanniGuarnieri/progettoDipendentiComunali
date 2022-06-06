package it.epicode.dipendenti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertComuneRequestDTO {

	private String nome;
	private int numeroDipendenti;
	private String nomeRegione;
	
}

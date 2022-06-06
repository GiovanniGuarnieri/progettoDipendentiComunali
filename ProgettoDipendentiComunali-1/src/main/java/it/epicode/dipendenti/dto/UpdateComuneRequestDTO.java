package it.epicode.dipendenti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateComuneRequestDTO {
	
	
	private Long idComune;
	private String nome;
	private int numeroDipendenti;
	private String nomeRegione;
}

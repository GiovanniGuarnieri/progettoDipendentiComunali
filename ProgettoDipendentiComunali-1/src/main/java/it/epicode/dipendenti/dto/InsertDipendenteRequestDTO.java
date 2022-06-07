package it.epicode.dipendenti.dto;

import it.epicode.dipendenti.model.ETipoDipendente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertDipendenteRequestDTO {
	
	private String nome;
	private String cognome;
	private ETipoDipendente ruoloDipendente;
	private String codiceFiscale;
	private String numeroTelefonico;
	private Long idComune;

}

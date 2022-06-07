package it.epicode.dipendenti.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dipendente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	private String nome;
	private String cognome;
	@Enumerated(EnumType.STRING)
	private ETipoDipendente ruoloDipendente;
	private String codiceFiscale;
	private String numeroTelefonico;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "id_comuni")
	private Comune comune;
}

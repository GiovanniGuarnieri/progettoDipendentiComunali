package it.epicode.dipendenti.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Regione {

	@Id
	private String nome;
	private BigDecimal numeroAbitanti;
	
	@OneToMany(mappedBy = "regione", cascade = CascadeType.ALL)
	private List<Comune> comuni = new ArrayList<>();
	
	
	
}

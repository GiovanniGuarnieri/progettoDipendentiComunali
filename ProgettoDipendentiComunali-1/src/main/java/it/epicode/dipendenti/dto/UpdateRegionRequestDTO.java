package it.epicode.dipendenti.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRegionRequestDTO {

	private String nome;
	private BigDecimal numeroAbitanti;

}

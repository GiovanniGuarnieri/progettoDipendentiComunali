package it.epicode.dipendenti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Comune;

public interface ComuneRepository extends PagingAndSortingRepository <Comune , Long> {
	
	
	Page<Comune>findComuneByNumeroDipendentiBetween(int numeroMin, int numeroMax,Pageable page);
	Page<Comune>findByNumeroDipendenti(int numeroDipendenti,Pageable page);

}

package it.epicode.dipendenti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Regione;

public interface RegioneRepository extends PagingAndSortingRepository<Regione, String> {

	
	Page<Regione> findByRegioneContaining(Pageable page,String Regione);
}

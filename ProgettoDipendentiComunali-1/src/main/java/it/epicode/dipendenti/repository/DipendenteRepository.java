package it.epicode.dipendenti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Dipendente;

public interface DipendenteRepository extends PagingAndSortingRepository <Dipendente , Long> {

	@Query("select dp from Dipendente dp join dp.comune c join c.regione r where r.nome = ?1 ")
	Page<Dipendente> findByDipendenteNomeRegione(String regione,Pageable page);

	
}

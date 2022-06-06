package it.epicode.dipendenti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Regione;

public interface RegioneRepository extends PagingAndSortingRepository<Regione, String> {

}

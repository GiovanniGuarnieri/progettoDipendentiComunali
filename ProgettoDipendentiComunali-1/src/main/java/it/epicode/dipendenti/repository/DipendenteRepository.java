package it.epicode.dipendenti.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Dipendente;

public interface DipendenteRepository extends PagingAndSortingRepository <Dipendente , Long> {

}

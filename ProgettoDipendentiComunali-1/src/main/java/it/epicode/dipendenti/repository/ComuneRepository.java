package it.epicode.dipendenti.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.dipendenti.model.Comune;

public interface ComuneRepository extends PagingAndSortingRepository <Comune , Long> {

}

package it.epicode.dipendenti.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.dipendenti.dto.ChangeRegionRequestDTO;
import it.epicode.dipendenti.dto.InsertRegionRequestDTO;
import it.epicode.dipendenti.errors.ElementAlreadyPresentException;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.model.Regione;
import it.epicode.dipendenti.repository.RegioneRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RegioneService {
	@Autowired
	RegioneRepository rr;

	/**
	 * Inserting a Region into the database using the request mapping POST
	 * @param dto
	 * @throws ElementAlreadyPresentException
	 */

	public void insertRegion(InsertRegionRequestDTO dto ) throws ElementAlreadyPresentException {
		log.info("========================siamo nel service inserisci regione===============");
		log.info("Nome Regione "+ dto.getNome());
		if(!rr.existsById(dto.getNome())) {
			log.info("Regione "+ dto.getNome());
			log.info("Regione "+ dto.getNome());
			Regione r = new Regione();
			BeanUtils.copyProperties(dto, r);
			rr.save(r);}
		else {
			throw new  ElementAlreadyPresentException("Regione gia esistente");
		}
	}
	/**
	 * Eliminating a region trough the search of the name used as primary key in the entity using the request mapping DELETE
	 * @param dto
	 * @throws NotFoundException
	 */
	public void deleteRegion(String nome ) throws NotFoundException {
		log.info("========================siamo nel service elimina provincia===============");
		if (rr.existsById(nome)) {
			log.info("Nome Regione "+ nome);
			rr.deleteById(nome);
		}else {
			throw new NotFoundException("Regione non trovata");
		}
	}
	/**
	 * Searching all the regions in the database with the possibility of paging
	 * @param page
	 * @return
	 */
	public Page searchRegione(Pageable page) {
		log.info("========================siamo nel service cerca Regione===============");
		return rr.findAll(page);

	}
	/**
	 * Modifying a region present in the database through PUT request mapping
	 * @param dto
	 * @throws NotFoundException
	 */
	public void ChangeRegion(ChangeRegionRequestDTO dto) throws NotFoundException {
		log.info("========================siamo nel service modifica Regione===============");
		log.info(" " + dto.getNome());
		if(rr.existsById(dto.getNome())) {
			log.info("regione "+ dto.getNome());
			Regione r = rr.findById(dto.getNome()).get();
			BeanUtils.copyProperties(dto, r);
			rr.save(r);

		}else{
			throw new  NotFoundException("regione non trovata");
		}


	}
	/**
	 * Searching a Region through the name of the region with the possibility of paging using the GET request mapping
	 * @param page
	 * @param provincia
	 * @return
	 */
	public Page searchRegionByName(Pageable page,String nome) {
		log.info("========================siamo nel service cerca  Regione nome===============");
		log.info(" nome Regione " + nome);
		return rr.findByRegioneContaining(page,nome);
	}

	
	
}

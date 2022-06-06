package it.epicode.dipendenti.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.dipendenti.dto.InsertRegionRequestDto;
import it.epicode.dipendenti.errors.ElementAlreadyPresentException;
import it.epicode.dipendenti.repository.RegioneRepository;


@Service
public class RegioneService {
	@Autowired
	RegioneRepository rr;

	/**
	 * inserimento di una Regione nel db utilizzando il request mapping POST
	 * @param dto
	 * @throws ElementAlreadyPresentException
	 */

	public void inserisciProvincia(InsertRegionRequestDto dto ) throws ElementAlreadyPresentException {
		log.info("========================siamo nel service inserisci provincia===============");
		log.info("sigla provincia "+ dto.getSigla());
		if(!rr.existsById(dto.getSigla())) {
			log.info("provincia "+ dto.getProvincia());
			log.info("regione "+ dto.getRegione());
			Provincia p = new Provincia();
			BeanUtils.copyProperties(dto, p);
			pr.save(p);}
		else {
			throw new  ElementAlreadyPresentException("Provincia gia esistente");
		}
	}
	/**
	 * eliminazione di una provincia attraverso la ricerca della sigla utilizzata come chiave primaria nell'entity utilizzando il request mapping DELETE
	 * @param dto
	 * @throws NotFoundException
	 */
	public void eliminaProvincia(EliminaProvinciaRequestDTO dto) throws NotFoundException {
		log.info("========================siamo nel service elimina provincia===============");
		if(pr.existsById(dto.getSigla())) {
			log.info("sigla provincia "+ dto.getSigla());
			Provincia p = pr.findById(dto.getSigla()).get();
			pr.delete(p);
		}else {
			throw new NotFoundException("provincia non trovata");
		}
	}
	/**
	 * ricerca di tutte le province presenti nel db con possibilita di paginazione
	 * @param page
	 * @return
	 */
	public Page cercaProvincia(Pageable page) {
		log.info("========================siamo nel service cerca provincee===============");
		return pr.findAll(page);

	}
	/**
	 * modifica di una provincia presente nel db attraverso request mapping PUT
	 * @param dto
	 * @throws NotFoundException
	 */
	public void modificaProvincia(ModificaProvinciaRequestDTO dto) throws NotFoundException {
		log.info("========================siamo nel service modifica provincia===============");
		log.info(" " + dto.getSigla());
		if(pr.existsById(dto.getSigla())) {
			log.info("provincia "+ dto.getProvincia());
			log.info("regione "+ dto.getRegione());
			Provincia p = pr.findById(dto.getSigla()).get();
			BeanUtils.copyProperties(dto, p);
			pr.save(p);

		}else{
			throw new  NotFoundException("provincia non trovata");
		}


	}
	/**
	 * ricerca della provincia attraverso il nome della provincia con possibilita di paginazione utilizzando il request mapping GET
	 * @param page
	 * @param provincia
	 * @return
	 */
	public Page cercaProvinciaNome(Pageable page,String provincia) {
		log.info("========================siamo nel service cerca  provincia nome===============");
		log.info(" nome provincia " + provincia);
		return pr.findByProvinciaContaining(page,provincia);
	}

	
	
}

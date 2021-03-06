package it.epicode.dipendenti.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.epicode.dipendenti.dto.InsertComuneRequestDTO;
import it.epicode.dipendenti.dto.UpdateComuneRequestDTO;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.model.Comune;
import it.epicode.dipendenti.model.Regione;
import it.epicode.dipendenti.repository.ComuneRepository;
import it.epicode.dipendenti.repository.RegioneRepository;

@Service
public class ComuneService {


	@Autowired
	ComuneRepository comuneRepo;

	@Autowired
	RegioneRepository regioneRepo;




	public void insertComune(InsertComuneRequestDTO dto) throws NotFoundException {
		Comune comune = new Comune();
		BeanUtils.copyProperties(dto, comune);
		if(regioneRepo.existsById(dto.getNomeRegione())) {
			Regione regione = regioneRepo.findById(dto.getNomeRegione()).get();
			comune.setRegione(regione);
			regione.getComuni().add(comune);
			comuneRepo.save(comune);
		}
		else {
			throw new NotFoundException("regione non trovata");
		}

	}

	
	public void updateComune(UpdateComuneRequestDTO dto) throws NotFoundException {
		if(comuneRepo.existsById(dto.getIdComune())) {
			Comune comune = comuneRepo.findById(dto.getIdComune()).get();
			BeanUtils.copyProperties(dto, comune);
			if(regioneRepo.existsById(dto.getNomeRegione())) {
				Regione regione = regioneRepo.findById(dto.getNomeRegione()).get();
				comune.setRegione(regione);
				regione.getComuni().add(comune);
				comuneRepo.save(comune);

			}
			else {
				throw new NotFoundException("Regione non trovata");
			}

		}
		else {
			throw new NotFoundException("Comune non trovato");
		}

	}
	
	public void deleteComune(Long idComune) throws NotFoundException {
		if(comuneRepo.existsById(idComune)) {
			Comune comune = comuneRepo.findById(idComune).get();
			comuneRepo.delete(comune);
		}
		else {
			throw new NotFoundException("comune non trovato");
		}
	}
	
	public Comune findComuneById(Long idComune) throws NotFoundException {
		if(comuneRepo.existsById(idComune)) {
			Comune comune = comuneRepo.findById(idComune).get();
			return comune;
		}
		else {
			throw new NotFoundException("comune non trovato");
		}
		
	}
	
	public Page findByNumeroDipendentiBetween(int numeroMin, int numeroMax, Pageable page) {
		return comuneRepo.findComuneByNumeroDipendentiBetween(numeroMin, numeroMax, page);
		
	}
	
	public Page findByNumeroDipendenti(int numeroDipendenti, Pageable page) {
		return comuneRepo.findByNumeroDipendenti(numeroDipendenti, page);
	}
	
	
	public Page findAllComuni(Pageable page) {
		return comuneRepo.findAll(page);
	}



}

package it.epicode.dipendenti.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.epicode.dipendenti.dto.InsertDipendenteRequestDTO;
import it.epicode.dipendenti.dto.UpdateDipendenteRequestDTO;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.model.Comune;
import it.epicode.dipendenti.model.Dipendente;
import it.epicode.dipendenti.model.Regione;
import it.epicode.dipendenti.repository.ComuneRepository;
import it.epicode.dipendenti.repository.DipendenteRepository;
import it.epicode.dipendenti.repository.RegioneRepository;

@Service
public class DipendenteService {
	@Autowired
	DipendenteRepository dipendenteRepo;

	@Autowired
	ComuneRepository comuneRepo;



	public void insertDipendente(InsertDipendenteRequestDTO dto) throws NotFoundException {
		Dipendente dipendente = new Dipendente();
		BeanUtils.copyProperties(dto, dipendente);
		if(comuneRepo.existsById(dto.getIdComune())) {
			Comune comune = comuneRepo.findById(dto.getIdComune()).get();
			dipendente.setComune(comune);
			comune.getDipendenti().add(dipendente);
			dipendenteRepo.save(dipendente);
		}
		else {
			throw new NotFoundException("Comune non trovato");
		}
	}


	public void updateDipendente(UpdateDipendenteRequestDTO dto) throws NotFoundException {
		if(dipendenteRepo.existsById(dto.getIdDipendente())) {
			Dipendente dipendente = dipendenteRepo.findById(dto.getIdDipendente()).get();
			BeanUtils.copyProperties(dto, dipendente);
			if(comuneRepo.existsById(dto.getIdComune())) {
				Comune comune = comuneRepo.findById(dto.getIdComune()).get();
				dipendente.setComune(comune);
				comune.getDipendenti().add(dipendente);
				dipendenteRepo.save(dipendente);
			}
			else {
				throw new NotFoundException("comune non trovato");
			}
		}
		else {
			throw new NotFoundException("dipendente non trovato");
		}
	}



	public void deleteDipendente(Long id) throws NotFoundException {
		if(dipendenteRepo.existsById(id)) {
			dipendenteRepo.deleteById(id);
		}
		else {
			throw new NotFoundException("dipendente non trovato");
		}
	}
	

	
	public Page findByDipendenteRegione(String regione, Pageable page) {
		return dipendenteRepo.findByDipendenteNomeRegione(regione, page);
	}


}

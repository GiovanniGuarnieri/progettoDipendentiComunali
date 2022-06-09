package it.epicode.dipendenti.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.dipendenti.dto.UpdateRegionRequestDTO;
import it.epicode.dipendenti.dto.InsertDipendenteRequestDTO;
import it.epicode.dipendenti.dto.InsertRegionRequestDTO;
import it.epicode.dipendenti.dto.UpdateDipendenteRequestDTO;
import it.epicode.dipendenti.errors.ElementAlreadyPresentException;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.model.ETipoDipendente;
import it.epicode.dipendenti.services.DipendenteService;


@RestController
@RequestMapping("/regione")
public class DipendenteController {

	@Autowired
	DipendenteService dipendenteService;
	
	

	@SuppressWarnings("rawtypes")
	@Operation (summary = "Inserisce un dipendente nel db")
	@ApiResponse(responseCode = "200" , description = "Dipendente inserita con successo nel db !")
	@ApiResponse(responseCode ="404" , description = "Dipendente non trovato")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping(path = "/inseriscidipendente" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity insertDipendente(@Valid @RequestBody InsertDipendenteRequestDTO dto) throws NotFoundException, ElementAlreadyPresentException  {
		dipendenteService.insertDipendente(dto);
			return ResponseEntity.ok("DIPENDENTE INSERITO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Modifica un dipendente presente nel db ")
	@ApiResponse(responseCode = "200" , description = "Modifica avvenuta")
	@ApiResponse(responseCode ="404" , description = "Dipendente non trovato")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/modificadipendente")
	public ResponseEntity updateRegione(@Valid @RequestBody UpdateDipendenteRequestDTO dto) throws NotFoundException {
		dipendenteService.updateDipendente(dto);
		return ResponseEntity.ok("DIPENDENTE MODIFICATO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Elimina una regione presente nel db")
	@ApiResponse(responseCode = "200" , description = "Eliminazione avvenuta")
	@ApiResponse(responseCode ="404" , description = "Regione non Trovata ")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@DeleteMapping("/eliminadipendente/{id}")
	public ResponseEntity deleteComune( @PathVariable("id")Long id) throws NotFoundException {
		dipendenteService.deleteDipendente(id);
		return ResponseEntity.ok("DIPENDENTE ELIMINATO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna tutti i dipendenti presenti nel db")
	@ApiResponse(responseCode = "200" , description = "lista dipendenti")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tuttiidipendenti")
	public ResponseEntity getAllDipendenti(Pageable page) {
		return ResponseEntity.ok(dipendenteService.findAll(page));
	}
	
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna tutti i dipendenti associti al ruolo inserito a parametro ")
	@ApiResponse(responseCode = "200" , description = "lista dipendenti")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tuttiidipendentiruolo")
	public ResponseEntity getDipendenteByRuolo(ETipoDipendente ruoloDipendente,Pageable page) {
		return ResponseEntity.ok(dipendenteService.findByRuoloDipendente(ruoloDipendente,page));
	}
	
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna tutti i dipendenti associti alla regione inserita a parametro ")
	@ApiResponse(responseCode = "200" , description = "lista dipendenti")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tuttiidipendentiregioneassociata")
	public ResponseEntity getDipendenteByRegione(String regione ,Pageable page) {
		return ResponseEntity.ok(dipendenteService.findByDipendenteRegione(regione, page));
	}
	
	
}

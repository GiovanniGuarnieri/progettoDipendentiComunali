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
import it.epicode.dipendenti.dto.InsertComuneRequestDTO;
import it.epicode.dipendenti.dto.UpdateComuneRequestDTO;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.services.ComuneService;


@RestController
@RequestMapping("/comune")
public class ComuneController {

	
	@Autowired
	ComuneService comuneService;
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Inserisce un comune nel db", description = "inserisce un comune nel db ")
	@ApiResponse(responseCode = "200" , description = "comune inserito con successo nel db !")
	@ApiResponse(responseCode ="404" , description = "Comune non trovato")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping(path = "/inseriscicomune" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity insertComune(@Valid @RequestBody InsertComuneRequestDTO dto) throws NotFoundException  {
		comuneService.insertComune(dto);
			return ResponseEntity.ok("COMUNE INSERITO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Modifica un comune presente nel db ", description = "Modifica un comune presente nel db ")
	@ApiResponse(responseCode = "200" , description = "Modifica avvenuta")
	@ApiResponse(responseCode ="404" , description = "Comune non trovato")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/modificacomune")
	public ResponseEntity updateComune(@Valid @RequestBody UpdateComuneRequestDTO dto) throws NotFoundException {
		comuneService.updateComune(dto);
		return ResponseEntity.ok("COMUNE MODIFICATO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Elimina un  comune presente nel db", description = "Elimina un comune presente nel db ")
	@ApiResponse(responseCode = "200" , description = "Eliminazione avvenuta")
	@ApiResponse(responseCode ="404" , description = "Comune non Trovato ")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@DeleteMapping("/eliminacomune/{idComune}")
	public ResponseEntity deleteComune( @PathVariable("idComune")Long idComune, Pageable page) throws NotFoundException {
		comuneService.deleteComune(idComune);
		return ResponseEntity.ok("COMUNE ELIMINATO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna tutti i comuni presenti nel db", description = "Ritorna la lista di tutti i comuni presenti nel db ")
	@ApiResponse(responseCode = "200" , description = "lista Comuni")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tutticomuni")
	public ResponseEntity getAllComuni(Pageable page) {
		return ResponseEntity.ok(comuneService.findAllComuni(page));
	}
	
}

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
import it.epicode.dipendenti.dto.InsertComuneRequestDTO;
import it.epicode.dipendenti.dto.InsertRegionRequestDTO;
import it.epicode.dipendenti.dto.UpdateComuneRequestDTO;
import it.epicode.dipendenti.errors.ElementAlreadyPresentException;
import it.epicode.dipendenti.errors.NotFoundException;
import it.epicode.dipendenti.services.RegioneService;

@RestController
@RequestMapping("/regione")
public class RegioneController {

	@Autowired
	RegioneService regioneService;
	
	
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Inserisce una regione nel db")
	@ApiResponse(responseCode = "200" , description = "regione inserita con successo nel db !")
	@ApiResponse(responseCode ="404" , description = "Regione non trovata")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PostMapping(path = "/inserisciregione" ,produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity insertRegione(@Valid @RequestBody InsertRegionRequestDTO dto) throws NotFoundException, ElementAlreadyPresentException  {
		regioneService.insertRegion(dto);
			return ResponseEntity.ok("REGIONE INSERITA");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Modifica una regione presente nel db ")
	@ApiResponse(responseCode = "200" , description = "Modifica avvenuta")
	@ApiResponse(responseCode ="404" , description = "Regione non trovata")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/modificaregione")
	public ResponseEntity updateRegione(@Valid @RequestBody UpdateRegionRequestDTO dto) throws NotFoundException {
		regioneService.ChangeRegion(dto);
		return ResponseEntity.ok("REGIONE MODIFICATO");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Elimina una regione presente nel db")
	@ApiResponse(responseCode = "200" , description = "Eliminazione avvenuta")
	@ApiResponse(responseCode ="404" , description = "Regione non Trovata ")
	@PreAuthorize("hasRole('ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	@DeleteMapping("/eliminaregione/{nome}")
	public ResponseEntity deleteComune( @PathVariable("nome")String nome) throws NotFoundException {
		regioneService.deleteRegion(nome);
		return ResponseEntity.ok("REGIONE ELIMINATA");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna tutti le regioni presenti nel db")
	@ApiResponse(responseCode = "200" , description = "lista regioni")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tutteleregioni")
	public ResponseEntity getAllComuni(Pageable page) {
		return ResponseEntity.ok(regioneService.searchAllRegioni(page));
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@Operation (summary = "Ritorna la regione associata al nome dato a parametro nel db")
	@ApiResponse(responseCode = "200" , description = "lista regioni")
	@ApiResponse(responseCode ="500" , description = "Error Internal Server")
	@PreAuthorize("isAuthenticated()")
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping ("/tutteleregioni/{nome}")
	public ResponseEntity getRegioneByName(@PathVariable("nome")String nome, Pageable page) {
		return ResponseEntity.ok(regioneService.searchRegionByName(nome,page));
	}
	
}

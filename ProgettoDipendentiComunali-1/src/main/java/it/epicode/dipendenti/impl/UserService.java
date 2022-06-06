package it.epicode.dipendenti.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.epicode.azienda.dto.CercaTuttiGliUserResponseDTO;
import it.epicode.azienda.dto.DisabilitaUserRequestDTO;
import it.epicode.azienda.dto.InserisciUserRequestDTO;
import it.epicode.azienda.dto.InserisciUserRequestDTOSecond;
import it.epicode.azienda.dto.ModificaUserRequestDTO;
import it.epicode.dipendenti.errors.ElementAlreadyPresentException;
import it.epicode.dipendenti.errors.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired UserRepository ur;
	@Autowired RoleRepository rr;

	public List<UserResponse> getAllUsersBasicInformations() {
		return ur.findAll()
				.stream()
				.map( user ->  UserResponse
						.builder()
						.userName(  user.getUsername()  )
						.role( user.getRoles().stream().findFirst().get().getRoleName().name() )
						.build()   
						).collect(Collectors.toList());
	}

	public UserResponse getUserBasicInformations(String userName) {
		User user = ur.findByUsername(userName).get();



		return UserResponse
				.builder()
				.userName(userName)
				.role( user.getRoles().stream().findFirst().get().getRoleName().name()).build();

	}




	/**
	 * inserimento di uno user con il ruolo user di default
	 * @param user
	 * @throws ElementAlreadyPresentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void inserisciRuoloUser(InserisciUserRequestDTO user) throws ElementAlreadyPresentException {
		log.info("siamo nell'inserisci user con ruolo user");
		User user2 = new User();
		if(!ur.existsByUsername(user.getUsername())) {
			BeanUtils.copyProperties(user, user2);
			user2.setPassword(BCrypt.hashpw(user2.getPassword(),BCrypt.gensalt()));
			Set hash = new HashSet<Role>();
			Role user1= Role.builder().roleName(ERole.ROLE_USER).build();
			hash.add(user1);
			user2.setRoles(hash);
			user2.setAccountActive(true);
			ur.save(user2);

		}
		else {
			throw new ElementAlreadyPresentException("user gia esistente nel db");
		}
	}


	/**
	 * inserimento di uno user con possibilita di scelta sul ruolo
	 * @param user
	 * @throws ElementAlreadyPresentException
	 * @throws NotFoundException
	 */
	public void inserisciUtente(InserisciUserRequestDTOSecond user) throws ElementAlreadyPresentException, NotFoundException{
		log.info("siamo nell'inserisci nell'inseri user");
		User user2 = new User();
		if(!ur.existsByUsername(user.getUsername())) {
			BeanUtils.copyProperties(user, user2);
			user2.setPassword(BCrypt.hashpw(user2.getPassword(),BCrypt.gensalt()));
			user2.setAccountActive(true);
			String elencoRuoli = user.getRoles();
			if(elencoRuoli.isBlank()) {
				elencoRuoli= "ROLE_USER";
			}
			String[]listaRuoli=elencoRuoli.split(",");
			Set <Role> ruoli = new HashSet<Role>();
			log.info(elencoRuoli);
			for(int i=0; i<listaRuoli.length; i++) {
				Role r = rr.findByRoleNameString(listaRuoli[i]);
				if(r != null) {
					log.info(r.getRoleName().toString());
					ruoli.add(r);
				}
				else {
					throw new NotFoundException("ruolo inesistente");
				}
			}
			user2.setRoles(ruoli);
			ur.save(user2);
		}
		else {
			throw new ElementAlreadyPresentException("user gia esistente nel db");
		}
	}

	/**
	 * inserimento di un admin
	 * @param user
	 * @throws ElementAlreadyPresentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void inserisciAdmin(InserisciUserRequestDTO user) throws ElementAlreadyPresentException {
		log.info("siamo nell'inserisci admin");
		User user2 = new User();
		if(!ur.existsByUsername(user.getUsername())) {
			BeanUtils.copyProperties(user, user2);
			user2.setPassword(BCrypt.hashpw(user2.getPassword(),BCrypt.gensalt()));
			Set hash = new HashSet<Role>();
			Role user1= Role.builder().roleName(ERole.ROLE_ADMIN).build();
			hash.add(user1);
			user2.setRoles(hash);
			user2.setAccountActive(true);
			ur.save(user2);
		}
		else {
			throw new ElementAlreadyPresentException("user gia esistente nel db");
		}
	}


	/**
	 * modifica di uno user
	 * @param user
	 * @throws NotFoundException
	 */
	public void modificaUser(ModificaUserRequestDTO user) throws NotFoundException  {
		log.info("siamo nel modifica user");
		if(ur.existsByUsername(user.getUsername())) {
			User user2 = ur.findByUsername(user.getUsername()).get();
			BeanUtils.copyProperties(user, user2);
			user2.setPassword(BCrypt.hashpw(user2.getPassword(),BCrypt.gensalt()));
			String elencoRuoli = user.getRoles();
			if(elencoRuoli.isBlank()) {
				elencoRuoli= "ROLE_USER";
			}
			String[]listaRuoli=elencoRuoli.split(",");
			Set <Role> ruoli = new HashSet<Role>();
			log.info(elencoRuoli);
			for(int i=0; i<listaRuoli.length; i++) {
				Role r = rr.findByRoleNameString(listaRuoli[i]);
				if(r != null) {
					log.info(r.getRoleName().toString());
					ruoli.add(r);
				}
				else {
					throw new NotFoundException("ruolo inesistente");
				}
			}
			user2.setRoles(ruoli);
			ur.save(user2);
		}
		else {
			throw new NotFoundException("user non esistente nel db");
		}
	}

	/**
	 * eliminazione di uno user
	 * @param id
	 * @throws NotFoundException
	 */
	public void eliminaUser(Long id) throws NotFoundException {
		log.info("siamo nell'elimina user");
		if(ur.existsById(id)) {
			ur.deleteById(id);

		}
		else{ throw new NotFoundException("User inesistente");}

	}

	/**
	 * ricerca di tutti gli user presenti nel db
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CercaTuttiGliUserResponseDTO trovaTuttiGliUser() {
		log.info("siamo nel trova modifica user");
		CercaTuttiGliUserResponseDTO dto = new CercaTuttiGliUserResponseDTO ();
		List<User> lu = (List)ur.findAll(); 
		if(lu.size()> 0) {
			dto.setUserTrovati(lu.size());
			dto.setElencoUser(lu);
			return dto;
		}
		return dto;

	}

	/**
	 * disabilitazione di un account
	 * @param dto
	 * @throws NotFoundException
	 */
	public void disabilitaUser(DisabilitaUserRequestDTO dto) throws NotFoundException {
		log.info("siamo nel disabilità user");
		if(ur.existsById(dto.getId())){
			User u	=ur.findById(dto.getId()).get();
			u.setAccountActive(false);
			ur.save(u);

		}
		else {

			throw new NotFoundException("User inesistente");
		}


	}


	/**
	 * attivazione di un account
	 * @param dto
	 * @throws NotFoundException
	 */
	public void attivaUser(DisabilitaUserRequestDTO dto) throws NotFoundException {
		log.info("siamo disabilita user");
		if(ur.existsById(dto.getId())){
			User u	=ur.findById(dto.getId()).get();
			u.setAccountActive(true);
			ur.save(u);

		}
		else {

			throw new NotFoundException("User inesistente");
		}
	}




}

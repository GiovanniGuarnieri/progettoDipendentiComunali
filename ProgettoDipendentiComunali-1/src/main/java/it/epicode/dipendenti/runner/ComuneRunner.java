package it.epicode.dipendenti.runner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.dipendenti.impl.ERole;
import it.epicode.dipendenti.impl.Role;
import it.epicode.dipendenti.impl.User;
import it.epicode.dipendenti.impl.UserRepository;
import it.epicode.dipendenti.model.Comune;
import it.epicode.dipendenti.model.Dipendente;
import it.epicode.dipendenti.model.ETipoDipendente;
import it.epicode.dipendenti.model.Regione;
import it.epicode.dipendenti.repository.ComuneRepository;
import it.epicode.dipendenti.repository.DipendenteRepository;
import it.epicode.dipendenti.repository.RegioneRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ComuneRunner implements CommandLineRunner {

	@Autowired
	ComuneRepository comuneRepository;
	@Autowired
	DipendenteRepository dipendenteRepository;
	@Autowired
	RegioneRepository regioneRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserRepository userRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		
		log.info("============================================ RUNNER PARTITO");
		
		
		Regione Marche = Regione.builder().nome("Marche").numeroAbitanti(BigDecimal.valueOf(20035000)).build();
		Comune SanCostanzo = Comune.builder().nome("SanCostanzo").numeroDipendenti(35).regione(Marche).build();
		Dipendente Francesco = Dipendente.builder().nome("Francesco").cognome("Donati").numeroTelefonico("48374956").codiceFiscale("GMPSBA97A13D487T")
							.ruoloDipendente(ETipoDipendente.SETTORE_DELLE_RISORSE_UMANE).comune(SanCostanzo).build();
		
		dipendenteRepository.save(Francesco);
		
		
		log.info("============================================ CREAZIONE DELLA REGIONE MARCHE, COMUNE SANCOSTANZO E DIPENDENTE FRANCESCO");
		
		
		Regione Campania = Regione.builder().nome("Campania").numeroAbitanti(BigDecimal.valueOf(200556000)).build();
		Comune Salerno = Comune.builder().nome("Salerno").numeroDipendenti(50).regione(Campania).build();
		Dipendente Giampaolo = Dipendente.builder().nome("Giampaolo").cognome("Saba").numeroTelefonico("3497237742").codiceFiscale("GMPSBA97A13D487T")
							.ruoloDipendente(ETipoDipendente.DIRIGENZIALE).comune(Salerno).build();
		
		dipendenteRepository.save(Giampaolo);
		
		log.info("============================================ CREAZIONE DELLA REGIONE CAMPANIA, COMUNE SALERNO E DIPENDENTE GIAMPAOLO");
		
		
		Comune Napoli = Comune.builder().nome("Salerno").numeroDipendenti(50).regione(Campania).build();
		Dipendente Giovanni = Dipendente.builder().nome("Giovanni").cognome("Guarnieri").numeroTelefonico("3497455642").codiceFiscale("GMPSBA505A13D487T")
							.ruoloDipendente(ETipoDipendente.AMMINISTRATIVO).comune(Salerno).build();
		
		dipendenteRepository.save(Giovanni);
		
		log.info("============================================ CREAZIONE DELLA REGIONE CAMPANIA, COMUNE NAPOLI E DIPENDENTE GIOVANNI");
		
		Set hash = new HashSet<Role>();
		Role admin= Role.builder().roleName(ERole.ROLE_ADMIN).build();
		hash.add(admin);
		Set hash1 = new HashSet<Role>();
		Role user= Role.builder().roleName(ERole.ROLE_USER).build();
		hash1.add(user);
		User u1 = User.builder().username("mauro").nome("Roberto").cognome("Loris")
				.password( BCrypt.hashpw("mauro", BCrypt.gensalt())).email("ciaociao@hotmail.it").roles(hash1).accountActive(true).build();
		User u = User.builder().username("giovanni").nome("Lorella").cognome("Lorenzi")
				.password(encoder.encode("maurito")).roles(hash).email("ciaocaio@hotmail.it").accountActive(true).build();

		userRepository.save(u);
		userRepository.save(u1);	
		
	}

}

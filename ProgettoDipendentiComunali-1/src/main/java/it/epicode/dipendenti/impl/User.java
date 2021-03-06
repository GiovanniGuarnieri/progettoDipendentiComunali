package it.epicode.dipendenti.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 120)
	private String nome;
	
	@NotBlank
	@Size(max = 120)
	private String cognome;
	
	@NotBlank
	@Size(max = 20)
	private String username;


	@NotBlank
	@Size(max = 120)
	private String password;
	
	@NotBlank
	@Size(max = 120)
	private String email;
	
	
	
	
	private boolean accountActive ;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id",referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, String email,String nome,String cognome) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}

}

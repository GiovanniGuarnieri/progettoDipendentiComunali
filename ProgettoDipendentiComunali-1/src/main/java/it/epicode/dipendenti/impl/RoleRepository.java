package it.epicode.dipendenti.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
public Role findByRoleName( ERole nomeRuolo);

@Query(value = "select * from roles  where role_name=?1" ,nativeQuery = true)
public Role findByRoleNameString(String nome);

}

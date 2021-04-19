package ma.youcode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

	Role findByIdRole(Long idRole);
}

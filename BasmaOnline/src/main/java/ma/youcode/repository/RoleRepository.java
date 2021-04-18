package ma.youcode.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.entities.Role;

@Repository
@EnableJpaRepositories

public interface RoleRepository extends CrudRepository<Role, Long> {
	//Role findByRoleId(Long id);

}

package ma.youcode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	User findByUserId(String userId);
}

package ma.youcode.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByEmail(String email);

	User findByUserId(String userId);
}

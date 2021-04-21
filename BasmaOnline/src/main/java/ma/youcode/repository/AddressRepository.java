package ma.youcode.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ma.youcode.entities.Address;
import ma.youcode.entities.User;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

	List<Address> findByUser(User currentUser);

	Address findByAddressId(String addressId);

}

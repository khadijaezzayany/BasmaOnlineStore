package ma.youcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.youcode.entities.Role;
import ma.youcode.repository.RoleRepository;

@SpringBootTest
class BasmaOnlineApplicationTests {

	@Autowired
	RoleRepository roleRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRole() {
		Role role = roleRepository.findByIdRole(1L);
		System.out.println(role.getName());
		System.out.println(role.getidRole());
	}

}

package ma.youcode.servicesImp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.youcode.entities.User;
import ma.youcode.repository.UserRepository;
import ma.youcode.services.UserService;
import ma.youcode.shared.UserDto;
import ma.youcode.shared.Utils;

@Service
public class UserServicesImp implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Alrady Exist !!");

		User userEntities = new User();

		BeanUtils.copyProperties(user, userEntities);
		// Crypting password
		userEntities.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntities.setUserId(utils.genereteUserId(32));
		// Pirsist in DB
		User newUser = userRepository.save(userEntities);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);

		return userDto;
	}

}

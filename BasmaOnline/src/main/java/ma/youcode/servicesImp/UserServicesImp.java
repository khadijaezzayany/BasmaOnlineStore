package ma.youcode.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.youcode.entities.Role;
import ma.youcode.entities.User;
import ma.youcode.repository.RoleRepository;
import ma.youcode.repository.UserRepository;
import ma.youcode.services.UserService;
import ma.youcode.shared.UserDto;
import ma.youcode.shared.Utils;

@Service
public class UserServicesImp implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		User checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exist !!");

		User userEntities = new User();

		// recuper role
		Role role = roleRepository.findByIdRole(user.getRoleId());
		BeanUtils.copyProperties(user, userEntities);
		// Crypting password
		userEntities.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntities.setUserId(utils.genereteUserId(32));
		// Add role to user
		userEntities.setRole(role);
		// Pirsist in DB
		User newUser = userRepository.save(userEntities);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(newUser, userDto);

		return userDto;
	}

	@Override
	// Récupérer User vai son adresse Email
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntities = userRepository.findByEmail(email);
		if (userEntities == null)
			throw new UsernameNotFoundException(email);

		return new org.springframework.security.core.userdetails.User(userEntities.getEmail(),
				userEntities.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {

		User userEntities = userRepository.findByEmail(email);
		if (userEntities == null)
			throw new UsernameNotFoundException(email);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntities, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntities, userDto);

		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		userEntities.setFirstName(userDto.getFirstName());
		userEntities.setLastName(userDto.getLastName());
		User userEntity = userRepository.save(userEntities);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userEntity, user);

		return user;

	}

	@Override
	public void deleteUser(String userId) {
		User userEntities = userRepository.findByUserId(userId);
		if (userEntities == null)
			throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntities);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {

		List<UserDto> userDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<User> userPage = userRepository.findAll(pageableRequest);

		List<User> users = userPage.getContent();
		for (User userEntity : users) {
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			userDto.add(user);

		}

		return userDto;
	}

}

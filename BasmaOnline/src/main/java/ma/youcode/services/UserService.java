package ma.youcode.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.youcode.shared.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDto);

	UserDto getUser(String email);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String id, UserDto userDto);

	void deleteUser (String userId);

}

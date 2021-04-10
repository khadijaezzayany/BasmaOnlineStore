package ma.youcode.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.requests.UserRequest;
import ma.youcode.responses.UserResponse;
import ma.youcode.services.UserService;
import ma.youcode.shared.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "get user was called";

	}

	@PostMapping
	public UserResponse createUser(@RequestBody UserRequest userRequest) {

		// la coche représeentation
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// passer information ver le service
		UserDto createUser = userService.createUser(userDto);

		// create reponse
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);
		return userResponse;
	}

	@PutMapping
	public String updateUser() {
		return "Update user was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
}

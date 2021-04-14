package ma.youcode.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

		// la coche représeentation
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// passer information ver le service
		UserDto createUser = userService.createUser(userDto);

		// create reponse
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(createUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		// la coche représeentation
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);

		// passer information ver le service
		UserDto updateUser = userService.updateUser(id, userDto);

		// create reponse
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}

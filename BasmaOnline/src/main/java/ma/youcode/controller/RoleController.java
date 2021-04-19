package ma.youcode.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.youcode.entities.Role;
import ma.youcode.requests.RoleRequest;
import ma.youcode.responses.RoleResponses;
import ma.youcode.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;

	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<RoleResponses> createRole(@RequestBody RoleRequest roleRequest) {
		Role role = new Role();
		BeanUtils.copyProperties(roleRequest, role);

		Role createRoe = roleService.createRole(role);

		RoleResponses roleResponses = new RoleResponses();
		BeanUtils.copyProperties(createRoe, roleResponses);
		return new ResponseEntity<RoleResponses>(roleResponses, HttpStatus.CREATED);

	}

	@DeleteMapping(path = "/{id")
	public void deleteRole(@PathVariable String idRole) {
		Long id = Long.parseLong(idRole);

		roleService.deleteRole(id);

	}
}

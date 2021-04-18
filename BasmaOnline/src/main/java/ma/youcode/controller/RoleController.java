//package ma.youcode.controller;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import ma.youcode.entities.Role;
//import ma.youcode.responses.RoleResponses;
//import ma.youcode.services.RoleService;
//
//@RestController
//@RequestMapping("/role")
////@EnableAutoConfiguration
//public class RoleController {
//	@Autowired
//	RoleService roleService;
//
//	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
//			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
////	
////	public ResponseEntity<RoleResponses> createRole(@RequestBody RoleRequest roleRequest) {
////
////		Role role = new Role();
////		Role createRole = roleService.createRole(role);
////		// create reponse
////		RoleResponses RoleResponses = new RoleResponses();
////		BeanUtils.copyProperties(createRole, RoleResponses);
////		return new ResponseEntity<RoleResponses>(RoleResponses, HttpStatus.CREATED);
////	}
//
//	@GetMapping(path="/{id}")
//	public ResponseEntity<RoleResponses> getRole(@PathVariable long id){
//		Role role = roleService.getRoleByRoleId(id);
//		RoleResponses  roleResponses = new RoleResponses();
//		BeanUtils.copyProperties(role, roleResponses);
//		return new ResponseEntity<RoleResponses>(roleResponses,HttpStatus.OK);
//			
//	}
//
//}

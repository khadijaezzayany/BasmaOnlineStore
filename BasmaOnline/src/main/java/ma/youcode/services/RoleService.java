package ma.youcode.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.youcode.entities.Role;

public interface RoleService extends UserDetailsService {
	Role createRole(Role role);

	Role getRole(Role role);

	void deleteRole(long idRole);

	Role updateRole(Role role);
	
	List<Role> getRoles (Role role);

	//Role getRoleByRoleId(Long id);

}

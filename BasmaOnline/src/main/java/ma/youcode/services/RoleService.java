package ma.youcode.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.youcode.entities.Role;

public interface RoleService extends UserDetailsService {
	Role createRole(Role role);

	Role getRole(Role role);

	void deleteRole(Role role);

	Role updateRole(Role role);

	//Role getRoleByRoleId(Long id);

}

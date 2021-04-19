package ma.youcode.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.youcode.entities.Role;
import ma.youcode.repository.RoleRepository;
import ma.youcode.services.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role createRole(Role roles) {
		Role checkRole = roleRepository.findByName(roles.getName());
		if(checkRole != null) throw new RuntimeException( "Role Already Exist!!");
		
//		Role role = new Role();
		roleRepository.save(roles);

		return roles;
	}

	@Override
	public Role getRole(Role role) {
		
		return null;
	}

	@Override
	public void deleteRole(long idRole) {

		Role entityRole = roleRepository.findByIdRole(idRole);
		roleRepository.delete(entityRole);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
//
//	@Override
//	public Role getRoleByRoleId(Long id) {
//		Role role = roleRepository.findByRoleId(id);
//
//		return role;
//	}

	@Override
	public List<Role> getRoles(Role role) {
		
		return null;
	}

}

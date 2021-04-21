package ma.youcode.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ma.youcode.entities.Address;

public class UserRequest {
	@NotNull(message = "Ce champ ne doit etre null!")
	@Size(min=3 ,message = "Ce champ doit avoir au mois 3 Caracteres ")
	private String firstName;
	
	@NotNull(message = "Ce champ ne doit etre null!")
	@Size(min=3,message = "Ce champ doit avoir au mois 3 Caracteres ")
	private String lastName;
	
	@NotNull(message = "Ce champ ne doit etre null!")
	@Email(message = "Ce champ doit respecter le f;ormay email !")
	private String email;
	
	private Boolean admin;
	
	@NotNull(message = "Ce champ ne doit etre null!")
	@Size(min=8, message = "Ce champ ne doit au moins 8 caracteres !")
	@Size(max=12, message = "Ce champ ne doit au max  12 caracteres !")

	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\r\n"
			+ "",message = "Ce mot de pass doit avoir des lettres en Maj ,Min et nemero")
	private String password;
	
	
	private List<AddressRequest> addresses;

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}

package ma.youcode.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role implements Serializable {

	private static final long serialVersionUID = 5604619746647853842L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRole;
	@Column(nullable = false, length = 30)
	private String name;

	public long getidRole() {
		return idRole;
	}

	public void setidRole(long idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "role")
	private List<User> user;

}

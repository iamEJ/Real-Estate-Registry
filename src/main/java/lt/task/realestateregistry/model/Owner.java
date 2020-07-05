package lt.task.realestateregistry.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "owners")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 20)
	private String firstName;
	@NotNull
	@Size(min = 2, max = 30)
	private String lastName;
	@NotNull
	@Column(unique = true)
	private String personalCode;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
	private List<Building> buildings = new ArrayList<>();

	public Owner() {
	}

	public Owner(String firstName, String lastName, String personalCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalCode = personalCode;
	}

	public Owner(Long id, String firstName, String lastName, String personalCode) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalCode = personalCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalCode() {
		return personalCode;
	}

	public void setPersonalCode(String personalCode) {
		this.personalCode = personalCode;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public void addBuilding(Building building) {
		building.setOwnersPersonalCode(this.getPersonalCode());
		buildings.add(building);
		building.setOwner(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((personalCode == null) ? 0 : personalCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Owner other = (Owner) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (personalCode == null) {
			if (other.personalCode != null)
				return false;
		} else if (!personalCode.equals(other.personalCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", personalCode="
				+ personalCode + ", buildings=" + buildings + "]";
	}

}

package lt.task.realestateregistry.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "buildings")
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private double size;
	@NotNull
	private double marketValue;
	@Enumerated(EnumType.STRING)
	private Type propertyType;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "owner_id")
	@JsonIgnore
	private Owner owner;
	private String ownersPersonalCode;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public Building() {
	}

	public Building(double size, double marketValue, Type propertyType, Address address) {
		super();
		this.size = size;
		this.marketValue = marketValue;
		this.propertyType = propertyType;
		this.address = address;
	}

	public Building(Long id, double size, double marketValue, Type propertyType, Address address) {
		this.id = id;
		this.size = size;
		this.marketValue = marketValue;
		this.propertyType = propertyType;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public Type getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Type propertyType) {
		this.propertyType = propertyType;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getOwnersPersonalCode() {
		return ownersPersonalCode;
	}

	public void setOwnersPersonalCode(String ownersPersonalCode) {
		this.ownersPersonalCode = ownersPersonalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(marketValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((ownersPersonalCode == null) ? 0 : ownersPersonalCode.hashCode());
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Building other = (Building) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(marketValue) != Double.doubleToLongBits(other.marketValue))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (ownersPersonalCode == null) {
			if (other.ownersPersonalCode != null)
				return false;
		} else if (!ownersPersonalCode.equals(other.ownersPersonalCode))
			return false;
		if (propertyType != other.propertyType)
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", size=" + size + ", marketValue=" + marketValue + ", propertyType="
				+ propertyType + ", owner=" + owner + ", ownersPersonalCode=" + ownersPersonalCode + ", address="
				+ address + "]";
	}

}

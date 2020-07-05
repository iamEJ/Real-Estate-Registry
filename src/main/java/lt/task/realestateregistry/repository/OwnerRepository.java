package lt.task.realestateregistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.task.realestateregistry.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
	
}

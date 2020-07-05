package lt.task.realestateregistry;


import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import lt.task.realestateregistry.model.Address;
import lt.task.realestateregistry.model.Building;
import lt.task.realestateregistry.model.Owner;
import lt.task.realestateregistry.model.Type;
import lt.task.realestateregistry.repository.BuildingRepository;
import lt.task.realestateregistry.repository.OwnerRepository;
import lt.task.realestateregistry.service.BuildingService;
import lt.task.realestateregistry.service.OwnerService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class RealestateregistryApplicationTests {

	@Autowired
	private OwnerService ownerService;

	@MockBean
	private OwnerRepository ownerRepository;

	@Autowired
	private BuildingService buildingService;

	@MockBean
	private BuildingRepository buildingRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void FindOwnerByIdTest() {
		// Setup our mock repository
		Owner owner = new Owner(1l, "Josh", "Peck", "12459874523");
		doReturn(Optional.of(owner)).when(ownerRepository).findById(1l);
		// Execute the service call
		Optional<Owner> returnedOwner = ownerService.findById(1l);
		// Assert the response
		Assertions.assertTrue(returnedOwner.isPresent(), "Owner was not found");
		Assertions.assertSame(returnedOwner.get(), owner, "The owner returned was not the same as the mock");
	}

	@Test
	void FindOwnerByIdNotFoundTest() {
		// Setup our mock repository
		doReturn(Optional.empty()).when(ownerRepository).findById(1l);
		// Execute the service call
		Optional<Owner> returnedOwner = ownerService.findById(1l);
		// Assert the response
		Assertions.assertFalse(returnedOwner.isPresent(), "Owner should not be found");
	}

	@Test
	void FindAllOwnersTest() {
		// Setup our mock repository
		Owner owner1 = new Owner(1l, "Jim", "Jackson", "55521222211");
		Owner owner2 = new Owner(2l, "Frank", "Smily", "4458125469");
		doReturn(Arrays.asList(owner1, owner2)).when(ownerRepository).findAll();
		// Execute the service call
		List<Owner> owners = ownerService.findAll();
		// Assert the response
		Assertions.assertEquals(2, owners.size(), "findAll should return 2 owners");
	}

	@Test
	void createOwnerTest() {
		// Setup our mock repository
		Owner owner = new Owner(1l, "Tommy", "Hols", "1256974121");
		ownerService.create(owner);
		verify(ownerRepository, times(1)).save(owner);
	}

	@Test
	public void deleteOwnerTest() {
		Long ownerId = 42L;
		ownerService.delete(ownerId);
		verify(ownerRepository, times(1)).deleteById(ownerId);
	}

	//// Building service tests
	@Test
	void FindBuildingByIdTest() {
		// Setup our mock repository
		Building building = new Building(1L, 24, 33000, Type.APARTMENT, new Address("Paris", "Sand st.", 7));
		doReturn(Optional.of(building)).when(buildingRepository).findById(1l);
		// Execute the service call
		Optional<Building> returnedBuilding = buildingService.findById(1l);
		// Assert the response
		Assertions.assertTrue(returnedBuilding.isPresent(), "Building was not found");
		Assertions.assertSame(returnedBuilding.get(), building, "The building returned was not the same as the mock");
	}

	@Test
	void FindBuildingByIdNotFoundTest() {
		// Setup our mock repository
		doReturn(Optional.empty()).when(buildingRepository).findById(1l);
		// Execute the service call
		Optional<Building> returnedBuilding = buildingService.findById(1l);
		// Assert the response
		Assertions.assertFalse(returnedBuilding.isPresent(), "Building should not be found");
	}

	@Test
	void FindAllBuildingsTest() {
		// Setup our mock repository
		Building building1 = new Building(1L, 24, 33000, Type.APARTMENT, new Address("Paris", "Sand st.", 7));
		Building building2 = new Building(2L, 54, 56000, Type.COMMERCIAL, new Address("London", "Road st.", 4));
		doReturn(Arrays.asList(building1, building2)).when(buildingRepository).findAll();
		// Execute the service call
		List<Building> buildings = buildingService.findAll();
		// Assert the response
		Assertions.assertEquals(2, buildings.size(), "findAll should return 2 buildings");
	}

	@Test
	void createBuildingTest() {
		// Setup our mock repository
		Building building = new Building(1L, 24, 33000, Type.APARTMENT, new Address("Paris", "Sand st.", 7));
		buildingService.create(building);
		verify(buildingRepository, times(1)).save(building);
	}

	@Test
	public void deleteBuildingTest() {
		Long buildingId = 42L;
		buildingService.deleteById(buildingId);
		verify(buildingRepository, times(1)).deleteById(buildingId);
	}

}

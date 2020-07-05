package lt.task.realestateregistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lt.task.realestateregistry.model.Address;
import lt.task.realestateregistry.model.Building;
import lt.task.realestateregistry.model.Owner;
import lt.task.realestateregistry.model.Type;
import lt.task.realestateregistry.service.OwnerService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RealestateregistryApplication implements CommandLineRunner {

	@Autowired
	private OwnerService ownerService;

	public static void main(String[] args) {
		SpringApplication.run(RealestateregistryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Owner o1 = new Owner("Tom", "Hid", "1234569870");
		o1.addBuilding(new Building(54, 10000, Type.APARTMENT, new Address("Londin", "Caol", 5)));
		o1.addBuilding(new Building(254, 90000, Type.HOUSE, new Address("Berlin", "Jaum", 5)));
		
		ownerService.create(o1);

		Owner o2 = new Owner("Mark", "Blue", "1234500000");
		o2.addBuilding(new Building(14, 85000, Type.APARTMENT, new Address("Paris", "Green", 99)));
		o2.addBuilding(new Building(120, 190000, Type.COTTAGE, new Address("Jibur", "Smart", 86)));

		ownerService.create(o2);
	}

}

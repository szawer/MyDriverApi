package pl.lukasz.szawronski.mydriverapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.lukasz.szawronski.mydriverapi.driver.Driver;
import pl.lukasz.szawronski.mydriverapi.driver.DriverRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class DriverRepositoryTests {
    @Autowired
    private DriverRepository repo;

    @Test
    public void testAddNew(){
        Driver driver = new Driver();
        driver.setFirstName("Hans");
        driver.setLastName("Klos");
        driver.setPesel("12312312311");
        driver.setPhoneNumber("101202303");
        driver.setDateOfBorn("10.09.1999");
        driver.setDateOfEmployment("10.05.2021");
        driver.setRegistrationNumber("LSW71870");
        driver.setFuel(2500);
        driver.setKilometers(8000);
        driver.setSalary(4010);

        Driver savedDriver = repo.save(driver);

        assertThat(savedDriver).isNotNull();
        assertThat(savedDriver.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Driver> drivers = repo.findAll();
        assertThat(drivers).hasSizeGreaterThan(0);

        for (Driver driver : drivers){
            System.out.println(driver);
        }
    }

    @Test
    public void testUpdate(){
        Integer driverId = 1;
        Optional<Driver> optionalDriver = repo.findById(driverId);
        Driver driver = optionalDriver.get();
        driver.setPhoneNumber("111222333");
        repo.save(driver);

        Driver updatedDriver = repo.findById(driverId).get();
        assertThat(updatedDriver.getPhoneNumber().equals("111222333"));
    }
    @Test
    public void testGet(){
        Integer driverId = 1;
        Optional<Driver> optionalDriver = repo.findById(driverId);

        assertThat(optionalDriver).isPresent();
        System.out.println(optionalDriver.get());
    }

    @Test
    public void testDelete(){
        Integer driverId = 2;
        repo.deleteById(driverId);
        Optional<Driver> optionalDriver = repo.findById(driverId);

        assertThat(optionalDriver).isNotPresent();
    }
}

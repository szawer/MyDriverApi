package pl.lukasz.szawronski.mydriverapi.driver;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DriverRepository extends CrudRepository<Driver, Integer> {
    public Long countById(Integer id);
}

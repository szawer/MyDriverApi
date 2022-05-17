package pl.lukasz.szawronski.mydriverapi.truck;

import org.springframework.data.repository.CrudRepository;

public interface TruckRepository extends CrudRepository<Truck, Integer> {
    Long countById(Integer id);
}

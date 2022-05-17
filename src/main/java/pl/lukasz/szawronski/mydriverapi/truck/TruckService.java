package pl.lukasz.szawronski.mydriverapi.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukasz.szawronski.mydriverapi.driver.DriverNotFoundException;

import java.util.List;
import java.util.Optional;
@Service
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;
    public List<Truck> listAll(){
        return (List<Truck>) truckRepository.findAll();
    }

    public void save(Truck truck) {
        truckRepository.save(truck);
    }

    public Truck get(Integer id) throws DriverNotFoundException {
        Optional<Truck> result = truckRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new DriverNotFoundException(" Could not find any drivers with ID " + id);
    }

    public void delete(Integer id) throws DriverNotFoundException {
        Long count = truckRepository.countById(id);
        if (count == null || count == 0){
            throw new DriverNotFoundException("Could not find any users with id: " + id);
        }
        truckRepository.deleteById(id);
    }
}


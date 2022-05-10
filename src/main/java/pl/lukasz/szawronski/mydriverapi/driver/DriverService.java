package pl.lukasz.szawronski.mydriverapi.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository repo;
    public List<Driver> listAll(){
        return (List<Driver>) repo.findAll();
    }

    public void save(Driver driver) {
        repo.save(driver);
    }
    public Driver get(Integer id) throws DriverNotFoundException {
        Optional<Driver> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new DriverNotFoundException(" Could not find any drivers with ID " + id);
    }

    public void delete(Integer id) throws DriverNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new DriverNotFoundException("Could not find any users with id: " + id);
        }
        repo.deleteById(id);
    }
}

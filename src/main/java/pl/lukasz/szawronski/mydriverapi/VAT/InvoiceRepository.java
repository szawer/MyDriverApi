package pl.lukasz.szawronski.mydriverapi.VAT;

import org.springframework.data.repository.CrudRepository;
import pl.lukasz.szawronski.mydriverapi.truck.Truck;

public interface InvoiceRepository extends CrudRepository<VatInvoice, Integer> {
    Long countById(Integer id);
}

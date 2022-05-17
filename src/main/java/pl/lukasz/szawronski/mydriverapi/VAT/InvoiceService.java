package pl.lukasz.szawronski.mydriverapi.VAT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<VatInvoice> listAll(){
        return (List<VatInvoice>) invoiceRepository.findAll();
    }

    public void save(VatInvoice vatInvoice) {
        invoiceRepository.save(vatInvoice);
    }

    public VatInvoice get(Integer id) throws InvoiceNotFoundException {
        Optional<VatInvoice> result = invoiceRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new InvoiceNotFoundException(" Could not find any invoices with ID " + id);
    }

    public void delete(Integer id) throws InvoiceNotFoundException {
        Long count = invoiceRepository.countById(id);
        if (count == null || count == 0){
            throw new InvoiceNotFoundException("Could not find any invoices with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }
}


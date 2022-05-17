package pl.lukasz.szawronski.mydriverapi.VAT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukasz.szawronski.mydriverapi.driver.Driver;
import pl.lukasz.szawronski.mydriverapi.driver.DriverNotFoundException;

import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/invoices")
    public String showInvoicesList(Model model) {
        List<VatInvoice> listInvoices = invoiceService.listAll();
        model.addAttribute("listInvoices", listInvoices);
        return "invoices";
    }

    @GetMapping("/invoices/new")
    public String showInvoicesNewForm(Model model) {
        model.addAttribute("invoice", new VatInvoice());
        model.addAttribute("pageTitle", "Add New VatInvoice");
        return "invoice_form";
    }

    @PostMapping("/invoices/save")
    public String saveInvoice(VatInvoice vatInvoice, RedirectAttributes ra) {
        invoiceService.save(vatInvoice);
        ra.addFlashAttribute("message", "The VAT Invoice has been saved successfully!");
        return "redirect:/invoices";
    }

    @GetMapping("/invoices/edit/{id}")
    public String editInvoice(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            VatInvoice vatInvoice = invoiceService.get(id);
            model.addAttribute("vatInvoice", vatInvoice);
            model.addAttribute("pageTitle", "Edit Invoice (ID: " + id + ") ");

            return "invoice_form";
        } catch (InvoiceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/invoices";
        }
    }

    @GetMapping("/invoices/delete/{id}")
    public String editInvoice(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            invoiceService.delete(id);
            ra.addFlashAttribute("message", "The VAT Invoice ID " + id + " has been deleted!");
        } catch (InvoiceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/invoices";
    }
    @GetMapping("/invoices/info/{id}")
    public String singleInvoiceInfo(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            VatInvoice vatInvoice = invoiceService.get(id);
            model.addAttribute("invoice", vatInvoice);
            model.addAttribute("invoice", invoiceRepository.findById(id).get());

            return "invoice_info";
        } catch (InvoiceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/invoices";
        }
    }
}


package pl.lukasz.szawronski.mydriverapi.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DriverController {
    @Autowired
    private DriverService service;

    @Autowired
    DriverRepository repository;

    @GetMapping("/drivers")
    public String showDriverList(Model model) {
        List<Driver> listDrivers = service.listAll();
        model.addAttribute("listDrivers", listDrivers);
        return "drivers";
    }

    @GetMapping("/drivers/new")
    public String showNewForm(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("pageTitle", "Add New Driver");
        return "driver_form";
    }

    @PostMapping("/drivers/save")
    public String saveDriver(Driver driver, RedirectAttributes ra) {
        service.save(driver);
        ra.addFlashAttribute("message", "The driver has been saved successfully!");
        return "redirect:/drivers";
    }

    @GetMapping("/drivers/edit/{id}")
    public String deleteDriver(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Driver driver = service.get(id);
            model.addAttribute("driver", driver);
            model.addAttribute("pageTitle", "Edit Driver (ID: " + id + ") ");

            return "driver_form";
        } catch (DriverNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/drivers";
        }
    }
    @GetMapping("/drivers/delete/{id}")
    public String deleteDriver(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The driver ID " + id + " has been deleted!");
        } catch (DriverNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/drivers";
    }
    @GetMapping("/drivers/info/{id}")
    public String singleDriverInfo(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Driver driver = service.get(id);
            model.addAttribute("driver", driver);
            model.addAttribute("driver", repository.findById(id).get());

            return "driver_info";
        } catch (DriverNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/drivers";
        }
    }

//    public String singleDriverInfo(@PathVariable("id") Integer id, Model model) throws DriverNotFoundException {
//        List<Driver> listDrivers = service.listAll();
//        model.addAttribute("listDrivers", listDrivers);
//
//        return "driver_info";
//    }
}


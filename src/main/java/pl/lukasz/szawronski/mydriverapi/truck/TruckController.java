package pl.lukasz.szawronski.mydriverapi.truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TruckController {

    @Autowired
    TruckService truckService;


    @GetMapping("/trucks")
    public String showDriverList(Model model) {
        List<Truck> listTrucks = truckService.listAll();
        model.addAttribute("listTrucks", listTrucks);
        return "trucks";
    }

    @GetMapping("/trucks/new")
    public String showNewTruckForm(Model model){
        model.addAttribute("truck", new Truck());
        model.addAttribute("pageTitle", "Add new Truck");
        return "truck_form";
    }

    @PostMapping("/trucks/save")
    public String saveTruck(Truck truck, RedirectAttributes ra) {
        truckService.save(truck);
        ra.addFlashAttribute("message", "Truck has been saved successfully!");
        return "redirect:/trucks";
    }
}

package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets.html"})
    public String listVets(Model model) {
        Set<Vet> vetSet = vetService.findAll();
        model.addAttribute("vets", vetSet);
        return "vets/vets-list";
    }

    @GetMapping("/api/vets")
    @ResponseBody
    public Set<Vet> getJason() {
        return vetService.findAll();
    }
}

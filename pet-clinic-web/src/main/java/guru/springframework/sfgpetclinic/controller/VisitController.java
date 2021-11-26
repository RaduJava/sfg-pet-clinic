package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VisitController {
    private final VisitService visitService;
    private final OwnerService ownerService;
    private final PetService petService;

    public VisitController(VisitService visitService, OwnerService ownerService, PetService petService) {
        this.visitService = visitService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit initVisit(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        visit.setPet(pet);
        pet.getVisits().add(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initCreationForm(@PathVariable("petId") Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
        }
        return "redirect:/owners/{ownerId}";
    }
}

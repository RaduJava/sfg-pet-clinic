package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savePetTypeDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savePetTypeCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality radiologySpeciality = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality surgerySpeciality = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality dentistrySpeciality = specialityService.save(dentistry);

        Owner ion = new Owner();
        ion.setFirstName("Ion");
        ion.setLastName("Turcan");
        ion.setAddress("Moscow 16");
        ion.setCity("Chisinau");
        ion.setTelephone("069121361");


        Pet ionPet = new Pet();
        ionPet.setPetType(dog);
        ionPet.setOwner(ion);
        ionPet.setBirthDay(LocalDate.now());
        ionPet.setName("Olaf");
        ion.getPets().add(ionPet);

        ownerService.save(ion);
        Visit visit = new Visit();
        visit.setPet(ionPet);
        visit.setDate(LocalDate.now());
        visit.setDescription("Snizy Kitty");

        visitService.save(visit);

        Owner radu = new Owner();
        radu.setFirstName("Radu");
        radu.setLastName("Gribincea");
        radu.setAddress("Dimo 12");
        radu.setCity("Balti");
        radu.setTelephone("079234232");

        Pet raduPet = new Pet();
        raduPet.setPetType(cat);
        raduPet.setOwner(radu);
        raduPet.setBirthDay(LocalDate.now());
        raduPet.setName("Smokie");
        radu.getPets().add(raduPet);
        ownerService.save(radu);

        System.out.println("Load owners........");

        Vet gicu = new Vet();
        gicu.setFirstName("Gicu");
        gicu.setLastName("Tarus");
        gicu.getSpecialities().add(radiologySpeciality);
        vetService.save(gicu);

        Vet petru = new Vet();
        petru.setFirstName("Petru");
        petru.setLastName("Cornescu");
        petru.getSpecialities().add(dentistrySpeciality);
        vetService.save(petru);

        System.out.println("Load vets......");
    }
}

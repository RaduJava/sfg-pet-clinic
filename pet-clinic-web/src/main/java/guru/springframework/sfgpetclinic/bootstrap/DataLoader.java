package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savePetTypeDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savePetTypeCat = petTypeService.save(cat);

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

        Owner radu = new Owner();
        radu.setFirstName("Radu");
        radu.setLastName("Gribincea");
        radu.setAddress("Dimo 12");
        radu.setCity("Balti");
        ion.setTelephone("079234232");

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
        vetService.save(gicu);

        Vet petru = new Vet();
        petru.setFirstName("Petru");
        petru.setLastName("Cornescu");
        vetService.save(petru);

        System.out.println("Load vets......");
    }
}

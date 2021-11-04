package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner ion = new Owner();
        ion.setId(1L);
        ion.setFirstName("Ion");
        ion.setLastName("Turcan");
        ownerService.save(ion);

        Owner radu = new Owner();
        radu.setId(2L);
        radu.setFirstName("Radu");
        radu.setLastName("Gribincea");
        ownerService.save(radu);

        System.out.println("Load owners........");

        Vet gicu = new Vet();
        gicu.setId(1L);
        gicu.setFirstName("Gicu");
        gicu.setLastName("Tarus");
        vetService.save(gicu);

        Vet petru = new Vet();
        petru.setId(2L);
        petru.setFirstName("Petru");
        petru.setLastName("Cornescu");
        vetService.save(petru);

        System.out.println("Load vets......");
    }
}

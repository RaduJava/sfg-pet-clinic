package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class OwnerServiceMapTest {
    private OwnerServiceMap ownerServiceMap;
    @Mock
    private PetTypeService petTypeService;
    @Mock
    private PetService petService;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(petTypeService, petService);
        ownerServiceMap.save(Owner.builder().id(1L).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(ownerId, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerServiceMap.findById(ownerId).getId());
        assertEquals(0, ownerServiceMap.findAll().size());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long id = 2l;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(2, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(lastName);
        assertEquals(lastName, smith.getLastName());
        assertNotNull(smith);
    }
    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("lastName");
        assertNull(smith);
    }
}
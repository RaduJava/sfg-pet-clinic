package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialitiesRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJPAService implements SpecialityService {
    private final SpecialitiesRepository specialitiesRepository;

    public SpecialitySDJPAService(SpecialitiesRepository specialitiesRepository) {
        this.specialitiesRepository = specialitiesRepository;
    }

    @Override
    public Speciality findById(Long id) {
        return specialitiesRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialitiesRepository.save(speciality);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialitiesRepository.findAll().forEach(speciality -> specialities.add(speciality));
        return specialities;
    }

    @Override
    public void delete(Speciality object) {
        specialitiesRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialitiesRepository.deleteById(aLong);
    }
}

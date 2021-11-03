package guru.springframework.sfgpetclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
    T findById(Long id);

    T save(T t);

    Set<T> findAll();

    void delete(T object);

    void deleteById(ID id);
}

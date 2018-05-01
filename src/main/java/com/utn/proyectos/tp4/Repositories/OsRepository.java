package com.utn.proyectos.tp4.Repositories;

import com.utn.proyectos.tp4.Pojos.Os;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramir
 */
@Repository
public interface OsRepository extends CrudRepository<Os, Long> {

    Os findByNameAndVersion(String name, String version);
}

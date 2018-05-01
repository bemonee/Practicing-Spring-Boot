package com.utn.proyectos.tp4.Repositories;

import com.utn.proyectos.tp4.Pojos.Browser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramir
 */
@Repository
public interface BrowserRepository extends CrudRepository<Browser, Long> {

    Browser findByNameAndVersion(String name, String version);
}

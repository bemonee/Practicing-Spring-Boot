
package com.utn.proyectos.tp4.Repositories;

import com.utn.proyectos.tp4.Pojos.Browser;
import com.utn.proyectos.tp4.Pojos.Client;
import com.utn.proyectos.tp4.Pojos.Os;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ramir
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    
    @Query("SELECT b FROM Client c, Browser b WHERE c.browser = b GROUP BY b ORDER BY COUNT(c.browser) DESC ")
    public List<Browser> findBrowsersOrderedByUsage();
    
    @Query("SELECT o FROM Client c, Os o WHERE c.os = o GROUP BY o ORDER BY COUNT(c.os) DESC ")
    public List<Os> findOssOrderedByUsage();

}

package com.utn.proyectos.tp4.Services;

import com.utn.proyectos.tp4.Pojos.Os;
import com.utn.proyectos.tp4.Repositories.OsRepository;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramir
 */
@Service
@NoArgsConstructor
public class OsService {

    @Autowired
    private OsRepository osRepository;

    public List<Os> findAll() {
        return (List<Os>) this.osRepository.findAll();
    }
}

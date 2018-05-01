package com.utn.proyectos.tp4.Services;

import com.utn.proyectos.tp4.Pojos.Browser;
import com.utn.proyectos.tp4.Repositories.BrowserRepository;
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
public class BrowserService {
    
    @Autowired
    private BrowserRepository browserRepository;
    
    public List<Browser> findAll(){
        return (List<Browser>) this.browserRepository.findAll();
    }
}

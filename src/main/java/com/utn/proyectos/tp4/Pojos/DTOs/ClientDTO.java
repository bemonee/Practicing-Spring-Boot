package com.utn.proyectos.tp4.Pojos.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ramir
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private NameVersionDTO browser;
    private NameVersionDTO os;
    
}

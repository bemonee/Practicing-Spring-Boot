package com.utn.proyectos.tp4.Configs;

import com.utn.proyectos.tp4.Pojos.Browser;
import com.utn.proyectos.tp4.Pojos.DTOs.ClientDTO;
import com.utn.proyectos.tp4.Pojos.DTOs.NameVersionDTO;
import com.utn.proyectos.tp4.Pojos.Os;
import javafx.util.Pair;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ramir
 */
@Configuration
public class Config {

    @Bean
    public UserAgentAnalyzer getUserAgentAnalyzer() {
        return UserAgentAnalyzer.newBuilder()
                .withField("AgentName")
                .withField("AgentVersion")
                .withField("OperatingSystemName")
                .withField("OperatingSystemVersion")
                .build();
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Pair<Os, Browser>, ClientDTO> converter = new Converter<Pair<Os, Browser>, ClientDTO>() {
            public ClientDTO convert(MappingContext<Pair<Os, Browser>, ClientDTO> context) {
                ClientDTO destination = context.getDestination();
                destination.setOs(modelMapper.map(context.getSource().getKey(), NameVersionDTO.class));
                destination.setBrowser(modelMapper.map(context.getSource().getValue(), NameVersionDTO.class));
                return destination;
            }
        };
        modelMapper.addConverter(converter);
        return modelMapper;
    }
}

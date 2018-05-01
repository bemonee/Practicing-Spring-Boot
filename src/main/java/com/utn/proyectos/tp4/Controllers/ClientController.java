package com.utn.proyectos.tp4.Controllers;

import com.utn.proyectos.tp4.Pojos.Browser;
import com.utn.proyectos.tp4.Pojos.Client;
import com.utn.proyectos.tp4.Pojos.DTOs.ClientDTO;
import com.utn.proyectos.tp4.Pojos.DTOs.NameVersionDTO;
import com.utn.proyectos.tp4.Pojos.Os;
import com.utn.proyectos.tp4.Services.BrowserService;
import com.utn.proyectos.tp4.Services.ClientService;
import com.utn.proyectos.tp4.Services.OsService;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;
import lombok.NoArgsConstructor;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ramir
 */
@RestController
@RequestMapping("/clients")
@NoArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BrowserService browserService;

    @Autowired
    private OsService osService;

    @Autowired
    private UserAgentAnalyzer uaa;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public ClientDTO captureDataFromClient(@RequestHeader(value = "User-Agent") String userAgent) {
        UserAgent ua = this.uaa.parse(userAgent);

        Client client = this.clientService.insertClient(ua);
        ClientDTO clientDTO = this.modelMapper.map(client, ClientDTO.class);

        return clientDTO;
    }

    @RequestMapping(path = "/browsers", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getMostUsedBrowserByClients(@RequestParam(value = "filter", defaultValue = "all") String filter) {
        if (filter.equalsIgnoreCase("most_used")) {
            Browser browser = this.clientService.getMostUsedBrowserByClients();
            if (browser == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                NameVersionDTO NameVersionDTO = this.modelMapper.map(browser, NameVersionDTO.class);
                return new ResponseEntity<>(NameVersionDTO, HttpStatus.OK);
            }
        } else {
            List<Browser> browsers = this.browserService.findAll();
            if (browsers.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(browsers.stream()
                        .map(browser -> this.modelMapper.map(browser, NameVersionDTO.class))
                        .collect(Collectors.toList()),
                        HttpStatus.OK);
            }
        }
    }

    @RequestMapping(path = "/oss", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getMostUsedOsByClients(@RequestParam(value = "filter", defaultValue = "all") String filter) {
        if (filter.equalsIgnoreCase("most_used")) {
            Os os = this.clientService.getMostUsedOsByClients();
            if (os == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                NameVersionDTO NameVersionDTO = this.modelMapper.map(os, NameVersionDTO.class);
                return new ResponseEntity<>(NameVersionDTO, HttpStatus.OK);
            }
        } else {
            List<Os> oss = this.osService.findAll();
            if (oss.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(oss.stream()
                        .map(os -> this.modelMapper.map(os, NameVersionDTO.class))
                        .collect(Collectors.toList()),
                        HttpStatus.OK);
            }
        }
    }

    @RequestMapping(path = "/oss/browsers", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getMostUsedCombinationByClients(@RequestParam(value = "filter", defaultValue = "all") String filter) {
        if (filter.equalsIgnoreCase("most_used")) {
            Pair<Os, Browser> combination = this.clientService.getMostUsedCombinationByClients();
            if (combination == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                ClientDTO clientDTO = this.modelMapper.map(combination, ClientDTO.class);
                return new ResponseEntity<>(clientDTO, HttpStatus.OK);
            }
        } else {
            List<Client> clients = this.clientService.findAll();
            if (clients.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(clients.stream()
                        .map(client -> this.modelMapper.map(client, ClientDTO.class))
                        .collect(Collectors.toList()),
                        HttpStatus.OK);
            }
        }
    }
}

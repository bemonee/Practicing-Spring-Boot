package com.utn.proyectos.tp4.Services;

import com.utn.proyectos.tp4.Pojos.Browser;
import com.utn.proyectos.tp4.Pojos.Client;
import com.utn.proyectos.tp4.Pojos.Os;
import com.utn.proyectos.tp4.Repositories.BrowserRepository;
import com.utn.proyectos.tp4.Repositories.ClientRepository;
import com.utn.proyectos.tp4.Repositories.OsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import lombok.NoArgsConstructor;
import nl.basjes.parse.useragent.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramir
 */
@Service
@NoArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BrowserRepository browserRepository;

    @Autowired
    private OsRepository osRepository;

    public Client insertClient(UserAgent ua) {
        Client client = new Client();

        String browserName = ua.getValue("AgentName");
        String browserVersion = ua.getValue("AgentVersion");
        Browser browser = this.browserRepository.findByNameAndVersion(browserName, browserVersion);
        if (browser == null) {
            Browser userBrowser = new Browser();
            userBrowser.setName(browserName);
            userBrowser.setVersion(browserVersion);
            this.browserRepository.save(userBrowser);
            client.setBrowser(userBrowser);
        } else {
            client.setBrowser(browser);
        }

        String osName = ua.getValue("OperatingSystemName");
        String osVersion = ua.getValue("OperatingSystemVersion");
        Os os = this.osRepository.findByNameAndVersion(osName, osVersion);
        if (os == null) {
            Os userOs = new Os();
            userOs.setName(osName);
            userOs.setVersion(osVersion);
            this.osRepository.save(userOs);
            client.setOs(userOs);
        } else {
            client.setOs(os);
        }

        this.clientRepository.save(client);

        return client;
    }

    public Browser getMostUsedBrowserByClients() {
        List<Browser> browsers = this.clientRepository.findBrowsersOrderedByUsage();
        if (!browsers.isEmpty()) {
            return browsers.get(0);
        }
        return null;
    }

    public Os getMostUsedOsByClients() {
        List<Os> oss = this.clientRepository.findOssOrderedByUsage();
        if (!oss.isEmpty()) {
            return oss.get(0);
        }
        return null;
    }

    public Pair<Os, Browser> getMostUsedCombinationByClients() {
        List<Client> clients = this.findAll();
        if (!clients.isEmpty()) {
            Map<Pair<Os, Browser>, Integer> combinations = new HashMap<>();
            Integer count;
            for (Client c : clients) {
                Pair<Os, Browser> key = new Pair<>(c.getOs(), c.getBrowser());
                if (combinations.containsKey(key)) {
                    count = combinations.get(key);
                    combinations.put(key, ++count);
                } else {
                    combinations.putIfAbsent(key, 1);
                }
            }
            Map.Entry<Pair<Os, Browser>, Integer> mostUsed = null;
            for (Map.Entry<Pair<Os, Browser>, Integer> entry : combinations.entrySet()) {
                if (mostUsed == null) {
                    mostUsed = entry;
                } else {
                    if (entry.getValue() > mostUsed.getValue()) {
                        mostUsed = entry;
                    }
                }
            }
            return mostUsed.getKey();
        }
        return null;

    }

    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

}

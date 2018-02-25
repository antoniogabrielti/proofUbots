package ubots.proof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ubots.proof.model.Client;
import ubots.proof.model.Purchase;
import java.util.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    private RestTemplate restTemplate;
    private List<Client> clients;
    private List<Purchase> purchases;
    private ProcessDataController processData;

    @Autowired
    public ReportController() {
        this.restTemplate = new RestTemplate();
        this.clients = Arrays.asList(restTemplate.getForObject("http://www.mocky.io/v2/598b16291100004705515ec5", Client[].class));
        this.purchases = Arrays.asList(restTemplate.getForObject("http://www.mocky.io/v2/598b16861100004905515ec7", Purchase[].class));
        this.processData = new ProcessDataController(this.clients, this.purchases);
    }

    @RequestMapping(value="/clients" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients() {
        return this.clients;
    }


    @RequestMapping(value="/purchases" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Purchase> getPurchases() {
        return this.purchases;
    }

    @RequestMapping(value="/purchases/sumtotal" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getPurchasesOrdenedTotal() {
        return this.processData.PurchasesSumTotal();
    }

}

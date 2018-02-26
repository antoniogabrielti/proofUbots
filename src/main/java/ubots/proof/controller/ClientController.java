package ubots.proof.controller;

import ubots.proof.model.Client;
import ubots.proof.model.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientController {

    private List<Client> clients;

    public ClientController(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClientsByPurchases(List<Purchase> purchases){
        List<Client> filteredClients = new ArrayList<>();

        for(Purchase p : purchases){
            for(Client c : this.clients){
                if(c.getCpf().equalsIgnoreCase(p.getCliente())){
                    filteredClients.add(c);
                }
            }
        }

        return filteredClients;
    }

    public List<Client> getClientsByCpf(List<Map.Entry<String,Integer>> cpfs){
        List<Client> filteredClients = new ArrayList<>();
        cpfs.stream().forEach(cpf -> filteredClients.add(this.searchClientByCpf(cpf.getKey())));
        return filteredClients;
    }

    public Client searchClientByCpf(String cpf){
        return this.clients.stream()
                .filter(c -> c.getCpf().equalsIgnoreCase(cpf)).collect(Collectors.toList()).get(0);
    }

}

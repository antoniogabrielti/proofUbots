package ubots.proof.controller;


import ubots.proof.model.Client;
import ubots.proof.model.Purchase;

import java.util.*;
import java.util.stream.Collectors;


public class ProcessDataController {

    private List<Client> clients;
    private List<Purchase> purchases;


    public ProcessDataController(List<Client> clients, List<Purchase> purchases) {
        this.clients = clients;
        this.purchases = purchases;
        this.formatCPF();
    }

    public void formatCPF (){
        for(Purchase p : this.purchases){
            p.setCliente("000.000.000-"+p.getCliente().substring(p.getCliente().length() - 2));
        }
    }

    public List<Purchase> OrdenedClientPurchases(List<Purchase> purchases){
        List<Purchase> ordenedPurchases = purchases;
        Collections.sort(ordenedPurchases);
        return ordenedPurchases;
    }

    public List<Client> PurchasesSumTotal(){
        List<Purchase> ordenedPurchases = this.OrdenedClientPurchases(this.purchases);
        List<Purchase> purchases = new ArrayList<>();

        ordenedPurchases.stream()
                .collect(Collectors.groupingBy(purchase -> purchase.getCliente(),
                         Collectors.summingDouble(purchase->purchase.getValorTotal())))
                .forEach((cliente,valorTotal)->purchases.add(new Purchase(cliente, valorTotal)));

        List<Purchase> maxPurchases = purchases.stream()
                .sorted(Collections.reverseOrder(Comparator.comparingDouble(purchase -> purchase.getValorTotal())))
                .collect(Collectors.toList());

        return this.getClients(maxPurchases);

     }


     public List<Client> getClients(List<Purchase> purchases){
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

}

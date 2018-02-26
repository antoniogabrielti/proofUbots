package ubots.proof.controller;


import ubots.proof.model.Client;
import ubots.proof.model.Purchase;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessDataController {

    private List<Client> clients;
    private List<Purchase> purchases;
    private PurchaseController purchaseController;
    private ClientController clientController;


    public ProcessDataController(List<Client> clients, List<Purchase> purchases,
                                 PurchaseController purchaseController, ClientController clientController) {
        this.clients = clients;
        this.purchases = purchases;
        this.purchaseController = purchaseController;
        this.clientController = clientController;
        this.formatCPF();
    }

    private void formatCPF (){
        for(Purchase p : this.purchases){
            p.setCliente("000.000.000-"+p.getCliente().substring(p.getCliente().length() - 2));
        }
    }

    public List<Client> getClientsOrdenedTotal(){
        List<Purchase> ordenedPurchases = this.purchaseController.ordenedClientPurchases(this.purchases);
        List<Purchase> purchases = this.purchaseController.groupByPurchasesTotal(ordenedPurchases);

        List<Purchase> maxPurchases = purchases.stream()
                .sorted(Collections.reverseOrder(Comparator.comparingDouble(purchase -> purchase.getValorTotal())))
                .collect(Collectors.toList());

        return this.clientController.getClientsByPurchases(maxPurchases);
     }

    public List<Client> getClientsMostFaithful(){
        List<String> cpfPurchases = new ArrayList<>();
        Set<String> cpfClientsComparator = new HashSet<>();
        HashMap<String,Integer> clientsQtdPurchases = new HashMap<>();

        for(Purchase p : this.purchases){
            cpfClientsComparator.add(p.getCliente());
            cpfPurchases.add(p.getCliente());
        }

        for(String cpf : cpfClientsComparator){
            clientsQtdPurchases.put(cpf,Collections.frequency(cpfPurchases,cpf));
        }

        List<Map.Entry<String,Integer>> cpfByQtdPurchases = clientsQtdPurchases.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());

        return this.clientController.getClientsByCpf(cpfByQtdPurchases);
    }

    public Client getClientMaxPurchase(){
        List<Purchase> ordenedPurchases = this.purchaseController.ordenedClientPurchases(this.purchases);
        HashMap<String,Double> clientsMaxSinglePurchase = new HashMap<>();

        List<Purchase> purchasesYear2016 = this.purchaseController.ordenedPurchasesByYear(ordenedPurchases,"2016");

        purchasesYear2016.stream().forEach(p ->
                clientsMaxSinglePurchase.put(p.getCliente(),
                p.getItems().stream()
                .max((itemPrevious, itemNext) -> Double.compare(itemPrevious.getPreco(), itemNext.getPreco()))
                .get().getPreco()));

        String cpfMaxSinglePurchase = clientsMaxSinglePurchase.entrySet()
                .stream()
                .max((purchase1, purchase2) -> Double.compare(purchase1.getValue(),purchase2.getValue()))
                .get().getKey();

        return this.clientController.searchClientByCpf(cpfMaxSinglePurchase);
    }
}

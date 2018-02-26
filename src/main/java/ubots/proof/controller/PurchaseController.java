package ubots.proof.controller;

import ubots.proof.model.Purchase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseController {

    public List<Purchase> ordenedClientPurchases(List<Purchase> purchases){
        List<Purchase> ordenedPurchases = purchases;
        Collections.sort(ordenedPurchases);
        return ordenedPurchases;
    }

    public List<Purchase> groupByPurchasesTotal(List<Purchase> purchases){
        List<Purchase> purchasesOrdenedTotal = new ArrayList<>();

        purchases.stream()
                .collect(Collectors.groupingBy(purchase -> purchase.getCliente(),
                        Collectors.summingDouble(purchase->purchase.getValorTotal())))
                .forEach((cliente,valorTotal)->purchasesOrdenedTotal.add(new Purchase(cliente, valorTotal)));

        return purchasesOrdenedTotal;
    }

    public List<Purchase> ordenedPurchasesByYear(List<Purchase> purchases,String year){
        return purchases.stream()
                .filter(p -> p.getData().substring(p.getData().length() - 4).equalsIgnoreCase(year))
                .collect(Collectors.toList());
    }
}

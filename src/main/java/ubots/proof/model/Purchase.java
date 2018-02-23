package ubots.proof.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchase implements Comparable<Purchase> {
    private String codigo;
    private String data;
    private String cliente;
    private Item [] items;
    private double valorTotal;

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public String getCliente() {
        return cliente;
    }

    public Item[] getItems() {
        return items;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int compareTo(Purchase purchase){
        return Double.compare(this.getValorTotal(),purchase.getValorTotal());
    }
}

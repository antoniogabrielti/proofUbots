package ubots.proof.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Purchase implements Comparable<Purchase> {
    private String codigo;
    private String data;
    private String cliente;
    @SerializedName("itens")
    private List<Item> items;
    private double valorTotal;

    public Purchase(@JsonProperty("codigo") String codigo,@JsonProperty("data") String data,@JsonProperty("cliente") String cliente,
                    @JsonProperty("itens") List<Item> items, @JsonProperty("valorTotal") double valorTotal) {
        this.codigo = codigo;
        this.data = data;
        this.cliente = cliente;
        this.items = items;
        this.valorTotal = valorTotal;
    }

    public Purchase(String cliente, Double valorTotal) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int compareTo(Purchase purchase){
        return this.getCliente().compareTo(purchase.getCliente());
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "codigo='" + codigo + '\'' +
                ", data='" + data + '\'' +
                ", cliente='" + cliente + '\'' +
                ", items=" + items +
                ", valorTotal=" + valorTotal +
                '}';
    }
}

package ubots.proof.model;

public class Item {
    private String produto;
    private String variedade;
    private String pais;
    private String categoria;
    private int safra;
    private double preco;


    public String getProduto() {
        return produto;
    }

    public String getVariedade() {
        return variedade;
    }

    public String getPais() {
        return pais;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getSafra() {
        return safra;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Item{" +
                "produto='" + produto + '\'' +
                ", variedade='" + variedade + '\'' +
                ", pais='" + pais + '\'' +
                ", categoria='" + categoria + '\'' +
                ", safra=" + safra +
                ", preco=" + preco +
                '}';
    }
}

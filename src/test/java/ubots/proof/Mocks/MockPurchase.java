package ubots.proof.Mocks;

import ubots.proof.model.Item;
import ubots.proof.model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class MockPurchase {
    private List<Purchase> mockListPurchase;
    private List<Item> mockListItem;

    public MockPurchase() {
        this.mockListPurchase = new ArrayList<>();
        this.mockListItem = new ArrayList<>();
        this.buildData();
    }

    private void buildData(){

        this.mockListItem.add(new Item("vinho1","xx","Brazil","branco",2016,266));
        this.mockListItem.add(new Item("vinho2","xx","Argentina","tinto",2015,355));
        this.mockListItem.add(new Item("vinho3","xx","Chile","tinto",2013,257));

        this.mockListPurchase.add(new Purchase("123","12-12-2016","000.000.000-03",this.mockListItem,
                455));
        this.mockListPurchase.add(new Purchase("123","12-05-2016","000.000.000-02",this.mockListItem,
                855));
        this.mockListPurchase.add(new Purchase("123","12-03-2016","000.000.000-01",this.mockListItem,
                500));
        this.mockListItem.add(new Item("vinho4","xx","Brazil","branco",2011,800));
        this.mockListPurchase.add(new Purchase("123","12-07-2016","000.000.000-01",this.mockListItem,
                100));
    }

    public List<Purchase> getMockListPurchase() {
        return mockListPurchase;
    }
}


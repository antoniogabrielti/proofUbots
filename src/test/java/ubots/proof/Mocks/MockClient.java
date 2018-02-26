package ubots.proof.Mocks;

import ubots.proof.model.Client;

import java.util.ArrayList;
import java.util.List;

public class MockClient {
    private List<Client> mockListClient;

    public MockClient() {
        this.mockListClient = new ArrayList<>();
        this.buildData();
    }

    private void buildData(){
        this.mockListClient.add(new Client(1,"Antonio","000.000.000-01"));
        this.mockListClient.add(new Client(2,"Paulo","000.000.000-02"));
        this.mockListClient.add(new Client(3,"Rafael","000.000.000-03"));
        this.mockListClient.add(new Client(4,"Vinicius","000.000.000-04"));
    }

    public List<Client> getMockListClient() {
        return mockListClient;
    }
}

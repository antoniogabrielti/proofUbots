package ubots.proof;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ubots.proof.Mocks.MockClient;
import ubots.proof.Mocks.MockPurchase;
import ubots.proof.controller.ClientController;
import ubots.proof.controller.ProcessDataController;
import ubots.proof.controller.PurchaseController;
import ubots.proof.model.Client;
import ubots.proof.model.Purchase;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessDataControllerTest {
    private ProcessDataController processData;
    private List<Client> mockClients;
    private List<Purchase> mockPurchases;
    private List<Client> expectedClients;

    @Before
    public void setUp() throws Exception {
        this.mockClients = new MockClient().getMockListClient();
        this.mockPurchases = new MockPurchase().getMockListPurchase();
        this.processData = new ProcessDataController(this.mockClients, this.mockPurchases,new PurchaseController(), new ClientController(this.mockClients));
    }

    @Test
    public void testGetClientsOrdenedTotal(){
        this.expectedClients = new ArrayList<>();
        this.expectedClients.add(new Client(2,"Paulo","000.000.000-02"));
        this.expectedClients.add(new Client(1,"Antonio","000.000.000-01"));
        this.expectedClients.add(new Client(3,"Rafael","000.000.000-03"));
        List<Client> clientsResult = this.processData.getClientsOrdenedTotal();
        assertThat(clientsResult.toString(), is(this.expectedClients.toString()));
    }

    @Test
    public void testGetClientsMostFaithful(){
        this.expectedClients = new ArrayList<>();
        this.expectedClients.add(new Client(1,"Antonio","000.000.000-01"));
        this.expectedClients.add(new Client(2,"Paulo","000.000.000-02"));
        this.expectedClients.add(new Client(3,"Rafael","000.000.000-03"));
        List<Client> clientsResult = this.processData.getClientsMostFaithful();
        assertThat(clientsResult.toString(), is(this.expectedClients.toString()));
    }

    @Test
    public void testGetClientMaxPurchase(){
        Client expectedClient = new Client(1,"Antonio","000.000.000-01");
        Client clientResult = this.processData.getClientMaxPurchase();
        assertThat(clientResult.toString(), is(expectedClient.toString()));
    }
}

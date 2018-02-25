package ubots.proof;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import ubots.proof.controller.ReportController;
import ubots.proof.model.Client;
import ubots.proof.model.Purchase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerTest {
    private ReportController reportController;
    private List<Client> expectedClients;
    private List<Purchase> expectedPurchase;

    @Before
    public void setUp() throws Exception {
        this.reportController = new ReportController();
        RestTemplate restTemplate = new RestTemplate();

        Gson gson = new Gson();
        File f = new File("");
        String pathClient = f.getAbsolutePath() + "/src/test/java/ubots/proof/Mocks/Clients.json";
        BufferedReader clientReader = new BufferedReader(new FileReader(pathClient));
        String pathPurchase = f.getAbsolutePath() + "/src/test/java/ubots/proof/Mocks/Purchases.json";
        BufferedReader purchaseReader = new BufferedReader(new FileReader(pathPurchase));

        this.expectedClients = Arrays.asList(gson.fromJson(clientReader,Client[].class));
        this.expectedPurchase = Arrays.asList(gson.fromJson(purchaseReader,Purchase[].class));
    }

    @Test
    public void testGetClients() throws IOException{
        List<Client> clientsTest = this.reportController.getClients();
        assertThat(clientsTest.toString(), is(this.expectedClients.toString()));
    }

    @Test
    public void testGetPurchases() throws IOException{
        List<Purchase> purchasesTest = this.reportController.getPurchases();
        assertThat(purchasesTest.toString(), is(this.expectedPurchase.toString()));
    }
}

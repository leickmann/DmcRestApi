import beans.Product;
import beans.SubProduct;
import beans.TopLevelProduct;
import junit.framework.Assert;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LabelGenTest {

    public static final int HTTP_CREATED = 200;
    private Client client = ClientBuilder.newClient();
    private TopLevelProduct prod10 = new TopLevelProduct(1000, "Lokomotive", 789065468L);

    @Test
    public void testGenerateLabel() {
        prod10.setSerialNr("87387666");
        SubProduct subProd1 = new SubProduct(1111, "Filter", 765592566L);
        SubProduct subProd2 = new SubProduct(2222, "Hebel", 917752772L);

        subProd1.setSerialNr("2222");
        subProd2.setSerialNr("3333");

        prod10.addSubProduct(subProd1);
        prod10.addSubProduct(subProd2);

        Response response = generateLabel(prod10);
        InputStream in = response.readEntity(InputStream.class);
        Path path = Paths.get("dmctest.png");
        try {
            Files.copy(in, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getClass().toString());

        Assert.assertEquals(response.getStatus(), HTTP_CREATED);
    }

    public Response generateLabel(TopLevelProduct product) {
        return client
                .target("http://localhost:8080/DmcRestApi_war_exploded/labelgen")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .post(Entity.entity(product, MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void testGenerateLabel2() {
//
//
//
//    }
}


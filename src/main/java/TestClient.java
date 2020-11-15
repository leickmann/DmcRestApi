import beans.Supplier;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestClient {

    Client client = ClientBuilder.newClient();
//    WebTarget webTarget = client.target("http://localhost:9080/DmcRestApi_war_exploded");
//
//    WebTarget labelGenWebTarget = webTarget.path("labelgen");

    public Response createJsonEmployee(Supplier sup) {
        return client
                .target("http://localhost:9080/DmcRestApi_war_exploded/labelgen")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(sup, MediaType.APPLICATION_JSON));
    }
}

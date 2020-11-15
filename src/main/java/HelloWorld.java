import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloWorld {

    @GET
    @Produces("application/xml")
    public String getClichedMessage() {
        return "<test>test</test>";
    }
}
package services;

import beans.Supplier;
import beans.TopLevelProduct;
import org.glassfish.jersey.client.ClientResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Path("/labelgen")
public class LabelGeneratorRessource {

    private StringGenerator stringGenerator = new StringGenerator();
    private DmcGenerator dmcGenerator = new DmcGenerator();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateLabel(TopLevelProduct topLevelProduct) {
        System.out.println(topLevelProduct.getProductName());

        String labelString = stringGenerator.generateLabelString(topLevelProduct);
        System.out.println(labelString);

        File file = dmcGenerator.getDmcAsFile(labelString);

        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=" + file.getName() + "" ) //optional
                .build();


    }

}

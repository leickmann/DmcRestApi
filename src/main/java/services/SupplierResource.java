package services;

import databaseservice.MockService;
import beans.Supplier;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Arrays;
import java.util.List;

@Path("suppliers")
public class SupplierResource {

   MockService ms = MockService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuppliers(@Context UriInfo uriInfo) {
        List<Supplier> suppliers = ms.getAllSuppliers();
        suppliers.forEach(p -> initLinks(p, uriInfo));

        GenericEntity<List<Supplier>> genericEntity = new GenericEntity<List<Supplier>>(suppliers) {};

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();

        return Response.ok(genericEntity)
                .links(self).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getSupplier(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Supplier supplier = ms.findSupplier(id);

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();

        return Response.ok(supplier)
                .links(self).build();
    }



    private void initLinks(Supplier supplier, UriInfo uriInfo) {

        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();

        UriBuilder selfUriBuilder = uriBuilder.path(Integer.toString(supplier.getSupplierId()));
        Link.Builder selfLinkBuilder = Link.fromUriBuilder(selfUriBuilder);
        Link selfLink = selfLinkBuilder.rel("self").build();

        UriBuilder productsUriBuilder = selfUriBuilder.path("products");
        Link.Builder productsLinkBuilder = Link.fromUriBuilder(productsUriBuilder);
        Link productsLink = productsLinkBuilder.rel("products").build();

        //also we can add other meta-data by using: linkBuilder.param(..),
        // linkBuilder.type(..), linkBuilder.title(..)
        supplier.setLinks(Arrays.asList(selfLink, productsLink));
    }
}

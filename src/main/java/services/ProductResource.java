package services;

import beans.TopLevelProduct;
import databaseservice.MockService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.Arrays;
import java.util.List;

@Path("/suppliers/{supId:.*}/products")
public class ProductResource {

    MockService ms = MockService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@PathParam("supId") int supplierID, @Context UriInfo uriInfo) {
        List<TopLevelProduct> products = ms.getAllProductsOfSupplier(supplierID);
        products.forEach(p -> initLinks(p, uriInfo));

        GenericEntity<List<TopLevelProduct>> genericEntity = new GenericEntity<List<TopLevelProduct>>(products) {};

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();

        return Response.ok(genericEntity)
                .links(self).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProduct(@PathParam("supId") int supplierId, @PathParam("id") int id, @Context UriInfo uriInfo) {

        TopLevelProduct product = ms.findProduct(supplierId, id);

        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder())
                .rel("self").build();

        return Response.ok(product)
                .links(self).build();
    }


    private void initLinks(TopLevelProduct product, UriInfo uriInfo) {

        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();

        UriBuilder selfUriBuilder = uriBuilder.path(Integer.toString(product.getProductId()));
        Link.Builder selfLinkBuilder = Link.fromUriBuilder(selfUriBuilder);
        Link selfLink = selfLinkBuilder.rel("self").build();

//        UriBuilder fieldsUriBuilder = selfUriBuilder.path("labelFields");
//        Link.Builder fieldsLinkBuilder = Link.fromUriBuilder(fieldsUriBuilder);
//        Link fieldsLink = fieldsLinkBuilder.rel("fields").build();

        //also we can add other meta-data by using: linkBuilder.param(..),
        // linkBuilder.type(..), linkBuilder.title(..)
        product.setLinks(Arrays.asList(selfLink));
    }
}

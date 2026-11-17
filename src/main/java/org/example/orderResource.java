package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class orderResource {
    @Inject
    OrderService orderService;

    @GET
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") long id){
        return orderService.findById(id)
                .map(order -> Response.ok(order).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createOrder(createOrderRequest request){
        Order order = orderService.createOrder(request);
        return Response.status(Response.Status.CREATED)
                .entity(order)
                .build();
    }

}

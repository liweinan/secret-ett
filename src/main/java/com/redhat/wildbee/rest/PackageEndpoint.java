package com.redhat.wildbee.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.redhat.wildbee.model.Package;
import com.redhat.wildbee.model.User;

/**
 * 
 */
@Stateless
@Path("/packages")
public class PackageEndpoint
{
   @PersistenceContext(unitName = "wildbee-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Package entity) {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(PackageEndpoint.class)
              .path(String.valueOf(entity.getId())).build())
              .build();
   }

   @GET
   public String packages() {
      return "hello world";
   }
}

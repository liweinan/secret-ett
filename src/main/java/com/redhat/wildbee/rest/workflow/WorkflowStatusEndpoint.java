package com.redhat.wildbee.rest.workflow;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.redhat.wildbee.json.workflow.NewWorkflow;
import com.redhat.wildbee.model.StatusPackage;
import com.redhat.wildbee.model.workflow.WorkflowStatus;

/**
 *
 */
@Stateless
@Path("/workflowstatuses")
public class WorkflowStatusEndpoint
{
   @PersistenceContext(unitName = "wildbee-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(WorkflowStatus entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(WorkflowStatusEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      WorkflowStatus entity = em.find(WorkflowStatus.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<WorkflowStatus> findByIdQuery = em.createQuery("SELECT DISTINCT w FROM WorkflowStatus w LEFT JOIN FETCH w.workflow LEFT JOIN FETCH w.statusPackage LEFT JOIN FETCH w.nextStatusPackage WHERE w.id = :entityId ORDER BY w.id", WorkflowStatus.class);
      findByIdQuery.setParameter("entityId", id);
      WorkflowStatus entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<WorkflowStatus> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<WorkflowStatus> findAllQuery = em.createQuery("SELECT DISTINCT w FROM WorkflowStatus w LEFT JOIN FETCH w.workflow LEFT JOIN FETCH w.statusPackage LEFT JOIN FETCH w.nextStatusPackage ORDER BY w.id", WorkflowStatus.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<WorkflowStatus> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/update")
   @Consumes("application/json")
   @Produces("application/json")
   /*
    * TODO: add the actual logic for creating new workflows
    */
   public String update(NewWorkflow[] newWorkflows) {
      System.out.println("heya");
      System.out.println("heya");
      System.out.println("heya");
      System.out.println("heya");
      System.out.println(newWorkflows[0].getCurrentStatusId());
      System.out.println("heya");
      System.out.println("heya");
      StatusPackage entity = em.find(StatusPackage.class, newWorkflows[0].getCurrentStatusId());
      System.out.println(entity.getName());
      return "{\"no\": 5}";
   }
}

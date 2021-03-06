package com.redhat.wildbee.model.workflow;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import javax.persistence.ManyToOne;
import com.redhat.wildbee.model.StatusPackage;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class WorkflowStatus implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @ManyToOne(optional = false)
   private Workflow workflow;

   @ManyToOne(optional = false)
   private StatusPackage statusPackage;

   @ManyToOne
   private StatusPackage nextStatusPackage;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof WorkflowStatus))
      {
         return false;
      }
      WorkflowStatus other = (WorkflowStatus) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public Workflow getWorkflow()
   {
      return this.workflow;
   }

   public void setWorkflow(final Workflow workflow)
   {
      this.workflow = workflow;
   }

   public StatusPackage getStatusPackage()
   {
      return this.statusPackage;
   }

   public void setStatusPackage(final StatusPackage statusPackage)
   {
      this.statusPackage = statusPackage;
   }

   public StatusPackage getNextStatusPackage()
   {
      return this.nextStatusPackage;
   }

   public void setNextStatusPackage(final StatusPackage nextStatusPackage)
   {
      this.nextStatusPackage = nextStatusPackage;
   }
}
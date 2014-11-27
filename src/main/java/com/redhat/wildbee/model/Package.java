package com.redhat.wildbee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

import com.redhat.wildbee.model.User;
import com.redhat.wildbee.model.Release;
import javax.persistence.ManyToOne;

@Entity
@XmlRootElement
@Table(uniqueConstraints=
           @UniqueConstraint(columnNames = {"release_id", "name"}))
public class Package implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;

   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String name;

   @Column
   private String sourceURL;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.TIMESTAMP)
   private Date dateCreated;

   @Column
   @Temporal(TemporalType.TIMESTAMP)
   private Date dateUpdated;

   @ManyToOne(optional = true)
   private User assignee;

   @ManyToOne(optional = false)
   private User creator;

   @ManyToOne
   private Release release;

   public Long getId()
   {
      return this.id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Package))
      {
         return false;
      }
      Package other = (Package) obj;
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

   public String getSourceURL()
   {
      return sourceURL;
   }

   public void setSourceURL(String sourceURL)
   {
      this.sourceURL = sourceURL;
   }

   public Date getDateCreated()
   {
      return dateCreated;
   }

   public void setDateCreated(Date dateCreated)
   {
      this.dateCreated = dateCreated;
   }

   public Date getDateUpdated()
   {
      return dateUpdated;
   }

   public void setDateUpdated(Date dateUpdated)
   {
      this.dateUpdated = dateUpdated;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (name != null && !name.trim().isEmpty())
         result += ", name: " + name;
      if (sourceURL != null && !sourceURL.trim().isEmpty())
         result += ", sourceURL: " + sourceURL;
      if (dateCreated != null)
         result += ", dateCreated: " + dateCreated;
      if (dateUpdated != null)
         result += ", dateUpdated: " + dateUpdated;
      return result;
   }

   public User getAssignee()
   {
      return this.assignee;
   }

   public void setAssignee(final User assignee)
   {
      this.assignee = assignee;
   }

   public User getCreator()
   {
      return this.creator;
   }

   public void setCreator(final User creator)
   {
      this.creator = creator;
   }

   @PreUpdate
   @PrePersist
   private void setDateUpdatedAndCreated()
   {
      Date date = new Date();
      if (dateCreated == null)
      {
         dateCreated = date;
      }
      dateUpdated = date;
   }

   public Release getRelease()
   {
      return this.release;
   }

   public void setRelease(final Release release)
   {
      this.release = release;
   }
}
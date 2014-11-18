package com.redhat.wildbee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Override;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable
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

   @Column(nullable = false)
   private String email;

   @Column(nullable = false, updatable = false)
   @Temporal(TemporalType.DATE)
   private Date dateCreated;

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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof User))
      {
         return false;
      }
      User other = (User) obj;
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

   public Date getDateCreated()
   {
      return dateCreated;
   }

   public void setDateCreated(Date dateCreated)
   {
      this.dateCreated = dateCreated;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (name != null && !name.trim().isEmpty())
         result += "name: " + name;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      return result;
   }

   @PreUpdate @PrePersist
   private void setDateCreated()
   {
      Date date = new Date();
      if (dateCreated == null)
      {
         dateCreated = date;
      }
   }
}
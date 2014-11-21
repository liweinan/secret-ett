package com.redhat.wildbee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class StatusPackage implements Serializable
{
   public enum Tag {
      FINISHED, ORDINARY, IN_PROGRESS
   }

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
   @Enumerated(EnumType.STRING)
   private Tag tag;

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
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof StatusPackage))
      {
         return false;
      }
      StatusPackage other = (StatusPackage) obj;
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Tag getTag()
   {
      return tag;
   }

   public void setTag(Tag tag)
   {
      this.tag = tag;
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
      if (tag != null)
         result += ", flag: " + tag;
      return result;
   }
}
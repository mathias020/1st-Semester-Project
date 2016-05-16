package version5;

import java.io.Serializable;
/**
 * Person class for the Fairytale Library system.<br /><br />
 * @author <center><i>Group 3</i><br /> Szilard, Simos, Mathias & Jonas</center>
 *
 */
public class Person implements Serializable
{
   private String name;
   private String id;
   private String addr;
   private String email;
   private boolean isTeacher;
/**
 * Creates a Person object
 * @param name String containing the name of the person
 * @param id String containing the ID of the person
 * @param address String containing the address of the person
 * @param email String containing the email address of the person
 * @param isTeacher boolean containing if the person is a teacher
 */
   public Person(String name, String id, String address, String email,
         boolean isTeacher)
   {
      this.name = name;
      this.id = id;
      this.addr = address;
      this.email = email;
      this.isTeacher = isTeacher;
   }
/**
 * Gets the name of the person object
 * @return String containing the name of the person
 */
   public String getName()
   {
      return name;
   }
/**
 * Sets the name field of the Person
 * @param name a String containing the name of the Person
 */
   public void setName(String name)
   {
      this.name = name;
   }
/**
 * Gets the id of the Person
 * @return a String containing the ID of the Person
 */
   public String getId()
   {
      return id;
   }
/**
 * Sets the id field of the Person
 * @param id a String containing the ID of the Person
 */
   public void setId(String id)
   {
      this.id = id;
   }
/**
 * Gets the address of the Person
 * @return a String containing the address of the Person
 */
   public String getAddress()
   {
      return addr;
   }
/**
 * Sets the address field of the Person
 * @param addr a String containing the address of the Person
 */
   public void setAddress(String addr)
   {
      this.addr = addr;
   }
/**
 * Gets the email of the Person
 * @return a String containing the email address of the Person
 */
   public String getEmail()
   {
      return email;
   }
/**
 * Sets the email field of the Person
 * @param email a String containing the email address of the Person
 */
   public void setEmail(String email)
   {
      this.email = email;
   }
/**
 * Gets the isTeacher field of the Person
 * @return a boolean true if the Person is a teacher, false if the Person is not a teacher
 */
   public boolean isTeacher()
   {
      return isTeacher;
   }
/**
 * Sets the isTeacher field of the Person
 * @param isTeacher a boolean containing true if the Person is a teacher, false if not
 */
   public void setTeacher(boolean isTeacher)
   {
      this.isTeacher = isTeacher;
   }
/**
 * Makes an exact copy of the Person object
 * @return Person object containing a copy of the Person object
 */
   public Person copy()
   {
      return new Person(name,id,addr,email,isTeacher);
   }
 /**
  * Checks if the Object obj is equal to the Person object
  * @param obj an Object containing the object to compare with
  * @return a boolean true if they are the same, false if not  
  */
   public boolean equals(Object obj)
   {
      if (!(obj instanceof Person))
         return false;
      
      Person other = (Person)obj;
      
      return other.name.equals(name) && other.id.equals(id) && other.addr.equals(addr) && other.email.equals(email) && other.isTeacher == isTeacher;
   }
   /**
    * Returns all information about the Person object
    * @return a String containing all the information about the Person object
    */
   public String toString()
   {
      if (isTeacher)
         return "Name: " + name + "\tAddress: " + addr + "\tEmail: " + email
               + " is a teacher." + "\tID: " + id;
      else
         return "Name: " + name + "\tAddress: " + addr + "\tEmail: " + email
               + " is a student." + "\tID: " + id;
   }

}

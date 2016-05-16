package version5;

import java.io.Serializable;

/**
 * A class that stores all the items that are reserved.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */
public class Reserved implements Serializable
{
   private Person reserver;
   private Item item;
   private boolean notified;

   /**
    * Reserved class constructor.
    * 
    * @param personID
    *           a String holding the reserver's person ID
    * @param item
    *           the item that has been reserved
    * @param notified
    *          if the person has been notified if a copy of the item is available
    */
   public Reserved(Person reserver, Item item)
   {
      this.reserver = reserver;

      if (item instanceof Book)
         this.item = ((Book) item).copy();
      else if (item instanceof Article)
         this.item = ((Article) item).copy();
      else if (item instanceof CD)
         this.item = ((CD) item).copy();
      else if (item instanceof DVD)
         this.item = ((DVD) item).copy();
      else
         this.item = null;
      
      notified=false;
   }

   /**
    * Returns the reserver's person ID.
    * 
    * @return a String holding the reserver's person ID
    */
   public Person getReserver()
   {
      return reserver;
   }

   /**
    * Returns the item that is reserved.
    * 
    * @return an Item object
    */
   public Item getItem()
   {
      return item.copy();
   }

   /**
    * Returns the borrowed item's info.
    * 
    * @return a String holding the reserver's person ID and the item's info
    */
   public String toString()
   {
      return reserver + "\n" + item;
   }
   /**
    * Returns the reserved items notified status
    * @return a boolean stating if the person has been notified
    */
   public boolean isNotified()
   {
      return notified;
   }
   /**
    * Sets the reserved's notified field to the input in the parameter
    * @param notified if the person who reserved has been notified
    */
   public void setNotified(boolean notified)
   {
      this.notified=notified;
   }
}

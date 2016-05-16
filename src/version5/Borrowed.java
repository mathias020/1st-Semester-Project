package version5;

import java.io.Serializable;

/**
 * A class that stores all the items that are currently borrowed by someone.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */

public class Borrowed implements Serializable
{
   private Person borrower;
   private String uniqueID;
   private Item item;
   private MyDate returnDate;
   private boolean isEmailSent, isEmailSentAfter, isOverdue;

   /**
    * Borrowed class constructor.
    * 
    * @param personID
    *           a String holding the borrower's person ID
    * @param uniqueID
    *           a String holding the item's unique ID
    * @param item
    *           the item that has been borrowed
    */
   public Borrowed(Person borrower, String uniqueID, Item item,
         MyDate returnDate)
   {
      this.borrower = borrower;
      this.uniqueID = uniqueID;

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

      this.returnDate = returnDate;
   }

   /**
    * Returns the borrower.
    * 
    * @return a Person object
    */
   public Person getBorrower()
   {
      return this.borrower;
   }

   /**
    * Returns the item that is borrowed.
    * 
    * @return an Item object
    */
   public Item getItem()
   {
      return item.copy();
   }

   /**
    * Returns the borrowed item's return date.
    * 
    * @return a MyDate object holding he return date
    */
   public MyDate getReturnDate()
   {
      return returnDate;
   }

   /**
    * Returns the borrowed item's unique ID.
    * 
    * @return a String holding the item's unique ID
    */
   public String getUniqueID()
   {
      return uniqueID;
   }

   /**
    * Returns the borrowed item's isEmailSent status.
    * 
    * @return a boolean, true is email is sent, else false
    */
   public boolean getIsEmailSent()
   {
      return isEmailSent;
   }

   /**
    * Returns the borrowed item's isOverdue status.
    * 
    * @return a boolean, true is item is overdue, else false
    */
   public boolean getIsOverdue()
   {
      return isOverdue;
   }

   /**
    * Returns the borrowed item's isEmailSentAfter status.
    * 
    * @return a boolean, true is email is sent, else false
    */
   public boolean getIsEmailSentAfter()
   {
      return isEmailSentAfter;
   }

   /**
    * Changes the borrowed item's isEmailSent field.
    * 
    * @param isEmailSent
    *           a boolean
    */
   public void setIsEmailSent(boolean isEmailSent)
   {
      this.isEmailSent = isEmailSent;
   }

   /**
    * Changes the borrowed item's isEmailSentAfter field.
    * 
    * @param isEmailSentAfter
    *           a boolean
    */
   public void setIsEmailSentAfter(boolean isEmailSentAfter)
   {
      this.isEmailSentAfter = isEmailSentAfter;
   }

   /**
    * Changes the borrowed item's isOverdue field.
    * 
    * @param isOverdue
    *           a boolean
    */
   public void setIsOverdue(boolean isOverdue)
   {
      this.isOverdue = isOverdue;
   }
   
   public void setReturnDate(MyDate date)
   {
	   this.returnDate = date.copy();
   }

   /**
    * Returns the borrowed item's info.
    * 
    * @return a String holding the borrower's person ID, the item's info and the
    *         return date
    */
   public String toString()
   {
      return borrower + "\n" + item + "\n" + returnDate;
   }
}

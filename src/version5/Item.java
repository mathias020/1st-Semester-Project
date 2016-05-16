package version5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Parent class for all the objects inside the Fairytale Library system.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */
public abstract class Item implements Serializable
{
   private String title;
   private String type;
   private ArrayList<String> uniqueIDs;

   /**
    * Item class constructor. <br/>
    * Creates an Item object with the given title and type and generates a
    * unique ID for it.
    * 
    * @param title
    *           a String holding the item's title
    * @param type
    *           a String holding the item's type
    */
   public Item(String title, String type)
   {
      this.title = title;
      this.type = type;

      String tempID = title + "-" + type + "-"
            + ((int) (Math.random() * 10000));

      uniqueIDs = new ArrayList<String>();
      uniqueIDs.add(tempID);
   }

   /**
    * Returns the item's title.
    * 
    * @return a String holding the item's title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Returns the item's type.
    * 
    * @return a String holding the item's type
    */
   public String getType()
   {
      return type;
   }

   /**
    * Returns all the item's unique IDs.
    * 
    * @return a String array holding all the unique IDs of that Item
    */
   public String[] getUniqueIDs()
   {
      return uniqueIDs.toArray(new String[uniqueIDs.size()]);
   }

   /**
    * Returns the item's quantity, based on how many are available at the
    * moment.
    * 
    * @return an integer holding the item's quantity
    */
   public int getQuantity()
   {
      return uniqueIDs.size();
   }

   /**
    * Creates a Reserved object that holds the reserver's info.
    * 
    * @param reserver
    *           a Person object
    */
   public Reserved reserveIt(Person reserver)
   {
      return new Reserved(reserver, copy());
   }

   /**
    * Removes an Item's unique ID from the array list that contains all of its
    * unique IDs.
    * 
    * @param uniqueID
    *           a String holding an Item's unique ID
    */
   public void removeUniqueID(String uniqueID)
   {
      System.err.println("Removing uniqueID");
      uniqueIDs.remove(uniqueID);
   }

   /**
    * Adds an Item's unique ID to the array list that contains all of its unique
    * IDs.
    * 
    * @param uniqueID
    *           a String holding an Item's unique ID
    */
   public void addUniqueID(String uniqueID)
   {
      uniqueIDs.add(uniqueID);
   }

   /**
    * Checks if there are any available copies of the Item object in the Library
    * at the moment.
    * 
    * @return a boolean, true if there are, else false
    */
   public boolean isAvailable()
   {
      System.err.println(uniqueIDs.size());
      return (uniqueIDs.size() > 0);
   }

   /**
    * Creates a copy of an Item object.
    * 
    * @return an Item object
    */
   public abstract Item copy();

   /**
    * Lends out an Item to a Person and generates a Borrowed object.
    * 
    * @param borrower
    *           the Person that borrows the item
    * @param uniqueID
    *           a String with the item's unique ID
    * @return a Borrowed object with the Item's and borrower's info
    */
   public abstract Borrowed lendTo(Person borrower, String uniqueID);

   /**
    * Returns the item's info.
    * 
    * @return a String holding the item's title, type and quantity
    */
   public String toString()
   {
      return "Title: " + title + "\tType" + type + "\tAvailable quantity: "
            + uniqueIDs.size();
   }

}

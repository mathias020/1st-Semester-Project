package version5;

/**
 * DVD class extending the Item class in fairytale library.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 */

public class DVD extends Item
{
   private String director;
   private MyDate birthday;

   /**
    * @param title
    *           holds the information about title of the DVD
    * @param director
    *           holds the name of the director of the DVD
    */
   public DVD(String title, String director)
   {
      super(title, "DVD");
      this.director = director;
      this.birthday = MyDate.today();
   }

   public DVD(String title, String director, MyDate birthday)
   {
      super(title, "DVD");
      this.director = director;
      this.birthday = birthday.copy();
   }

   /**
    * Lends out a DVD to a Person and generates a Borrowed object.
    * 
    * @param borrower
    *           the Person that borrows the item
    * @param uniqueID
    *           a String with the item's unique ID
    * @return a Borrowed object with the DVD's and borrower's info
    */
   public Borrowed lendTo(Person borrower, String uniqueID)
   {
      Borrowed temp1 = null;

      if (isAvailable())
      {

         if (birthday.isOlder(365))
         {
            if (borrower.isTeacher())
            {
               MyDate temp = new MyDate();
               temp1 = new Borrowed(borrower, uniqueID, copy(),
                     temp.getReturnDate(180));
               removeUniqueID(uniqueID);
            }
            else
            {
               MyDate temp = new MyDate();
               temp1 = new Borrowed(borrower, uniqueID, copy(),
                     temp.getReturnDate(30));
               removeUniqueID(uniqueID);
            }
         }
         else
         {
            MyDate temp = new MyDate();
            temp1 = new Borrowed(borrower, uniqueID, copy(),
                  temp.getReturnDate(14));
            removeUniqueID(uniqueID);
         }
      }

      return temp1;
   }

   /**
    * Returns the DVD's director.
    * 
    * @return a String holding the DVD's director
    */
   public String getDirector()
   {
      return director;
   }

   /**
    * Changes the DVD's director.
    * 
    * @param director
    *           the new director
    */

   public void setDirector(String director)
   {
      this.director = director;
   }

   /**
    * Returns the DVD's info.
    * 
    * @return a String containing the DVD's director and title
    */
   public String toString()
   {
      return "Director: " + director + "\n" + super.toString();
   }

   /**
    * Return a copy of a DVD as an Item object.
    * 
    * @return an Item object
    */
   public Item copy()
   {
      return ((Item) new DVD(super.getTitle(), director));
   }
}

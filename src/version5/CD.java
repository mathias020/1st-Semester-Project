package version5;

/**
 * CD class, extending the Item class in Fairytale library system.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */

public class CD extends Item
{
   private String artist;
   private MyDate birthday;

   /**
    * CD class constructor.
    * 
    * @param title
    *           a String holding the CD's title
    * @param artist
    *           a String holding the CD's artist
    */
   public CD(String title, String artist)
   {
      super(title, "CD");
      this.artist = artist;
      this.birthday = MyDate.today();
   }

   /**
    * CD class constructor.
    * 
    * @param title
    *           a String holding the CD's title
    * @param artist
    *           a String holding the CD's artist
    * @param birthday
    *           a MyDate object holding the date the CD was acquired by the
    *           library
    */
   public CD(String title, String artist, MyDate birthday)
   {
      super(title, "CD");
      this.artist = artist;
      this.birthday = birthday.copy();
   }

   /**
    * Lends out a CD to a Person and generates a Borrowed object.
    * 
    * @param borrower
    *           the Person that borrows the item
    * @param uniqueID
    *           a String with the item's unique ID
    * @return a Borrowed object with the CD's and borrower's info
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
    * Returns the CD's artist.
    * 
    * @return a String holding the CD's artist
    */
   public String getArtist()
   {
      return artist;
   }

   /**
    * Changes the CD's artist.
    * 
    * @param author
    *           the new artist
    */
   public void setArtist(String artist)
   {
      this.artist = artist;
   }

   /**
    * Returns the CD's info.
    * 
    * @return a String containing the CD's artist and title
    */
   public String toString()
   {
      return "Artist: " + artist + "\n" + super.toString();
   }

   /**
    * Returns a copy of a CD as an Item object.
    * 
    * @return an Item object
    */
   public Item copy()
   {
      return ((Item) new CD(super.getTitle(), artist));
   }
}

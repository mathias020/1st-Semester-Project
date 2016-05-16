package version5;

/**
 * Article class, extending the Item class in Fairytale library system.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 */

public class Article extends Item
{
   private String author;

   /**
    * Article class constructor.
    * 
    * @param title
    *           a String holding the article's title
    * @param author
    *           a String holding the article's author
    */
   public Article(String title, String author)
   {
      super(title, "Article");
      this.author = author;
   }

   /**
    * Lends out an Article to a Person and generates a Borrowed object.
    * 
    * @param borrower
    *           the Person that borrows the item
    * @param uniqueID
    *           a String with the item's unique ID
    * @return a Borrowed object with the article's and borrower's info
    */
   public Borrowed lendTo(Person borrower, String uniqueID)
   {
      Borrowed temp1 = null;

      if (isAvailable())
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

      return temp1;
   }

   /**
    * Returns the Article's author.
    * 
    * @return a String holding the article's author
    */
   public String getAuthor()
   {
      return author;
   }

   /**
    * Changes the article's author.
    * 
    * @param author
    *           the new author
    */
   public void setAuthor(String author)
   {
      this.author = author;
   }

   /**
    * Returns the Article's info.
    * 
    * @return a String containing the article's author and title
    */
   public String toString()
   {
      return "Author: " + author + "\n" + super.toString();
   }

   /**
    * Returns a copy of an Article as an Item object.
    * 
    * @return an Item object
    */
   public Item copy()
   {
      return ((Item) new CD(super.getTitle(), author));
   }
}

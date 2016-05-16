package version5;

/**
 * Book class, extending the Item class in Fairytale library system.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */
public class Book extends Item
{
   private String author;
   private String ISBN;

   /**
    * Book class constructor.
    * 
    * @param title
    *           a String holding the book's title
    * @param ISBN
    *           a String holding the book's ISBN code
    * @param author
    *           a String holding the book's author
    */
   public Book(String title, String ISBN, String author)
   {
      super(title, "Book");

      this.ISBN = ISBN;
      this.author = author;
   }

   /**
    * Lends out a Book to a Person and generates a Borrowed object.
    * 
    * @param borrower
    *           the Person that borrows the item
    * @param uniqueID
    *           a String with the item's unique ID
    * @return a Borrowed object with the Book's and borrower's info
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
            super.removeUniqueID(uniqueID);
         }
         else
         {
            MyDate temp = new MyDate();
            temp1 = new Borrowed(borrower, uniqueID, copy(),
                  temp.getReturnDate(30));
            super.removeUniqueID(uniqueID);
         }
      }
            
      return temp1;
   }
   
   /**
    * Returns the book's ISBN code.
    * 
    * @return a String holding the book's ISBN
    */
   public String getISBN()
   {
      return ISBN;
   }

   /**
    * Returns the book's author.
    * 
    * @return a String holding the book's author
    */
   public String getAuthor()
   {
      return author;
   }

   /**
    * Changes the book's ISBN code.
    * 
    * @param ISBN
    *           the new ISBN code
    */
   public void setISBN(String iSBN)
   {
      ISBN = iSBN;
   }

   /**
    * Changes the book's author.
    * 
    * @param author
    *           the new author
    */
   public void setAuthor(String author)
   {
      this.author = author;
   }

   /**
    * Return a copy of a Book as an Item object.
    * 
    * @return an Item object
    */
   public Item copy()
   {
      return ((Item) new Book(super.getTitle(), ISBN, author));
   }

   /**
    * Returns the book's info.
    * 
    * @return a String containing the book's author, title and ISBN
    */
   public String toString()
   {
      return "Author: " + author + "\n" + super.toString() + "\nISBN: " + ISBN;
   }
}

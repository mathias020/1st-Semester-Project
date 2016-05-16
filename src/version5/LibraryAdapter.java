package version5;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * File management for the Fairytale Library system.
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 */
public class LibraryAdapter
{
   private String file;
   private FileIO[] fileIO = new FileIO[4];

   /* File IO Constants */
   /**
    * An integer representing the binary item file
    */
   static final int ITEMS_FILE = 0;
   /**
    * An integer representing the binary persons file
    */
   static final int PERSONS_FILE = 1;
   /**
    * An integer representing the binary borrowed file
    */
   static final int BORROWED_FILE = 2;
   /**
    * An integer representing the binary reserved file
    */
   static final int RESERVED_FILE = 3;

   /**
    * Creates a LibraryAdapter that connects with every file used by the
    * Library.
    */
   public LibraryAdapter()
   {

      System.out.println("(@LibraryAdapter) Using file: " + file);

      File jarFile = null;

      try
      {
         jarFile = new File(this.getClass().getProtectionDomain()
               .getCodeSource().getLocation().toURI());
      }
      catch (URISyntaxException e)
      {
         e.printStackTrace();
      }

      if (jarFile != null)
      {
         fileIO[0] = new FileIO(jarFile.getParentFile() + "\\" + "items.bin");
         fileIO[1] = new FileIO(jarFile.getParentFile() + "\\" + "persons.bin");
         fileIO[2] = new FileIO(jarFile.getParentFile() + "\\" + "borrowed.bin");
         fileIO[3] = new FileIO(jarFile.getParentFile() + "\\" + "reserved.bin");
      }
      else
      {
         JOptionPane.showMessageDialog(null,
               "FATAL ERROR: System could not get parent location.",
               "Fatal System Error", JOptionPane.ERROR_MESSAGE);
         System.exit(-1);
      }
   }

   /**
    * @return Returns the FileIO reference for easy method access
    */
   public FileIO getIO(int io)
   {
      return fileIO[io];
   }

   /**
    * Appends an item to the existing file
    * 
    * @param item
    *           holds the Item object that has to be appended to the file
    */
   public void appendToFile(Item item)
   {
      try
      {
         getIO(LibraryAdapter.ITEMS_FILE).fappend(item);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   /**
    * @return Returns all items to an Item array
    */
   public Item[] getAllItems()
   {
      ArrayList<Item> items = new ArrayList<Item>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {

            items.add((Item) iList[i]);
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Item[] itemList = items.toArray(new Item[items.size()]);

      return itemList;
   }

   /**
    * @return Returns all books to an Book array
    */
   public Book[] getAllBooks()
   {
      ArrayList<Book> books = new ArrayList<Book>();
      Object[] iList = null;

      try
      {
         iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof Book)
            {
               books.add((Book) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Book[] bookList = books.toArray(new Book[books.size()]);

      return bookList;
   }

   /**
    * Gets all the CD's from the CD binary file
    * 
    * @return CD array containing all CDs from the Item binary file
    */
   public CD[] getAllCDs()
   {
      ArrayList<CD> cds = new ArrayList<CD>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof CD)
            {
               cds.add((CD) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      CD[] cdList = cds.toArray(new CD[cds.size()]);

      return cdList;
   }

   /**
    * Gets all the DVD's from the Item binary file
    * 
    * @return DVD array containing all the DVDs from the Item binary file
    */
   public DVD[] getAllDVDs()
   {
      ArrayList<DVD> dvds = new ArrayList<DVD>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof DVD)
            {
               dvds.add((DVD) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      DVD[] dvdList = dvds.toArray(new DVD[dvds.size()]);

      return dvdList;
   }

   /**
    * @return Returns all Articles to an Article array
    */
   public Article[] getAllArticles()
   {
      ArrayList<Article> articles = new ArrayList<Article>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof Article)
            {
               articles.add((Article) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Article[] articleList = articles.toArray(new Article[articles.size()]);

      return articleList;
   }

   /**
    * Returns all the available items that are stored in the Item file
    * 
    * @return Returns all available items
    */
   public Item[] getAllAvailableItems()
   {
      ArrayList<Item> availableItems = new ArrayList<Item>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof Book)
            {
               Book other = (Book) iList[i];

               if (other.isAvailable())
                  availableItems.add((Book) iList[i]);
            }
            else if (iList[i] instanceof Article)
            {
               Article other = (Article) iList[i];

               if (other.isAvailable())
                  availableItems.add((Article) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Item[] itemList = availableItems.toArray(new Item[availableItems.size()]);

      return itemList;
   }

   /**
    * Gets all the available books from the Item binary file
    * 
    * @return Returns all available books
    */
   public Book[] getAllAvailableBooks()
   {
      ArrayList<Book> availableBooks = new ArrayList<Book>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof Book)
            {
               Book other = (Book) iList[i];

               if (other.isAvailable())
                  availableBooks.add((Book) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Book[] bookList = availableBooks.toArray(new Book[availableBooks.size()]);

      return bookList;
   }

   /**
    * Gets all the available CDs from the Item binary file
    * 
    * @return CD array containing all CDs from the Item binary file
    */
   public CD[] getAllAvailableCDs()
   {
      ArrayList<CD> availableCDs = new ArrayList<CD>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof CD)
            {
               CD other = (CD) iList[i];

               if (other.isAvailable())
                  availableCDs.add((CD) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      CD[] cdList = availableCDs.toArray(new CD[availableCDs.size()]);

      return cdList;
   }

   /**
    * Gets all the available DVDs from the Item binary file
    * 
    * @return DVD array containing all the available DVDs from the Item binary
    *         file
    */
   public DVD[] getAllAvailableDVDs()
   {
      ArrayList<DVD> availableDVDs = new ArrayList<DVD>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof DVD)
            {
               DVD other = (DVD) iList[i];

               if (other.isAvailable())
                  availableDVDs.add((DVD) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      DVD[] dvdList = availableDVDs.toArray(new DVD[availableDVDs.size()]);

      return dvdList;
   }

   /**
    * Gets all the available articles from the Item binary file
    * 
    * @return Returns all available articles
    */
   public Article[] getAllAvailableArticles()
   {
      ArrayList<Article> availableArticles = new ArrayList<Article>();

      try
      {
         Object[] iList = getIO(LibraryAdapter.ITEMS_FILE).freadArray();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i] instanceof Article)
            {
               Article other = (Article) iList[i];

               if (other.isAvailable())
                  availableArticles.add((Article) iList[i]);
            }
         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Article[] articleList = availableArticles
            .toArray(new Article[availableArticles.size()]);

      return articleList;
   }

   /**
    * Returns all items with the given title.
    * 
    * @param title
    *           Title of the item that is being searched
    * @return Returns an array of items with a title matching the one sent to
    *         the method.
    */
   public Item[] getItems(String title)
   {
      ArrayList<Item> selectedItems = new ArrayList<Item>();

      try
      {
         Item[] iList = getAllItems();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i].getTitle().toUpperCase().indexOf(title.toUpperCase()) != -1)
            {
               selectedItems.add(iList[i]);
            }

         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      Item[] itemList = selectedItems.toArray(new Item[selectedItems.size()]);
      return itemList;
   }

   /**
    * Gets the item by title
    * 
    * @param title
    *           Title of the item that is being looked for
    * @return Returns an Item object holding the information of the found item
    */
   public Item getItem(String title)
   {
      Item selected = null;

      try
      {
         Item[] iList = getAllItems();

         for (int i = 0; i < iList.length; i++)
         {
            if (iList[i].getTitle().equals(title))
            {
               selected = iList[i];
               break;
            }

         }
      }
      catch (Exception e)
      {
         System.err.println("Could not read array from file");
      }

      return selected;
   }

   /**
    * Saves an array of Person to the file by overwriting the file
    * 
    * @param persons
    *           to be stored in the person file
    */
   public void savePerson(Person[] persons)
   {
      try
      {
         getIO(LibraryAdapter.PERSONS_FILE).fwrite(persons);
      }
      catch (Exception e)
      {
         System.err.println("Could not write to file.");
      }
   }

   /**
    * Saves an array of Borrowed to the file by overwriting the file
    * 
    * @param borrowed
    *           objects to be stored in the borrowed file.
    */
   public void saveBorrowed(Borrowed[] borrowed)
   {
      try
      {
         getIO(LibraryAdapter.BORROWED_FILE).fwrite(borrowed);
      }
      catch (Exception e)
      {
         System.err.println("Could not write to file");
      }
   }

   /**
    * Saves an array of Reserved to the file by overwriting the file
    * 
    * @param reserved
    *           objects to be stored in the reserved file
    */
   public void saveReserved(Reserved[] reserved)
   {
      try
      {
         getIO(LibraryAdapter.RESERVED_FILE).fwrite(reserved);
      }
      catch (Exception e)
      {
         System.err.println("Could not write to file");
      }
   }

   /**
    * Saves an array of items to the file by overwriting the file
    * 
    * @param items
    *           objects to be stored in the reserved file
    */
   public void saveItems(Item[] items)
   {
      try
      {
         getIO(LibraryAdapter.ITEMS_FILE).fwrite(items);
      }
      catch (Exception e)
      {
         System.err.println("(@Items) Could not save item file");
      }
   }

   /**
    * Gets all persons from the Person binary file
    * 
    * @return an array of persons containing all persons in the Person binary
    *         file
    */
   public Person[] getAllPersons()
   {
      ArrayList<Person> list = new ArrayList<Person>();

      Object[] persons = null;

      try
      {
         persons = getIO(LibraryAdapter.PERSONS_FILE).freadArray();
      }
      catch (Exception e)
      {
         System.err.println("(@Persons) Could not read array from file.");
      }

      if (persons != null)
      {
         for (int i = 0; i < persons.length; i++)
         {
            list.add((Person) persons[i]);
         }

         return list.toArray(new Person[list.size()]);
      }
      else
         return null;
   }

   /**
    * Gets all borrowed objects from the Borrowed binary file
    * 
    * @return an array of Borrowed objects containing all borrow objects from
    *         the Borrowed binary file
    */
   public Borrowed[] getAllBorrowed()
   {
      ArrayList<Borrowed> list = new ArrayList<Borrowed>();

      Object[] borrowed = null;

      try
      {
         borrowed = getIO(LibraryAdapter.BORROWED_FILE).freadArray();
      }
      catch (Exception e)
      {
         System.err.println("(@Borrowed) Could not read array from file");
      }

      if (borrowed != null)
      {
         for (int i = 0; i < borrowed.length; i++)
         {
            list.add((Borrowed) borrowed[i]);
         }

         return list.toArray(new Borrowed[list.size()]);
      }
      else
         return null;
   }

   /**
    * Gets all the reserved objects from the Reserved binary file
    * 
    * @return an array of Reserved containing all the reserved objects from the
    *         Reserved binary file
    */
   public Reserved[] getAllReserved()
   {
      ArrayList<Reserved> list = new ArrayList<Reserved>();

      Object[] reserved = null;

      try
      {
         reserved = getIO(LibraryAdapter.RESERVED_FILE).freadArray();
      }
      catch (Exception e)
      {
         System.err.println("(@Reserved) Could not read array from file");
      }

      if (reserved != null)
      {
         for (int i = 0; i < reserved.length; i++)
         {
            list.add((Reserved) reserved[i]);
         }

         return list.toArray(new Reserved[list.size()]);
      }
      else
         return null;
   }
}

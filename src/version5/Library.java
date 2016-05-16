package version5;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Library class for the Fairytale Library system.<br/>
 * 
 * @author <center><i>Group 3</i><br />
 *         Szilard, Simos, Mathias & Jonas</center>
 * @version 1.0
 */
public class Library
{
   private LibraryAdapter libraryAdapter;

   /**
    * Library class constructor.
    */
   public Library()
   {
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
         libraryAdapter = new LibraryAdapter();
      else
      {
         JOptionPane.showMessageDialog(null,
               "FATAL ERROR: System could not get parent location.",
               "Fatal System Error", JOptionPane.ERROR_MESSAGE);
         System.exit(-1);
      }

      // initializeData(); // Remove this part later !!!!
   }

   /**
    * Gets all the items that have 4 days or less till the return date
    * 
    * @return returns all items that have 4 days or less till the return date
    */
   public Borrowed[] isBefore()
   {
      ArrayList<Borrowed> list = new ArrayList<Borrowed>();

      Borrowed[] temp = getAdapter().getAllBorrowed();

      if (temp != null)
      {
         for (int i = 0; i < temp.length; i++)
         {
            if (temp[i].getReturnDate() != null)
            {
               if (temp[i].getReturnDate().isBefore()
                     && temp[i].getIsEmailSent() == false)
                  list.add(temp[i]);
            }
         }
      }
      return list.toArray(new Borrowed[list.size()]);
   }

   public Borrowed[] getBorrowedBy(String personID)
   {
      Borrowed[] allBorrowed = getAdapter().getAllBorrowed();

      ArrayList<Borrowed> borrowed = new ArrayList<Borrowed>();

      if (allBorrowed != null)
      {
         for (int i = 0; i < allBorrowed.length; i++)
         {
            if (allBorrowed[i].getBorrower().getId().equals(personID))
            {
               borrowed.add(allBorrowed[i]);
            }
         }
      }

      return borrowed.toArray(new Borrowed[borrowed.size()]);
   }

   public Reserved[] getReservedBy(String personID)
   {
      Reserved[] allReserved = getAdapter().getAllReserved();

      if (allReserved == null)
         return null;

      ArrayList<Reserved> reserved = new ArrayList<Reserved>();

      for (int i = 0; i < allReserved.length; i++)
      {
         if (allReserved[i].getReserver().getId().equals(personID))
         {
            reserved.add(allReserved[i]);
         }
      }

      return reserved.toArray(new Reserved[reserved.size()]);
   }

   /**
    * Gets all the items that are exactly two days overdue.
    * 
    * @return returns all the items that are exactly two days overdue and sets
    *         the overdue field to true
    */
   public Borrowed[] isPast()
   {
      ArrayList<Borrowed> list = new ArrayList<Borrowed>();

      Borrowed[] temp = getAdapter().getAllBorrowed();

      if (temp != null)
      {
         for (int i = 0; i < temp.length; i++)
         {
            if (temp[i].getReturnDate() != null)
            {
               if (temp[i].getReturnDate().isPast()
                     && temp[i].getIsEmailSentAfter() == false
                     || temp[i].getIsOverdue()
                     && temp[i].getIsEmailSentAfter() == false)
               {
                  temp[i].setIsOverdue(true);
                  list.add(temp[i]);
               }
            }
         }
      }

      try
      {
         getAdapter().saveBorrowed(temp);

      }
      catch (Exception e)
      {
         System.err.println("Could not write to file.");
      }

      return list.toArray(new Borrowed[list.size()]);
   }

   /**
    * Removes an items from the isBefore list, once an email is sent to the
    * borrower
    * 
    * @param title
    *           the Item's title
    */
   public void removeFromIsBefore(String title)
   {
      Borrowed[] itemArray = getAdapter().getAllBorrowed();

      for (int i = 0; i < itemArray.length; i++)
      {
         if (itemArray[i].getItem().getTitle().equalsIgnoreCase(title)
               && itemArray[i].getReturnDate().isBefore())
            itemArray[i].setIsEmailSent(true);
      }

      try
      {
         getAdapter().saveBorrowed(itemArray);

      }
      catch (Exception e)
      {
         System.err.println("Could not write to file.");
      }
   }

   /**
    * Removes an items from the isPast list, once an email is sent to the
    * borrower
    * 
    * @param title
    *           the Item's title
    */
   public void removeFromIsPast(String title)
   {
      Borrowed[] itemArray = getAdapter().getAllBorrowed();
      for (int i = 0; i < itemArray.length; i++)
      {
         if (itemArray[i].getItem().getTitle().equalsIgnoreCase(title)
               && itemArray[i].getIsOverdue() == true)
            itemArray[i].setIsEmailSentAfter(true);
      }

      try
      {
         getAdapter().saveBorrowed(itemArray);
      }
      catch (Exception e)
      {
         System.err.println("Could not write to file.");
      }
   }

   /**
    * Returns an array with all the items that are overdue (their return date
    * has passed)
    * 
    * @return itemList an Item array with the overdue items
    */
   public Borrowed[] getAllOverdueItems()
   {
      ArrayList<Borrowed> iList = new ArrayList<Borrowed>();

      Borrowed[] items = getAdapter().getAllBorrowed();
      for (int i = 0; i < items.length; i++)
      {
         Borrowed temp = (Borrowed) items[i];

         if (temp.getIsOverdue() == true)
         {
            iList.add(items[i]);
         }
      }

      Borrowed[] itemList = iList.toArray(new Borrowed[iList.size()]);

      return itemList;
   }

   public LibraryAdapter getAdapter()
   {
      return libraryAdapter;
   }

   /**
    * Adds a item to the library
    * 
    * @param item
    *           holds the item object that has to be put into the file
    */
   public void addItem(Item item)
   {
      getAdapter().appendToFile(item);
   }

   /**
    * Adds a Book to the library
    * 
    * @param book
    *           holds the Book object that has to be put into the file
    */
   public String addBook(Book book)
   {
      Item[] tempItems = getAdapter().getAllItems();
      String[] testItemsUniques = null;

      for (int i = 0; i < tempItems.length; i++)
      {
         if (tempItems[i].getTitle().equals(book.getTitle())
               && tempItems[i].getType().equals("Book"))
         {
            testItemsUniques = tempItems[i].getUniqueIDs();
         }
      }

      if (testItemsUniques == null)
      {
         addItem(book);
         String[] uniques = book.getUniqueIDs();
         return uniques[0];
      }
      else
      {
         String newUniqueID = "WRONG ID";

         do
         {
            newUniqueID = book.getTitle() + "-Book-"
                  + ((int) (Math.random() * 10000));
         }
         while (checkUniqueIDExists(book, newUniqueID));

         for (int i = 0; i < tempItems.length; i++)
         {
            if (tempItems[i].getTitle().equals(book.getTitle())
                  && tempItems[i].getType().equals("Book"))
               tempItems[i].addUniqueID(newUniqueID);
         }

         getAdapter().saveItems(tempItems);
         return newUniqueID;
      }
   }

   /**
    * Adds an Article to the library
    * 
    * @param article
    *           holds the Article object that has to be put into the file
    */
   public String addArticle(Article article)
   {
      Item[] tempItems = getAdapter().getAllItems();
      String[] testItemsUniques = null;

      for (int i = 0; i < tempItems.length; i++)
      {
         if (tempItems[i].getTitle().equals(article.getTitle())
               && tempItems[i].getType().equals("Article"))
         {
            testItemsUniques = tempItems[i].getUniqueIDs();
         }
      }

      if (testItemsUniques == null)
      {
         addItem(article);
         String[] uniques = article.getUniqueIDs();
         return uniques[0];
      }
      else
      {
         String newUniqueID = "WRONG ID";

         do
         {
            newUniqueID = article.getTitle() + "-Article-"
                  + ((int) (Math.random() * 10000));
         }
         while (checkUniqueIDExists(article, newUniqueID));

         for (int i = 0; i < tempItems.length; i++)
         {
            if (tempItems[i].getTitle().equals(article.getTitle())
                  && tempItems[i].getType().equals("Article"))
               tempItems[i].addUniqueID(newUniqueID);
         }

         getAdapter().saveItems(tempItems);
         return newUniqueID;
      }
   }

   /**
    * Adds a CD to the library
    * 
    * @param cd
    *           holds the CD object that has to be put into the file
    */
   public String addCD(CD cd)
   {
      Item[] tempItems = getAdapter().getAllItems();
      String[] testItemsUniques = null;

      for (int i = 0; i < tempItems.length; i++)
      {
         if (tempItems[i].getTitle().equals(cd.getTitle())
               && tempItems[i].getType().equals("CD"))
         {
            testItemsUniques = tempItems[i].getUniqueIDs();
         }
      }

      if (testItemsUniques == null)
      {
         addItem(cd);
         String[] uniques = cd.getUniqueIDs();
         return uniques[0];
      }
      else
      {
         String newUniqueID = "WRONG ID";

         do
         {
            newUniqueID = cd.getTitle() + "-CD-"
                  + ((int) (Math.random() * 10000));
         }
         while (checkUniqueIDExists(cd, newUniqueID));

         for (int i = 0; i < tempItems.length; i++)
         {
            if (tempItems[i].getTitle().equals(cd.getTitle())
                  && tempItems[i].getType().equals("CD"))
               tempItems[i].addUniqueID(newUniqueID);
         }

         getAdapter().saveItems(tempItems);
         return newUniqueID;
      }
   }

   /**
    * Adds an DVD to the library
    * 
    * @param dvd
    *           holds the DVD object that has to be put into the file
    */
   public String addDVD(DVD dvd)
   {
      Item[] tempItems = getAdapter().getAllItems();
      String[] testItemsUniques = null;

      for (int i = 0; i < tempItems.length; i++)
      {
         if (tempItems[i].getTitle().equals(dvd.getTitle())
               && tempItems[i].getType().equals("DVD"))
         {
            testItemsUniques = tempItems[i].getUniqueIDs();
         }
      }

      if (testItemsUniques == null)
      {
         addItem(dvd);
         String[] uniques = dvd.getUniqueIDs();
         return uniques[0];
      }
      else
      {
         String newUniqueID = "WRONG ID";

         do
         {
            newUniqueID = dvd.getTitle() + "-DVD-"
                  + ((int) (Math.random() * 10000));
         }
         while (checkUniqueIDExists(dvd, newUniqueID));

         for (int i = 0; i < tempItems.length; i++)
         {
            if (tempItems[i].getTitle().equals(dvd.getTitle())
                  && tempItems[i].getType().equals("DVD"))
            {
               tempItems[i].addUniqueID(newUniqueID);
            }
         }

         getAdapter().saveItems(tempItems);
         return newUniqueID;
      }
   }

   /**
    * Checks if the id already exists in a Item object in the file
    * 
    * @param item
    *           The item that should be checked
    * @param uniqueID
    *           the ID that is being checked if it exists within the item
    * @return returns true if the unique id already exists, else returns false
    */
   public boolean checkUniqueIDExists(Item item, String uniqueID)
   {
      boolean IDExists = false;

      for (int i = 0; i < item.getUniqueIDs().length; i++)
      {
         if (item.getUniqueIDs()[i].equals(uniqueID))
         {
            IDExists = true;
            break;
         }
      }

      if (!IDExists)
      {
         Borrowed[] borrowedList = getAdapter().getAllBorrowed();

         if (borrowedList != null)
         {
            for (int i = 0; i < borrowedList.length; i++)
            {
               if (borrowedList[i].getUniqueID().equals(uniqueID))
               {
                  IDExists = true;
                  break;
               }
            }
         }
      }

      return IDExists;
   }

   /**
    * Removes an Item with the id from the file storage/system.
    * 
    * @param uniqueID
    *           is the unique ID of the Item that should be removed
    */
   public boolean removeItem(String uniqueID)
   {
      Item[] allItems = getAdapter().getAllItems();
      boolean success = false;
      ArrayList<Item> newItems = new ArrayList<Item>();

      String[] info = uniqueID.split("-");

      for (int i = 0; i < allItems.length; i++)
      {
         if (allItems[i].getTitle().equals(info[0])
               && allItems[i].getType().equals(info[1]))
         {
            for (int k = 0; k < allItems[i].getUniqueIDs().length; k++)
            {
               if (allItems[i].getUniqueIDs()[k].equals(uniqueID))
               {
                  allItems[i].removeUniqueID(uniqueID);
                  success = true;
               }
            }

         }

         if (allItems[i].getUniqueIDs().length > 0)
         {
            newItems.add(allItems[i]);
         }
      }

      try
      {
         getAdapter().getIO(LibraryAdapter.ITEMS_FILE).fwrite(
               newItems.toArray());
      }
      catch (Exception e)
      {
         System.err.println("Could not save changes.");
      }
      return success;
   }

   /**
    * Lets a person borrow an item from the library.
    * 
    * @param uniqueID
    *           a String holding the item's unique ID
    * @param borrower
    *           a Person object holding the borrower's info
    * @param item
    *           the Item object to be borrowed
    */
   public void borrowItem(String uniqueID, Person borrower, Item item)
   {
      Borrowed temp = null;
      Person[] reservers = getItemReservers(item.getTitle(), item.getType());

      if (reservers.length > 0 && reservers.length >= item.getQuantity())
      {
         if (reservers[0].equals(borrower))
         {
            temp = item.lendTo(borrower, uniqueID);

            if (temp != null)
            {
               cancelReservation(reservers[0].getId(), item.getTitle(),
                     item.getType());
            }
         }
         else
         {
            int count = -1;

            for (int i = 0; i < reservers.length; i++)
            {
               if (borrower.equals(reservers[i]))
               {
                  count = i;
               }
            }
            if (count != -1)
            {
               JOptionPane.showMessageDialog(null, "You are currently number: "
                     + (count + 1) + " in the que");
            }

            else
            {
               int choice = JOptionPane.showConfirmDialog(null,
                     "There are currently " + reservers.length
                           + " in the queue. Do you want to reserve the item?");

               if (!(choice == JOptionPane.NO_OPTION
                     || choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION))
               {
                  Reserved reserved = item.reserveIt(borrower);

                  try
                  {
                     getAdapter().getIO(LibraryAdapter.RESERVED_FILE).fappend(
                           reserved);
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                  }
               }

            }
         }

      }
      else
      {
         boolean idFound = false;

         String[] uniqueIDs = item.getUniqueIDs();

         for (int i = 0; i < uniqueIDs.length; i++)
         {
            if (uniqueIDs[i].equals(uniqueID))
            {
               idFound = true;
            }
         }

         if (idFound)
            temp = item.lendTo(borrower, uniqueID);
         else
            JOptionPane
                  .showMessageDialog(
                        null,
                        "That item is already borrowed, you need to return it before lending it out again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
      }

      if (temp != null)
      {
         Item[] allItems = getAdapter().getAllItems();
         String[] uniqueIDs = null;

         for (int i = 0; i < allItems.length; i++)
         {
            if (allItems[i].getTitle().equals(item.getTitle())
                  && allItems[i].getType().equals(item.getType()))
            {
               uniqueIDs = allItems[i].getUniqueIDs();

               boolean uniqueIDfound = false;

               for (int k = 0; k < uniqueIDs.length; k++)
               {
                  if (uniqueIDs[k].equals(uniqueID))
                  {
                     uniqueIDfound = true;
                     break;
                  }
               }

               if (uniqueIDfound)
               {
                  allItems[i].removeUniqueID(uniqueID);
                  JOptionPane
                        .showMessageDialog(
                              null,
                              "The item has been lended out to "
                                    + borrower.getName(), "Borrow an Item",
                              JOptionPane.INFORMATION_MESSAGE);
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Item is already registered as borrowed.",
                        "Item Unavailable", JOptionPane.ERROR_MESSAGE);
               }
            }
         }

         try
         {
            if (getAdapter().getAllBorrowed() != null)
               getAdapter().getIO(LibraryAdapter.BORROWED_FILE).fappend(temp);
            else
               getAdapter().getIO(LibraryAdapter.BORROWED_FILE).fwrite(temp);

            getAdapter().getIO(LibraryAdapter.ITEMS_FILE).fwrite(allItems);
         }
         catch (Exception e)
         {
            System.err.println("Could not append to file");
         }
      }
      else if (reservers.length == 0 && temp == null && item.getQuantity() == 0)
      {
         int choice = JOptionPane.showConfirmDialog(null,
               "There are currently " + reservers.length
                     + " in the queue. Do you want to reserve the item?");

         if (!(choice == JOptionPane.NO_OPTION
               || choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION))
         {
            // Reserved reserved=item.reserveIt(borrower);

            Reserved reserved = new Reserved(borrower, item);

            try
            {
               getAdapter().getIO(LibraryAdapter.RESERVED_FILE).fappend(
                     reserved);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
   }

   /**
    * Lets a borrower return an item to the library
    * 
    * @param uniqueID
    *           the item's unique ID
    */
   public void returnItem(String uniqueID)
   {
      String[] info = uniqueID.split("-");

      Item[] allItems = getAdapter().getAllItems();

      for (int i = 0; i < allItems.length; i++)
      {
         if (allItems[i].getTitle().equals(info[0])
               && allItems[i].getType().equals(info[1]))
         {
            System.err.println("Found Item to return");
            allItems[i].addUniqueID(uniqueID);

            System.err.println("Unique ID has been added back");
            Borrowed[] borrows = getAdapter().getAllBorrowed();

            ArrayList<Borrowed> newBorrowed = new ArrayList<Borrowed>();

            System.err.println("Runs through borrow.bin ");

            for (int k = 0; k < borrows.length; k++)
            {
               if (!borrows[k].getUniqueID().equals(uniqueID))
               {
                  newBorrowed.add(borrows[k]);
               }
            }

            System.err.println("Saving borrowed.bin");
            getAdapter().saveBorrowed(
                  newBorrowed.toArray(new Borrowed[newBorrowed.size()]));

            break;
         }
      }
      System.err.println("Saving items");
      getAdapter().saveItems(allItems);
   }

   /**
    * Saves a reserved item to the reserved items file.
    * 
    * @param reserved
    *           the item that was reserved
    */
   public void reserveItem(Reserved reserved)
   {
      try
      {
         getAdapter().getIO(LibraryAdapter.RESERVED_FILE).fappend(reserved);
      }
      catch (Exception e)
      {
         System.err.println("Could not append to file");
      }
   }

   /**
    * Returns an item's borrowers.
    * 
    * @param title
    *           the item's title
    * @param type
    *           the item's type
    * @return a Person array with all the borrowers
    */
   public Person[] getItemBorrowers(String title, String type)
   {
      Borrowed[] allBorrows = getAdapter().getAllBorrowed();

      ArrayList<Person> borrowers = new ArrayList<Person>();

      for (int i = 0; i < allBorrows.length; i++)
      {
         if (allBorrows[i].getItem().getTitle().equals(title)
               && allBorrows[i].getItem().getType().equals(type))
         {
            borrowers.add(allBorrows[i].getBorrower());
         }
      }

      return borrowers.toArray(new Person[borrowers.size()]);
   }

   /**
    * Returns the items a borrower has borrowed.
    * 
    * @param personID
    *           the borrower's ID
    * @return an Item array with all the items
    */
   public Item[] getItemsBorrowedByPerson(String personID)
   {
      Borrowed[] allBorrows = getAdapter().getAllBorrowed();

      ArrayList<Item> borrowedItems = new ArrayList<Item>();

      for (int i = 0; i < allBorrows.length; i++)
      {
         if (allBorrows[i].getBorrower().getId().equals(personID))
         {
            borrowedItems.add(allBorrows[i].getItem());
         }
      }

      return borrowedItems.toArray(new Item[borrowedItems.size()]);
   }

   /**
    * Returns the borrowers that have reserved an item.
    * 
    * @param title
    *           the item's title
    * @param type
    *           the item's type
    * @return a Person array with all the borrowers
    */
   public Person[] getItemReservers(String title, String type)
   {
      Reserved[] allReservations = getAdapter().getAllReserved();

      ArrayList<Person> reservers = new ArrayList<Person>();
      if (allReservations != null)
      {

         for (int i = 0; i < allReservations.length; i++)
         {
            if (allReservations[i].getItem().getTitle().equals(title)
                  && allReservations[i].getItem().getType().equals(type))
            {
               reservers.add(allReservations[i].getReserver());
            }
         }

      }

      return reservers.toArray(new Person[reservers.size()]);
   }

   /**
    * Returns the borrowers that have reserved an item.
    * @param title
    *           the item's title
    * @param type
    *           the item's type
    * @return a Reserved array with all the borrowers
    */
   public Reserved[] getItemReserverList(String title, String type)
   {
      Reserved[] allReservations = getAdapter().getAllReserved();

      ArrayList<Reserved> reservers = new ArrayList<Reserved>();
      if (allReservations != null)
      {

         for (int i = 0; i < allReservations.length; i++)
         {
            if (allReservations[i].getItem().getTitle().equals(title)
                  && allReservations[i].getItem().getType().equals(type))
            {
               reservers.add(allReservations[i]);
            }
         }

      }

      return reservers.toArray(new Reserved[reservers.size()]);
   }

   /**
    * Returns the items a borrower has reserved.
    * 
    * @param personID
    *           the borrower's ID
    * @return an Item array with all the items
    */
   public Item[] getItemsReservedByPerson(String personID)
   {
      Reserved[] allReservations = getAdapter().getAllReserved();

      ArrayList<Item> items = new ArrayList<Item>();

      for (int i = 0; i < allReservations.length; i++)
      {
         if (allReservations[i].getReserver().getId().equals(personID))
         {
            items.add(allReservations[i].getItem());
         }
      }

      return items.toArray(new Item[items.size()]);
   }

   /**
    * Removes a borrower's reservation on an item.
    * 
    * @param personID
    *           a String holding the borrower's ID
    * @param title
    *           a String holding the item's title
    * @param type
    *           a String holding the item's type
    */
   public void cancelReservation(String personID, String title, String type)
   {
      Reserved[] allReservations = getAdapter().getAllReserved();

      ArrayList<Reserved> temp = new ArrayList<Reserved>();
      if (allReservations != null)
      {
         for (int i = 0; i < allReservations.length; i++)
         {
            if (!(allReservations[i].getReserver().getId().equals(personID)
                  && allReservations[i].getItem().getTitle().equals(title) && allReservations[i]
                  .getItem().getType().equals(type)))
            {
               temp.add(allReservations[i]);
            }
         }
      }

      getAdapter().saveReserved(temp.toArray(new Reserved[temp.size()]));
   }

   /**
    * Saves a Person object to the Persons file.
    * 
    * @param person
    *           the Person object to be stored
    */
   public void addPerson(Person person)
   {
      try
      {
         getAdapter().getIO(LibraryAdapter.PERSONS_FILE).fappend(person);
      }
      catch (Exception e)
      {
         System.err.println("Could not append to file");
      }
   }

   /**
    * Removes a Person object from the Persons file.
    * 
    * @param person
    *           the Person object to be removed
    */
   public void removePerson(String personID)
   {
      Person[] allPersons = getAdapter().getAllPersons();

      ArrayList<Person> temp = new ArrayList<Person>();

      for (int i = 0; i < allPersons.length; i++)
      {
         if (!allPersons[i].getId().equals(personID))
         {
            temp.add(allPersons[i]);
         }
      }

      getAdapter().savePerson(temp.toArray(new Person[temp.size()]));
   }

   /**
    * Returns a Person object from the Persons file.
    * 
    * @param person
    *           the Person object to be found
    * @return a Person object
    */
   public Person getPerson(String personID)
   {
      Person[] allPersons = getAdapter().getAllPersons();

      Person selected = null;

      if (allPersons != null)
      {
         for (int i = 0; i < allPersons.length; i++)
         {
            if (allPersons[i].getId().equals(personID))
            {
               selected = allPersons[i];
               break;
            }
         }
      }
      return selected;
   }

   /**
    * Gets all the items that has been reserved and are available where the
    * reserver has not been notified
    * 
    * @return Reserved array that holds all Reserved where the item is available
    *         and the reserver has not been notified
    */
   public Reserved[] getAllAvailableReserved()
   {
      Reserved[] allReserves = getAdapter().getAllReserved();
      Item[] allItems = getAdapter().getAllItems();
      Reserved[] itemReservers = null;

      ArrayList<Reserved> temp = new ArrayList<Reserved>();
      if (allItems != null)
      {
         for (int i = 0; i < allItems.length; i++)
         {
            if (allItems[i].isAvailable())
            {
               System.err.println("(@" + allItems[i].getTitle()
                     + ") Availability true");
               itemReservers = getItemReserverList(allItems[i].getTitle(),
                     allItems[i].getType());

               if (itemReservers != null && allReserves != null)
               {
                  int count = 0;
                  for (int k = 0; k < itemReservers.length; k++)
                  {
                     if (!itemReservers[k].isNotified())
                     {

                        if (count < allItems[i].getQuantity())
                        {
                           temp.add(itemReservers[k]);
                           count++;
                        }

                     }
                  }
               }
            }
         }
      }

      System.err.println("Temp size: " + temp.size());
      return temp.toArray(new Reserved[temp.size()]);
   }

   /**
    * Removes the item from the available list where email notifications has to
    * be sent to, by setting the notified field in the Reserved to true
    * 
    * @param title
    *           title of the reserved Item
    * @param type
    *           type of the reserved Item
    * @param personID
    *           id of the Person reserving it
    */
   public void removeFromAvailableReserved(String title, String type,
         String personID)
   {
      Reserved[] allReserves = getAdapter().getAllReserved();

      if (allReserves != null)
      {
         for (int i = 0; i < allReserves.length; i++)
         {
            if (allReserves[i].getItem().getTitle().equals(title)
                  && allReserves[i].getItem().getType().equals(type)
                  && allReserves[i].getReserver().getId().equals(personID))
            {
               allReserves[i].setNotified(true);
            }

         }
      }

      getAdapter().saveReserved(allReserves);

   }

}

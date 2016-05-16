package version5;

public class LibaryInitialiser
{
public static void main(String[] args)
{   
	Library l = new Library();
	
	
   Book first= new Book("Harry Potter and the Order of the Phoenix","5066-400-330","J.K. Rowling");
   Book second= new Book("The Catcher in the Rye","5020-2039-444","J.D. Salinger");
   Book third= new Book("The Lord of the Rings","3094-6097-30934","J.R.R Tolkien");
   Book fourth= new Book("Harry Potter and the Goblet of Fire","49586-30934-300","J.K. Rowling");
   Book fifth= new Book("Harry Potter and the Half-Blood Prince","3490-0293-02","J.K. Rowling");
   
   Person jack= new Person("Jack Nicholsen","206791","them","jack@hotmail.dk",true);
   Person peter= new Person("Jack Nicholsen","206792","them","jack@hotmail.dk",true);
   
   
   
//   Book[] books= new Book[100];
   
   
//   for(int i=0; i<=50; i++)
//   {
//      books[i]=second;
//   }
//   for(int i=51; i<100; i++)
//   {
//      books[i]=third;
//   }
   
   
   Item[] items = {first, second, third, fourth, fifth};
   
//   Item[] items= (Item[]) books;
   try
   {
      l.getAdapter().getIO(LibraryAdapter.ITEMS_FILE).fwrite(items);
      l.getAdapter().getIO(LibraryAdapter.PERSONS_FILE).fwrite(jack);
      l.getAdapter().getIO(LibraryAdapter.PERSONS_FILE).fappend(peter);
   }
   catch (Exception e)
   {
      e.printStackTrace();
   }
   
   System.err.println("Data initialized.");
   
   
   String tempro= first.getUniqueIDs()[0];
   
   String tempro2 = second.getUniqueIDs()[0];
   
   System.out.println(tempro);
   
   l.borrowItem(tempro, jack, first);
   l.borrowItem(tempro2, peter, second);
   
   
   Borrowed[] test=l.getAdapter().getAllBorrowed();

   test[0].setReturnDate(new MyDate(15, 12, 2014));
   test[1].setReturnDate(new MyDate(9, 12, 2014));
   
   l.getAdapter().saveBorrowed(test);
   
   
   
   
   

}
}

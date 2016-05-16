package version5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUIAddRemoveItem extends JTabbedPane
{
   private Library library;
   
   private JTabbedPane addRemoveTab;
   private JPanel addPanel;
   private JPanel removePanel;
   
   private JPanel addBookPanel;
   private JTextField bookTitleBook;
   private JTextField authorNameBook;
   private JTextField isbnTextBook;
   private JCheckBox checkMeBook;
   private JButton addBookButton;   
   
   private JPanel addArticlePanel;
   private JTextField articleTitle;
   private JTextField authorNameArticle;
   private JCheckBox checkMeArticle;
   private JButton addArticleButton;
   
   private JPanel addCDPanel;
   private JTextField CDTitle;
   private JTextField artistName;
   private JCheckBox checkMeCD;
   private JButton addCDButton;
   
   private JPanel addDVDPanel;
   private JTextField DVDTitle;
   private JTextField directorName;
   private JCheckBox checkMeDVD;
   private JButton addDVDButton;

   
   private JPanel removeBookPanel;
   private JTextField bookTitleToRemove;
   private JTextField idTextToRemoveBook;
   private JCheckBox checkMeRemoveBook;
   private JButton removeBookButton;
   
   private JPanel removeArticlePanel;
   private JTextField articleTitleToRemove;
   private JTextField idTextToRemoveArticle;
   private JCheckBox checkMeRemoveArticle;
   private JButton removeArticleButton;
   
   private JPanel removeCDPanel;
   private JTextField CDTitleToRemove;
   private JTextField idTextToRemoveCD;
   private JCheckBox checkMeRemoveCD;
   private JButton removeCDButton;
   
   private JPanel removeDVDPanel;
   private JTextField DVDTitleToRemove;
   private JTextField idTextToRemoveDVD;
   private JCheckBox checkMeRemoveDVD;
   private JButton removeDVDButton;
   
   
   private JComboBox<String> choose;
   private JComboBox<String> chooseRemove;

   public GUIAddRemoveItem(Library l)
   {
      super();
      
      library = l;

      setTabPlacement(JTabbedPane.LEFT);

      addPanel = addPanel();
      removePanel = removePanel();

      add("<html><body><h4>Add Item</h4></body></html>", addPanel);
      add("<html><body><h4>Remove Item</h4></body></html>", removePanel);
      
      
      ChangeListener tabbedPaneListener = new ChangeListener()
      {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(getSelectedIndex() == 0)
			{
				clearAdd();
			}
			else if(getSelectedIndex() == 1)
			{
				clearRemove();
			}
			
		}
    	  
      };
      
      addChangeListener(tabbedPaneListener);

   }

   public JTabbedPane addRemoveTab()
   {
      addRemoveTab = new JTabbedPane();
      addRemoveTab.setTabPlacement(JTabbedPane.LEFT);

      addPanel = addPanel();
      removePanel = removePanel();

      addRemoveTab.add("Add Item", addPanel);
      addRemoveTab.add("Remove Item", removePanel);

      return addRemoveTab;
   }

   public JPanel addPanel()
   {
      // Create add panel
      addPanel = new JPanel();
      addPanel.setLayout(new BorderLayout());

      // Create Top part of the add panel, the combo box
      JPanel addPanelTop = new JPanel();
      addPanelTop.setLayout(new BorderLayout());

      String[] pickList = {
            "<html><body><h3>Choose an Item type</h3></body></html>", "Book",
            "Article", "CD", "DVD" };
      choose = new JComboBox<String>(pickList);
      MyListener listener = new MyListener();
      choose.addActionListener(listener);
      JPanel choosePanel = new JPanel(new BorderLayout());
      choosePanel.add(choose, BorderLayout.WEST);
      choosePanel.setPreferredSize(new Dimension(100, 35));
      // choosePanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));

      // Creating the Gap fillers and adding the Combo box to the top Panel
      JPanel spacerNorth = new JPanel();
      spacerNorth.setPreferredSize(new Dimension(100, 15));
      // spacerNorth.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      JPanel spacerWest = new JPanel();
      spacerWest.setPreferredSize(new Dimension(100, 20));
      // spacerWest.setBorder(BorderFactory.createLineBorder(Color.RED));
      JPanel spacerSouth = new JPanel();
      spacerSouth.setPreferredSize(new Dimension(20, 10));
      // spacerSouth.setBorder(BorderFactory.createLineBorder(Color.GREEN));

      addPanelTop.add(spacerNorth, BorderLayout.NORTH);
      addPanelTop.add(spacerWest, BorderLayout.WEST);
      addPanelTop.add(spacerSouth, BorderLayout.SOUTH);
      addPanelTop.add(choosePanel, BorderLayout.CENTER);

      // Making the Center Panel
      JPanel addPanelCenter = new JPanel();
      addPanelCenter.add(addBookPanel());
      addPanelCenter.add(addArticlePanel());
      addPanelCenter.add(addCDPanel());
      addPanelCenter.add(addDVDPanel());

      // Adding to the add panel
      addPanel.add(addPanelTop, BorderLayout.NORTH);
      addPanel.add(addPanelCenter, BorderLayout.WEST);

      return addPanel;
   }

   public JPanel addBookPanel()
   {
      addBookPanel = new JPanel();
      // addBookPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      addBookPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 25);

      addBookPanel.setVisible(false);

      // Creating the Gap fillers and adding the Combo box to the top Panel

      JLabel titleBook = new JLabel(
            "<html><body><h2>Title of the book:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      addBookPanel.add(titleBook, c);

      bookTitleBook = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      addBookPanel.add(bookTitleBook, c);

      JLabel authorBook = new JLabel(
            "<html><body><h2>Author of the book:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      addBookPanel.add(authorBook, c);

      authorNameBook = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      addBookPanel.add(authorNameBook, c);

      JLabel isbnBook = new JLabel("<html><body><h2>ISBN:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 2;
      addBookPanel.add(isbnBook, c);

      isbnTextBook = new JTextField(22);
      c.gridx = 1;
      c.gridy = 2;
      addBookPanel.add(isbnTextBook, c);

      JLabel checkMeTextBook = new JLabel("Book information is correct");
      c.gridx = 0;
      c.gridy = 3;
      addBookPanel.add(checkMeTextBook, c);

      checkMeBook = new JCheckBox();
      c.gridx = 1;
      c.gridy = 3;
      addBookPanel.add(checkMeBook, c);

      addBookButton = new JButton("Add Book to the system");
      c.gridx = 0;
      c.gridy = 4;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      addBookButton.addActionListener(new MyListener());
      addBookPanel.add(addBookButton, c);

      return addBookPanel;
   }

   public JPanel addArticlePanel()
   {
      addArticlePanel = new JPanel();
      addArticlePanel.setVisible(false);
      addArticlePanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 14);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the article:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      addArticlePanel.add(title, c);

      articleTitle = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      addArticlePanel.add(articleTitle, c);

      JLabel author = new JLabel(
            "<html><body><h2>Author of the article:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      addArticlePanel.add(author, c);

      authorNameArticle = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      addArticlePanel.add(authorNameArticle, c);

      JLabel checkMeText = new JLabel("Article information is correct");
      c.gridx = 0;
      c.gridy = 2;
      addArticlePanel.add(checkMeText, c);

      checkMeArticle = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      addArticlePanel.add(checkMeArticle, c);

      addArticleButton = new JButton("Add Article to the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      addArticleButton.addActionListener(new MyListener());
      addArticlePanel.add(addArticleButton, c);

      return addArticlePanel;
   }

   public JPanel addCDPanel()
   {
      addCDPanel = new JPanel();
      addCDPanel.setLayout(new GridBagLayout());
      addCDPanel.setVisible(false);

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 42);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the CD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      addCDPanel.add(title, c);

      CDTitle = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      addCDPanel.add(CDTitle, c);

      JLabel artist = new JLabel(
            "<html><body><h2>Author of the CD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      addCDPanel.add(artist, c);

      artistName = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      addCDPanel.add(artistName, c);

      JLabel checkMeText = new JLabel("CD information is correct");
      c.gridx = 0;
      c.gridy = 2;
      addCDPanel.add(checkMeText, c);

      checkMeCD = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      addCDPanel.add(checkMeCD, c);

      addCDButton = new JButton("Add CD to the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      addCDButton.addActionListener(new MyListener());
      addCDPanel.add(addCDButton, c);

      return addCDPanel;
   }

   public JPanel addDVDPanel()
   {
      addDVDPanel = new JPanel();
      addDVDPanel.setVisible(false);
      addDVDPanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 18);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the DVD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      addDVDPanel.add(title, c);

      DVDTitle = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      addDVDPanel.add(DVDTitle, c);

      JLabel director = new JLabel(
            "<html><body><h2>Director of the DVD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      addDVDPanel.add(director, c);

      directorName = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      addDVDPanel.add(directorName, c);

      JLabel checkMeText = new JLabel("DVD information is correct");
      c.gridx = 0;
      c.gridy = 2;
      addDVDPanel.add(checkMeText, c);

      checkMeDVD = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      addDVDPanel.add(checkMeDVD, c);

      addDVDButton = new JButton("Add DVD to the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      addDVDButton.addActionListener(new MyListener());
      addDVDPanel.add(addDVDButton, c);

      return addDVDPanel;
   }

   public JPanel removePanel()
   {
      removePanel = new JPanel();
      removePanel.setLayout(new BorderLayout());

      JPanel removePanelTop = new JPanel();
      removePanelTop.setLayout(new BorderLayout());

      String[] pickList = {
            "<html><body><h3>Choose an Item type</h3></body></html>", "Book",
            "Article", "CD", "DVD" };

      chooseRemove = new JComboBox<String>(pickList);
      MyListener listener = new MyListener();
      chooseRemove.addActionListener(listener);

      JPanel choosePanel = new JPanel(new BorderLayout());

      choosePanel.add(chooseRemove, BorderLayout.WEST);
      choosePanel.setPreferredSize(new Dimension(100, 35));

      // Creating the Gap fillers and adding the Combo box to the top Panel
      JPanel spacerNorth = new JPanel();
      spacerNorth.setPreferredSize(new Dimension(100, 15));
      // spacerNorth.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      JPanel spacerWest = new JPanel();
      spacerWest.setPreferredSize(new Dimension(100, 20));
      // spacerWest.setBorder(BorderFactory.createLineBorder(Color.RED));
      JPanel spacerSouth = new JPanel();
      spacerSouth.setPreferredSize(new Dimension(20, 10));
      // spacerSouth.setBorder(BorderFactory.createLineBorder(Color.GREEN));

      removePanelTop.add(spacerNorth, BorderLayout.NORTH);
      removePanelTop.add(spacerWest, BorderLayout.WEST);
      removePanelTop.add(spacerSouth, BorderLayout.SOUTH);
      removePanelTop.add(choosePanel, BorderLayout.CENTER);

      // Making the Center Panel
      JPanel removePanelCenter = new JPanel();
      removePanelCenter.add(removeBookPanel());
      removePanelCenter.add(removeArticlePanel());
      removePanelCenter.add(removeCDPanel());
      removePanelCenter.add(removeDVDPanel());

      // Adding to the remove panel
      removePanel.add(removePanelTop, BorderLayout.NORTH);
      removePanel.add(removePanelCenter, BorderLayout.WEST);

      return removePanel;
   }

   public JPanel removeBookPanel()
   {
      removeBookPanel = new JPanel();
      removeBookPanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 25);

      removeBookPanel.setVisible(false);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the book:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      removeBookPanel.add(title, c);

      bookTitleToRemove = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      removeBookPanel.add(bookTitleToRemove, c);
      
      JLabel id = new JLabel("<html><body><h2>ID:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      removeBookPanel.add(id, c);

      idTextToRemoveBook = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      removeBookPanel.add(idTextToRemoveBook, c);

      JLabel checkMeText = new JLabel("Book information is correct");
      c.gridx = 0;
      c.gridy = 2;
      removeBookPanel.add(checkMeText, c);

      checkMeRemoveBook = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      removeBookPanel.add(checkMeRemoveBook, c);

      removeBookButton = new JButton("Remove Book from the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      removeBookButton.addActionListener(new MyListener());
      removeBookPanel.add(removeBookButton, c);

      return removeBookPanel;
   }

   public JPanel removeArticlePanel()
   {
      removeArticlePanel = new JPanel();
      removeArticlePanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 14);

      removeArticlePanel.setVisible(false);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the article:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      removeArticlePanel.add(title, c);

      articleTitleToRemove = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      removeArticlePanel.add(articleTitleToRemove, c);
      
      JLabel id = new JLabel("<html><body><h2>ID:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      removeArticlePanel.add(id, c);

      idTextToRemoveArticle = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      removeArticlePanel.add(idTextToRemoveArticle, c);

      JLabel checkMeText = new JLabel("Article information is correct");
      c.gridx = 0;
      c.gridy = 2;
      removeArticlePanel.add(checkMeText, c);

      checkMeRemoveArticle = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      removeArticlePanel.add(checkMeRemoveArticle, c);

      removeArticleButton = new JButton("Remove Article from the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      removeArticleButton.addActionListener(new MyListener());
      removeArticlePanel.add(removeArticleButton, c);

      return removeArticlePanel;
   }

   public JPanel removeCDPanel()
   {
      removeCDPanel = new JPanel();
      removeCDPanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 42);

      removeCDPanel.setVisible(false);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the CD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      removeCDPanel.add(title, c);

      CDTitleToRemove = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      removeCDPanel.add(CDTitleToRemove, c);

      JLabel id = new JLabel("<html><body><h2>ID:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      removeCDPanel.add(id, c);

      idTextToRemoveCD = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      removeCDPanel.add(idTextToRemoveCD, c);

      JLabel checkMeText = new JLabel("CD information is correct");
      c.gridx = 0;
      c.gridy = 2;
      removeCDPanel.add(checkMeText, c);

      checkMeRemoveCD = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      removeCDPanel.add(checkMeRemoveCD, c);

      removeCDButton = new JButton("Remove CD from the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      removeCDButton.addActionListener(new MyListener());
      removeCDPanel.add(removeCDButton, c);

      return removeCDPanel;
   }

   public JPanel removeDVDPanel()
   {
      removeDVDPanel = new JPanel();

      removeDVDPanel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();

      c.fill = GridBagConstraints.NONE;
      c.anchor = GridBagConstraints.LINE_START;
      c.weightx = 0.5;
      c.insets = new Insets(0, 50, 10, 18);

      removeDVDPanel.setVisible(false);

      JLabel title = new JLabel(
            "<html><body><h2>Title of the DVD:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 0;
      removeDVDPanel.add(title, c);

      DVDTitleToRemove = new JTextField(22);
      c.gridx = 1;
      c.gridy = 0;
      removeDVDPanel.add(DVDTitleToRemove, c);

      JLabel id = new JLabel("<html><body><h2>ID:</h2></body></html>");
      c.gridx = 0;
      c.gridy = 1;
      removeDVDPanel.add(id, c);

      idTextToRemoveDVD = new JTextField(22);
      c.gridx = 1;
      c.gridy = 1;
      removeDVDPanel.add(idTextToRemoveDVD, c);

      JLabel checkMeText = new JLabel("DVD information is correct");
      c.gridx = 0;
      c.gridy = 2;
      removeDVDPanel.add(checkMeText, c);

      checkMeRemoveDVD = new JCheckBox();
      c.gridx = 1;
      c.gridy = 2;
      removeDVDPanel.add(checkMeRemoveDVD, c);

      removeDVDButton = new JButton("Remove DVD from the system");
      c.gridx = 0;
      c.gridy = 3;
      c.weightx = 0.0;
      c.gridwidth = 2;
      c.ipady = 25;
      c.anchor = GridBagConstraints.LAST_LINE_START;
      c.fill = GridBagConstraints.HORIZONTAL;
      removeDVDButton.addActionListener(new MyListener());
      removeDVDPanel.add(removeDVDButton, c);

      return removeDVDPanel;
   }

   private class MyListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == choose)
         {
            String selected = (String) choose.getSelectedItem();
            setVisibleFalse();
            if (selected.equals("Book"))
            {               
               System.out.println("Book selected");
               addBookPanel.setVisible(true);
            }
            else if (selected.equals("Article"))
            {
               System.out.println("Article selected");
               addArticlePanel.setVisible(true);
            }
            else if (selected.equals("CD"))
            {
               System.out.println("CD selected");
               addCDPanel.setVisible(true);
            }
            else if (selected.equals("DVD"))
            {
               System.out.println("DVD selected");
               addDVDPanel.setVisible(true);
            }
         }
         
         if (e.getSource() == chooseRemove)
         {
            String selected = (String) chooseRemove.getSelectedItem();
            setVisibleFalse();
            if (selected.equals("Book"))
            {               
               System.out.println("Book selected to remove");
               removeBookPanel.setVisible(true);
            }
            else if (selected.equals("Article"))
            {
               System.out.println("Article selected to remove");
               removeArticlePanel.setVisible(true);
            }
            else if (selected.equals("CD"))
            {
               System.out.println("CD selected to remove");
               removeCDPanel.setVisible(true);
            }
            else if (selected.equals("DVD"))
            {
               System.out.println("DVD selected to remove");
               removeDVDPanel.setVisible(true);
            }
         }

         //Adding new book
         if (e.getSource() == addBookButton)
         {
            if (checkMeBook.isSelected())
            {
               checkMeBook.setSelected(false);
               String title = bookTitleBook.getText();
               String author = authorNameBook.getText();
               String isbn = isbnTextBook.getText();
               boolean correctInfo = true;

               if (title.equals("") || title == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter title",
                        "No title ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }
               if (author.equals("") || author == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter author",
                        "No author ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }
               if (isbn.equals("") || isbn == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter ISBN",
                        "No isbn ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }

               if (correctInfo)
               {
                  Book newBook = new Book(title, isbn, author);

                  // Adding the new Book
                  String temp2 = library.addBook(newBook);

                  String[] tempArr = temp2.split("-");
                  String id = tempArr[2];

                  JOptionPane.showMessageDialog(null, "Book added\nTitle: "
                        + title + "\nAuthor: " + author + "\nISBN: " + isbn
                        + "\nID: " + id, "New Book added to the system",
                        JOptionPane.INFORMATION_MESSAGE);

                  bookTitleBook.setText("");
                  authorNameBook.setText("");
                  isbnTextBook.setText("");

               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please check information",
                     "CheckBox", JOptionPane.ERROR_MESSAGE);
            }
         }

         // Adding new article
         if (e.getSource() == addArticleButton)
         {
            if (checkMeArticle.isSelected())
            {
               checkMeArticle.setSelected(false);
               String title = articleTitle.getText();
               String author = authorNameArticle.getText();
               boolean correctInfo = true;

               if (title.equals("") || title == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter title",
                        "No title ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }
               if (author.equals("") || author == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter author",
                        "No author ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }

               if (correctInfo)
               {
                  Article newArticle = new Article(title, author);

                  String temp2 = library.addArticle(newArticle);
                  String[] tempArr = temp2.split("-");
                  String id = tempArr[2];

                  JOptionPane.showMessageDialog(null, "Article added\nTitle: "
                        + title + "\nAuthor: " + author + "\nID: " + id,
                        "New Article added to the system",
                        JOptionPane.INFORMATION_MESSAGE);

                  articleTitle.setText("");
                  authorNameArticle.setText("");

               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please check information",
                     "CheckBox", JOptionPane.ERROR_MESSAGE);
            }
         }

         // adding new CD
         if (e.getSource() == addCDButton)
         {
            if (checkMeCD.isSelected())
            {
               checkMeCD.setSelected(false);
               String title = CDTitle.getText();
               String artist = artistName.getText();
               boolean correctInfo = true;

               if (title.equals("") || title == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter title",
                        "No title ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }
               if (artist.equals("") || artist == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter artist",
                        "No artist ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }

               if (correctInfo)
               {
                  CD newCD = new CD(title, artist);

                  String temp2 = library.addCD(newCD);
                  String[] tempArr = temp2.split("-");
                  String id = tempArr[2];

                  JOptionPane.showMessageDialog(null, "CD added\nTitle: "
                        + title + "\nArtist: " + artist + "\nID: " + id,
                        "New CD added to the system",
                        JOptionPane.INFORMATION_MESSAGE);

                  CDTitle.setText("");
                  artistName.setText("");
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please check information",
                     "CheckBox", JOptionPane.ERROR_MESSAGE);
            }
         }

         // adding new DVD
         if (e.getSource() == addDVDButton)
         {
            if (checkMeDVD.isSelected())
            {
               checkMeDVD.setSelected(false);
               String title = DVDTitle.getText();
               String director = directorName.getText();
               boolean correctInfo = true;

               if (title.equals("") || title == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter title",
                        "No title ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }
               if (director.equals("") || director == null)
               {
                  JOptionPane.showMessageDialog(null, "Please enter director",
                        "No director ERROR", JOptionPane.ERROR_MESSAGE);
                  correctInfo = false;
               }

               if (correctInfo)
               {
                  DVD newDVD = new DVD(title, director);

                  // Adding the new DVD
                  String temp2 = library.addDVD(newDVD);
                  String[] tempArr = temp2.split("-");
                  String id = tempArr[2];

                  JOptionPane.showMessageDialog(null, "DVD added\nTitle: "
                        + title + "\nDirector: " + director + "\nID: " + id,
                        "New DVD added to the system",
                        JOptionPane.INFORMATION_MESSAGE);

                  DVDTitle.setText("");
                  directorName.setText("");
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null,
                     "Please check information", "CheckBox",
                     JOptionPane.ERROR_MESSAGE);
            }
         }

            // remove book item
            if (e.getSource() == removeBookButton)
            {
            	System.err.println("Remove Book Button clicked");
            	
               if (checkMeRemoveBook.isSelected())
               {
                  checkMeRemoveBook.setSelected(false);
                  String title = bookTitleToRemove.getText();
                  String id = idTextToRemoveBook.getText();
                  boolean correctInfo = true;

                  if (title.equals("") || title == null)
                  {
                     JOptionPane.showMessageDialog(null, "Please enter title",
                           "No title ERROR", JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }
                  if (id.equals("") || id == null)
                  {
                     JOptionPane.showMessageDialog(null,
                           "Please enter the correct ID", "No ID ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }

                  if (correctInfo)
                  {
                     boolean success = library
                           .removeItem(title + "-Book-" + id);
                     System.err.println("Success");
                     if (success)
                     {
                        JOptionPane.showMessageDialog(null,
                              "The book with the title " + title + " and ID: "
                                    + id + " has been removed succesfully",
                              "Book removed", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else
                     {
                    	 Item getItem = library.getAdapter().getItem(title);
                    	 
                    	 if(getItem!=null)
                    	 {
                    		 if(library.checkUniqueIDExists(getItem, title + "-Book-" + id))
                    		 {
                    			 JOptionPane.showMessageDialog(null, "The book is borrowed. Failed to remove book from the system.", "Error", JOptionPane.ERROR_MESSAGE);
                    		 }
                    		 else
                    		 {
     	                        JOptionPane.showMessageDialog(null,
     		                              "(!) The book with the title " + title + " and ID: "
     		                                    + id + " does not exist. Please try again",
     		                              "Book NOT removed", JOptionPane.WARNING_MESSAGE);
                    		 }
                    	 }
                    	 else
                    	 {
	                        JOptionPane.showMessageDialog(null,
	                              "(!!) The book with the title " + title + " and ID: "
	                                    + id + " does not exist. Please try again",
	                              "Book NOT removed", JOptionPane.WARNING_MESSAGE);
                    	 }
                     }

                     bookTitleToRemove.setText("");
                     idTextToRemoveBook.setText("");
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Please check information", "CheckBox",
                        JOptionPane.ERROR_MESSAGE);
               }
            }

            // remove Article item
            if (e.getSource() == removeArticleButton)
            {
               if (checkMeRemoveArticle.isSelected())
               {
                  checkMeRemoveArticle.setSelected(false);
                  String title = articleTitleToRemove.getText();
                  String id = idTextToRemoveArticle.getText();
                  boolean correctInfo = true;

                  if (title.equals("") || title == null)
                  {
                     JOptionPane.showMessageDialog(null, "Please enter title",
                           "No title ERROR", JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }
                  if (id.equals("") || id == null)
                  {
                     JOptionPane.showMessageDialog(null,
                           "Please enter the correct ID", "No ID ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }

                  if (correctInfo)
                  {
                     boolean success = library.removeItem(title + "-Article-"
                           + id);

                     if (success)
                     {
                        JOptionPane.showMessageDialog(null,
                              "The article with the title " + title
                                    + " and ID: " + id
                                    + " has been removed succesfully",
                              "Article removed",
                              JOptionPane.INFORMATION_MESSAGE);
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(null,
                              "The article with the title " + title
                                    + " and ID: " + id
                                    + " does not exist. Please try again",
                              "Article NOT removed",
                              JOptionPane.WARNING_MESSAGE);
                     }

                     articleTitleToRemove.setText("");
                     idTextToRemoveArticle.setText("");

                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Please check information", "CheckBox",
                        JOptionPane.ERROR_MESSAGE);
               }
            }

            // remove CD item
            if (e.getSource() == removeCDButton)
            {
               if (checkMeRemoveCD.isSelected())
               {
                  checkMeRemoveCD.setSelected(false);
                  String title = CDTitleToRemove.getText();
                  String id = idTextToRemoveCD.getText();
                  boolean correctInfo = true;

                  if (title.equals("") || title == null)
                  {
                     JOptionPane.showMessageDialog(null, "Please enter title",
                           "No title ERROR", JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }
                  if (id.equals("") || id == null)
                  {
                     JOptionPane.showMessageDialog(null,
                           "Please enter the correct ID", "No ID ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }

                  if (correctInfo)
                  {
                     boolean success = library.removeItem(title + "-CD-" + id);

                     if (success)
                     {
                        JOptionPane.showMessageDialog(null,
                              "The CD with the title " + title + " and ID: "
                                    + id + " has been removed succesfully",
                              "CD removed", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(null,
                              "The CD with the title " + title + " and ID: "
                                    + id + " does not exist. Please try again",
                              "CD NOT removed", JOptionPane.WARNING_MESSAGE);
                     }

                     CDTitleToRemove.setText("");
                     idTextToRemoveCD.setText("");
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Please check information", "CheckBox",
                        JOptionPane.ERROR_MESSAGE);
               }
            }

            // remove DVD item
            if (e.getSource() == removeDVDButton)
            {
               if (checkMeRemoveDVD.isSelected())
               {
                  checkMeRemoveDVD.setSelected(false);
                  String title = DVDTitleToRemove.getText();
                  String id = idTextToRemoveDVD.getText();
                  boolean correctInfo = true;

                  if (title.equals("") || title == null)
                  {
                     JOptionPane.showMessageDialog(null, "Please enter title",
                           "No title ERROR", JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }
                  if (id.equals("") || id == null)
                  {
                     JOptionPane.showMessageDialog(null,
                           "Please enter the correct ID", "No ID ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     correctInfo = false;
                  }

                  if (correctInfo)
                  {
                     boolean success = library.removeItem(title + "-DVD-" + id);
                     if (success)
                     {
                        JOptionPane.showMessageDialog(null,
                              "The DVD with the title " + title + " and ID: "
                                    + id + " has been removed succesfully",
                              "DVD removed", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(null,
                              "The DVD with the title " + title + " and ID: "
                                    + id + " does not exist. Please try again",
                              "DVD NOT removed", JOptionPane.WARNING_MESSAGE);
                     }

                     DVDTitleToRemove.setText("");
                     idTextToRemoveDVD.setText("");
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null,
                        "Please check information", "CheckBox",
                        JOptionPane.ERROR_MESSAGE);
               }
            }
      	}
   }
   
   private void setVisibleFalse()
   {
      addBookPanel.setVisible(false);
      addArticlePanel.setVisible(false);
      addCDPanel.setVisible(false);
      addDVDPanel.setVisible(false);
      
      removeBookPanel.setVisible(false);
      removeArticlePanel.setVisible(false);
      removeCDPanel.setVisible(false);
      removeDVDPanel.setVisible(false);
   }

   public void clearAll()
   {
	   clearAdd();
	   clearRemove();
   }
   
   public void clearRemove()
   {
	   chooseRemove.setSelectedIndex(0);
	   bookTitleToRemove.setText("");
	   idTextToRemoveBook.setText("");
	   checkMeRemoveBook.setSelected(false);
	   
	   articleTitleToRemove.setText("");
	   idTextToRemoveArticle.setText("");
	   checkMeRemoveArticle.setSelected(false);
	   
	   CDTitleToRemove.setText("");
	   idTextToRemoveCD.setText("");
	   checkMeRemoveCD.setSelected(false);
	   
	   DVDTitleToRemove.setText("");
	   idTextToRemoveDVD.setText("");
	   checkMeRemoveDVD.setSelected(false);
   }
   
   public void clearAdd()
   {
	   choose.setSelectedIndex(0);
	   bookTitleBook.setText("");
	   authorNameBook.setText("");
	   isbnTextBook.setText("");
	   checkMeBook.setSelected(false);
	   
	   articleTitle.setText("");
	   authorNameArticle.setText("");
	   checkMeArticle.setSelected(false);
	   
	   CDTitle.setText("");
	   artistName.setText("");
	   checkMeCD.setSelected(false);
	   
	   DVDTitle.setText("");
	   directorName.setText("");
	   checkMeDVD.setSelected(false);
   }

}

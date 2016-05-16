package version5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * EditUser class for the Fairytale Library system's GUI.
 * 
 * @author Simos Christodoulou
 * @version 1.0 6/12/2014
 */
public class EditUser extends JPanel
{
   private JPanel mainPanel;
   private JPanel idPanel;
   private JPanel centerPanel;
   private JPanel containerPanel;
   private JPanel namePanel;
   private JPanel addressPanel;
   private JPanel emailPanel;
   private JPanel buttonPanel;

   private JLabel idLabel, nameLabel, addressLabel, emailLabel;
   private JTextField idField, nameField, addressField, emailField;

   private JButton searchButton;
   private JButton editButton;
   private JButton removeButton;
   private JButton saveButton;

   private Library library;
   private boolean foundPerson;

   /**
    * EditUser class constructor. <br />
    * Creates a panel with all the appropriate components that allow
    * modification of a student's or lecturer's info.
    */
   public EditUser(Library l)
   {
      library = l;
      foundPerson = false;

      setLayout(new BorderLayout());

      // -----------------------------------------------------------------------
      // Main Panel
      // Created to contain all the other panels
      // -----------------------------------------------------------------------
      mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());

      // -----------------------------------------------------------------------
      // ID Panel
      // Contains the id Label and TextField and the search Button
      // -----------------------------------------------------------------------
      idPanel = new JPanel();
      idPanel.setPreferredSize(new Dimension(200, 100));

      idLabel = new JLabel("ID:");
      idLabel.setPreferredSize(new Dimension(20, 50));

      idField = new JTextField(12);
      idField.setPreferredSize(new Dimension(100, 25));

      searchButton = new JButton("Search");
      searchButton.addActionListener(new ListenToSearch());

      idPanel.add(idLabel);
      idPanel.add(idField);
      idPanel.add(searchButton);

      // ----------------------------------------------------------------------
      // Name Panel
      // Contains the name Label and TextField
      // ----------------------------------------------------------------------
      namePanel = new JPanel();

      nameLabel = new JLabel("Name:");
      nameLabel.setPreferredSize(new Dimension(60, 50));

      nameField = new JTextField(22);
      nameField.setPreferredSize(new Dimension(100, 25));
      nameField.setEditable(false);

      namePanel.add(nameLabel);
      namePanel.add(nameField);

      // ----------------------------------------------------------------------
      // Address Panel
      // Contains the address Label and TextField
      // ----------------------------------------------------------------------
      addressPanel = new JPanel();

      addressLabel = new JLabel("Address:");
      addressLabel.setPreferredSize(new Dimension(60, 50));

      addressField = new JTextField(22);
      addressField.setPreferredSize(new Dimension(100, 25));
      addressField.setEditable(false);

      addressPanel.add(addressLabel);
      addressPanel.add(addressField);

      // ----------------------------------------------------------------------
      // Email Panel
      // Contains the email Label and TextField
      // ----------------------------------------------------------------------
      emailPanel = new JPanel();

      emailLabel = new JLabel("Email:");
      emailLabel.setPreferredSize(new Dimension(60, 50));

      emailField = new JTextField(22);
      emailField.setPreferredSize(new Dimension(100, 25));
      emailField.setEditable(false);

      emailPanel.add(emailLabel);
      emailPanel.add(emailField);

      // ----------------------------------------------------------------------
      // Container Panel
      // Contains the name, address and email Panels
      // ----------------------------------------------------------------------
      containerPanel = new JPanel();
      containerPanel.setLayout(new GridLayout(3, 1));
      containerPanel.setPreferredSize(new Dimension(400, 250));

      containerPanel.add(namePanel);
      containerPanel.add(addressPanel);
      containerPanel.add(emailPanel);

      // ----------------------------------------------------------------------
      // Center Panel
      // Contains the container Panel and the area Text area, in order display
      // their contents properly
      // ----------------------------------------------------------------------
      centerPanel = new JPanel();
      centerPanel.add(containerPanel);

      // ----------------------------------------------------------------------
      // Button Panel
      // Contains the edit, remove and save Buttons
      // ----------------------------------------------------------------------
      buttonPanel = new JPanel(new GridBagLayout());
      // buttonPanel.setPreferredSize(new Dimension(50, 110));

      GridBagConstraints gbc = new GridBagConstraints();

      editButton = new JButton("Edit");
      editButton.addActionListener(new ListenToEdit());
      removeButton = new JButton("Remove Account");
      removeButton.addActionListener(new ListenToRemove());
      saveButton = new JButton("Save Changes");
      saveButton.addActionListener(new ListenToSave());

      removeButton.setBackground(Color.RED);
      removeButton.setForeground(Color.WHITE);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(5, 5, 5, 5);
      buttonPanel.add(editButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.insets = new Insets(5, 5, 5, 5);
      buttonPanel.add(saveButton, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.weightx = 1;
      gbc.insets = new Insets(10, 5, 5, 5);
      buttonPanel.add(removeButton, gbc);

      // Add all the panels to the main Panel
      mainPanel.add(idPanel, BorderLayout.NORTH);
      mainPanel.add(centerPanel, BorderLayout.CENTER);
      mainPanel.add(buttonPanel, BorderLayout.SOUTH);
      mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

      // Create to spacer Panels to cover the north and south positions
      JPanel spacer = new JPanel();
      JPanel spacer2 = new JPanel();
      JPanel spacer3 = new JPanel();
      JPanel spacer4 = new JPanel();
      spacer.setPreferredSize(new Dimension(300, 5));
      spacer2.setPreferredSize(new Dimension(300, 10));
      spacer3.setPreferredSize(new Dimension(10, 10));
      spacer4.setPreferredSize(new Dimension(10, 10));

      // Add the spacer Panel and the main Panel to the generated Panel from the
      // constructor
      add(spacer, BorderLayout.NORTH);
      add(mainPanel, BorderLayout.CENTER);
      add(spacer2, BorderLayout.SOUTH);
      add(spacer3, BorderLayout.WEST);
      add(spacer4, BorderLayout.EAST);

   }

   public EditUser(String id, Library l)
   {
      library = new Library();
      foundPerson = false;

      setLayout(new BorderLayout());

      // -----------------------------------------------------------------------
      // Main Panel
      // Created to contain all the other panels
      // -----------------------------------------------------------------------
      mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());

      // -----------------------------------------------------------------------
      // ID Panel
      // Contains the id Label and TextField and the search Button
      // -----------------------------------------------------------------------
      idPanel = new JPanel();
      idPanel.setPreferredSize(new Dimension(200, 100));

      idLabel = new JLabel("ID:");
      idLabel.setPreferredSize(new Dimension(20, 50));

      idField = new JTextField(12);
      idField.setPreferredSize(new Dimension(100, 25));

      searchButton = new JButton("Search");
      searchButton.addActionListener(new ListenToSearch());

      idPanel.add(idLabel);
      idPanel.add(idField);
      idPanel.add(searchButton);

      // ----------------------------------------------------------------------
      // Name Panel
      // Contains the name Label and TextField
      // ----------------------------------------------------------------------
      namePanel = new JPanel();

      nameLabel = new JLabel("Name:");
      nameLabel.setPreferredSize(new Dimension(60, 50));

      nameField = new JTextField(22);
      nameField.setPreferredSize(new Dimension(100, 25));
      nameField.setEditable(false);

      namePanel.add(nameLabel);
      namePanel.add(nameField);

      // ----------------------------------------------------------------------
      // Address Panel
      // Contains the address Label and TextField
      // ----------------------------------------------------------------------
      addressPanel = new JPanel();

      addressLabel = new JLabel("Address:");
      addressLabel.setPreferredSize(new Dimension(60, 50));

      addressField = new JTextField(22);
      addressField.setPreferredSize(new Dimension(100, 25));
      addressField.setEditable(false);

      addressPanel.add(addressLabel);
      addressPanel.add(addressField);

      // ----------------------------------------------------------------------
      // Email Panel
      // Contains the email Label and TextField
      // ----------------------------------------------------------------------
      emailPanel = new JPanel();

      emailLabel = new JLabel("Email:");
      emailLabel.setPreferredSize(new Dimension(60, 50));

      emailField = new JTextField(22);
      emailField.setPreferredSize(new Dimension(100, 25));
      emailField.setEditable(false);

      emailPanel.add(emailLabel);
      emailPanel.add(emailField);

      // ----------------------------------------------------------------------
      // Container Panel
      // Contains the name, address and email Panels
      // ----------------------------------------------------------------------
      containerPanel = new JPanel();
      containerPanel.setLayout(new GridLayout(3, 1));
      containerPanel.setPreferredSize(new Dimension(400, 250));

      containerPanel.add(namePanel);
      containerPanel.add(addressPanel);
      containerPanel.add(emailPanel);

      // ----------------------------------------------------------------------
      // Center Panel
      // Contains the container Panel and the area Text area, in order display
      // their contents properly
      // ----------------------------------------------------------------------
      centerPanel = new JPanel();
      centerPanel.add(containerPanel);

      // ----------------------------------------------------------------------
      // Button Panel
      // Contains the edit, remove and save Buttons
      // ----------------------------------------------------------------------
      buttonPanel = new JPanel(new GridBagLayout());
      // buttonPanel.setPreferredSize(new Dimension(50, 110));

      GridBagConstraints gbc = new GridBagConstraints();

      editButton = new JButton("Edit");
      editButton.addActionListener(new ListenToEdit());
      removeButton = new JButton("Remove User");
      removeButton.addActionListener(new ListenToRemove());
      saveButton = new JButton("Save Changes");
      saveButton.addActionListener(new ListenToSave());

      removeButton.setBackground(Color.RED);
      removeButton.setForeground(Color.WHITE);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(5, 5, 5, 5);
      buttonPanel.add(editButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.insets = new Insets(5, 5, 5, 5);
      buttonPanel.add(saveButton, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.weightx = 1;
      gbc.insets = new Insets(10, 5, 5, 5);
      buttonPanel.add(removeButton, gbc);

      // Add all the panels to the main Panel
      mainPanel.add(idPanel, BorderLayout.NORTH);
      mainPanel.add(centerPanel, BorderLayout.CENTER);
      mainPanel.add(buttonPanel, BorderLayout.SOUTH);
      mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

      // Create to spacer Panels to cover the north and south positions
      JPanel spacer = new JPanel();
      JPanel spacer2 = new JPanel();
      JPanel spacer3 = new JPanel();
      JPanel spacer4 = new JPanel();
      spacer.setPreferredSize(new Dimension(300, 5));
      spacer2.setPreferredSize(new Dimension(300, 10));
      spacer3.setPreferredSize(new Dimension(10, 10));
      spacer4.setPreferredSize(new Dimension(10, 10));

      // Add the spacer Panel and the main Panel to the generated Panel from the
      // constructor
      add(spacer, BorderLayout.NORTH);
      add(mainPanel, BorderLayout.CENTER);
      add(spacer2, BorderLayout.SOUTH);
      add(spacer3, BorderLayout.WEST);
      add(spacer4, BorderLayout.EAST);

      idField.setText(id);
      loadData();

   }

   public void loadData()
   {
      nameField.setText("");
      addressField.setText("");
      emailField.setText("");

      String tempID = idField.getText();

      if (tempID.equals(""))
      {
         JOptionPane.showMessageDialog(null,
               "Empty ID Field! \nPlease fill out the ID field.", "Error!",
               JOptionPane.ERROR_MESSAGE);
      }
      else
      {
         Person result = library.getPerson(tempID);

         foundPerson = true;

         if (result == null)
         {
            JOptionPane.showMessageDialog(null, "Person with ID: " + tempID
                  + " does not exist", "Warning!", JOptionPane.WARNING_MESSAGE);
         }

         nameField.setText(result.getName());
         addressField.setText(result.getAddress());
         emailField.setText(result.getEmail());
      }
   }

   public class ListenToSearch implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == searchButton)
         {
            nameField.setText("");
            addressField.setText("");
            emailField.setText("");

            String tempID = idField.getText();

            if (tempID.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "Empty ID Field! \nPlease fill out the ID field.",
                     "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               Person result = library.getPerson(tempID);

               foundPerson = true;

               if (result == null)
               {
                  JOptionPane.showMessageDialog(null, "Account with ID: "
                        + tempID + " does not exist", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
               }

               nameField.setText(result.getName());
               addressField.setText(result.getAddress());
               emailField.setText(result.getEmail());
            }
         }
      }
   }

   public class ListenToEdit implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == editButton)
         {
            if (foundPerson)
            {
               nameField.setEditable(true);
               addressField.setEditable(true);
               emailField.setEditable(true);
            }
         }
      }
   }

   public class ListenToRemove implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == removeButton)
         {
            String idTemp = idField.getText();

            if (idTemp.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "Empty ID Field! \nPlease fill out the ID field.",
                     "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               // Check if a Person with the given id exists in the system
               Person temp = library.getPerson(idTemp);

               if (temp == null)
               {
                  JOptionPane.showMessageDialog(null, "Account with id: "
                        + idTemp + " does not exist.", "Warning!",
                        JOptionPane.WARNING_MESSAGE);
               }
               else
               {
                  // Check if a Person with the given id has borrowed any items
                  // and has not returned them yet
                  boolean hasBorrowed = false;

                  Borrowed[] borrowedList = library.getAdapter()
                        .getAllBorrowed();

                  for (int i = 0; i < borrowedList.length; i++)
                  {
                     if (borrowedList[i].getBorrower().getId().equals(idTemp))
                     {
                        hasBorrowed = true;
                     }
                  }

                  // Display a message saying this Person cannot be removed uf
                  // he has not returned the items
                  if (hasBorrowed)
                  {
                     JOptionPane
                           .showMessageDialog(
                                 null,
                                 "Cannot remove an account that has not returned items yet.",
                                 "Warning!", JOptionPane.WARNING_MESSAGE);
                  }
                  else
                  {
                     // Display confirmation message
                     int choice = JOptionPane
                           .showConfirmDialog(
                                 null,
                                 "Do you wish to remove this account from the system?",
                                 "Removal Confirmation",
                                 JOptionPane.YES_NO_OPTION);

                     if (choice == JOptionPane.YES_OPTION)
                     {
                        // Remove the Person from the Person file
                        library.removePerson(idTemp);

                        JOptionPane
                              .showMessageDialog(null,
                                    "You have successfully removed the account from the system");

                        idField.setText("");
                        nameField.setText("");
                        addressField.setText("");
                        emailField.setText("");
                        
                        nameField.setEditable(false);
                        addressField.setEditable(false);
                        emailField.setEditable(false);

                        // Remove any reservations that Person might have made
                        Reserved[] reservedList = library.getAdapter()
                              .getAllReserved();

                        for (int i = 0; i < reservedList.length; i++)
                        {
                           if (reservedList[i].getReserver().getId()
                                 .equals(idTemp))
                           {
                              String personID = reservedList[i].getReserver()
                                    .getId();
                              String title = reservedList[i].getItem()
                                    .getTitle();
                              String type = reservedList[i].getItem().getType();

                              library.cancelReservation(personID, title, type);
                           }
                        }

                     }
                  }
               }
            }
         }
      }
   }

   public class ListenToSave implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == saveButton)
         {
            if (foundPerson)
            {
               // Create a String to hold the ID from the id Field
               String tempID = idField.getText();

               String name, address, email;

               name = nameField.getText();
               address = addressField.getText();
               email = emailField.getText();

               // Error messages in case any of the fields is empty before
               // saving.
               if (name.equals(""))
               {
                  JOptionPane.showMessageDialog(null,
                        "Empty Name Field! \nPlease fill out the Name field.",
                        "Error!", JOptionPane.ERROR_MESSAGE);
                  return;
               }
               else if (address.equals(""))
               {
                  JOptionPane
                        .showMessageDialog(
                              null,
                              "Empty Address Field! \nPlease fill out the Address field.",
                              "Error!", JOptionPane.ERROR_MESSAGE);
                  return;
               }
               else if (email.equals(""))
               {
                  JOptionPane
                        .showMessageDialog(
                              null,
                              "Empty Email Field! \nPlease fill out the Email field.",
                              "Error!", JOptionPane.ERROR_MESSAGE);
                  return;
               }

               // Confirmation message before saving the changes
               int choice = JOptionPane.showConfirmDialog(null,
                     "Do you want to save your changes?", "Confirmation",
                     JOptionPane.YES_NO_OPTION);

               if (choice == JOptionPane.YES_OPTION)
               {
                  // Get all the Persons stored in the system to find the Person
                  // with the given ID and then replace his data with the data
                  // given in the text Fields
                  Person[] personList = library.getAdapter().getAllPersons();
                  if (personList != null)
                     for (int i = 0; i < personList.length; i++)
                     {
                        if (personList[i].getId().equals(tempID))
                        {
                           personList[i].setName(name);
                           personList[i].setAddress(address);
                           personList[i].setEmail(email);
                        }
                     }

                  // Get all the Borrowers stored in the system to find the
                  // Person
                  // with the given ID and then replace his data with the data
                  // given in the text Fields
                  Borrowed[] borrowedList = library.getAdapter()
                        .getAllBorrowed();
                  if (borrowedList != null)
                     for (int i = 0; i < borrowedList.length; i++)
                     {
                        if (borrowedList[i].getBorrower().getId()
                              .equals(tempID))
                        {
                           borrowedList[i].getBorrower().setName(name);
                           borrowedList[i].getBorrower().setAddress(address);
                           borrowedList[i].getBorrower().setEmail(email);
                        }
                     }

                  // Get all the Reservers stored in the system to find the
                  // Person
                  // with the given ID and then replace his data with the data
                  // given in the text Fields
                  Reserved[] reservedList = library.getAdapter()
                        .getAllReserved();

                  if (reservedList != null)

                     for (int i = 0; i < reservedList.length; i++)
                     {
                        if (reservedList[i].getReserver().getId()
                              .equals(tempID))
                        {
                           reservedList[i].getReserver().setName(name);
                           reservedList[i].getReserver().setAddress(address);
                           reservedList[i].getReserver().setEmail(email);
                        }
                     }

                  // Save the Person to the file
                  try
                  {
                     if (personList != null)
                        library.getAdapter().savePerson(personList);
                     if (borrowedList != null)
                        library.getAdapter().saveBorrowed(borrowedList);
                     if (reservedList != null)
                        library.getAdapter().saveReserved(reservedList);
                  }
                  catch (Exception ex)
                  {
                     System.out.println("Error saving person.");
                     JOptionPane.showMessageDialog(null,
                           "Error! Could not save changes.", "Error!",
                           JOptionPane.ERROR_MESSAGE);
                  }

                  // Make all the text fields non-editable again
                  nameField.setEditable(false);
                  addressField.setEditable(false);
                  emailField.setEditable(false);

                  JOptionPane.showMessageDialog(null,
                        "Changes saved successfully.", "",
                        JOptionPane.NO_OPTION);
               }
            }
         }
      }
   }

//   public static void main(String[] args)
//   {
//      EditUser test = new EditUser();
//      JFrame frame = new JFrame();
//      // frame.setLayout(new FlowLayout());
//      frame.add(test);
//      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//      frame.setVisible(true);
//      frame.setResizable(true);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//   }
}

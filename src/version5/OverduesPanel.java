package version5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OverduesPanel extends JPanel
{
   private Library library;
   private JPanel notifyPanel, overduePanel, spacer, tables, info;
   private JLabel lab1, lab2, emailLabel;
   private JTextField emailField;
   private JTextArea messageArea;
   private JButton emailSentButton;
   private JCheckBox checkbox;
   private JTable tableForEmails, tableForOverdues;
   private JScrollPane scrollPane, scrollPane2;
   
   private JTextField addressField;
   private JLabel addressLabel;

   public OverduesPanel(Library l)
   {
      library = l;

      setLayout(new FlowLayout());

      // Create the tables panel
      tables = new JPanel();
      tables.setPreferredSize(new Dimension(450, 700));
      tables.setLayout(new FlowLayout());

      // Create the Persons to notify table
      generateEmailTable();

      // Create the Overdue items table
      generateOverdueTable();

      // Add scroll pane to the first table
      scrollPane = new JScrollPane(tableForEmails);
      notifyPanel = new JPanel();
      notifyPanel.setLayout(new BorderLayout());
      lab1 = new JLabel("Persons to Notify");
      notifyPanel.add(lab1, BorderLayout.NORTH);
      notifyPanel.add(scrollPane, BorderLayout.CENTER);

      // Add scroll pane to the second table
      scrollPane2 = new JScrollPane(tableForOverdues);
      overduePanel = new JPanel();
      overduePanel.setLayout(new BorderLayout());
      lab2 = new JLabel("Overdue List");
      overduePanel.add(lab2, BorderLayout.NORTH);
      overduePanel.add(scrollPane2, BorderLayout.CENTER);

      spacer = new JPanel(); // Occupies the bottom of the tables panel

      // Set the size for the 3 panels
      notifyPanel.setPreferredSize(new Dimension(450, 280));
      overduePanel.setPreferredSize(new Dimension(450, 370));

      // Add the 3 panels to the tables panel
      tables.add(notifyPanel);
      tables.add(overduePanel);

      add(tables);

      // Create the info panel
      info = new JPanel();
      info.setLayout(new BorderLayout());
      info.setPreferredSize(new Dimension(400, 300));

      emailLabel = new JLabel("Email:");
      emailField = new JTextField(20);
      displayEmail();
      addressLabel = new JLabel("Address:");
      addressField = new JTextField(20);
      displayAddress();
      messageArea = new JTextArea(10, 30);
      displayMessageInTextArea();
      checkbox = new JCheckBox();
      emailSentButton = new JButton("Email Sent");
      emailSentButton.setPreferredSize(new Dimension(100, 45));
      emailSentButton.addActionListener(new MyButtonListener());
      
      JPanel gbaglayout = new JPanel(new GridBagLayout());
      
      GridBagConstraints gbc = new GridBagConstraints();
      
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.insets = new Insets(2, 2, 2, 2);
      gbaglayout.add(emailLabel, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.insets = new Insets(2, 2, 2, 2);
      gbaglayout.add(emailField, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.insets = new Insets(2, 2, 2, 2);
      gbaglayout.add(addressLabel, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.insets = new Insets(2, 2, 2, 2);
      gbaglayout.add(addressField, gbc);
      
      JPanel north = new JPanel();
//      north.add(emailLabel);
//      north.add(emailField);
//      
//      north.add(addressLabel);
//      north.add(addressField);
      north.add(gbaglayout);

      JPanel center = new JPanel();
      JScrollPane messageScroll = new JScrollPane(messageArea);
      center.add(messageScroll);

      JPanel south = new JPanel();
      south.add(checkbox);
      south.add(emailSentButton);

      info.add(north, BorderLayout.NORTH);
      info.add(center, BorderLayout.CENTER);
      info.add(south, BorderLayout.SOUTH);

      // Create a container panel and add the info panel in it
      JPanel pn = new JPanel();
      pn.add(info);

      add(pn);
   }

   public void generateEmailTable()
   {
      Borrowed[] items = library.isPast();

      DefaultTableModel dtm = new DefaultTableModel();
      dtm.setRowCount(items.length);
      dtm.addColumn("Name");
      dtm.addColumn("Email");
      dtm.addColumn("Title");
      dtm.addColumn("Return date");

      if (items != null)
      {
         for (int i = 0; i < items.length; i++)
         {
            dtm.setValueAt(items[i].getBorrower().getName(), i, 0);
            dtm.setValueAt(items[i].getBorrower().getEmail(), i, 1);
            dtm.setValueAt(items[i].getItem().getTitle(), i, 2);
            dtm.setValueAt(items[i].getReturnDate(), i, 3);
         }
      }
      tableForEmails = new JTable(dtm);
   }

   public void generateOverdueTable()
   {
      String[] columnNames = { "ID", "Email", "Title", "Return Date" };
      Borrowed[] itemsOverdue = library.getAllOverdueItems();
      Object[][] objList2 = new Object[itemsOverdue.length][4];

      if (itemsOverdue != null)
      {
         for (int j = 0; j < objList2.length; j++)
         {
            objList2[j][0] = itemsOverdue[j].getBorrower().getId();
            objList2[j][1] = itemsOverdue[j].getBorrower().getEmail();
            objList2[j][2] = itemsOverdue[j].getItem().getTitle();
            objList2[j][3] = itemsOverdue[j].getReturnDate();
         }
      }

      tableForOverdues = new JTable(objList2, columnNames);
   }

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == emailSentButton)
         {
            if (checkbox.isSelected())
            {
               Borrowed[] temp = library.isPast();
               if (temp.length > 0)
               {
                  String titleFirst = temp[0].getItem().getTitle();
                  library.removeFromIsPast(titleFirst);

                  Borrowed[] twodaysafter = library.isPast();

                  DefaultTableModel model = (DefaultTableModel) tableForEmails
                        .getModel();
                  model.setRowCount(twodaysafter.length);

                  for (int i = 0; i < twodaysafter.length; i++)
                  {
                     tableForEmails.getModel().setValueAt(
                           twodaysafter[i].getBorrower().getName(), i, 0);
                     tableForEmails.getModel().setValueAt(
                           twodaysafter[i].getBorrower().getEmail(), i, 1);
                     tableForEmails.getModel().setValueAt(
                           twodaysafter[i].getItem().getTitle(), i, 2);
                     tableForEmails.getModel().setValueAt(
                           twodaysafter[i].getReturnDate(), i, 3);
                     emailField.setText(twodaysafter[0].getBorrower()
                           .getEmail());

                  }
               }
               checkbox.setSelected(false);
               displayEmail();
               displayAddress();
               displayMessageInTextArea();
            }
         }
      }
   }

   private void displayEmail()
   {
      Borrowed[] other = library.isPast();

      if (other.length != 0)
      {
         emailField.setText(other[0].getBorrower().getEmail());
      }
      else
      {
         emailField.setText("");
      }
   }
   
   private void displayAddress()
   {
	   Borrowed[] other = library.isPast();
	   
	   if(other.length != 0)
	   {
		   addressField.setText(other[0].getBorrower().getAddress());
	   }
	   else
	   {
		   addressField.setText("");
	   }
   }

   private void displayMessageInTextArea()
   {
      Borrowed[] other = library.isPast();

      if (other.length != 0)
      {
         messageArea
               .setText("Dear "
                     + other[0].getBorrower().getName()
                     + ",\n\nThe borrowing period for the item '"
                     + other[0].getItem().getTitle()
                     + "' expired two days ago on \n"
                     + other[0].getReturnDate()
                     + ". A fine of $5 has been issued to you. \nPlease make sure to return the item as soon as possible."
                     + "\n\nWith kind regards, \nFairytale Libary");

      }
      else
      {
         messageArea.setText("");
      }
   }
}

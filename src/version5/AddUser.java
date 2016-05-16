package version5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Box.Filler;

public class AddUser extends JFrame
{
   private Library library;
   private JLabel firstName;
   private JLabel lastName;

   private JLabel address;
   private JLabel email;
   private JLabel studentOrTeacher;
   private JLabel ID;

   private JTextField firstNameBox;
   private JTextField lastNameBox;
   private JTextField addressBox;
   private JTextField emailBox;
   private JTextField IDBox;

   private JRadioButton teacher;
   private JRadioButton student;

   private JButton create;

   private MyButtonListener listener;

   public AddUser(Library l)
   {
      super();
      setLayout(new FlowLayout());
      library = l;
      listener = new MyButtonListener();

      JPanel temp = new JPanel();
      temp.setLayout(new BoxLayout(temp, BoxLayout.PAGE_AXIS));

      firstName = new JLabel();
      firstName.setText("First name:");

      firstNameBox = new JTextField(10);
      JPanel firstNamePanel = new JPanel();
      firstNamePanel.setLayout(new GridLayout(1, 2));

      firstNamePanel.add(firstName);
      firstNamePanel.add(firstNameBox);

      temp.add(firstNamePanel);

      JPanel lastNamePanel = new JPanel();
      lastNamePanel.setLayout(new GridLayout(1, 2));

      lastName = new JLabel();
      lastName.setText("Last name:");

      lastNameBox = new JTextField(10);

      lastNamePanel.add(lastName);
      lastNamePanel.add(lastNameBox);

      temp.add(lastNamePanel);

      JPanel emailPane = new JPanel();
      emailPane.setLayout(new GridLayout(1, 2));

      email = new JLabel();
      email.setText("Email address:");

      emailBox = new JTextField(10);

      emailPane.add(email);
      emailPane.add(emailBox);

      temp.add(emailPane);

      JPanel addressPanel = new JPanel();
      addressPanel.setLayout(new GridLayout(1, 2));

      address = new JLabel();
      address.setText("Address:");

      addressBox = new JTextField(10);

      addressPanel.add(address);
      addressPanel.add(addressBox);

      temp.add(addressPanel);

      JPanel chooseType = new JPanel();
      chooseType.setLayout(new GridLayout(1, 3));

      studentOrTeacher = new JLabel();
      studentOrTeacher.setText("Teacher or student?");

      student = new JRadioButton("Student");
      student.addActionListener(listener);

      teacher = new JRadioButton("Teacher");
      teacher.addActionListener(listener);

      ButtonGroup group = new ButtonGroup();
      group.add(student);
      group.add(teacher);

      chooseType.add(studentOrTeacher);
      chooseType.add(new Box.Filler(new Dimension(20, 10),
            new Dimension(20, 10), new Dimension(20, 10)));
      chooseType.add(student);
      chooseType.add(teacher);

      temp.add(chooseType);

      JPanel idPanel = new JPanel();
      idPanel.setLayout(new GridLayout(1, 2));

      ID = new JLabel();
      ID.setText("ID:");

      IDBox = new JTextField(10);

      idPanel.add(ID);
      idPanel.add(IDBox);

      temp.add(idPanel);

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

      create = new JButton("Create account");
      create.addActionListener(listener);

      buttonPanel.add(create);

      temp.add(buttonPanel);

      add(temp);

      // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(500, 230);
      setVisible(true);

   }

   public class MyButtonListener implements ActionListener
   {

      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == create)
         {
            System.out.println("clicked");
            String firstNameTemp;

            firstNameTemp = firstNameBox.getText();

            String lastNameTemp;
            lastNameTemp = lastNameBox.getText();

            String address;
            address = addressBox.getText();

            String email;
            email = emailBox.getText();

            String id;
            id = IDBox.getText();

            if (firstNameTemp.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "You need to fill out the first name field.", "Error!",
                     JOptionPane.ERROR_MESSAGE);
            }
            else if (lastNameTemp.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "You need to fill out the last name field.", "Error!",
                     JOptionPane.ERROR_MESSAGE);
            }
            else if (address.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "You need to fill out the address field.", "Error!",
                     JOptionPane.ERROR_MESSAGE);
            }
            else if (email.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "You need to fill out the email address field.", "Error!",
                     JOptionPane.ERROR_MESSAGE);
            }
            else if (id.equals(""))
            {
               JOptionPane.showMessageDialog(null,
                     "Empty ID Field! \nYou need to fill out the ID field.",
                     "Error!", JOptionPane.ERROR_MESSAGE);
            }
            else if (!student.isSelected() && !teacher.isSelected())
            {
               JOptionPane.showMessageDialog(null,
                     "You need to select either student or teacher.", "Error!",
                     JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               Person temp = null;
               temp = library.getPerson(id);
               if (temp != null)
               {
                  JOptionPane
                        .showMessageDialog(
                              null,
                              "An account with that ID already exists in the system!",
                              "Error!", JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                  if (teacher.isSelected())
                  {
                     String fullName;
                     fullName = firstNameTemp + " " + lastNameTemp;

                     library.addPerson(new Person(fullName, id, address, email,
                           true));
                     JOptionPane.showMessageDialog(null,
                           "You have successfully added " + fullName
                                 + " as a teacher." + "\nID: " + id);

                  }
                  if (student.isSelected())
                  {
                     String fullName;
                     fullName = firstNameTemp + " " + lastNameTemp;

                     library.addPerson(new Person(fullName, id, address, email,
                           false));
                     JOptionPane.showMessageDialog(null,
                           "You have successfully added " + fullName
                                 + " as a student." + "\nID: " + id);

                  }

                  firstNameBox.setText("");
                  lastNameBox.setText("");
                  emailBox.setText("");
                  addressBox.setText("");
                  IDBox.setText("");
                  teacher.setSelected(false);
                  student.setSelected(false);
               }
            }
         }
      }
   }

//   public static void main(String[] args)
//   {
//      new AddUser();
//   }
}
